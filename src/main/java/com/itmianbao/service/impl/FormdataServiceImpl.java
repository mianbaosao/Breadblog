package com.itmianbao.service.impl;

import com.itmianbao.mapper.FormdataMapper;
import com.itmianbao.mapper.UserMapper;
import com.itmianbao.pojo.FormData;
import com.itmianbao.pojo.User;
import com.itmianbao.service.FormdataService;
import com.itmianbao.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FormdataServiceImpl implements FormdataService {
    @Autowired
    private FormdataMapper formdataMapper;
    @Override
    public List<FormData> list2() {return formdataMapper.list2(); }
    //获取详细分类
    @Override
    public List<FormData> list7(Integer categoryId) { return formdataMapper.list7(categoryId); }
    //删除
    @Override
    public void deleteform(String categoryName) {
        formdataMapper.deletebyname(categoryName);
    }
    //更新
    @Override
    public void update(FormData formData) {
        formdataMapper.update(formData);
    }
    //更新分类里的博客数量
    @Override
    public void updateCategory(Integer oldId) {
        formdataMapper.updateCategory(oldId);
    }
    @Override
    public void updateCategory2(Integer newId) {
        formdataMapper.updateCategory2(newId);
    }
}
