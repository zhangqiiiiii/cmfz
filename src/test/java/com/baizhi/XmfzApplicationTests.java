package com.baizhi;

import com.baizhi.entity.Admin;
import com.baizhi.service.AdminService;
import com.baizhi.service.BannerService;
import com.baizhi.service.MenuService;
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
    @Autowired
    private MenuService ms;
    @Autowired
    private BannerService bs;

    @Test
    public void contextLoads() {
        Admin admin = new Admin(1, "zq", "123456");
        System.out.println(as.login(admin));

        // Map m = ms.selectAll();
        //  System.out.println(m);

        //Map map = bs.selectAll();
        //System.out.println(map);
    }

}
