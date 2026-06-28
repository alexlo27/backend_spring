package com.alexlo.msvc_employee.schedule.service;

import com.alexlo.msvc_employee.schedule.dto.request.CreateReviewStatusRequestDTO;
import com.alexlo.msvc_employee.schedule.dto.request.UpdateReviewStatusRequestDTO;
import com.alexlo.msvc_employee.schedule.dto.response.ReviewStatusResponseDTO;
import com.alexlo.msvc_employee.schedule.maper.ReviewStatusMapper;
import com.alexlo.msvc_employee.schedule.model.ReviewStatusEntity;
import com.alexlo.msvc_employee.schedule.repository.ReviewStatusRepository;
import com.alexlo.msvc_employee.shared.exception.NotFoundException;
import com.alexlo.msvc_employee.shared.mapper.PageMapper;
import com.alexlo.msvc_employee.shared.mapper.PageResponse;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ReviewStatusServiceImpl implements ReviewStatusService {

    @Autowired
    private ReviewStatusRepository repository;

    @Autowired
    private ReviewStatusMapper mapper;

    @PostConstruct
    @Transactional
    public void seedStatuses() {
        seedIfNotExists("DRAFT", "Borrador");
        seedIfNotExists("PENDING", "Pendiente de revisión");
        seedIfNotExists("RETURNED", "Devuelto con observaciones");
        seedIfNotExists("APPROVED", "Aprobado");
        seedIfNotExists("REJECTED", "Rechazado");
    }

    private void seedIfNotExists(String code, String name) {
        if (!repository.existsByCodeIgnoreCase(code)) {
            ReviewStatusEntity entity = ReviewStatusEntity.builder()
                    .code(code)
                    .name(name)
                    .isActive(true)
                    .build();
            repository.save(entity);
        }
    }

    @Transactional
    @Override
    public ReviewStatusResponseDTO create(CreateReviewStatusRequestDTO dto) {
        return mapper.toResponse(repository.save(mapper.toEntity(dto)));
    }

    @Transactional
    @Override
    public ReviewStatusResponseDTO update(UpdateReviewStatusRequestDTO dto) {
        ReviewStatusEntity entity = getById(dto.id());
        mapper.updateEntityFromDto(dto, entity);
        return mapper.toResponse(repository.save(entity));
    }

    @Transactional(readOnly = true)
    @Override
    public List<ReviewStatusResponseDTO> all() {
        return mapper.toResponseList(repository.findAll());
    }

    @Transactional(readOnly = true)
    @Override
    public PageResponse<ReviewStatusResponseDTO> all(String name, Pageable pageable) {
        Page<ReviewStatusEntity> result;
        if (name != null && !name.isBlank()) {
            result = repository.findByNameContainingIgnoreCase(name, pageable);
        } else {
            result = repository.findAll(pageable);
        }
        return PageMapper.map(result, mapper::toResponse);
    }

    @Transactional(readOnly = true)
    @Override
    public ReviewStatusResponseDTO findById(Long id) {
        return mapper.toResponse(getById(id));
    }

    @Transactional
    @Override
    public void delete(Long id) {
        getById(id);
        repository.deleteById(id);
    }

    private ReviewStatusEntity getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Estado de revisión no encontrado"));
    }
}
