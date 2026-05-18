package com.alexlo.msvc_employee.employment.mapper;

import com.alexlo.msvc_employee.employment.dto.request.CreateEmployeeTypeRequestDTO;
import com.alexlo.msvc_employee.employment.dto.request.UpdateEmployeeTypeRequestDTO;
import com.alexlo.msvc_employee.employment.dto.response.EmployeeTypeResponseDTO;
import com.alexlo.msvc_employee.employment.model.EmployeeTypeEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring",
        builder = @org.mapstruct.Builder(disableBuilder = true)
        //nullValuePropertyMappingStrategy = org.mapstruct.NullValuePropertyMappingStrategy.IGNORE
)
public interface EmployeeTypeMapper {

    @Mapping(target = "isActive", defaultValue = "true")
    EmployeeTypeEntity toEntity (CreateEmployeeTypeRequestDTO dto);

    EmployeeTypeResponseDTO toResponse(EmployeeTypeEntity entity);

    List<EmployeeTypeResponseDTO> toResponseList(Iterable<EmployeeTypeEntity> entities);

    void updateEntityFromDto(UpdateEmployeeTypeRequestDTO dto, @MappingTarget EmployeeTypeEntity entity);
}