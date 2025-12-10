package com.alexlo.msvc_user.controller;

import com.alexlo.msvc_user.dto.request.CreatePermissionDTO;
import com.alexlo.msvc_user.dto.response.PageResponse;
import com.alexlo.msvc_user.dto.response.PermissionResponseDTO;
import com.alexlo.msvc_user.service.PermissionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/permission")
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

    @GetMapping("/paginate")
    public ResponseEntity<PageResponse<PermissionResponseDTO>> allPaginatePermisssion(/*@RequestParam(defaultValue = "1") int page,
                                                                                      @RequestParam(defaultValue = "10") int size*/ Pageable pageable){
        return ResponseEntity.ok(permissionService.all(/*page, size*/ pageable));
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
