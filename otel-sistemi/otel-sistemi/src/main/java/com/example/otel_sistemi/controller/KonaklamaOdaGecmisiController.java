package com.example.otel_sistemi.controller;

import com.example.otel_sistemi.entity.KonaklamaOdaGecmisi;
import com.example.otel_sistemi.service.KonaklamaOdaGecmisiService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/konaklama-oda-gecmisleri")
public class KonaklamaOdaGecmisiController {
    private final KonaklamaOdaGecmisiService service;

    public KonaklamaOdaGecmisiController(KonaklamaOdaGecmisiService service) {
        this.service = service;
    }

    @GetMapping
    public List<KonaklamaOdaGecmisi> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public KonaklamaOdaGecmisi findById(@PathVariable Integer id) {
        return service.findById(id);
    }

    @PostMapping
    public KonaklamaOdaGecmisi ekle(@RequestBody KonaklamaOdaGecmisi kayit) {
        return service.ekle(kayit);
    }
}
