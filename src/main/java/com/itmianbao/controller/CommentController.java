package com.itmianbao.controller;

import com.itmianbao.pojo.Comment;
import com.itmianbao.pojo.Result;
import com.itmianbao.service.CommentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 评论控制器
 */
@Slf4j
@RestController
@CrossOrigin(origins = "http://localhost:3001")
public class CommentController {

    @Autowired
    private CommentService commentService;

    /**
     * 展示指定博客的评论
     *
     * @param blogId 博客ID
     * @return 返回评论列表
     */
    @GetMapping("/showcomment/{blogId}")
    public Result showcomment(@PathVariable Integer blogId) {
        List<Comment> commentList = commentService.showcomment(blogId);
        return Result.success(commentList);
    }

    /**
     * 插入评论
     *
     * @param comment 评论信息
     * @return 返回评论ID1
     */
    @PostMapping("/insertComment")
    public Result save(@RequestBody Comment comment) {
        commentService.insertComment(comment);
        return Result.success(comment.getId());
    }

    /**
     * 删除评论
     *
     * @param commentId 评论ID
     * @return 返回操作结果
     */
    @DeleteMapping("/deleteComment/{commentId}")
    public Result deleteComment(@PathVariable Integer commentId) {
        commentService.deleteComment(commentId);
        return Result.success();
    }

    /**
     * 展示指定博客指定父评论的回复
     *
     * @param blogId   博客ID
     * @param parentId 父评论ID
     * @return 返回回复列表
     */
    @GetMapping("/showReply/{blogId}/{parentId}")
    public Result showcomment(@PathVariable Integer blogId, @PathVariable Integer parentId) {
        List<Comment> commentList = commentService.showreply(blogId, parentId);
        return Result.success(commentList);
    }
}
