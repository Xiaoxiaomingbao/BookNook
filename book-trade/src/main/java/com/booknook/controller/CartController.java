package com.booknook.controller;


import com.booknook.common.domain.dto.CartFormDTO;
import com.booknook.common.domain.vo.CartVO;
import com.booknook.domain.po.Cart;
import com.booknook.service.ICartService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Api(tags = "购物车相关接口")
@RestController
@RequestMapping("/carts")
@RequiredArgsConstructor
public class CartController {
    private final ICartService cartService;

    @ApiOperation("添加商品到购物车")
    @PostMapping
    public void addItem2Cart(@Valid @RequestBody CartFormDTO cartFormDTO){
        cartService.addItem2Cart(cartFormDTO);  // 调用服务层处理购物车的添加逻辑
    }

    @ApiOperation("更新购物车数据")
    @PutMapping
    public void updateCart(@RequestBody Cart cart){
        cartService.updateCart(cart);  // 使用标准 MyBatis 更新方法，替代 MyBatis Plus 方法
    }

    @ApiOperation("删除购物车中商品")
    @DeleteMapping("{id}")
    public void deleteCartItem(@Param("购物车条目id") @PathVariable("id") Long id){
        cartService.deleteCartItem(id);  // 使用标准 MyBatis 删除方法，替代 MyBatis Plus 方法
    }

    @ApiOperation("查询购物车列表")
    @GetMapping
    public List<CartVO> queryMyCarts(@RequestHeader(value = "user-info", required = false) String userInfo){
        System.out.println("userInfo = " + userInfo);
        return cartService.queryMyCarts();  // 调用服务层获取购物车列表
    }

    @ApiOperation("批量删除购物车中商品")
    @ApiImplicitParam(name = "ids", value = "购物车条目id集合")
    @DeleteMapping
    public void deleteCartItemByIds(@RequestParam("ids") List<Long> ids){
        cartService.deleteCartItemsByIds(ids);  // 使用标准方法批量删除购物车商品
    }
}
