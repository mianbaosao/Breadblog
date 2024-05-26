package com.itmianbao.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.itmianbao.mapper.BlogDataMapper;
import com.itmianbao.mapper.UserMapper;
import com.itmianbao.pojo.BlogData;
import com.itmianbao.pojo.Result;
import com.itmianbao.pojo.User;
import com.itmianbao.service.BlogDataservice;
import com.itmianbao.service.UserService;
import com.itmianbao.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.security.interfaces.RSAMultiPrimePrivateCrtKey;
import java.util.List;

@Service
public class BlogDataServiceImpl implements BlogDataservice {
    @Autowired
    private BlogDataMapper blogDataMapper;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private RedisUtil redisUtil;
    //博客信息
    @Override
    public List<BlogData> list4() { return blogDataMapper.list4(); }

    @Override
    public void save(BlogData blogData) { blogDataMapper.insert(blogData); }

    @Override
    public void deleteblog(int blogId) { blogDataMapper.deleteblog(blogId); }

    @Override
    public BlogData getBolgId(Integer blogId) {
        return blogDataMapper.getBlogId(blogId);
    }

    @Override
    public void updateblog(Integer blogId, BlogData blogData) {
        blogDataMapper.updateblog(blogId, blogData);
    }

    @Override
    public List<BlogData> getcategoryId(Integer categoryId) {
        return blogDataMapper.getcategoryId(categoryId);
    }

    //回收站
    @Override
    public List<BlogData> list6() {
        return blogDataMapper.list6();
    }
    //回收到回收站
    @Override
    public void recoverblog(Integer blogId) {
        blogDataMapper.recoverblog(blogId);
    }
    //返回博客管理
    @Override
    public void restoreblog(Integer blogId) {
        blogDataMapper.restoreblog(blogId);
    }
    //增加浏览次数
    @Override
    public void updatebrowse(Integer blogId) {
        blogDataMapper.updatebrowse(blogId);
    }

    @Override
    public Result likeBlog(int blogId,int userId) {
        //1.获取当前用户

        //2.判断当前用户是否已经点赞
        String key="blog:liked:"+blogId;
        Boolean isMember = stringRedisTemplate.opsForSet().isMember(key,String.valueOf(userId));
        if(isMember==false){
            //3.如果未点赞，可以点赞

            //3.1.数据库点赞数+1
            Boolean isSuccess = blogDataMapper.addLike(blogId);
            System.out.println(isSuccess+"666666666666666666666666666666666666");
            //3.2.保存用户到redis的set集合
            if(isSuccess){
                stringRedisTemplate.opsForSet().add(key, String.valueOf(userId));
            }
        }else{
            //4.如果已点赞，取消点赞

            //4.1.数据库点赞-1
            Boolean isSuccess = blogDataMapper.deleteLike(blogId);
            //4.2.把用户从redis的set集合里面移除
            if(isSuccess) {
                stringRedisTemplate.opsForSet().remove(key, String.valueOf(userId));
            }
        }

        return Result.success();
    }

    @Override
    public List<BlogData> pageInfo(int page, int size) {
        PageHelper.startPage(page,size);
        List<BlogData> blogData=blogDataMapper.list4();
        PageInfo<BlogData> pageInfo=new PageInfo<>(blogData);
        return pageInfo.getList();
    }

    @Override
    public int countNum() {
        return blogDataMapper.count();
    }

    @Override
    public List<BlogData> pageInfo2(int page, int size) {
        PageHelper.startPage(page,size);
        List<BlogData> blogData=blogDataMapper.list6();
        PageInfo<BlogData> pageInfo=new PageInfo<>(blogData);
        return pageInfo.getList();
    }

    @Override
    public int countNum2() {
        return blogDataMapper.count2();
    }

}
