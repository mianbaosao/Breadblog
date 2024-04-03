package com.itmianbao.mapper;

import com.itmianbao.pojo.BlogData;
import com.itmianbao.pojo.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface BlogDataMapper {
    //查询博客文章
    @Select("select *from blogdata where allowIssue='1'")
    List<BlogData> list4();
    //插入博客
    @Insert({
            "insert into blogdata(cover,title,writer,categoryName,allowComment,status,content,description,allowIssue,statusName,time,browse)",
            "values(#{cover},#{title},#{writer},#{categoryName},#{allowComment},#{status},#{content},#{description},#{allowIssue},#{statusName},#{time},0)"
    })
    void insert(BlogData blogData);
    //删除博客
    @Delete("delete from blogdata where blogId=#{blogId}")
    void deleteblog(int blogId);
    //查找博客
    @Select("select *from blogdata where blogId=#{blogId}")
    BlogData getBlogId(Integer blogId);
    //更新博客
    @Update("UPDATE blogdata SET cover = #{blogData.cover}, title = #{blogData.title}, writer = #{blogData.writer}, categoryName = #{blogData.categoryName},allowComment = #{blogData.allowComment}, statusName = #{blogData.statusName}, status = #{blogData.status}, content = #{blogData.content}, categoryId=#{blogData.categoryId},allowIssue=#{blogData.allowIssue},time=#{blogData.time} WHERE `blogId` = #{blogId}")
    void updateblog(Integer blogId, BlogData blogData);
    //查找分类id
    @Select("select *from blogdata where categoryId=#{categoryId}")
    List<BlogData> getcategoryId(Integer categoryId);
    //回收站blog查询
    @Select("select * from blogdata where allowIssue ='0'")
    List<BlogData> list6();
    //回收到回收站
    @Update("UPDATE blogdata SET allowIssue='0',statusName='未发布' WHERE `blogId` = #{blogId}")
    void recoverblog(Integer blogId);
    //返回博客管理
    @Update("UPDATE blogdata SET allowIssue='1',statusName='已发布' WHERE `blogId` = #{blogId}")
    void restoreblog(Integer blogId);
    //增加浏览次数'
    @Update("UPDATE blogdata SET browse=browse+1 WHERE `blogId` = #{blogId};")
    void updatebrowse(Integer blogId);
    //增加文章点赞
    @Update("UPDATE blogdata SET likes=likes+1 WHERE blogId = #{blogId}")
    Boolean addLike(int blogId);
    //移除点赞
    @Update("UPDATE blogdata SET likes=likes-1 WHERE blogId = #{blogId}")
    Boolean deleteLike(int blogId);
}
