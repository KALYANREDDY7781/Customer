package com.myorg.customerManagement.repository;

import com.myorg.customerManagement.model.Customer;

import java.util.List;

public interface CustomerRepository {

    List<Customer> findAll();
    Customer findById(int id);
    int save(Customer customer);
    int update(Customer customer);
    int delete(int id);
}
