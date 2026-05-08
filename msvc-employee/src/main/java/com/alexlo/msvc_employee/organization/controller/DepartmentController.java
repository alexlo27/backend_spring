package com.alexlo.msvc_employee.organization.controller;

import com.alexlo.msvc_employee.organization.dto.request.CreateDepartmentRequestDTO;
import com.alexlo.msvc_employee.organization.dto.request.UpdateDepartmentRequestDTO;
import com.alexlo.msvc_employee.organization.dto.response.DepartmentResponseDTO;
import com.alexlo.msvc_employee.organization.service.DepartmentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/department")
public class DepartmentController {

    @Autowired
    DepartmentService departmentService;

    @PostMapping
    public ResponseEntity<DepartmentResponseDTO> create(@Valid @RequestBody CreateDepartmentRequestDTO dto){
        return ResponseEntity.ok(departmentService.create(dto));
    }

    @PatchMapping
    public ResponseEntity<DepartmentResponseDTO> update(@Valid @RequestBody UpdateDepartmentRequestDTO dto){
        return ResponseEntity.ok(departmentService.update(dto));
    }

    @GetMapping
    public ResponseEntity<?> all(Pageable pageable,
                                 @RequestParam(defaultValue = "false") boolean includeParent) {
        if (includeParent) {
            return ResponseEntity.ok(departmentService.allWithParent(pageable));
        }
        return ResponseEntity.ok(departmentService.all(pageable));
    }

    @GetMapping("/all")
    public ResponseEntity<?> all(@RequestParam(defaultValue = "false") boolean includeParent) {
        if (includeParent) {
            return ResponseEntity.ok(departmentService.allWithParent());
        }
        return ResponseEntity.ok(departmentService.all());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DepartmentResponseDTO> findById(@PathVariable Long id){
        return ResponseEntity.ok(departmentService.findById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DepartmentResponseDTO> deleteById(@PathVariable Long id){
        departmentService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
