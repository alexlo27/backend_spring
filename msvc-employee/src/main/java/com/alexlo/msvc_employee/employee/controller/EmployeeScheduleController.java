package com.alexlo.msvc_employee.employee.controller;

import com.alexlo.msvc_employee.employee.dto.response.EmployeeScheduleDetailDTO;
import com.alexlo.msvc_employee.employee.dto.response.PageResponse;
import com.alexlo.msvc_employee.employee.service.EmployeeDetailQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/employee-schedule")
public class EmployeeScheduleController {

    @Autowired
    private EmployeeDetailQueryService employeeDetailQueryService;

    @GetMapping
    public ResponseEntity<PageResponse<EmployeeScheduleDetailDTO>> findAll(
            @RequestParam(required = false) Long departmentId,
            @RequestParam Long schedulePeriodId,
            @RequestParam(required = false) String documentNumber,
            @RequestParam(required = false) String fullName,
            Pageable pageable) {
        return ResponseEntity.ok(employeeDetailQueryService.findByDepartmentAndPeriod(
                departmentId, schedulePeriodId, documentNumber, fullName, pageable));
    }
}
