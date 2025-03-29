package com.booknook.user.mapper;

import com.booknook.user.domain.po.Address;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface AddressMapper {

    /**
     * 查询单个地址
     */
    @Select("SELECT id, uid, province, city, town, mobile, street, contact, is_default, notes FROM address WHERE id = #{id}")
    Address selectAddressById(Long id);

    /**
     * 查询所有地址
     */
    @Select("SELECT * FROM address")
    List<Address> selectAllAddresses();

    /**
     * 插入地址
     */
    @Insert("INSERT INTO address (uid, province, city, town, mobile, street, contact, is_default, notes) " +
            "VALUES (#{userId}, #{province}, #{city}, #{town}, #{mobile}, #{street}, #{contact}, #{isDefault}, #{notes})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insertAddress(Address address);

    /**
     * 更新地址
     */
    @Update("UPDATE address SET uid = #{userId}, province = #{province}, city = #{city}, town = #{town}, " +
            "mobile = #{mobile}, street = #{street}, contact = #{contact}, is_default = #{isDefault}, notes = #{notes} WHERE id = #{id}")
    int updateAddress(Address address);

    /**
     * 删除地址
     */
    @Delete("DELETE FROM address WHERE id = #{id}")
    int deleteAddressById(Long id);
}
