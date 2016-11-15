package com.xiaobangzhu.xiaobangzhu.Adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.xiaobangzhu.xiaobangzhu.Bean.NewsListResultCode;
import com.xiaobangzhu.xiaobangzhu.R;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by WQC on 2016/7/28.
 */
public class NewsListRecycleAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final String TAG = "NewsListRecycleAdapter";
    List<NewsListResultCode.Data> datas;
    Context mContext;
    OnItemClickListener mOnItemClickListener;
    private boolean loading = false;
    private static final int VIEW_PROG = 1;
    private static final int VIEW_HEADER = 2;

    public NewsListRecycleAdapter(Context context, List<NewsListResultCode.Data> dataList) {
        this.datas = dataList;
        this.mContext = context;
    }

    public boolean isLoading() {
        return loading;
    }

    public void setLoading(boolean loading) {
        this.loading = loading;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == VIEW_PROG) {
            return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.recycleview_footer, parent, false));
        }else if(viewType == VIEW_HEADER){
            return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.news_header , parent , false));
        }else{
            return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.tab_home_newslist_item, parent, false));
        }
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof FooterViewHolder) {
            FooterViewHolder footerViewHolder = (FooterViewHolder) holder;
            footerViewHolder.progressBar.setIndeterminate(false);
        }else if(holder instanceof HeaderViewHolder) {
            HeaderViewHolder headerViewHolder = (HeaderViewHolder) holder;
        }else if (holder instanceof ViewHolder) {
            //显示数据
            final NewsListRecycleAdapter.ViewHolder itemViewHolder = (NewsListRecycleAdapter.ViewHolder) holder;
            Log.i(TAG, "onBindViewHolder: " + position);
            final NewsListResultCode.Data data = datas.get(position);
            Picasso.with(mContext).load(data.getHeader()).into(itemViewHolder.itemHeadPortraitImg);
            itemViewHolder.itemNickName.setText(data.getNick_name());
            itemViewHolder.itemTitle.setText(data.getTitle());
            Log.i(TAG, "onBindViewHolder: " + data.getPicture1());
            if (data.getPicture1() != null && data.getPicture1() != "") {
                itemViewHolder.itemShowImgFirst.setVisibility(View.VISIBLE);
                Picasso.with(mContext).load(data.getPicture1()).into(itemViewHolder.itemShowImgFirst);
            }
            if (data.getPicture2() != null && data.getPicture2() != "") {
                itemViewHolder.itemShowImgSecond.setVisibility(View.VISIBLE);
                Picasso.with(mContext).load(data.getPicture2()).into(itemViewHolder.itemShowImgSecond);
            }
            if (data.getPicture3() != null && data.getPicture3() != "") {
                itemViewHolder.itemShowImgThrid.setVisibility(View.VISIBLE);
                Picasso.with(mContext).load(data.getPicture3()).into(itemViewHolder.itemShowImgThrid);
            }
            if (data.getHeader() != null) {
                itemViewHolder.itemHeadPortraitImg.setVisibility(View.VISIBLE);
                Picasso.with(mContext).load(data.getHeader()).into(itemViewHolder.itemHeadPortraitImg);
            }
            itemViewHolder.itemReadNum.setText(data.getRead_num()+"");
            itemViewHolder.itemComment.setText(data.getComments()+"");
            itemViewHolder.itemTimeStamp.setText(data.getC_time()+"");

            if (mOnItemClickListener != null) {
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        int pos = holder.getLayoutPosition();
                        mOnItemClickListener.onItemClick(itemViewHolder,data,pos);
                    }
                });
            }
            }
        }

    public void setmOnItemClickListener(OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }

    @Override
    public int getItemCount() {
        return datas.size() ;
    }

    @Override
    public int getItemViewType(int position) {
        Log.i(TAG, "getItemViewType: " + position);
        if(datas.size() > 0 && position < 1)
            return VIEW_HEADER;
        return super.getItemViewType(position);
    }

    public int getBottomId(int position) {
        return datas.get(position).getId();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final CircleImageView itemHeadPortraitImg;
        public final TextView itemNickName;
        public final TextView itemTitle;
       // public final TextView itemSticky;
        public final TextView itemReadNum;
        public final TextView itemComment;
        public final TextView itemTimeStamp;
        public final ImageView itemShowImgFirst,itemShowImgSecond,itemShowImgThrid;


        public ViewHolder(View itemView) {
            super(itemView);
            itemHeadPortraitImg = (CircleImageView) itemView.findViewById(R.id.home_item_header);
            itemNickName = (TextView) itemView.findViewById(R.id.home_item_nick_name);
            itemTitle = (TextView) itemView.findViewById(R.id.home_item_title);
            itemShowImgFirst = (ImageView) itemView.findViewById(R.id.home_item_show_image_first);
            itemShowImgSecond = (ImageView) itemView.findViewById(R.id.home_item_show_image_second);
            itemShowImgThrid = (ImageView) itemView.findViewById(R.id.home_item_show_image_thrid);

            //itemSticky = (TextView) itemView.findViewById(R.id.home_item_sticky_view);
            itemReadNum = (TextView) itemView.findViewById(R.id.home_item_read_num);
            itemComment = (TextView) itemView.findViewById(R.id.home_item_comment);
            itemTimeStamp = (TextView) itemView.findViewById(R.id.home_item_time_stamp);
        }
    }

    public class HeaderViewHolder extends RecyclerView.ViewHolder{

        public HeaderViewHolder(View itemView) {
            super(itemView);
        }
    }

    public interface OnItemClickListener{
        void onItemClick(RecyclerView.ViewHolder viewHolder ,NewsListResultCode.Data itemData ,int position);
    }
}
