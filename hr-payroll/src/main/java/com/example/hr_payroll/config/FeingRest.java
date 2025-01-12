package com.example.hr_payroll.config;

import com.example.hr_payroll.models.Worker;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient(name = "hr-worker")
public interface FeingRest {
    @GetMapping("/workers/worker/{id}")
    public Worker buscarTrabalhador(@PathVariable Long id);
}
