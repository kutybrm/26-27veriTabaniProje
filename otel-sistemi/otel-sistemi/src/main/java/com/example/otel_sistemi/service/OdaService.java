package com.example.otel_sistemi.service;

import jakarta.persistence.EntityNotFoundException;
import com.example.otel_sistemi.entity.Oda;
import com.example.otel_sistemi.repository.OdaRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class OdaService {
    private final OdaRepository odaRepository;

    public OdaService(OdaRepository odaRepository) {
        this.odaRepository = odaRepository;
    }

    public Oda odaEkle(Oda oda) {
        return ekle(oda);
    }

    public Oda ekle(Oda oda) {
        if (oda == null) {
            throw new IllegalArgumentException("Kayıt bilgileri boş olamaz.");
        }
        if (oda.getOdanumarasi() == null || oda.getOdanumarasi() <= 0) {
            throw new IllegalArgumentException("Oda numarası 0'dan büyük olmalıdır.");
        }
        if (oda.getKat() == null) {
            throw new IllegalArgumentException("Kat boş bırakılamaz.");
        }
        if (oda.getOdatipi() == null || oda.getOdatipi().getTipid() == null || oda.getOdatipi().getTipid() <= 0) {
            throw new IllegalArgumentException("Geçerli bir oda tipi belirtilmelidir.");
        }
        if (oda.getDurum() == null) {
            throw new IllegalArgumentException("Oda durumu boş bırakılamaz.");
        }
        return odaRepository.save(oda);
    }

    public List<Oda> findAll() {
        return odaRepository.findAll();
    }

    public Oda findById(Integer id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("Geçersiz ID değeri.");
        }
        return odaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Oda bulunamadı. ID: " + id));
    }
}