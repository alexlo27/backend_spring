package com.alexlo.msvc_user.controller;

import com.alexlo.msvc_user.dto.request.CreateUserDTO;
import com.alexlo.msvc_user.dto.response.PageResponse;
import com.alexlo.msvc_user.dto.response.UserResponseDTO;
import com.alexlo.msvc_user.model.UserEntity;
import com.alexlo.msvc_user.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<UserResponseDTO> saveUser(@Valid @RequestBody CreateUserDTO createUserDTO){
        return ResponseEntity.ok(userService.create(createUserDTO));
    }

    @PatchMapping
    public ResponseEntity<UserResponseDTO> updateUser(@Valid @RequestBody CreateUserDTO createUserDTO){
        return ResponseEntity.ok(userService.update(createUserDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> findByIdUser(@PathVariable Long id){
        return ResponseEntity.ok(userService.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> allUser(){
        return ResponseEntity.ok(userService.all());
    }

    @GetMapping("/withRoles")
    public ResponseEntity<List<UserResponseDTO>> allUserWithRoles(){
        return ResponseEntity.ok(userService.allWithRoles());
    }

    @GetMapping("/paginate")
    public ResponseEntity<PageResponse<UserResponseDTO>> allUser(Pageable pageable){
        return ResponseEntity.ok(userService.allWithRoles(pageable));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id){
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
