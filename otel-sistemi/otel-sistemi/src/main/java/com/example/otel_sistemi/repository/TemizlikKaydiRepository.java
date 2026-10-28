package com.example.otel_sistemi.repository;

import com.example.otel_sistemi.entity.TemizlikKaydi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TemizlikKaydiRepository extends JpaRepository<TemizlikKaydi, Integer> {
}