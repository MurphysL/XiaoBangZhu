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
import com.xiaobangzhu.xiaobangzhu.Adapter.NewsListRecycleAdapter;
import com.xiaobangzhu.xiaobangzhu.Bean.NewsListResultCode;
import com.xiaobangzhu.xiaobangzhu.Interface.DataChangeListener;
import com.xiaobangzhu.xiaobangzhu.MyApplication;
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
//public class NewsListFragment extends Fragment{
//    private static final String TAG = "NewsListFragment";
//    View mRootView;
//    RecyclerView newsListRecyclerView;
//    NewsListRecycleAdapter newsListRecycleAdapter;
//    final List<NewsListResultCode.Data> newsList = new ArrayList<NewsListResultCode.Data>();
//    NewsListResultCode newsListResultCode;
//    TextView refreshBtn;
//    SwipeRefreshLayout refreshLayout;
//    @Override
//    public void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        newsListRecycleAdapter = new NewsListRecycleAdapter(getContext(), newsList);
//
//        NetRequestManager.getInstance().getNewsList(MyApplication.getInstance().getUserToken(),BaseUrlManager.getUrlForGetNewsList(MyApplication.getInstance().getUserCollegeId(),newsList.size(),5));
//
//        NetRequestManager.getInstance().setNewsListResultCodeListener(new DataChangeListener<NewsListResultCode>() {
//            @Override
//            public void onSuccessful(NewsListResultCode data) {
//                Log.i("inform", data.getDesc());
//                if (data != null) {
//                    if (data.getData().size() > 0) {
//                        newsListResultCode = data;
//                        initNewsList();
//                    }
//                }else{
//                    newsListRecycleAdapter.setLoading(false);
//                }
//            }
//
//            @Override
//            public void onError(VolleyError volleyError) {
//                MyApplication.showDialog(getContext(),"网络连到火星上了");
//                newsListRecycleAdapter.setLoading(false);
//                //refreshLayout.setRefreshing(false);
//            }
//
//            @Override
//            public void onResponseNull() {
//                MyApplication.showDialog(getContext(),"暂时无法获取内容");
//              //  refreshLayout.setRefreshing(false);
//                newsListRecycleAdapter.setLoading(false);
//            }
//        });
//    }
//
//    /**
//     * 初始化新闻列表的数据
//     */
//    void initNewsList() {
////        refreshLayout.setRefreshing(false);
//        newsListRecycleAdapter.setLoading(false);
//        if (newsListResultCode != null) {
//            for (int i=0;i<newsListResultCode.getData().size();i++) {
//                newsList.add(newsListResultCode.getData().get(i));
//            }
//       //     refreshBtn.setVisibility(View.INVISIBLE);
//            MyApplication.dismissProgress();
//            newsListRecycleAdapter.notifyDataSetChanged();
//            newsListRecyclerView.setVisibility(View.VISIBLE);
//            Log.i(TAG, "initNewsList: newsList is not null");
//        }else{
//       //     refreshBtn.setVisibility(View.VISIBLE);
//            Log.i(TAG, "initNewsList: newsListResultCode is null");
//        }
//    }
//
//    @Nullable
//    @Override
//    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        mRootView = inflater.inflate(R.layout.tab_home_newslist, container, false);
//       //refreshLayout = (SwipeRefreshLayout) mRootView.findViewById(R.id.tab_home_newslist_pull_refresh);
//        //refreshBtn = (TextView) mRootView.findViewById(R.id.tab_home_newslist_refresh);
//        newsListRecyclerView = (RecyclerView) mRootView.findViewById(R.id.tab_home_newslist);
//        newsListRecyclerView.setLayoutManager(new LinearLayoutManager(container.getContext()));
//        newsListRecyclerView.addItemDecoration(new DividerItemDecoration(container.getContext(), DividerItemDecoration.VERTICAL_LIST));
//        newsListRecyclerView.setAdapter(newsListRecycleAdapter);
//        if (newsListRecycleAdapter.getItemCount() == 0) {
//            newsListRecyclerView.setVisibility(View.GONE);
//            //refreshBtn.setVisibility(View.VISIBLE);
//        }
//
//        initEvent();
//        return mRootView;
//    }
//
//    private void initEvent() {
//        /*refreshLayout.setColorSchemeColors(Color.RED,Color.BLUE,Color.GREEN);
//        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
//            @Override
//            public void onRefresh() {
//                newsList.clear();
//                NetRequestManager.getInstance().getNewsList(MyApplication.getInstance().getUserToken(),BaseUrlManager.getUrlForGetNewsList(MyApplication.getInstance().getUserCollegeId(),0,15));
//            }
//        });
//
//        refreshBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                MyApplication.showProgress(getContext(),"加载中","请稍等");
//                Timer timer = new Timer();
//                timer.schedule(new TimerTask() {
//                    @Override
//                    public void run() {
//                        MyApplication.dismissProgress();
//                    }
//                },8000);
//                NetRequestManager.getInstance().getNewsList(MyApplication.getInstance().getUserToken(),BaseUrlManager.getUrlForGetNewsList(MyApplication.getInstance().getUserCollegeId(),0,15));            }
//        });*/
//
//        newsListRecycleAdapter.setmOnItemClickListener(new NewsListRecycleAdapter.OnItemClickListener() {
//            @Override
//            public void onItemClick(RecyclerView.ViewHolder viewHolder, NewsListResultCode.Data itemData, int position) {
//                Intent intent  = new Intent(getContext(), WebActivity.class);
//                Bundle bundle = new Bundle();
//                bundle.putString("title", "详情");
//                bundle.putString("url", HtmlManager.getmInstance().getUrlForNewsDetail(MyApplication.getInstance().getUserToken(), itemData.getId()));
//                intent.putExtras(bundle);
//                startActivity(intent);
//            }
//        });
//
//        newsListRecyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
//            @Override
//            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
//                super.onScrollStateChanged(recyclerView, newState);
//            }
//
//            @Override
//            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
//                LinearLayoutManager layoutManager = (LinearLayoutManager) newsListRecyclerView.getLayoutManager();
//                int totalItemCount = layoutManager.getItemCount();
//                int lastVisibleItem = layoutManager.findLastVisibleItemPosition();
//
//                if (!newsListRecycleAdapter.isLoading() && totalItemCount < (lastVisibleItem + 3)) {
//                    newsListRecycleAdapter.setLoading(true);
//                    NetRequestManager.getInstance().getNewsList(MyApplication.getInstance().getUserToken(),BaseUrlManager.getUrlForGetNewsList(MyApplication.getInstance().getUserCollegeId(),newsListRecycleAdapter.getBottomId(lastVisibleItem)+1,5));
//                }
//            }
//        });
//    }
//
//}
