package com.baizhi.controller;


import com.baizhi.entity.Chapter;
import com.baizhi.service.ChapterService;
import com.baizhi.util.FileUtil;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.UUID;

@Controller
public class ChapterController {
    @Autowired
    private ChapterService cs;

    @RequestMapping("/Chapter_add")
    public @ResponseBody
    boolean addChapter(MultipartFile wenjian, Chapter chapter, Integer id, HttpServletRequest request) {
        /*
         * 调用业务
         * 获取大小时长
         * 保存文件   //文件上传 文件保存到那 文件重名  将文件保存到指定目录
         * 保存数据
         * */

        System.out.println("*******" + id);
        String realPath = request.getSession().getServletContext().getRealPath("/");  //webapp当前项目的路径
        File file = new File(realPath + "/upload");
        if (!file.exists()) {
            file.mkdir();
        }
        //获取文件的名字  是后缀
        String extension = FilenameUtils.getExtension(wenjian.getOriginalFilename());
        System.out.println("老名字" + extension);
        UUID uuid = UUID.randomUUID();
        String s = uuid.toString();
        //拼接   新得名字
        String newName = s + "." + extension;

        System.out.println("新名字" + newName);
        try {
            wenjian.transferTo(new File(file.getAbsolutePath(), newName));
        } catch (IOException e) {
            e.printStackTrace();
        }

        //时长
        int length = FileUtil.getDuration(new File(file.getAbsolutePath() + "/" + newName));
        long size = wenjian.getSize();
        double l = size / 1024.0 / 1024.0;
        String daxiao = l + "MB";
        System.out.println(daxiao);
        //文件的大小
        chapter.setSize(daxiao);
        //外键
        chapter.setAid(id);
        //地址
        chapter.setDownPath(newName);
        //时长
        int fen = length / 60;
        int miao = length % 60;
        String shichang = fen + "分" + miao + "秒";
        chapter.setDuration(shichang);
        System.out.println("添加章节的打印" + chapter);

        //文件大小  时长  名字  url   date
        try {
            cs.add(chapter);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @RequestMapping("/download")
    public void download(String downPath, String title, HttpServletRequest request, HttpServletResponse response) {
        String uploadPath = request.getSession().getServletContext().getRealPath("upload");  //webapp当前项目的路径
        String path = uploadPath + "/" + downPath;
        File file = new File(path);
        //获取地址的后缀
        String hz = downPath.substring(downPath.lastIndexOf("."));
        System.out.println("打印后缀" + hz);
        String s = title + hz;
        System.out.println("打印uuid的名字" + s);
        System.out.println(title);
        try {
            response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(s, "utf-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        response.setContentType("audio/mpeg");

        try {
            ServletOutputStream outputStream = response.getOutputStream();
            outputStream.write(FileUtils.readFileToByteArray(file));
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}
