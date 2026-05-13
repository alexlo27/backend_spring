package com.alexlo.msvc_employee.schedule.controller;

import com.alexlo.msvc_employee.schedule.dto.request.CreateShiftRequestDTO;
import com.alexlo.msvc_employee.schedule.dto.request.UpdateShiftRequestDTO;
import com.alexlo.msvc_employee.schedule.dto.response.ShiftResponseDTO;
import com.alexlo.msvc_employee.schedule.service.ShiftService;
import com.alexlo.msvc_employee.shared.mapper.PageResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/shift")
public class ShiftController {

    @Autowired
    ShiftService shiftService;

    @PostMapping
    public ResponseEntity<ShiftResponseDTO> save(@Valid @RequestBody CreateShiftRequestDTO dto) {
        return ResponseEntity.ok(shiftService.create(dto));
    }

    @PatchMapping
    public ResponseEntity<ShiftResponseDTO> update(@Valid @RequestBody UpdateShiftRequestDTO dto) {
        return ResponseEntity.ok(shiftService.update(dto));
    }

    @GetMapping("/all")
    public ResponseEntity<List<ShiftResponseDTO>> all() {
        return ResponseEntity.ok(shiftService.all());
    }

    @GetMapping
    public ResponseEntity<PageResponse<ShiftResponseDTO>> all(@RequestParam(required = false) String name, Pageable pageable) {
        return ResponseEntity.ok(shiftService.all(name, pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ShiftResponseDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(shiftService.findById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        shiftService.delete(id);
        return ResponseEntity.noContent().build();
    }
}