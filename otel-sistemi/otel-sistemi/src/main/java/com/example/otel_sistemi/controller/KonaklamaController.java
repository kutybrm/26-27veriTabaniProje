package com.example.otel_sistemi.controller;

import com.example.otel_sistemi.entity.Konaklama;
import com.example.otel_sistemi.service.KonaklamaService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/konaklamalar")
public class KonaklamaController {
    private final KonaklamaService service;

    public KonaklamaController(KonaklamaService service) {
        this.service = service;
    }

    @GetMapping
    public List<Konaklama> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Konaklama findById(@PathVariable Integer id) {
        return service.findById(id);
    }

    @PostMapping
    public Konaklama ekle(@RequestBody Konaklama konaklama) {
        return service.ekle(konaklama);
    }
}
