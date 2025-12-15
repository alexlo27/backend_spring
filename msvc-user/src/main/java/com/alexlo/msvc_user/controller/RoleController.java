package com.alexlo.msvc_user.controller;

import com.alexlo.msvc_user.dto.request.CreateRoleDTO;
import com.alexlo.msvc_user.dto.response.PageResponse;
import com.alexlo.msvc_user.dto.response.RoleResponseDTO;
import com.alexlo.msvc_user.service.RoleService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
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
    public ResponseEntity<RoleResponseDTO> findById(@PathVariable Long id){
        return ResponseEntity.ok(roleService.findById(id));
    }

    @GetMapping("/{id}/with-permissions")
    public ResponseEntity<RoleResponseDTO> findByIdWithPermissions(@PathVariable Long id){
        return ResponseEntity.ok(roleService.findByIdWithPermissions(id));
    }

    @GetMapping
    public ResponseEntity<List<RoleResponseDTO>> all(){
        System.out.println("controlador");
        return ResponseEntity.ok(roleService.all());
    }

    @GetMapping("/paginate")
    public ResponseEntity<PageResponse<RoleResponseDTO>> all(@RequestParam(required = false) String name, Pageable pageable){
        return ResponseEntity.ok(roleService.allWithPermissions(name, pageable));
    }

    @GetMapping("/with-permissions")
    public ResponseEntity<List<RoleResponseDTO>> allWithPermission(){
        System.out.println("controlador");
        return ResponseEntity.ok(roleService.allWithPermissions());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteRole(@PathVariable Long id){
        roleService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
