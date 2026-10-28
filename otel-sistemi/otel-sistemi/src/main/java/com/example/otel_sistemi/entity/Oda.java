package com.example.otel_sistemi.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Enumerated;
import jakarta.persistence.EnumType;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

@Entity
@Table(name = "oda")
public class Oda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer odaid;

    private Integer odanumarasi;

    @ManyToOne
    @JoinColumn(name = "tipid", nullable = false)
    private OdaTipi odatipi;

    private Integer kat;

    @Enumerated(EnumType.STRING)
    @JdbcTypeCode(SqlTypes.NAMED_ENUM)
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