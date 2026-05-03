package com.alexlo.msvc_user.mappers;

import com.alexlo.msvc_user.dto.response.UserResponseDTO;
import com.alexlo.msvc_user.model.RoleEntity;
import com.alexlo.msvc_user.model.UserEntity;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class UserMaperDeprecated {

    public static UserResponseDTO toDTO(UserEntity user) {
        Set<String> roleNames = user.getRoles()
                .stream()
                .map(RoleEntity::getName)
                .collect(Collectors.toSet());

        return new UserResponseDTO(
                user.getId(),
                user.getEmail(),
                user.getUsername(),
                user.getIsEnabled(),
                roleNames
        );
    }

    public static List<UserResponseDTO> toDTOList(List<UserEntity> users) {
        return users.stream()
                .map(UserMaperDeprecated::toDTO)
                .collect(Collectors.toList());
    }
}
