package com.xiaobangzhu.xiaobangzhu.View;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.panxw.android.imageindicator.ImageIndicatorView;
import com.squareup.picasso.Picasso;
import com.xiaobangzhu.xiaobangzhu.R;

import java.util.List;

/**
 * Created by WQC on 2016/7/15.
 */
public class NetWorkImageIndicator extends ImageIndicatorView {

    private static final String TAG = "NetWorkImage";
    Context mContext;
    boolean loadResult = false;
    List<String> adLists;

    public NetWorkImageIndicator(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public NetWorkImageIndicator(Context context) {
        super(context);
        this.mContext = context;
    }
    @Override
    public void setupLayoutByImageUrl(List<String> urlList) {
        adLists = urlList;
        for (String url : adLists) {
            ImageView imageView = new ImageView(getContext());
            imageView.setLayoutParams(new LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
            imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
            Log.i(TAG, "setupLayoutByImageUrl: "+url);
            Picasso.with(getContext())
                    .load(url)
                    .resize(150,50)
                    .centerInside()
                    .placeholder(R.drawable.login_background)
                    .error(R.drawable.person_message)
                    .into(imageView);
            addViewItem(imageView);
        }
    }
}
