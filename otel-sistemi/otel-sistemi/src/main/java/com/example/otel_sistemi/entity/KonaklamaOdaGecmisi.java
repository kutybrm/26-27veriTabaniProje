package com.example.otel_sistemi.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.time.LocalDateTime;

@Entity
@Table(name = "konaklamaodagecmisi")
public class KonaklamaOdaGecmisi {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "gecmisid")
    private Integer gecmisid;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "konaklamaid", nullable = false)
    private Konaklama konaklama;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "odaid", nullable = false)
    private Oda oda;

    @Column(name = "baslangictarihi", nullable = false)
    private LocalDateTime baslangictarihi;

    @Column(name = "bitistarihi")
    private LocalDateTime bitistarihi;

    public Integer getGecmisid() { return gecmisid; }
    public void setGecmisid(Integer gecmisid) { this.gecmisid = gecmisid; }
    public Konaklama getKonaklama() { return konaklama; }
    public void setKonaklama(Konaklama konaklama) { this.konaklama = konaklama; }
    public Oda getOda() { return oda; }
    public void setOda(Oda oda) { this.oda = oda; }
    public LocalDateTime getBaslangictarihi() { return baslangictarihi; }
    public void setBaslangictarihi(LocalDateTime baslangictarihi) { this.baslangictarihi = baslangictarihi; }
    public LocalDateTime getBitistarihi() { return bitistarihi; }
    public void setBitistarihi(LocalDateTime bitistarihi) { this.bitistarihi = bitistarihi; }
}
