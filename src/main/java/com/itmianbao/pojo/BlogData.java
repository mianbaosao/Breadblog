package com.itmianbao.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class BlogData implements Serializable {
    private Integer blogId;
    private String cover;
    private String title;
    private String writer;
    private String categoryName;
    private String allowComment;
    private String statusName;
    private String status;
    private String content;
    private Integer categoryId;
    private String description;
    private String allowIssue;
    private String time;
    private Integer browse;
    private Integer isLike;
    private Integer likes;
}
