package com.itmianbao.mapper;

import com.itmianbao.pojo.Comment;
import com.itmianbao.pojo.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface CommentMapper {
    //展示博客下面的回复
    @Select("select * from comment where blogId=#{blogId} and parentId is null")
    List<Comment> showcomment(Integer blogId);
    //插入评论内容
    @Insert( "insert into comment(blogId,userId,username,time,parentId,content,cover,parentName) values(#{blogId},#{userId},#{username},#{time},#{parentId},#{content},#{cover},#{parentName})")
    void insertComment(Comment comment);
    //删除评论
    @Delete("delete from comment where id=#{commentId}")
    void deleteComment(Integer commentId);
    //展示多级回复
    @Select("select * from comment where blogId=#{blogId} and parentId=#{parentId}")
    List<Comment> showreply(Integer blogId, Integer parentId);
}
