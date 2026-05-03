package com.alexlo.msvc_employee.organization.model;

import com.alexlo.msvc_employee.shared.audit.Auditable;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table( name = "departments")
public class DepartmentEntity extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(unique = true)
    private String code;

    private String description;

    @ManyToOne
    @JoinColumn(name = "parent_id")
    private DepartmentEntity parent;

    @Column(name = "is_active", nullable = false)
    private Boolean isActive = true;

    @OneToMany(mappedBy = "parent", fetch = FetchType.LAZY)
    private List<DepartmentEntity> children;

    @Override
    public boolean equals(Object o) {
        if( this == o) return true;
        if( !(o instanceof DepartmentEntity department)) return false;
        return id != null && id.equals(department.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    @Override
    public String toString() {
        return "DepartmentEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", description='" + description + '\'' +
                ", parent=" + parent +
                ", isActive=" + isActive +
                '}';
    }
}
