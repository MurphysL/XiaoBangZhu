package com.xiaobangzhu.xiaobangzhu.UI.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.xiaobangzhu.xiaobangzhu.Adapter.ActivityListRecycleAdapter;
import com.xiaobangzhu.xiaobangzhu.Interface.DataChangeListener;
import com.xiaobangzhu.xiaobangzhu.MyApplication;
import com.xiaobangzhu.xiaobangzhu.Bean.ActivityListResultCode;
import com.xiaobangzhu.xiaobangzhu.NetworkService.BaseUrlManager;
import com.xiaobangzhu.xiaobangzhu.NetworkService.HtmlManager;
import com.xiaobangzhu.xiaobangzhu.NetworkService.NetRequestManager;
import com.xiaobangzhu.xiaobangzhu.R;
import com.xiaobangzhu.xiaobangzhu.UI.activity.LaunchActivity;
import com.xiaobangzhu.xiaobangzhu.UI.activity.WebActivity;
import com.xiaobangzhu.xiaobangzhu.View.DividerItemDecoration;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by WQC on 2016/8/19.
 */
public class ActivityListFragment extends Fragment {
    private static final String TAG = "ActivityListFragment";
    View mRootView;
    RecyclerView activityListRecyclerView;
    ActivityListRecycleAdapter activityListRecycleAdapter;
    TextView refreshBtn;
    final List<ActivityListResultCode.Data> activityList = new ArrayList<ActivityListResultCode.Data>();
    ActivityListResultCode activityListResultCode;
    SwipeRefreshLayout refreshLayout;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityListRecycleAdapter = new ActivityListRecycleAdapter(getContext(),activityList);

        NetRequestManager.getInstance().getActivityList(MyApplication.getInstance().getUserToken(), BaseUrlManager.getUrlForGetActivityList(MyApplication.getInstance().getUserCollegeId(),activityList.size(),5));

        NetRequestManager.getInstance().setActivityListResultCodeListener(new DataChangeListener<ActivityListResultCode>() {
            @Override
            public void onSuccessful(ActivityListResultCode data) {
                Log.i("ActivityListFragment", data.getDesc());
                if (data != null) {
                    if (data.getStatus() == 0) {
                        if (data.getData().size() >= 0) {
                            activityListResultCode = data;
                            initActivityList();
                        }

                    }else{
                        activityListRecycleAdapter.setLoading(false);
                    }

                }

            }

            @Override
            public void onError(VolleyError volleyError) {
                MyApplication.showDialog(getContext(),"网络连到火星上了");
                refreshLayout.setRefreshing(false);
                activityListRecycleAdapter.setLoading(false);
            }

            @Override
            public void onResponseNull() {
                MyApplication.showDialog(getContext(),"暂时无法获取内容");
                refreshLayout.setRefreshing(false);
                activityListRecycleAdapter.setLoading(false);
            }
        });

    }

    /**
     * 初始化活动列表
     */
    private void initActivityList() {
        refreshLayout.setRefreshing(false);
        activityListRecycleAdapter.setLoading(false);
        if (activityListResultCode != null) {
            for (int i=0;i<activityListResultCode.getData().size();i++) {
                activityList.add(activityListResultCode.getData().get(i));
            }
            refreshBtn.setVisibility(View.INVISIBLE);
            MyApplication.dismissProgress();
            activityListRecycleAdapter.notifyDataSetChanged();
            activityListRecyclerView.setVisibility(View.VISIBLE);
            Log.i(TAG, "initActivityList: ActivityList is not null"+activityList.size());
        }else{
            refreshBtn.setVisibility(View.VISIBLE);
            Log.i(TAG, "initActivityList: ActivityList is null");
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.tab_home_activitylist, container, false);
        activityListRecyclerView = (RecyclerView) mRootView.findViewById(R.id.tab_home_activitylist);
        refreshBtn = (TextView) mRootView.findViewById(R.id.tab_home_activitylist_refresh);
        refreshLayout = (SwipeRefreshLayout) mRootView.findViewById(R.id.tab_home_activitylist_pull_refresh);

        activityListRecyclerView.setLayoutManager(new LinearLayoutManager(container.getContext()));
        activityListRecyclerView.addItemDecoration(new DividerItemDecoration(container.getContext(), DividerItemDecoration.VERTICAL_LIST));
        activityListRecyclerView.setAdapter(activityListRecycleAdapter);
        if (activityListRecycleAdapter.getItemCount() == 0) {
            activityListRecyclerView.setVisibility(View.GONE);
            refreshBtn.setVisibility(View.VISIBLE);
        }

        initEvent();

        return mRootView;
    }

    private void initEvent() {
        refreshLayout.setColorSchemeColors(Color.BLUE,Color.BLACK,Color.RED);
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                activityList.clear();
                NetRequestManager.getInstance().getActivityList(MyApplication.getInstance().getUserToken(),BaseUrlManager.getUrlForGetActivityList(MyApplication.getInstance().getUserCollegeId(),0,10));
            }
        });

        refreshBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyApplication.showProgress(getContext(),"加载中","请稍等");
                Timer timer = new Timer();
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        MyApplication.dismissProgress();
                    }
                },8000);
                NetRequestManager.getInstance().getActivityList(MyApplication.getInstance().getUserToken(),BaseUrlManager.getUrlForGetNewsList(MyApplication.getInstance().getUserCollegeId(),0,15));
            }
        });

        activityListRecycleAdapter.setOnItemClickListener(new ActivityListRecycleAdapter.OnItemClickListener() {
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
        });
    }


}
