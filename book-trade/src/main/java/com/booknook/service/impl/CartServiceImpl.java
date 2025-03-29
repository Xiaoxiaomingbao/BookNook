package com.booknook.service.impl;

import cn.hutool.core.util.StrUtil;
import com.booknook.common.client.ProductClient;
import com.booknook.common.domain.dto.CartFormDTO;
import com.booknook.common.domain.dto.ProductDTO;
import com.booknook.common.domain.vo.CartVO;
import com.booknook.common.exception.BizIllegalException;
import com.booknook.common.utils.BeanUtils;
import com.booknook.common.utils.CollUtils;
import com.booknook.common.utils.UserContext;
import com.booknook.config.CartProperties;
import com.booknook.domain.po.Cart;
import com.booknook.mapper.CartMapper;
import com.booknook.service.ICartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * <p>
 * 订单详情表 服务实现类
 * </p>
 *
 * @author 虎哥
 * @since 2023-05-05
 */
@Service
@RequiredArgsConstructor
public class CartServiceImpl implements ICartService {

    private final ProductClient productClient;

    private final CartProperties cartProperties;

    private final CartMapper cartMapper;

    @Override
    public void addItem2Cart(CartFormDTO cartFormDTO) {
        // 1.获取登录用户
        Long userId = UserContext.getUser();

        // 2.判断是否已经存在该商品
        if(checkItemExists(cartFormDTO.getItemId(), userId)){
            // 2.1.商品已存在，则更新购物车数量
            cartMapper.updateNum(cartFormDTO.getItemId(), userId);
            return;
        }
        // 2.2.商品不存在，检查购物车是否已满
        checkCartsFull(userId);

        // 3.新增购物车条目
        // 3.1.转换DTO为PO对象
        Cart cart = BeanUtils.copyBean(cartFormDTO, Cart.class);
        // 3.2.设置用户ID
        cart.setUserId(userId);
        // 3.3.保存到数据库
        cartMapper.insert(cart);  // 使用标准MyBatis插入方法
    }

    @Override
    public List<CartVO> queryMyCarts() {
        // 1.查询当前用户的购物车列表
        List<Cart> carts = cartMapper.selectByUserId(UserContext.getUser());
        if (CollUtils.isEmpty(carts)) {
            return CollUtils.emptyList();
        }

        // 2.将PO转换为VO
        List<CartVO> vos = BeanUtils.copyList(carts, CartVO.class);

        // 3.处理VO中的商品信息（通过商品ID获取商品详情）
        handleCartItems(vos);

        // 4.返回购物车VO列表
        return vos;
    }

    private void handleCartItems(List<CartVO> vos) {
        // 1.获取购物车中的所有商品ID
        Set<Long> itemIds = vos.stream().map(CartVO::getItemId).collect(Collectors.toSet());

        // 2.查询商品信息
        List<ProductDTO> items = productClient.queryItemByIds(itemIds);
        if (CollUtils.isEmpty(items)) {
            return;
        }

        // 3.将商品信息映射为ID到商品的映射
        Map<Long, ProductDTO> itemMap = items.stream().collect(Collectors.toMap(ProductDTO::getPid, Function.identity()));

        // 4.将商品信息填充到购物车VO中
        for (CartVO v : vos) {
            ProductDTO item = itemMap.get(v.getItemId());
            if (item != null) {
                v.setNewPrice(item.getPrice());
                v.setStatus(item.getStatus());
                v.setStock(item.getStock());
            }
        }
    }

    @Override
    @Transactional
    public void removeByItemIds(Collection<Long> itemIds) {
        // 1.删除指定商品的购物车条目
        cartMapper.deleteByItemIds(UserContext.getUser(), itemIds);  // 使用标准MyBatis删除方法
    }

    private void checkCartsFull(Long userId) {
        // 1.检查当前用户购物车中的商品数量
        int count = cartMapper.countByUserId(userId);  // 使用标准MyBatis查询数量
        if (count >= cartProperties.getMaxItems()) {
            throw new BizIllegalException(
                    StrUtil.format("用户购物车不能超过{}项", cartProperties.getMaxItems()));
        }
    }

    private boolean checkItemExists(Long itemId, Long userId) {
        // 1.检查购物车中是否已存在该商品
        int count = cartMapper.countByUserIdAndItemId(userId, itemId);  // 使用标准MyBatis查询数量
        return count > 0;
    }

    @Override
    public void deleteCartItem(Long itemId) {
        // 1. 获取当前登录用户的ID
        Long userId = UserContext.getUser();

        // 2. 判断购物车中是否存在该商品
        int count = cartMapper.countByUserIdAndItemId(userId, itemId);
        if (count <= 0) {
            // 如果商品不存在于购物车中，则抛出异常
            throw new BizIllegalException("商品不在购物车中，无法删除");
        }

        // 3. 删除该商品的购物车条目
        List<Long> temp = new ArrayList<>();
        temp.add(itemId);
        cartMapper.deleteByItemIds(userId, temp); // 使用已定义的删除方法，传入商品ID进行删除
    }


    @Override
    public void updateCart(Cart cart) {
        // 获取当前用户
        Long userId = UserContext.getUser();
        // 设置用户ID，避免被伪造覆盖
        cart.setUserId(userId);
        // 执行更新操作
        cartMapper.update(cart);
    }

    @Override
    public void deleteCartItemsByIds(List<Long> ids) {
        // 获取当前登录用户的ID
        Long userId = UserContext.getUser();

        // 删除指定的购物车条目
        cartMapper.deleteByItemIds(userId, ids);  // 调用CartMapper的deleteByItemIds方法
    }

}
