package com.baizhi.service;

import com.baizhi.entity.Album;

import java.util.Map;

public interface AlbumService {
    public Map selectAll(int page, int rows);

    public void add(Album album);
}
