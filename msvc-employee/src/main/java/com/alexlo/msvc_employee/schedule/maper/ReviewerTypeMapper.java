package com.alexlo.msvc_employee.schedule.maper;

import com.alexlo.msvc_employee.schedule.dto.request.CreateReviewerTypeRequestDTO;
import com.alexlo.msvc_employee.schedule.dto.request.UpdateReviewerTypeRequestDTO;
import com.alexlo.msvc_employee.schedule.dto.response.ReviewerTypeResponseDTO;
import com.alexlo.msvc_employee.schedule.model.ReviewerTypeEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring",
        builder = @org.mapstruct.Builder(disableBuilder = true),
        nullValuePropertyMappingStrategy = org.mapstruct.NullValuePropertyMappingStrategy.IGNORE
)
public interface ReviewerTypeMapper {

    ReviewerTypeEntity toEntity(CreateReviewerTypeRequestDTO dto);

    ReviewerTypeResponseDTO toResponse(ReviewerTypeEntity entity);

    List<ReviewerTypeResponseDTO> toResponseList(Iterable<ReviewerTypeEntity> entities);

    void updateEntityFromDto(UpdateReviewerTypeRequestDTO dto, @MappingTarget ReviewerTypeEntity entity);
}
