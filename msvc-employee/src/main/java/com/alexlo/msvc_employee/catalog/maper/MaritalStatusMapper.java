package com.alexlo.msvc_employee.catalog.maper;

import com.alexlo.msvc_employee.catalog.dto.request.CreateMaritalStatusRequestDTO;
import com.alexlo.msvc_employee.catalog.dto.request.UpdateMaritalStatusRequestDTO;
import com.alexlo.msvc_employee.catalog.dto.response.MaritalStatusResponseDTO;
import com.alexlo.msvc_employee.catalog.model.MaritalStatusEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring",
        builder = @org.mapstruct.Builder(disableBuilder = true)
        //nullValuePropertyMappingStrategy = org.mapstruct.NullValuePropertyMappingStrategy.IGNORE
)
public interface MaritalStatusMapper {

    @Mapping(target = "isActive", defaultValue = "true")
    MaritalStatusEntity toEntity (CreateMaritalStatusRequestDTO dto);

    MaritalStatusResponseDTO toResponse(MaritalStatusEntity entity);

    List<MaritalStatusResponseDTO> toResponseList(Iterable<MaritalStatusEntity> entities);

    void updateEntityFromDto(UpdateMaritalStatusRequestDTO dto, @MappingTarget MaritalStatusEntity entity);
}