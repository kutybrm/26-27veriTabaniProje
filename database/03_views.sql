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
