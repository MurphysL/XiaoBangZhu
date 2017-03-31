package com.xiaobangzhu.xiaobangzhu.Bean;

import java.util.List;

/**
 * GetAllItemsCode
 *
 * @author: MurphySL
 * @time: 2017/3/16 18:39
 */


public class GetAllItemsCode {

    /**
     * itemsList : [{"id":3,"name":"薯片","category":"零食","pic":"","pricep":4,"priceg":3,"pricew":2,"priceh":1,"freenump":4,"freenumg":3,"freenumw":2,"freenumh":1,"createtime":"Mar 10, 2017 3:14:51 AM","note":"23412"},{"id":2,"name":"棒棒糖","category":"零食","pic":"","pricep":4,"priceg":4,"pricew":4,"priceh":4,"freenump":2,"freenumg":2,"freenumw":2,"freenumh":2,"createtime":"Mar 10, 2017 1:40:04 AM","note":"很二"},{"id":1,"name":"冰激凌","category":"零食","pic":"http://obfj79jro.bkt.clouddn.com/AD1477489259028.jpg","pricep":3,"priceg":3,"pricew":3,"priceh":3,"freenump":4,"freenumg":3,"freenumw":2,"freenumh":1,"createtime":"Mar 10, 2017 1:39:35 AM","note":"好吃的很"}]
     * state : 0
     */

    private int state;
    private List<NewItemsListBean> newItemsList;

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public List<NewItemsListBean> getNewItemsList() {
        return newItemsList;
    }

    public void setNewItemsList(List<NewItemsListBean> newItemsList) {
        this.newItemsList = newItemsList;
    }

}
