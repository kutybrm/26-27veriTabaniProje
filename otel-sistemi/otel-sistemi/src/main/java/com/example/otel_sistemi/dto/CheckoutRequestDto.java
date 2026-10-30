package com.example.otel_sistemi.dto;

public class CheckoutRequestDto {
    private Integer konaklamaId;
    private String odemeYontemi;

    public CheckoutRequestDto() {
    }

    public CheckoutRequestDto(Integer konaklamaId, String odemeYontemi) {
        this.konaklamaId = konaklamaId;
        this.odemeYontemi = odemeYontemi;
    }

    public Integer getKonaklamaId() {
        return konaklamaId;
    }

    public void setKonaklamaId(Integer konaklamaId) {
        this.konaklamaId = konaklamaId;
    }

    public String getOdemeYontemi() {
        return odemeYontemi;
    }

    public void setOdemeYontemi(String odemeYontemi) {
        this.odemeYontemi = odemeYontemi;
    }
}