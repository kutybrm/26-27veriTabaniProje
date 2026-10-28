package com.example.otel_sistemi.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "konaklamahizmeti")
public class KonaklamaHizmeti {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "konaklamahizmetiid")
    private Integer konaklamahizmetiid;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "konaklamaid", nullable = false)
    private Konaklama konaklama;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hizmetid", nullable = false)
    private Hizmet hizmet;

    @Column(name = "adet", nullable = false)
    private Integer adet = 1;

    @Column(name = "birimfiyat", precision = 10, scale = 2, nullable = false)
    private BigDecimal birimfiyat;

    @Column(name = "islemtarihi", nullable = false)
    private LocalDateTime islemtarihi;

    @PrePersist
    void setDefaultValues() {
        if (adet == null) {
            adet = 1;
        }
        if (islemtarihi == null) {
            islemtarihi = LocalDateTime.now();
        }
    }

    public Integer getKonaklamahizmetiid() { return konaklamahizmetiid; }
    public void setKonaklamahizmetiid(Integer konaklamahizmetiid) { this.konaklamahizmetiid = konaklamahizmetiid; }
    public Konaklama getKonaklama() { return konaklama; }
    public void setKonaklama(Konaklama konaklama) { this.konaklama = konaklama; }
    public Hizmet getHizmet() { return hizmet; }
    public void setHizmet(Hizmet hizmet) { this.hizmet = hizmet; }
    public Integer getAdet() { return adet; }
    public void setAdet(Integer adet) { this.adet = adet; }
    public BigDecimal getBirimfiyat() { return birimfiyat; }
    public void setBirimfiyat(BigDecimal birimfiyat) { this.birimfiyat = birimfiyat; }
    public LocalDateTime getIslemtarihi() { return islemtarihi; }
    public void setIslemtarihi(LocalDateTime islemtarihi) { this.islemtarihi = islemtarihi; }
}
