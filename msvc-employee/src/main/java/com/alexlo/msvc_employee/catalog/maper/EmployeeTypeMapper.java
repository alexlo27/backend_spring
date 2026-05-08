package com.alexlo.msvc_employee.catalog.maper;

import com.alexlo.msvc_employee.catalog.dto.request.CreateEmployeeTypeRequestDTO;
import com.alexlo.msvc_employee.catalog.dto.request.UpdateEmployeeTypeRequestDTO;
import com.alexlo.msvc_employee.catalog.dto.response.EmployeeTypeResponseDTO;
import com.alexlo.msvc_employee.catalog.model.EmployeeTypeEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring",
        builder = @org.mapstruct.Builder(disableBuilder = true),
        nullValuePropertyMappingStrategy = org.mapstruct.NullValuePropertyMappingStrategy.IGNORE
)
public interface EmployeeTypeMapper {

    @Mapping(target = "isActive", defaultValue = "true")
    EmployeeTypeEntity toEntity (CreateEmployeeTypeRequestDTO dto);

    EmployeeTypeResponseDTO toResponse(EmployeeTypeEntity entity);

    List<EmployeeTypeResponseDTO> toResponseList(Iterable<EmployeeTypeEntity> entities);

    void updateEntityFromDto(UpdateEmployeeTypeRequestDTO dto, @MappingTarget EmployeeTypeEntity entity);
}