package com.example.otel_sistemi.controller;

import com.example.otel_sistemi.entity.SezonFiyati;
import com.example.otel_sistemi.service.SezonFiyatiService;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.List;

@RestController
@RequestMapping("/api/sezon-fiyatlari")
public class SezonFiyatiController {

    private final SezonFiyatiService sezonFiyatiService;

    public SezonFiyatiController(SezonFiyatiService sezonFiyatiService) {
        this.sezonFiyatiService = sezonFiyatiService;
    }

    @GetMapping({"", "/hepsi"})
    public List<SezonFiyati> tumunuGetir() {
        return sezonFiyatiService.findAll();
    }

    @GetMapping("/{id}")
    public SezonFiyati findById(@PathVariable Integer id) {
        return sezonFiyatiService.findById(id);
    }

    @PostMapping({"", "/ekle"})
    public SezonFiyati ekle(@RequestBody SezonFiyati sezonFiyati) {
        return sezonFiyatiService.ekle(sezonFiyati);
    }
}