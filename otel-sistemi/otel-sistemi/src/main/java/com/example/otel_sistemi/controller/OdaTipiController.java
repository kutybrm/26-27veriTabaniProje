package com.example.otel_sistemi.controller;

import com.example.otel_sistemi.entity.OdaTipi;
import com.example.otel_sistemi.service.OdaTipiService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/oda-tipleri")
public class OdaTipiController {
    private final OdaTipiService odaTipiService;

    public OdaTipiController(OdaTipiService odaTipiService) {

        this.odaTipiService = odaTipiService;
    }

    @PostMapping({"", "/ekle"})
    public OdaTipi ekle(@RequestBody OdaTipi odaTipi) {
        return odaTipiService.ekle(odaTipi);
    }

    @GetMapping({"", "/hepsi"})
    public List<OdaTipi> tumunuGetir() {
        return odaTipiService.findAll();
    }

    @GetMapping("/{id}")
    public OdaTipi findById(@PathVariable Integer id) {
        return odaTipiService.findById(id);
    }
}