package com.example.otel_sistemi.service;

import com.example.otel_sistemi.entity.Personel;
import com.example.otel_sistemi.repository.PersonelRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PersonelService {
    private final PersonelRepository personelRepository;

    public PersonelService(PersonelRepository personelRepository) {
        this.personelRepository = personelRepository;
    }

    public List<Personel> findAll() {
        return personelRepository.findAll();
    }

    public Personel save(Personel personel) {
        return ekle(personel);
    }

    public Personel ekle(Personel personel) {
        if (personel == null) {
            throw new IllegalArgumentException("Kayıt bilgileri boş olamaz.");
        }
        if (personel.getKullanici() == null || personel.getKullanici().getKullaniciid() == null
                || personel.getKullanici().getKullaniciid() <= 0) {
            throw new IllegalArgumentException("Geçerli bir kullanıcı belirtilmelidir.");
        }
        if (personel.getAd() == null || personel.getAd().trim().isEmpty()) {
            throw new IllegalArgumentException("Ad boş bırakılamaz.");
        }
        if (personel.getSoyad() == null || personel.getSoyad().trim().isEmpty()) {
            throw new IllegalArgumentException("Soyad boş bırakılamaz.");
        }
        if (personel.getGorev() == null || personel.getGorev().trim().isEmpty()) {
            throw new IllegalArgumentException("Görev boş bırakılamaz.");
        }
        if (personel.getIsegiristarihi() == null) {
            throw new IllegalArgumentException("İşe giriş tarihi boş bırakılamaz.");
        }
        personel.setAd(personel.getAd().trim());
        personel.setSoyad(personel.getSoyad().trim());
        personel.setGorev(personel.getGorev().trim());
        if (personel.getAd().length() > 100 || personel.getSoyad().length() > 100
                || personel.getGorev().length() > 100) {
            throw new IllegalArgumentException("Personel alanları 100 karakteri aşamaz.");
        }
        return personelRepository.save(personel);
    }

    public Personel findById(Integer id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("Geçersiz ID değeri.");
        }
        return personelRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Personel bulunamadı. ID: " + id));
    }
}
