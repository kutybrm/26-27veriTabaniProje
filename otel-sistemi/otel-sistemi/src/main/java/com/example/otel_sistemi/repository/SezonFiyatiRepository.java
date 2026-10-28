package com.example.otel_sistemi.repository;

import com.example.otel_sistemi.entity.SezonFiyati;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SezonFiyatiRepository extends JpaRepository<SezonFiyati, Integer> {
}
