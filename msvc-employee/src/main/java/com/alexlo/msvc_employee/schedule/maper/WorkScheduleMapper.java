package com.alexlo.msvc_employee.schedule.maper;

import com.alexlo.msvc_employee.schedule.dto.request.CreateWorkScheduleRequestDTO;
import com.alexlo.msvc_employee.schedule.dto.request.UpdateWorkScheduleRequestDTO;
import com.alexlo.msvc_employee.schedule.dto.response.WorkScheduleResponseDTO;
import com.alexlo.msvc_employee.schedule.model.WorkScheduleEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring",
        builder = @org.mapstruct.Builder(disableBuilder = true),
        nullValuePropertyMappingStrategy = org.mapstruct.NullValuePropertyMappingStrategy.IGNORE
)
public interface WorkScheduleMapper {

    @Mapping(target = "schedulePeriod", ignore = true)
    @Mapping(target = "employee", ignore = true)
    @Mapping(target = "shift", ignore = true)
    WorkScheduleEntity toEntity (CreateWorkScheduleRequestDTO dto);

    @Mapping(target = "schedulePeriodId", source = "schedulePeriod.id")
    @Mapping(target = "employeeId", source = "employee.id")
    @Mapping(target = "shiftId", source = "shift.id")
    WorkScheduleResponseDTO toResponse(WorkScheduleEntity entity);

    List<WorkScheduleResponseDTO> toResponseList(Iterable<WorkScheduleEntity> entities);

    @Mapping(target = "schedulePeriod", ignore = true)
    @Mapping(target = "employee", ignore = true)
    @Mapping(target = "shift", ignore = true)
    void updateEntityFromDto(UpdateWorkScheduleRequestDTO dto, @MappingTarget WorkScheduleEntity entity);
}