package com.baizhi.entity;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

@Data
public class Chapter implements Serializable {
    private Integer id;

    private String title;

    private String size;

    private String duration;

    private String downPath;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JSONField(format = "yyyy-MM-dd")
    private Date uploadDate;

    private Integer aid;

    public Chapter() {
        super();
    }

    public Chapter(Integer id, String title, String size, String duration, String downPath, Date uploadDate, Integer aid) {
        this.id = id;
        this.title = title;
        this.size = size;
        this.duration = duration;
        this.downPath = downPath;
        this.uploadDate = uploadDate;
        this.aid = aid;
    }
}