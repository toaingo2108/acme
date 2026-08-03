package com.saas.acme.mapper;

import com.saas.acme.dto.request.CustomerRequest;
import com.saas.acme.dto.response.CustomerResponse;
import com.saas.acme.entity.Customer;
import org.springframework.stereotype.Component;

// không dùng static vì có trường hợp cần inject
@Component
public class CustomerMapper {
    // Khi tạo mới
    public Customer toEntity(CustomerRequest req) {
        Customer c = new Customer();
        c.setName(req.name());
        c.setEmail(req.email());
        return c;
    }

    public CustomerResponse toResponse(Customer c) {
        return new CustomerResponse(
                c.getId(),
                c.getName(),
                c.getEmail(),
                c.getStatus(),
                c.getCreatedAt()
        );
    }
}
