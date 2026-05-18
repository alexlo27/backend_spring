package com.alexlo.msvc_employee.schedule.service;

import com.alexlo.msvc_employee.schedule.dto.request.CreateSchedulePeriodRequestDTO;
import com.alexlo.msvc_employee.schedule.dto.request.UpdateSchedulePeriodRequestDTO;
import com.alexlo.msvc_employee.schedule.dto.response.SchedulePeriodResponseDTO;
import com.alexlo.msvc_employee.schedule.maper.SchedulePeriodMapper;
import com.alexlo.msvc_employee.schedule.model.SchedulePeriodEntity;
import com.alexlo.msvc_employee.schedule.repository.SchedulePeriodRepository;
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
public class SchedulePeriodServiceImpl implements SchedulePeriodService {

    @Autowired
    SchedulePeriodMapper schedulePeriodMapper;

    @Autowired
    SchedulePeriodRepository schedulePeriodRepository;

    @Transactional
    @Override
    public SchedulePeriodResponseDTO create(CreateSchedulePeriodRequestDTO dto) {
        if (schedulePeriodRepository.existsByPeriodIgnoreCase(dto.period())) {
            throw new DuplicateResourceException("El período ya existe", "period");
        }
        return schedulePeriodMapper.toResponse(schedulePeriodRepository.save(schedulePeriodMapper.toEntity(dto)));
    }

    @Transactional
    @Override
    public SchedulePeriodResponseDTO update(UpdateSchedulePeriodRequestDTO dto) {
        if (schedulePeriodRepository.existsByPeriodIgnoreCaseAndIdNot(dto.period(), dto.id())) {
            throw new DuplicateResourceException("El período ya existe", "period");
        }
        SchedulePeriodEntity schedulePeriod = getSchedulePeriodById(dto.id());
        schedulePeriodMapper.updateEntityFromDto(dto, schedulePeriod);
        return schedulePeriodMapper.toResponse(schedulePeriodRepository.save(schedulePeriod));
    }

    @Transactional(readOnly = true)
    @Override
    public List<SchedulePeriodResponseDTO> all() {
        return schedulePeriodMapper.toResponseList(schedulePeriodRepository.findAll());
    }

    @Transactional(readOnly = true)
    @Override
    public PageResponse<SchedulePeriodResponseDTO> all(String period, Pageable pageable) {
        Page<SchedulePeriodEntity> result = schedulePeriodRepository.findByPeriodContainingIgnoreCase(period, pageable);
        return PageMapper.map(result, schedulePeriodMapper::toResponse);
    }

    @Transactional(readOnly = true)
    @Override
    public SchedulePeriodResponseDTO findById(Long id) {
        return schedulePeriodMapper.toResponse(getSchedulePeriodById(id));
    }

    @Transactional
    @Override
    public void delete(Long id) {
        getSchedulePeriodById(id);
        schedulePeriodRepository.deleteById(id);
    }

    private SchedulePeriodEntity getSchedulePeriodById(Long id) {
        return schedulePeriodRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Período de horario no encontrado"));
    }
}