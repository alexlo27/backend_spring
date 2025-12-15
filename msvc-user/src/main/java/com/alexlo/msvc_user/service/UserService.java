package com.alexlo.msvc_user.service;

import com.alexlo.msvc_user.dto.request.CreateUserDTO;
import com.alexlo.msvc_user.dto.response.PageResponse;
import com.alexlo.msvc_user.dto.response.UserResponseDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserService {

    UserResponseDTO create(CreateUserDTO dto);

    UserResponseDTO update(CreateUserDTO dto);

    UserResponseDTO findById(Long id);

    List<UserResponseDTO> all();

    List<UserResponseDTO> allWithRoles();

    PageResponse<UserResponseDTO> allWithRoles(String username, Pageable pageable);

    void delete(Long id);
}
