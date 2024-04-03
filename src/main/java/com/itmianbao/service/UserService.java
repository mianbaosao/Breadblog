package com.itmianbao.service;

import com.itmianbao.pojo.Comment;
import com.itmianbao.pojo.User;

import java.util.List;

public interface UserService {
    User phoneLogin(String phone);
    //删除用户
    void deleteuser(Integer id);
    //登录
    User login(User user);
    //更新
    void updateuser( User user);
    //展示单个用户
    List<User> list5(Integer id);
    //展示所有用户
    List<User> showuser();

}
