package com.xiaobangzhu.xiaobangzhu.UI.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xiaobangzhu.xiaobangzhu.Adapter.MyFragmentPagerAdapter;
import com.xiaobangzhu.xiaobangzhu.MyApplication;
import com.xiaobangzhu.xiaobangzhu.NetworkService.HtmlManager;
import com.xiaobangzhu.xiaobangzhu.R;

import java.util.ArrayList;
/**
 * Created by WQC on 2016/7/13.
 */
public class RewardFragment extends Fragment implements TabLayout.OnTabSelectedListener{
    private static final String TAG = "RewardFragment";
    View mRootView;
    TabLayout mTabLayout;
    ViewPager viewPager;
    FragmentPagerAdapter pagerAdapter;
    ArrayList<Fragment> fragmentList = new ArrayList<>();
    RewardListFragment normalTaskFragment = new RewardListFragment();
    RewardListFragment secondTaskFragment= new RewardListFragment();
    RewardListFragment teamTaskFragment= new RewardListFragment();
    RewardListFragment expressTeskFragment= new RewardListFragment();
    RewardListFragment tangleTaskFragment= new RewardListFragment();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String token = MyApplication.getInstance().getUserToken();
        int collegeId = MyApplication.getInstance().getUserCollegeId();

        normalTaskFragment.setArguments(createBundle(HtmlManager.getmInstance().getUrlForNormalTask(token,collegeId)));

        secondTaskFragment.setArguments(createBundle(HtmlManager.getmInstance().getUrlForSecond(token,collegeId)));

        teamTaskFragment.setArguments(createBundle(HtmlManager.getmInstance().getUrlForTeam(token,collegeId)));

        tangleTaskFragment.setArguments(createBundle(HtmlManager.getmInstance().getUrlForTangle(token,collegeId)));

        expressTeskFragment.setArguments(createBundle(HtmlManager.getmInstance().getUrlForExpress(token,collegeId)));



        fragmentList.add(normalTaskFragment);
        fragmentList.add(secondTaskFragment);
        fragmentList.add(tangleTaskFragment);
        fragmentList.add(teamTaskFragment);
        fragmentList.add(expressTeskFragment);

        Log.i(TAG, "onCreate: " + fragmentList.size());

        pagerAdapter = new MyFragmentPagerAdapter(getChildFragmentManager(), fragmentList);

    }

    /**
     * 创建bundle数据
     * @param url
     * @return
     */
    private Bundle createBundle(String url) {
        Bundle bundle = new Bundle();
        bundle.putString("url",url);
        return bundle;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.fragment_reward, container, false);
        mTabLayout = (TabLayout) mRootView.findViewById(R.id.reward_tablayout);
        createTabs();
        viewPager = (ViewPager) mRootView.findViewById(R.id.reward_view_pager);
        initViewPager();
        initTabs();
        initEvents();
        return mRootView;
    }

    /**
     * 初始化viewpager
     */
    private void initViewPager() {
        viewPager.setAdapter(pagerAdapter);
        viewPager.setCurrentItem(0);
        mTabLayout.setupWithViewPager(viewPager);
    }

    /**
     * 初始化tab的文字
     */
    private void initTabs() {
        mTabLayout.getTabAt(0).setText(R.string.normal_task);
        mTabLayout.getTabAt(1).setText(R.string.second_trading);
        mTabLayout.getTabAt(2).setText(R.string.tangle);
        mTabLayout.getTabAt(3).setText(R.string.team);
        mTabLayout.getTabAt(4).setText(R.string.express_delivery);

    }


    /**
     * 初始化tab面板
     */
    private void createTabs() {
        mTabLayout.addTab(mTabLayout.newTab().setText(R.string.normal_task));
        mTabLayout.addTab(mTabLayout.newTab().setText(R.string.second_trading));
        mTabLayout.addTab(mTabLayout.newTab().setText(R.string.tangle));
        mTabLayout.addTab(mTabLayout.newTab().setText(R.string.team));
        mTabLayout.addTab(mTabLayout.newTab().setText(R.string.express_delivery));
    }
    /**
     * 初始化事件绑定
     */
    private void initEvents() {
        mTabLayout.addOnTabSelectedListener(this);
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {
        int tabPosition = tab.getPosition();
        switch (tabPosition) {
            case 0:
                normalTaskFragment.reLoadWeb();
//                rewardWeb.loadUrl(HtmlManager.getmInstance().getUrlForNormalTask(MyApplication.getInstance().getUserToken(),MyApplication.getInstance().getUserCollegeId()));
                Log.i(TAG, "onTabSelected: "+ HtmlManager.getmInstance().getUrlForNormalTask(MyApplication.getInstance().getUserToken(),MyApplication.getInstance().getUserCollegeId()));
                break;
            case 1:
                secondTaskFragment.reLoadWeb();
//                rewardWeb.loadUrl(HtmlManager.getmInstance().getUrlForSecond(MyApplication.getInstance().getUserToken(),MyApplication.getInstance().getUserCollegeId()));
                Log.i(TAG, "onTabSelected: " +HtmlManager.getmInstance().getUrlForSecond(MyApplication.getInstance().getUserToken(),MyApplication.getInstance().getUserCollegeId()));
                break;
            case 2:
                tangleTaskFragment.reLoadWeb();
//                rewardWeb.loadUrl(HtmlManager.getmInstance().getUrlForTangle(MyApplication.getInstance().getUserToken(),MyApplication.getInstance().getUserCollegeId()));
                Log.i(TAG, "onTabSelected: " + HtmlManager.getmInstance().getUrlForTangle(MyApplication.getInstance().getUserToken(),MyApplication.getInstance().getUserCollegeId()));
                break;
            case 3:
                teamTaskFragment.reLoadWeb();
//                rewardWeb.loadUrl(HtmlManager.getmInstance().getUrlForTeam(MyApplication.getInstance().getUserToken(),MyApplication.getInstance().getUserCollegeId()));
                Log.i(TAG, "onTabSelected: " + HtmlManager.getmInstance().getUrlForTeam(MyApplication.getInstance().getUserToken(),MyApplication.getInstance().getUserCollegeId()));
                break;
            case 4:
                expressTeskFragment.reLoadWeb();
//                rewardWeb.loadUrl(HtmlManager.getmInstance().getUrlForHomePage(1));
//                Log.i(TAG, "onTabSelected: " + HtmlManager.getmInstance().getUrlForHomePage(1));
                break;
            default:
                break;
        }
    }
}
