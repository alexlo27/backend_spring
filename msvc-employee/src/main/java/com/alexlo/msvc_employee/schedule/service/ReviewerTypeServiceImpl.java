package com.alexlo.msvc_employee.schedule.service;

import com.alexlo.msvc_employee.schedule.dto.request.CreateReviewerTypeRequestDTO;
import com.alexlo.msvc_employee.schedule.dto.request.UpdateReviewerTypeRequestDTO;
import com.alexlo.msvc_employee.schedule.dto.response.ReviewerTypeResponseDTO;
import com.alexlo.msvc_employee.schedule.maper.ReviewerTypeMapper;
import com.alexlo.msvc_employee.schedule.model.ReviewerTypeEntity;
import com.alexlo.msvc_employee.schedule.repository.ReviewerTypeRepository;
import com.alexlo.msvc_employee.shared.exception.DuplicateResourceException;
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
public class ReviewerTypeServiceImpl implements ReviewerTypeService {

    @Autowired
    private ReviewerTypeRepository repository;

    @Autowired
    private ReviewerTypeMapper mapper;

    @PostConstruct
    @Transactional
    public void seedReviewerTypes() {
        seedIfNotExists("SUPERVISOR", "Supervisor", 1);
        seedIfNotExists("HR", "Recursos Humanos", 2);
    }

    private void seedIfNotExists(String code, String name, int level) {
        if (!repository.existsByCodeIgnoreCase(code)) {
            ReviewerTypeEntity entity = ReviewerTypeEntity.builder()
                    .code(code)
                    .name(name)
                    .level(level)
                    .isActive(true)
                    .build();
            repository.save(entity);
        }
    }

    @Transactional
    @Override
    public ReviewerTypeResponseDTO create(CreateReviewerTypeRequestDTO dto) {
        if (repository.existsByLevelAndIsActiveTrue(dto.level())) {
            throw new DuplicateResourceException("Ya existe un tipo de revisor activo con el nivel " + dto.level(), "level");
        }
        return mapper.toResponse(repository.save(mapper.toEntity(dto)));
    }

    @Transactional
    @Override
    public ReviewerTypeResponseDTO update(UpdateReviewerTypeRequestDTO dto) {
        ReviewerTypeEntity entity = getById(dto.id());
        if (dto.level() != null && repository.existsByLevelAndIsActiveTrueAndIdNot(dto.level(), dto.id())) {
            throw new DuplicateResourceException("Ya existe otro tipo de revisor activo con el nivel " + dto.level(), "level");
        }
        mapper.updateEntityFromDto(dto, entity);
        return mapper.toResponse(repository.save(entity));
    }

    @Transactional(readOnly = true)
    @Override
    public List<ReviewerTypeResponseDTO> all() {
        return mapper.toResponseList(repository.findAll());
    }

    @Transactional(readOnly = true)
    @Override
    public PageResponse<ReviewerTypeResponseDTO> all(String name, Pageable pageable) {
        Page<ReviewerTypeEntity> result;
        if (name != null && !name.isBlank()) {
            result = repository.findByNameContainingIgnoreCase(name, pageable);
        } else {
            result = repository.findAll(pageable);
        }
        return PageMapper.map(result, mapper::toResponse);
    }

    @Transactional(readOnly = true)
    @Override
    public ReviewerTypeResponseDTO findById(Long id) {
        return mapper.toResponse(getById(id));
    }

    @Transactional
    @Override
    public void delete(Long id) {
        getById(id);
        repository.deleteById(id);
    }

    private ReviewerTypeEntity getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Tipo de revisor no encontrado"));
    }
}
