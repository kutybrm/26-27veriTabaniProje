package com.example.otel_sistemi.service;

import com.example.otel_sistemi.entity.Oda;
import com.example.otel_sistemi.repository.OdaRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class OdaService {
    private final OdaRepository odaRepository;

    public OdaService(OdaRepository odaRepository) {
        this.odaRepository = odaRepository;
    }

    public Oda odaEkle(Oda oda) {
        return odaRepository.save(oda);
    }

    public List<Oda> findAll() {
        return odaRepository.findAll();
    }
}