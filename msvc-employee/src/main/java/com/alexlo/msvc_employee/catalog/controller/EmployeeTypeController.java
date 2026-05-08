package com.alexlo.msvc_employee.catalog.controller;

import com.alexlo.msvc_employee.catalog.dto.request.CreateEmployeeTypeRequestDTO;
import com.alexlo.msvc_employee.catalog.dto.request.UpdateEmployeeTypeRequestDTO;
import com.alexlo.msvc_employee.catalog.dto.response.EmployeeTypeResponseDTO;
import com.alexlo.msvc_employee.catalog.service.EmployeeTypeService;
import com.alexlo.msvc_employee.shared.mapper.PageResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee-type")
public class EmployeeTypeController {

    @Autowired
    EmployeeTypeService employeeTypeService;

    @PostMapping
    public ResponseEntity<EmployeeTypeResponseDTO> save(@Valid @RequestBody CreateEmployeeTypeRequestDTO dto){
        return ResponseEntity.ok(employeeTypeService.create(dto));
    }

    @PatchMapping
    public ResponseEntity<EmployeeTypeResponseDTO> update(@Valid @RequestBody UpdateEmployeeTypeRequestDTO dto){
        return ResponseEntity.ok(employeeTypeService.update(dto));
    }

    @GetMapping("/all")
    public ResponseEntity<List<EmployeeTypeResponseDTO>> all(){
        return ResponseEntity.ok(employeeTypeService.all());
    }

    @GetMapping
    public ResponseEntity<PageResponse<EmployeeTypeResponseDTO>> all(@RequestParam(required = false) String name, Pageable pageable){
        return ResponseEntity.ok(employeeTypeService.all(name, pageable));
    }


    @GetMapping("/{id}")
    public ResponseEntity<EmployeeTypeResponseDTO> findById(@PathVariable Long id){
        return ResponseEntity.ok(employeeTypeService.findById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        employeeTypeService.delete(id);
        return ResponseEntity.noContent().build();
    }
}