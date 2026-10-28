package com.example.otel_sistemi.service;

import com.example.otel_sistemi.entity.Personel;
import com.example.otel_sistemi.repository.PersonelRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PersonelService {
    private final PersonelRepository personelRepository;

    public PersonelService(PersonelRepository personelRepository) {
        this.personelRepository = personelRepository;
    }

    public List<Personel> findAll() {
        return personelRepository.findAll();
    }

    public Personel save(Personel personel) {
        return personelRepository.save(personel);
    }
}
