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
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "rezervasyon")
public class Rezervasyon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rezervasyonid")
    private Integer rezervasyonid;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "misafirid", nullable = false)
    private Misafir misafir;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "odaid", nullable = false)
    private Oda oda;

    @Column(name = "giristarihi", nullable = false)
    private LocalDate giristarihi;

    @Column(name = "cikistarihi", nullable = false)
    private LocalDate cikistarihi;

    @Column(name = "kisisayisi", nullable = false)
    private Integer kisisayisi;

    @JdbcTypeCode(SqlTypes.NAMED_ENUM)
    @Column(name = "durum", columnDefinition = "rezervasyondurumu", nullable = false)
    private RezervasyonDurumu durum = RezervasyonDurumu.Beklemede;

    @Column(name = "olusturmatarihi", nullable = false)
    private LocalDateTime olusturmatarihi;

    @PrePersist
    void setDefaultValues() {
        if (durum == null) {
            durum = RezervasyonDurumu.Beklemede;
        }
        if (olusturmatarihi == null) {
            olusturmatarihi = LocalDateTime.now();
        }
    }

    public Integer getRezervasyonid() { return rezervasyonid; }
    public void setRezervasyonid(Integer rezervasyonid) { this.rezervasyonid = rezervasyonid; }
    public Misafir getMisafir() { return misafir; }
    public void setMisafir(Misafir misafir) { this.misafir = misafir; }
    public Oda getOda() { return oda; }
    public void setOda(Oda oda) { this.oda = oda; }
    public LocalDate getGiristarihi() { return giristarihi; }
    public void setGiristarihi(LocalDate giristarihi) { this.giristarihi = giristarihi; }
    public LocalDate getCikistarihi() { return cikistarihi; }
    public void setCikistarihi(LocalDate cikistarihi) { this.cikistarihi = cikistarihi; }
    public Integer getKisisayisi() { return kisisayisi; }
    public void setKisisayisi(Integer kisisayisi) { this.kisisayisi = kisisayisi; }
    public RezervasyonDurumu getDurum() { return durum; }
    public void setDurum(RezervasyonDurumu durum) { this.durum = durum; }
    public LocalDateTime getOlusturmatarihi() { return olusturmatarihi; }
    public void setOlusturmatarihi(LocalDateTime olusturmatarihi) { this.olusturmatarihi = olusturmatarihi; }
}
