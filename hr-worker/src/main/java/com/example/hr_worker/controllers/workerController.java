package com.example.hr_worker.controllers;

import com.example.hr_worker.domain.entities.Worker;
import com.example.hr_worker.dto.JwtDecodedDTO;
import com.example.hr_worker.repositories.WorkerRepository;

import com.example.hr_worker.service.OnlineUserLogService;
import com.example.hr_worker.util.JwtUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

@RestController
@RequestMapping("/workers")
public class workerController {

    @Autowired
    private OnlineUserLogService onlineUserLogService;
    @Autowired
    private WorkerRepository repository;

    @Autowired
    private Environment env;

    private static String extractWorkerData(String logLine) {
        Pattern pattern = Pattern.compile("Worker\\{id=(\\d+), name='(.*?)', dailyIncome=([\\d.]+)\\}");
        Matcher matcher = pattern.matcher(logLine);

        if (matcher.find()) {
            return "ID: " + matcher.group(1) + ", Nome: " + matcher.group(2) + ", Renda Diária: " + matcher.group(3);
        }
        return null;
    }



    public void decodificarToken(String token){
        String jwtToken = token.replace("Bearer ", "");

        // Decodificar o token JWT
        JwtDecodedDTO decodedInfo = JwtUtil.decodeToken(jwtToken);
        System.out.println(decodedInfo);
    }

    @GetMapping("/worker/{id}")
    public ResponseEntity<Worker> buscarWorker(@PathVariable Long id, @RequestHeader("Authorization") String token){

        //logger.info("PORT = " + env.getProperty("local.server.port"));
        decodificarToken(token);
        Worker worker = repository.findById(id).get();
        try {
            onlineUserLogService.updateUserActivity(worker);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return ResponseEntity.ok().body(worker);
    }
    @GetMapping("/all")
    public ResponseEntity<List<Worker>> allWorkers(){

        List<Worker> lista = repository.findAll();
        return ResponseEntity.ok().body(lista);
    }
    @PostMapping
    public Worker createWorker(){
        return null;
    }
}
