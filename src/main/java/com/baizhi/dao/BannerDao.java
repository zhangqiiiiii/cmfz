package com.baizhi.dao;

import com.baizhi.entity.Banner;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BannerDao {
    public List<Banner> selectAll(@Param("start") int start, @Param("rows") int rows);

    public int count();

    public void delete(int id);

    public void update(Banner banner);

    public void add(Banner banner);
}