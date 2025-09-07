package com.alexlo.msvc_user.controller;

import com.alexlo.msvc_user.dto.request.CreateUserDTO;
import com.alexlo.msvc_user.dto.response.UserResponseDTO;
import com.alexlo.msvc_user.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.stream.Collectors;

@RestController
public class MainController {

    @Autowired
    private UserService userService;

    @GetMapping("/hello")
    public String hello(){
        return "Helo world not Secured";
    }

    @GetMapping("/helloSecured")
    public String helloSecured(){
        return "Helo world Secured";
    }

    @PostMapping("/createUser")
    public ResponseEntity<?> createUser(@Valid @RequestBody CreateUserDTO createUserDTO){
        return ResponseEntity.ok(userService.create(createUserDTO));
    }

    @DeleteMapping("/deleteUser")
    public String deleteUser(@RequestParam String id){
        userService.delete(Long.parseLong(id));
        return "Se ha borrado el usuario con id : ".concat(id);
    }
}
