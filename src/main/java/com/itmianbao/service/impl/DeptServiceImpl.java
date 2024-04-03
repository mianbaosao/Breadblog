package com.itmianbao.service.impl;

import com.itmianbao.mapper.DeptMapper;
import com.itmianbao.pojo.*;
import com.itmianbao.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeptServiceImpl implements DeptService  {
    @Autowired
    private DeptMapper deptMapper;

    @Override
    public List<User> list() {
        return deptMapper.list();
    }

    @Override
    public void delete(Integer id) {
        deptMapper.deleteById(id);
    }

    @Override
    public void deleteuser(Integer id) {
        deptMapper.deleteuser(id);
    }

    @Override
    public User login(User user) {
        return deptMapper.getByaccount(user);
    }

    @Override
    public List<FormData> list2() {return deptMapper.list2(); }

    @Override
    public void deleteform(String categoryName) {
        deptMapper.deletebyname(categoryName);
    }

    @Override
    public void update(FormData formData) {
        deptMapper.update(formData);
    }

    @Override
    public List<BlogData> list4() { return deptMapper.list4(); }

    @Override
    public void save(BlogData blogData) { deptMapper.insert(blogData); }

    @Override
    public void deleteblog(int blogId) { deptMapper.deleteblog(blogId); }

    @Override
    public BlogData getBolgId(Integer blogId) {
        return deptMapper.getBlogId(blogId);
    }

    @Override
    public void updateblog(Integer blogId, BlogData blogData) {
        deptMapper.updateblog(blogId, blogData);
    }

    @Override
    public List<BlogData> getcategoryId(Integer categoryId) {
        return deptMapper.getcategoryId(categoryId);
    }

    @Override
    public void updateuser( User user) {
        deptMapper.updateuser(user);
    }

    @Override
    public List<User> list5(Integer id) {
        return deptMapper.list5(id);
    }
    //回收站
    @Override
    public List<BlogData> list6() {
        return deptMapper.list6();
    }
     //回收到回收站
    @Override
    public void recoverblog(Integer blogId) {
        deptMapper.recoverblog(blogId);
    }
    //返回博客管理
    @Override
    public void restoreblog(Integer blogId) {
        deptMapper.restoreblog(blogId);
    }
   //更新分类里的博客数量
    @Override
    public void updateCategory(Integer oldId) {
        deptMapper.updateCategory(oldId);
    }
    @Override
    public void updateCategory2(Integer newId) {
        deptMapper.updateCategory2(newId);
    }
    //获取详细分类
    @Override
    public List<FormData> list7(Integer categoryId) {
        return deptMapper.list7(categoryId);
    }
   //查找全部成员
    @Override
    public List<User> showuser() {
        return deptMapper.showuser();
    }
   //增加浏览次数
    @Override
    public void updatebrowse(Integer blogId) {
        deptMapper.updatebrowse(blogId);
    }

    @Override
    public List<Comment> showcomment(Integer blogId) {
        return deptMapper.showcomment(blogId);
    }

    @Override
    public void insertComment(Comment comment) {
        deptMapper.insertComment(comment);
    }
   //删除评论
    @Override
    public void deleteComment(Integer commentId) {
        deptMapper.deleteComment(commentId);
    }
  //展示多级回复
    @Override
    public List<Comment> showreply(Integer blogId, Integer parentId) {
        return deptMapper.showreply(blogId,parentId);
    }


}
