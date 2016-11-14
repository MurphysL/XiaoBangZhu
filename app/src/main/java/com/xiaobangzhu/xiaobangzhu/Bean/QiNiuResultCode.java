package com.xiaobangzhu.xiaobangzhu.Bean;

/**
 * Created by WQC on 2016/10/3.
 */

public class QiNiuResultCode {

    private Data data;

    private String desc;

    private int status;

    public void setData(Data data){
        this.data = data;
    }
    public Data getData(){
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
        private String token;

        public void setToken(String token){
            this.token = token;
        }
        public String getToken(){
            return this.token;
        }

    }
}
