package com.example.otel_sistemi.repository;

import com.example.otel_sistemi.entity.Oda;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface OdaRepository extends JpaRepository<Oda, Integer> {
    Optional<Oda> findByOdanumarasi(Integer odanumarasi);
}