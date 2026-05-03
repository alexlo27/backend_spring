package com.alexlo.msvc_employee.organization.controller;

import com.alexlo.msvc_employee.organization.dto.request.CreatePositionRequestDTO;
import com.alexlo.msvc_employee.organization.dto.request.UpdatePositionRequestDTO;
import com.alexlo.msvc_employee.organization.dto.response.PositionResponseDTO;
import com.alexlo.msvc_employee.organization.service.PositionService;
import com.alexlo.msvc_employee.shared.mapper.PageResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/position")
public class PositionController {

    @Autowired
    PositionService positionService;

    @PostMapping
    public ResponseEntity<PositionResponseDTO> create(@Valid @RequestBody CreatePositionRequestDTO dto){
        return ResponseEntity.ok(positionService.create(dto));
    }

    @PatchMapping
    public ResponseEntity<PositionResponseDTO> update(@Valid @RequestBody UpdatePositionRequestDTO dto){
        return ResponseEntity.ok(positionService.update(dto));
    }

    @GetMapping
    public ResponseEntity<PageResponse<PositionResponseDTO>> all(Pageable pageable){
        return ResponseEntity.ok(positionService.all(pageable));
    }

    @GetMapping("/all")
    public ResponseEntity<List<PositionResponseDTO>> all(){
        return ResponseEntity.ok(positionService.all());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PositionResponseDTO> findById(@PathVariable Long id){
        return ResponseEntity.ok(positionService.findById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<PositionResponseDTO> deleteById(@PathVariable Long id){
        positionService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
