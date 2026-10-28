package com.example.otel_sistemi.service;

import com.example.otel_sistemi.entity.IslemLog;
import com.example.otel_sistemi.repository.IslemLogRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IslemLogService {
    private final IslemLogRepository repository;

    public IslemLogService(IslemLogRepository repository) {
        this.repository = repository;
    }

    public List<IslemLog> findAll() {
        return repository.findAll();
    }

    public IslemLog findById(Integer id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("Geçersiz ID değeri.");
        }
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("IslemLog bulunamadı. ID: " + id));
    }

    public IslemLog ekle(IslemLog log) {
        if (log == null) {
            throw new IllegalArgumentException("Kayıt bilgileri boş olamaz.");
        }
        if (log.getTabloadi() == null || log.getTabloadi().trim().isEmpty()) {
            throw new IllegalArgumentException("Tablo adı boş bırakılamaz.");
        }
        if (log.getIslemturu() == null || log.getIslemturu().trim().isEmpty()) {
            throw new IllegalArgumentException("İşlem türü boş bırakılamaz.");
        }
        if (log.getAciklama() == null || log.getAciklama().trim().isEmpty()) {
            throw new IllegalArgumentException("Açıklama boş bırakılamaz.");
        }
        if (log.getKullanici() != null
                && (log.getKullanici().getKullaniciid() == null || log.getKullanici().getKullaniciid() <= 0)) {
            throw new IllegalArgumentException("Geçerli bir kullanıcı belirtilmelidir.");
        }
        log.setTabloadi(log.getTabloadi().trim());
        log.setIslemturu(log.getIslemturu().trim());
        log.setAciklama(log.getAciklama().trim());
        if (log.getTabloadi().length() > 100 || log.getIslemturu().length() > 100) {
            throw new IllegalArgumentException("Tablo adı ve işlem türü 100 karakteri aşamaz.");
        }
        return repository.save(log);
    }
}
