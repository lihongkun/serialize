package com.lihongkun.serialize.entity;

import java.io.Serializable;
import java.util.List;

/**
 * @author lihongkun
 */
public class DemoResponse implements Serializable {

    private int code;

    private List<DemoEntity> data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public List<DemoEntity> getData() {
        return data;
    }

    public void setData(List<DemoEntity> data) {
        this.data = data;
    }
}
