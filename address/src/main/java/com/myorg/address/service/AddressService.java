package com.myorg.address.service;

import com.myorg.address.model.Address;

public interface AddressService {
    int createAdddress(Address address);
    int updateAddress(Address address,int customerId);
    int deleteAddressById(int customerId);
    Address getAddressById(int customerId);

}
