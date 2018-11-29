package com.baizhi.entity;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

@Data
public class Banner implements Serializable {
    private Integer id;

    private String title;

    private String imgPath;

    private String desc;

    private String status;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JSONField(format = "yyyy-MM-dd")
    private Date dates;

    public Banner() {


    }

    public Banner(Integer id, String title, String imgPath, String desc, String status, Date dates) {
        this.id = id;
        this.title = title;
        this.imgPath = imgPath;
        this.desc = desc;
        this.status = status;
        this.dates = dates;
    }
}