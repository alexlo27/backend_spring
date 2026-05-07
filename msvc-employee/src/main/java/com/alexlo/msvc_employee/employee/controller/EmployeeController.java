package com.alexlo.msvc_employee.employee.controller;

import com.alexlo.msvc_employee.employee.dto.request.CreateEmployeeDTO;
import com.alexlo.msvc_employee.employee.dto.request.UpdateEmployeeDTO;
import com.alexlo.msvc_employee.employee.dto.response.EmployeeResponseDTO;
import com.alexlo.msvc_employee.employee.service.EmployeeService;
import com.alexlo.msvc_employee.shared.mapper.PageResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @PostMapping
    public ResponseEntity<EmployeeResponseDTO> createEmployee(@Valid @RequestBody CreateEmployeeDTO createEmployeeDTO){
        return ResponseEntity.ok(employeeService.create(createEmployeeDTO));
    }

    @PatchMapping
    public ResponseEntity<EmployeeResponseDTO> updateEmployee(@Valid @RequestBody UpdateEmployeeDTO updateEmployeeDTO){
        return ResponseEntity.ok(employeeService.update(updateEmployeeDTO));
    }

    @GetMapping
    public ResponseEntity<PageResponse<EmployeeResponseDTO>> all(@RequestParam(required = false) String name, Pageable pageable){
        return ResponseEntity.ok(employeeService.all(name, pageable));
    }

    @GetMapping("/all")
    public ResponseEntity<List<EmployeeResponseDTO>> all(){
        return ResponseEntity.ok(employeeService.all());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeResponseDTO> findById(@PathVariable Long id){
        return ResponseEntity.ok(employeeService.findById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
       employeeService.delete(id);
       return ResponseEntity.noContent().build();
    }
}
