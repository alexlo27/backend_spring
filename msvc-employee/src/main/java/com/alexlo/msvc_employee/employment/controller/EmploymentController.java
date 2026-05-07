package com.alexlo.msvc_employee.employment.controller;

import com.alexlo.msvc_employee.employment.dto.request.CreateEmploymentRequestDTO;
import com.alexlo.msvc_employee.employment.dto.request.UpdateEmploymentRequestDTO;
import com.alexlo.msvc_employee.employment.dto.response.EmploymentResponseDTO;
import com.alexlo.msvc_employee.employment.service.EmploymentService;
import com.alexlo.msvc_employee.shared.mapper.PageResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/employment")
@RequiredArgsConstructor
@RestController
public class EmploymentController {

    private final EmploymentService employmentService;

    @PostMapping
    public ResponseEntity<EmploymentResponseDTO> create(@RequestBody @Valid CreateEmploymentRequestDTO dto){
        return ResponseEntity.ok(employmentService.create(dto));
    }

    @PatchMapping
    public ResponseEntity<EmploymentResponseDTO> update(@RequestBody @Valid UpdateEmploymentRequestDTO dto){
        return ResponseEntity.ok(employmentService.update(dto));
    }

    @GetMapping
    public ResponseEntity<PageResponse<EmploymentResponseDTO>> all(Pageable pageable){
        return ResponseEntity.ok(employmentService.all(pageable));
    }

    @GetMapping("/all")
    public ResponseEntity<List<EmploymentResponseDTO>> all(){
        return ResponseEntity.ok(employmentService.all());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmploymentResponseDTO> byId(@PathVariable Long id){
        return ResponseEntity.ok(employmentService.findById(id));
    }

    @GetMapping("/employee/{id}/all")
    public ResponseEntity<List<EmploymentResponseDTO>> employeeById(@PathVariable Long id){
        return ResponseEntity.ok(employmentService.findByEmployeeId(id));
    }

    @GetMapping("/employee/{id}")
    public ResponseEntity<PageResponse<EmploymentResponseDTO>> employeeById(@PathVariable Long id, Pageable pageable){
        return ResponseEntity.ok(employmentService.findByEmployeeId(id, pageable));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        employmentService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
