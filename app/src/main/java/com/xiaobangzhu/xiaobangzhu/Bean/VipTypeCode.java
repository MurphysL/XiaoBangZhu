package com.xiaobangzhu.xiaobangzhu.Bean;

import java.util.List;

/**
 * VipTypeCode
 *
 * @author: MurphySL
 * @time: 2016/11/22 13:25
 */


public class VipTypeCode {
    /**
     * data : [{"name":"普通会员","price":10,"id":1},{"name":"高级会员","price":20,"id":2},{"name":"王冠会员","price":30,"id":3},{"name":"皇冠会员","price":40,"id":4}]
     * desc : 成功
     * status : 0
     */

    private String desc;
    private int status;
    private List<Data> data;

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

    public List<Data> getData() {
        return data;
    }

    public void setData(List<Data> data) {
        this.data = data;
    }

    public static class Data {
        /**
         * name : 普通会员
         * price : 10
         * id : 1
         */

        private String name;
        private int price;
        private int id;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getPrice() {
            return price;
        }

        public void setPrice(int price) {
            this.price = price;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }
    }

}
