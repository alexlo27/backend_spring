package com.alexlo.msvc_employee.catalog.controller;

import com.alexlo.msvc_employee.catalog.dto.request.CreateMaritalStatusRequestDTO;
import com.alexlo.msvc_employee.catalog.dto.request.UpdateMaritalStatusRequestDTO;
import com.alexlo.msvc_employee.catalog.dto.response.MaritalStatusResponseDTO;
import com.alexlo.msvc_employee.catalog.service.MaritalStatusService;
import com.alexlo.msvc_employee.shared.mapper.PageResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/marital-status")
public class MaritalStatusController {

    @Autowired
    MaritalStatusService maritalStatusService;

    @PostMapping
    public ResponseEntity<MaritalStatusResponseDTO> save(@Valid @RequestBody CreateMaritalStatusRequestDTO dto){
        return ResponseEntity.ok(maritalStatusService.create(dto));
    }

    @PatchMapping
    public ResponseEntity<MaritalStatusResponseDTO> update(@Valid @RequestBody UpdateMaritalStatusRequestDTO dto){
        return ResponseEntity.ok(maritalStatusService.update(dto));
    }

    @GetMapping("/all")
    public ResponseEntity<List<MaritalStatusResponseDTO>> all(){
        return ResponseEntity.ok(maritalStatusService.all());
    }

    @GetMapping
    public ResponseEntity<PageResponse<MaritalStatusResponseDTO>> all(@RequestParam(required = false) String name, Pageable pageable){
        return ResponseEntity.ok(maritalStatusService.all(name, pageable));
    }


    @GetMapping("/{id}")
    public ResponseEntity<MaritalStatusResponseDTO> findById(@PathVariable Long id){
        return ResponseEntity.ok(maritalStatusService.findById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        maritalStatusService.delete(id);
        return ResponseEntity.noContent().build();
    }
}