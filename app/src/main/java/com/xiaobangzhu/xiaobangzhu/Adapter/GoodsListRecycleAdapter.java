package com.xiaobangzhu.xiaobangzhu.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.xiaobangzhu.xiaobangzhu.R;

import java.util.List;

/**
 * GoodsListRecycleAdapter
 *
 * @author: MurphySL
 * @time: 2017/1/2 16:23
 */


public class GoodsListRecycleAdapter extends RecyclerView.Adapter<GoodsListRecycleAdapter.GoodsViewHolder>{

    private Context context ;
    private List data;

    public GoodsListRecycleAdapter(Context context , List data){
        this.context = context;
        this.data = data;
    }

    @Override
    public GoodsListRecycleAdapter.GoodsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new GoodsListRecycleAdapter.GoodsViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.goods_list_view, parent, false));
    }

    @Override
    public void onBindViewHolder(GoodsViewHolder holder, int position) {
        holder.iv_goods.setImageResource(R.mipmap.emperor_member);
        holder.tx_goods.setText("珍珠奶茶");
        holder.tx_price.setText("15");
    }


    @Override
    public int getItemCount() {
        //return data.size();
        return 1;
    }

    public class GoodsViewHolder extends RecyclerView.ViewHolder{
        private ImageView iv_goods;
        private TextView tx_goods;
        private TextView tx_price;

        public GoodsViewHolder(View itemView) {
            super(itemView);
            iv_goods = (ImageView) itemView.findViewById(R.id.goods_image);
            tx_goods = (TextView) itemView.findViewById(R.id.goods_name);
            tx_price = (TextView) itemView.findViewById(R.id.goods_price);
        }

    }
}
