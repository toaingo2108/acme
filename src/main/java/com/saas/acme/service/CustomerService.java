package com.saas.acme.service;

import com.saas.acme.dto.request.CustomerRequest;
import com.saas.acme.dto.response.CustomerResponse;
import com.saas.acme.entity.Customer;
import com.saas.acme.exception.DuplicateResourceException;
import com.saas.acme.exception.ResourceNotFoundException;
import com.saas.acme.mapper.CustomerMapper;
import com.saas.acme.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    @Transactional(readOnly = true)
    public List<CustomerResponse> findAll() {
        return customerRepository.findAll()
                .stream()
                .map(customerMapper::toResponse)
                .toList();
    }

    @Transactional(readOnly = true)
    public CustomerResponse findById(Long id) {
        Customer c = customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy customer id = " + id));
        return customerMapper.toResponse(c);
    }

    @Transactional
    public CustomerResponse create(CustomerRequest req) {
        if (customerRepository.existsByEmail(req.email())) {
            throw new DuplicateResourceException("Email da ton tai: " + req.email());
        }
        Customer entity = customerMapper.toEntity(req);
        Customer saved = customerRepository.save(entity);
        return customerMapper.toResponse(saved);
    }

    @Transactional
    public void delete(Long id) {
        if (!customerRepository.existsById(id)) {
            throw new ResourceNotFoundException("Khong tim thay customer id = " + id);
        }
        customerRepository.deleteById(id);
    }
}
