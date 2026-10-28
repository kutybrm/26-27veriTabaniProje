package com.example.otel_sistemi.controller;

import com.example.otel_sistemi.entity.Odeme;
import com.example.otel_sistemi.service.OdemeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/odemeler")
public class OdemeController {
    private final OdemeService service;

    public OdemeController(OdemeService service) {
        this.service = service;
    }

    @GetMapping
    public List<Odeme> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Odeme findById(@PathVariable Integer id) {
        return service.findById(id);
    }

    @PostMapping
    public Odeme ekle(@RequestBody Odeme odeme) {
        return service.ekle(odeme);
    }
}
