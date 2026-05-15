package com.alexlo.msvc_employee.employment.service;

import com.alexlo.msvc_employee.employment.dto.request.CreateContractTypeRequestDTO;
import com.alexlo.msvc_employee.employment.dto.request.UpdateContractTypeRequestDTO;
import com.alexlo.msvc_employee.employment.dto.response.ContractTypeResponseDTO;
import com.alexlo.msvc_employee.employment.mapper.ContractTypeMapper;
import com.alexlo.msvc_employee.employment.model.ContractTypeEntity;
import com.alexlo.msvc_employee.employment.repository.ContractTypeRepository;
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
public class ContractTypeServiceImpl implements ContractTypeService{

    @Autowired
    ContractTypeMapper contractTypeMapper;

    @Autowired
    ContractTypeRepository contractTypeRepository;

    @Transactional
    @Override
    public ContractTypeResponseDTO create(CreateContractTypeRequestDTO dto) {
        if (contractTypeRepository.existsByCodeIgnoreCase(dto.code())) {
            throw new DuplicateResourceException("El código ya existe", "code");
        }
        return contractTypeMapper.toResponse(contractTypeRepository.save(contractTypeMapper.toEntity(dto)));
    }

    @Transactional
    @Override
    public ContractTypeResponseDTO update(UpdateContractTypeRequestDTO dto) {
        if (contractTypeRepository.existsByCodeIgnoreCaseAndIdNot(dto.code(), dto.id())) {
            throw new DuplicateResourceException("El código ya existe", "code");
        }
        ContractTypeEntity contractType= getContractTypeById(dto.id());
        contractTypeMapper.updateEntityFromDto(dto, contractType);
        return contractTypeMapper.toResponse(contractTypeRepository.save(contractType));
    }

    @Transactional(readOnly = true)
    @Override
    public List<ContractTypeResponseDTO> all() {
        return contractTypeMapper.toResponseList(contractTypeRepository.findAll());
    }

    @Transactional(readOnly = true)
    @Override
    public PageResponse<ContractTypeResponseDTO> all(String name, Pageable pageable) {
        Page<ContractTypeEntity> result = contractTypeRepository.findByNameContainingIgnoreCase(name, pageable);
        return PageMapper.map(result, contractTypeMapper::toResponse);
    }

    @Transactional(readOnly = true)
    @Override
    public ContractTypeResponseDTO findById(Long id) {
        return contractTypeMapper.toResponse(getContractTypeById(id));
    }

    @Transactional
    @Override
    public void delete(Long id) {
        getContractTypeById(id);
        contractTypeRepository.deleteById(id);
    }

    private ContractTypeEntity getContractTypeById(Long id){
        return contractTypeRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Tipo de contrato no encontrado"));
    }

}