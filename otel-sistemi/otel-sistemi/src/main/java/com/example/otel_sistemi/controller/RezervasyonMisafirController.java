package com.example.otel_sistemi.controller;

import com.example.otel_sistemi.entity.RezervasyonMisafir;
import com.example.otel_sistemi.service.RezervasyonMisafirService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/rezervasyon-misafirleri")
public class RezervasyonMisafirController {
    private final RezervasyonMisafirService service;

    public RezervasyonMisafirController(RezervasyonMisafirService service) {
        this.service = service;
    }

    @GetMapping
    public List<RezervasyonMisafir> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public RezervasyonMisafir findById(@PathVariable Integer id) {
        return service.findById(id);
    }

    @PostMapping
    public RezervasyonMisafir ekle(@RequestBody RezervasyonMisafir kayit) {
        return service.ekle(kayit);
    }
}
