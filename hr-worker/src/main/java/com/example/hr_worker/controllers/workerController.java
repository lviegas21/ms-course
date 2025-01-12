package com.example.hr_worker.controllers;

import com.example.hr_worker.domain.entities.Worker;
import com.example.hr_worker.repositories.WorkerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/workers")
public class workerController {
    @Autowired
    private WorkerRepository repository;

    @GetMapping("/worker/{id}")
    public ResponseEntity<Worker> buscarWorker(@PathVariable Long id){
        Worker worker = repository.findById(id).get();
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
