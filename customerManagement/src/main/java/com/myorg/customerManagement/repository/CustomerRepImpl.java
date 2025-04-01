package com.myorg.customerManagement.repository;

import com.myorg.customerManagement.mapper.CustomerMapper;
import com.myorg.customerManagement.model.Customer;
import com.myorg.customerManagement.utility.QueryLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CustomerRepImpl implements CustomerRepository{

    private final JdbcTemplate jdbcTemplate;
    private final QueryLoader queryLoader;
    private final CustomerMapper customerMapper;

    @Autowired
    public CustomerRepImpl(JdbcTemplate jdbcTemplate,QueryLoader queryLoader,CustomerMapper customerMapper){
        this.jdbcTemplate = jdbcTemplate;
        this.queryLoader = queryLoader;
        this.customerMapper=customerMapper;
    }
    @Override
    public List<Customer> findAll() {
        String sql = queryLoader.getQuery("getAllCustomers");
        return jdbcTemplate.query(sql, customerMapper);
    }

    @Override
    public Customer findById(int id) {
        String sql = queryLoader.getQuery("getCustomerById");
        try{
            return jdbcTemplate.queryForObject(sql,customerMapper,id);
        }
        catch (IncorrectResultSizeDataAccessException e){
            return null;
        }

    }

    @Override
    public int save(Customer customer) {
        String sql = queryLoader.getQuery("createCustomer");
        return jdbcTemplate.update(sql,customer.getFirst_name(),customer.getLast_name(),customer.getEmail(),customer.getPhone(),customer.getAddress());
    }

    @Override
    public int update(Customer customer) {
        String sql = queryLoader.getQuery("updateCustomer");
        return jdbcTemplate.update(sql,customer.getFirst_name(),customer.getLast_name(),customer.getEmail(),customer.getPhone(),customer.getAddress(),customer.getId());
    }

    @Override
    public int delete(int id) {
        String sql = queryLoader.getQuery("deleteCustomerById");
        return jdbcTemplate.update(sql,id);
    }
}
