package com.example.otel_sistemi.service;

import com.example.otel_sistemi.entity.KonaklamaHizmeti;
import com.example.otel_sistemi.repository.KonaklamaHizmetiRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class KonaklamaHizmetiService {
    private final KonaklamaHizmetiRepository repository;

    public KonaklamaHizmetiService(KonaklamaHizmetiRepository repository) {
        this.repository = repository;
    }

    public List<KonaklamaHizmeti> findAll() {
        return repository.findAll();
    }

    public KonaklamaHizmeti findById(Integer id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("Geçersiz ID değeri.");
        }
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("KonaklamaHizmeti bulunamadı. ID: " + id));
    }

    public KonaklamaHizmeti ekle(KonaklamaHizmeti kayit) {
        if (kayit == null) {
            throw new IllegalArgumentException("Kayıt bilgileri boş olamaz.");
        }
        if (kayit.getKonaklama() == null || kayit.getKonaklama().getKonaklamaid() == null
                || kayit.getKonaklama().getKonaklamaid() <= 0) {
            throw new IllegalArgumentException("Geçerli bir konaklama belirtilmelidir.");
        }
        if (kayit.getHizmet() == null || kayit.getHizmet().getHizmetid() == null
                || kayit.getHizmet().getHizmetid() <= 0) {
            throw new IllegalArgumentException("Geçerli bir hizmet belirtilmelidir.");
        }
        if (kayit.getAdet() == null || kayit.getAdet() <= 0) {
            throw new IllegalArgumentException("Adet 0'dan büyük olmalıdır.");
        }
        if (kayit.getBirimfiyat() == null || kayit.getBirimfiyat().compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Birim fiyat boş veya negatif olamaz.");
        }
        return repository.save(kayit);
    }
}
