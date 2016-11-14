package com.xiaobangzhu.xiaobangzhu.Bean;

/**
 * Author: WQC
 * Created by: ModelGenerator on 2016/10/23
 */
public class InitImageResultCode {
    private Data data;
    private String desc;
    private int status;

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
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

    /**
     * Author: WQC
     * Created by: ModelGenerator on 2016/10/23
     */
    public class Data {
        private String name;
        private int id;
        private int isOpen;
        private String url;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getIsOpen() {
            return isOpen;
        }

        public void setIsOpen(int isOpen) {
            this.isOpen = isOpen;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}