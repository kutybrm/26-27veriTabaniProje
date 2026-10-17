create type kullanicirol as enum ('Misafir', 'Resepsiyon', 'Yonetici');

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
            check (birimfiyat >= (0)::numeric)
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

--tüm tabloları şema olarak triggerlardan ayırdık. 
--viewlar yapulacak. (en az 2)
--stored procedure yapılacak. (en az 3)
--transaction yazılacak.
--raporlama yapılacak örnek veri girilecek
