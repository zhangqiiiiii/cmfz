package com.baizhi;

import com.baizhi.entity.Admin;
import com.baizhi.service.AdminService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class XmfzApplicationTests {
    @Autowired
    private AdminService as;

    @Test
    public void contextLoads() {
        Admin admin = new Admin(1, "zq", "123456");
        System.out.println(as.login(admin));
    }

}
