package com.example.otel_sistemi.service;

import com.example.otel_sistemi.entity.Misafir;
import com.example.otel_sistemi.repository.MisafirRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class MisafirService {
    private final MisafirRepository misafirRepository;

    public MisafirService(MisafirRepository misafirRepository) {
        this.misafirRepository = misafirRepository;
    }

    public Misafir misafirEkle(Misafir misafir) {
        return misafirRepository.save(misafir);
    }

    public List<Misafir> findAll() {
        return misafirRepository.findAll();
    }
}
