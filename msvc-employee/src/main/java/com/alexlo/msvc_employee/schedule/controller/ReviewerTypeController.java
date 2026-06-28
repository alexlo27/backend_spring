package com.alexlo.msvc_employee.schedule.controller;

import com.alexlo.msvc_employee.schedule.dto.request.CreateReviewerTypeRequestDTO;
import com.alexlo.msvc_employee.schedule.dto.request.UpdateReviewerTypeRequestDTO;
import com.alexlo.msvc_employee.schedule.dto.response.ReviewerTypeResponseDTO;
import com.alexlo.msvc_employee.schedule.service.ReviewerTypeService;
import com.alexlo.msvc_employee.shared.mapper.PageResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reviewer-type")
public class ReviewerTypeController {

    @Autowired
    private ReviewerTypeService reviewerTypeService;

    @PostMapping
    public ResponseEntity<ReviewerTypeResponseDTO> save(@Valid @RequestBody CreateReviewerTypeRequestDTO dto) {
        return ResponseEntity.ok(reviewerTypeService.create(dto));
    }

    @PatchMapping
    public ResponseEntity<ReviewerTypeResponseDTO> update(@Valid @RequestBody UpdateReviewerTypeRequestDTO dto) {
        return ResponseEntity.ok(reviewerTypeService.update(dto));
    }

    @GetMapping("/all")
    public ResponseEntity<List<ReviewerTypeResponseDTO>> all() {
        return ResponseEntity.ok(reviewerTypeService.all());
    }

    @GetMapping
    public ResponseEntity<PageResponse<ReviewerTypeResponseDTO>> all(@RequestParam(required = false) String name, Pageable pageable) {
        return ResponseEntity.ok(reviewerTypeService.all(name, pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReviewerTypeResponseDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(reviewerTypeService.findById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        reviewerTypeService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
