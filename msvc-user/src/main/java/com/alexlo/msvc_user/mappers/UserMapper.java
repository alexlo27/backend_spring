package com.alexlo.msvc_user.mappers;


import com.alexlo.msvc_user.dto.request.CreateUserDTO;
import com.alexlo.msvc_user.dto.response.UserResponseDTO;
import com.alexlo.msvc_user.model.RoleEntity;
import com.alexlo.msvc_user.model.UserEntity;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring" , uses = {RoleMapper.class}, builder = @org.mapstruct.Builder(disableBuilder = true))
public interface UserMapper {

    @Mapping(target = "roles", ignore = true)
    UserEntity toEntity(CreateUserDTO dto);

    @Named("toBasic")
    @Mapping(target = "roles", ignore = true)
    UserResponseDTO toResponseBasic(UserEntity entity);

    @Named("toDetail")
    UserResponseDTO toResponseDetail(UserEntity entity);

    @IterableMapping(qualifiedByName = "toBasic")
    List<UserResponseDTO> toResponseListBasic(List<UserEntity> entities);

    @IterableMapping(qualifiedByName = "toDetail")
    List<UserResponseDTO> toResponseListDetail(List<UserEntity> entities);

    default String map(RoleEntity role) {
        return role.getName();
    }
}
