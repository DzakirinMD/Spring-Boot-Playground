package com.springbootplayground.customer.entity;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.time.LocalDate;
import java.time.Period;

@Entity
@Table(name = "customer")
@Data
public class Customer {

    @Id
    @SequenceGenerator(
            name = "customer_sequence",
            sequenceName = "customer_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "customer_sequence")
    private Long id;
    private String name;
    private String email;
    private LocalDate dob;
    @Transient
    @Getter(AccessLevel.NONE)
    private Integer age;

    @ManyToOne
    private CustomerCar customerCar;

    public Customer() {

    }

    public Customer(Long id, String name, String email, LocalDate dob, CustomerCar customerCar) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.dob = dob;
        this.customerCar = customerCar;
    }

    public Customer(String name, String email, LocalDate dob, CustomerCar customerCar) {
        this.name = name;
        this.email = email;
        this.dob = dob;
        this.customerCar = customerCar;
    }

    public Integer getAge() {
        return Period.between(this.dob, LocalDate.now()).getYears();
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
