package com.xiaobangzhu.xiaobangzhu.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.xiaobangzhu.xiaobangzhu.Bean.NewItemsListBean;
import com.xiaobangzhu.xiaobangzhu.R;
import com.xiaobangzhu.xiaobangzhu.UI.activity.PayForGoodsActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * GoodsListRecycleAdapter
 *
 * @author: MurphySL
 * @time: 2017/1/2 16:23
 */


public class GoodsListRecycleAdapter extends RecyclerView.Adapter<GoodsListRecycleAdapter.GoodsViewHolder>{
    private static final String TAG = "GoodsListRecycleAdapter";

    private Context context ;
    private List<NewItemsListBean> data = new ArrayList<>();

    public GoodsListRecycleAdapter(Context context , List<NewItemsListBean> data){
        this.context = context;
        this.data = data;
        if(data != null){
            Log.i(TAG, "GoodsListRecycleAdapter: " + data.size());
        }
    }

    @Override
    public GoodsListRecycleAdapter.GoodsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new GoodsListRecycleAdapter.GoodsViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.goods_list_view, parent, false));
    }

    @Override
    public void onBindViewHolder(final GoodsViewHolder holder, int position) {
        NewItemsListBean bean = data.get(position);
        Picasso.with(context)
                .load(bean.getPic())
                .into(holder.iv_goods);
        holder.tx_goods.setText(bean.getName());

        Log.i(TAG, "onBindViewHolder: " + bean.getPriceh());
        //价格更改
        holder.tx_price.setText(bean.getPriceh()+"");
        holder.tx_member.setText("普通会员");

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context , PayForGoodsActivity.class));
            }
        });
    }


    @Override
    public int getItemCount() {
        Log.i(TAG, "getItemCount: " + data.size());
        return data.size();
    }

    public class GoodsViewHolder extends RecyclerView.ViewHolder{
        private ImageView iv_goods;
        private TextView tx_goods;
        private TextView tx_price;
        private View itemView;
        private TextView tx_member;

        public GoodsViewHolder(View itemView) {
            super(itemView);
            this.itemView = itemView;
            iv_goods = (ImageView) itemView.findViewById(R.id.goods_image);
            tx_goods = (TextView) itemView.findViewById(R.id.goods_name);
            tx_price = (TextView) itemView.findViewById(R.id.goods_price);
            tx_member = (TextView) itemView.findViewById(R.id.goods_member);
        }

    }
}
