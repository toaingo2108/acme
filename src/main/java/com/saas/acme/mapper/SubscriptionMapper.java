package com.saas.acme.mapper;

import com.saas.acme.dto.response.SubscriptionResponse;
import com.saas.acme.entity.Customer;
import com.saas.acme.entity.Plan;
import com.saas.acme.entity.Subscription;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SubscriptionMapper {
    private final CustomerMapper customerMapper;
    private final PlanMapper planMapper;

    public Subscription toEntity(Customer customer, Plan plan) {
        Subscription sub = new Subscription();
        sub.setCustomer(customer);
        sub.setPlan(plan);
        return sub;
    }

    public SubscriptionResponse toResponse(Subscription s) {
        return new SubscriptionResponse(
                s.getId(),
                customerMapper.toResponse(s.getCustomer()),
                planMapper.toResponse(s.getPlan()),
                s.getStatus(),
                s.getCurrentPeriodStart(),
                s.getCurrentPeriodEnd(),
                s.getCreatedAt()
        );
    }
}
