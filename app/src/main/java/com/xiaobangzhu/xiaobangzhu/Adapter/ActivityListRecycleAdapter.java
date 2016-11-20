package com.xiaobangzhu.xiaobangzhu.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.xiaobangzhu.xiaobangzhu.Bean.ActivityListResultCode;
import com.xiaobangzhu.xiaobangzhu.Interface.LoadMoreListener;
import com.xiaobangzhu.xiaobangzhu.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by WQC on 2016/10/1.
 */

public class ActivityListRecycleAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private static final String TAG = "ActivityListAdapter";
    Context mContext;
    List<ActivityListResultCode.Data> dataList;
    ActivityListRecycleAdapter.OnItemClickListener onItemClickListener;
    LoadMoreListener loadMoreListener = null;
    private static final int VIEW_PROG = 1;
    private boolean loading;

    public void setLoadMoreListener(LoadMoreListener loadMoreListener) {
        this.loadMoreListener = loadMoreListener;
    }

    public ActivityListRecycleAdapter(Context mContext, List<ActivityListResultCode.Data> dataList) {
        this.mContext = mContext;
        this.dataList = dataList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == VIEW_PROG) {
            return new FooterViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.recycleview_footer, parent, false));
        }else{
            return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.tab_home_activitylist_item, parent, false));
        }
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof FooterViewHolder) {
            ((FooterViewHolder) holder).progressBar.setIndeterminate(false);
        } else if (holder instanceof ViewHolder) {
            final ActivityListResultCode.Data data = dataList.get(position);
            final ViewHolder itemViewHolder = (ViewHolder) holder;
            Picasso.with(mContext).load(data.getPoster()) .fit()
                    .centerCrop().into(itemViewHolder.bgImageView);
            Picasso.with(mContext).load(data.getHeader()) .fit()
                    .centerCrop().into(itemViewHolder.headerCircleView);
            itemViewHolder.titleTextView.setText(data.getTitle());

            String content = data.getContent();
            char[] c = new char[80];
            if(content.length() > 70){
                content.getChars(0 , 70 , c , 0);
                c[71] = '.';
                c[72] = '.';
                c[73] = '.';
                String s = new String(c);
                itemViewHolder.contentTextView.setText(s);
            }else{
                itemViewHolder.contentTextView.setText(content);
            }


            itemViewHolder.numTextView.setText(data.getPerson_num() + "");
            itemViewHolder.tag_1_TextView.setText(data.getTags());
            itemViewHolder.timeTextView.setText(data.getC_time());


            /**
             * Y
             */
            SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            long now = System.currentTimeMillis();

            try {
                if(!"".equals(data.getApply_endtime()) || !data.getApply_endtime().equals(null)){
                    long t = (sdf.parse(data.getApply_endtime()).getTime());
                    Log.i(TAG, "onBindViewHolder: " + (t > now) );
                    if(t < now){
                        itemViewHolder.joinBtn.setBackgroundResource(R.color.base_gray);
                        itemViewHolder.joinBtn.setClickable(false);
                        itemViewHolder.joinBtn.setText("活动结束");
                    }else{
                        if (onItemClickListener != null) {
                            itemViewHolder.joinBtn.setBackgroundResource(R.color.orange);
                            itemViewHolder.joinBtn.setText("加入活动");
                            itemViewHolder.joinBtn.setOnClickListener(new View.OnClickListener() {
                                @Override

                                public void onClick(View v) {
                                    Log.i(TAG, "onClick:is use full");
                                    onItemClickListener.onItemClick(itemViewHolder, data, position);
                                }
                            });

                        }
                    }
                }else{
                    Log.i(TAG, "onBindViewHolder: " + "获取数据出错");
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }

        }
    }

    public boolean isLoading() {
        return loading;
    }

    public void setLoading(boolean loading) {
        this.loading = loading;
    }

    public void setOnItemClickListener(ActivityListRecycleAdapter.OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public int getItemViewType(int position) {
        return dataList.get(position) != null ? super.getItemViewType(position) : VIEW_PROG;
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }


    public int getBottomId(int position) {
        return dataList.get(position).getId();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public final ImageView bgImageView;
        public final CircleImageView headerCircleView;
        public final TextView titleTextView;
        public final TextView contentTextView;
        public final TextView tag_1_TextView;
        public final TextView tag_2_TextView;
        public final TextView timeTextView;
        public final TextView numTextView;
        public final Button joinBtn;

        public ViewHolder(View itemView) {
            super(itemView);
            bgImageView = (ImageView) itemView.findViewById(R.id.activity_item_bg);
            headerCircleView = (CircleImageView) itemView.findViewById(R.id.activity_item_circle);
            titleTextView = (TextView) itemView.findViewById(R.id.activity_item_name);
            contentTextView = (TextView) itemView.findViewById(R.id.activity_item_desc);
            tag_1_TextView = (TextView) itemView.findViewById(R.id.activity_item_tag1);
            tag_2_TextView = (TextView) itemView.findViewById(R.id.activity_item_tag2);
            timeTextView = (TextView) itemView.findViewById(R.id.activity_item_time);
            numTextView = (TextView) itemView.findViewById(R.id.activity_item_numbers);
            joinBtn = (Button) itemView.findViewById(R.id.activity_enter_activity);
        }
    }



    public interface OnItemClickListener{
        void onItemClick(ViewHolder itemView, ActivityListResultCode.Data data, int position);
    }
}
