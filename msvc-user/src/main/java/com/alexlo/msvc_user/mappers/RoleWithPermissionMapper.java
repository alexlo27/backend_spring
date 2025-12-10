package com.alexlo.msvc_user.mappers;

import com.alexlo.msvc_user.dto.request.CreateRoleDTO;
import com.alexlo.msvc_user.dto.response.RoleResponseDTO;
import com.alexlo.msvc_user.model.PermissionEntity;
import com.alexlo.msvc_user.model.RoleEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RoleWithPermissionMapper {

    @Mapping(target = "isActive", ignore = true)
    RoleEntity toEntity(CreateRoleDTO dto);

    RoleResponseDTO toResponse(RoleEntity entity);

    List<RoleResponseDTO> toResponseList(Iterable<RoleEntity> entities);

    /*@Mapping(target = "isActive", ignore = true)
    void updateEntityFromDto(CreateRoleDTO dto, @MappingTarget RoleEntity entity);*/


    default PermissionEntity map(String permissionName) {
        return PermissionEntity.builder().name(permissionName).build();
    }

    default String map(PermissionEntity permission) {
        return permission.getName();
    }

    @Named("roleDetailName") // ⭐️ Nombre para la conversión detallada
    default String mapToName(RoleEntity entity) {
        return entity.getName();
    }
}
