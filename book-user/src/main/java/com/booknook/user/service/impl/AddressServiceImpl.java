package com.booknook.user.service.impl;

import com.booknook.user.domain.dto.AddressDTO;
import com.booknook.user.domain.po.Address;
import com.booknook.user.mapper.AddressMapper;
import com.booknook.user.service.IAddressService;
import org.springframework.stereotype.Service;

import javax.management.Query;
import java.util.Collections;
import java.util.List;

@Service
public class AddressServiceImpl implements IAddressService {

    AddressMapper addressMapper;

    @Override
    public List<Address> listAddresses() {
        return addressMapper.selectAllAddresses();
    }

    @Override
    public Address getById(Long id) {
        return addressMapper.selectAddressById(id);
    }

    @Override
    public void addAddress(Address address) {
        addressMapper.insertAddress(address);
    }

    @Override
    public void updateAddress(Address address) {
        addressMapper.updateAddress(address);
    }

    @Override
    public void deleteAddressById(Long id) {
        addressMapper.deleteAddressById(id);
    }

    @Override
    public List<AddressDTO> query(Long uid) {
        return Collections.emptyList();
    }


}
