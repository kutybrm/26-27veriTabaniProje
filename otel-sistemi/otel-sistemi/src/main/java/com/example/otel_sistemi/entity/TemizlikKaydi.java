package com.example.otel_sistemi.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;

import java.time.LocalDate;

@Entity
@Table(name = "temizlikkaydi")
public class TemizlikKaydi {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "temizlikkaydiid")
    private Integer temizlikkaydiid;

    @Column(name = "odaid", nullable = false)
    private Integer odaid;

    @Column(name = "personelid", nullable = false)
    private Integer personelid;

    @Column(name = "temizliktarihi", nullable = false)
    private LocalDate temizliktarihi;

    @Column(name = "durum", nullable = false)
    private String durum = "Bekliyor";

    public Integer getTemizlikkaydiid() {
        return temizlikkaydiid;
    }

    public void setTemizlikkaydiid(Integer temizlikkaydiid) {
        this.temizlikkaydiid = temizlikkaydiid;
    }

    public Integer getOdaid() {
        return odaid;
    }

    public void setOdaid(Integer odaid) {
        this.odaid = odaid;
    }

    public LocalDate getTemizliktarihi() {
        return temizliktarihi;
    }

    public void setTemizliktarihi(LocalDate temizliktarihi) {
        this.temizliktarihi = temizliktarihi;
    }

    public String getDurum() {
        return durum;
    }

    public void setDurum(String durum) {
        this.durum = durum;
    }

    public Integer getPersonelid() {
        return personelid;
    }

    public void setPersonelid(Integer personelid) {
        this.personelid = personelid;
    }
}
