package com.alexlo.msvc_employee.schedule.controller;

import com.alexlo.msvc_employee.schedule.dto.request.CreateSchedulePeriodRequestDTO;
import com.alexlo.msvc_employee.schedule.dto.request.UpdateSchedulePeriodRequestDTO;
import com.alexlo.msvc_employee.schedule.dto.response.SchedulePeriodResponseDTO;
import com.alexlo.msvc_employee.schedule.service.SchedulePeriodService;
import com.alexlo.msvc_employee.shared.mapper.PageResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/schedule-period")
public class SchedulePeriodController {

    @Autowired
    SchedulePeriodService schedulePeriodService;

    @PostMapping
    public ResponseEntity<SchedulePeriodResponseDTO> save(@Valid @RequestBody CreateSchedulePeriodRequestDTO dto) {
        return ResponseEntity.ok(schedulePeriodService.create(dto));
    }

    @PatchMapping
    public ResponseEntity<SchedulePeriodResponseDTO> update(@Valid @RequestBody UpdateSchedulePeriodRequestDTO dto) {
        return ResponseEntity.ok(schedulePeriodService.update(dto));
    }

    @GetMapping("/all")
    public ResponseEntity<List<SchedulePeriodResponseDTO>> all() {
        return ResponseEntity.ok(schedulePeriodService.all());
    }

    @GetMapping
    public ResponseEntity<PageResponse<SchedulePeriodResponseDTO>> all(@RequestParam(required = false) String period, Pageable pageable) {
        return ResponseEntity.ok(schedulePeriodService.all(period, pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<SchedulePeriodResponseDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(schedulePeriodService.findById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        schedulePeriodService.delete(id);
        return ResponseEntity.noContent().build();
    }
}