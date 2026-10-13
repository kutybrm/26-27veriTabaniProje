
CREATE TYPE KullaniciRol AS ENUM ('Misafir', 'Resepsiyon', 'Yonetici');
CREATE TYPE RezervasyonDurumu AS ENUM ('Beklemede', 'Onaylandi', 'IptalEdildi', 'Tamamlandi', 'NoShow');
CREATE TYPE OdaDurumu AS ENUM ('Bos', 'Dolu', 'Temizlikte', 'Bakimda');
CREATE TYPE KonaklamaDurumu AS ENUM ('Aktif', 'CikisYapildi');
CREATE TYPE OdemeYontemi AS ENUM ('Nakit', 'KrediKarti', 'Havale');
CREATE TYPE TemizlikDurumu AS ENUM ('Bekliyor', 'DevamEdiyor', 'Tamamlandi');

CREATE TABLE Kullanici (
    KullaniciID SERIAL PRIMARY KEY,
    Eposta VARCHAR(255) NOT NULL UNIQUE,
    SifreHash VARCHAR(255) NOT NULL,
    Rol KullaniciRol NOT NULL
);

CREATE TABLE OdaTipi (
    TipID SERIAL PRIMARY KEY,
    TipAdi VARCHAR(100) NOT NULL,
    Kapasite INT NOT NULL CHECK (Kapasite > 0),
    TemelFiyat DECIMAL(10, 2) NOT NULL CHECK (TemelFiyat >= 0),
    OdaAciklama TEXT
);

CREATE TABLE Oda (
    OdaID SERIAL PRIMARY KEY,
    OdaNumarasi INT NOT NULL UNIQUE,
    TipID INT NOT NULL REFERENCES OdaTipi(TipID),
    Kat INT NOT NULL,
    Durum OdaDurumu NOT NULL DEFAULT 'Bos'
);

CREATE TABLE SezonFiyati (
    SezonFiyatiID SERIAL PRIMARY KEY,
    TipID INT NOT NULL REFERENCES OdaTipi(TipID),
    BaslangicTarihi DATE NOT NULL,
    BitisTarihi DATE NOT NULL,
    GecelikFiyat DECIMAL(10, 2) NOT NULL CHECK (GecelikFiyat >= 0),
    CHECK (BitisTarihi > BaslangicTarihi)
);

CREATE TABLE Misafir (
    MisafirID SERIAL PRIMARY KEY,
    KullaniciID INT UNIQUE REFERENCES Kullanici(KullaniciID),
    Ad VARCHAR(100) NOT NULL,
    Soyad VARCHAR(100) NOT NULL,
    TcNo VARCHAR(11) UNIQUE,
    PasaportNo VARCHAR(50) UNIQUE,
    TelefonNo VARCHAR(20) NOT NULL,
    DogumTarihi DATE
);

CREATE TABLE Personel (
    PersonelID SERIAL PRIMARY KEY,
    KullaniciID INT NOT NULL UNIQUE REFERENCES Kullanici(KullaniciID),
    Ad VARCHAR(100) NOT NULL,
    Soyad VARCHAR(100) NOT NULL,
    Gorev VARCHAR(100) NOT NULL,
    IseGirisTarihi DATE NOT NULL
);

CREATE TABLE Rezervasyon (
    RezervasyonID SERIAL PRIMARY KEY,
    MisafirID INT NOT NULL REFERENCES Misafir(MisafirID),
    OdaID INT NOT NULL REFERENCES Oda(OdaID),
    GirisTarihi DATE NOT NULL,
    CikisTarihi DATE NOT NULL,
    KisiSayisi INT NOT NULL CHECK (KisiSayisi > 0),
    Durum RezervasyonDurumu NOT NULL DEFAULT 'Beklemede',
    OlusturmaTarihi TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CHECK (CikisTarihi > GirisTarihi)
);

CREATE TABLE RezervasyonMisafir (
    RezervasyonMisafirID SERIAL PRIMARY KEY,
    RezervasyonID INT NOT NULL REFERENCES Rezervasyon(RezervasyonID) ON DELETE CASCADE,
    MisafirID INT NOT NULL REFERENCES Misafir(MisafirID),
    UNIQUE (RezervasyonID, MisafirID)
);

CREATE TABLE TemizlikKaydi (
    TemizlikKaydiID SERIAL PRIMARY KEY,
    OdaID INT NOT NULL REFERENCES Oda(OdaID),
    PersonelID INT NOT NULL REFERENCES Personel(PersonelID),
    TemizlikTarihi DATE NOT NULL,
    Durum TemizlikDurumu NOT NULL DEFAULT 'Bekliyor'
);

CREATE TABLE Konaklama (
    KonaklamaID SERIAL PRIMARY KEY,
    RezervasyonID INT NOT NULL UNIQUE REFERENCES Rezervasyon(RezervasyonID),
    GercekGirisTarihi TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    GercekCikisTarihi TIMESTAMP,
    PersonelID INT NOT NULL REFERENCES Personel(PersonelID),
    Durum KonaklamaDurumu NOT NULL DEFAULT 'Aktif'
);

CREATE TABLE KonaklamaOdaGecmisi (
    GecmisID SERIAL PRIMARY KEY,
    KonaklamaID INT NOT NULL REFERENCES Konaklama(KonaklamaID),
    OdaID INT NOT NULL REFERENCES Oda(OdaID),
    BaslangicTarihi TIMESTAMP NOT NULL,
    BitisTarihi TIMESTAMP
);

CREATE TABLE Hizmet (
    HizmetID SERIAL PRIMARY KEY,
    Ad VARCHAR(100) NOT NULL,
    BirimFiyat DECIMAL(10, 2) NOT NULL CHECK (BirimFiyat >= 0)
);

CREATE TABLE KonaklamaHizmeti (
    KonaklamaHizmetiID SERIAL PRIMARY KEY,
    KonaklamaID INT NOT NULL REFERENCES Konaklama(KonaklamaID),
    HizmetID INT NOT NULL REFERENCES Hizmet(HizmetID),
    Adet INT NOT NULL DEFAULT 1 CHECK (Adet > 0),
    BirimFiyat DECIMAL(10, 2) NOT NULL CHECK (BirimFiyat >= 0),
    IslemTarihi TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE Odeme (
    OdemeID SERIAL PRIMARY KEY,
    KonaklamaID INT NOT NULL REFERENCES Konaklama(KonaklamaID),
    Tutar DECIMAL(10, 2) NOT NULL CHECK (Tutar > 0),
    Yontem OdemeYontemi NOT NULL,
    IslemTarihi TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IslemLog (
    LogID SERIAL PRIMARY KEY,
    KullaniciID INT REFERENCES Kullanici(KullaniciID),
    TabloAdi VARCHAR(100) NOT NULL,
    IslemTuru VARCHAR(100) NOT NULL,
    Aciklama TEXT NOT NULL,
    IslemTarihi TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

-- Yarim kalan isler:
-- triggerlar ekle (rezervasyon çakışma kontrolü, oda durum güncelleme, islem loglama)
-- fonksiyonları yaz (boş oda sorgulama, sezonluk fiyat/ödeme hesaplama, aylık rapor)
-- günlük giris çıkış ve oda temizlik durumu vs. için Vievlar oluştur
-- test verileri ekle
