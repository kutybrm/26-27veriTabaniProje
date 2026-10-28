package com.example.otel_sistemi.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

@Entity
@Table(name = "kullanici")
public class Kullanici {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "kullaniciid")
    private Integer kullaniciid;

    @Column(name = "eposta", length = 255, nullable = false, unique = true)
    private String eposta;

    @Column(name = "sifrehash", length = 255, nullable = false)
    private String sifrehash;

    @JdbcTypeCode(SqlTypes.NAMED_ENUM)
    @Column(name = "rol", columnDefinition = "kullanicirol", nullable = false)
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