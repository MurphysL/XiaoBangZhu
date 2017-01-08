package com.xiaobangzhu.xiaobangzhu.UI.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xiaobangzhu.xiaobangzhu.Adapter.GoodsListRecycleAdapter;
import com.xiaobangzhu.xiaobangzhu.R;
import com.xiaobangzhu.xiaobangzhu.View.DividerItemDecoration;

import java.util.ArrayList;
import java.util.List;

import static com.xiaobangzhu.xiaobangzhu.R.id.tab_home_shoplist_refresh;

/**
 * ShopSnackFragment
 *
 * @author: MurphySL
 * @time: 2017/1/7 14:55
 */


public class ShopSnackFragment extends Fragment {

    private RecyclerView shopRecyclerView;
    private SwipeRefreshLayout refreshLayout;
    private TextView refreshBtn;
    private GoodsListRecycleAdapter adapter;

    List data = new ArrayList();
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_shop , container , false);
        shopRecyclerView = (RecyclerView) view.findViewById(R.id.shop_list);
        refreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.shop_pull_refresh);
        refreshBtn = (TextView) view.findViewById(tab_home_shoplist_refresh);


        shopRecyclerView.setLayoutManager(new GridLayoutManager(container.getContext() , 2));
        shopRecyclerView.addItemDecoration(new DividerItemDecoration(container.getContext(), DividerItemDecoration.VERTICAL_LIST));

        adapter = new GoodsListRecycleAdapter(getContext() , data);
        shopRecyclerView.setAdapter(adapter);
        if (adapter.getItemCount() == 0) {
            shopRecyclerView.setVisibility(View.GONE);
            refreshBtn.setVisibility(View.VISIBLE);
        }

        return view;
    }
}
