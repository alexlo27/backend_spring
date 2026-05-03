package com.alexlo.msvc_employee.organization.maper;

import com.alexlo.msvc_employee.organization.dto.request.CreateDepartmentRequestDTO;
import com.alexlo.msvc_employee.organization.dto.request.UpdateDepartmentRequestDTO;
import com.alexlo.msvc_employee.organization.dto.response.DepartmentResponseDTO;
import com.alexlo.msvc_employee.organization.model.DepartmentEntity;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", builder = @org.mapstruct.Builder(disableBuilder = true))
public interface DepartmentMapper {

    @Mapping( target = "name",  expression = "java(dto.name().trim().toUpperCase())" )
    @Mapping( target = "code",  expression = "java(dto.code() != null ? dto.code().trim().toUpperCase() : null)" )
    DepartmentEntity toEntity(CreateDepartmentRequestDTO dto);

    @Mapping(target = "parentId", source = "parent.id")
    DepartmentResponseDTO toResponse(DepartmentEntity department);

    List<DepartmentResponseDTO> toResponseList(Iterable<DepartmentEntity> entities);

    @BeanMapping(
            nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
    )
    @Mapping(target = "parent", ignore = true)
    @Mapping( target = "name",  expression = "java(dto.name().trim().toUpperCase())" )
    @Mapping( target = "code",  expression = "java(dto.code() != null ? dto.code().trim().toUpperCase() : null)" )
    void updateEntityFromDto(UpdateDepartmentRequestDTO dto, @MappingTarget DepartmentEntity entity);
}
