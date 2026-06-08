package com.alexlo.msvc_employee.employee.repository;

import com.alexlo.msvc_employee.employee.model.EmployeeEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
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

    @Query("""
    SELECT e FROM EmployeeEntity e
    WHERE LOWER(e.documentNumber) LIKE LOWER(CONCAT('%', :keyword, '%'))
       OR LOWER(CONCAT(e.name, ' ', e.lastName)) LIKE LOWER(CONCAT('%', :keyword, '%'))
""")
    Page<EmployeeEntity> autocomplete(@Param("keyword") String keyword, Pageable pageable);

    @Override
    @EntityGraph(attributePaths = {"documentType", "gender"})
    Page<EmployeeEntity> findAll(Pageable pageable);

    @Override
    @EntityGraph(attributePaths = {"documentType", "gender"})
    List<EmployeeEntity> findAll();

    @EntityGraph(attributePaths = {
        "documentType", "gender", "maritalStatus",
        "employments", "employments.department",
        "employments.position", "employments.contractType",
        "employments.employeeType"
    })
    @Query("SELECT DISTINCT e FROM EmployeeEntity e")
    List<EmployeeEntity> findAllWithDetailsGraph();

    @EntityGraph(attributePaths = {"documentType", "gender", "maritalStatus"})
    @Query("""
    SELECT e FROM EmployeeEntity e
    WHERE LOWER(e.documentNumber)
        LIKE LOWER(CONCAT('%', COALESCE(:documentNumber, ''), '%'))
    AND LOWER(CONCAT(e.name, ' ', e.lastName))
        LIKE LOWER(CONCAT('%', COALESCE(:fullName, ''), '%'))
    """)
    Page<EmployeeEntity> findByFilters(
            @Param("documentNumber") String documentNumber,
            @Param("fullName") String fullName,
            Pageable pageable
    );

    @EntityGraph(attributePaths = {"documentType", "gender", "maritalStatus"})
    @Query("""
    SELECT e FROM EmployeeEntity e
    WHERE e.id IN :ids
    ORDER BY e.id
    """)
    List<EmployeeEntity> findByIdIn(@Param("ids") Collection<Long> ids);

    @Query("""
    SELECT COUNT(e) FROM EmployeeEntity e
    WHERE e.id IN :ids
    """)
    long countByIdIn(@Param("ids") Collection<Long> ids);
}
