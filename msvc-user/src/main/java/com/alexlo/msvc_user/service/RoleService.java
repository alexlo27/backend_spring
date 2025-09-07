package com.alexlo.msvc_user.service;



import com.alexlo.msvc_user.dto.request.CreateRoleDTO;
import com.alexlo.msvc_user.dto.response.RoleResponseDTO;

import java.util.List;

public interface RoleService {

    RoleResponseDTO create(CreateRoleDTO dto);

    RoleResponseDTO update(CreateRoleDTO dto);

    RoleResponseDTO findById(Long id);

    List<RoleResponseDTO> all();

    void delete(Long id);
}
