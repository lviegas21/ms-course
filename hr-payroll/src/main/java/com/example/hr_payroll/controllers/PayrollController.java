package com.example.hr_payroll.controllers;

import com.example.hr_payroll.domain.entities.Payment;
import com.example.hr_payroll.services.PayrollService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/payroll")
public class PayrollController {
    @Autowired
    private PayrollService service;

    @GetMapping("/worker/{id}/days/{days}")
    public ResponseEntity<Payment> getPayment(@PathVariable Long id, @PathVariable Integer days){
        Payment payment = service.getPayment(id, days);
        return ResponseEntity.ok().body(payment);
    }
}
