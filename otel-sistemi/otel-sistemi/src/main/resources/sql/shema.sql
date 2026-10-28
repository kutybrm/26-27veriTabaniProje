create type kullanicirol as enum ('Misafir', 'Resepsiyon', 'Yonetici', 'Personel', 'Temizlik', 'Muhasebe');

alter type kullanicirol owner to postgres;

create type rezervasyondurumu as enum ('Beklemede', 'Onaylandi', 'IptalEdildi', 'Tamamlandi', 'NoShow');

alter type rezervasyondurumu owner to postgres;

create type odadurumu as enum ('Bos', 'Dolu', 'Temizlikte', 'Bakimda');

alter type odadurumu owner to postgres;

create type konaklamadurumu as enum ('Aktif', 'CikisYapildi');

alter type konaklamadurumu owner to postgres;

create type odemeyontemi as enum ('Nakit', 'KrediKarti', 'Havale');

alter type odemeyontemi owner to postgres;

create type temizlikdurumu as enum ('Bekliyor', 'DevamEdiyor', 'Tamamlandi');

alter type temizlikdurumu owner to postgres;

create table kullanici
(
    kullaniciid serial
        primary key,
    eposta      varchar(255) not null
        unique,
    sifrehash   varchar(255) not null,
    rol         kullanicirol not null
);

alter table kullanici
    owner to postgres;

create index idx_kullanici_eposta
    on kullanici (eposta);

create table odatipi
(
    tipid       serial
        primary key,
    tipadi      varchar(100)   not null,
    kapasite    integer        not null
        constraint odatipi_kapasite_check
            check (kapasite > 0),
    temelfiyat  numeric(10, 2) not null
        constraint odatipi_temelfiyat_check
            check (temelfiyat >= (0)::numeric),
    odaaciklama text
);

alter table odatipi
    owner to postgres;

create table oda
(
    odaid       serial
        primary key,
    odanumarasi integer                            not null
        unique,
    tipid       integer                            not null
        references odatipi,
    kat         integer                            not null,
    durum       odadurumu default 'Bos'::odadurumu not null
);

alter table oda
    owner to postgres;

create index idx_oda_numarasi
    on oda (odanumarasi);

create table sezonfiyati
(
    sezonfiyatiid   serial
        primary key,
    tipid           integer        not null
        references odatipi,
    baslangictarihi date           not null,
    bitistarihi     date           not null,
    gecelikfiyat    numeric(10, 2) not null
        constraint sezonfiyati_gecelikfiyat_check
            check (gecelikfiyat >= (0)::numeric),
    constraint sezonfiyati_check
        check (bitistarihi > baslangictarihi)
);

alter table sezonfiyati
    owner to postgres;

create table misafir
(
    misafirid   serial
        primary key,
    kullaniciid integer
        unique
        references kullanici,
    ad          varchar(100) not null,
    soyad       varchar(100) not null,
    tcno        varchar(11)
        unique,
    pasaportno  varchar(50)
        unique,
    telefonno   varchar(20)  not null,
    dogumtarihi date
);

alter table misafir
    owner to postgres;

create table personel
(
    personelid     serial
        primary key,
    kullaniciid    integer      not null
        unique
        references kullanici,
    ad             varchar(100) not null,
    soyad          varchar(100) not null,
    gorev          varchar(100) not null,
    isegiristarihi date         not null
);

alter table personel
    owner to postgres;

create table rezervasyon
(
    rezervasyonid   serial
        primary key,
    misafirid       integer                                                  not null
        references misafir,
    odaid           integer                                                  not null
        references oda,
    giristarihi     date                                                     not null,
    cikistarihi     date                                                     not null,
    kisisayisi      integer                                                  not null
        constraint rezervasyon_kisisayisi_check
            check (kisisayisi > 0),
    durum           rezervasyondurumu default 'Beklemede'::rezervasyondurumu not null,
    olusturmatarihi timestamp         default CURRENT_TIMESTAMP              not null,
    constraint rezervasyon_check
        check (cikistarihi > giristarihi)
);

alter table rezervasyon
    owner to postgres;

create index idx_rezervasyon_tarihleri
    on rezervasyon (giristarihi, cikistarihi);

create table rezervasyonmisafir
(
    rezervasyonmisafirid serial
        primary key,
    rezervasyonid        integer not null
        references rezervasyon
            on delete cascade,
    misafirid            integer not null
        references misafir,
    unique (rezervasyonid, misafirid)
);

alter table rezervasyonmisafir
    owner to postgres;

create table temizlikkaydi
(
    temizlikkaydiid serial
        primary key,
    odaid           integer                                           not null
        references oda,
    personelid      integer                                           not null
        references personel,
    temizliktarihi  date                                              not null,
    durum           temizlikdurumu default 'Bekliyor'::temizlikdurumu not null
);

alter table temizlikkaydi
    owner to postgres;

create table konaklama
(
    konaklamaid       serial
        primary key,
    rezervasyonid     integer                                          not null
        unique
        references rezervasyon,
    gercekgiristarihi timestamp       default CURRENT_TIMESTAMP        not null,
    gercekcikistarihi timestamp,
    personelid        integer                                          not null
        references personel,
    durum             konaklamadurumu default 'Aktif'::konaklamadurumu not null
);

alter table konaklama
    owner to postgres;

create table konaklamaodagecmisi
(
    gecmisid        serial
        primary key,
    konaklamaid     integer   not null
        references konaklama,
    odaid           integer   not null
        references oda,
    baslangictarihi timestamp not null,
    bitistarihi     timestamp
);

alter table konaklamaodagecmisi
    owner to postgres;

create table hizmet
(
    hizmetid   serial
        primary key,
    ad         varchar(100)   not null,
    birimfiyat numeric(10, 2) not null
        constraint hizmet_birimfiyat_check
            check (birimfiyat >= (0)::numeric),
    aciklama   text
);

alter table hizmet
    owner to postgres;

create table konaklamahizmeti
(
    konaklamahizmetiid serial
        primary key,
    konaklamaid        integer                             not null
        references konaklama,
    hizmetid           integer                             not null
        references hizmet,
    adet               integer   default 1                 not null
        constraint konaklamahizmeti_adet_check
            check (adet > 0),
    birimfiyat         numeric(10, 2)                      not null
        constraint konaklamahizmeti_birimfiyat_check
            check (birimfiyat >= (0)::numeric),
    islemtarihi        timestamp default CURRENT_TIMESTAMP not null
);

alter table konaklamahizmeti
    owner to postgres;

create table odeme
(
    odemeid     serial
        primary key,
    konaklamaid integer                             not null
        references konaklama,
    tutar       numeric(10, 2)                      not null
        constraint odeme_tutar_check
            check (tutar > (0)::numeric),
    yontem      odemeyontemi                        not null,
    islemtarihi timestamp default CURRENT_TIMESTAMP not null
);

alter table odeme
    owner to postgres;

create table islemlog
(
    logid       serial
        primary key,
    kullaniciid integer
        references kullanici,
    tabloadi    varchar(100)                        not null,
    islemturu   varchar(100)                        not null,
    aciklama    text                                not null,
    islemtarihi timestamp default CURRENT_TIMESTAMP not null
);

alter table islemlog
    owner to postgres;

create view vw_gunluk_hareketlilik(misafir, telefonno, odanumarasi, islemturu, tarih) as
SELECT (m.ad::text || ' '::text) || m.soyad::text AS misafir,
    m.telefonno,
       o.odanumarasi,
       'Giriş Yapacak'::text                      AS islemturu,
    r.giristarihi                              AS tarih
FROM rezervasyon r
         JOIN misafir m ON r.misafirid = m.misafirid
         JOIN oda o ON r.odaid = o.odaid
WHERE r.giristarihi = CURRENT_DATE
  AND (r.durum <> ALL (ARRAY ['IptalEdildi'::rezervasyondurumu, 'NoShow'::rezervasyondurumu]))
UNION ALL
SELECT (m.ad::text || ' '::text) || m.soyad::text AS misafir,
    m.telefonno,
       o.odanumarasi,
       'Çıkış Yapacak'::text                      AS islemturu,
    r.cikistarihi                              AS tarih
FROM rezervasyon r
         JOIN misafir m ON r.misafirid = m.misafirid
         JOIN oda o ON r.odaid = o.odaid
WHERE r.cikistarihi = CURRENT_DATE
  AND (r.durum <> ALL (ARRAY ['IptalEdildi'::rezervasyondurumu, 'NoShow'::rezervasyondurumu]));

alter table vw_gunluk_hareketlilik
    owner to postgres;

create view vw_oda_guncel_durum(odanumarasi, tipadi, odadurumu, sontemizliktarihi) as
SELECT o.odanumarasi,
       ot.tipadi,
       o.durum                    AS odadurumu,
       (SELECT max(tk.temizliktarihi) AS max
    FROM temizlikkaydi tk
    WHERE tk.odaid = o.odaid) AS sontemizliktarihi
    FROM oda o
    JOIN odatipi ot ON o.tipid = ot.tipid;

alter table vw_oda_guncel_durum
    owner to postgres;

create function fn_rezervasyon_cakisma_kontrol() returns trigger
    language plpgsql
as
$$
BEGIN
    IF EXISTS (
        SELECT 1
        FROM Rezervasyon
        WHERE OdaID = NEW.OdaID
          AND RezervasyonID IS DISTINCT FROM NEW.RezervasyonID 
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

create function sp_bos_odalari_getir(p_giris date, p_cikis date, p_tip_id integer)
    returns TABLE(odanumarasi integer, kat integer, tipadi character varying)
    language plpgsql
as
$$
BEGIN
RETURN QUERY
SELECT o.OdaNumarasi, o.Kat, ot.TipAdi::VARCHAR
FROM Oda o
         JOIN OdaTipi ot ON o.TipID = ot.TipID
WHERE o.TipID = p_tip_id
  AND o.OdaID NOT IN (
    SELECT r.OdaID
    FROM Rezervasyon r
    WHERE r.Durum IN ('Beklemede', 'Onaylandi')
      AND r.GirisTarihi < p_cikis
      AND r.CikisTarihi > p_giris
);
END;
$$;

alter function sp_bos_odalari_getir(date, date, integer) owner to postgres;

create function sp_konaklama_tutar_hesapla(p_konaklama_id integer) returns numeric
    language plpgsql
as
$$
DECLARE
v_toplam_oda_tutari NUMERIC(10, 2) := 0;
    v_hizmet_tutari NUMERIC(10, 2) := 0;
    v_oda_tip_id INT;
    v_giris DATE;
    v_cikis DATE;
    v_gun DATE;
    v_gunluk_fiyat NUMERIC(10, 2);
BEGIN
SELECT r.GirisTarihi, r.CikisTarihi, o.TipID
INTO v_giris, v_cikis, v_oda_tip_id
FROM Konaklama k
         JOIN Rezervasyon r ON k.RezervasyonID = r.RezervasyonID
         JOIN Oda o ON r.OdaID = o.OdaID
WHERE k.KonaklamaID = p_konaklama_id;

v_gun := v_giris;
    WHILE v_gun < v_cikis LOOP
SELECT COALESCE(
               (SELECT GecelikFiyat FROM SezonFiyati
                WHERE TipID = v_oda_tip_id AND v_gun >= BaslangicTarihi AND v_gun <= BitisTarihi
               LIMIT 1),
            (SELECT TemelFiyat FROM OdaTipi WHERE TipID = v_oda_tip_id)
        ) INTO v_gunluk_fiyat;

v_toplam_oda_tutari := v_toplam_oda_tutari + v_gunluk_fiyat;

        v_gun := v_gun + 1;
END LOOP;

SELECT COALESCE(SUM(Adet * BirimFiyat), 0)
INTO v_hizmet_tutari
FROM KonaklamaHizmeti
WHERE KonaklamaID = p_konaklama_id;

RETURN v_toplam_oda_tutari + v_hizmet_tutari;
END;
$$;

alter function sp_konaklama_tutar_hesapla(integer) owner to postgres;

create function sp_aylik_rapor(p_yil integer, p_ay integer)
    returns TABLE(raporyili integer, raporayi integer, toplamrezervasyonsayisi bigint, ayliktoplamgelir numeric)
    language plpgsql
as
$$
BEGIN
RETURN QUERY
SELECT
    p_yil AS RaporYili,
    p_ay AS RaporAyi,

    (SELECT COUNT(*)
     FROM Rezervasyon
     WHERE EXTRACT(YEAR FROM GirisTarihi) = p_yil
       AND EXTRACT(MONTH FROM GirisTarihi) = p_ay) AS ToplamRezervasyonSayisi,

    COALESCE((SELECT SUM(Tutar)
              FROM Odeme
              WHERE EXTRACT(YEAR FROM IslemTarihi) = p_yil
                AND EXTRACT(MONTH FROM IslemTarihi) = p_ay), 0.00) AS AylikToplamGelir;
END;
$$;

alter function sp_aylik_rapor(integer, integer) owner to postgres;

create procedure sp_checkout_yap(IN p_konaklama_id integer, IN p_odeme_yontemi odemeyontemi)
    language plpgsql
as
$$
DECLARE
v_toplam_tutar NUMERIC(10, 2);
BEGIN
SELECT sp_konaklama_tutar_hesapla(p_konaklama_id) INTO v_toplam_tutar;

UPDATE Konaklama
SET Durum = 'CikisYapildi',
    GercekCikisTarihi = CURRENT_TIMESTAMP
WHERE KonaklamaID = p_konaklama_id AND Durum = 'Aktif';

IF NOT FOUND THEN
        RAISE EXCEPTION 'Aktif bir konaklama bulunamadı veya zaten çıkış yapılmış!';
END IF;

INSERT INTO Odeme (KonaklamaID, Tutar, Yontem)
VALUES (p_konaklama_id, v_toplam_tutar, p_odeme_yontemi);

COMMIT;

EXCEPTION
    WHEN OTHERS THEN
        ROLLBACK;
        RAISE EXCEPTION 'Check out işlemi sırasında hata oluştu bundan ötürü sistem eski haline döndürüldü. Hata: %', SQLERRM;
END;
$$;

alter procedure sp_checkout_yap(integer, odemeyontemi) owner to postgres;

