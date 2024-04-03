package com.itmianbao.service;

import com.itmianbao.pojo.FormData;
import com.itmianbao.pojo.User;

import java.util.List;

public interface FormdataService  {
    //查询分类
    List<FormData> list2();
    //查询单个分类
    List<FormData> list7(Integer categoryId);
    //删除
    void deleteform(String categoryName);
    //更新
    void update(FormData formData);
    //更新分类里的博客数量
    void updateCategory(Integer oldId);
    void updateCategory2(Integer newId);
}
