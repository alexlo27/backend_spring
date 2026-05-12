package com.alexlo.msvc_employee.catalog.validator;

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
    private final EmployeeTypeRepository employeeTypeRepository;
    private final ContractTypeRepository contractTypeRepository;

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

    public DocumentTypeEntity getDocumentTypeById(Long id){
        return documentTypeRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Tipo Documento no encontrado"));
    }

    public GenderEntity getGender(String code){
        return genderRepository.findByCodeIgnoreCase(code)
                .orElseThrow(() -> new NotFoundException("Género no encontrado"));
    }

    public GenderEntity getGenderById(Long id){
        return genderRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Género no encontrado"));
    }

    public MaritalStatusEntity getMaritalStatus(String code){
        return maritalStatusRepository.findByCodeIgnoreCase(code)
                .orElseThrow(() -> new NotFoundException("Estado civil no encontrado"));
    }

    public MaritalStatusEntity getMaritalStatusById(Long id){
        return maritalStatusRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Estado civil no encontrado"));
    }

    public EmployeeTypeEntity getEmployeeTypeById(Long id){
        return employeeTypeRepository.findById(id)
                .orElseThrow(()-> new NotFoundException("No existe tipo empleado"));
    }

    public ContractTypeEntity getContractTypeById(Long id){
        return contractTypeRepository.findById(id)
                .orElseThrow(()-> new NotFoundException("No existe tipo contrato"));
    }




}
