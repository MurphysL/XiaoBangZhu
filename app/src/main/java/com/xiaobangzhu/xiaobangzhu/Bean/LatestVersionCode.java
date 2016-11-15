package com.xiaobangzhu.xiaobangzhu.Bean;

/**
 * LatestVersionCode
 *
 * @author: MurphySL
 * @time: 2016/11/15 18:50
 */


public class LatestVersionCode {
    private Data data;
    private String desc;
    private int status;

    public Data getData() {
        return data;
    }

    public void setData(Data dat) {
        this.data = dat;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public class Data{
        private String c_time;
        private int id;
        private String log;
        private String version;

        public String getC_time() {
            return c_time;
        }

        public void setC_time(String c_time) {
            this.c_time = c_time;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getLog() {
            return log;
        }

        public void setLog(String log) {
            this.log = log;
        }

        public String getVersion() {
            return version;
        }

        public void setVersion(String version) {
            this.version = version;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        private String url;
    }
}
