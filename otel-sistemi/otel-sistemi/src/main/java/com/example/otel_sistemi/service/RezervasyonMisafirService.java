package com.example.otel_sistemi.service;

import com.example.otel_sistemi.entity.RezervasyonMisafir;
import com.example.otel_sistemi.repository.RezervasyonMisafirRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RezervasyonMisafirService {
    private final RezervasyonMisafirRepository repository;

    public RezervasyonMisafirService(RezervasyonMisafirRepository repository) {
        this.repository = repository;
    }

    public List<RezervasyonMisafir> findAll() {
        return repository.findAll();
    }

    public RezervasyonMisafir findById(Integer id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("Geçersiz ID değeri.");
        }
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("RezervasyonMisafir bulunamadı. ID: " + id));
    }

    public RezervasyonMisafir ekle(RezervasyonMisafir kayit) {
        if (kayit == null) {
            throw new IllegalArgumentException("Kayıt bilgileri boş olamaz.");
        }
        if (kayit.getRezervasyon() == null || kayit.getRezervasyon().getRezervasyonid() == null
                || kayit.getRezervasyon().getRezervasyonid() <= 0) {
            throw new IllegalArgumentException("Geçerli bir rezervasyon belirtilmelidir.");
        }
        if (kayit.getMisafir() == null || kayit.getMisafir().getMisafirid() == null
                || kayit.getMisafir().getMisafirid() <= 0) {
            throw new IllegalArgumentException("Geçerli bir misafir belirtilmelidir.");
        }
        return repository.save(kayit);
    }
}
