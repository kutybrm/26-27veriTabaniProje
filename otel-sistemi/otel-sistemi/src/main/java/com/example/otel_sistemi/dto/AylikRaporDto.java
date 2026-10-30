package com.example.otel_sistemi.dto;

import java.math.BigDecimal;

public class AylikRaporDto {
    private Integer raporyili;
    private Integer raporayi;
    private Long toplamrezervasyonsayisi;
    private BigDecimal ayliktoplamgelir;

    public AylikRaporDto() {
    }

    public AylikRaporDto(Integer raporyili, Integer raporayi, Long toplamrezervasyonsayisi, BigDecimal ayliktoplamgelir) {
        this.raporyili = raporyili;
        this.raporayi = raporayi;
        this.toplamrezervasyonsayisi = toplamrezervasyonsayisi;
        this.ayliktoplamgelir = ayliktoplamgelir;
    }

    public Integer getRaporyili() {
        return raporyili;
    }

    public void setRaporyili(Integer raporyili) {
        this.raporyili = raporyili;
    }

    public Integer getRaporayi() {
        return raporayi;
    }

    public void setRaporayi(Integer raporayi) {
        this.raporayi = raporayi;
    }

    public Long getToplamrezervasyonsayisi() {
        return toplamrezervasyonsayisi;
    }

    public void setToplamrezervasyonsayisi(Long toplamrezervasyonsayisi) {
        this.toplamrezervasyonsayisi = toplamrezervasyonsayisi;
    }

    public BigDecimal getAyliktoplamgelir() {
        return ayliktoplamgelir;
    }

    public void setAyliktoplamgelir(BigDecimal ayliktoplamgelir) {
        this.ayliktoplamgelir = ayliktoplamgelir;
    }
}