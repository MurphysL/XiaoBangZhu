package com.xiaobangzhu.xiaobangzhu.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.xiaobangzhu.xiaobangzhu.Interface.RecycleViewItemClickListener;
import com.xiaobangzhu.xiaobangzhu.R;

/**
 * Created by WQC on 2016/8/3.
 */
public class DebitRecycleAdapter extends RecyclerView.Adapter<DebitRecycleAdapter.DebitRecycleViewHolder>{
    String[] names;
    int[] icons;
    RecycleViewItemClickListener itemClickListener;

    public DebitRecycleAdapter(String[] names, int[] icons) {
        this.names = names;
        this.icons = icons;
    }
    
    @Override
    public DebitRecycleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.debit_recycle_item, parent, false);
        return new DebitRecycleViewHolder(itemView,itemClickListener);
    }

    public void setItemClickListener(RecycleViewItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @Override
    public void onBindViewHolder(DebitRecycleViewHolder holder, int position) {
        holder.mImageView.setImageResource(icons[position]);
        holder.mTextView.setText(names[position]);
    }

    @Override
    public int getItemCount() {
        return icons.length;
    }


    public class DebitRecycleViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        ImageView mImageView;
        TextView mTextView;
        RecycleViewItemClickListener itemClickListener;

        public DebitRecycleViewHolder(View itemView, RecycleViewItemClickListener viewItemClickListener) {
            super(itemView);
            this.mImageView = (ImageView) itemView.findViewById(R.id.debit_recycle_item_img);
            this.mTextView = (TextView) itemView.findViewById(R.id.debit_recycle_item_text);
            this.itemClickListener = viewItemClickListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (itemClickListener != null) {
                itemClickListener.onItemClick(view, getAdapterPosition());
            }
        }
    }


}


