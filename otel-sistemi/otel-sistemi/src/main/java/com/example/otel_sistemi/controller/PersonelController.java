package com.example.otel_sistemi.controller;

import com.example.otel_sistemi.entity.Personel;
import com.example.otel_sistemi.service.PersonelService;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.List;

@RestController
@RequestMapping("/api/personeller")
public class PersonelController {

    private final PersonelService personelService;

    public PersonelController(PersonelService personelService) {
        this.personelService = personelService;
    }

    @PostMapping("/ekle")
    public Personel ekle(@RequestBody Personel personel) {
        return personelService.save(personel);
    }

    @GetMapping("/hepsi")
    public List<Personel> tumunuGetir() {
        return personelService.findAll();
    }
}