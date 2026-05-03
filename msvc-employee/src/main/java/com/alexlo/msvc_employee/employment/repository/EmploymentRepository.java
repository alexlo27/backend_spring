package com.alexlo.msvc_employee.employment.repository;

import com.alexlo.msvc_employee.employment.model.EmploymentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmploymentRepository extends JpaRepository<EmploymentEntity, Long> {


}
