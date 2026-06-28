package com.alexlo.msvc_employee.schedule.model;

import com.alexlo.msvc_employee.shared.audit.Auditable;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "reviewer_types")
public class ReviewerTypeEntity extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String code;

    @Column(nullable = false)
    @NotBlank
    private String name;

    @Column(nullable = false)
    @NotNull
    private Integer level;

    @Column(name = "is_active")
    private Boolean isActive = true;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ReviewerTypeEntity that)) return false;
        return id != null && id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    @Override
    public String toString() {
        return "ReviewerTypeEntity{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", level=" + level +
                ", isActive=" + isActive +
                '}';
    }
}
