package com.example.otel_sistemi.exception;

import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    public record HataYaniti(
            LocalDateTime zaman,
            int durum,
            String hata,
            String mesaj,
            String yol
    ) {}

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<HataYaniti> handleValidation(IllegalArgumentException ex, HttpServletRequest request) {
        HataYaniti hata = new HataYaniti(
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST.value(),
                "Validasyon Hatası",
                ex.getMessage(),
                request.getRequestURI()
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(hata);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<HataYaniti> handleNotFound(EntityNotFoundException ex, HttpServletRequest request) {
        HataYaniti hata = new HataYaniti(
                LocalDateTime.now(),
                HttpStatus.NOT_FOUND.value(),
                "Kayıt Bulunamadı",
                ex.getMessage(),
                request.getRequestURI()
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(hata);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<HataYaniti> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpServletRequest request) {
        String detaylar = ex.getBindingResult().getFieldErrors().stream()
                .map(err -> err.getField() + ": " + err.getDefaultMessage())
                .collect(Collectors.joining("; "));

        HataYaniti hata = new HataYaniti(
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST.value(),
                "Giriş Doğrulama Hatası",
                detaylar,
                request.getRequestURI()
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(hata);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<HataYaniti> handleTypeMismatch(MethodArgumentTypeMismatchException ex, HttpServletRequest request) {
        HataYaniti hata = new HataYaniti(
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST.value(),
                "Geçersiz Parametre Tipi",
                String.format("'%s' parametresi beklenen tiple uyuşmuyor: %s", ex.getName(), ex.getValue()),
                request.getRequestURI()
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(hata);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<HataYaniti> handleNotReadable(HttpMessageNotReadableException ex, HttpServletRequest request) {
        HataYaniti hata = new HataYaniti(
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST.value(),
                "Geçersiz İstek Gövdesi",
                "JSON formatı hatalı veya girilen enum/tarih değeri sistemle uyuşmuyor.",
                request.getRequestURI()
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(hata);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<HataYaniti> handleConflict(DataIntegrityViolationException ex, HttpServletRequest request) {
        String kokMesaj = ex.getMostSpecificCause() != null ? ex.getMostSpecificCause().getMessage() : ex.getMessage();

        HataYaniti hata = new HataYaniti(
                LocalDateTime.now(),
                HttpStatus.CONFLICT.value(),
                "Veri Bütünlüğü / Kısıt Hatası",
                kokMesaj,
                request.getRequestURI()
        );
        return ResponseEntity.status(HttpStatus.CONFLICT).body(hata);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<HataYaniti> handleGeneral(Exception ex, HttpServletRequest request) {
        HataYaniti hata = new HataYaniti(
                LocalDateTime.now(),
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "Sunucu Hatası",
                "Beklenmeyen bir hata oluştu: " + ex.getMessage(),
                request.getRequestURI()
        );
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(hata);
    }
}