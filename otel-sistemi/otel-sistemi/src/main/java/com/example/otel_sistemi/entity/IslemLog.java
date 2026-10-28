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

import java.time.LocalDateTime;

@Entity
@Table(name = "islemlog")
public class IslemLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "logid")
    private Integer logid;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "kullaniciid")
    private Kullanici kullanici;

    @Column(name = "tabloadi", length = 100, nullable = false)
    private String tabloadi;

    @Column(name = "islemturu", length = 100, nullable = false)
    private String islemturu;

    @Column(name = "aciklama", columnDefinition = "text", nullable = false)
    private String aciklama;

    @Column(name = "islemtarihi", nullable = false)
    private LocalDateTime islemtarihi;

    @PrePersist
    void setDefaultValues() {
        if (islemtarihi == null) {
            islemtarihi = LocalDateTime.now();
        }
    }

    public Integer getLogid() { return logid; }
    public void setLogid(Integer logid) { this.logid = logid; }
    public Kullanici getKullanici() { return kullanici; }
    public void setKullanici(Kullanici kullanici) { this.kullanici = kullanici; }
    public String getTabloadi() { return tabloadi; }
    public void setTabloadi(String tabloadi) { this.tabloadi = tabloadi; }
    public String getIslemturu() { return islemturu; }
    public void setIslemturu(String islemturu) { this.islemturu = islemturu; }
    public String getAciklama() { return aciklama; }
    public void setAciklama(String aciklama) { this.aciklama = aciklama; }
    public LocalDateTime getIslemtarihi() { return islemtarihi; }
    public void setIslemtarihi(LocalDateTime islemtarihi) { this.islemtarihi = islemtarihi; }
}
