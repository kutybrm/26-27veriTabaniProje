package com.example.otel_sistemi.service;

import jakarta.persistence.EntityNotFoundException;
import com.example.otel_sistemi.entity.Misafir;
import com.example.otel_sistemi.repository.MisafirRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class MisafirService {
    private final MisafirRepository misafirRepository;

    public MisafirService(MisafirRepository misafirRepository) {
        this.misafirRepository = misafirRepository;
    }

    public Misafir misafirEkle(Misafir misafir) {
        return ekle(misafir);
    }

    public Misafir ekle(Misafir misafir) {
        if (misafir == null) {
            throw new IllegalArgumentException("Kayıt bilgileri boş olamaz.");
        }
        if (misafir.getAd() == null || misafir.getAd().trim().isEmpty()) {
            throw new IllegalArgumentException("Ad boş bırakılamaz.");
        }
        if (misafir.getSoyad() == null || misafir.getSoyad().trim().isEmpty()) {
            throw new IllegalArgumentException("Soyad boş bırakılamaz.");
        }
        if (misafir.getTelefonno() == null || misafir.getTelefonno().trim().isEmpty()) {
            throw new IllegalArgumentException("Telefon numarası boş bırakılamaz.");
        }
        if (misafir.getKullanici() != null
                && (misafir.getKullanici().getKullaniciid() == null
                || misafir.getKullanici().getKullaniciid() <= 0)) {
            throw new IllegalArgumentException("Geçerli bir kullanıcı belirtilmelidir.");
        }
        misafir.setAd(misafir.getAd().trim());
        misafir.setSoyad(misafir.getSoyad().trim());
        misafir.setTelefonno(misafir.getTelefonno().trim());
        if (misafir.getAd().length() > 100 || misafir.getSoyad().length() > 100
                || misafir.getTelefonno().length() > 20) {
            throw new IllegalArgumentException("Misafir bilgileri şema alan sınırlarını aşamaz.");
        }
        if (misafir.getTcno() != null) {
            misafir.setTcno(misafir.getTcno().trim());
            if (misafir.getTcno().isEmpty()) {
                misafir.setTcno(null);
            } else if (!misafir.getTcno().matches("\\d{11}")) {
                throw new IllegalArgumentException("TC numarası 11 rakamdan oluşmalıdır.");
            }
        }
        if (misafir.getPasaportno() != null) {
            misafir.setPasaportno(misafir.getPasaportno().trim());
            if (misafir.getPasaportno().isEmpty()) {
                misafir.setPasaportno(null);
            }
        }
        if (misafir.getPasaportno() != null && misafir.getPasaportno().length() > 50) {
            throw new IllegalArgumentException("Pasaport numarası 50 karakteri aşamaz.");
        }
        return misafirRepository.save(misafir);
    }

    public List<Misafir> findAll() {
        return misafirRepository.findAll();
    }

    public Misafir findById(Integer id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("Geçersiz ID değeri.");
        }
        return misafirRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Misafir bulunamadı. ID: " + id));
    }
}
