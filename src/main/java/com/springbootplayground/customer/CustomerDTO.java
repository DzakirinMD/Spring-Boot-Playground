package com.springbootplayground.customer;

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
}
