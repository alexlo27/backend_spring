package com.alexlo.msvc_user.controller;

import com.alexlo.msvc_user.dto.request.CreateRoleDTO;
import com.alexlo.msvc_user.dto.response.RoleResponseDTO;
import com.alexlo.msvc_user.service.RoleService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    RoleService roleService;

    @PostMapping
    public ResponseEntity<RoleResponseDTO> saveRole(@Valid @RequestBody CreateRoleDTO createRoleDTO){
        return ResponseEntity.ok(roleService.create(createRoleDTO));
    }

    @PatchMapping
    public ResponseEntity<RoleResponseDTO> updateRole(@Valid @RequestBody CreateRoleDTO createRoleDTO){
        return ResponseEntity.ok(roleService.update(createRoleDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<RoleResponseDTO> findByIdRole(@PathVariable Long id){
        return ResponseEntity.ok(roleService.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<RoleResponseDTO>> allRole(){
        return ResponseEntity.ok(roleService.all());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteRole(@PathVariable Long id){
        roleService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
