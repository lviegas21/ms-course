package com.example.hr_payroll.services;

import com.example.hr_payroll.config.FeingRest;
import com.example.hr_payroll.domain.entities.Payment;
import com.example.hr_payroll.models.Worker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PayrollService {

    @Autowired
    private FeingRest feign;

    public Payment getPayment(Long workerId, int days){
        Worker worker = feign.buscarTrabalhador(workerId);
        Payment payment = new Payment();
        payment.setName(worker.getName());
        payment.setDailyIncome(worker.getDailyIncome());
        payment.setDays(days);
        return payment;
    }
}
