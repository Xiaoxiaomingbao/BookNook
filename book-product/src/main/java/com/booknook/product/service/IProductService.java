package com.booknook.product.service;

import com.booknook.common.domain.dto.OrderDetailDTO;
import com.booknook.common.domain.dto.ProductDTO;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public interface IProductService {

    void deductStock(List<OrderDetailDTO> orders);

    List<ProductDTO> queryProductByIds(Collection<Long> ids);

    ProductDTO queryProductById(Long id);

    void add(ProductDTO p);
}
