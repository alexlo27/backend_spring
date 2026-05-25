package com.alexlo.msvc_employee.schedule.service;

import com.alexlo.msvc_employee.employee.model.EmployeeEntity;
import com.alexlo.msvc_employee.employee.repository.EmployeeRepository;
import com.alexlo.msvc_employee.schedule.dto.request.CreateWorkScheduleRequestDTO;
import com.alexlo.msvc_employee.schedule.dto.request.UpdateWorkScheduleRequestDTO;
import com.alexlo.msvc_employee.schedule.dto.response.WorkScheduleResponseDTO;
import com.alexlo.msvc_employee.schedule.maper.WorkScheduleMapper;
import com.alexlo.msvc_employee.schedule.model.SchedulePeriodEntity;
import com.alexlo.msvc_employee.schedule.model.ShiftEntity;
import com.alexlo.msvc_employee.schedule.model.WorkScheduleEntity;
import com.alexlo.msvc_employee.schedule.repository.SchedulePeriodRepository;
import com.alexlo.msvc_employee.schedule.repository.ShiftRepository;
import com.alexlo.msvc_employee.schedule.repository.WorkScheduleRepository;
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
public class WorkScheduleServiceImpl implements WorkScheduleService {

    @Autowired
    private WorkScheduleMapper workScheduleMapper;

    @Autowired
    private WorkScheduleRepository workScheduleRepository;

    @Autowired
    private SchedulePeriodRepository schedulePeriodRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private ShiftRepository shiftRepository;

    @Transactional
    @Override
    public WorkScheduleResponseDTO create(CreateWorkScheduleRequestDTO dto) {
        WorkScheduleEntity entity = workScheduleMapper.toEntity(dto);
        entity.setSchedulePeriod(getSchedulePeriodById(dto.schedulePeriodId()));
        entity.setEmployee(getEmployeeById(dto.employeeId()));
        if (dto.shiftId() != null) {
            entity.setShift(getShiftById(dto.shiftId()));
        }
        return workScheduleMapper.toResponse(workScheduleRepository.save(entity));
    }

    @Transactional
    @Override
    public WorkScheduleResponseDTO update(UpdateWorkScheduleRequestDTO dto) {
        WorkScheduleEntity entity = getWorkScheduleById(dto.id());
        workScheduleMapper.updateEntityFromDto(dto, entity);
        if (dto.schedulePeriodId() != null) {
            entity.setSchedulePeriod(getSchedulePeriodById(dto.schedulePeriodId()));
        }
        if (dto.employeeId() != null) {
            entity.setEmployee(getEmployeeById(dto.employeeId()));
        }
        entity.setShift(dto.shiftId() != null ? getShiftById(dto.shiftId()) : null);
        return workScheduleMapper.toResponse(workScheduleRepository.save(entity));
    }

    @Transactional(readOnly = true)
    @Override
    public List<WorkScheduleResponseDTO> all() {
        return workScheduleMapper.toResponseList(workScheduleRepository.findAll());
    }

    @Transactional(readOnly = true)
    @Override
    public PageResponse<WorkScheduleResponseDTO> all(Long employeeId, Pageable pageable) {
        Page<WorkScheduleEntity> result;
        if (employeeId != null) {
            result = workScheduleRepository.findByEmployeeId(employeeId, pageable);
        } else {
            result = workScheduleRepository.findAll(pageable);
        }
        return PageMapper.map(result, workScheduleMapper::toResponse);
    }

    @Transactional(readOnly = true)
    @Override
    public WorkScheduleResponseDTO findById(Long id) {
        return workScheduleMapper.toResponse(getWorkScheduleById(id));
    }

    @Transactional(readOnly = true)
    @Override
    public List<WorkScheduleResponseDTO> findByEmployeeAndPeriod(Long employeeId, Long schedulePeriodId) {
        return workScheduleMapper.toResponseList(
                workScheduleRepository.findByEmployeeIdAndSchedulePeriodId(employeeId, schedulePeriodId));
    }

    @Transactional
    @Override
    public void delete(Long id) {
        getWorkScheduleById(id);
        workScheduleRepository.deleteById(id);
    }

    private WorkScheduleEntity getWorkScheduleById(Long id) {
        return workScheduleRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Horario de trabajo no encontrado"));
    }

    private SchedulePeriodEntity getSchedulePeriodById(Long id) {
        return schedulePeriodRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Período de horario no encontrado"));
    }

    private EmployeeEntity getEmployeeById(Long id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Empleado no encontrado"));
    }

    private ShiftEntity getShiftById(Long id) {
        return shiftRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Turno no encontrado"));
    }
}