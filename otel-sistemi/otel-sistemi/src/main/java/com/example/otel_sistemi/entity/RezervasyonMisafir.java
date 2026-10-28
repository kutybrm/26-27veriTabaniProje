package com.example.otel_sistemi.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Column;

@Entity
@Table(name = "rezervasyonmisafir")
public class RezervasyonMisafir {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rezervasyonmisafirid")
    private Integer rezervasyonmisafirid;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rezervasyonid", nullable = false)
    private Rezervasyon rezervasyon;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "misafirid", nullable = false)
    private Misafir misafir;

    public Integer getRezervasyonmisafirid() { return rezervasyonmisafirid; }
    public void setRezervasyonmisafirid(Integer rezervasyonmisafirid) { this.rezervasyonmisafirid = rezervasyonmisafirid; }
    public Rezervasyon getRezervasyon() { return rezervasyon; }
    public void setRezervasyon(Rezervasyon rezervasyon) { this.rezervasyon = rezervasyon; }
    public Misafir getMisafir() { return misafir; }
    public void setMisafir(Misafir misafir) { this.misafir = misafir; }
}
