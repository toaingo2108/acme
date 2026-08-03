package com.saas.acme.mapper;

import com.saas.acme.dto.request.PlanRequest;
import com.saas.acme.dto.response.PlanResponse;
import com.saas.acme.entity.Plan;
import org.springframework.stereotype.Component;

@Component
public class PlanMapper {
    public Plan toEntity(PlanRequest req) {
        Plan plan = new Plan();
        plan.setName(req.name());
        plan.setSku(req.sku());
        plan.setCategory(req.category());
        plan.setPriceCents(req.priceCents());
        return plan;
    }

    public PlanResponse toResponse(Plan p) {
        return new PlanResponse(
                p.getId(),
                p.getName(),
                p.getSku(),
                p.getCategory(),
                p.getPriceCents(),
                p.isActive(),
                p.getCreatedAt()
        );
    }
}
