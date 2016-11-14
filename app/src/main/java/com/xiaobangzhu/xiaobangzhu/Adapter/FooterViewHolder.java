package com.xiaobangzhu.xiaobangzhu.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import com.xiaobangzhu.xiaobangzhu.R;

/**
 * Created by WQC on 2016/10/22.
 */

public class FooterViewHolder extends RecyclerView.ViewHolder {
    public final ProgressBar progressBar;

    public FooterViewHolder(View itemView) {
        super(itemView);
        progressBar = (ProgressBar) itemView.findViewById(R.id.footer_progress);
    }
}
