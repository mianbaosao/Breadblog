package com.itmianbao.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class FormData {
    private String cover;
    private String categoryName;
    private String categoryDesc;
    private int blogCount;
    private int categoryId;
    private String title;
}