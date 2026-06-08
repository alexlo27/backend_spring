package com.alexlo.msvc_employee.employee.model;

import com.alexlo.msvc_employee.catalog.model.DocumentTypeEntity;
import com.alexlo.msvc_employee.catalog.model.GenderEntity;
import com.alexlo.msvc_employee.catalog.model.MaritalStatusEntity;
import com.alexlo.msvc_employee.employment.model.EmploymentEntity;
import com.alexlo.msvc_employee.shared.audit.Auditable;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "employees")
public class EmployeeEntity  extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @NotBlank
    private String name;

    @Column(name = "last_name", nullable = false)
    @NotBlank
    private String lastName;

    @ManyToOne(optional = false)
    @JoinColumn(name = "document_type_id", nullable = false)
    @NotNull
    private DocumentTypeEntity documentType;

    @Column(name = "document_number", nullable = false, unique = true)
    @NotBlank
    private String documentNumber;

    @Column(name = "birth_date")
    private LocalDate birthDate;

    @Column(nullable = false, unique = true)
    @NotBlank
    @Email
    private String email;

    private String phone;

    private String address;

    @ManyToOne(optional = false)
    @JoinColumn(name = "gender_id", nullable = false)
    private GenderEntity gender;

    @ManyToOne(optional = false)
    @JoinColumn(name = "marital_status_id", nullable = false)
    private MaritalStatusEntity maritalStatus;

    @Column(name = "is_active", nullable = false)
    private Boolean isActive = true;

    @JsonIgnore
    @OneToMany(mappedBy = "employee")
    private List<EmploymentEntity> employments;

    @Override
    public boolean equals(Object o) {
        if( this == o) return true;
        if( !(o instanceof EmployeeEntity employee)) return false;
        return id != null && id.equals(employee.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", documentType=" + documentType +
                ", documentNumber='" + documentNumber + '\'' +
                ", birthDate=" + birthDate +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                ", gender=" + gender +
                ", isActive=" + isActive +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
