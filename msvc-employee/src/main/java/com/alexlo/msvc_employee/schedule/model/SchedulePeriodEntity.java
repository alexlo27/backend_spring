package com.alexlo.msvc_employee.schedule.model;

import com.alexlo.msvc_employee.shared.audit.Auditable;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "schedule_periods")
public class SchedulePeriodEntity extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 7)
    @NotBlank
    @Pattern(
            regexp = "^(0[1-9]|1[0-2])/\\d{4}$",
            message = "El período debe tener el formato MM/YYYY"
    )
    private String period;

    @Column(name = "is_active")
    private Boolean isActive = true;

    @Override
    public boolean equals(Object o) {
        if( this == o) return true;
        if( !(o instanceof SchedulePeriodEntity schedulePeriod)) return false;
        return id != null && id.equals(schedulePeriod.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    @Override
    public String toString() {
        return "SchedulePeriodEntity{" +
                "id=" + id +
                ", period='" + period + '\'' +
                ", isActive=" + isActive +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}