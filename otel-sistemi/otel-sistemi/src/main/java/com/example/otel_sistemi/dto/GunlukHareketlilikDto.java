package com.example.otel_sistemi.dto;

import java.time.LocalDate;

public class GunlukHareketlilikDto {
    private String misafir;
    private String telefonno;
    private Integer odanumarasi;
    private String islemturu;
    private LocalDate tarih;

    public GunlukHareketlilikDto() {
    }

    public GunlukHareketlilikDto(String misafir, String telefonno, Integer odanumarasi, String islemturu, LocalDate tarih) {
        this.misafir = misafir;
        this.telefonno = telefonno;
        this.odanumarasi = odanumarasi;
        this.islemturu = islemturu;
        this.tarih = tarih;
    }

    public String getMisafir() {
        return misafir;
    }

    public void setMisafir(String misafir) {
        this.misafir = misafir;
    }

    public String getTelefonno() {
        return telefonno;
    }

    public void setTelefonno(String telefonno) {
        this.telefonno = telefonno;
    }

    public Integer getOdanumarasi() {
        return odanumarasi;
    }

    public void setOdanumarasi(Integer odanumarasi) {
        this.odanumarasi = odanumarasi;
    }

    public String getIslemturu() {
        return islemturu;
    }

    public void setIslemturu(String islemturu) {
        this.islemturu = islemturu;
    }

    public LocalDate getTarih() {
        return tarih;
    }

    public void setTarih(LocalDate tarih) {
        this.tarih = tarih;
    }
}