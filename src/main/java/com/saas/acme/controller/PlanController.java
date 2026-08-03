package com.saas.acme.controller;

import com.saas.acme.dto.request.PlanRequest;
import com.saas.acme.dto.response.ApiResponse;
import com.saas.acme.dto.response.PlanResponse;
import com.saas.acme.service.PlanService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/plans")
@RequiredArgsConstructor
public class PlanController {
    private final PlanService planService;

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<PlanResponse>> findById(@PathVariable Long id) {
        PlanResponse planResponse = planService.findById(id);
        return ResponseEntity.ok(ApiResponse.success(planResponse));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<PlanResponse>>> findAll() {
        List<PlanResponse> data = planService.findAll();
        return ResponseEntity.ok(ApiResponse.success(data));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<PlanResponse>> create(@Valid @RequestBody PlanRequest request) {
        PlanResponse data = planService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success(data));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Long id) {
        planService.delete(id);
        return ResponseEntity.ok(ApiResponse.success("Xoá thành công plan", null));
    }
}
