package com.xiaobangzhu.xiaobangzhu.Bean;

import java.util.List;

/**
 * Created by WQC on 2016/10/14.
 */

public class GetExressCompanyResultCode {

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
        private String name;

        private int id;

        public void setName(String name){
            this.name = name;
        }
        public String getName(){
            return this.name;
        }
        public void setId(int id){
            this.id = id;
        }
        public int getId(){
            return this.id;
        }

    }
}
