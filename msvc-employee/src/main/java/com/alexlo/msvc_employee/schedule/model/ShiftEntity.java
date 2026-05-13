package com.alexlo.msvc_employee.schedule.model;

import com.alexlo.msvc_employee.shared.audit.Auditable;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "shifts")
public class ShiftEntity extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false)
    private String name;

    @NotBlank
    @Column(nullable = false)
    private String code;

    private String abbreviation;

    @NotNull
    @Column(name = "start_time", nullable = false)
    private LocalTime startTime;

    @NotNull
    @Column(name = "end_time", nullable = false)
    private LocalTime endTime;

    @Column(name = "start_break")
    private LocalTime startBreak;

    @Column(name = "end_break")
    private LocalTime endBreak;

    private Number tolerance;

    @Column(name = "check_in_start_time")
    private LocalTime checkInStartTime; // desde que hora se considera una marcacion de enttrada

    @Column(name = "check_in_end_time")
    private LocalTime checkInEndTime; // desde que hora se considera una marcacion de salida final del dia

    @Column(name = "check_in_end_break")
    private LocalTime checkInEndBreak; // desde que hora se considera una marcacion de entrada del break

    @Column(name = "late_check_in_limit")
    private LocalTime lateCheckInLimit; // hasta que hora de ingreso se considera tardanza

    @Column(name = "is_active", nullable = false)
    private Boolean isActive = true;

    @Override
    public boolean equals(Object o) {
        if( this == o) return true;
        if( !(o instanceof ShiftEntity shiftEntity)) return false;
        return id != null && id.equals(shiftEntity.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    @Override
    public String toString() {
        return "ShiftEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", abbreviation='" + abbreviation + '\'' +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", startBreak=" + startBreak +
                ", endBreak=" + endBreak +
                ", tolerance=" + tolerance +
                ", checkInStartTime=" + checkInStartTime +
                ", checkInEndTime=" + checkInEndTime +
                ", checkInEndBreak=" + checkInEndBreak +
                ", lateCheckInLimit=" + lateCheckInLimit +
                ", isActive=" + isActive +
                '}';
    }
}
