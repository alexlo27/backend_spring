package com.alexlo.msvc_employee.catalog.validator;

import com.alexlo.msvc_employee.catalog.model.DocumentTypeEntity;
import com.alexlo.msvc_employee.catalog.model.GenderEntity;
import com.alexlo.msvc_employee.catalog.repository.DocumentTypeRepository;
import com.alexlo.msvc_employee.catalog.repository.GenderRepository;
import com.alexlo.msvc_employee.shared.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class CatalogLookupService {

    private final GenderRepository genderRepository;
    private final DocumentTypeRepository documentTypeRepository;

    public void existGender(String code){
        if(!genderRepository.existsByCodeIgnoreCase(code)){
            throw new NotFoundException("Género no encontrado");
        }
    }
    public void existGender(String code, Long id){
        if(!genderRepository.existsByCodeIgnoreCaseAndIdNot(code, id)){
            throw new NotFoundException("Género no encontrado");
        }
    }

    public void existDocumentType(String code){
        if(!documentTypeRepository.existsByCodeIgnoreCase(code)){
            throw new NotFoundException("Tipo documento no encontrado");
        }
    }

    public void existDocumentType(String code, Long id){
        if(!documentTypeRepository.existsByCodeIgnoreCaseAndIdNot(code, id)){
            throw new NotFoundException("Tipo documento no encontrado");
        }
    }

    public DocumentTypeEntity getDocumentType(String code){
        return documentTypeRepository.findByCodeIgnoreCase(code)
                .orElseThrow(() -> new NotFoundException("Tipo Documento no encontrado"));
    }

    public GenderEntity getGender(String code){
        return genderRepository.findByCodeIgnoreCase(code)
                .orElseThrow(() -> new NotFoundException("Género no encontrado"));
    }

}
