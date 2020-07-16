package com.jyami.springbootgettingstartedmaven.webMVC.user;

/**
 * Created by jyami on 2020/07/16
 */
public class Sample {
    private String prefix;
    private String name;

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return prefix+ " " + name;
    }
}
