create function fn_rezervasyon_cakisma_kontrol() returns trigger
    language plpgsql
as
$$
BEGIN
    IF EXISTS (
        SELECT 1
        FROM Rezervasyon
        WHERE OdaID = NEW.OdaID
          AND RezervasyonID IS DISTINCT FROM NEW.RezervasyonID --burda is distincti ekledik ki != ile karşılaştırmada null çıkarsa işler bozulmasın.
          AND Durum IN ('Beklemede', 'Onaylandi')
          AND NEW.GirisTarihi < CikisTarihi
          AND NEW.CikisTarihi > GirisTarihi
    ) THEN
        RAISE EXCEPTION 'İşlem Reddedildi: Bu oda belirtilen tarihlerde dolu!';
    END IF;

    RETURN NEW;
END;
$$;

alter function fn_rezervasyon_cakisma_kontrol() owner to postgres;

create trigger trg_rezervasyon_cakisma
    before insert or update
    on rezervasyon
    for each row
execute procedure fn_rezervasyon_cakisma_kontrol();

create function fn_rezervasyon_logla() returns trigger
    language plpgsql
as
$$
DECLARE
    v_kullanici_id INT;
BEGIN
    SELECT KullaniciID INTO v_kullanici_id
    FROM Misafir
    WHERE MisafirID = NEW.MisafirID;

    IF OLD.Durum IS DISTINCT FROM NEW.Durum THEN
        INSERT INTO IslemLog (KullaniciID, TabloAdi, IslemTuru, Aciklama)
        VALUES (
            v_kullanici_id,
            'Rezervasyon',
            'UPDATE',
            'Durum değiştirildi: ' || OLD.Durum || ' -> ' || NEW.Durum
        );
    END IF;

    RETURN NEW;
END;
$$;

alter function fn_rezervasyon_logla() owner to postgres;

create trigger trg_rezervasyon_log
    after update
    on rezervasyon
    for each row
execute procedure fn_rezervasyon_logla();

create function fn_oda_durum_guncelle() returns trigger
    language plpgsql
as
$$
DECLARE
    v_oda_id INT;
BEGIN
    SELECT OdaID INTO v_oda_id
    FROM Rezervasyon
    WHERE RezervasyonID = NEW.RezervasyonID;

    IF TG_OP = 'INSERT' OR (TG_OP = 'UPDATE' AND NEW.Durum = 'Aktif') THEN
        UPDATE Oda SET Durum = 'Dolu' WHERE OdaID = v_oda_id;

    ELSIF TG_OP = 'UPDATE' AND NEW.Durum = 'CikisYapildi' AND OLD.Durum != 'CikisYapildi' THEN
        UPDATE Oda SET Durum = 'Temizlikte' WHERE OdaID = v_oda_id;
    END IF;

    RETURN NEW;
END;
$$;

alter function fn_oda_durum_guncelle() owner to postgres;

create trigger trg_oda_durum_otomasyonu
    after insert or update
    on konaklama
    for each row
execute procedure fn_oda_durum_guncelle();

create function fn_odeme_logla() returns trigger
    language plpgsql
as
$$
BEGIN
    IF TG_OP = 'INSERT' THEN
        INSERT INTO IslemLog (TabloAdi, IslemTuru, Aciklama)
        VALUES (
            'Odeme',
            'INSERT',
            'Yeni ödeme alındı. Tutar: ' || NEW.Tutar || ' TL, Yöntem: ' || NEW.Yontem
        );
    ELSIF TG_OP = 'UPDATE' THEN
        INSERT INTO IslemLog (TabloAdi, IslemTuru, Aciklama)
        VALUES (
            'Odeme',
            'UPDATE',
            'Ödeme güncellendi. Eski Tutar: ' || OLD.Tutar || ' -> Yeni Tutar: ' || NEW.Tutar
        );
    END IF;

    RETURN NEW;
END;
$$;

alter function fn_odeme_logla() owner to postgres;

create trigger trg_odeme_log
    after insert or update
    on odeme
    for each row
execute procedure fn_odeme_logla();

