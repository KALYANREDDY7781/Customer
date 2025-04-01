package com.myorg.customerManagement.controller;

import com.myorg.customerManagement.dto.ResponseDto;
import com.myorg.customerManagement.model.Customer;
import com.myorg.customerManagement.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    private CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService){
        this.customerService = customerService;
    }

    @PostMapping
    public ResponseEntity<Map<String, String>> addCustomer(@RequestBody Customer customer){
        customerService.save(customer);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Customer created successfully");
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
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

    @GetMapping("/fetch/{customerId}")
    public ResponseEntity<ResponseDto> getCustomerDetails(@PathVariable int customerId){
        ResponseDto responseDto = customerService.fetchCustomerDetails(customerId);
        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }
}
