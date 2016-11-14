package com.xiaobangzhu.xiaobangzhu.Bean;

import java.util.List;

/**
 * Created by WQC on 2016/7/28.
 */
public class TangledItem{
    private List<Data> data ;

    private String desc;

    private String status;

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
    public void setStatus(String status){
        this.status = status;
    }
    public String getStatus(){
        return this.status;
    }

    public class Data {
        private int level;

        private int user_id;

        private String c_time;

        private String header;

        private int id;

        private String type;

        private String choice1;

        private String content;

        private String choice2;

        public void setLevel(int level){
            this.level = level;
        }
        public int getLevel(){
            return this.level;
        }
        public void setUser_id(int user_id){
            this.user_id = user_id;
        }
        public int getUser_id(){
            return this.user_id;
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
        public void setType(String type){
            this.type = type;
        }
        public String getType(){
            return this.type;
        }
        public void setChoice1(String choice1){
            this.choice1 = choice1;
        }
        public String getChoice1(){
            return this.choice1;
        }
        public void setContent(String content){
            this.content = content;
        }
        public String getContent(){
            return this.content;
        }
        public void setChoice2(String choice2){
            this.choice2 = choice2;
        }
        public String getChoice2(){
            return this.choice2;
        }

    }

}
