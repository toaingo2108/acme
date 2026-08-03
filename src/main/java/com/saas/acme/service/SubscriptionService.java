package com.saas.acme.service;

import com.saas.acme.dto.request.SubscriptionRequest;
import com.saas.acme.dto.response.SubscriptionResponse;
import com.saas.acme.entity.Customer;
import com.saas.acme.entity.Plan;
import com.saas.acme.entity.Subscription;
import com.saas.acme.exception.ResourceNotFoundException;
import com.saas.acme.mapper.SubscriptionMapper;
import com.saas.acme.repository.CustomerRepository;
import com.saas.acme.repository.PlanRepository;
import com.saas.acme.repository.SubscriptionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SubscriptionService {
    private final SubscriptionRepository subscriptionRepository;
    private final CustomerRepository customerRepository;
    private final PlanRepository planRepository;

    private final SubscriptionMapper subscriptionMapper;

    @Transactional(readOnly = true)
    public SubscriptionResponse findById(Long id) {
        Subscription sub = subscriptionRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Khong tim thay subscription id = " + id));
        return subscriptionMapper.toResponse(sub);
    }

    @Transactional(readOnly = true)
    public List<SubscriptionResponse> findAll() {
        List<Subscription> subs = subscriptionRepository
                .findAll();
        return subs.stream().map(subscriptionMapper::toResponse).toList();
    }

    @Transactional
    public SubscriptionResponse create(SubscriptionRequest req) {
        Customer customer = customerRepository.findById(req.customerId())
                .orElseThrow(() -> new ResourceNotFoundException("Khong tim thay customer id = " + req.customerId()));
        Plan plan = planRepository.findById(req.planId())
                .orElseThrow(() -> new ResourceNotFoundException("Khong tim thay plan id = " + req.planId()));

        Subscription sub = subscriptionMapper.toEntity(customer, plan);
        Subscription saved = subscriptionRepository.save(sub);
        return subscriptionMapper.toResponse(saved);
    }
}
