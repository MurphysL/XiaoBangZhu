package com.xiaobangzhu.xiaobangzhu.UI.fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.xiaobangzhu.xiaobangzhu.Adapter.NewsListAdapter;
import com.xiaobangzhu.xiaobangzhu.Bean.AdPictures;
import com.xiaobangzhu.xiaobangzhu.Bean.NewsListResultCode;
import com.xiaobangzhu.xiaobangzhu.Interface.DataChangeListener;
import com.xiaobangzhu.xiaobangzhu.MyApplication;
import com.xiaobangzhu.xiaobangzhu.NetworkService.BaseUrlManager;
import com.xiaobangzhu.xiaobangzhu.NetworkService.HtmlManager;
import com.xiaobangzhu.xiaobangzhu.NetworkService.NetRequestManager;
import com.xiaobangzhu.xiaobangzhu.R;
import com.xiaobangzhu.xiaobangzhu.UI.activity.WebActivity;
import com.xiaobangzhu.xiaobangzhu.View.ImageCycleView;

import java.util.ArrayList;
import java.util.List;

/**
 * NewsListFragment2
 *
 * @author: MurphySL
 * @time: 2016/11/4 16:13
 */

public class NewsListFragment2 extends Fragment implements AdapterView.OnItemClickListener, AbsListView.OnScrollListener{
    private static final String TAG = "NewsListFragment";
    View mRootView;
    ListView newsListView;
    NewsListAdapter newsListAdapter;
    final List<NewsListResultCode.Data> newsList = new ArrayList<NewsListResultCode.Data>();
    NewsListResultCode newsListResultCode;
    TextView refreshBtn;
    SwipeRefreshLayout refreshLayout;

    ImageCycleView mImageIndicatorView;
    final List<String> adImageUrlList = new ArrayList<String>();
    AdPictures adPictures;
    LinearLayout shelter;

    Boolean isBottom;

    int firstVisibleItem;
    int visibleItemCount;
    int totalItemCount;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        NetRequestManager.getInstance().getAdPictures(MyApplication.getInstance().getUserCollegeId(), MyApplication.getInstance().getUserToken());
        NetRequestManager.getInstance().setAdPicturesListener(new DataChangeListener<AdPictures>() {
            @Override
            public void onSuccessful(AdPictures data) {
                if (data != null) {
                    adPictures = data;
                    if (data.getStatus() == 0 && data.getData().size() > 0) {
                        initAdPictures();
                    }
                }
            }
            @Override
            public void onError(VolleyError volleyError) {
                MyApplication.showDialog(getContext(),"请检查网络状况");
            }
            @Override
            public void onResponseNull() {
                MyApplication.showDialog(getContext(),"请检查网络状况");
            }
        });
        NetRequestManager.getInstance().getNewsList(MyApplication.getInstance().getUserToken(), BaseUrlManager.getUrlForGetNewsList(MyApplication.getInstance().getUserCollegeId(),newsList.size(),5));
        NetRequestManager.getInstance().setNewsListResultCodeListener(new DataChangeListener<NewsListResultCode>() {
            @Override
            public void onSuccessful(NewsListResultCode data) {
                Log.i("inform", data.getDesc());
                if(newsListAdapter!= null)
                    newsListAdapter.setLoading(false);
                if (data != null && data.getData() != null) {
                    if (data.getData().size() > 0) {
                        newsListResultCode = data;
                        initNewsList();
                    }else{
                        refreshLayout.setRefreshing(false);
                    }
                }else{
                    newsListAdapter.setLoading(false);
                }
            }

            @Override
            public void onError(VolleyError volleyError) {
                MyApplication.showDialog(getContext(),"网络连到火星上了");
                newsListAdapter.setLoading(false);
                refreshLayout.setRefreshing(false);
            }

            @Override
            public void onResponseNull() {
                MyApplication.showDialog(getContext(),"暂时无法获取内容");
                refreshLayout.setRefreshing(false);
                newsListAdapter.setLoading(false);
            }
        });
    }


    @Override
    public void onStart() {
        super.onStart();
        if(adImageUrlList != null){
            mImageIndicatorView.loadData(adImageUrlList);
        }
    }

    /**
     * 初始化首页图片数据
     */
    void initAdPictures() {
        if (adPictures != null) {
            for (int i=0;i<adPictures.getData().size();i++) {
                adImageUrlList.add(adPictures.getData().get(i).getPicture());
            }
            if(adImageUrlList.size() > 0 && mImageIndicatorView!= null){
                mImageIndicatorView.loadData(adImageUrlList);
            }
        }else{
            Log.i(TAG, "initAdPictures: adPictures is null");
        }
    }

    /**
     * 初始化新闻列表的数据
     */
    void initNewsList() {
        if(refreshLayout != null)
            refreshLayout.setRefreshing(false);
        if(shelter != null)
            shelter.setVisibility(View.GONE);

        if (newsListResultCode != null && newsListResultCode.getData().size() > 0) {
            Log.i(TAG, "initNewsList: " +newsListResultCode.getData().size() +newsListResultCode.getData().toString());

            for (int i=0;i<newsListResultCode.getData().size();i++) {
                newsList.add(newsListResultCode.getData().get(i));
            }
            refreshBtn.setVisibility(View.INVISIBLE);
            MyApplication.dismissProgress();
            Log.i(TAG, "initNewsList: " + newsList.size());
            newsListAdapter.notifyDataSetChanged();
            newsListView.setVisibility(View.VISIBLE);
        }else{
            refreshBtn.setVisibility(View.VISIBLE);
            Log.i(TAG, "initNewsList: newsListResultCode is null");
        }

        newsListAdapter.setLoading(false);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.tab_home_newslist, container, false);
        refreshLayout = (SwipeRefreshLayout) mRootView.findViewById(R.id.tab_home_newslist_pull_refresh);
        refreshBtn = (TextView) mRootView.findViewById(R.id.tab_home_newslist_refresh);
        shelter = (LinearLayout) mRootView.findViewById(R.id.shleter1);
        refreshBtn.setVisibility(View.VISIBLE);
        refreshLayout.setEnabled(true);

        LayoutInflater mInflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View mHeadView = mInflater.inflate(R.layout.news_header, null);
        mImageIndicatorView = (ImageCycleView) mHeadView.findViewById(R.id.auto_image_indicate);

        newsListView = (ListView) mRootView.findViewById(R.id.tab_home_newslist);
        newsListView.addHeaderView(mHeadView);
        newsListAdapter = new NewsListAdapter(getContext() , newsList);
        newsListView.setAdapter(newsListAdapter);
        if (newsListAdapter.getItemCount() == 0) {
            newsListView.setVisibility(View.GONE);
            refreshBtn.setVisibility(View.VISIBLE);
        }
        initEvent();
        return mRootView;
    }

    private void initEvent() {
        //listview的条目事件点击
        newsListView.setOnItemClickListener(this);
        //listview的滑动监听
        newsListView.setOnScrollListener(this);

        refreshLayout.setColorSchemeColors(Color.RED,Color.BLUE,Color.GREEN);

        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshLayout.setClickable(false);
                shelter.setVisibility(View.VISIBLE);
                newsList.clear();
                newsListAdapter.notifyDataSetChanged();
                NetRequestManager.getInstance().getNewsList(MyApplication.getInstance().getUserToken(),BaseUrlManager.getUrlForGetNewsList(MyApplication.getInstance().getUserCollegeId(),0,15));
            }
        });

        mImageIndicatorView.setOnPageClickListener(new ImageCycleView.OnPageClickListener() {
            @Override
            public void onItemClick(String url, int position) {
                if (adImageUrlList.size()<1){
                    NetRequestManager.getInstance().getAdPictures(MyApplication.getInstance().getUserCollegeId(), MyApplication.getInstance().getUserToken());
                }else{
                    Log.i(TAG, "onItemClick: " + position);
                    Intent intent = new Intent(getActivity(), WebActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("title", "详情");
                    bundle.putString("url", adPictures.getData().get(position %4).getHref());
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
            }
        });
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        //isBottom是自定义的boolean变量，用于标记是否滑动到底部
        if (scrollState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE && isBottom && !newsListAdapter.isLoading()) {
            int lastVisibleItem = firstVisibleItem + visibleItemCount;

            if (!newsListAdapter.isLoading() && totalItemCount < (lastVisibleItem + 3)) {
                newsListAdapter.setLoading(true);
                refreshLayout.setRefreshing(true);
                Log.i(TAG, "onScrollStateChanged: " +"读取数据中。。。" + newsList.size());
                NetRequestManager.getInstance().getNewsList(MyApplication.getInstance().getUserToken(),BaseUrlManager.getUrlForGetNewsList(MyApplication.getInstance().getUserCollegeId(),newsList.size(),5));
            }
        }

        if(!canChildScrollUp()){
            refreshLayout.setEnabled(true);
        }else{
            refreshLayout.setEnabled(false);
        }

    }

    /**
     * 判断是否到底
     * @param view
     * @param firstVisibleItem
     * @param visibleItemCount
     * @param totalItemCount
     */
    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        this.firstVisibleItem = firstVisibleItem - 2;
        this.visibleItemCount = visibleItemCount;
        this.totalItemCount = totalItemCount - 1;

        isBottom = firstVisibleItem + visibleItemCount == totalItemCount;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Log.i(TAG, "onItemClick: " + " " + position);
        if(!newsListAdapter.isLoading()){
            Intent intent  = new Intent(getContext(), WebActivity.class);
            Bundle bundle = new Bundle();
            bundle.putString("title", "详情");
            bundle.putString("url", HtmlManager.getmInstance().getUrlForNewsDetail(MyApplication.getInstance().getUserToken(), newsList.get(position - 1).getId()));
            intent.putExtras(bundle);
            startActivity(intent);
        }
    }

    /**
     * 判断是否滑动到顶端
     * @return 可以true 不可以false
     */
    public boolean canChildScrollUp() {
        if (android.os.Build.VERSION.SDK_INT < 14) {
            if (newsListView instanceof AbsListView) {
                final AbsListView absListView = (AbsListView) newsListView;
                return absListView.getChildCount() > 0 &&
                        (absListView.getFirstVisiblePosition() > 0 ||
                                absListView.getChildAt(0).getTop() < absListView.getPaddingTop());
            } else {
                return ViewCompat.canScrollVertically(newsListView, -1) || newsListView.getScrollY() > 0;
            }
        } else {
            return ViewCompat.canScrollVertically(newsListView, -1);
        }
    }
}
