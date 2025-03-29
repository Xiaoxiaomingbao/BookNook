package com.booknook.service;

import com.booknook.common.domain.dto.CartFormDTO;
import com.booknook.common.domain.vo.CartVO;
import com.booknook.domain.po.Cart;

import java.util.Collection;
import java.util.List;

/**
 * <p>
 * 购物车 服务类
 * </p>
 *
 * @author 虎哥
 * @since 2023-05-05
 */
public interface ICartService {

    /**
     * 添加商品到购物车
     *
     * @param cartFormDTO 购物车表单数据
     */
    void addItem2Cart(CartFormDTO cartFormDTO);

    /**
     * 查询当前用户的购物车列表
     *
     * @return 购物车列表
     */
    List<CartVO> queryMyCarts();

    /**
     * 批量删除购物车中的商品
     *
     * @param itemIds 商品id集合
     */
    void removeByItemIds(Collection<Long> itemIds);

    void deleteCartItem(Long itemId);

    void updateCart(Cart cart);

    void deleteCartItemsByIds(List<Long> ids);

}
