package com.example.otel_sistemi.service;

import jakarta.persistence.EntityNotFoundException;
import com.example.otel_sistemi.entity.Kullanici;
import com.example.otel_sistemi.repository.KullaniciRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KullaniciService {

    private final KullaniciRepository kullaniciRepository;

    public KullaniciService(KullaniciRepository kullaniciRepository) {

        this.kullaniciRepository = kullaniciRepository;
    }

    public Kullanici kullaniciEkle(Kullanici kullanici) {
        return ekle(kullanici);
    }

    public Kullanici ekle(Kullanici kullanici) {
        if (kullanici == null) {
            throw new IllegalArgumentException("Kayıt bilgileri boş olamaz.");
        }
        if (kullanici.getEposta() == null || kullanici.getEposta().trim().isEmpty()) {
            throw new IllegalArgumentException("E-posta boş bırakılamaz.");
        }
        if (kullanici.getSifrehash() == null || kullanici.getSifrehash().trim().isEmpty()) {
            throw new IllegalArgumentException("Şifre özeti boş bırakılamaz.");
        }
        if (kullanici.getRol() == null) {
            throw new IllegalArgumentException("Kullanıcı rolü boş bırakılamaz.");
        }
        kullanici.setEposta(kullanici.getEposta().trim());
        kullanici.setSifrehash(kullanici.getSifrehash().trim());
        if (kullanici.getEposta().length() > 255 || kullanici.getSifrehash().length() > 255) {
            throw new IllegalArgumentException("Kullanıcı alanları 255 karakteri aşamaz.");
        }
        return kullaniciRepository.save(kullanici);
    }

    public List<Kullanici> findAll() {
        return kullaniciRepository.findAll();
    }

    public Kullanici findById(Integer id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("Geçersiz ID değeri.");
        }
        return kullaniciRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Kullanici bulunamadı. ID: " + id));
    }

}