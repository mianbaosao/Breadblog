package com.itmianbao.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.http.HttpHost;
import org.junit.Test;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.IOException;
import java.io.Serializable;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Document(indexName = "blogdata", shards = 1, replicas = 1)
public class BlogData implements Serializable {
    @Id
    private Integer blogId;
    @Field(type = FieldType.Text)
    private String cover;
    @Field(type = FieldType.Text)
    private String title;
    @Field(type = FieldType.Text)
    private String writer;
    @Field(type = FieldType.Text)
    private String categoryName;
    @Field(type = FieldType.Text)
    private String allowComment;
    @Field(type = FieldType.Text)
    private String statusName;
    @Field(type = FieldType.Text)
    private String status;
    @Field(type = FieldType.Text)
    private String content;
    @Field(type = FieldType.Integer)
    private Integer categoryId;
    @Field(type = FieldType.Text)
    private String description;
    @Field(type = FieldType.Boolean)
    private String allowIssue;
    //@Field(type = FieldType.Text)
    private String time;
    @Field(type = FieldType.Integer)
    private Integer browse;
    @Field(type = FieldType.Integer)
    private Integer isLike;
    @Field(type = FieldType.Integer)
    private Integer likes;

    private Integer total;

}
