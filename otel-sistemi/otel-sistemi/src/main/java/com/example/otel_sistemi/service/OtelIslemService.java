package com.example.otel_sistemi.service;

import com.example.otel_sistemi.dto.BosOdaDto;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import com.example.otel_sistemi.dto.AylikRaporDto;

import java.time.LocalDate;
import java.util.List;

@Service
public class OtelIslemService {

    private final JdbcTemplate jdbcTemplate;

    public OtelIslemService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<BosOdaDto> getBosOdalar(LocalDate giris, LocalDate cikis, Integer tipId) {
        if (giris == null || cikis == null) {
            throw new IllegalArgumentException("Giriş ve çıkış tarihleri boş bırakılamaz.");
        }
        if (!cikis.isAfter(giris)) {
            throw new IllegalArgumentException("Çıkış tarihi giriş tarihinden sonraki bir gün olmalıdır.");
        }
        if (tipId == null || tipId <= 0) {
            throw new IllegalArgumentException("Geçersiz oda tipi ID değeri.");
        }

        String sql = "SELECT odanumarasi, kat, tipadi FROM sp_bos_odalari_getir(?, ?, ?)";

        return jdbcTemplate.query(sql, (rs, rowNum) -> new BosOdaDto(
                rs.getInt("odanumarasi"),
                rs.getInt("kat"),
                rs.getString("tipadi")
        ), giris, cikis, tipId);
    }

    public BigDecimal getKonaklamaTutari(Integer konaklamaId) {
        if (konaklamaId == null || konaklamaId <= 0) {
            throw new IllegalArgumentException("Geçersiz konaklama ID değeri.");
        }

        String sql = "SELECT sp_konaklama_tutar_hesapla(?)";

        return jdbcTemplate.queryForObject(sql, BigDecimal.class, konaklamaId);
    }

    public List<AylikRaporDto> getAylikRapor(Integer yil, Integer ay) {
        if (yil == null || yil < 2000 || yil > 2100) {
            throw new IllegalArgumentException("Geçersiz yıl değeri girdiniz.");
        }
        if (ay == null || ay < 1 || ay > 12) {
            throw new IllegalArgumentException("Ay değeri 1 ile 12 arasında olmalıdır.");
        }

        String sql = "SELECT raporyili, raporayi, toplamrezervasyonsayisi, ayliktoplamgelir FROM sp_aylik_rapor(?, ?)";

        return jdbcTemplate.query(sql, (rs, rowNum) -> new AylikRaporDto(
                rs.getInt("raporyili"),
                rs.getInt("raporayi"),
                rs.getLong("toplamrezervasyonsayisi"),
                rs.getBigDecimal("ayliktoplamgelir")
        ), yil, ay);
    }

    public void checkoutYap(Integer konaklamaId, String odemeYontemi) {
        if (konaklamaId == null || konaklamaId <= 0) {
            throw new IllegalArgumentException("Geçersiz konaklama ID değeri.");
        }
        if (odemeYontemi == null || odemeYontemi.trim().isEmpty()) {
            throw new IllegalArgumentException("Ödeme yöntemi boş bırakılamaz.");
        }

        String yontemTemiz = odemeYontemi.trim();
        if (!yontemTemiz.equals("Nakit") && !yontemTemiz.equals("KrediKarti") && !yontemTemiz.equals("Havale")) {
            throw new IllegalArgumentException("Geçersiz ödeme yöntemi! Kabul edilenler: Nakit, KrediKarti, Havale");
        }

        // PostgreSQL özel enum türü olduğu için ?::odemeyontemi şeklinde cast ediyoruz
        String sql = "CALL sp_checkout_yap(?, ?::odemeyontemi)";

        jdbcTemplate.update(sql, konaklamaId, yontemTemiz);
    }

    public List<com.example.otel_sistemi.dto.GunlukHareketlilikDto> getGunlukHareketlilik() {
        String sql = "SELECT misafir, telefonno, odanumarasi, islemturu, tarih FROM vw_gunluk_hareketlilik";

        return jdbcTemplate.query(sql, (rs, rowNum) -> new com.example.otel_sistemi.dto.GunlukHareketlilikDto(
                rs.getString("misafir"),
                rs.getString("telefonno"),
                rs.getInt("odanumarasi"),
                rs.getString("islemturu"),
                rs.getDate("tarih") != null ? rs.getDate("tarih").toLocalDate() : null
        ));
    }

    public List<com.example.otel_sistemi.dto.OdaGuncelDurumDto> getOdaGuncelDurumlari() {
        String sql = "SELECT odanumarasi, tipadi, odadurumu, sontemizliktarihi FROM vw_oda_guncel_durum";

        return jdbcTemplate.query(sql, (rs, rowNum) -> new com.example.otel_sistemi.dto.OdaGuncelDurumDto(
                rs.getInt("odanumarasi"),
                rs.getString("tipadi"),
                rs.getString("odadurumu"),
                rs.getDate("sontemizliktarihi") != null ? rs.getDate("sontemizliktarihi").toLocalDate() : null
        ));
    }
}