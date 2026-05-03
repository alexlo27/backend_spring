package com.alexlo.msvc_employee.catalog.service;

import com.alexlo.msvc_employee.catalog.dto.request.CreateDocumentTypeRequestDTO;
import com.alexlo.msvc_employee.catalog.dto.request.UpdateDocumentTypeRequestDTO;
import com.alexlo.msvc_employee.catalog.dto.response.DocumentTypeResponseDTO;
import com.alexlo.msvc_employee.shared.mapper.PageResponse;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface DocumentTypeService {

    DocumentTypeResponseDTO create(CreateDocumentTypeRequestDTO dto);

    DocumentTypeResponseDTO update(UpdateDocumentTypeRequestDTO dto);

    List<DocumentTypeResponseDTO> all();

    PageResponse<DocumentTypeResponseDTO> all(Pageable pageable);

    DocumentTypeResponseDTO findById(Long id);

    void delete(Long id);

}
