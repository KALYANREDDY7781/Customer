package com.myorg.customerManagement.service;

import com.myorg.customerManagement.dto.CardResponseDto;
import com.myorg.customerManagement.dto.ResponseDto;
import com.myorg.customerManagement.exception.CustomerNotFoundException;
import com.myorg.customerManagement.model.Customer;
import com.myorg.customerManagement.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService{

    private final CustomerRepository customerRepository;
    private final RestTemplate restTemplate;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository,RestTemplate restTemplate){
        this.customerRepository = customerRepository;
        this.restTemplate = restTemplate;
    }
    @Override
    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    @Override
    public Customer findById(int id) {
        Customer customer = customerRepository.findById(id);
        if(customer == null){
            throw new CustomerNotFoundException("Customer not found with ID: "+id);
        }
        return customer;
    }

    @Override
    public int save(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public int update(Customer customer) {
        Customer c1 = customerRepository.findById(customer.getId());
        if(c1 == null){
            throw new CustomerNotFoundException("Customer not found with ID: "+customer.getId());
        }
        return customerRepository.update(customer);
    }

    @Override
    public int delete(int id) {
        Customer customer = customerRepository.findById(id);
        if(customer == null){
            throw new CustomerNotFoundException("Customer not found with ID: "+id);
        }
        return customerRepository.delete(id);
    }

    @Override
    public ResponseDto fetchCustomerDetails(int id) {
        Customer customer = findById(id);
        if(customer == null){
            throw new CustomerNotFoundException("Customer not found with ID: "+id);
        }
        List<CardResponseDto> cards = fetchCardDetails(id);
        ResponseDto responseDto = new ResponseDto();
        responseDto.setCustomerId(id);
        responseDto.setEmail(customer.getEmail());
        responseDto.setPhone(customer.getPhone());
        responseDto.setFirst_name(customer.getFirst_name());
        responseDto.setLast_name(customer.getLast_name());
        responseDto.setAddress(customer.getAddress());
        if(!cards.isEmpty()){
            responseDto.setCardResponseDto(cards);
        }

        return responseDto;
    }

    public List<CardResponseDto> fetchCardDetails(int customerId){
        String url = "http://localhost:8080/cards/"+customerId;
        System.out.println(url);
        ResponseEntity<CardResponseDto[]> response = restTemplate.getForEntity(url,CardResponseDto[].class);
        if (response.getStatusCode() == HttpStatus.OK) {
            return Arrays.asList(response.getBody());
        } else {
            return List.of();  // Return empty list if no cards are found
        }
    }
}
