package com.example.otel_sistemi.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;
import jakarta.persistence.OneToOne;
import jakarta.persistence.JoinColumn;
import java.time.LocalDate;

@Entity
@Table(name = "misafir")
public class Misafir {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer misafirid;

    @OneToOne
    @JoinColumn(name = "kullaniciid", unique = true)
    private Kullanici kullanici;

    @Column(name = "ad", length = 100, nullable = false)
    private String ad;

    @Column(name = "soyad", length = 100, nullable = false)
    private String soyad;

    @Column(name = "tcno", length = 11, unique = true)
    private String tcno;

    @Column(name = "pasaportno", length = 50, unique = true)
    private String pasaportno;

    @Column(name = "telefonno", length = 20, nullable = false)
    private String telefonno;

    @Column(name = "dogumtarihi")
    private LocalDate dogumtarihi;

    public Integer getMisafirid() {
        return misafirid;
    }

    public void setMisafirid(Integer misafirid) {
        this.misafirid = misafirid;
    }

    public Kullanici getKullanici() {
        return kullanici;
    }

    public void setKullanici(Kullanici kullanici) {
        this.kullanici = kullanici;
    }

    public String getAd() {
        return ad;
    }

    public void setAd(String ad) {
        this.ad = ad;
    }

    public String getSoyad() {
        return soyad;
    }

    public void setSoyad(String soyad) {
        this.soyad = soyad;
    }

    public String getTcno() {
        return tcno;
    }

    public void setTcno(String tcno) {
        this.tcno = tcno;
    }

    public String getPasaportno() {
        return pasaportno;
    }

    public void setPasaportno(String pasaportno) {
        this.pasaportno = pasaportno;
    }

    public String getTelefonno() {
        return telefonno;
    }

    public void setTelefonno(String telefonno) {
        this.telefonno = telefonno;
    }

    public LocalDate getDogumtarihi() {
        return dogumtarihi;
    }

    public void setDogumtarihi(LocalDate dogumtarihi) {
        this.dogumtarihi = dogumtarihi;
    }
}