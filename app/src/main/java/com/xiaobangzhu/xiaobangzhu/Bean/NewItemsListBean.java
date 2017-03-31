package com.xiaobangzhu.xiaobangzhu.Bean;

/**
 * NewItemsListBean
 *
 * @author: MurphySL
 * @time: 2017/3/25 20:29
 */


public class NewItemsListBean {

    /**
     * id : 4
     * name : 口香糖
     * category : 零食
     * pic : http://omt7jzat2.bkt.clouddn.com/AD1489545372496.jpg
     * pricep : 4
     * priceg : 3
     * pricew : 2
     * priceh : 1
     * freenump : 1
     * freenumg : 2
     * freenumw : 3
     * freenumh : 4
     * createtime : Mar 13, 2017 5:46:13 PM
     * note : 扣下
     * payItemsNum : 0
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
    private int payItemsNum;

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

    public int getPayItemsNum() {
        return payItemsNum;
    }

    public void setPayItemsNum(int payItemsNum) {
        this.payItemsNum = payItemsNum;
    }

    @Override
    public String toString() {
        return "NewItemsListBean{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", category='" + category + '\'' +
                ", pic='" + pic + '\'' +
                ", pricep=" + pricep +
                ", priceg=" + priceg +
                ", pricew=" + pricew +
                ", priceh=" + priceh +
                ", freenump=" + freenump +
                ", freenumg=" + freenumg +
                ", freenumw=" + freenumw +
                ", freenumh=" + freenumh +
                ", createtime='" + createtime + '\'' +
                ", note='" + note + '\'' +
                ", payItemsNum=" + payItemsNum +
                '}';
    }
}
