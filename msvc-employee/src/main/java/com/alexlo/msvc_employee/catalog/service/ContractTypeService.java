package com.alexlo.msvc_employee.catalog.service;

import com.alexlo.msvc_employee.catalog.dto.request.CreateContractTypeRequestDTO;
import com.alexlo.msvc_employee.catalog.dto.request.UpdateContractTypeRequestDTO;
import com.alexlo.msvc_employee.catalog.dto.response.ContractTypeResponseDTO;
import com.alexlo.msvc_employee.shared.mapper.PageResponse;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ContractTypeService {

    ContractTypeResponseDTO create(CreateContractTypeRequestDTO dto);

    ContractTypeResponseDTO update(UpdateContractTypeRequestDTO dto);

    List<ContractTypeResponseDTO> all();

    PageResponse<ContractTypeResponseDTO> all(String name, Pageable pageable);

    ContractTypeResponseDTO findById(Long id);

    void delete(Long id);

}