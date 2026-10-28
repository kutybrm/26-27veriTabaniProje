package com.example.otel_sistemi.controller;

import com.example.otel_sistemi.entity.TemizlikKaydi;
import com.example.otel_sistemi.service.TemizlikKaydiService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/temizlik-kayitlari")
public class TemizlikKaydiController {
    private final TemizlikKaydiService temizlikKaydiService;

    public TemizlikKaydiController(TemizlikKaydiService temizlikKaydiService) {
        this.temizlikKaydiService = temizlikKaydiService;
    }

    @GetMapping
    public List<TemizlikKaydi> findAll() {
        return temizlikKaydiService.findAll();
    }

    @GetMapping("/{id}")
    public TemizlikKaydi findById(@PathVariable Integer id) {
        return temizlikKaydiService.findById(id);
    }

    @PostMapping
    public TemizlikKaydi ekle(@RequestBody TemizlikKaydi kayit) {
        return temizlikKaydiService.ekle(kayit);
    }
}
