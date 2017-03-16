package com.xiaobangzhu.xiaobangzhu.Bean;

import java.util.List;

/**
 * GetAllOrdersByUidCode
 *
 * @author: MurphySL
 * @time: 2017/3/16 18:41
 */


public class GetAllOrdersByUidCode {
    /**
     * allOrders : [{"uid":2,"number":"061aaca5-4aa8-4cb4-ba28-d4b4eabf8a72","money":6,"paystate":0,"note":"\"beizhu\"","createtime":"Mar 11, 2017 1:21:18 PM"}]
     * state : 0
     */

    private int state;
    private List<AllOrdersBean> allOrders;

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public List<AllOrdersBean> getAllOrders() {
        return allOrders;
    }

    public void setAllOrders(List<AllOrdersBean> allOrders) {
        this.allOrders = allOrders;
    }

    public static class AllOrdersBean {
        /**
         * uid : 2
         * number : 061aaca5-4aa8-4cb4-ba28-d4b4eabf8a72
         * money : 6
         * paystate : 0
         * note : "beizhu"
         * createtime : Mar 11, 2017 1:21:18 PM
         */

        private int uid;
        private String number;
        private int money;
        private int paystate;
        private String note;
        private String createtime;

        public int getUid() {
            return uid;
        }

        public void setUid(int uid) {
            this.uid = uid;
        }

        public String getNumber() {
            return number;
        }

        public void setNumber(String number) {
            this.number = number;
        }

        public int getMoney() {
            return money;
        }

        public void setMoney(int money) {
            this.money = money;
        }

        public int getPaystate() {
            return paystate;
        }

        public void setPaystate(int paystate) {
            this.paystate = paystate;
        }

        public String getNote() {
            return note;
        }

        public void setNote(String note) {
            this.note = note;
        }

        public String getCreatetime() {
            return createtime;
        }

        public void setCreatetime(String createtime) {
            this.createtime = createtime;
        }
    }
}
