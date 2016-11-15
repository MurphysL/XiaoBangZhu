package com.xiaobangzhu.xiaobangzhu.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

/**
 * NotificationAdapter
 *
 * @author: MurphySL
 * @time: 2016/11/15 9:05
 */


public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.NotificationViewHolder>{

    @Override
    public NotificationViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(NotificationViewHolder holder, int position) {

    }


    @Override
    public int getItemCount() {
        return 0;
    }

    public class NotificationViewHolder extends RecyclerView.ViewHolder{

        public NotificationViewHolder(View itemView) {
            super(itemView);
        }
    }
}
