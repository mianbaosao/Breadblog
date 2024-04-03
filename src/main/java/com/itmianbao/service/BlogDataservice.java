package com.itmianbao.service;

import com.itmianbao.pojo.BlogData;
import com.itmianbao.pojo.Comment;
import com.itmianbao.pojo.Result;
import com.itmianbao.pojo.User;

import java.util.List;

public interface BlogDataservice {
    //博客信息
    List<BlogData> list4();
    //插入
    void save(BlogData blogData);
    //删除
    void deleteblog(int blogId);
    //获取id
    BlogData getBolgId(Integer blogId);
    //更新博客
    void updateblog(Integer blogId, BlogData blogData);
    //查找分类id
    List<BlogData> getcategoryId(Integer categoryId);
    //回收站
    List<BlogData> list6();
    //博客管理点击删除回收到回收站
    void recoverblog(Integer blogId);
    //回收站点击回收返回博客管理
    void restoreblog(Integer blogId);
    //更新浏览次数
    void updatebrowse(Integer blogId);
    //文章是否点赞
    Result likeBlog(int blogId, int userId);


}
