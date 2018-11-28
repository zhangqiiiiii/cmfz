package com.baizhi.controller;

import com.baizhi.entity.Banner;
import com.baizhi.service.BannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
public class BannerController {
    @Autowired
    private BannerService bs;

    @RequestMapping("/selectAll")
    public @ResponseBody
    Map selectAll(int page, int rows) {
        return bs.selectAll(page, rows);
    }

    @RequestMapping("/delete")
    public @ResponseBody
    boolean delete(int id) {
        try {
            bs.delete(id);
            return true;
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return false;
        }
    }

    @RequestMapping("/update")
    public @ResponseBody
    boolean update(Banner banner) {
        try {
            bs.update(banner);
            return true;
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return false;
        }
    }

    @RequestMapping("/add")
    public @ResponseBody
    boolean add(Banner banner) {
        try {
            bs.add(banner);
            return true;
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return false;
        }
    }
}
