package com.baizhi.service;

import com.baizhi.dao.AlbumDao;
import com.baizhi.entity.Album;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

@Service
@Transactional
public class AlbumServiceImpl implements AlbumService {
    @Autowired
    private AlbumDao ad;

    @Override
    public Map selectAll(int page, int rows) {
        int start = (page - 1) * rows;
        int count = ad.count();
        Map map = new HashMap();
        map.put("rows", ad.selectAll(start, rows));
        map.put("total", count);
        return map;
    }

    @Override
    public void add(Album album) {
        ad.add(album);
    }


}
