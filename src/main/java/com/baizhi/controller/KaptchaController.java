package com.baizhi.controller;

import com.google.code.kaptcha.Producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;

@Controller
public class KaptchaController {

    @Autowired
    private Producer producer;

    @RequestMapping("/kaptcha")
    public void kaptcha(HttpSession session, HttpServletResponse response)
            throws IOException {
        // 1.生成文本内容
        String text = producer.createText();
        // 2.把文本内容放到session中
        session.setAttribute("kaptcha", text);
        // 3.把生成的文本内容放到一个图片中 生一个图片
        BufferedImage image = producer.createImage(text);
        // 4.通过图片工具类，把图片写入到响应输出流
        ImageIO.write(image, "jpg", response.getOutputStream());

    }
}
