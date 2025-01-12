package com.example.hr_payroll.services;

import com.example.hr_payroll.config.FeingRest;
import com.example.hr_payroll.domain.entities.Payment;
import com.example.hr_payroll.models.Worker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class PayrollService {
    @Value("${spring.worker.url}")
    private String workerurl;

    @Autowired
    private RestTemplate restTemplate;

    public Payment getPayment(Long workerId, int days){
        Map<String, String> uriVariables = new HashMap<>();
        uriVariables.put("id", String.valueOf(workerId));
        String urlCompleta = workerurl + "/workers/worker/{id}";
        Worker worker = restTemplate.getForObject(urlCompleta, Worker.class, uriVariables);
        return new Payment(worker.getName(), worker.getDailyIncome(), days);
    }

//    @Autowired
//    private FeingRest feign;
//
//    public Payment getPayment(Long workerId, int days){
//        Worker worker = feign.buscarTrabalhador(workerId);
//        Payment payment = new Payment();
//        payment.setName(worker.getName());
//        payment.setDailyIncome(worker.getDailyIncome());
//        payment.setDays(days);
//        return payment;
//    }
}
