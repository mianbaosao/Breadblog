package com.itmianbao.service.impl;

import com.itmianbao.mapper.CommentMapper;
import com.itmianbao.mapper.UserMapper;
import com.itmianbao.pojo.Comment;
import com.itmianbao.pojo.User;
import com.itmianbao.service.CommentService;
import com.itmianbao.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentMapper commentMapper;
    //展示评论
    @Override
    public List<Comment> showcomment(Integer blogId) {
        return commentMapper.showcomment(blogId);
    }
    //插入评论
    @Override
    public void insertComment(Comment comment) {
        commentMapper.insertComment(comment);
    }
    //删除评论
    @Override
    public void deleteComment(Integer commentId) {
        commentMapper.deleteComment(commentId);
    }
    //展示多级回复
    @Override
    public List<Comment> showreply(Integer blogId, Integer parentId) { return commentMapper.showreply(blogId,parentId); }

}
