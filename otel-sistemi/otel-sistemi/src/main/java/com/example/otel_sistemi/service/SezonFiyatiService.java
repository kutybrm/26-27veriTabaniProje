package com.example.otel_sistemi.service;

import com.example.otel_sistemi.entity.SezonFiyati;
import com.example.otel_sistemi.repository.SezonFiyatiRepository;
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
        if (sezonFiyati.getGecelikfiyat() == null || sezonFiyati.getGecelikfiyat().compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Gecelik fiyat 0'dan küçük olamaz!");
        }

        if (sezonFiyati.getBaslangictarihi() == null || sezonFiyati.getBitistarihi() == null) {
            throw new IllegalArgumentException("Başlangıç ve bitiş tarihleri boş bırakılamaz!");
        }

        if (!sezonFiyati.getBitistarihi().isAfter(sezonFiyati.getBaslangictarihi())) {
            throw new IllegalArgumentException("Bitiş tarihi başlangıç tarihinden sonra olmalıdır!");
        }

        return sezonFiyatiRepository.save(sezonFiyati);
    }
}