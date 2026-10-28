package com.example.otel_sistemi.repository;

import com.example.otel_sistemi.entity.Kullanici;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KullaniciRepository extends JpaRepository<Kullanici, Integer> {
}