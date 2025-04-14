package com.myorg.address.controller;

import com.myorg.address.model.Address;
import com.myorg.address.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/address")
public class AddressController {

    private final AddressService addressService;

    @Autowired
    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @PostMapping
    public String createAddress(@RequestBody Address address){
        int res = addressService.createAdddress(address);
        return "Address created successfully";
    }

    @PutMapping("/{customerId}")
    public String updateAddress(@RequestBody Address address,@PathVariable int customerId){
        int res = addressService.updateAddress(address,customerId);
        if(res == 0){
            return "Address not updated";
        }
        return "Address updated successfully";
    }

    @DeleteMapping("/{customerId}")
    public String deleteAddress(@PathVariable int customerId){
        int res = addressService.deleteAddressById(customerId);
        if(res == 0){
            return "Address not updated";
        }
        return "Address deleted successfully";
    }

    @GetMapping("/{customerId}")
    public Address getAddressByCustomerId(@PathVariable int customerId){
        return addressService.getAddressById(customerId);
    }

}
