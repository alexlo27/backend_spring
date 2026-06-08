package com.alexlo.msvc_employee.employment.service;

import com.alexlo.msvc_employee.employment.dto.response.EmploymentDTO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class EmploymentQueryServiceImpl implements EmploymentQueryService {

    @Autowired
    private EntityManager entityManager;

    @Transactional(readOnly = true)
    @Override
    public Map<Long, EmploymentDTO> findCurrentByEmployeeIds(Collection<Long> employeeIds) {
        if (employeeIds == null || employeeIds.isEmpty()) {
            return Collections.emptyMap();
        }

        String jpql = """
            SELECT new com.alexlo.msvc_employee.employment.dto.response.EmploymentDTO(
                e.employee.id,
                d.id, d.name, d.code, d.description,
                p.id, p.name,
                ct.id, ct.code, ct.name,
                et.id, et.code, et.name
            )
            FROM EmploymentEntity e
            JOIN e.department d
            JOIN e.position p
            LEFT JOIN e.contractType ct
            LEFT JOIN e.employeeType et
            WHERE e.employee.id IN :employeeIds
            AND e.startDate <= CURRENT_DATE
            AND (e.endDate IS NULL OR e.endDate >= CURRENT_DATE)
            AND e.startDate = (
                SELECT MAX(e2.startDate)
                FROM EmploymentEntity e2
                WHERE e2.employee.id = e.employee.id
                AND e2.startDate <= CURRENT_DATE
                AND (e2.endDate IS NULL OR e2.endDate >= CURRENT_DATE)
            )
            """;

        TypedQuery<EmploymentDTO> query = entityManager.createQuery(jpql, EmploymentDTO.class);
        query.setParameter("employeeIds", employeeIds);

        return query.getResultList().stream()
                .collect(Collectors.toMap(EmploymentDTO::employeeId, dto -> dto));
    }

    @Transactional(readOnly = true)
    @Override
    public EmploymentDTO findCurrentByEmployeeId(Long employeeId) {
        return findCurrentByEmployeeIds(Collections.singletonList(employeeId))
                .get(employeeId);
    }

    @Transactional(readOnly = true)
    @Override
    public Set<Long> findCurrentEmployeeIdsByDepartment(Long departmentId) {
        String jpql = """
            SELECT DISTINCT e.employee.id
            FROM EmploymentEntity e
            WHERE e.department.id = :departmentId
            AND e.startDate <= CURRENT_DATE
            AND (e.endDate IS NULL OR e.endDate >= CURRENT_DATE)
            AND e.startDate = (
                SELECT MAX(e2.startDate)
                FROM EmploymentEntity e2
                WHERE e2.employee.id = e.employee.id
                AND e2.startDate <= CURRENT_DATE
                AND (e2.endDate IS NULL OR e2.endDate >= CURRENT_DATE)
            )
            """;

        TypedQuery<Long> query = entityManager.createQuery(jpql, Long.class);
        query.setParameter("departmentId", departmentId);

        return query.getResultStream().collect(Collectors.toSet());
    }
}
