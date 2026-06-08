package com.alexlo.msvc_employee.employment.model;

import com.alexlo.msvc_employee.employment.model.ContractTypeEntity;
import com.alexlo.msvc_employee.employment.model.EmployeeTypeEntity;
import com.alexlo.msvc_employee.employee.model.EmployeeEntity;
import com.alexlo.msvc_employee.organization.model.DepartmentEntity;
import com.alexlo.msvc_employee.organization.model.PositionEntity;
import com.alexlo.msvc_employee.shared.audit.Auditable;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "Employments")
public class EmploymentEntity extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "employee_id", nullable = false)
    private EmployeeEntity employee;

    @ManyToOne(optional = false)
    @JoinColumn(name = "department_id", nullable = false)
    private DepartmentEntity department;

    @ManyToOne(optional = false)
    @JoinColumn(name = "position_id", nullable = false)
    private PositionEntity position;

    @ManyToOne(optional = true)
    @JoinColumn(name = "employee_type_id", nullable = true)
    private EmployeeTypeEntity employeeType;

    @ManyToOne(optional = true)
    @JoinColumn(name = "contract_type_id", nullable = true)
    private ContractTypeEntity contractType;

    private LocalDate startDate;
    private LocalDate endDate;

    private BigDecimal salary;

    private Boolean isActive;

    @Override
    public boolean equals(Object o) {
        if( this == o) return true;
        if( !(o instanceof EmploymentEntity employment)) return false;
        return id != null && id.equals(employment.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    @Override
    public String toString() {
        return "EmploymentEntity{" +
                "id=" + id +
                ", employeeId=" + employee.getId() +
                ", department=" + department +
                ", position=" + position +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", salary=" + salary +
                ", isActive=" + isActive +
                '}';
    }
}
