package com.alexlo.msvc_employee.catalog.controller;

import com.alexlo.msvc_employee.catalog.dto.request.CreateContractTypeRequestDTO;
import com.alexlo.msvc_employee.catalog.dto.request.UpdateContractTypeRequestDTO;
import com.alexlo.msvc_employee.catalog.dto.response.ContractTypeResponseDTO;
import com.alexlo.msvc_employee.catalog.service.ContractTypeService;
import com.alexlo.msvc_employee.shared.mapper.PageResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/contract-type")
public class ContractTypeController {

    @Autowired
    ContractTypeService contractTypeService;

    @PostMapping
    public ResponseEntity<ContractTypeResponseDTO> save(@Valid @RequestBody CreateContractTypeRequestDTO dto){
        return ResponseEntity.ok(contractTypeService.create(dto));
    }

    @PatchMapping
    public ResponseEntity<ContractTypeResponseDTO> update(@Valid @RequestBody UpdateContractTypeRequestDTO dto){
        return ResponseEntity.ok(contractTypeService.update(dto));
    }

    @GetMapping("/all")
    public ResponseEntity<List<ContractTypeResponseDTO>> all(){
        return ResponseEntity.ok(contractTypeService.all());
    }

    @GetMapping
    public ResponseEntity<PageResponse<ContractTypeResponseDTO>> all(@RequestParam(required = false) String name, Pageable pageable){
        return ResponseEntity.ok(contractTypeService.all(name, pageable));
    }


    @GetMapping("/{id}")
    public ResponseEntity<ContractTypeResponseDTO> findById(@PathVariable Long id){
        return ResponseEntity.ok(contractTypeService.findById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        contractTypeService.delete(id);
        return ResponseEntity.noContent().build();
    }
}