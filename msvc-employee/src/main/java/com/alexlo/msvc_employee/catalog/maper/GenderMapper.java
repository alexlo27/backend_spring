package com.alexlo.msvc_employee.catalog.maper;

import com.alexlo.msvc_employee.catalog.dto.request.CreateGenderRequestDTO;
import com.alexlo.msvc_employee.catalog.dto.request.UpdateGenderRequestDTO;
import com.alexlo.msvc_employee.catalog.dto.response.GenderResponseDTO;
import com.alexlo.msvc_employee.catalog.model.GenderEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring",
        builder = @org.mapstruct.Builder(disableBuilder = true)
        //nullValuePropertyMappingStrategy = org.mapstruct.NullValuePropertyMappingStrategy.IGNORE
)
public interface GenderMapper {

    @Mapping(target = "isActive", defaultValue = "true")
    GenderEntity toEntity (CreateGenderRequestDTO dto);

    GenderResponseDTO toResponse(GenderEntity entity);

    List<GenderResponseDTO> toResponseList(Iterable<GenderEntity> entities);

    void updateEntityFromDto(UpdateGenderRequestDTO dto, @MappingTarget GenderEntity entity);
}
