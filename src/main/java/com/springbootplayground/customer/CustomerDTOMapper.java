package com.springbootplayground.customer;

import org.springframework.stereotype.Service;

import java.util.function.Function;

/**
 * This class is functional programming when its implement the Function
 *
 * Mapper Function to map Customer to CustomerDTO
 */
@Service
public class CustomerDTOMapper implements Function<Customer, CustomerDTO> {

    @Override
    public CustomerDTO apply(Customer customer) {
        return new CustomerDTO(
                customer.getId(),
                customer.getName()
        );
    }
}
