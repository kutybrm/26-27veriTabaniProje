package com.example.otel_sistemi.service;

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

        return kullaniciRepository.save(kullanici);
    }

    public List<Kullanici> findAll() {
        return kullaniciRepository.findAll();
    }

}