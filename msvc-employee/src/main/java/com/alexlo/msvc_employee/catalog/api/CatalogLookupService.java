package com.alexlo.msvc_employee.catalog.api;

import com.alexlo.msvc_employee.catalog.model.*;
import com.alexlo.msvc_employee.catalog.repository.*;
import com.alexlo.msvc_employee.shared.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class CatalogLookupService {

    private final GenderRepository genderRepository;
    private final DocumentTypeRepository documentTypeRepository;
    private final MaritalStatusRepository maritalStatusRepository;

    public DocumentTypeEntity getDocumentType(String code){
        return documentTypeRepository.findByCodeIgnoreCase(code)
                .orElseThrow(() -> new NotFoundException("Tipo Documento no encontrado"));
    }

    public GenderEntity getGender(String code){
        return genderRepository.findByCodeIgnoreCase(code)
                .orElseThrow(() -> new NotFoundException("Género no encontrado"));
    }

    public MaritalStatusEntity getMaritalStatus(String code){
        return maritalStatusRepository.findByCodeIgnoreCase(code)
                .orElseThrow(() -> new NotFoundException("Estado civil no encontrado"));
    }


}