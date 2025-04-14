package com.myorg.address.repository;

import com.myorg.address.mapper.AddressMapper;
import com.myorg.address.model.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class AddressRepositoryImpl implements AddressRepository{

    private final NamedParameterJdbcTemplate jdbcTemplate;
    private final AddressMapper addressMapper;

    @Autowired
    public AddressRepositoryImpl(NamedParameterJdbcTemplate jdbcTemplate, AddressMapper addressMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.addressMapper = addressMapper;
    }


    @Override
    public int create(Address address) {
        String sql = "insert into address (customer_id,address_line1,city,state,zip_code,type,is_default," +
                "created_at,updated_at) values (:customerId,:addressLine1,:city,:state,:zipcode,:type," +
                ":isDefault,NOW(),NOW())";
        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("customerId",address.getCustomerId());
        map.addValue("addressLine1",address.getAddressLine1());
        map.addValue("city",address.getCity());
        map.addValue("zipcode",address.getZipCode());
        map.addValue("state",address.getState());
        map.addValue("type",address.getType());
        map.addValue("isDefault",address.isDefault());
        return jdbcTemplate.update(sql,map);
    }

    @Override
    public int update(Address address, int customerId) {
        String sql = "UPDATE address SET address_line1 = :addressLine1, " +
                "city = :city, " +
                "state = :state, " +
                "zip_code = :zipcode, " +
                "type = :type, " +
                "is_default = :isDefault, " +
                "updated_at = NOW() " +
                "WHERE customer_id = :customerId";

        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("customerId",address.getCustomerId());
        map.addValue("addressLine1",address.getAddressLine1());
        map.addValue("city",address.getCity());
        map.addValue("zipcode",address.getZipCode());
        map.addValue("state",address.getState());
        map.addValue("type",address.getType());
        map.addValue("isDefault",address.isDefault());
        return jdbcTemplate.update(sql,map);
    }

    @Override
    public int delete(int customerId) {
        String sql = "delete from address where customer_id=:customerId";
        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("customerId",customerId);
        return jdbcTemplate.update(sql,map);
    }

    @Override
    public Address get(int customerId) {
        String sql = "select * from address where customer_id=:customerId";
        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("customerId",customerId);
        return jdbcTemplate.queryForObject(sql,map,addressMapper);
    }
}
