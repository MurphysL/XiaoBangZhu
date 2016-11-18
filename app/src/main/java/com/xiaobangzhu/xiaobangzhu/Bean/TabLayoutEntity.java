package com.xiaobangzhu.xiaobangzhu.Bean;

import com.flyco.tablayout.listener.CustomTabEntity;

/**
 * TabLayoutEntity
 *
 * @author: MurphySL
 * @time: 2016/11/18 16:20
 */


public class TabLayoutEntity implements CustomTabEntity {
    public String title;

    public TabLayoutEntity(String title) {
        this.title = title;
    }

    @Override
    public String getTabTitle() {
        return title;
    }

    @Override
    public int getTabSelectedIcon() {
        return 0;
    }

    @Override
    public int getTabUnselectedIcon() {
        return 0;
    }
}
