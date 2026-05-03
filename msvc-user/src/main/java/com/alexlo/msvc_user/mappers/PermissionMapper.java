package com.alexlo.msvc_user.mappers;

import com.alexlo.msvc_user.dto.request.CreatePermissionDTO;
import com.alexlo.msvc_user.dto.response.PermissionResponseDTO;
import com.alexlo.msvc_user.model.PermissionEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring", builder = @org.mapstruct.Builder(disableBuilder = true))
public interface PermissionMapper {

    //@Mapping(target = "isActive", ignore = true)
    PermissionEntity toEntity(CreatePermissionDTO dto);

    PermissionResponseDTO toResponse(PermissionEntity entity);

    List<PermissionResponseDTO> toResponseList(Iterable<PermissionEntity> entities);

    //@Mapping(target = "isActive", ignore = true)
    void updateEntityFromDto(CreatePermissionDTO dto, @MappingTarget PermissionEntity entity);
}
