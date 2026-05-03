package com.alexlo.msvc_employee.organization.service;

import com.alexlo.msvc_employee.organization.dto.request.CreatePositionRequestDTO;
import com.alexlo.msvc_employee.organization.dto.request.UpdatePositionRequestDTO;
import com.alexlo.msvc_employee.organization.dto.response.PositionResponseDTO;
import com.alexlo.msvc_employee.organization.maper.PositionMapper;
import com.alexlo.msvc_employee.organization.model.PositionEntity;
import com.alexlo.msvc_employee.organization.repository.PositionRepository;
import com.alexlo.msvc_employee.organization.validator.PositionBusinessRules;
import com.alexlo.msvc_employee.shared.exception.NotFoundException;
import com.alexlo.msvc_employee.shared.mapper.PageMapper;
import com.alexlo.msvc_employee.shared.mapper.PageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PositionServiceImpl implements PositionService {


    private final PositionRepository positionRepository;
    private final PositionMapper positionMapper;
    private final PositionBusinessRules rules;

    @Transactional
    @Override
    public PositionResponseDTO create(CreatePositionRequestDTO dto) {
        rules.validateCreate(dto);
        return positionMapper.toResponse(positionRepository.save(positionMapper.toEntity(dto)));
    }

    @Transactional
    @Override
    public PositionResponseDTO update(UpdatePositionRequestDTO dto) {
        rules.validateUpdate(dto);
        PositionEntity position = getPositionById(dto.id());
        positionMapper.updateEntityFromDto(dto, position);
        return positionMapper.toResponse(position);
    }

    @Transactional(readOnly = true)
    @Override
    public List<PositionResponseDTO> all() {
        return positionMapper.toResponseList(positionRepository.findAll());
    }

    @Transactional(readOnly = true)
    @Override
    public PositionResponseDTO findById(Long id) {
        return positionMapper.toResponse(getPositionById(id));
    }

    @Transactional(readOnly = true)
    @Override
    public PageResponse<PositionResponseDTO> all(Pageable pageable) {
        Page<PositionEntity> result = positionRepository.findAll(pageable);
        return PageMapper.map(result, positionMapper::toResponse);
    }

    @Transactional
    @Override
    public void delete(Long id) {
        getPositionById(id);
        positionRepository.deleteById(id);
    }

    private PositionEntity getPositionById(Long id){
        return positionRepository.findById(id).orElseThrow(()-> new NotFoundException("No existe cargo"));
    }

}
