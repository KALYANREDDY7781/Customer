package com.myorg.address.service;

import com.myorg.address.model.Address;
import com.myorg.address.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressServiceImpl implements AddressService{

    private final AddressRepository addressRepository;

    @Autowired
    public AddressServiceImpl(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    @Override
    public int createAdddress(Address address) {
        return addressRepository.create(address);
    }

    @Override
    public int updateAddress(Address address, int customerId) {
        return addressRepository.update(address,customerId);
    }

    @Override
    public int deleteAddressById(int customerId) {
        return addressRepository.delete(customerId);
    }

    @Override
    public Address getAddressById(int customerId) {
        return addressRepository.get(customerId);
    }
}
