package com.example.otel_sistemi.dto;

public class BosOdaDto {
    private Integer odanumarasi;
    private Integer kat;
    private String tipadi;

    public BosOdaDto() {
    }

    public BosOdaDto(Integer odanumarasi, Integer kat, String tipadi) {
        this.odanumarasi = odanumarasi;
        this.kat = kat;
        this.tipadi = tipadi;
    }

    public Integer getOdanumarasi() {
        return odanumarasi;
    }

    public void setOdanumarasi(Integer odanumarasi) {
        this.odanumarasi = odanumarasi;
    }

    public Integer getKat() {
        return kat;
    }

    public void setKat(Integer kat) {
        this.kat = kat;
    }

    public String getTipadi() {
        return tipadi;
    }

    public void setTipadi(String tipadi) {
        this.tipadi = tipadi;
    }
}