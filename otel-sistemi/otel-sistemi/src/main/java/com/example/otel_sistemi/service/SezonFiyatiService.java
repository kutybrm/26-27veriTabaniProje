package com.example.otel_sistemi.service;

import com.example.otel_sistemi.entity.SezonFiyati;
import com.example.otel_sistemi.repository.SezonFiyatiRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.List;

@Service
public class SezonFiyatiService {

    private final SezonFiyatiRepository sezonFiyatiRepository;

    public SezonFiyatiService(SezonFiyatiRepository sezonFiyatiRepository) {
        this.sezonFiyatiRepository = sezonFiyatiRepository;
    }

    public List<SezonFiyati> findAll() {
        return sezonFiyatiRepository.findAll();
    }

    public SezonFiyati save(SezonFiyati sezonFiyati) {
        return ekle(sezonFiyati);
    }

    public SezonFiyati ekle(SezonFiyati sezonFiyati) {
        if (sezonFiyati == null) {
            throw new IllegalArgumentException("Kayıt bilgileri boş olamaz.");
        }
        if (sezonFiyati.getOdatipi() == null || sezonFiyati.getOdatipi().getTipid() == null
                || sezonFiyati.getOdatipi().getTipid() <= 0) {
            throw new IllegalArgumentException("Geçerli bir oda tipi belirtilmelidir.");
        }
        if (sezonFiyati.getGecelikfiyat() == null
                || sezonFiyati.getGecelikfiyat().compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Gecelik fiyat boş veya negatif olamaz.");
        }
        if (sezonFiyati.getBaslangictarihi() == null || sezonFiyati.getBitistarihi() == null) {
            throw new IllegalArgumentException("Başlangıç ve bitiş tarihleri boş bırakılamaz.");
        }
        if (!sezonFiyati.getBitistarihi().isAfter(sezonFiyati.getBaslangictarihi())) {
            throw new IllegalArgumentException("Bitiş tarihi başlangıç tarihinden sonra olmalıdır.");
        }
        return sezonFiyatiRepository.save(sezonFiyati);
    }

    public SezonFiyati findById(Integer id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("Geçersiz ID değeri.");
        }
        return sezonFiyatiRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("SezonFiyati bulunamadı. ID: " + id));
    }
}