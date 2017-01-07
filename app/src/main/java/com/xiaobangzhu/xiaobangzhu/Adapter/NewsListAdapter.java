package com.xiaobangzhu.xiaobangzhu.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.xiaobangzhu.xiaobangzhu.Bean.NewsListResultCode;
import com.xiaobangzhu.xiaobangzhu.R;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * NewsListAdapter
 *
 * @author: MurphySL
 * @time: 2016/11/5 15:21
 */


public class NewsListAdapter extends BaseAdapter {

    private static final String TAG = "NewsListAdapter";
    List<NewsListResultCode.Data> datas;
    Context mContext;
    private boolean loading = false;

    public NewsListAdapter(Context context, List<NewsListResultCode.Data> dataList) {
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
    public int getCount() {
        return datas.size();
    }

    @Override
    public Object getItem(int position) {
        return datas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public int getItemCount(){
        return datas.size() ;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;

        if(null == convertView){
            convertView = LayoutInflater.from(mContext).inflate(R.layout.tab_home_newslist_item , parent , false);
            viewHolder = new ViewHolder();
            viewHolder.itemHeadPortraitImg = (CircleImageView) convertView.findViewById(R.id.home_item_header);
            viewHolder.itemNickName = (TextView) convertView.findViewById(R.id.home_item_nick_name);
            viewHolder.itemTitle = (TextView) convertView.findViewById(R.id.home_item_title1);
            viewHolder.itemShowImgFirst = (ImageView) convertView.findViewById(R.id.home_item_show_image_first);
            viewHolder.itemShowImgSecond = (ImageView) convertView.findViewById(R.id.home_item_show_image_second);
            viewHolder.itemShowImgThrid = (ImageView) convertView.findViewById(R.id.home_item_show_image_thrid);
            viewHolder.itemReadNum = (TextView) convertView.findViewById(R.id.home_item_read_num);
            viewHolder.itemComment = (TextView) convertView.findViewById(R.id.home_item_comment);
            viewHolder.itemTimeStamp = (TextView) convertView.findViewById(R.id.home_item_time_stamp);
            convertView.setTag(viewHolder);
        } else {
            //获取缓存布局
            viewHolder = (ViewHolder) convertView.getTag();
        }

        final NewsListResultCode.Data data = datas.get(position);
        Log.i(TAG, position + "getView: " + data.toString());
        Picasso.with(mContext)
                .load(data.getHeader())
                .into(viewHolder.itemHeadPortraitImg);
        viewHolder.itemNickName.setText(data.getNick_name());
        viewHolder.itemTitle.setText(data.getTitle());
        if (data.getPicture1() != null && data.getPicture1() != "") {
            viewHolder.itemShowImgFirst.setVisibility(View.VISIBLE);
            Picasso.with(mContext).load(data.getPicture1()).into(viewHolder.itemShowImgFirst);
        }else{
            viewHolder.itemShowImgFirst.setImageResource(R.color.base_white);
        }
        if (data.getPicture2() != null && data.getPicture2() != "") {
            viewHolder.itemShowImgSecond.setVisibility(View.VISIBLE);
            Picasso.with(mContext).load(data.getPicture2()).into(viewHolder.itemShowImgSecond);
        }else{
            viewHolder.itemShowImgSecond.setImageResource(R.color.base_white);
        }
        if (data.getPicture3() != null && data.getPicture3() != "") {
            viewHolder.itemShowImgThrid.setVisibility(View.VISIBLE);
            Picasso.with(mContext).load(data.getPicture3()).into(viewHolder.itemShowImgThrid);
        }else{
            viewHolder.itemShowImgThrid.setImageResource(R.color.base_white);
        }
        if (data.getHeader() != null) {
            viewHolder.itemHeadPortraitImg.setVisibility(View.VISIBLE);
            Picasso.with(mContext).load(data.getHeader()).into(viewHolder.itemHeadPortraitImg);
        }
        viewHolder.itemReadNum.setText(data.getRead_num()+"");
        viewHolder.itemComment.setText(data.getComments()+"");
        viewHolder.itemTimeStamp.setText(data.getC_time()+"");

        return convertView;
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    class ViewHolder{
        public CircleImageView itemHeadPortraitImg;
        public TextView itemNickName;
        public TextView itemTitle;
        public TextView itemReadNum;
        public TextView itemComment;
        public TextView itemTimeStamp;
        public ImageView itemShowImgFirst,itemShowImgSecond,itemShowImgThrid;
    }
}