package com.xiaobangzhu.xiaobangzhu.UI.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xiaobangzhu.xiaobangzhu.Adapter.NotificationAdapter;
import com.xiaobangzhu.xiaobangzhu.Bean.Notification;
import com.xiaobangzhu.xiaobangzhu.MyApplication;
import com.xiaobangzhu.xiaobangzhu.R;
import com.xiaobangzhu.xiaobangzhu.db.NotificationDao;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.ArrayList;
import java.util.List;

/**
 * NotificationFragment
 *
 * @author: MurphySL
 * @time: 2016/11/15 8:15
 */

public class NotificationFragment extends Fragment {
    private static final String TAG = "NotificationFragment";

    private RecyclerView recyclerView;
    private List<Notification> list = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        initData();
        super.onCreate(savedInstanceState);
    }

    private void initData() {
        NotificationDao dao = MyApplication.getInstance().getDao();
        QueryBuilder<Notification> builder = dao.queryBuilder();
        list = builder.list();

        for(int i = 0 ; i < list.size() ; i ++){
            Log.i(TAG, "initData: " + list.get(i).getMessage());
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_notification , container , false);
        recyclerView = (RecyclerView) root.findViewById(R.id.notification_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        Log.i(TAG, "initData: " + list.size());
        recyclerView.setAdapter(new NotificationAdapter(list));
        return root;
    }
}
