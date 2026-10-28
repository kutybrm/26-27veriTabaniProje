package com.example.otel_sistemi.controller;

import com.example.otel_sistemi.entity.Misafir;
import com.example.otel_sistemi.service.MisafirService;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.List;

@RestController
@RequestMapping("/api/misafirler")
public class MisafirController {
    private final MisafirService misafirService;
    public MisafirController(MisafirService misafirService) {
        this.misafirService = misafirService;
    }

    @PostMapping({"", "/ekle"})
    public Misafir ekle(@RequestBody Misafir misafir) {
        return misafirService.ekle(misafir);
    }

    @GetMapping({"", "/hepsi"})
    public List<Misafir> tumunuGetir() {
        return misafirService.findAll();
    }

    @GetMapping("/{id}")
    public Misafir findById(@PathVariable Integer id) {
        return misafirService.findById(id);
    }
}