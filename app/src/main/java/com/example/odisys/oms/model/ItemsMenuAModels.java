package com.example.odisys.oms.model;

/**
 * Created by Odisys on 25/02/2017.
 */

public class ItemsMenuAModels {
    private int icon;
    private String title,desc;

    public ItemsMenuAModels(String title,String desc,int icon) {
        this.icon = icon;
        this.title = title;
        this.desc = desc;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
