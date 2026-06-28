package com.alexlo.msvc_employee.schedule.model;

import com.alexlo.msvc_employee.shared.audit.Auditable;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "schedule_reviews")
public class ScheduleReviewEntity extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "employee_id", nullable = false)
    private Long employeeId;

    @Column(name = "schedule_period_id", nullable = false)
    private Long schedulePeriodId;

    @Column(name = "current_cycle")
    private Integer currentCycle;

    @Column(name = "current_level")
    private Integer currentLevel;

    @ManyToOne
    @JoinColumn(name = "status_id")
    private ReviewStatusEntity status;

    @Column(name = "submitted_at")
    private LocalDateTime submittedAt;

    @Column(name = "completed_at")
    private LocalDateTime completedAt;

    @OneToMany(mappedBy = "scheduleReview", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<ReviewActionEntity> actions = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ScheduleReviewEntity that)) return false;
        return id != null && id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    @Override
    public String toString() {
        return "ScheduleReviewEntity{" +
                "id=" + id +
                ", employeeId=" + employeeId +
                ", schedulePeriodId=" + schedulePeriodId +
                ", currentCycle=" + currentCycle +
                ", currentLevel=" + currentLevel +
                ", status=" + status +
                ", submittedAt=" + submittedAt +
                ", completedAt=" + completedAt +
                '}';
    }
}
