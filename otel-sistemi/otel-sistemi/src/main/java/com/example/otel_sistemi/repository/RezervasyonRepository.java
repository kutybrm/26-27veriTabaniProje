package com.example.otel_sistemi.repository;

import com.example.otel_sistemi.entity.Rezervasyon;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RezervasyonRepository extends JpaRepository<Rezervasyon, Integer> {
    List<Rezervasyon> findByMisafir_Misafirid(Integer misafirid);
}
