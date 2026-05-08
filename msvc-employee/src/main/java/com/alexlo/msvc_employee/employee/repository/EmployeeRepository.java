package com.alexlo.msvc_employee.employee.repository;

import com.alexlo.msvc_employee.employee.model.EmployeeEntity;
import com.alexlo.msvc_employee.organization.model.DepartmentEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Long> {

    boolean existsByEmailIgnoreCase(String email);
    boolean existsByEmailIgnoreCaseAndIdNot(String email, Long id);
    boolean existsByDocumentNumberIgnoreCase(String documentNumber);
    boolean existsByDocumentNumberIgnoreCaseAndIdNot(String documentNumber, Long id);
    //Page<EmployeeEntity> findByNameContainingIgnoreCase(String name, Pageable pageable);

    @Query("""
    SELECT e FROM EmployeeEntity e
    WHERE LOWER(CONCAT(e.name, ' ', e.lastName)) 
          LIKE LOWER(CONCAT('%', :name, '%'))
""")
    Page<EmployeeEntity> search(@Param("name") String name, Pageable pageable);

    @Override
    @EntityGraph(attributePaths = {"documentType", "gender"})
    Page<EmployeeEntity> findAll(Pageable pageable);

    @Override
    @EntityGraph(attributePaths = {"documentType", "gender"})
    List<EmployeeEntity> findAll();
}
