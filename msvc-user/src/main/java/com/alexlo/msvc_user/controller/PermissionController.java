package com.alexlo.msvc_user.controller;

import com.alexlo.msvc_user.dto.request.CreatePermissionDTO;
import com.alexlo.msvc_user.dto.response.PermissionResponseDTO;
import com.alexlo.msvc_user.service.PermissionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/permissions")
public class PermissionController {

    @Autowired
    PermissionService permissionService;

    @PostMapping
    public ResponseEntity<PermissionResponseDTO> savePermission(@Valid @RequestBody CreatePermissionDTO createPermissionDTO){
        return ResponseEntity.ok(permissionService.create(createPermissionDTO));
    }

    @PatchMapping
    public ResponseEntity<PermissionResponseDTO> updatePermission(@Valid @RequestBody CreatePermissionDTO createPermissionDTO){
        return ResponseEntity.ok(permissionService.update(createPermissionDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PermissionResponseDTO> findByIdPermission(@PathVariable Long id){
        return ResponseEntity.ok(permissionService.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<PermissionResponseDTO>> allPermisssion(){
        return ResponseEntity.ok(permissionService.all());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePermisssion(@PathVariable Long id){
        permissionService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
