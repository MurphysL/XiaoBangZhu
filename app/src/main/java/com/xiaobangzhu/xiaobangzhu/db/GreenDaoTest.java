package com.xiaobangzhu.xiaobangzhu.db;


import org.greenrobot.greendao.generator.DaoGenerator;
import org.greenrobot.greendao.generator.Entity;
import org.greenrobot.greendao.generator.Schema;

/**
 * GreenDao
 *
 * @author: MurphySL
 * @time: 2016/11/15 12:48
 */


public class GreenDaoTest {

    public static void main(String[] args){
        Schema schema = new Schema(0 , "com.xiaobangzhu.xiaobangzhu");
        Entity notification = schema.addEntity("Notification");
        notification.addIdProperty();
        notification.addStringProperty("message").notNull();
        notification.addStringProperty("extras");

        try {
            new DaoGenerator().generateAll(schema , "e:/XiaoBangZhu/XiaoBangZhu/app/src/main/java/com/xiaobangzhu/" +
                    "xiaobangzhu/db/entity");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
