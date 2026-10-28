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

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "odeme")
public class Odeme {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "odemeid")
    private Integer odemeid;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "konaklamaid", nullable = false)
    private Konaklama konaklama;

    @Column(name = "tutar", precision = 10, scale = 2, nullable = false)
    private BigDecimal tutar;

    @JdbcTypeCode(SqlTypes.NAMED_ENUM)
    @Column(name = "yontem", columnDefinition = "odemeyontemi", nullable = false)
    private OdemeYontemi yontem;

    @Column(name = "islemtarihi", nullable = false)
    private LocalDateTime islemtarihi;

    @PrePersist
    void setDefaultValues() {
        if (islemtarihi == null) {
            islemtarihi = LocalDateTime.now();
        }
    }

    public Integer getOdemeid() { return odemeid; }
    public void setOdemeid(Integer odemeid) { this.odemeid = odemeid; }
    public Konaklama getKonaklama() { return konaklama; }
    public void setKonaklama(Konaklama konaklama) { this.konaklama = konaklama; }
    public BigDecimal getTutar() { return tutar; }
    public void setTutar(BigDecimal tutar) { this.tutar = tutar; }
    public OdemeYontemi getYontem() { return yontem; }
    public void setYontem(OdemeYontemi yontem) { this.yontem = yontem; }
    public LocalDateTime getIslemtarihi() { return islemtarihi; }
    public void setIslemtarihi(LocalDateTime islemtarihi) { this.islemtarihi = islemtarihi; }
}
