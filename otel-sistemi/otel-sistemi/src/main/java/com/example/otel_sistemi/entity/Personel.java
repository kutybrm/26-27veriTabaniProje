package com.example.otel_sistemi.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;
import jakarta.persistence.OneToOne;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.FetchType;
import java.time.LocalDate;

@Entity
@Table(name = "personel")
public class Personel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "personelid")
    private Integer personelid;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "kullaniciid", nullable = false, unique = true)
    private Kullanici kullanici;

    @Column(name = "ad", length = 100, nullable = false)
    private String ad;

    @Column(name = "soyad", length = 100, nullable = false)
    private String soyad;

    @Column(name = "gorev", length = 100, nullable = false)
    private String gorev;

    @Column(name = "isegiristarihi", nullable = false)
    private LocalDate isegiristarihi;


    public Integer getPersonelid() {
        return personelid;
    }

    public void setPersonelid(Integer personelid) {
        this.personelid = personelid;
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

    public String getGorev() {
        return gorev;
    }

    public void setGorev(String gorev) {
        this.gorev = gorev;
    }

    public LocalDate getIsegiristarihi() {
        return isegiristarihi;
    }

    public void setIsegiristarihi(LocalDate isegiristarihi) {
        this.isegiristarihi = isegiristarihi;
    }
}