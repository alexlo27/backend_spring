package com.alexlo.msvc_employee.schedule.maper;

import com.alexlo.msvc_employee.schedule.dto.request.CreateShiftRequestDTO;
import com.alexlo.msvc_employee.schedule.dto.request.UpdateShiftRequestDTO;
import com.alexlo.msvc_employee.schedule.dto.response.ShiftResponseDTO;
import com.alexlo.msvc_employee.schedule.model.ShiftEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring",
        builder = @org.mapstruct.Builder(disableBuilder = true)
        //nullValuePropertyMappingStrategy = org.mapstruct.NullValuePropertyMappingStrategy.IGNORE
)
public interface ShiftMapper {

    @Mapping(target = "isActive", defaultValue = "true")
    ShiftEntity toEntity(CreateShiftRequestDTO dto);

    ShiftResponseDTO toResponse(ShiftEntity entity);

    List<ShiftResponseDTO> toResponseList(Iterable<ShiftEntity> entities);

    void updateEntityFromDto(UpdateShiftRequestDTO dto, @MappingTarget ShiftEntity entity);
}