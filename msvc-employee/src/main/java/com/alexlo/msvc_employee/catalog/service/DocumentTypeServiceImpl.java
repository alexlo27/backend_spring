package com.alexlo.msvc_employee.catalog.service;


import com.alexlo.msvc_employee.catalog.dto.request.CreateDocumentTypeRequestDTO;
import com.alexlo.msvc_employee.catalog.dto.request.UpdateDocumentTypeRequestDTO;
import com.alexlo.msvc_employee.catalog.dto.response.DocumentTypeResponseDTO;
import com.alexlo.msvc_employee.catalog.maper.DocumentTypeMapper;
import com.alexlo.msvc_employee.catalog.model.DocumentTypeEntity;
import com.alexlo.msvc_employee.catalog.repository.DocumentTypeRepository;
import com.alexlo.msvc_employee.catalog.validator.CatalogLookupService;
import com.alexlo.msvc_employee.shared.exception.DuplicateResourceException;
import com.alexlo.msvc_employee.shared.exception.NotFoundException;
import com.alexlo.msvc_employee.shared.mapper.PageMapper;
import com.alexlo.msvc_employee.shared.mapper.PageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DocumentTypeServiceImpl implements DocumentTypeService {

    @Autowired
    DocumentTypeRepository documentTypeRepository;

    @Autowired
    DocumentTypeMapper documentTypeMapper;

    @Autowired
    CatalogLookupService catalogLookupService;

    @Transactional
    @Override
    public DocumentTypeResponseDTO create(CreateDocumentTypeRequestDTO dto) {
        if (documentTypeRepository.existsByCodeIgnoreCase(dto.code())) {
            throw new DuplicateResourceException("El código ya existe", "code");
        }
        return documentTypeMapper.toResponse(documentTypeRepository.save(documentTypeMapper.toEntity(dto)));
    }

    @Transactional
    @Override
    public DocumentTypeResponseDTO update(UpdateDocumentTypeRequestDTO dto) {
        DocumentTypeEntity documentType = catalogLookupService.getDocumentTypeById(dto.id());
        documentTypeMapper.updateEntityFromDto(dto, documentType);
        return documentTypeMapper.toResponse(documentTypeRepository.save(documentType));
    }

    @Transactional(readOnly = true)
    @Override
    public List<DocumentTypeResponseDTO> all() {
        return documentTypeMapper.toResponseList(documentTypeRepository.findAll());
    }

    @Transactional(readOnly = true)
    @Override
    public PageResponse<DocumentTypeResponseDTO> all(Pageable pageable) {
        Page<DocumentTypeEntity> result = documentTypeRepository.findAll(pageable);
        return PageMapper.map(result, documentTypeMapper::toResponse);
    }

    @Transactional(readOnly = true)
    @Override
    public DocumentTypeResponseDTO findById(Long id) {
        return documentTypeMapper.toResponse(catalogLookupService.getDocumentTypeById(id));
    }

    @Transactional
    @Override
    public void delete(Long id) {
        catalogLookupService.getDocumentTypeById(id);
        documentTypeRepository.deleteById(id);
    }

}
