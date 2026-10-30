package com.example.otel_sistemi.dto;

import java.time.LocalDate;

public class OdaGuncelDurumDto {
    private Integer odanumarasi;
    private String tipadi;
    private String odadurumu;
    private LocalDate sontemizliktarihi;

    public OdaGuncelDurumDto() {
    }

    public OdaGuncelDurumDto(Integer odanumarasi, String tipadi, String odadurumu, LocalDate sontemizliktarihi) {
        this.odanumarasi = odanumarasi;
        this.tipadi = tipadi;
        this.odadurumu = odadurumu;
        this.sontemizliktarihi = sontemizliktarihi;
    }

    public Integer getOdanumarasi() {
        return odanumarasi;
    }

    public void setOdanumarasi(Integer odanumarasi) {
        this.odanumarasi = odanumarasi;
    }

    public String getTipadi() {
        return tipadi;
    }

    public void setTipadi(String tipadi) {
        this.tipadi = tipadi;
    }

    public String getOdadurumu() {
        return odadurumu;
    }

    public void setOdadurumu(String odadurumu) {
        this.odadurumu = odadurumu;
    }

    public LocalDate getSontemizliktarihi() {
        return sontemizliktarihi;
    }

    public void setSontemizliktarihi(LocalDate sontemizliktarihi) {
        this.sontemizliktarihi = sontemizliktarihi;
    }
}