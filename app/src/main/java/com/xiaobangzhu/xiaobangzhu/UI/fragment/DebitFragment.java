package com.xiaobangzhu.xiaobangzhu.UI.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xiaobangzhu.xiaobangzhu.Adapter.DebitRecycleAdapter;
import com.xiaobangzhu.xiaobangzhu.Interface.RecycleViewItemClickListener;
import com.xiaobangzhu.xiaobangzhu.MyApplication;
import com.xiaobangzhu.xiaobangzhu.NetworkService.HtmlManager;
import com.xiaobangzhu.xiaobangzhu.R;
import com.xiaobangzhu.xiaobangzhu.UI.activity.WebActivity;

/**
 * Created by WQC on 2016/7/13.
 */
public class DebitFragment extends Fragment implements RecycleViewItemClickListener {

    private static final String TAG = "DebitFragment";
    View mRootView;
    RecyclerView mRecyclerView;
    private DebitRecycleAdapter mDebitRecycleAdapter;

    private String[] names  = {"极速取现","话费/流量","游戏充值","房屋分期","门票分期","一元耍大牌"};
    private int[] iconIds = {R.drawable.debit_fast_cash, R.drawable.debit_calls_traffic, R.drawable.debit_games_recharge, R.drawable.debit_house_staging, R.drawable.tickets_staging, R.drawable.one_yuan_diva};

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDebitRecycleAdapter = new DebitRecycleAdapter(names, iconIds);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.fragment_debit, container, false);
        mRecyclerView = (RecyclerView) mRootView.findViewById(R.id.debit_recycle_view);
        mRecyclerView.setLayoutManager(new GridLayoutManager(container.getContext(), 2));
        mDebitRecycleAdapter.setItemClickListener(this);
        mRecyclerView.setAdapter(mDebitRecycleAdapter);
        return mRootView;
    }

    @Override
    public void onItemClick(View view, int position) {
        Intent intent = new Intent(getActivity(),WebActivity.class);
        Bundle bundle = new Bundle();
        switch (position) {
            case 0:
                bundle.putString("title", "急速取现");
                bundle.putString("url", HtmlManager.getmInstance().getUrlForTakeMoney(MyApplication.getInstance().getUserToken()));
                break;
            case 1:
                bundle.putString("title", "流量话费");
                bundle.putString("url", HtmlManager.getmInstance().getUrlForTakeMoney(MyApplication.getInstance().getUserToken()));
                break;
            case 2:
                bundle.putString("title", "游戏充值");
                bundle.putString("url", HtmlManager.getmInstance().getUrlForTakeMoney(MyApplication.getInstance().getUserToken()));
                break;
            case 3:
                bundle.putString("title", "房屋分期");
                bundle.putString("url", HtmlManager.getmInstance().getUrlForTakeMoney(MyApplication.getInstance().getUserToken()));
                break;
            case 4:
                bundle.putString("title", "门票分期");
                bundle.putString("url", HtmlManager.getmInstance().getUrlForTakeMoney(MyApplication.getInstance().getUserToken()));
                break;
            case 5:
                bundle.putString("title", "一元耍大牌");
                bundle.putString("url", HtmlManager.getmInstance().getUrlForTakeMoney(MyApplication.getInstance().getUserToken()));
                break;
        }
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
