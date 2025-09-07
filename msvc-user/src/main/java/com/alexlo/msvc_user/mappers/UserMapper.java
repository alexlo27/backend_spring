package com.alexlo.msvc_user.mappers;


import com.alexlo.msvc_user.dto.request.CreateUserDTO;
import com.alexlo.msvc_user.dto.response.UserResponseDTO;
import com.alexlo.msvc_user.model.RoleEntity;
import com.alexlo.msvc_user.model.UserEntity;
import com.alexlo.msvc_user.service.UserService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserEntity toEntity(CreateUserDTO dto);

    //@Mapping(target = "roles", ignore = true)
    UserResponseDTO toResponse(UserEntity entity);

    List<UserResponseDTO> toResponseList(List<UserEntity> entities);

    void updateEntityFromDto(CreateUserDTO dto, @MappingTarget UserEntity entity);

    default RoleEntity map(String roleName) {
        return RoleEntity.builder().name(roleName).build();
    }

    default String map(RoleEntity role) {
        return role.getName();
    }
}
