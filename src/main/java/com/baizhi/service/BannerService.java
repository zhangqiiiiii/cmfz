package com.baizhi.service;

import com.baizhi.entity.Banner;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

public interface BannerService {
    public Map selectAll(@Param("start") int start, @Param("rows") int rows);

    public void delete(int id);

    public void update(Banner banner);

    public void add(Banner banner);
}
