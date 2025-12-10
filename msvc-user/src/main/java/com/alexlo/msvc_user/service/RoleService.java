package com.alexlo.msvc_user.service;



import com.alexlo.msvc_user.dto.request.CreateRoleDTO;
import com.alexlo.msvc_user.dto.response.PageResponse;
import com.alexlo.msvc_user.dto.response.RoleResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface RoleService {

    RoleResponseDTO create(CreateRoleDTO dto);

    RoleResponseDTO update(CreateRoleDTO dto);

    RoleResponseDTO findById(Long id);

    RoleResponseDTO findByIdWithPermissions(Long id);

    List<RoleResponseDTO> all();

    List<RoleResponseDTO> allWithPermissions();

    PageResponse<RoleResponseDTO> allWithPermissions(Pageable pageable);

    void delete(Long id);
}
