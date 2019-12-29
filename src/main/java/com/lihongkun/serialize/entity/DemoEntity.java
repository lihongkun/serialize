package com.lihongkun.serialize.entity;

import java.io.Serializable;

/**
 * @author lihongkun
 */
public class DemoEntity implements Serializable {

    private int id;

    private String name;

    private String category;

    private Long price;

    private Long appId;

    private Long ctr;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public Long getAppId() {
        return appId;
    }

    public void setAppId(Long appId) {
        this.appId = appId;
    }

    public Long getCtr() {
        return ctr;
    }

    public void setCtr(Long ctr) {
        this.ctr = ctr;
    }

}
