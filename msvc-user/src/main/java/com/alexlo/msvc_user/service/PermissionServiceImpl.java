package com.alexlo.msvc_user.service;

import com.alexlo.msvc_user.dto.request.CreatePermissionDTO;
import com.alexlo.msvc_user.dto.response.PageResponse;
import com.alexlo.msvc_user.dto.response.PermissionResponseDTO;
import com.alexlo.msvc_user.exception.NotFoundException;
import com.alexlo.msvc_user.mappers.PageMapper;
import com.alexlo.msvc_user.mappers.PermissionMapper;
import com.alexlo.msvc_user.model.PermissionEntity;
import com.alexlo.msvc_user.repository.PermissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermissionServiceImpl implements PermissionService{

    @Autowired
    PermissionRepository permissionRepository;

    @Autowired
    PermissionMapper permissionMapper;

    @Override
    public PermissionResponseDTO create(CreatePermissionDTO dto) {
        return permissionMapper.toResponse(permissionRepository.save(permissionMapper.toEntity(dto)));
    }

    @Override
    public PermissionResponseDTO update(CreatePermissionDTO dto) {
        PermissionEntity permissionEntity = getPermissionById(dto.id());
        permissionMapper.updateEntityFromDto(dto, permissionEntity);
        return permissionMapper.toResponse(permissionRepository.save(permissionEntity));
    }

    @Override
    public PermissionResponseDTO findById(Long id) {
        PermissionEntity permissionEntity =  permissionRepository.findById(id)
                                                .orElseThrow(() ->new NotFoundException("Permiso no encontrado"));
        return permissionMapper.toResponse(permissionEntity);
    }

    @Override
    public PageResponse<PermissionResponseDTO> all(/*int page, int size*/ Pageable pageable) {
        /*page = Math.max(0, page-1);
        size = size <= 0 ? 10 : size;
        Pageable pageable = PageRequest.of(page, size, Sort.by("id").descending());*/
        Page<PermissionEntity> result = permissionRepository.findAll(pageable);
        return PageMapper.map(result, permissionMapper::toResponse);
        //return result.map(permissionMapper::toResponse);
        //return permissionMapper.toResponseList(permissionRepository.findAll());
    }

    @Override
    public List<PermissionResponseDTO> all() {
        //Page<PermissionEntity> result = permissionRepository.findAll();
        return permissionMapper.toResponseList(permissionRepository.findAll());
    }

    @Override
    public void delete(Long id) {
        getPermissionById(id);
        permissionRepository.deleteById(id);
    }

    private PermissionEntity getPermissionById(Long id){
        return permissionRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Permiso no encontrado"));
    }
}
