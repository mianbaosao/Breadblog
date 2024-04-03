package com.itmianbao.mapper;

import com.itmianbao.pojo.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
@Component
@Mapper
public interface DeptMapper {
    //查询全部部门数据
    @Select("select * from user")
    List<User> list();
    @Delete("delete from dept where id = #{id}" )
    void deleteById(Integer id);
    @Delete("delete from user where id =#{id}")
    void deleteuser(Integer id);
    //用户密码
    @Select("SELECT * FROM user WHERE account = #{account} AND password = #{password}")
    User getByaccount(User user);
    //查询分类列表的数据
    @Select("select *from formdata")
    List<FormData> list2();
    //根据名称删除表格内容
    @Delete("delete from formdata where categoryName=#{categoryName}")
    void deletebyname(String categoryName);
    //更新表格内容
    @Update("UPDATE formdata SET cover = #{cover}, categoryName = #{categoryName}, categoryDesc = #{categoryDesc} WHERE `categoryId` = #{categoryId}")
    void update(FormData formData);
    //查询博客文章
    @Select("select *from blogdata where allowIssue='1'")
    List<BlogData> list4();
    @Insert({
            "insert into blogdata(cover,title,writer,categoryName,allowComment,status,content,description,allowIssue,statusName,time,browse)",
            "values(#{cover},#{title},#{writer},#{categoryName},#{allowComment},#{status},#{content},#{description},#{allowIssue},#{statusName},#{time},0)"
    })
    void insert(BlogData blogData);

    @Delete("delete from blogdata where blogId=#{blogId}")
    void deleteblog(int blogId);

    @Select("select *from blogdata where blogId=#{blogId}")
    BlogData getBlogId(Integer blogId);

    @Update("UPDATE blogdata SET cover = #{blogData.cover}, title = #{blogData.title}, writer = #{blogData.writer}, categoryName = #{blogData.categoryName},allowComment = #{blogData.allowComment}, statusName = #{blogData.statusName}, status = #{blogData.status}, content = #{blogData.content}, categoryId=#{blogData.categoryId},allowIssue=#{blogData.allowIssue},time=#{blogData.time} WHERE `blogId` = #{blogId}")
    void updateblog(Integer blogId, BlogData blogData);

    @Select("select *from blogdata where categoryId=#{categoryId}")
    List<BlogData> getcategoryId(Integer categoryId);

    @Update("UPDATE user SET cover=#{cover}, username=#{username},password=#{password},birthday=#{birthday},gender=#{gender},description=#{description} WHERE `id` = #{id}")
    void updateuser(User user);
    @Select("select *from user where id =#{id}")
    List<User> list5(Integer id);
    //回收站blog查询
    @Select("select * from blogdata where allowIssue ='0'")
    List<BlogData> list6();
    //回收到回收站
    @Update("UPDATE blogdata SET allowIssue='0',statusName='未发布' WHERE `blogId` = #{blogId}")
    void recoverblog(Integer blogId);
    //返回博客管理
    @Update("UPDATE blogdata SET allowIssue='1',statusName='已发布' WHERE `blogId` = #{blogId}")
    void restoreblog(Integer blogId);
   //更新分类里的博客数量
   @Update("UPDATE formdata SET blogCount = blogCount - 1 WHERE categoryId = #{oldId};")
   void updateCategory(Integer oldId);
    @Update("UPDATE formdata SET blogCount = blogCount + 1 WHERE categoryId = #{newId};")
    void updateCategory2(Integer newId);
  //获取详细分类
  @Select("select *from formdata where categoryId=#{categoryId}")
    List<FormData> list7(Integer categoryId);
   //展示所有成员
    @Select("select * from user")
    List<User> showuser();
   //增加浏览次数'
    @Update("UPDATE blogdata SET browse=browse+1 WHERE `blogId` = #{blogId};")
    void updatebrowse(Integer blogId);
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
