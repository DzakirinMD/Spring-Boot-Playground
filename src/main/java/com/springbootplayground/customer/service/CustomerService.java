package com.springbootplayground.customer.service;

import com.springbootplayground.customer.entity.dto.CustomerDTO;
import com.springbootplayground.customer.entity.mapper.CustomerDTOMapper;
import com.springbootplayground.customer.exception.CustomerResourceNotFoundException;
import com.springbootplayground.customer.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service("customerService")
public class CustomerService {

    private final CustomerRepository customerRepository;

    private final CustomerDTOMapper customerDTOMapper;

    @Autowired
    public CustomerService(CustomerRepository customerRepository, CustomerDTOMapper customerDTOMapper) {
        this.customerRepository = customerRepository;
        this.customerDTOMapper = customerDTOMapper;
    }

    // line 24 will capp the mapper functional programming to auto map object to dto
    public List<CustomerDTO> getAllCustomers() {
        return customerRepository
                .findAll()
                .stream()
                // send the result to mapper for mapping the Customer to CustomerDTO
                .map(customerDTOMapper)
                .collect(Collectors.toList());
    }

    public Optional<CustomerDTO> getCustomer(Long id) {
        return Optional.ofNullable(customerRepository
                .findById(id)
                .map(customerDTOMapper)
                .orElseThrow(() -> new CustomerResourceNotFoundException("No customer with id : " + id))
        );
    }
}
