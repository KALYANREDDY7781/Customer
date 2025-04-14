package com.myorg.address.mapper;

import com.myorg.address.model.Address;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class AddressMapper implements RowMapper<Address> {

    @Override
    public Address mapRow(ResultSet rs, int rowNum) throws SQLException {
        Address address = new Address();
        address.setAddressId(rs.getInt("id"));
        address.setCustomerId(rs.getInt("customer_id"));
        address.setAddressLine1(rs.getString("address_line1"));
        address.setCity(rs.getString("city"));
        address.setState(rs.getString("state"));
        address.setType(rs.getString("type"));
        address.setZipCode(rs.getInt("zip_code"));
        address.setDefault(rs.getBoolean("is_default"));
        address.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
        address.setUpdatedAt(rs.getTimestamp("updated_at").toLocalDateTime());
        return address;
    }
}
