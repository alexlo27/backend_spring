package com.alexlo.msvc_employee.employee.controller;

import com.alexlo.msvc_employee.employee.dto.response.EmployeeDetailResponseDTO;
import com.alexlo.msvc_employee.employee.dto.response.PageResponse;
import com.alexlo.msvc_employee.employee.service.EmployeeDetailQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/employee-detail-query")
public class EmployeeDetailQueryController {

    @Autowired
    private EmployeeDetailQueryService employeeDetailQueryService;

    @GetMapping
    public ResponseEntity<PageResponse<EmployeeDetailResponseDTO>> findAll(
            @RequestParam(required = false) Long departmentId,
            @RequestParam(required = false) String documentNumber,
            @RequestParam(required = false) String fullName,
            Pageable pageable) {
        return ResponseEntity.ok(employeeDetailQueryService.findAll(departmentId, documentNumber, fullName, pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDetailResponseDTO> findById(@PathVariable Long id) {
        EmployeeDetailResponseDTO dto = employeeDetailQueryService.findById(id);
        if (dto == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(dto);
    }
}
