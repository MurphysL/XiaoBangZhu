package com.xiaobangzhu.xiaobangzhu.Bean;

import org.greenrobot.greendao.annotation.*;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT. Enable "keep" sections if you want to edit.

/**
 * Entity mapped to table "NOTIFICATION".
 */
@Entity
public class Notification {

    @Id
    private Long id;

    @NotNull
    private String message;
    private String extras;
    private long time;
    private boolean isRead;

    @Generated
    public Notification() {
    }

    public Notification(Long id) {
        this.id = id;
    }

    @Generated
    public Notification(Long id, String message, String extras, long time, boolean isRead) {
        this.id = id;
        this.message = message;
        this.extras = extras;
        this.time = time;
        this.isRead = isRead;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @NotNull
    public String getMessage() {
        return message;
    }

    /** Not-null value; ensure this value is available before it is saved to the database. */
    public void setMessage(@NotNull String message) {
        this.message = message;
    }

    public String getExtras() {
        return extras;
    }

    public void setExtras(String extras) {
        this.extras = extras;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public boolean getIsRead() {
        return isRead;
    }

    public void setIsRead(boolean isRead) {
        this.isRead = isRead;
    }

}
