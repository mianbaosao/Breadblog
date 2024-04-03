package com.itmianbao.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Comment {
    private Integer id;
    private Integer blogId;
    private Integer userId;
    private String username;
    private String time;
    private Integer parentId;
    private String content;
    private String cover;
    private String parentName;

}
