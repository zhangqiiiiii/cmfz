package com.baizhi.controller;

import com.baizhi.service.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
public class AlbumController {
    @Autowired
    private AlbumService as;

    @RequestMapping("/Album_selectAll")
    public @ResponseBody
    Map selectAll(int page, int rows) {
        System.out.println("专辑 打印" + as.selectAll(page, rows));
        return as.selectAll(page, rows);
    }
}
