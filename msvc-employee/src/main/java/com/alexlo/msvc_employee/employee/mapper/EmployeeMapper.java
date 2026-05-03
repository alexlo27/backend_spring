package com.alexlo.msvc_employee.employee.mapper;

import com.alexlo.msvc_employee.employee.dto.request.CreateEmployeeDTO;
import com.alexlo.msvc_employee.employee.dto.request.UpdateEmployeeDTO;
import com.alexlo.msvc_employee.employee.dto.response.EmployeeResponseDTO;
import com.alexlo.msvc_employee.employee.model.EmployeeEntity;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", builder = @org.mapstruct.Builder(disableBuilder = true))
public interface EmployeeMapper {

    @Mapping(target = "documentType", ignore = true)
    @Mapping(target = "gender", ignore = true)
    EmployeeEntity toEntity(CreateEmployeeDTO dto);

    @Mapping(target = "gender", source = "gender.code")
    @Mapping(target = "documentType", source = "documentType.code")
    EmployeeResponseDTO toResponse(EmployeeEntity entity);

    List<EmployeeResponseDTO> toResponseList(Iterable<EmployeeEntity> entities);

    @Mapping(target = "documentType", ignore = true)
    @Mapping(target = "gender", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromDto(UpdateEmployeeDTO dto, @MappingTarget EmployeeEntity entity);

}
