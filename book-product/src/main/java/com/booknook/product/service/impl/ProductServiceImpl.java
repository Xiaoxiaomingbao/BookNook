package com.booknook.product.service.impl;

import com.booknook.common.domain.dto.OrderDetailDTO;
import com.booknook.common.domain.dto.ProductDTO;
import com.booknook.common.enums.ProductCondition;
import com.booknook.product.domain.po.Cover;
import com.booknook.product.domain.po.Product;
import com.booknook.product.mapper.CoverMapper;
import com.booknook.product.mapper.ProductMapper;
import com.booknook.product.service.IProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ProductServiceImpl implements IProductService {
    @Autowired
    ProductMapper productMapper;
    @Autowired
    CoverMapper coverMapper;

    @Override
    public void deductStock(List<OrderDetailDTO> orders) {
        orders
            .forEach(order -> productMapper.deductStock(order.getPid(), order.getNum()));
    }

    @Override
    public List<ProductDTO> queryProductByIds(Collection<Long> ids) {
        Map<Long, List<String>> coverMap = new HashMap<>();
        coverMapper.queryCoversByPids((List<Long>) ids)
                .forEach(cover -> {
                    if (coverMap.containsKey(cover.getPid())) {
                        coverMap.get(cover.getPid()).add(cover.getCover());
                    } else {
                        List<String> list = new ArrayList<>();
                        list.add(cover.getCover());
                        coverMap.put(cover.getPid(), list);
                    }
                });
        return productMapper.queryProductsByIds((List<Long>) ids).stream()
                .map(product -> new ProductDTO(product.getId(), product.getUid(), product.getName(), product.getIsbn(),
                        product.getPublisher(), product.getPublishTime(), product.getAuthor(), product.getCategory(),
                        product.getDescription(), coverMap.get(product.getId()), ProductCondition.fromValue(product.getCondition()),
                        product.getStatus(), product.getPrice(), product.getStock()))
                .collect(Collectors.toList());
    }

    @Override
    public ProductDTO queryProductById(Long id) {
        Product product = productMapper.queryProductById(id);
        List<String> covers = coverMapper.queryCoverByPid(id);
        return new ProductDTO(product.getId(), product.getUid(), product.getName(), product.getIsbn(),
                product.getPublisher(), product.getPublishTime(), product.getAuthor(), product.getCategory(),
                product.getDescription(), covers, ProductCondition.fromValue(product.getCondition()),
                product.getStatus(), product.getPrice(), product.getStock());
    }

    @Override
    public void add(ProductDTO p) {
        Product product = new Product(-1L, p.getUid(), p.getName(), p.getIsbn(), p.getPublisher(),
                p.getPublishTime(), p.getAuthor(), p.getCategory(), p.getDescription(), p.getCondition().getValue(),
                p.getStatus(), p.getPrice(), p.getStock(), LocalDateTime.now(), null);
        productMapper.add(product);
        if (product.getId() == -1) {
            log.error("写入 product 表未获取到自增主键");
        } else {
            log.info("写入 product 表，获取到自增主键{}", product.getId());
        }
        for (int i = 0; i < p.getCovers().size(); i++) {
            Cover cover = new Cover(-1L, product.getId(), p.getCovers().get(i), i);
            coverMapper.add(cover);
            if (cover.getId() == -1) {
                log.error("写入 cover 表未获取到自增主键");
            } else {
                log.info("{}号图片写入 cover 表，获取到自增主键{}", cover.getNumber(), cover.getId());
            }
        }
        // 把 product 的自增 ID 返回
        p.setPid(product.getId());
    }
}
