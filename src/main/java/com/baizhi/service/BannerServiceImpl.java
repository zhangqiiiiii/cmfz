package com.baizhi.service;

import com.baizhi.dao.BannerDao;
import com.baizhi.entity.Banner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

@Service
@Transactional
public class BannerServiceImpl implements BannerService {
    @Autowired
    private BannerDao bd;

    @Override
    public Map selectAll(int page, int rows) {
        HashMap map = new HashMap();
        int start = (page - 1) * rows;
        map.put("rows", bd.selectAll(start, rows));
        map.put("total", bd.count());
        return map;
    }

    @Override
    public void delete(int id) {
        bd.delete(id);

    }

    @Override
    public void update(Banner banner) {
        bd.update(banner);
    }

    @Override
    public void add(Banner banner) {
        bd.add(banner);
    }


}
