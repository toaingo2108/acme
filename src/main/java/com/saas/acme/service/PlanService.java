package com.saas.acme.service;

import com.saas.acme.dto.request.PlanRequest;
import com.saas.acme.dto.response.PlanResponse;
import com.saas.acme.entity.Plan;
import com.saas.acme.exception.DuplicateResourceException;
import com.saas.acme.exception.ResourceNotFoundException;
import com.saas.acme.mapper.PlanMapper;
import com.saas.acme.repository.PlanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PlanService {
    private final PlanRepository planRepository;
    private final PlanMapper planMapper;

    @Transactional(readOnly = true)
    public PlanResponse findById(Long id) {
        Plan plan = planRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Khong tim thay plan id = " + id));
        return planMapper.toResponse(plan);
    }

    @Transactional(readOnly = true)
    public List<PlanResponse> findAll() {
        List<Plan> plans = planRepository.findAll();
        return plans.stream().map(planMapper::toResponse).toList();
    }

    @Transactional
    public PlanResponse create(PlanRequest req) {
        if (planRepository.existsBySku(req.sku())) {
            throw new DuplicateResourceException("Sku da ton tai");
        }
        Plan plan = planMapper.toEntity(req);
        Plan saved = planRepository.save(plan);
        return planMapper.toResponse(saved);
    }

    @Transactional
    public void delete(Long id) {
        if (!planRepository.existsById(id)) {
            throw new ResourceNotFoundException("Khong tim thay plan id = " + id);
        }
        planRepository.deleteById(id);
    }
}
