package com.example.otel_sistemi.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;

import java.math.BigDecimal;
@Entity
@Table(name = "hizmet")
public class Hizmet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer hizmetid;

    @Column(nullable = false, length = 100)
    private String ad;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal birimfiyat;

    @Column(columnDefinition = "TEXT")
    private String aciklama;

    public Integer getHizmetid() {
        return hizmetid;
    }

    public void setHizmetid(Integer hizmetid) {
        this.hizmetid = hizmetid;
    }

    public String getAd() {
        return ad;
    }

    public void setAd(String ad) {
        this.ad = ad;
    }

    public BigDecimal getBirimfiyat() {
        return birimfiyat;
    }

    public void setBirimfiyat(BigDecimal birimfiyat) {
        this.birimfiyat = birimfiyat;
    }

    public String getAciklama() {
        return aciklama;
    }

    public void setAciklama(String aciklama) {
        this.aciklama = aciklama;
    }
}