package com.example.otel_sistemi.service;

import com.example.otel_sistemi.entity.Odeme;
import com.example.otel_sistemi.repository.OdemeRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class OdemeService {
    private final OdemeRepository repository;

    public OdemeService(OdemeRepository repository) {
        this.repository = repository;
    }

    public List<Odeme> findAll() {
        return repository.findAll();
    }

    public Odeme findById(Integer id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("Geçersiz ID değeri.");
        }
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Odeme bulunamadı. ID: " + id));
    }

    public Odeme ekle(Odeme odeme) {
        if (odeme == null) {
            throw new IllegalArgumentException("Kayıt bilgileri boş olamaz.");
        }
        if (odeme.getKonaklama() == null || odeme.getKonaklama().getKonaklamaid() == null
                || odeme.getKonaklama().getKonaklamaid() <= 0) {
            throw new IllegalArgumentException("Geçerli bir konaklama belirtilmelidir.");
        }
        if (odeme.getTutar() == null || odeme.getTutar().compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Ödeme tutarı 0'dan büyük olmalıdır.");
        }
        if (odeme.getYontem() == null) {
            throw new IllegalArgumentException("Ödeme yöntemi boş bırakılamaz.");
        }
        return repository.save(odeme);
    }
}
