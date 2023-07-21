package com.springbootplayground.customer.repository;

import com.springbootplayground.customer.entity.CustomerCar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerCarRepository extends JpaRepository<CustomerCar, Long> {
}
