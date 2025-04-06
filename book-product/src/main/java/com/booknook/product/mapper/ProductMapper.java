package com.booknook.product.mapper;

import com.booknook.product.domain.po.Product;
import org.apache.ibatis.annotations.*;

@Mapper
public interface ProductMapper {
    @Select("select * from product where id = #{id}")
    Product queryProductById(long id);

    @Update("update product set stock = stock - #{num} where id = #{id}")
    void deductStock(long id, int num);

    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into product (uid, name, isbn, publisher, publish_time, author, category, " +
            "description, `condition`, status, price, stock, create_time, update_time) values " +
            "(#{uid}, #{name}, #{isbn}, #{publisher}, #{publishTime}, #{author}, #{category}, " +
            "#{description}, #{condition}, #{status}, #{price}, #{stock}, #{createTime}, #{updateTime})")
    void add(Product product);
}
