package com.alexlo.msvc_user.service;

import com.alexlo.msvc_user.dto.request.CreateRoleDTO;
import com.alexlo.msvc_user.dto.response.RoleResponseDTO;
import com.alexlo.msvc_user.exception.NotFoundException;
import com.alexlo.msvc_user.mappers.RoleMapper;
import com.alexlo.msvc_user.model.PermissionEntity;
import com.alexlo.msvc_user.model.RoleEntity;
import com.alexlo.msvc_user.repository.PermissionRepository;
import com.alexlo.msvc_user.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class RoleServiceImpl implements RoleService{

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PermissionRepository permissionRepository;

    @Autowired
    RoleMapper roleMapper;

    @Override
    public RoleResponseDTO create(CreateRoleDTO dto) {
        Set<String> permissions = Optional.ofNullable(dto.permissions()).orElse(Collections.emptySet());
        Set<PermissionEntity> permissionsEntity = resolvePermissions(permissions);
        RoleEntity roleEntity = RoleEntity.builder()
                .name(dto.name())
                .permissions(permissionsEntity)
                .build();
        return roleMapper.toResponse(roleRepository.save(roleEntity));
    }

    @Override
    public RoleResponseDTO update(CreateRoleDTO dto) {
        RoleEntity roleEntity = getRoleById(dto.id());

        Set<String> permissions = Optional.ofNullable(dto.permissions()).orElse(Collections.emptySet());
        Set<PermissionEntity> permissionsEntity = resolvePermissions(permissions);
        roleEntity.setPermissions(permissionsEntity);

        roleEntity.setName(dto.name());

        //roleMapper.updateEntityFromDto(dto, roleEntity);
        return roleMapper.toResponse(roleRepository.save(roleEntity));
    }

    @Override
    public RoleResponseDTO findById(Long id) {
        RoleEntity roleEntity =  roleRepository.findById(id)
                                    .orElseThrow( () -> new NotFoundException("El rol no existe"));
        return roleMapper.toResponse(roleEntity);
    }

    @Override
    public List<RoleResponseDTO> all() {
        return roleMapper.toResponseList(roleRepository.findAll());
    }

    @Override
    public void delete(Long id) {
        getRoleById(id);
        roleRepository.deleteById(id);
    }

    private RoleEntity getRoleById(Long id){
        return roleRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Rol no encontrado"));
    }

    private Set<PermissionEntity> resolvePermissions(Set<String> permissionsName) {
        return permissionsName.stream()
                .map(permission -> permissionRepository.findByName(permission)
                        .orElseThrow(() -> new NotFoundException("Permiso no encontrado: " + permission)))
                .collect(Collectors.toSet());
    }
}
