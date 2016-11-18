package com.xiaobangzhu.xiaobangzhu.UI.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.PointerIcon;
import android.widget.TextView;

import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.SlidingTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.xiaobangzhu.xiaobangzhu.Bean.Notification;
import com.xiaobangzhu.xiaobangzhu.Bean.TabLayoutEntity;
import com.xiaobangzhu.xiaobangzhu.MyApplication;
import com.xiaobangzhu.xiaobangzhu.R;
import com.xiaobangzhu.xiaobangzhu.UI.fragment.MsgFragment;
import com.xiaobangzhu.xiaobangzhu.UI.fragment.NotificationFragment;
import com.xiaobangzhu.xiaobangzhu.db.DaoMaster;
import com.xiaobangzhu.xiaobangzhu.db.NotificationDao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * MessageActivity
 *
 * @author: MurphySL
 * @time: 2016/11/13 14:28
 */


public class MessageActivity extends AppCompatActivity {
    private static final String TAG = "MessageActivity";

    private SlidingTabLayout tabLayout2;
    private ViewPager viewPager;
    private ArrayList<Fragment> list = new ArrayList<>();
    private TextView title;

    private String[] titles = {"与我相关" , "系统通知"};
    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();
    private List<Notification> notificationList;
    private NotificationDao dao;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_msg);

        initView();
        initFragment();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(notificationList!=null && notificationList.size() != 0){
            Iterator<Notification> iterator = notificationList.iterator();
            while (iterator.hasNext()){
                Notification notification = iterator.next();
                if(notification != null){
                    notification.setIsRead(true);
                    dao.update(notification);
                }
            }
        }

    }

    private void initFragment() {

        list.add(new MsgFragment());
        list.add(new NotificationFragment());

        for(int i = 0 ; i < list.size() ; i++){
            mTabEntities.add(new TabLayoutEntity(titles[i]));
        }

        viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public int getCount() {
                return list.size();
            }

            @Override
            public android.support.v4.app.Fragment getItem(int position) {
                Log.i(TAG, "getItem: " + position);
                return list.get(position);
            }

        });

        tabLayout2.setViewPager(viewPager , titles);
        dao = MyApplication.getInstance().getDao();

        notificationList = dao.queryBuilder()
                // 查询的条件
                .where(NotificationDao.Properties.IsRead.eq(false))
                .list();
        if(notificationList != null && notificationList.size() != 0){
            tabLayout2.showMsg(2 , notificationList.size());
            tabLayout2.setMsgMargin(2 , 66 , 10);
        }

    }

    private void initView() {
        tabLayout2 = (SlidingTabLayout) findViewById(R.id.tab_layout1 );
        viewPager = (ViewPager) findViewById(R.id.msg_viewpager);
        title = (TextView) findViewById(R.id.web_title);
        title.setText("我的消息");
    }
}
