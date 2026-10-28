package com.example.otel_sistemi.service;

import com.example.otel_sistemi.entity.Rezervasyon;
import com.example.otel_sistemi.repository.RezervasyonRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RezervasyonService {
    private final RezervasyonRepository rezervasyonRepository;

    public RezervasyonService(RezervasyonRepository rezervasyonRepository) {
        this.rezervasyonRepository = rezervasyonRepository;
    }

    public List<Rezervasyon> findAll() {
        return rezervasyonRepository.findAll();
    }

    public Rezervasyon findById(Integer id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("Geçersiz ID değeri.");
        }
        return rezervasyonRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Rezervasyon bulunamadı. ID: " + id));
    }

    public Rezervasyon ekle(Rezervasyon rezervasyon) {
        if (rezervasyon == null) {
            throw new IllegalArgumentException("Kayıt bilgileri boş olamaz.");
        }
        if (rezervasyon.getMisafir() == null || rezervasyon.getMisafir().getMisafirid() == null
                || rezervasyon.getMisafir().getMisafirid() <= 0) {
            throw new IllegalArgumentException("Geçerli bir misafir belirtilmelidir.");
        }
        if (rezervasyon.getOda() == null || rezervasyon.getOda().getOdaid() == null
                || rezervasyon.getOda().getOdaid() <= 0) {
            throw new IllegalArgumentException("Geçerli bir oda belirtilmelidir.");
        }
        if (rezervasyon.getGiristarihi() == null || rezervasyon.getCikistarihi() == null) {
            throw new IllegalArgumentException("Giriş ve çıkış tarihleri boş bırakılamaz.");
        }
        if (!rezervasyon.getCikistarihi().isAfter(rezervasyon.getGiristarihi())) {
            throw new IllegalArgumentException("Çıkış tarihi giriş tarihinden sonra olmalıdır.");
        }
        if (rezervasyon.getKisisayisi() == null || rezervasyon.getKisisayisi() <= 0) {
            throw new IllegalArgumentException("Kişi sayısı 0'dan büyük olmalıdır.");
        }
        if (rezervasyon.getDurum() == null) {
            throw new IllegalArgumentException("Rezervasyon durumu boş bırakılamaz.");
        }
        return rezervasyonRepository.save(rezervasyon);
    }

    public List<Rezervasyon> findByMisafirId(Integer misafirid) {
        if (misafirid == null || misafirid <= 0) {
            throw new IllegalArgumentException("Geçersiz misafir ID değeri.");
        }
        return rezervasyonRepository.findByMisafir_Misafirid(misafirid);
    }
}
