package com.xiaobangzhu.xiaobangzhu.Bean;

/**
 * Created by WQC on 2016/7/27.
 */
public class LoginResultCode {
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
        private int attentionNum;

        private String login_id;

        private int fansNum;

        private int college_id;

        private String college_name;

        private int level;

        private String signature;

        private String sex;

        private String c_time;

        private String type;

        private String token;

        private String identified;

        private String major;

        private String background;

        private String nick_name;

        private int deposit;

        private String header;

        private int id;

        private int loanlimit;

        private int live = 2592000;

        public void setAttentionNum(int attentionNum){
            this.attentionNum = attentionNum;
        }
        public int getAttentionNum(){
            return this.attentionNum;
        }
        public void setLogin_id(String login_id){
            this.login_id = login_id;
        }
        public String getLogin_id(){
            return this.login_id;
        }
        public void setFansNum(int fansNum){
            this.fansNum = fansNum;
        }
        public int getFansNum(){
            return this.fansNum;
        }
        public void setCollege_id(int college_id){
            this.college_id = college_id;
        }
        public int getCollege_id(){
            return this.college_id;
        }
        public void setCollege_name(String college_name){
            this.college_name = college_name;
        }
        public String getCollege_name(){
            return this.college_name;
        }
        public void setLevel(int level){
            this.level = level;
        }
        public int getLevel(){
            return this.level;
        }
        public void setSignature(String signature){
            this.signature = signature;
        }
        public String getSignature(){
            return this.signature;
        }
        public void setSex(String sex){
            this.sex = sex;
        }
        public String getSex(){
            return this.sex;
        }
        public void setC_time(String c_time){
            this.c_time = c_time;
        }
        public String getC_time(){
            return this.c_time;
        }
        public void setType(String type){
            this.type = type;
        }
        public String getType(){
            return this.type;
        }
        public void setToken(String token){
            this.token = token;
        }
        public String getToken(){
            return this.token;
        }
        public void setIdentified(String identified){
            this.identified = identified;
        }
        public String getIdentified(){
            return this.identified;
        }
        public void setMajor(String major){
            this.major = major;
        }
        public String getMajor(){
            return this.major;
        }
        public void setBackground(String background){
            this.background = background;
        }
        public String getBackground(){
            return this.background;
        }
        public void setNick_name(String nick_name){
            this.nick_name = nick_name;
        }
        public String getNick_name(){
            return this.nick_name;
        }
        public void setDeposit(int deposit){
            this.deposit = deposit;
        }
        public int getDeposit(){
            return this.deposit;
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
        public void setLoanlimit(int loanlimit){
            this.loanlimit = loanlimit;
        }
        public int getLoanlimit(){
            return this.loanlimit;
        }
        public void setLive(int live){
            this.live = live;
        }
        public int getLive(){
            return this.live;
        }

    }


}
