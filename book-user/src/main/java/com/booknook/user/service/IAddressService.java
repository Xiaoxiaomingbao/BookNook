package com.booknook.user.service;

import com.booknook.user.domain.dto.AddressDTO;
import com.booknook.user.domain.po.Address;

import java.util.List;

public interface IAddressService {

    /**
     * 查询所有地址
     */
    List<Address> listAddresses();

    /**
     * 根据ID查询地址
     */
    Address getById(Long id);

    /**
     * 添加地址
     */
    void addAddress(Address address);

    /**
     * 更新地址
     */
    void updateAddress(Address address);

    /**
     * 删除地址
     */
    void deleteAddressById(Long id);

    List<AddressDTO> query(Long uid);
}