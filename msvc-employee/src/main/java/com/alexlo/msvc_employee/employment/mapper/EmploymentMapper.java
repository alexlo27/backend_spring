package com.alexlo.msvc_employee.employment.mapper;

import com.alexlo.msvc_employee.employment.dto.request.CreateEmploymentRequestDTO;
import com.alexlo.msvc_employee.employment.dto.request.UpdateEmploymentRequestDTO;
import com.alexlo.msvc_employee.employment.dto.response.EmploymentResponseDTO;
import com.alexlo.msvc_employee.employment.model.EmploymentEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring", builder = @org.mapstruct.Builder(disableBuilder = true))
public interface EmploymentMapper {

    @Mapping(target = "employee", ignore = true)
    @Mapping(target = "department", ignore = true)
    @Mapping(target = "position", ignore = true)
    @Mapping(target = "employeeType", ignore = true)
    @Mapping(target = "contractType", ignore = true)
    EmploymentEntity toEntity(CreateEmploymentRequestDTO dto);

    @Mapping(target = "employeeName", source = "employee.name")
    @Mapping(target = "employeeId", source = "employee.id")
    @Mapping(target = "departmentName", source = "department.name")
    @Mapping(target = "departmentId", source = "department.id")
    @Mapping(target = "positionName", source = "position.name")
    @Mapping(target = "positionId", source = "position.id")
    @Mapping(target = "employeeTypeName", source = "employeeType.name")
    @Mapping(target = "employeeTypeId", source = "employeeType.id")
    @Mapping(target = "contractTypeName", source = "contractType.name")
    @Mapping(target = "contractTypeId", source = "contractType.id")
    EmploymentResponseDTO toResponse(EmploymentEntity entity);

    List<EmploymentResponseDTO> toResponseList(Iterable<EmploymentEntity> entities);

    void updateEntityFromDto(UpdateEmploymentRequestDTO dto, @MappingTarget EmploymentEntity entity);
}
