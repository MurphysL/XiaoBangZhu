package com.xiaobangzhu.xiaobangzhu.UI.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xiaobangzhu.xiaobangzhu.Adapter.NotificationAdapter;
import com.xiaobangzhu.xiaobangzhu.AliPay.PayResult;
import com.xiaobangzhu.xiaobangzhu.R;

/**
 * NotificationFragment
 *
 * @author: MurphySL
 * @time: 2016/11/15 8:15
 */


public class NotificationFragment extends Fragment{

    private RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_notification , container , false);
        recyclerView = (RecyclerView) root.findViewById(R.id.notification_list);
        recyclerView.setAdapter(new NotificationAdapter());
        return root;
    }
}
