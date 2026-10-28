package com.example.otel_sistemi.service;

import com.example.otel_sistemi.entity.Konaklama;
import com.example.otel_sistemi.repository.KonaklamaRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KonaklamaService {
    private final KonaklamaRepository repository;

    public KonaklamaService(KonaklamaRepository repository) {
        this.repository = repository;
    }

    public List<Konaklama> findAll() {
        return repository.findAll();
    }

    public Konaklama findById(Integer id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("Geçersiz ID değeri.");
        }
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Konaklama bulunamadı. ID: " + id));
    }

    public Konaklama ekle(Konaklama konaklama) {
        if (konaklama == null) {
            throw new IllegalArgumentException("Kayıt bilgileri boş olamaz.");
        }
        if (konaklama.getRezervasyon() == null || konaklama.getRezervasyon().getRezervasyonid() == null
                || konaklama.getRezervasyon().getRezervasyonid() <= 0) {
            throw new IllegalArgumentException("Geçerli bir rezervasyon belirtilmelidir.");
        }
        if (konaklama.getPersonel() == null || konaklama.getPersonel().getPersonelid() == null
                || konaklama.getPersonel().getPersonelid() <= 0) {
            throw new IllegalArgumentException("Geçerli bir personel belirtilmelidir.");
        }
        if (konaklama.getDurum() == null) {
            throw new IllegalArgumentException("Konaklama durumu boş bırakılamaz.");
        }
        if (konaklama.getGercekcikistarihi() != null
                && konaklama.getGercekgiristarihi() != null
                && konaklama.getGercekcikistarihi().isBefore(konaklama.getGercekgiristarihi())) {
            throw new IllegalArgumentException("Gerçek çıkış tarihi gerçek giriş tarihinden önce olamaz.");
        }
        return repository.save(konaklama);
    }
}
