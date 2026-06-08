package com.alexlo.msvc_employee.employee.mapper;

import com.alexlo.msvc_employee.employee.dto.response.ContractTypeDTO;
import com.alexlo.msvc_employee.employee.dto.request.CreateEmployeeDTO;
import com.alexlo.msvc_employee.employee.dto.request.UpdateEmployeeDTO;
import com.alexlo.msvc_employee.employee.dto.response.DepartmentDTO;
import com.alexlo.msvc_employee.employee.dto.response.DocumentTypeDTO;
import com.alexlo.msvc_employee.employee.dto.response.EmployeeDetailResponseDTO;
import com.alexlo.msvc_employee.employee.dto.response.EmployeeTypeDTO;
import com.alexlo.msvc_employee.employee.dto.response.EmployeeResponseDTO;
import com.alexlo.msvc_employee.employee.dto.response.GenderDTO;
import com.alexlo.msvc_employee.employee.dto.response.MaritalStatusDTO;
import com.alexlo.msvc_employee.employee.dto.response.PositionDTO;
import com.alexlo.msvc_employee.employee.model.EmployeeEntity;
import com.alexlo.msvc_employee.employment.model.EmploymentEntity;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", builder = @org.mapstruct.Builder(disableBuilder = true))
public interface EmployeeMapper {

    @Mapping(target = "documentType", ignore = true)
    @Mapping(target = "gender", ignore = true)
    @Mapping(target = "maritalStatus", ignore = true)
    EmployeeEntity toEntity(CreateEmployeeDTO dto);

    @Mapping(target = "gender", source = "gender.code")
    @Mapping(target = "documentType", source = "documentType.code")
    @Mapping(target = "maritalStatus", source = "maritalStatus.code")
    EmployeeResponseDTO toResponse(EmployeeEntity entity);

    List<EmployeeResponseDTO> toResponseList(Iterable<EmployeeEntity> entities);

    @Mapping(target = "documentType", ignore = true)
    @Mapping(target = "gender", ignore = true)
    @Mapping(target = "maritalStatus", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromDto(UpdateEmployeeDTO dto, @MappingTarget EmployeeEntity entity);

    default EmployeeDetailResponseDTO toDetailResponse(EmployeeEntity entity) {
        EmploymentEntity employment = entity.getEmployments().stream()
            .filter(em -> {
                java.time.LocalDate now = java.time.LocalDate.now();
                return !em.getStartDate().isAfter(now)
                    && (em.getEndDate() == null || !em.getEndDate().isBefore(now));
            })
            .findFirst()
            .orElse(null);

        return new EmployeeDetailResponseDTO(
            entity.getId(),
            entity.getName(),
            entity.getLastName(),
            new DocumentTypeDTO(
                entity.getDocumentType().getId(),
                entity.getDocumentType().getCode(),
                entity.getDocumentType().getName()
            ),
            entity.getDocumentNumber(),
            entity.getBirthDate(),
            entity.getEmail(),
            entity.getPhone(),
            entity.getAddress(),
            new GenderDTO(
                entity.getGender().getId(),
                entity.getGender().getCode(),
                entity.getGender().getName()
            ),
            new MaritalStatusDTO(
                entity.getMaritalStatus().getId(),
                entity.getMaritalStatus().getCode(),
                entity.getMaritalStatus().getName()
            ),
            entity.getIsActive(),
            employment != null ? new DepartmentDTO(
                employment.getDepartment().getId(),
                employment.getDepartment().getName(),
                employment.getDepartment().getCode(),
                employment.getDepartment().getDescription()
            ) : null,
            employment != null ? new PositionDTO(
                employment.getPosition().getId(),
                employment.getPosition().getName()
            ) : null,
            employment != null && employment.getContractType() != null
                ? new ContractTypeDTO(
                    employment.getContractType().getId(),
                    employment.getContractType().getCode(),
                    employment.getContractType().getName()
                ) : null,
            employment != null && employment.getEmployeeType() != null
                ? new EmployeeTypeDTO(
                    employment.getEmployeeType().getId(),
                    employment.getEmployeeType().getCode(),
                    employment.getEmployeeType().getName()
                ) : null
        );
    }

    default List<EmployeeDetailResponseDTO> toDetailResponseList(List<EmployeeEntity> entities) {
        return entities.stream().map(this::toDetailResponse).toList();
    }

}
