package com.saas.acme.repository;

import com.saas.acme.entity.Plan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlanRepository extends JpaRepository<Plan, Long> {
    boolean existsBySku(String sku);
}
