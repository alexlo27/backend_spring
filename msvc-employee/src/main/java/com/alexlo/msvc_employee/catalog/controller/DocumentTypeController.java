package com.alexlo.msvc_employee.catalog.controller;

import com.alexlo.msvc_employee.catalog.dto.request.CreateDocumentTypeRequestDTO;
import com.alexlo.msvc_employee.catalog.dto.request.UpdateDocumentTypeRequestDTO;
import com.alexlo.msvc_employee.catalog.dto.response.DocumentTypeResponseDTO;
import com.alexlo.msvc_employee.catalog.service.DocumentTypeService;
import com.alexlo.msvc_employee.shared.mapper.PageResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/documentType")
public class DocumentTypeController {

    @Autowired
    DocumentTypeService documentTypeService;

    @PostMapping
    public ResponseEntity<DocumentTypeResponseDTO> save(@Valid @RequestBody CreateDocumentTypeRequestDTO dto){
        return ResponseEntity.ok(documentTypeService.create(dto));
    }

    @PatchMapping
    public ResponseEntity<DocumentTypeResponseDTO> update(@Valid @RequestBody UpdateDocumentTypeRequestDTO dto){
        return ResponseEntity.ok(documentTypeService.update(dto));
    }

    @GetMapping("/all")
    public ResponseEntity<List<DocumentTypeResponseDTO>> all(){
        return ResponseEntity.ok(documentTypeService.all());
    }

    @GetMapping
    public ResponseEntity<PageResponse<DocumentTypeResponseDTO>> all(Pageable pageable){
        return ResponseEntity.ok(documentTypeService.all(pageable));
    }


    @GetMapping("/{id}")
    public ResponseEntity<DocumentTypeResponseDTO> findById(@PathVariable Long id){
        return ResponseEntity.ok(documentTypeService.findById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        documentTypeService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
