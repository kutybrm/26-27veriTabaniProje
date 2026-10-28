package com.example.otel_sistemi.service;

import jakarta.persistence.EntityNotFoundException;
import com.example.otel_sistemi.entity.TemizlikKaydi;
import com.example.otel_sistemi.repository.TemizlikKaydiRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TemizlikKaydiService {
    private final TemizlikKaydiRepository temizlikKaydiRepository;
    public TemizlikKaydiService(TemizlikKaydiRepository repository) {
        this.temizlikKaydiRepository = repository;
    }

    public TemizlikKaydi ekle(TemizlikKaydi kayit) {
        if (kayit == null) {
            throw new IllegalArgumentException("Temizlik kaydı boş olamaz.");
        }
        if (kayit.getOdaid() == null || kayit.getOdaid() <= 0) {
            throw new IllegalArgumentException("Geçerli bir oda ID belirtilmelidir.");
        }
        if (kayit.getPersonelid() == null || kayit.getPersonelid() <= 0) {
            throw new IllegalArgumentException("Geçerli bir personel ID belirtilmelidir.");
        }
        if (kayit.getTemizliktarihi() == null) {
            throw new IllegalArgumentException("Temizlik tarihi boş bırakılamaz.");
        }
        if (kayit.getDurum() == null || kayit.getDurum().isBlank()) {
            kayit.setDurum("Bekliyor");
        } else if (!List.of("Bekliyor", "DevamEdiyor", "Tamamlandi").contains(kayit.getDurum())) {
            throw new IllegalArgumentException("Geçersiz temizlik durumu. Değerler: Bekliyor, DevamEdiyor, Tamamlandi olmalıdır.");
        }

        return temizlikKaydiRepository.save(kayit);
    }

    public List<TemizlikKaydi> findAll() {
        return temizlikKaydiRepository.findAll();
    }

    public TemizlikKaydi findById(Integer id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("Geçersiz ID değeri.");
        }
        return temizlikKaydiRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("TemizlikKaydi bulunamadı. ID: " + id));
    }
}
