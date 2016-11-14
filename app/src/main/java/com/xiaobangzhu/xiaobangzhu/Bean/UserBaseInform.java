package com.xiaobangzhu.xiaobangzhu.Bean;


/**
 * Created by WQC on 2016/7/14.
 */
public class UserBaseInform {
    //private InitImageResultCode.Data data;
    private Data data;

    private String desc;

    private String status;

    /*public void setData(InitImageResultCode.Data data) {
        this.data = data;
    }

    public InitImageResultCode.Data getData() {
        return this.data;
    }*/
    public void setData(Data data){
        this.data = data;
    }

    public Data getData(){
        return data;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return this.desc;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return this.status;
    }


    public class Data {
        private String login_id;

        private String identified;

        private int college_id;

        private int level;

        private String signature;

        private String nick_name;

        private String sex;

        private String c_time;

        private String header;

        private int id;

        private String type;

        private String major;

        private String college_name;

        public void setLogin_id(String login_id) {
            this.login_id = login_id;
        }

        public String getLogin_id() {
            return this.login_id;
        }

        public void setIdentified(String identified) {
            this.identified = identified;
        }

        public String getIdentified() {
            return this.identified;
        }

        public void setCollege_id(int college_id) {
            this.college_id = college_id;
        }

        public int getCollege_id() {
            return this.college_id;
        }

        public void setLevel(int level) {
            this.level = level;
        }

        public int getLevel() {
            return this.level;
        }

        public void setSignature(String signature) {
            this.signature = signature;
        }

        public String getSignature() {
            return this.signature;
        }

        public void setNick_name(String nick_name) {
            this.nick_name = nick_name;
        }

        public String getNick_name() {
            return this.nick_name;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public String getSex() {
            return this.sex;
        }

        public void setC_time(String c_time) {
            this.c_time = c_time;
        }

        public String getC_time() {
            return this.c_time;
        }

        public void setHeader(String header) {
            this.header = header;
        }

        public String getHeader() {
            return this.header;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getId() {
            return this.id;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getType() {
            return this.type;
        }

        public void setMajor(String major){
            this.major = major;
        }

        public String getMajor(){
            return major;
        }

        public String getCollege_name() {
            return college_name;
        }

        public void setCollege_name(String college_name) {
            this.college_name = college_name;
        }
    }
}
