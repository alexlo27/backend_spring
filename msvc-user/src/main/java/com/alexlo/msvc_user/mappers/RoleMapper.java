package com.alexlo.msvc_user.mappers;

import com.alexlo.msvc_user.dto.request.CreateRoleDTO;
import com.alexlo.msvc_user.dto.response.RoleResponseDTO;
import com.alexlo.msvc_user.model.PermissionEntity;
import com.alexlo.msvc_user.model.RoleEntity;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", builder = @org.mapstruct.Builder(disableBuilder = true))
public interface RoleMapper {

    @Mapping(target = "permissions", ignore = true)
    RoleEntity toEntity(CreateRoleDTO dto);

    @Named("toBasic")
    @Mapping(target = "permissions", ignore = true)
    RoleResponseDTO toResponse(RoleEntity entity);


    @Named("toDetail")
    RoleResponseDTO toResponseDetail(RoleEntity entity);

    @IterableMapping(qualifiedByName = "toBasic")
    List<RoleResponseDTO> toResponseListBasic(List<RoleEntity> entities);

    @IterableMapping(qualifiedByName = "toDetail")
    @Mapping(target = "permissions", ignore = true)
    List<RoleResponseDTO> toResponseListDetail(List<RoleEntity> entities);

    default String map(PermissionEntity permission) {
        return permission.getName();
    }
}
