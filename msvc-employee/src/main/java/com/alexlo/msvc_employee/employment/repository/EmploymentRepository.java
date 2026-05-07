package com.alexlo.msvc_employee.employment.repository;

import com.alexlo.msvc_employee.employment.model.EmploymentEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmploymentRepository extends JpaRepository<EmploymentEntity, Long> {

    List<EmploymentEntity> findByEmployeeId(Long employeeId);
    Page<EmploymentEntity> findByEmployeeId(Long employeeId, Pageable pageable);
}
