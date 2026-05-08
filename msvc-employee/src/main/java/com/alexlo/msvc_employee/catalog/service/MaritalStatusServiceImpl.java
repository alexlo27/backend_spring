package com.alexlo.msvc_employee.catalog.service;

import com.alexlo.msvc_employee.catalog.dto.request.CreateMaritalStatusRequestDTO;
import com.alexlo.msvc_employee.catalog.dto.request.UpdateMaritalStatusRequestDTO;
import com.alexlo.msvc_employee.catalog.dto.response.MaritalStatusResponseDTO;
import com.alexlo.msvc_employee.catalog.maper.MaritalStatusMapper;
import com.alexlo.msvc_employee.catalog.model.MaritalStatusEntity;
import com.alexlo.msvc_employee.catalog.repository.MaritalStatusRepository;
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
public class MaritalStatusServiceImpl implements MaritalStatusService{

    @Autowired
    MaritalStatusMapper maritalStatusMapper;

    @Autowired
    MaritalStatusRepository maritalStatusRepository;

    @Transactional
    @Override
    public MaritalStatusResponseDTO create(CreateMaritalStatusRequestDTO dto) {
        if (maritalStatusRepository.existsByCodeIgnoreCase(dto.code())) {
            throw new DuplicateResourceException("El código ya existe", "code");
        }
        return maritalStatusMapper.toResponse(maritalStatusRepository.save(maritalStatusMapper.toEntity(dto)));
    }

    @Transactional
    @Override
    public MaritalStatusResponseDTO update(UpdateMaritalStatusRequestDTO dto) {
        if (maritalStatusRepository.existsByCodeIgnoreCaseAndIdNot(dto.code(), dto.id())) {
            throw new DuplicateResourceException("El código ya existe", "code");
        }
        MaritalStatusEntity maritalStatus= getMaritalStatusById(dto.id());
        maritalStatusMapper.updateEntityFromDto(dto, maritalStatus);
        return maritalStatusMapper.toResponse(maritalStatusRepository.save(maritalStatus));
    }

    @Transactional(readOnly = true)
    @Override
    public List<MaritalStatusResponseDTO> all() {
        return maritalStatusMapper.toResponseList(maritalStatusRepository.findAll());
    }

    @Transactional(readOnly = true)
    @Override
    public PageResponse<MaritalStatusResponseDTO> all(String name, Pageable pageable) {
        Page<MaritalStatusEntity> result = maritalStatusRepository.findByNameContainingIgnoreCase(name, pageable);
        return PageMapper.map(result, maritalStatusMapper::toResponse);
    }

    @Transactional(readOnly = true)
    @Override
    public MaritalStatusResponseDTO findById(Long id) {
        return maritalStatusMapper.toResponse(getMaritalStatusById(id));
    }

    @Transactional
    @Override
    public void delete(Long id) {
        getMaritalStatusById(id);
        maritalStatusRepository.deleteById(id);
    }

    private MaritalStatusEntity getMaritalStatusById(Long id){
        return maritalStatusRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Estado civil no encontrado"));
    }
}