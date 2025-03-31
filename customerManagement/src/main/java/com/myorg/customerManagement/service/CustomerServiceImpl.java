package com.myorg.customerManagement.service;

import com.myorg.customerManagement.model.Customer;
import com.myorg.customerManagement.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService{

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository){
        this.customerRepository = customerRepository;
    }
    @Override
    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    @Override
    public Customer findById(int id) {
        return customerRepository.findById(id);
    }

    @Override
    public int save(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public int update(Customer customer) {
        return customerRepository.update(customer);
    }

    @Override
    public int delete(int id) {
        return customerRepository.delete(id);
    }
}
