package com.example.otel_sistemi.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import java.math.BigDecimal;

@Entity
@Table(name = "odatipi")
public class OdaTipi {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer tipid;

    private String tipadi;
    private Integer kapasite;
    private BigDecimal temelfiyat;
    private String odaaciklama;

    public Integer getTipid() {
        return tipid;
    }

    public void setTipid(Integer tipid) {
        this.tipid = tipid;
    }

    public String getTipadi() {
        return tipadi;
    }

    public void setTipadi(String tipadi) {
        this.tipadi = tipadi;
    }

    public Integer getKapasite() {
        return kapasite;
    }

    public void setKapasite(Integer kapasite) {
        this.kapasite = kapasite;
    }

    public BigDecimal getTemelfiyat() {
        return temelfiyat;
    }

    public void setTemelfiyat(BigDecimal temelfiyat) {
        this.temelfiyat = temelfiyat;
    }

    public String getOdaaciklama() {
        return odaaciklama;
    }

    public void setOdaaciklama(String odaaciklama) {
        this.odaaciklama = odaaciklama;
    }
}