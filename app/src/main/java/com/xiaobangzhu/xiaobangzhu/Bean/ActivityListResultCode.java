package com.xiaobangzhu.xiaobangzhu.Bean;

import java.util.List;

/**
 * Created by WQC on 2016/10/12.
 */
public class ActivityListResultCode {
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
        private String address;

        private int college_id;

        private int person_num;

        private String apply_endtime;

        private String end_time;

        private String begin_time;

        private String c_time;

        private String title;

        private String content;

        private String picture;

        private String tags;

        private int user_id;

        private String header;

        private int id;

        private String poster;

        public void setAddress(String address){
            this.address = address;
        }
        public String getAddress(){
            return this.address;
        }
        public void setCollege_id(int college_id){
            this.college_id = college_id;
        }
        public int getCollege_id(){
            return this.college_id;
        }
        public void setPerson_num(int person_num){
            this.person_num = person_num;
        }
        public int getPerson_num(){
            return this.person_num;
        }
        public void setApply_endtime(String apply_endtime){
            this.apply_endtime = apply_endtime;
        }
        public String getApply_endtime(){
            return this.apply_endtime;
        }
        public void setEnd_time(String end_time){
            this.end_time = end_time;
        }
        public String getEnd_time(){
            return this.end_time;
        }
        public void setBegin_time(String begin_time){
            this.begin_time = begin_time;
        }
        public String getBegin_time(){
            return this.begin_time;
        }
        public void setC_time(String c_time){
            this.c_time = c_time;
        }
        public String getC_time(){
            return this.c_time;
        }
        public void setTitle(String title){
            this.title = title;
        }
        public String getTitle(){
            return this.title;
        }
        public void setContent(String content){
            this.content = content;
        }
        public String getContent(){
            return this.content;
        }
        public void setPicture(String picture){
            this.picture = picture;
        }
        public String getPicture(){
            return this.picture;
        }
        public void setTags(String tags){
            this.tags = tags;
        }
        public String getTags(){
            return this.tags;
        }
        public void setUser_id(int user_id){
            this.user_id = user_id;
        }
        public int getUser_id(){
            return this.user_id;
        }
        public void setHeader(String header){
            this.header = header;
        }
        public String getHeader(){
            return this.header;
        }
        public void setId(int id){
            this.id = id;
        }
        public int getId(){
            return this.id;
        }
        public void setPoster(String poster){
            this.poster = poster;
        }
        public String getPoster(){
            return this.poster;
        }

    }

}
