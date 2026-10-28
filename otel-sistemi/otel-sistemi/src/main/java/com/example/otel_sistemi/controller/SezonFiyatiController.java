package com.example.otel_sistemi.controller;

import com.example.otel_sistemi.entity.SezonFiyati;
import com.example.otel_sistemi.service.SezonFiyatiService;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.List;

@RestController
@RequestMapping("/api/sezonfiyatlari")
public class SezonFiyatiController {

    private final SezonFiyatiService sezonFiyatiService;

    public SezonFiyatiController(SezonFiyatiService sezonFiyatiService) {
        this.sezonFiyatiService = sezonFiyatiService;
    }

    @GetMapping("/hepsi")
    public List<SezonFiyati> tumunuGetir() {
        return sezonFiyatiService.findAll();
    }

    @PostMapping("/ekle")
    public SezonFiyati ekle(@RequestBody SezonFiyati sezonFiyati) {
        return sezonFiyatiService.save(sezonFiyati);
    }
}