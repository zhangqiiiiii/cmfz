package com.baizhi.service;

import com.baizhi.dao.MenuDao;
import com.baizhi.entity.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class MenuServiceImpl implements MenuService {
    @Autowired
    private MenuDao md;

    @Override
    public Map selectAll() {
        Map map = new HashMap();
        List<Menu> list = md.selectAll();
        System.out.println("菜单打印" + list);
        map.put("list", list);
        return map;
    }
}
