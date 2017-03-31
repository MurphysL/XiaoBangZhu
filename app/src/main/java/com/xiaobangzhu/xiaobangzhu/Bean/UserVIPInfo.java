package com.xiaobangzhu.xiaobangzhu.Bean;

/**
 * UserVIPInfo
 *
 * @author: MurphySL
 * @time: 2017/3/31 14:42
 */


public class UserVIPInfo {

    /**
     * uid : 18435186562
     * vipuserid : 6
     * endtime : Jun 11, 2017
     * viptype : 3
     * starttime : Mar 11, 2017
     * expressnum : 2
     * status : 0
     */

    private long uid;
    private int vipuserid;
    private String endtime;
    private int viptype;
    private String starttime;
    private int expressnum;
    private int status;

    public long getUid() {
        return uid;
    }

    public void setUid(long uid) {
        this.uid = uid;
    }

    public int getVipuserid() {
        return vipuserid;
    }

    public void setVipuserid(int vipuserid) {
        this.vipuserid = vipuserid;
    }

    public String getEndtime() {
        return endtime;
    }

    public void setEndtime(String endtime) {
        this.endtime = endtime;
    }

    public int getViptype() {
        return viptype;
    }

    public void setViptype(int viptype) {
        this.viptype = viptype;
    }

    public String getStarttime() {
        return starttime;
    }

    public void setStarttime(String starttime) {
        this.starttime = starttime;
    }

    public int getExpressnum() {
        return expressnum;
    }

    public void setExpressnum(int expressnum) {
        this.expressnum = expressnum;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "UserVIPInfo{" +
                "uid=" + uid +
                ", vipuserid=" + vipuserid +
                ", endtime='" + endtime + '\'' +
                ", viptype=" + viptype +
                ", starttime='" + starttime + '\'' +
                ", expressnum=" + expressnum +
                ", status=" + status +
                '}';
    }
}
