package com.xiaobangzhu.xiaobangzhu.Bean;

import java.util.List;

/**
 * Created by WQC on 2016/8/13.
 */
public class NewsListResultCode {
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
        private int read_num;

        private int comments;

        private int user_id;

        private String nick_name;

        private String picture1;

        private String c_time;

        private String header;

        private int id;

        private String title;

        private String content;

        private String picture3;

        private String picture2;

        public void setRead_num(int read_num){
            this.read_num = read_num;
        }
        public int getRead_num(){
            return this.read_num;
        }
        public void setComments(int comments){
            this.comments = comments;
        }
        public int getComments(){
            return this.comments;
        }
        public void setUser_id(int user_id){
            this.user_id = user_id;
        }
        public int getUser_id(){
            return this.user_id;
        }
        public void setNick_name(String nick_name){
            this.nick_name = nick_name;
        }
        public String getNick_name(){
            return this.nick_name;
        }
        public void setPicture1(String picture1){
            this.picture1 = picture1;
        }
        public String getPicture1(){
            return this.picture1;
        }
        public void setC_time(String c_time){
            this.c_time = c_time;
        }
        public String getC_time(){
            return this.c_time;
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
        public void setPicture3(String picture3){
            this.picture3 = picture3;
        }
        public String getPicture3(){
            return this.picture3;
        }
        public void setPicture2(String picture2){
            this.picture2 = picture2;
        }
        public String getPicture2(){
            return this.picture2;
        }


    }

}
