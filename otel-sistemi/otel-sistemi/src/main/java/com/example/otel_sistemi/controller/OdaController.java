package com.example.otel_sistemi.controller;

import com.example.otel_sistemi.entity.Oda;
import com.example.otel_sistemi.service.OdaService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/odalar")
public class OdaController {
    private final OdaService odaService;

    public OdaController(OdaService odaService) {
        this.odaService = odaService;
    }

    @PostMapping("/ekle")
    public Oda ekle(@RequestBody Oda oda) {
        return odaService.odaEkle(oda);
    }

    @GetMapping("/hepsi")
    public List<Oda> tumunuGetir() {
        return odaService.findAll();
    }
}