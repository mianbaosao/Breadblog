package com.itmianbao.service;


import com.itmianbao.pojo.*;

import java.util.List;
public interface DeptService {
    List<User> list();
//查询全部部门数据

    void delete(Integer id);

    void deleteuser(Integer id);


    User login(User user);

    List<FormData> list2();
    //删除
    void deleteform(String categoryName);
    //更新
    void update(FormData formData);

     //博客信息
    List<BlogData> list4();

    void save(BlogData blogData);

    void deleteblog(int blogId);


    BlogData getBolgId(Integer blogId);

    void updateblog(Integer blogId, BlogData blogData);

    List<BlogData> getcategoryId(Integer categoryId);


    void updateuser( User user);

    List<User> list5( Integer id);

    List<BlogData> list6();

    void recoverblog(Integer blogId);

    void restoreblog(Integer blogId);

    void updateCategory(Integer oldId);

    void updateCategory2(Integer newId);


    List<FormData> list7(Integer categoryId);

    List<User> showuser();

    void updatebrowse(Integer blogId);


    List<Comment> showcomment(Integer blogId);

    void insertComment(Comment comment);

    void deleteComment(Integer commentId);

    List<Comment> showreply(Integer blogId, Integer parentId);
}
