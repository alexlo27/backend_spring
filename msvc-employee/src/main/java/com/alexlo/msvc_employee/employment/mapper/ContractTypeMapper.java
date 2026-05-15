package com.alexlo.msvc_employee.employment.mapper;

import com.alexlo.msvc_employee.employment.dto.request.CreateContractTypeRequestDTO;
import com.alexlo.msvc_employee.employment.dto.request.UpdateContractTypeRequestDTO;
import com.alexlo.msvc_employee.employment.dto.response.ContractTypeResponseDTO;
import com.alexlo.msvc_employee.employment.model.ContractTypeEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring",
        builder = @org.mapstruct.Builder(disableBuilder = true),
        nullValuePropertyMappingStrategy = org.mapstruct.NullValuePropertyMappingStrategy.IGNORE
)
public interface ContractTypeMapper {

    @Mapping(target = "isActive", defaultValue = "true")
    ContractTypeEntity toEntity (CreateContractTypeRequestDTO dto);

    ContractTypeResponseDTO toResponse(ContractTypeEntity entity);

    List<ContractTypeResponseDTO> toResponseList(Iterable<ContractTypeEntity> entities);

    void updateEntityFromDto(UpdateContractTypeRequestDTO dto, @MappingTarget ContractTypeEntity entity);
}