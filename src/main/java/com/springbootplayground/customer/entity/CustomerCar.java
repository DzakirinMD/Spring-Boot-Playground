package com.springbootplayground.customer.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

/**
 * This customer car is show customer is using which car model
 */
@Entity
@Table(name = "customercar")
@Data
public class CustomerCar {

    @Id
    @SequenceGenerator(
            name = "customercar_sequence",
            sequenceName = "customercar_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "customercar_sequence")
    private Long id;

    private String make;
    private String model;


    @OneToMany(mappedBy = "customerCar")
    private Set<Customer> customers = new HashSet<>();

    public CustomerCar() {

    }

    public CustomerCar(String make, String model) {
        this.make = make;
        this.model = model;
    }
}
