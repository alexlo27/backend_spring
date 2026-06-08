package com.alexlo.msvc_employee.employee.service;

import com.alexlo.msvc_employee.employee.dto.response.EmployeeDetailResponseDTO;
import com.alexlo.msvc_employee.employee.dto.response.PageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EmployeeDetailServiceImpl implements EmployeeDetailService {

    @Autowired
    private EmployeeDetailQueryService employeeDetailQueryService;

    @Transactional(readOnly = true)
    @Override
    public PageResponse<EmployeeDetailResponseDTO> findAll(Long departmentId, String documentNumber, String fullName, Pageable pageable) {
        return employeeDetailQueryService.findAll(departmentId, documentNumber, fullName, pageable);
    }

    @Transactional(readOnly = true)
    @Override
    public EmployeeDetailResponseDTO findById(Long id) {
        return employeeDetailQueryService.findById(id);
    }
}