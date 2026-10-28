package com.example.otel_sistemi.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.LocalDateTime;

@Entity
@Table(name = "konaklama")
public class Konaklama {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "konaklamaid")
    private Integer konaklamaid;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rezervasyonid", nullable = false, unique = true)
    private Rezervasyon rezervasyon;

    @Column(name = "gercekgiristarihi", nullable = false)
    private LocalDateTime gercekgiristarihi;

    @Column(name = "gercekcikistarihi")
    private LocalDateTime gercekcikistarihi;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "personelid", nullable = false)
    private Personel personel;

    @JdbcTypeCode(SqlTypes.NAMED_ENUM)
    @Column(name = "durum", columnDefinition = "konaklamadurumu", nullable = false)
    private KonaklamaDurumu durum = KonaklamaDurumu.Aktif;

    @PrePersist
    void setDefaultValues() {
        if (gercekgiristarihi == null) {
            gercekgiristarihi = LocalDateTime.now();
        }
        if (durum == null) {
            durum = KonaklamaDurumu.Aktif;
        }
    }

    public Integer getKonaklamaid() { return konaklamaid; }
    public void setKonaklamaid(Integer konaklamaid) { this.konaklamaid = konaklamaid; }
    public Rezervasyon getRezervasyon() { return rezervasyon; }
    public void setRezervasyon(Rezervasyon rezervasyon) { this.rezervasyon = rezervasyon; }
    public LocalDateTime getGercekgiristarihi() { return gercekgiristarihi; }
    public void setGercekgiristarihi(LocalDateTime gercekgiristarihi) { this.gercekgiristarihi = gercekgiristarihi; }
    public LocalDateTime getGercekcikistarihi() { return gercekcikistarihi; }
    public void setGercekcikistarihi(LocalDateTime gercekcikistarihi) { this.gercekcikistarihi = gercekcikistarihi; }
    public Personel getPersonel() { return personel; }
    public void setPersonel(Personel personel) { this.personel = personel; }
    public KonaklamaDurumu getDurum() { return durum; }
    public void setDurum(KonaklamaDurumu durum) { this.durum = durum; }
}
