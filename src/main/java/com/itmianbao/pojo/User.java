package com.itmianbao.pojo;

import javafx.scene.chart.PieChart;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class User {
    private Integer id;
    private String account;
    private String password;
    private String username;
    private String cover;
    private Date birthday;
    private  String gender;
    private  String description;
    private String checkCode;

    public String getCode() {
        return  this.checkCode;
    }
}
