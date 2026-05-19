package com.alexlo.msvc_employee.schedule.controller;

import com.alexlo.msvc_employee.schedule.dto.request.CreateWorkScheduleRequestDTO;
import com.alexlo.msvc_employee.schedule.dto.request.UpdateWorkScheduleRequestDTO;
import com.alexlo.msvc_employee.schedule.dto.response.WorkScheduleResponseDTO;
import com.alexlo.msvc_employee.schedule.service.WorkScheduleService;
import com.alexlo.msvc_employee.shared.mapper.PageResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/work-schedule")
public class WorkScheduleController {

    @Autowired
    WorkScheduleService workScheduleService;

    @PostMapping
    public ResponseEntity<WorkScheduleResponseDTO> save(@Valid @RequestBody CreateWorkScheduleRequestDTO dto) {
        return ResponseEntity.ok(workScheduleService.create(dto));
    }

    @PatchMapping
    public ResponseEntity<WorkScheduleResponseDTO> update(@Valid @RequestBody UpdateWorkScheduleRequestDTO dto) {
        return ResponseEntity.ok(workScheduleService.update(dto));
    }

    @GetMapping("/all")
    public ResponseEntity<List<WorkScheduleResponseDTO>> all() {
        return ResponseEntity.ok(workScheduleService.all());
    }

    @GetMapping
    public ResponseEntity<PageResponse<WorkScheduleResponseDTO>> all(@RequestParam(required = false) Long employeeId, Pageable pageable) {
        return ResponseEntity.ok(workScheduleService.all(employeeId, pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<WorkScheduleResponseDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(workScheduleService.findById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        workScheduleService.delete(id);
        return ResponseEntity.noContent().build();
    }
}