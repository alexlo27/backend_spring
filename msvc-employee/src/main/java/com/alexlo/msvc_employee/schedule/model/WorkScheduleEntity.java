package com.alexlo.msvc_employee.schedule.model;

import com.alexlo.msvc_employee.employee.model.EmployeeEntity;
import com.alexlo.msvc_employee.shared.audit.Auditable;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "work_schedules")
public class WorkScheduleEntity extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "schedule_period_id", nullable = false)
    private SchedulePeriodEntity schedulePeriod;

    @ManyToOne(optional = false)
    @JoinColumn(name = "employee_id", nullable = false)
    private EmployeeEntity employee;

    @ManyToOne
    @JoinColumn(name = "shift_id")
    private ShiftEntity shift;

    @NotNull
    private LocalDate date;

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

    @Column(name = "check_in_time")
    private LocalTime checkInTime;

    @Column(name = "check_out_time")
    private LocalTime checkOutTime;

    @Column(name = "break_check_out_time")
    private LocalTime breakCheckOutTime;

    @Column(name = "break_check_in_time")
    private LocalTime breakCheckInTime;

    private Integer tolerance;

    @Override
    public boolean equals(Object o) {
        if( this == o) return true;
        if( !(o instanceof WorkScheduleEntity workSchedule)) return false;
        return id != null && id.equals(workSchedule.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    @Override
    public String toString() {
        return "WorkScheduleEntity{" +
                "id=" + id +
                ", schedulePeriod=" + schedulePeriod +
                ", employee=" + employee +
                ", shift=" + shift +
                ", date=" + date +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", startBreak=" + startBreak +
                ", endBreak=" + endBreak +
                ", checkInTime=" + checkInTime +
                ", checkOutTime=" + checkOutTime +
                ", breakCheckOutTime=" + breakCheckOutTime +
                ", breakCheckInTime=" + breakCheckInTime +
                ", tolerance=" + tolerance +
                '}';
    }
}
