package com.baizhi.controller;

import com.baizhi.entity.Album;
import com.baizhi.service.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
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

    @RequestMapping("Album_add")
    public @ResponseBody
    boolean add(MultipartFile wenjian, HttpServletRequest request, Album album) {
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
            album.setCoverImg(fileName);
            as.add(album);
            return true;
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return false;
        }
    }
}
