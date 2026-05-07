package com.alexlo.msvc_employee.catalog.controller;

import com.alexlo.msvc_employee.catalog.dto.request.CreateGenderRequestDTO;
import com.alexlo.msvc_employee.catalog.dto.request.UpdateGenderRequestDTO;
import com.alexlo.msvc_employee.catalog.dto.response.GenderResponseDTO;
import com.alexlo.msvc_employee.catalog.service.GenderService;
import com.alexlo.msvc_employee.shared.mapper.PageResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/gender")
public class GenderController {

    @Autowired
    GenderService genderService;

    @PostMapping
    public ResponseEntity<GenderResponseDTO> save(@Valid @RequestBody CreateGenderRequestDTO dto){
        return ResponseEntity.ok(genderService.create(dto));
    }

    @PatchMapping
    public ResponseEntity<GenderResponseDTO> update(@Valid @RequestBody UpdateGenderRequestDTO dto){
        return ResponseEntity.ok(genderService.update(dto));
    }

    @GetMapping("/all")
    public ResponseEntity<List<GenderResponseDTO>> all(){
        return ResponseEntity.ok(genderService.all());
    }

    @GetMapping
    public ResponseEntity<PageResponse<GenderResponseDTO>> all(@RequestParam(required = false) String name, Pageable pageable){
        return ResponseEntity.ok(genderService.all(name, pageable));
    }


    @GetMapping("/{id}")
    public ResponseEntity<GenderResponseDTO> findById(@PathVariable Long id){
        return ResponseEntity.ok(genderService.findById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        genderService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
