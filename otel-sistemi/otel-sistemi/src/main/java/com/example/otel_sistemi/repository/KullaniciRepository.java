package com.example.otel_sistemi.repository;

import com.example.otel_sistemi.entity.Kullanici;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface KullaniciRepository extends JpaRepository<Kullanici, Integer> {
    Optional<Kullanici> findByEposta(String eposta);
}