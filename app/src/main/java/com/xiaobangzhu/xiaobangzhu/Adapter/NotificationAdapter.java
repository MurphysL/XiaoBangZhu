package com.xiaobangzhu.xiaobangzhu.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xiaobangzhu.xiaobangzhu.Bean.Notification;
import com.xiaobangzhu.xiaobangzhu.R;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.logging.SimpleFormatter;
import java.util.zip.Inflater;

/**
 * NotificationAdapter
 *
 * @author: MurphySL
 * @time: 2016/11/15 9:05
 */


public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.NotificationViewHolder>{

    private List<Notification> list;

    public NotificationAdapter(List<Notification> list){
        this.list = list;
    }

    @Override
    public NotificationViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new NotificationViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.notification_item  , parent , false));
    }

    @Override
    public void onBindViewHolder(NotificationViewHolder holder, int position) {
        Notification notification = list.get(position);
        holder.msg .setText(notification.getMessage());
        long time = notification.getTime();
        long now = System.currentTimeMillis();

        if(time - now < 36000){
            holder.time.setText("刚刚");
        }else{
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss ");
            holder.time.setText(formatter.format(time));
        }

    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    public class NotificationViewHolder extends RecyclerView.ViewHolder{

        public TextView msg;
        public TextView time;

        public NotificationViewHolder(View itemView) {
            super(itemView);
            msg = (TextView) itemView.findViewById(R.id.notification_content);
            time = (TextView) itemView.findViewById(R.id.notification_time);
        }
    }
}
