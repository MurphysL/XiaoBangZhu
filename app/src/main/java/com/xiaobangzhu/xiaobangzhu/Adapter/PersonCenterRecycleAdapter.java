package com.xiaobangzhu.xiaobangzhu.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.xiaobangzhu.xiaobangzhu.R;

import java.util.List;

/**
 * Created by WQC on 2016/7/29.
 */
public class PersonCenterRecycleAdapter extends RecyclerView.Adapter<PersonCenterRecycleAdapter.PersonCenterRecycleHolder>{
    List<String> imageUrlList;
    Context mContext;

    public PersonCenterRecycleAdapter(List<String> imgUrlList, Context context) {
        this.mContext = context;
        this.imageUrlList = imgUrlList;
    }

    @Override
    public PersonCenterRecycleAdapter.PersonCenterRecycleHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_person_center_list_item, parent, false);
        return new PersonCenterRecycleHolder(itemView);
    }

    @Override
    public void onBindViewHolder(PersonCenterRecycleHolder holder, int position) {
        String imageUrl = imageUrlList.get(position);
        Picasso.with(mContext).load(imageUrl).into(holder.imageShow);
    }

    @Override
    public int getItemCount() {
        return imageUrlList.size();
    }

    public static class PersonCenterRecycleHolder extends RecyclerView.ViewHolder {
        public final ImageView imageShow;

        public PersonCenterRecycleHolder(View itemView) {
            super(itemView);
            imageShow = (ImageView) itemView.findViewById(R.id.person_center_iamge);
        }
    }

}
