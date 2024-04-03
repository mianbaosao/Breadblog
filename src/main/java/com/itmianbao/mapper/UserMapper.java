package com.itmianbao.mapper;

import com.itmianbao.pojo.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface UserMapper {
    //删除用户
    @Delete("delete from user where id =#{id}")
    void deleteuser(Integer id);
    //登录
    @Select("SELECT * FROM user WHERE account = #{account} AND password = #{password}")
    User getByaccount(User user);
    //更新
    @Update("UPDATE user SET cover=#{cover}, username=#{username},password=#{password},birthday=#{birthday},gender=#{gender},description=#{description} WHERE `id` = #{id}")
    void updateuser(User user);
    //展示用户
    @Select("select *from user where id =#{id}")
    List<User> list5(Integer id);
    //展示所有成员
    @Select("select * from user")
    List<User> showuser();
    @Select("select * from db01.user where phone=#{phone}")
    User phoneLogin(String phone);
}
