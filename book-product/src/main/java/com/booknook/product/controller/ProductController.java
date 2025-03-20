package com.booknook.product.controller;

import com.booknook.common.domain.dto.OrderDetailDTO;
import com.booknook.common.domain.dto.ProductDTO;
import com.booknook.common.utils.BeanUtils;
import com.booknook.product.service.IProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "商品管理相关接口")
@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final IProductService productService;

    @ApiOperation("根据id批量查询商品")
    @GetMapping
    public List<ProductDTO> queryProductByIds(@RequestParam("ids") List<Long> ids){
        return productService.queryProductByIds(ids);
    }

    @ApiOperation("根据id查询商品")
    @GetMapping("{id}")
    public ProductDTO queryItemById(@PathVariable("id") Long id) {
        return BeanUtils.copyBean(productService.queryProductById(id), ProductDTO.class);
    }

    @ApiOperation("批量扣减库存")
    @Transactional
    @PutMapping("/stock/deduct")
    public void deductStock(@RequestBody List<OrderDetailDTO> products){
        productService.deductStock(products);
    }

    @ApiOperation("新增商品")
    @Transactional
    @PostMapping
    public void add(@RequestBody ProductDTO product) {
        productService.add(BeanUtils.copyBean(product, ProductDTO.class));
    }

}
