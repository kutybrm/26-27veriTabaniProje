package com.example.otel_sistemi.controller;

import com.example.otel_sistemi.entity.Rezervasyon;
import com.example.otel_sistemi.service.RezervasyonService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/rezervasyonlar")
public class RezervasyonController {
    private final RezervasyonService service;

    public RezervasyonController(RezervasyonService service) {
        this.service = service;
    }

    @GetMapping
    public List<Rezervasyon> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Rezervasyon findById(@PathVariable Integer id) {
        return service.findById(id);
    }

    @PostMapping
    public Rezervasyon ekle(@RequestBody Rezervasyon rezervasyon) {
        return service.ekle(rezervasyon);
    }
}
