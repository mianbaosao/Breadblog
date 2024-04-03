package com.itmianbao.service;

import com.itmianbao.pojo.Comment;
import com.itmianbao.pojo.User;

import java.util.List;

public interface CommentService {
        //展示评论
        List<Comment> showcomment(Integer blogId);
        //插入评论
        void insertComment(Comment comment);
        //删除评论
        void deleteComment(Integer commentId);
        //多级评论
        List<Comment> showreply(Integer blogId, Integer parentId);
}
