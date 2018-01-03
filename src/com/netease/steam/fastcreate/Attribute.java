package com.netease.steam.fastcreate;

/**
 * Created by zhangchanglu on 2017/7/11
 * email zclsoft@163.com
 */
public class Attribute {
    private String name;
    private String type;

    public Attribute(String name, String type) {
        this.name = name;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
