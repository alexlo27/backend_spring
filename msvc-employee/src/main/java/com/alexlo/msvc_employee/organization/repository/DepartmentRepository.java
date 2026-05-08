package com.alexlo.msvc_employee.organization.repository;

import com.alexlo.msvc_employee.organization.model.DepartmentEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DepartmentRepository extends JpaRepository<DepartmentEntity, Long> {

    boolean existsByNameIgnoreCase(String name);
    boolean existsByNameIgnoreCaseAndIdNot(String name, Long id);

    boolean existsByCodeIgnoreCase(String code);
    boolean existsByCodeIgnoreCaseAndIdNot(String code, Long id);

    @EntityGraph(attributePaths = "parent")
    Page<DepartmentEntity> findByParentIsNull(Pageable pageable);

    @EntityGraph(attributePaths = {"children"/*,"parent"*/})
    List<DepartmentEntity> findByParentIsNull();

    @EntityGraph(attributePaths = "parent")
    Page<DepartmentEntity> findAll(Pageable pageable);

    @EntityGraph(attributePaths = "parent")
    List<DepartmentEntity> findAll();
}
