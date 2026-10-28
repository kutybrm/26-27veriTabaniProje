package com.example.otel_sistemi.service;

import com.example.otel_sistemi.entity.KonaklamaOdaGecmisi;
import com.example.otel_sistemi.repository.KonaklamaOdaGecmisiRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KonaklamaOdaGecmisiService {
    private final KonaklamaOdaGecmisiRepository repository;

    public KonaklamaOdaGecmisiService(KonaklamaOdaGecmisiRepository repository) {
        this.repository = repository;
    }

    public List<KonaklamaOdaGecmisi> findAll() {
        return repository.findAll();
    }

    public KonaklamaOdaGecmisi findById(Integer id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("Geçersiz ID değeri.");
        }
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("KonaklamaOdaGecmisi bulunamadı. ID: " + id));
    }

    public KonaklamaOdaGecmisi ekle(KonaklamaOdaGecmisi kayit) {
        if (kayit == null) {
            throw new IllegalArgumentException("Kayıt bilgileri boş olamaz.");
        }
        if (kayit.getKonaklama() == null || kayit.getKonaklama().getKonaklamaid() == null
                || kayit.getKonaklama().getKonaklamaid() <= 0) {
            throw new IllegalArgumentException("Geçerli bir konaklama belirtilmelidir.");
        }
        if (kayit.getOda() == null || kayit.getOda().getOdaid() == null || kayit.getOda().getOdaid() <= 0) {
            throw new IllegalArgumentException("Geçerli bir oda belirtilmelidir.");
        }
        if (kayit.getBaslangictarihi() == null) {
            throw new IllegalArgumentException("Başlangıç tarihi boş bırakılamaz.");
        }
        if (kayit.getBitistarihi() != null && !kayit.getBitistarihi().isAfter(kayit.getBaslangictarihi())) {
            throw new IllegalArgumentException("Bitiş tarihi başlangıç tarihinden sonra olmalıdır.");
        }
        return repository.save(kayit);
    }
}
