package com.myorg.customerManagement.service;

import com.myorg.customerManagement.model.Customer;
import com.myorg.customerManagement.repository.CustomerRepository;
import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CustomerServiceTests {

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private CustomerServiceImpl customerService;

    private AutoCloseable closeable;

    @BeforeEach
    void setUp() {
        closeable = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void tearDown() throws Exception {
        closeable.close();
    }

    @Test
    void testSaveCustomer() {
        Customer customer = new Customer();
        customer.setId(14);
        customer.setFirst_name("Iyer");
        customer.setLast_name("Shreyas");
        customer.setAddress("Punjab");
        customer.setPhone("46");
        customer.setEmail("shreyas@gmail.com");
        when(customerRepository.save(customer)).thenReturn(customer.getId()); // Return the full object

        int res = customerService.save(customer); // Match return type

        assertEquals(14, res);
        verify(customerRepository).save(customer);
    }
}
