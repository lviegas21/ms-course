package com.example.hr_payroll.services;

import com.example.hr_payroll.domain.entities.Payment;
import org.springframework.stereotype.Service;

@Service
public class PayrollService {

    public Payment getPayment(Long workerId, int days){
        return new Payment("Bob", 200.0, days);
    }
}
