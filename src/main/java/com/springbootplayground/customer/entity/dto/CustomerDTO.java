package com.springbootplayground.customer.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * DTO should contain only the field to be exposed to client
 */

@Data
@AllArgsConstructor
public class CustomerDTO {
    private Long id;
    private String name;
    private String customerDTO;
}
