package com.alexlo.msvc_employee.organization.maper;

import com.alexlo.msvc_employee.organization.dto.request.CreatePositionRequestDTO;
import com.alexlo.msvc_employee.organization.dto.request.UpdatePositionRequestDTO;
import com.alexlo.msvc_employee.organization.dto.response.PositionResponseDTO;
import com.alexlo.msvc_employee.organization.model.PositionEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring", builder = @org.mapstruct.Builder(disableBuilder = true))
public interface PositionMapper {

    @Mapping( target = "name",  expression = "java(dto.name().trim().toUpperCase())" )
    PositionEntity toEntity(CreatePositionRequestDTO dto);

    PositionResponseDTO toResponse(PositionEntity position);

    List<PositionResponseDTO> toResponseList(Iterable<PositionEntity> entities);

    void updateEntityFromDto(UpdatePositionRequestDTO dto, @MappingTarget PositionEntity entity);
}
