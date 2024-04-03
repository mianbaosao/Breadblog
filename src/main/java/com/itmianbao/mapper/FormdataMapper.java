package com.itmianbao.mapper;

import com.itmianbao.pojo.FormData;
import com.itmianbao.pojo.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface FormdataMapper {
    //查询分类列表的数据
    @Select("select *from formdata")
    List<FormData> list2();
    //获取详细分类
    @Select("select *from formdata where categoryId=#{categoryId}")
    List<FormData> list7(Integer categoryId);
    //根据名称删除表格内容
    @Delete("delete from formdata where categoryName=#{categoryName}")
    void deletebyname(String categoryName);
    //更新表格内容
    @Update("UPDATE formdata SET cover = #{cover}, categoryName = #{categoryName}, categoryDesc = #{categoryDesc} WHERE `categoryId` = #{categoryId}")
    void update(FormData formData);
    //更新分类里的博客数量
    @Update("UPDATE formdata SET blogCount = blogCount - 1 WHERE categoryId = #{oldId};")
    void updateCategory(Integer oldId);
    @Update("UPDATE formdata SET blogCount = blogCount + 1 WHERE categoryId = #{newId};")
    void updateCategory2(Integer newId);
}
