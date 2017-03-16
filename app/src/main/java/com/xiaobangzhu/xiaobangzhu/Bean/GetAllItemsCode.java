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
    private List<ItemsListBean> itemsList;

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public List<ItemsListBean> getItemsList() {
        return itemsList;
    }

    public void setItemsList(List<ItemsListBean> itemsList) {
        this.itemsList = itemsList;
    }

    public static class ItemsListBean {
        /**
         * id : 3
         * name : 薯片
         * category : 零食
         * pic :
         * pricep : 4
         * priceg : 3
         * pricew : 2
         * priceh : 1
         * freenump : 4
         * freenumg : 3
         * freenumw : 2
         * freenumh : 1
         * createtime : Mar 10, 2017 3:14:51 AM
         * note : 23412
         */

        private int id;
        private String name;
        private String category;
        private String pic;
        private int pricep;
        private int priceg;
        private int pricew;
        private int priceh;
        private int freenump;
        private int freenumg;
        private int freenumw;
        private int freenumh;
        private String createtime;
        private String note;

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

        public String getPic() {
            return pic;
        }

        public void setPic(String pic) {
            this.pic = pic;
        }

        public int getPricep() {
            return pricep;
        }

        public void setPricep(int pricep) {
            this.pricep = pricep;
        }

        public int getPriceg() {
            return priceg;
        }

        public void setPriceg(int priceg) {
            this.priceg = priceg;
        }

        public int getPricew() {
            return pricew;
        }

        public void setPricew(int pricew) {
            this.pricew = pricew;
        }

        public int getPriceh() {
            return priceh;
        }

        public void setPriceh(int priceh) {
            this.priceh = priceh;
        }

        public int getFreenump() {
            return freenump;
        }

        public void setFreenump(int freenump) {
            this.freenump = freenump;
        }

        public int getFreenumg() {
            return freenumg;
        }

        public void setFreenumg(int freenumg) {
            this.freenumg = freenumg;
        }

        public int getFreenumw() {
            return freenumw;
        }

        public void setFreenumw(int freenumw) {
            this.freenumw = freenumw;
        }

        public int getFreenumh() {
            return freenumh;
        }

        public void setFreenumh(int freenumh) {
            this.freenumh = freenumh;
        }

        public String getCreatetime() {
            return createtime;
        }

        public void setCreatetime(String createtime) {
            this.createtime = createtime;
        }

        public String getNote() {
            return note;
        }

        public void setNote(String note) {
            this.note = note;
        }
    }
}
