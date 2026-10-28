package com.example.otel_sistemi.controller;

import com.example.otel_sistemi.entity.IslemLog;
import com.example.otel_sistemi.service.IslemLogService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/islem-loglari")
public class IslemLogController {
    private final IslemLogService service;

    public IslemLogController(IslemLogService service) {
        this.service = service;
    }

    @GetMapping
    public List<IslemLog> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public IslemLog findById(@PathVariable Integer id) {
        return service.findById(id);
    }

    @PostMapping
    public IslemLog ekle(@RequestBody IslemLog log) {
        return service.ekle(log);
    }
}
