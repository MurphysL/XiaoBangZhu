package com.xiaobangzhu.xiaobangzhu.UI.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterViewFlipper;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.android.volley.VolleyError;
import com.xiaobangzhu.xiaobangzhu.Adapter.MyFragmentPagerAdapter;
import com.xiaobangzhu.xiaobangzhu.Adapter.ViewFlipperAdapter;
import com.xiaobangzhu.xiaobangzhu.Bean.AdPictures;
import com.xiaobangzhu.xiaobangzhu.Interface.DataChangeListener;
import com.xiaobangzhu.xiaobangzhu.Interface.OnViewFlipperClickListener;
import com.xiaobangzhu.xiaobangzhu.MyApplication;
import com.xiaobangzhu.xiaobangzhu.NetworkService.NetRequestManager;
import com.xiaobangzhu.xiaobangzhu.R;
import com.xiaobangzhu.xiaobangzhu.UI.activity.WebActivity;
import com.xiaobangzhu.xiaobangzhu.Utils.PixelUtil;
import com.xiaobangzhu.xiaobangzhu.View.ImageCycleView;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by WQC on 2016/7/13.
 */
public class HomeFragment extends Fragment implements ViewPager.OnPageChangeListener
        ,GestureDetector.OnGestureListener , View.OnTouchListener{
    private static final String TAG = "HomeFragment";
    View mRootView;
    TabLayout mTabLayout;
    ImageCycleView mImageIndicatorView;
    final List<String> adImageUrlList = new ArrayList<String>();
    AdPictures adPictures;
    ViewPager tabPager;
    ArrayList<Fragment> tabFragments;
    Fragment newsListFragment,activityListFragment;
    ViewFlipperAdapter baseAdapter;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        tabFragments = new ArrayList<>();
        newsListFragment = new NewsListFragment2();
        activityListFragment = new ActivityListFragment();
        //baseAdapter = new ViewFlipperAdapter(getContext(), adImageUrlList);

        tabFragments.add(newsListFragment);
        tabFragments.add(activityListFragment);
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
    }

    /**
     * 初始化首页图片数据
     */
    void initAdPictures() {
        if (adPictures != null) {
            for (int i=0;i<adPictures.getData().size();i++) {
                adImageUrlList.add(adPictures.getData().get(i).getPicture());
            }
            mImageIndicatorView.loadData(adImageUrlList);
            //baseAdapter.notifyDataSetChanged();
        }else{
            Log.i(TAG, "initAdPictures: adPictures is null");
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.fragment_home, container, false);
        initViews(mRootView);
        initEvent();
        return mRootView;
    }

    @Override
    public void onStart() {
        super.onStart();
        initAdPictures();
    }

    /**
     * 初始化界面布局
     * @param mRootView
     */
    void initViews(View mRootView){
        mImageIndicatorView = (ImageCycleView) mRootView.findViewById(R.id.auto_image_indicate);

        mTabLayout = (TabLayout) mRootView.findViewById(R.id.home_tablayout);
        mTabLayout.addTab(mTabLayout.newTab());
        mTabLayout.addTab(mTabLayout.newTab());

        tabPager = (ViewPager) mRootView.findViewById(R.id.home_tab_pager);
        tabPager.setAdapter(new MyFragmentPagerAdapter(getChildFragmentManager(),tabFragments));
        mTabLayout.setupWithViewPager(tabPager);
        tabPager.setCurrentItem(0);
        tabPager.setOnTouchListener(this);

        mTabLayout.getTabAt(0).setText(R.string.news_ground);
        mTabLayout.getTabAt(1).setText(R.string.campus_activity);

        try {
            setTabWidth(mTabLayout);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        //mImageIndicatorView.setAdapter(baseAdapter);
        //mImageIndicatorView.startFlipping();
    }


    /**
     * 初始化事件监听
     */
    private void initEvent() {

       /* baseAdapter.setOnItemClickListener(new OnViewFlipperClickListener() {
            @Override
            public void onItemClick(String url, int position) {
                if (adImageUrlList.size()<1){
                    NetRequestManager.getInstance().getAdPictures(MyApplication.getInstance().getUserCollegeId(), MyApplication.getInstance().getUserToken());
                    Log.i(TAG, "onItemClick: ");
                }else{
                    Log.i(TAG, "onItemClick: " + position);
                    Intent intent = new Intent(getActivity(), WebActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("title", "详情");
                    bundle.putString("url", adPictures.getData().get(position).getHref());
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
            }
        });*/

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
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        tabPager.setCurrentItem(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    /**
     *设置tab的宽度
     * @param tabWidth
     * @throws NoSuchFieldException
     * @throws IllegalAccessException
     */
    public void setTabWidth(TabLayout tabWidth) throws NoSuchFieldException, IllegalAccessException  {
        Class<?> tablayout = mTabLayout.getClass();
        Field tabStrip = tablayout.getDeclaredField("mTabStrip");
        tabStrip.setAccessible(true);
        LinearLayout ll_tab= (LinearLayout) tabStrip.get(mTabLayout);
        for (int i = 0; i < ll_tab.getChildCount(); i++) {
            View child = ll_tab.getChildAt(i);
            child.setPadding(0,0,0,0);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT,1);
            params.setMarginStart(PixelUtil.dp2px(55f));
            params.setMarginEnd(PixelUtil.dp2px(55f));
            child.setLayoutParams(params);
            child.invalidate();
        }
    }

    @Override
    public boolean onDown(MotionEvent e) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {

    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        return false;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return false;
    }
}
