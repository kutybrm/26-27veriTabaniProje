package com.example.otel_sistemi.controller;

import com.example.otel_sistemi.entity.Kullanici;
import org.springframework.web.bind.annotation.*;
import com.example.otel_sistemi.service.KullaniciService;

import java.util.List;

@RestController
@RequestMapping("/api/kullanicilar")

public class KullaniciController {
    private final KullaniciService kullaniciService;

    public KullaniciController(KullaniciService kullaniciService) {

        this.kullaniciService = kullaniciService;
    }

    @PostMapping({"", "/ekle"})
    public Kullanici ekle(@RequestBody Kullanici kullanici) {
        return kullaniciService.ekle(kullanici);
    }

    @GetMapping({"", "/hepsi"})
    public List<Kullanici> tumunuGetir() {
        return kullaniciService.findAll();
    }

    @GetMapping("/{id}")
    public Kullanici findById(@PathVariable Integer id) {
        return kullaniciService.findById(id);
    }
}
