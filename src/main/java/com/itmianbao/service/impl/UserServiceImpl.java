package com.itmianbao.service.impl;

import com.itmianbao.mapper.UserMapper;
import com.itmianbao.pojo.Comment;
import com.itmianbao.pojo.User;
import com.itmianbao.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    //手机验证码登录
    @Override
    public User phoneLogin(String phone) { return userMapper.phoneLogin(phone); }
    @Override
    public void deleteuser(Integer id) { userMapper.deleteuser(id); }
    //登录
    @Override
    public User login(User user) { return userMapper.getByaccount(user);}
    //更新
    @Override
    public void updateuser( User user) {userMapper.updateuser(user); }
    //展示用户
    @Override
    public List<User> list5(Integer id) { return userMapper.list5(id); }
    //查找全部成员
    @Override
    public List<User> showuser() {
        return userMapper.showuser();
    }


}
