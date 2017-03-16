package com.xiaobangzhu.xiaobangzhu.Bean;

import java.util.List;

/**
 * GetCartAllItemsCode
 *
 * @author: MurphySL
 * @time: 2017/3/16 18:40
 */


public class GetCartAllItemsCode {
    /**
     * sfmoney : 0
     * yfmoney : 4
     * cartItemsList : [{"id":19,"itemsid":2,"itemsnum":1}]
     * state : 0
     */

    private int sfmoney;
    private int yfmoney;
    private int state;
    private List<CartItemsListBean> cartItemsList;

    public int getSfmoney() {
        return sfmoney;
    }

    public void setSfmoney(int sfmoney) {
        this.sfmoney = sfmoney;
    }

    public int getYfmoney() {
        return yfmoney;
    }

    public void setYfmoney(int yfmoney) {
        this.yfmoney = yfmoney;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public List<CartItemsListBean> getCartItemsList() {
        return cartItemsList;
    }

    public void setCartItemsList(List<CartItemsListBean> cartItemsList) {
        this.cartItemsList = cartItemsList;
    }

    public static class CartItemsListBean {
        /**
         * id : 19
         * itemsid : 2
         * itemsnum : 1
         */

        private int id;
        private int itemsid;
        private int itemsnum;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getItemsid() {
            return itemsid;
        }

        public void setItemsid(int itemsid) {
            this.itemsid = itemsid;
        }

        public int getItemsnum() {
            return itemsnum;
        }

        public void setItemsnum(int itemsnum) {
            this.itemsnum = itemsnum;
        }
    }
}
