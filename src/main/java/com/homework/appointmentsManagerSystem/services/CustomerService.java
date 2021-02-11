package com.homework.appointmentsManagerSystem.services;

import com.homework.appointmentsManagerSystem.entities.Customer;
import com.homework.appointmentsManagerSystem.repositories.CustomerRepository;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public void saveCustomer(Customer customer) {
        customerRepository.save(customer);
    }

}
