package com.alexlo.msvc_employee.schedule.controller;

import com.alexlo.msvc_employee.schedule.dto.request.CreateScheduleReviewRequestDTO;
import com.alexlo.msvc_employee.schedule.dto.request.ReviewActionRequestDTO;
import com.alexlo.msvc_employee.schedule.dto.response.ScheduleReviewDetailDTO;
import com.alexlo.msvc_employee.schedule.service.ScheduleReviewService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/schedule-review")
public class ScheduleReviewController {

    @Autowired
    private ScheduleReviewService scheduleReviewService;

    @PostMapping("/submit")
    public ResponseEntity<ScheduleReviewDetailDTO> submit(@Valid @RequestBody CreateScheduleReviewRequestDTO dto) {
        return ResponseEntity.ok(scheduleReviewService.submit(dto));
    }

    @PostMapping("/{id}/approve")
    public ResponseEntity<ScheduleReviewDetailDTO> approve(@PathVariable Long id, @Valid @RequestBody ReviewActionRequestDTO dto) {
        return ResponseEntity.ok(scheduleReviewService.approve(id, dto));
    }

    @PostMapping("/{id}/return")
    public ResponseEntity<ScheduleReviewDetailDTO> returnReview(@PathVariable Long id, @Valid @RequestBody ReviewActionRequestDTO dto) {
        return ResponseEntity.ok(scheduleReviewService.returnReview(id, dto));
    }

    @PostMapping("/{id}/reject")
    public ResponseEntity<ScheduleReviewDetailDTO> reject(@PathVariable Long id, @Valid @RequestBody ReviewActionRequestDTO dto) {
        return ResponseEntity.ok(scheduleReviewService.reject(id, dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ScheduleReviewDetailDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(scheduleReviewService.findById(id));
    }

    @GetMapping("/by-employee-period")
    public ResponseEntity<List<ScheduleReviewDetailDTO>> findByEmployeeAndPeriod(
            @RequestParam Long employeeId, @RequestParam Long schedulePeriodId) {
        return ResponseEntity.ok(scheduleReviewService.findByEmployeeAndPeriod(employeeId, schedulePeriodId));
    }
}
