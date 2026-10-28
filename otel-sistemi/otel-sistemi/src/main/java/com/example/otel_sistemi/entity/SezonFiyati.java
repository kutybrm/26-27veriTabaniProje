package com.example.otel_sistemi.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "sezonfiyati")
public class SezonFiyati {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer sezonfiyatiid;

    @ManyToOne
    @JoinColumn(name = "tipid", nullable = false)
    private OdaTipi odatipi;

    @Column(name = "baslangictarihi", nullable = false)
    private LocalDate baslangictarihi;

    @Column(name = "bitistarihi", nullable = false)
    private LocalDate bitistarihi;

    @Column(name = "gecelikfiyat", nullable = false)
    private BigDecimal gecelikfiyat;

    // --- Getter & Setter Metotları ---

    public Integer getSezonfiyatiid() {
        return sezonfiyatiid;
    }

    public void setSezonfiyatiid(Integer sezonfiyatiid) {
        this.sezonfiyatiid = sezonfiyatiid;
    }

    public OdaTipi getOdatipi() {
        return odatipi;
    }

    public void setOdatipi(OdaTipi odatipi) {
        this.odatipi = odatipi;
    }

    public LocalDate getBaslangictarihi() {
        return baslangictarihi;
    }

    public void setBaslangictarihi(LocalDate baslangictarihi) {
        this.baslangictarihi = baslangictarihi;
    }

    public LocalDate getBitistarihi() {
        return bitistarihi;
    }

    public void setBitistarihi(LocalDate bitistarihi) {
        this.bitistarihi = bitistarihi;
    }

    public BigDecimal getGecelikfiyat() {
        return gecelikfiyat;
    }

    public void setGecelikfiyat(BigDecimal gecelikfiyat) {
        this.gecelikfiyat = gecelikfiyat;
    }
}