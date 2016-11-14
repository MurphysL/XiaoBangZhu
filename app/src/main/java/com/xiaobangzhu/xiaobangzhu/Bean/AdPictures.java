package com.xiaobangzhu.xiaobangzhu.Bean;

import java.util.List;

/**
 * Created by WQC on 2016/8/13.
 */
public class AdPictures {
    private List<Data> data ;

    private String desc;

    private int status;

    public void setData(List<Data> data){
        this.data = data;
    }
    public List<Data> getData(){
        return this.data;
    }
    public void setDesc(String desc){
        this.desc = desc;
    }
    public String getDesc(){
        return this.desc;
    }
    public void setStatus(int status){
        this.status = status;
    }
    public int getStatus(){
        return this.status;
    }



    public class Data {
        private String c_time;

        private String href;

        private int id;

        private String picture;

        public void setC_time(String c_time){
            this.c_time = c_time;
        }
        public String getC_time(){
            return this.c_time;
        }
        public void setHref(String href){
            this.href = href;
        }
        public String getHref(){
            return this.href;
        }
        public void setId(int id){
            this.id = id;
        }
        public int getId(){
            return this.id;
        }
        public void setPicture(String picture){
            this.picture = picture;
        }
        public String getPicture(){
            return this.picture;
        }

    }
}


