package com.alexlo.msvc_user.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class TestRoleController {

    @GetMapping("/acces-admin")
    @PreAuthorize("hasRole('ADMIN')")
    public Map<String, Object> accesAdmin(){
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Acceso permitido");
        response.put("role", "ADMIN");
        return response;
    }

    @GetMapping("/acces-user")
    @PreAuthorize("hasRole('USER')")
    public String accesUser(){
        return "Holas, has accedido con rol de USER";
    }

    @GetMapping("/acces-invited")
    @PreAuthorize("hasRole('INVITED')")
    public String accesInvited(){
        return "Holas, has accedido con rol de INVITED";
    }
}
