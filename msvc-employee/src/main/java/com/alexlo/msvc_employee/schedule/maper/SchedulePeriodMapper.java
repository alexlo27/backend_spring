package com.alexlo.msvc_employee.schedule.maper;

import com.alexlo.msvc_employee.schedule.dto.request.CreateSchedulePeriodRequestDTO;
import com.alexlo.msvc_employee.schedule.dto.request.UpdateSchedulePeriodRequestDTO;
import com.alexlo.msvc_employee.schedule.dto.response.SchedulePeriodResponseDTO;
import com.alexlo.msvc_employee.schedule.model.SchedulePeriodEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring",
        builder = @org.mapstruct.Builder(disableBuilder = true)
        //nullValuePropertyMappingStrategy = org.mapstruct.NullValuePropertyMappingStrategy.IGNORE
)
public interface SchedulePeriodMapper {

    @Mapping(target = "isActive", defaultValue = "true")
    SchedulePeriodEntity toEntity(CreateSchedulePeriodRequestDTO dto);

    SchedulePeriodResponseDTO toResponse(SchedulePeriodEntity entity);

    List<SchedulePeriodResponseDTO> toResponseList(Iterable<SchedulePeriodEntity> entities);

    void updateEntityFromDto(UpdateSchedulePeriodRequestDTO dto, @MappingTarget SchedulePeriodEntity entity);
}