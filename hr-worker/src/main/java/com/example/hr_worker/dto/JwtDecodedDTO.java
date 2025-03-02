package com.example.hr_worker.dto;

import java.util.Map;

public class JwtDecodedDTO {
    private Map<String, Object> header;
    private Map<String, Object> payload;
    private String signature;

    public JwtDecodedDTO(Map<String, Object> header, Map<String, Object> payload, String signature) {
        this.header = header;
        this.payload = payload;
        this.signature = signature;
    }

    public Map<String, Object> getHeader() {
        return header;
    }

    public void setHeader(Map<String, Object> header) {
        this.header = header;
    }

    public Map<String, Object> getPayload() {
        return payload;
    }

    public void setPayload(Map<String, Object> payload) {
        this.payload = payload;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }
}
