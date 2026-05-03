package com.alexlo.msvc_employee.catalog.maper;

import com.alexlo.msvc_employee.catalog.dto.request.CreateDocumentTypeRequestDTO;
import com.alexlo.msvc_employee.catalog.dto.request.UpdateDocumentTypeRequestDTO;
import com.alexlo.msvc_employee.catalog.dto.response.DocumentTypeResponseDTO;
import com.alexlo.msvc_employee.catalog.model.DocumentTypeEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring",
        builder = @org.mapstruct.Builder(disableBuilder = true),
        nullValuePropertyMappingStrategy = org.mapstruct.NullValuePropertyMappingStrategy.IGNORE
)
public interface DocumentTypeMapper {

    @Mapping(target = "isAlphanumeric", defaultValue = "false")
    @Mapping(target = "isActive", defaultValue = "true")
    DocumentTypeEntity toEntity (CreateDocumentTypeRequestDTO dto);

    DocumentTypeResponseDTO toResponse(DocumentTypeEntity entity);

    List<DocumentTypeResponseDTO> toResponseList(Iterable<DocumentTypeEntity> entities);

    void updateEntityFromDto(UpdateDocumentTypeRequestDTO dto, @MappingTarget DocumentTypeEntity entity);
}
