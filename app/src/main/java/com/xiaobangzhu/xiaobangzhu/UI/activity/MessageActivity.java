package com.xiaobangzhu.xiaobangzhu.UI.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.xiaobangzhu.xiaobangzhu.R;
import com.xiaobangzhu.xiaobangzhu.UI.fragment.NotificationFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * MessageActivity
 *
 * @author: MurphySL
 * @time: 2016/11/13 14:28
 */


public class MessageActivity extends AppCompatActivity {
    private static final String TAG = "MessageActivity";

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private List<Fragment> list = new ArrayList<>();
    private TextView title;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_msg);

        initView();
        initEvent();
        initFragment();
    }

    private void initEvent() {
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
    }

    private void initFragment() {
        list.add(new Fragment());
        list.add(new NotificationFragment());

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

            @Override
            public CharSequence getPageTitle(int position) {
                return position == 0 ? "与我相关" : "系统通知";
            }

            /*@Override
            public boolean isViewFromObject(View view, Object object) {
                return view == object;
            }*/
        });

        tabLayout.setupWithViewPager(viewPager);
    }

    private void initView() {
        tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        viewPager = (ViewPager) findViewById(R.id.msg_viewpager);
        title = (TextView) findViewById(R.id.web_title);
        title.setText("我的消息");
    }
}
