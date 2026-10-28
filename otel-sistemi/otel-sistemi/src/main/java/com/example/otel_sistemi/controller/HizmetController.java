package com.example.otel_sistemi.controller;

import com.example.otel_sistemi.entity.Hizmet;
import com.example.otel_sistemi.service.HizmetService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/hizmetler")
public class HizmetController {
    private final HizmetService hizmetService;
    public HizmetController(HizmetService hizmetService) {
        this.hizmetService = hizmetService;
    }

    @GetMapping
    public List<Hizmet> findAll() {
        return hizmetService.findAll();
    }

    @GetMapping("/{id}")
    public Hizmet findById(@PathVariable Integer id) {
        return hizmetService.findById(id);
    }

    @PostMapping
    public Hizmet ekle(@RequestBody Hizmet hizmet) {
        return hizmetService.hizmetEkle(hizmet);
    }
}
