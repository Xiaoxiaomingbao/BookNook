package com.booknook.user.service.impl;

import com.booknook.common.exception.BadRequestException;
import com.booknook.common.utils.UserContext;
import com.booknook.user.domain.dto.AddressDTO;
import com.booknook.user.domain.po.Address;
import com.booknook.user.mapper.AddressMapper;
import com.booknook.user.service.IAddressService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Service
@Slf4j
public class AddressServiceImpl implements IAddressService {
    @Autowired
    AddressMapper addressMapper;

    @Override
    public List<Address> listAddresses() {
        return addressMapper.selectAllAddresses();
    }

    @Override
    public Address getById(Long id) {
        // 1. 查询地址
        Address address = addressMapper.selectAddressById(id);

        // 2. 检查地址是否存在
        if (address == null) {
            throw new BadRequestException("地址不存在");
        }
        if (address.getUid() == null) {
            throw new BadRequestException("地址数据异常：用户ID缺失");
        }

        return address;
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
