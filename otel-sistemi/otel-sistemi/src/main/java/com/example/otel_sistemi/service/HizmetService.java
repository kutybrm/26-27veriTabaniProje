package com.example.otel_sistemi.service;

import com.example.otel_sistemi.entity.Hizmet;
import com.example.otel_sistemi.repository.HizmetRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class HizmetService {
    private final HizmetRepository hizmetRepository;

    public HizmetService(HizmetRepository hizmetRepository) {
        this.hizmetRepository = hizmetRepository;
    }

    public Hizmet hizmetEkle(Hizmet hizmet) {
        if (hizmet == null) {
            throw new IllegalArgumentException("Hizmet bilgileri boş olamaz.");
        }
        if (hizmet.getAd() == null || hizmet.getAd().trim().isEmpty()) {
            throw new IllegalArgumentException("Hizmet adı boş bırakılamaz.");
        }
        if (hizmet.getBirimfiyat() == null || hizmet.getBirimfiyat().compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Birim fiyat boş veya negatif olamaz.");
        }

        return hizmetRepository.save(hizmet);
    }

    public List<Hizmet> findAll() {
        return hizmetRepository.findAll();
    }
}
