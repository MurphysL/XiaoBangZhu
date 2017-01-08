package com.xiaobangzhu.xiaobangzhu.UI.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.flyco.tablayout.SlidingTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.xiaobangzhu.xiaobangzhu.Adapter.GoodsListRecycleAdapter;
import com.xiaobangzhu.xiaobangzhu.Bean.TabLayoutEntity;
import com.xiaobangzhu.xiaobangzhu.MyApplication;
import com.xiaobangzhu.xiaobangzhu.R;
import com.xiaobangzhu.xiaobangzhu.UI.fragment.MsgFragment;
import com.xiaobangzhu.xiaobangzhu.UI.fragment.NotificationFragment;
import com.xiaobangzhu.xiaobangzhu.UI.fragment.ShopDrinkFragment;
import com.xiaobangzhu.xiaobangzhu.UI.fragment.ShopHealthFragment;
import com.xiaobangzhu.xiaobangzhu.UI.fragment.ShopSnackFragment;
import com.xiaobangzhu.xiaobangzhu.View.DividerItemDecoration;

import java.util.ArrayList;
import java.util.List;

/**
 * ShopActivity
 *
 * @author: MurphySL
 * @time: 2016/12/31 12:03
 */


public class ShopActivity extends AppCompatActivity {
    private static final String TAG = "shopViewPager";

    private ImageView back;

    private SlidingTabLayout tabLayout;
    private String[] titles = {"饮品" , "小吃","健康餐"};
    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();
    private ViewPager shopViewPager;

    private ArrayList<Fragment> list = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_shop);
        initView();

        initEvent();
    }

    private void initEvent() {
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
       /* refreshLayout.setColorSchemeColors(Color.BLUE,Color.BLACK,Color.RED);
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                data.clear();
                //NetRequestManager.getInstance().getActivityList(MyApplication.getInstance().getUserToken(),BaseUrlManager.getUrlForGetActivityList(MyApplication.getInstance().getUserCollegeId(),0,10));
            }
        });

        refreshBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyApplication.showProgress(ShopActivity.this,"加载中","请稍等");
                Timer timer = new Timer();
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        MyApplication.dismissProgress();
                    }
                },8000);
                //NetRequestManager.getInstance().getActivityList(MyApplication.getInstance().getUserToken(),BaseUrlManager.getUrlForGetNewsList(MyApplication.getInstance().getUserCollegeId(),0,15));
            }
        });*/

        /*adapter.setOnItemClickListener(new ActivityListRecycleAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(ActivityListRecycleAdapter.ViewHolder itemView, ActivityListResultCode.Data data, int position) {
                Log.i(TAG, "onItemClick:  is clicked ");
                Intent intent  = new Intent(getContext(), WebActivity.class);
                Bundle bundle = new Bundle();
                Log.i(TAG, "onItemClick: "+data.getId());
                bundle.putString("title", "详情");
                bundle.putString("url", HtmlManager.getmInstance().getUrlForActivityDetail(MyApplication.getInstance().getUserToken(), data.getId()));
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        activityListRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                LinearLayoutManager layoutManager = (LinearLayoutManager) activityListRecyclerView.getLayoutManager();
                int totalItemCount = layoutManager.getItemCount();
                int lastVisibleItem = layoutManager.findLastVisibleItemPosition();

                if (!activityListRecycleAdapter.isLoading() && totalItemCount < (lastVisibleItem + 3)) {
                    activityListRecycleAdapter.setLoading(true);
                    NetRequestManager.getInstance().getActivityList(MyApplication.getInstance().getUserToken(),BaseUrlManager.getUrlForGetActivityList(MyApplication.getInstance().getUserCollegeId(),activityListRecycleAdapter.getBottomId(lastVisibleItem)+1,5));
                }

            }
        });*/
    }

    private void initView() {
        tabLayout = (SlidingTabLayout) findViewById(R.id.shop_tab_layout);
        shopViewPager = (ViewPager) findViewById(R.id.shop_viewpager);
        back = (ImageView) findViewById(R.id.activity_join_member_back_icon);

        list.add(new ShopDrinkFragment());
        list.add(new ShopSnackFragment());
        list.add(new ShopHealthFragment());


        for(int i = 0 ; i < list.size() ; i++){
            mTabEntities.add(new TabLayoutEntity(titles[i]));
        }

        shopViewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public int getCount() {
                Log.i(TAG, "getCount: " + list.size());
                return list.size();
            }

            @Override
            public android.support.v4.app.Fragment getItem(int position) {
                return list.get(position);
            }

        });

        tabLayout.setViewPager(shopViewPager , titles);
    }
}
