package com.xiaobangzhu.xiaobangzhu.Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.squareup.picasso.Picasso;
import com.xiaobangzhu.xiaobangzhu.Interface.OnViewFlipperClickListener;

import java.util.List;

/**
 * Created by WQC on 2016/10/23.
 */

public class ViewFlipperAdapter extends BaseAdapter {
    private OnViewFlipperClickListener onItemClickListener;
    private List<String> imageUrlList;
    private Context mContext;

    public ViewFlipperAdapter( Context mContext,List<String> imageUrlList) {
        this.imageUrlList = imageUrlList;
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return imageUrlList.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View view, ViewGroup viewGroup) {
        ImageView imageView = new ImageView(mContext);
        imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        Picasso.with(mContext)
                .load(imageUrlList.get(position))
                .fit()
                .centerCrop()
                .into(imageView);
        imageView.setLayoutParams(new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        if (onItemClickListener != null) {
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.onItemClick(imageUrlList.get(position),position);
                }
            });
        }
        return imageView;
    }

    public void setOnItemClickListener(OnViewFlipperClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
}
