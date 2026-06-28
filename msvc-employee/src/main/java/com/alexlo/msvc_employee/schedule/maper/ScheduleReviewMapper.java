package com.alexlo.msvc_employee.schedule.maper;

import com.alexlo.msvc_employee.schedule.dto.response.ReviewActionResponseDTO;
import com.alexlo.msvc_employee.schedule.dto.response.ScheduleReviewDetailDTO;
import com.alexlo.msvc_employee.schedule.model.ReviewActionEntity;
import com.alexlo.msvc_employee.schedule.model.ScheduleReviewEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring",
        builder = @org.mapstruct.Builder(disableBuilder = true),
        nullValuePropertyMappingStrategy = org.mapstruct.NullValuePropertyMappingStrategy.IGNORE,
        uses = { ReviewStatusMapper.class, ReviewerTypeMapper.class }
)
public interface ScheduleReviewMapper {

    @Mapping(target = "status", source = "status")
    @Mapping(target = "actions", source = "actions")
    ScheduleReviewDetailDTO toDetailResponse(ScheduleReviewEntity entity);

    List<ScheduleReviewDetailDTO> toDetailResponseList(Iterable<ScheduleReviewEntity> entities);

    ReviewActionResponseDTO toActionResponse(ReviewActionEntity entity);

    List<ReviewActionResponseDTO> toActionResponseList(Iterable<ReviewActionEntity> entities);
}
