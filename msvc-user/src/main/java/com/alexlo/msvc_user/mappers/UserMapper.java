package com.alexlo.msvc_user.mappers;


import com.alexlo.msvc_user.dto.request.CreateUserDTO;
import com.alexlo.msvc_user.dto.response.UserResponseDTO;
import com.alexlo.msvc_user.model.RoleEntity;
import com.alexlo.msvc_user.model.UserEntity;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring" , uses = {RoleMapper.class})
public interface UserMapper {

    UserEntity toEntity(CreateUserDTO dto);

    @Named("toBasic")
    @Mapping(target = "roles", ignore = true)
    UserResponseDTO toResponseBasic(UserEntity entity);

    @Named("toDetail")
    UserResponseDTO toResponseDetail(UserEntity entity);

    @IterableMapping(qualifiedByName = "toBasic")
    List<UserResponseDTO> toResponseListBasic(List<UserEntity> entities);

    @IterableMapping(qualifiedByName = "toDetail")
    @Mapping(target = "roles", ignore = true)
    List<UserResponseDTO> toResponseListDetail(List<UserEntity> entities);

    default RoleEntity map(String roleName) {
        return RoleEntity.builder().name(roleName).build();
    }

    default String map(RoleEntity role) {
        return role.getName();
    }
}
