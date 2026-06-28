package com.alexlo.msvc_employee.schedule.controller;

import com.alexlo.msvc_employee.schedule.dto.request.CreateReviewStatusRequestDTO;
import com.alexlo.msvc_employee.schedule.dto.request.UpdateReviewStatusRequestDTO;
import com.alexlo.msvc_employee.schedule.dto.response.ReviewStatusResponseDTO;
import com.alexlo.msvc_employee.schedule.service.ReviewStatusService;
import com.alexlo.msvc_employee.shared.mapper.PageResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/review-status")
public class ReviewStatusController {

    @Autowired
    private ReviewStatusService reviewStatusService;

    @PostMapping
    public ResponseEntity<ReviewStatusResponseDTO> save(@Valid @RequestBody CreateReviewStatusRequestDTO dto) {
        return ResponseEntity.ok(reviewStatusService.create(dto));
    }

    @PatchMapping
    public ResponseEntity<ReviewStatusResponseDTO> update(@Valid @RequestBody UpdateReviewStatusRequestDTO dto) {
        return ResponseEntity.ok(reviewStatusService.update(dto));
    }

    @GetMapping("/all")
    public ResponseEntity<List<ReviewStatusResponseDTO>> all() {
        return ResponseEntity.ok(reviewStatusService.all());
    }

    @GetMapping
    public ResponseEntity<PageResponse<ReviewStatusResponseDTO>> all(@RequestParam(required = false) String name, Pageable pageable) {
        return ResponseEntity.ok(reviewStatusService.all(name, pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReviewStatusResponseDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(reviewStatusService.findById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        reviewStatusService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
