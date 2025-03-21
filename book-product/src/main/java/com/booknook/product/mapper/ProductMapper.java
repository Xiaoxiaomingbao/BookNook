package com.booknook.product.mapper;

import com.booknook.product.domain.po.Product;
import org.apache.ibatis.annotations.*;

@Mapper
public interface ProductMapper {
    @Select("select * from product where pid = #{id}")
    Product queryProductById(long id);

    @Update("update product set stock = stock - #{num} where pid = #{id}")
    void deductStock(long id, int num);

    @Options(useGeneratedKeys = true, keyProperty = "pid")  // 指定实体类的 pid 属性，用于接收数据库生成的主键
    @Insert("insert into product (uid, name, isbn, publisher, publish_time, author, category, " +
            "description, condition, status, price, stock, create_time, update_time) values " +
            "(#{uid}, #{name}, #{isbn}, #{publisher}, #{publishTime}, #{author}, #{category}, " +
            "#{description}, #{condition}, #{status}, #{price}, #{stock}, #{createTime}, #{updateTime})")
    void add(Product product);
}
