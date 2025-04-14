package com.myorg.address.repository;

import com.myorg.address.model.Address;

public interface AddressRepository {

    int create(Address address);
    int update(Address address, int customerId);
    int delete(int customerId);
    Address get(int customerId);
}
