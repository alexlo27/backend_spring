package com.alexlo.msvc_employee.schedule.model;

import com.alexlo.msvc_employee.shared.audit.Auditable;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "review_actions")
public class ReviewActionEntity extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "schedule_review_id", nullable = false)
    private ScheduleReviewEntity scheduleReview;

    @Column(nullable = false)
    private Integer cycle;

    @Column(nullable = false)
    private Integer level;

    @ManyToOne(optional = false)
    @JoinColumn(name = "reviewer_type_id", nullable = false)
    private ReviewerTypeEntity reviewerType;

    @Column(name = "reviewer_employee_id")
    private Long reviewerEmployeeId;

    @ManyToOne
    @JoinColumn(name = "action_status_id")
    private ReviewStatusEntity actionStatus;

    @Column(columnDefinition = "TEXT")
    private String comment;

    @Column(name = "action_at", nullable = false)
    private LocalDateTime actionAt;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ReviewActionEntity that)) return false;
        return id != null && id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    @Override
    public String toString() {
        return "ReviewActionEntity{" +
                "id=" + id +
                ", cycle=" + cycle +
                ", level=" + level +
                ", reviewerType=" + reviewerType +
                ", actionStatus=" + actionStatus +
                ", actionAt=" + actionAt +
                '}';
    }
}
