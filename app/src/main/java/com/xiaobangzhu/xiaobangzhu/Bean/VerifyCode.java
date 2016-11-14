package com.xiaobangzhu.xiaobangzhu.Bean;

/**
 * Created by WQC on 2016/7/26.
 */
public class VerifyCode{
    //request result
    private String desc;
    //request data
    private Data data;
    //request result code
    private String status;



    public String getDesc(){
        return this.desc;
    }
    public Data getData(){
        return this.data;
    }
    public String getStatus(){
        return this.status;
    }

    public class Data {
        private String code;
        public String getCode(){
            return this.code;
        }
    }
}
