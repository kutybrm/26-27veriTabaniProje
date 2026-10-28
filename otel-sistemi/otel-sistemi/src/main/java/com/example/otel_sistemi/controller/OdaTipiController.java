package com.example.otel_sistemi.controller;

import com.example.otel_sistemi.entity.OdaTipi;
import com.example.otel_sistemi.service.OdaTipiService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/odatipleri")
public class OdaTipiController {
    private final OdaTipiService odaTipiService;

    public OdaTipiController(OdaTipiService odaTipiService) {

        this.odaTipiService = odaTipiService;
    }

    @PostMapping("/ekle")
    public OdaTipi ekle(@RequestBody OdaTipi odaTipi) {

        return odaTipiService.odaTipiEkle(odaTipi);
    }

    @GetMapping("/hepsi")
    public List<OdaTipi> tumunuGetir() {
        return odaTipiService.findAll();
    }
}