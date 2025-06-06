package com.booknook.product.mapper;

import com.booknook.product.domain.po.Product;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ProductMapper {

    Product queryProductById(long id);

    List<Product> queryProductsByIds(@Param("ids") List<Long> ids);

    void deductStock(@Param("id") long id, @Param("num") int num);

    void add(Product product);
}
