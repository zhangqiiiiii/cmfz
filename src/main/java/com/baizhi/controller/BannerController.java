package com.baizhi.controller;

import com.baizhi.entity.Banner;
import com.baizhi.service.BannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.Map;

@Controller
public class BannerController {
    @Autowired
    private BannerService bs;

    @RequestMapping("/Bselect")
    public @ResponseBody
    Map selectAll(int page, int rows) {
        System.out.println(bs.selectAll(page, rows));
        return bs.selectAll(page, rows);
    }

    @RequestMapping("/delete")
    public @ResponseBody
    boolean delete(int id) {
        try {
            System.out.println(id);
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
    boolean add(MultipartFile wenjian, HttpServletRequest request, Banner banner) {
        try {
            // 获取文件的名字
            String fileName = wenjian.getOriginalFilename();
            System.out.println("文件上传的名字" + fileName);
            // 文件重新命名
            // fileName = new Date().getTime() + "_" + fileName;
            // 把接收到的文件转换成磁盘文件
            // wenjian.transferTo(new
            // File("F:\\Tomcat\\apache-tomcat-7.0.72\\webapps\\SpringmvcAuction\\wenjian"+fileName));
            // 根据文件夹名字获取绝对路径
            String realPath = request.getRealPath("img");
            wenjian.transferTo(new File(realPath + "\\" + fileName));
            banner.setImgPath(fileName);
            bs.add(banner);
            return true;
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return false;
        }
    }
}
