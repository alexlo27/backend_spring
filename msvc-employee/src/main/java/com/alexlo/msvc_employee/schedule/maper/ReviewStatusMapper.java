package com.alexlo.msvc_employee.schedule.maper;

import com.alexlo.msvc_employee.schedule.dto.request.CreateReviewStatusRequestDTO;
import com.alexlo.msvc_employee.schedule.dto.request.UpdateReviewStatusRequestDTO;
import com.alexlo.msvc_employee.schedule.dto.response.ReviewStatusResponseDTO;
import com.alexlo.msvc_employee.schedule.model.ReviewStatusEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring",
        builder = @org.mapstruct.Builder(disableBuilder = true),
        nullValuePropertyMappingStrategy = org.mapstruct.NullValuePropertyMappingStrategy.IGNORE
)
public interface ReviewStatusMapper {

    ReviewStatusEntity toEntity(CreateReviewStatusRequestDTO dto);

    ReviewStatusResponseDTO toResponse(ReviewStatusEntity entity);

    List<ReviewStatusResponseDTO> toResponseList(Iterable<ReviewStatusEntity> entities);

    void updateEntityFromDto(UpdateReviewStatusRequestDTO dto, @MappingTarget ReviewStatusEntity entity);
}
