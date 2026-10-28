package com.example.otel_sistemi.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.EnumType;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

@Entity
@Table(name = "kullanici")
public class Kullanici {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer kullaniciid;
    private String eposta;
    private String sifrehash;
    @Enumerated(EnumType.STRING)
    @JdbcTypeCode(SqlTypes.NAMED_ENUM)
    private KullaniciRol rol;

    public String getSifrehash() {
        return sifrehash;
    }

    public void setSifrehash(String sifrehash) {
        this.sifrehash = sifrehash;
    }

    public Integer getKullaniciid() {
        return kullaniciid;
    }

    public void setKullaniciid(Integer kullaniciid) {
        this.kullaniciid = kullaniciid;
    }

    public String getEposta() {
        return eposta;
    }

    public void setEposta(String eposta) {
        this.eposta = eposta;
    }

    public KullaniciRol getRol() {
        return rol;
    }

    public void setRol(KullaniciRol rol) {
        this.rol = rol;
    }
}