package com.example.otel_sistemi.service;

import com.example.otel_sistemi.entity.OdaTipi;
import com.example.otel_sistemi.repository.OdaTipiRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OdaTipiService {
    private final OdaTipiRepository odaTipiRepository;

    public OdaTipiService(OdaTipiRepository odaTipiRepository) {
        this.odaTipiRepository = odaTipiRepository;
    }

    public OdaTipi odaTipiEkle(OdaTipi odaTipi) {
        return odaTipiRepository.save(odaTipi);
    }

    public List<OdaTipi> findAll() {
        return odaTipiRepository.findAll();
    }
}