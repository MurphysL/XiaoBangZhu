package com.xiaobangzhu.xiaobangzhu.UI.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.xiaobangzhu.xiaobangzhu.R;

/**
 * MessageActivity
 *
 * @author: MurphySL
 * @time: 2016/11/13 14:28
 */


public class MessageActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_msg);

        initView();
    }

    private void initView() {
        tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        viewPager = (ViewPager) findViewById(R.id.msg_viewpager);

    }
}
