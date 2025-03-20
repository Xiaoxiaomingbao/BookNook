package com.booknook.product.mapper;

import com.booknook.product.domain.po.Cover;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CoverMapper {
    @Select("select cover from cover where pid = #{pid}")
    List<String> queryCoverByPid(long pid);

    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into cover (pid, cover, number) values (#{pid}, #{cover}, #{number})")
    void add(Cover cover);
}
