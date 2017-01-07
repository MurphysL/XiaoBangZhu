package com.xiaobangzhu.xiaobangzhu.Bean;

import java.util.List;

/**
 * VipTypeCode
 *
 * @author: MurphySL
 * @time: 2016/11/22 13:25
 */


public class VipTypeCode {

    private List<Data> data;
    private String desc;
    private int status;

    public class Data{
        private String name;
        private int price;
        private int id;
    }

    public void setData(List<Data> data) {
        this.data = data;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
