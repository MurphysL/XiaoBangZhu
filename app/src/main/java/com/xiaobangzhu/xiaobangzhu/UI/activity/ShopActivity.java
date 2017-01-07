package com.xiaobangzhu.xiaobangzhu.UI.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.xiaobangzhu.xiaobangzhu.Adapter.GoodsListRecycleAdapter;
import com.xiaobangzhu.xiaobangzhu.MyApplication;
import com.xiaobangzhu.xiaobangzhu.R;
import com.xiaobangzhu.xiaobangzhu.View.DividerItemDecoration;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * ShopActivity
 *
 * @author: MurphySL
 * @time: 2016/12/31 12:03
 */


public class ShopActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private SwipeRefreshLayout refreshLayout;
    private TextView refreshBtn;
    private GoodsListRecycleAdapter adapter;
    private List data;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_shop);
        initView();

        adapter= new GoodsListRecycleAdapter(this , data);
        recyclerView.setLayoutManager(new GridLayoutManager(this , 2));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST));
        recyclerView.setAdapter(adapter);
        if (adapter.getItemCount() == 0) {
            recyclerView.setVisibility(View.GONE);
            refreshBtn.setVisibility(View.VISIBLE);
        }

        //initEvent();
    }

    private void initEvent() {
        refreshLayout.setColorSchemeColors(Color.BLUE,Color.BLACK,Color.RED);
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
        });

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
        recyclerView = (RecyclerView) findViewById(R.id.shop_list);
        refreshLayout = (SwipeRefreshLayout) findViewById(R.id.shop_pull_refresh);
        refreshBtn = (TextView) findViewById(R.id.tab_home_shoplist_refresh);
    }
}
