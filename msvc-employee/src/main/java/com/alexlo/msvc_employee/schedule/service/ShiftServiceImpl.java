package com.alexlo.msvc_employee.schedule.service;

import com.alexlo.msvc_employee.schedule.dto.request.CreateShiftRequestDTO;
import com.alexlo.msvc_employee.schedule.dto.request.UpdateShiftRequestDTO;
import com.alexlo.msvc_employee.schedule.dto.response.ShiftResponseDTO;
import com.alexlo.msvc_employee.schedule.maper.ShiftMapper;
import com.alexlo.msvc_employee.schedule.model.ShiftEntity;
import com.alexlo.msvc_employee.schedule.repository.ShiftRepository;
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
public class ShiftServiceImpl implements ShiftService {

    @Autowired
    ShiftMapper shiftMapper;

    @Autowired
    ShiftRepository shiftRepository;

    @Transactional
    @Override
    public ShiftResponseDTO create(CreateShiftRequestDTO dto) {
        if (shiftRepository.existsByCodeIgnoreCase(dto.code())) {
            throw new DuplicateResourceException("El código ya existe", "code");
        }
        return shiftMapper.toResponse(shiftRepository.save(shiftMapper.toEntity(dto)));
    }

    @Transactional
    @Override
    public ShiftResponseDTO update(UpdateShiftRequestDTO dto) {
        if (shiftRepository.existsByCodeIgnoreCaseAndIdNot(dto.code(), dto.id())) {
            throw new DuplicateResourceException("El código ya existe", "code");
        }
        ShiftEntity shift = getShiftById(dto.id());
        System.out.println("dto:::"+dto);
        shiftMapper.updateEntityFromDto(dto, shift);
        System.out.println("entidad:::"+shift);
        return shiftMapper.toResponse(shiftRepository.save(shift));
    }

    @Transactional(readOnly = true)
    @Override
    public List<ShiftResponseDTO> all() {
        return shiftMapper.toResponseList(shiftRepository.findAll());
    }

    @Transactional(readOnly = true)
    @Override
    public PageResponse<ShiftResponseDTO> all(String name, Pageable pageable) {
        Page<ShiftEntity> result = shiftRepository.findByNameContainingIgnoreCase(name, pageable);
        return PageMapper.map(result, shiftMapper::toResponse);
    }

    @Transactional(readOnly = true)
    @Override
    public ShiftResponseDTO findById(Long id) {
        return shiftMapper.toResponse(getShiftById(id));
    }

    @Transactional
    @Override
    public void delete(Long id) {
        getShiftById(id);
        shiftRepository.deleteById(id);
    }

    private ShiftEntity getShiftById(Long id) {
        return shiftRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Turno no encontrado"));
    }
}