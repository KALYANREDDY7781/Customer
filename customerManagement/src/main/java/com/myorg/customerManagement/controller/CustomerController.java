package com.myorg.customerManagement.controller;

import com.myorg.customerManagement.model.Customer;
import com.myorg.customerManagement.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    private CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService){
        this.customerService = customerService;
    }

    @PostMapping
    public String addCustomer(@RequestBody Customer customer){
        customerService.save(customer);
        return "Customer created successfully";
    }

    @PutMapping
    public String updateCustomer(@RequestBody Customer customer) {
        customerService.update(customer);
        return "Customer updated successfully!";
    }

    @DeleteMapping("/{id}")
    public String deleteCustomer(@PathVariable int id) {
        customerService.delete(id);
        return "Customer deleted successfully!";
    }

    @GetMapping("/{id}")
    public Customer getCustomerById(@PathVariable int id) {
        return customerService.findById(id);
    }

    @GetMapping
    public List<Customer> getAllCustomers() {
        return customerService.findAll();
    }

    @GetMapping("/{customerId}/exists")
    public boolean checkCustomerExists(@PathVariable int customerId) {
        boolean exists = true;
        Customer customer = customerService.findById(customerId);
        if(customer==null){
            exists = false;
        }
        return exists;
    }
}
