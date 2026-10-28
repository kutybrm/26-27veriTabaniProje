package com.example.otel_sistemi.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.FetchType;
import jakarta.persistence.Column;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

@Entity
@Table(name = "oda")
public class Oda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "odaid")
    private Integer odaid;

    @Column(name = "odanumarasi", nullable = false, unique = true)
    private Integer odanumarasi;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tipid", nullable = false)
    private OdaTipi odatipi;

    @Column(name = "kat", nullable = false)
    private Integer kat;

    @JdbcTypeCode(SqlTypes.NAMED_ENUM)
    @Column(name = "durum", columnDefinition = "odadurumu", nullable = false)
    private OdaDurumu durum;

    public Integer getOdaid() {
        return odaid;
    }

    public void setOdaid(Integer odaid) {
        this.odaid = odaid;
    }

    public Integer getOdanumarasi() {
        return odanumarasi;
    }

    public void setOdanumarasi(Integer odanumarasi) {
        this.odanumarasi = odanumarasi;
    }

    public OdaTipi getOdatipi() {
        return odatipi;
    }

    public void setOdatipi(OdaTipi odatipi) {
        this.odatipi = odatipi;
    }

    public Integer getKat() {
        return kat;
    }

    public void setKat(Integer kat) {
        this.kat = kat;
    }

    public OdaDurumu getDurum() {
        return durum;
    }

    public void setDurum(OdaDurumu durum) {
        this.durum = durum;
    }
}