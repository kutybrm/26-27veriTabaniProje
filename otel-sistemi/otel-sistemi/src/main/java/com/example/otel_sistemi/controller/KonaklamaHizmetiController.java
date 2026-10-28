package com.example.otel_sistemi.controller;

import com.example.otel_sistemi.entity.KonaklamaHizmeti;
import com.example.otel_sistemi.service.KonaklamaHizmetiService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/konaklama-hizmetleri")
public class KonaklamaHizmetiController {
    private final KonaklamaHizmetiService service;

    public KonaklamaHizmetiController(KonaklamaHizmetiService service) {
        this.service = service;
    }

    @GetMapping
    public List<KonaklamaHizmeti> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public KonaklamaHizmeti findById(@PathVariable Integer id) {
        return service.findById(id);
    }

    @PostMapping
    public KonaklamaHizmeti ekle(@RequestBody KonaklamaHizmeti kayit) {
        return service.ekle(kayit);
    }
}
