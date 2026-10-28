package com.example.otel_sistemi.repository;

import com.example.otel_sistemi.entity.Hizmet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HizmetRepository extends JpaRepository<Hizmet, Integer> {
}