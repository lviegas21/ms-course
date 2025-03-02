package com.example.hr_worker.controllers;

import com.example.hr_worker.dto.UserAcessDTO;
import com.example.hr_worker.service.OnlineUserLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserAcessController {
    @Autowired
    private OnlineUserLogService onlineUserLogService;

    @GetMapping("buscar/user/online")
    public List<UserAcessDTO> buscarUserOnline(){
        return onlineUserLogService.buscarUsersOnline();
    }
}
