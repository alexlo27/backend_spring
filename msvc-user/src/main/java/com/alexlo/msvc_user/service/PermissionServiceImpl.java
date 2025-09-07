package com.alexlo.msvc_user.service;

import com.alexlo.msvc_user.dto.request.CreatePermissionDTO;
import com.alexlo.msvc_user.dto.response.PermissionResponseDTO;
import com.alexlo.msvc_user.exception.NotFoundException;
import com.alexlo.msvc_user.mappers.PermissionMapper;
import com.alexlo.msvc_user.model.PermissionEntity;
import com.alexlo.msvc_user.repository.PermissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
    public List<PermissionResponseDTO> all() {
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
