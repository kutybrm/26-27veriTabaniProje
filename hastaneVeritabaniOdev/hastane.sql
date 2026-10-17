create table poliklinik
(
    poliklinik_id  serial
        primary key,
    poliklinik_adi varchar(100) not null,
    bina_blok      varchar(20)
);

alter table poliklinik
    owner to postgres;

create table servis
(
    servis_id     serial
        primary key,
    servis_adi    varchar(100) not null,
    kat_no        integer,
    poliklinik_id integer      not null
        references poliklinik
);

alter table servis
    owner to postgres;

create table hemsire
(
    hemsire_id serial
        primary key,
    ad         varchar(50) not null,
    soyad      varchar(50) not null,
    telefon    varchar(15),
    servis_id  integer     not null
        references servis
);

alter table hemsire
    owner to postgres;

create table doktor
(
    doktor_id     serial
        primary key,
    ad            varchar(50) not null,
    soyad         varchar(50) not null,
    unvan         varchar(50),
    poliklinik_id integer     not null
        references poliklinik
);

alter table doktor
    owner to postgres;

create table hasta
(
    hasta_id     serial
        primary key,
    tc_numarasi  char(11)    not null
        unique,
    ad           varchar(50) not null,
    soyad        varchar(50) not null,
    dogum_tarihi date,
    telefon      varchar(15)
);

alter table hasta
    owner to postgres;

create table laboratuvar
(
    laboratuvar_id serial
        primary key,
    lab_adi        varchar(100) not null,
    oda_no         varchar(20)
);

alter table laboratuvar
    owner to postgres;

create table tahlil
(
    tahlil_id      serial
        primary key,
    tahlil_adi     varchar(100) not null,
    aciklama       text,
    laboratuvar_id integer      not null
        references laboratuvar
);

alter table tahlil
    owner to postgres;

create table muayene_olur
(
    poliklinik_id integer   not null
        references poliklinik,
    hasta_id      integer   not null
        references hasta,
    tarih         timestamp not null,
    sikayet       text,
    primary key (poliklinik_id, hasta_id, tarih)
);

alter table muayene_olur
    owner to postgres;

create table ister_yaptirir
(
    hasta_id     integer   not null
        references hasta,
    doktor_id    integer   not null
        references doktor,
    tahlil_id    integer   not null
        references tahlil,
    talep_tarihi timestamp not null,
    sonuc        text,
    primary key (hasta_id, doktor_id, tahlil_id, talep_tarihi)
);

alter table ister_yaptirir
    owner to postgres;


