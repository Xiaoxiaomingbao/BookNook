package com.booknook.product.mapper;

import com.booknook.product.domain.po.Cover;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CoverMapper {

    List<String> queryCoverByPid(long pid);

    List<Cover> queryCoversByPids(@Param("pids") List<Long> pids);

    void add(Cover cover);
}
