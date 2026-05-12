package com.alexlo.msvc_employee.catalog.service;

import com.alexlo.msvc_employee.catalog.dto.request.CreateGenderRequestDTO;
import com.alexlo.msvc_employee.catalog.dto.request.UpdateGenderRequestDTO;
import com.alexlo.msvc_employee.catalog.dto.response.GenderResponseDTO;
import com.alexlo.msvc_employee.catalog.maper.GenderMapper;
import com.alexlo.msvc_employee.catalog.model.GenderEntity;
import com.alexlo.msvc_employee.catalog.repository.GenderRepository;
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
public class GenderServiceImpl implements GenderService{

    @Autowired
    GenderMapper genderMapper;

    @Autowired
    GenderRepository genderRepository;

    @Autowired
    CatalogLookupService catalogLookupService;

    @Transactional
    @Override
    public GenderResponseDTO create(CreateGenderRequestDTO dto) {
        if (genderRepository.existsByCodeIgnoreCase(dto.code())) {
            throw new DuplicateResourceException("El código ya existe", "code");
        }
        return genderMapper.toResponse(genderRepository.save(genderMapper.toEntity(dto)));
    }

    @Transactional
    @Override
    public GenderResponseDTO update(UpdateGenderRequestDTO dto) {
        if (genderRepository.existsByCodeIgnoreCaseAndIdNot(dto.code(), dto.id())) {
            throw new DuplicateResourceException("El código ya existe", "code");
        }
        GenderEntity gender= catalogLookupService.getGenderById(dto.id());
        genderMapper.updateEntityFromDto(dto, gender);
        return genderMapper.toResponse(genderRepository.save(gender));
    }

    @Transactional(readOnly = true)
    @Override
    public List<GenderResponseDTO> all() {
        return genderMapper.toResponseList(genderRepository.findAll());
    }

    @Transactional(readOnly = true)
    @Override
    public PageResponse<GenderResponseDTO> all(String name, Pageable pageable) {
        Page<GenderEntity> result = genderRepository.findByNameContainingIgnoreCase(name, pageable);
        return PageMapper.map(result, genderMapper::toResponse);
    }

    @Transactional(readOnly = true)
    @Override
    public GenderResponseDTO findById(Long id) {
        return genderMapper.toResponse(catalogLookupService.getGenderById(id));
    }

    @Transactional
    @Override
    public void delete(Long id) {
        catalogLookupService.getGenderById(id);
        genderRepository.deleteById(id);
    }

}
