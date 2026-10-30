package com.example.otel_sistemi.controller;

import com.example.otel_sistemi.dto.BosOdaDto;
import com.example.otel_sistemi.service.OtelIslemService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.math.BigDecimal;
import java.util.Map;
import com.example.otel_sistemi.dto.AylikRaporDto;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/islemler")
public class OtelIslemController {

    private final OtelIslemService otelIslemService;

    public OtelIslemController(OtelIslemService otelIslemService) {
        this.otelIslemService = otelIslemService;
    }

    @GetMapping("/bos-odalar")
    public ResponseEntity<List<BosOdaDto>> getBosOdalar(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate giris,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate cikis,
            @RequestParam Integer tipId) {
        return ResponseEntity.ok(otelIslemService.getBosOdalar(giris, cikis, tipId));
    }

    @GetMapping("/konaklama-tutari/{konaklamaId}")
    public ResponseEntity<Map<String, Object>> getKonaklamaTutari(@PathVariable Integer konaklamaId) {
        BigDecimal toplamTutar = otelIslemService.getKonaklamaTutari(konaklamaId);

        return ResponseEntity.ok(Map.of(
                "konaklamaId", konaklamaId,
                "toplamTutar", toplamTutar
        ));
    }

    @GetMapping("/aylik-rapor")
    public ResponseEntity<List<AylikRaporDto>> getAylikRapor(
            @RequestParam Integer yil,
            @RequestParam Integer ay) {
        return ResponseEntity.ok(otelIslemService.getAylikRapor(yil, ay));
    }

    @PostMapping("/checkout")
    public ResponseEntity<Map<String, String>> checkout(@RequestBody com.example.otel_sistemi.dto.CheckoutRequestDto request) {
        if (request == null) {
            throw new IllegalArgumentException("İstek gövdesi boş olamaz.");
        }

        otelIslemService.checkoutYap(request.getKonaklamaId(), request.getOdemeYontemi());

        return ResponseEntity.ok(Map.of(
                "mesaj", "Check-out işlemi başarıyla tamamlandı. Ödeme kaydedildi ve oda temizliğe alındı."
        ));
    }

    @GetMapping("/gunluk-hareketlilik")
    public ResponseEntity<List<com.example.otel_sistemi.dto.GunlukHareketlilikDto>> getGunlukHareketlilik() {
        return ResponseEntity.ok(otelIslemService.getGunlukHareketlilik());
    }

    @GetMapping("/oda-durumlari")
    public ResponseEntity<List<com.example.otel_sistemi.dto.OdaGuncelDurumDto>> getOdaGuncelDurumlari() {
        return ResponseEntity.ok(otelIslemService.getOdaGuncelDurumlari());
    }
}