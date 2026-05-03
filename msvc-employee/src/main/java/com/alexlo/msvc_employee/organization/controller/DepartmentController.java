package com.alexlo.msvc_employee.organization.controller;

import com.alexlo.msvc_employee.organization.dto.request.CreateDepartmentRequestDTO;
import com.alexlo.msvc_employee.organization.dto.request.UpdateDepartmentRequestDTO;
import com.alexlo.msvc_employee.organization.dto.response.DepartmentResponseDTO;
import com.alexlo.msvc_employee.organization.service.DepartmentService;
import com.alexlo.msvc_employee.shared.mapper.PageResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public ResponseEntity<PageResponse<DepartmentResponseDTO>> all(Pageable pageable){
        return ResponseEntity.ok(departmentService.all(pageable));
    }

    @GetMapping("/all")
    public ResponseEntity<List<DepartmentResponseDTO>> all(){
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
