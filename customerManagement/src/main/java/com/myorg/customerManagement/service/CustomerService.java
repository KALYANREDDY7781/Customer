package com.myorg.customerManagement.service;

import com.myorg.customerManagement.model.Customer;

import java.util.List;

public interface CustomerService {

    List<Customer> findAll();
    Customer findById(int id);
    int save(Customer customer);
    int update(Customer customer);
    int delete(int id);
}
