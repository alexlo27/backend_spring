package com.alexlo.msvc_employee.schedule.service;

import com.alexlo.msvc_employee.schedule.dto.request.CreateScheduleReviewRequestDTO;
import com.alexlo.msvc_employee.schedule.dto.request.ReviewActionRequestDTO;
import com.alexlo.msvc_employee.schedule.dto.response.ScheduleReviewDetailDTO;
import com.alexlo.msvc_employee.schedule.maper.ScheduleReviewMapper;
import com.alexlo.msvc_employee.schedule.model.ReviewActionEntity;
import com.alexlo.msvc_employee.schedule.model.ReviewerTypeEntity;
import com.alexlo.msvc_employee.schedule.model.ReviewStatusEntity;
import com.alexlo.msvc_employee.schedule.model.ScheduleReviewEntity;
import com.alexlo.msvc_employee.schedule.repository.ReviewActionRepository;
import com.alexlo.msvc_employee.schedule.repository.ReviewerTypeRepository;
import com.alexlo.msvc_employee.schedule.repository.ReviewStatusRepository;
import com.alexlo.msvc_employee.schedule.repository.ScheduleReviewRepository;
import com.alexlo.msvc_employee.shared.exception.BusinessRuleException;
import com.alexlo.msvc_employee.shared.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ScheduleReviewServiceImpl implements ScheduleReviewService {

    @Autowired
    private ScheduleReviewRepository scheduleReviewRepository;

    @Autowired
    private ReviewStatusRepository reviewStatusRepository;

    @Autowired
    private ReviewActionRepository reviewActionRepository;

    @Autowired
    private ReviewerTypeRepository reviewerTypeRepository;

    @Autowired
    private ScheduleReviewMapper mapper;

    @Value("${app.approval.max-levels}")
    private int maxLevels;

    @Transactional
    @Override
    public ScheduleReviewDetailDTO submit(CreateScheduleReviewRequestDTO dto) {
        ReviewStatusEntity pendingStatus = getStatusByCode("PENDING");

        ScheduleReviewEntity entity = ScheduleReviewEntity.builder()
                .employeeId(dto.employeeId())
                .schedulePeriodId(dto.schedulePeriodId())
                .currentCycle(1)
                .currentLevel(1)
                .status(pendingStatus)
                .submittedAt(LocalDateTime.now())
                .build();

        return mapper.toDetailResponse(scheduleReviewRepository.save(entity));
    }

    @Transactional
    @Override
    public ScheduleReviewDetailDTO approve(Long reviewId, ReviewActionRequestDTO dto) {
        ScheduleReviewEntity review = getReviewById(reviewId);
        validateNotTerminal(review);

        ReviewActionEntity action = buildAction(review, dto, "APPROVED");
        reviewActionRepository.save(action);

        if (review.getCurrentLevel() >= maxLevels) {
            ReviewStatusEntity approvedStatus = getStatusByCode("APPROVED");
            review.setStatus(approvedStatus);
            review.setCompletedAt(LocalDateTime.now());
        } else {
            review.setCurrentLevel(review.getCurrentLevel() + 1);
        }

        return mapper.toDetailResponse(scheduleReviewRepository.save(review));
    }

    @Transactional
    @Override
    public ScheduleReviewDetailDTO returnReview(Long reviewId, ReviewActionRequestDTO dto) {
        ScheduleReviewEntity review = getReviewById(reviewId);
        validateNotTerminal(review);

        ReviewActionEntity action = buildAction(review, dto, "RETURNED");
        reviewActionRepository.save(action);

        ReviewStatusEntity returnedStatus = getStatusByCode("RETURNED");
        review.setStatus(returnedStatus);
        review.setCurrentCycle(review.getCurrentCycle() + 1);
        review.setCurrentLevel(1);

        return mapper.toDetailResponse(scheduleReviewRepository.save(review));
    }

    @Transactional
    @Override
    public ScheduleReviewDetailDTO reject(Long reviewId, ReviewActionRequestDTO dto) {
        ScheduleReviewEntity review = getReviewById(reviewId);
        validateNotTerminal(review);

        ReviewActionEntity action = buildAction(review, dto, "REJECTED");
        reviewActionRepository.save(action);

        ReviewStatusEntity rejectedStatus = getStatusByCode("REJECTED");
        review.setStatus(rejectedStatus);
        review.setCompletedAt(LocalDateTime.now());

        return mapper.toDetailResponse(scheduleReviewRepository.save(review));
    }

    @Transactional(readOnly = true)
    @Override
    public ScheduleReviewDetailDTO findById(Long reviewId) {
        return mapper.toDetailResponse(getReviewById(reviewId));
    }

    @Transactional(readOnly = true)
    @Override
    public List<ScheduleReviewDetailDTO> findByEmployeeAndPeriod(Long employeeId, Long schedulePeriodId) {
        return scheduleReviewRepository
                .findByEmployeeIdAndSchedulePeriodId(employeeId, schedulePeriodId)
                .map(mapper::toDetailResponse)
                .stream()
                .toList();
    }

    private void validateNotTerminal(ScheduleReviewEntity review) {
        String code = review.getStatus().getCode();
        if ("APPROVED".equals(code) || "REJECTED".equals(code)) {
            throw new BusinessRuleException("No se puede modificar: la revisión ya está " + code.toLowerCase());
        }
    }

    private ReviewActionEntity buildAction(ScheduleReviewEntity review, ReviewActionRequestDTO dto, String action) {
        ReviewerTypeEntity reviewerType = getReviewerTypeById(dto.reviewerTypeId());
        if (!reviewerType.getLevel().equals(review.getCurrentLevel())) {
            throw new BusinessRuleException(
                    "Este nivel requiere un revisor de tipo " + getReviewerTypeNameByLevel(review.getCurrentLevel()));
        }
        return ReviewActionEntity.builder()
                .scheduleReview(review)
                .cycle(review.getCurrentCycle())
                .level(review.getCurrentLevel())
                .reviewerType(reviewerType)
                .reviewerEmployeeId(dto.reviewerEmployeeId())
                .actionStatus(getStatusByCode(action))
                .comment(dto.comment())
                .actionAt(LocalDateTime.now())
                .build();
    }

    private ScheduleReviewEntity getReviewById(Long id) {
        return scheduleReviewRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Revisión de horario no encontrada"));
    }

    private ReviewStatusEntity getStatusByCode(String code) {
        return reviewStatusRepository.findByCodeIgnoreCase(code)
                .orElseThrow(() -> new NotFoundException("Estado de revisión no encontrado: " + code));
    }

    private ReviewerTypeEntity getReviewerTypeById(Long id) {
        return reviewerTypeRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Tipo de revisor no encontrado"));
    }

    private String getReviewerTypeNameByLevel(int level) {
        return reviewerTypeRepository.findByLevel(level)
                .map(ReviewerTypeEntity::getName)
                .orElse("Nivel " + level);
    }
}
