package com.xiaobangzhu.xiaobangzhu.db;

import java.util.Map;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.AbstractDaoSession;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.identityscope.IdentityScopeType;
import org.greenrobot.greendao.internal.DaoConfig;

import com.xiaobangzhu.xiaobangzhu.Bean.Notification;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see org.greenrobot.greendao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig notificationDaoConfig;

    private final NotificationDao notificationDao;

    public DaoSession(Database db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        notificationDaoConfig = daoConfigMap.get(NotificationDao.class).clone();
        notificationDaoConfig.initIdentityScope(type);

        notificationDao = new NotificationDao(notificationDaoConfig, this);

        registerDao(Notification.class, notificationDao);
    }
    
    public void clear() {
        notificationDaoConfig.clearIdentityScope();
    }

    public NotificationDao getNotificationDao() {
        return notificationDao;
    }

}
