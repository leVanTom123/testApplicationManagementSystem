package com.homework.appointmentsManagerSystem.repositories;

import com.homework.appointmentsManagerSystem.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
