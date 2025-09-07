package com.alexlo.msvc_user.service;

import com.alexlo.msvc_user.dto.request.CreatePermissionDTO;
import com.alexlo.msvc_user.dto.response.PermissionResponseDTO;

import java.util.List;

public interface PermissionService {

    PermissionResponseDTO create(CreatePermissionDTO dto);

    PermissionResponseDTO update(CreatePermissionDTO dto);

    PermissionResponseDTO findById(Long id);

    List<PermissionResponseDTO> all();

    void delete(Long id);
}
