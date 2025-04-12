package com.booknook.common.domain.dto;

import com.booknook.common.enums.ProductCondition;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Year;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "商品实体")
public class ProductDTO {
    @ApiModelProperty("商品id")
    private Long pid;
    @ApiModelProperty("卖家id")
    private Long uid;
    @ApiModelProperty("商品名称")
    private String name;
    @ApiModelProperty("ISBN(不带横杠)")
    private String isbn;
    @ApiModelProperty("出版社")
    private String publisher;
    @ApiModelProperty("出版时间")
    private Year publishTime;
    @ApiModelProperty("作者")
    private String author;
    @ApiModelProperty("类目名称")
    private String category;
    @ApiModelProperty("商品描述")
    private String description;
    @ApiModelProperty("商品封面的URL")
    private List<String> covers;
    @ApiModelProperty("商品成色")
    private ProductCondition condition;
    @ApiModelProperty("商品状态 1-正常，2-下架，3-删除")
    private Integer status;
    @ApiModelProperty("价格（分）")
    private Integer price;
    @ApiModelProperty("库存数量")
    private Integer stock;
}
