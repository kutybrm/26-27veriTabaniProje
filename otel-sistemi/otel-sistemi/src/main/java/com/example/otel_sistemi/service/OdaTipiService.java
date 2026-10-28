package com.example.otel_sistemi.service;

import jakarta.persistence.EntityNotFoundException;
import com.example.otel_sistemi.entity.OdaTipi;
import com.example.otel_sistemi.repository.OdaTipiRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class OdaTipiService {
    private final OdaTipiRepository odaTipiRepository;

    public OdaTipiService(OdaTipiRepository odaTipiRepository) {
        this.odaTipiRepository = odaTipiRepository;
    }

    public OdaTipi odaTipiEkle(OdaTipi odaTipi) {
        return ekle(odaTipi);
    }

    public OdaTipi ekle(OdaTipi odaTipi) {
        if (odaTipi == null) {
            throw new IllegalArgumentException("Kayıt bilgileri boş olamaz.");
        }
        if (odaTipi.getTipadi() == null || odaTipi.getTipadi().trim().isEmpty()) {
            throw new IllegalArgumentException("Oda tipi adı boş bırakılamaz.");
        }
        if (odaTipi.getKapasite() == null || odaTipi.getKapasite() <= 0) {
            throw new IllegalArgumentException("Kapasite 0'dan büyük olmalıdır.");
        }
        if (odaTipi.getTemelfiyat() == null || odaTipi.getTemelfiyat().compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Temel fiyat boş veya negatif olamaz.");
        }
        odaTipi.setTipadi(odaTipi.getTipadi().trim());
        if (odaTipi.getTipadi().length() > 100) {
            throw new IllegalArgumentException("Oda tipi adı 100 karakteri aşamaz.");
        }
        return odaTipiRepository.save(odaTipi);
    }

    public List<OdaTipi> findAll() {
        return odaTipiRepository.findAll();
    }

    public OdaTipi findById(Integer id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("Geçersiz ID değeri.");
        }
        return odaTipiRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("OdaTipi bulunamadı. ID: " + id));
    }
}