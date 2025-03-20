package com.booknook.common.client;

import com.booknook.common.domain.dto.OrderDetailDTO;
import com.booknook.common.domain.dto.ProductDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collection;
import java.util.List;

@FeignClient("book-product")
public interface ProductClient {

    @GetMapping("/products")
    List<ProductDTO> queryItemByIds(@RequestParam("ids") Collection<Long> ids);

    @PutMapping("products/stock/deduct")
    void deductStock(@RequestBody List<OrderDetailDTO> items);
}
