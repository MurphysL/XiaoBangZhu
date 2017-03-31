package com.xiaobangzhu.xiaobangzhu.UI.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.xiaobangzhu.xiaobangzhu.Adapter.GoodsListRecycleAdapter;
import com.xiaobangzhu.xiaobangzhu.Bean.GetAllItemsCode;
import com.xiaobangzhu.xiaobangzhu.Bean.NewItemsListBean;
import com.xiaobangzhu.xiaobangzhu.Bean.UserVIPInfo;
import com.xiaobangzhu.xiaobangzhu.Interface.DataChangeListener;
import com.xiaobangzhu.xiaobangzhu.MyApplication;
import com.xiaobangzhu.xiaobangzhu.NetworkService.NetRequestManager;
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
    private static final String TAG = "ShopSnackFragment";

    private RecyclerView shopRecyclerView;
    private SwipeRefreshLayout refreshLayout;
    private TextView refreshBtn;
    private GoodsListRecycleAdapter adapter;

    private List<NewItemsListBean> list = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_shop , container , false);
        shopRecyclerView = (RecyclerView) view.findViewById(R.id.shop_list);
        refreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.shop_pull_refresh);
        refreshBtn = (TextView) view.findViewById(tab_home_shoplist_refresh);

        adapter = new GoodsListRecycleAdapter(getContext() , list);



        GridLayoutManager manager = new GridLayoutManager(getContext() , 2);
        shopRecyclerView.setLayoutManager(manager);
        shopRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL_LIST));
       /* shopRecyclerView.addOnScrollListener(new LoadMoreListener(manager) {
            @Override
            public void loadMore(int currentPage) {

            }
        });*/
        shopRecyclerView.setAdapter(adapter);
       /* if (adapter.getItemCount() == 0) {
            shopRecyclerView.setVisibility(View.GONE);
            refreshBtn.setVisibility(View.VISIBLE);
        }*/
       refreshBtn.setVisibility(View.GONE);
        initData();

        return view;
    }

    private void initData() {
        final String uid = "18435186562";
        NetRequestManager.getInstance().getGetUserVIPInfo(uid);
        NetRequestManager.getInstance().setGetUserVIPInfo(new DataChangeListener<UserVIPInfo>() {
            @Override
            public void onSuccessful(UserVIPInfo data) {
                int viptype = data.getViptype();
                NetRequestManager.getInstance().getAllItems(uid, viptype);
                NetRequestManager.getInstance().setGetAllItemsDataChangeListener(new DataChangeListener<GetAllItemsCode>() {
                    @Override
                    public void onSuccessful(GetAllItemsCode data) {
                        if(data.getState() != 1 && data.getNewItemsList() != null){
                            for(int i = 0 ;i < data.getNewItemsList().size();i ++){
                                NewItemsListBean bean =data.getNewItemsList().get(i);
                                list.add(bean);
                                Log.i(TAG, "onSuccessful: " + bean.toString());
                            }
                            Log.i(TAG, "onSuccessful: " + list.size());
                            adapter.notifyDataSetChanged();
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

            @Override
            public void onError(VolleyError volleyError) {
                MyApplication.showDialog(getContext(),"请检查网络状况");
            }

            @Override
            public void onResponseNull() {
                MyApplication.showDialog(getContext(),"请检查网络状况");
            }
        });

      /*  NetRequestManager.getInstance().getUserInform(MyApplication.getInstance().getUserToken());
        NetRequestManager.getInstance().setUserBaseInformListener(new DataChangeListener<UserBaseInform>() {
            @Override
            public void onSuccessful(UserBaseInform data) {
                final String uid = data.getData().getLogin_id();

            }

            @Override
            public void onError(VolleyError volleyError) {
                MyApplication.showDialog(getContext(),"请检查网络状况");
            }

            @Override
            public void onResponseNull() {
                MyApplication.showDialog(getContext(),"请检查网络状况");
            }
        });*/

    }
}
