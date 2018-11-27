package com.baizhi.entity;

import java.util.List;


public class Menu {
    private int id;
    private String title;
    private String iconCls;
    private int parent_id;
    private String url;
    private List<Menu> menulist;

    public Menu() {


    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIconCls() {
        return iconCls;
    }

    public void setIconCls(String iconCls) {
        this.iconCls = iconCls;
    }

    public int getParent_id() {
        return parent_id;
    }

    public void setParent_id(int parent_id) {
        this.parent_id = parent_id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<Menu> getMenulist() {
        return menulist;
    }

    public void setMenulist(List<Menu> menulist) {
        this.menulist = menulist;
    }

    @Override
    public String toString() {
        return "Menu{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", iconCls='" + iconCls + '\'' +
                ", parent_id=" + parent_id +
                ", url='" + url + '\'' +
                ", menulist=" + menulist +
                '}';
    }

    public Menu(int id, String title, String iconCls, int parent_id, String url, List<Menu> menulist) {
        this.id = id;
        this.title = title;
        this.iconCls = iconCls;
        this.parent_id = parent_id;
        this.url = url;
        this.menulist = menulist;
    }
}
