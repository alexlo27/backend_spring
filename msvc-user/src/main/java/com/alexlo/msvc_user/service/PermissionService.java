package com.alexlo.msvc_user.service;

import com.alexlo.msvc_user.dto.request.CreatePermissionDTO;
import com.alexlo.msvc_user.dto.response.PageResponse;
import com.alexlo.msvc_user.dto.response.PermissionResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PermissionService {

    PermissionResponseDTO create(CreatePermissionDTO dto);

    PermissionResponseDTO update(CreatePermissionDTO dto);

    PermissionResponseDTO findById(Long id);

    List<PermissionResponseDTO> all();

    PageResponse<PermissionResponseDTO> all(/*int page, int size*/ Pageable pageable);

    void delete(Long id);
}
