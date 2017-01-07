package com.xiaobangzhu.xiaobangzhu.UI.activity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v8.renderscript.Allocation;
import android.support.v8.renderscript.Element;
import android.support.v8.renderscript.RenderScript;
import android.support.v8.renderscript.ScriptIntrinsicBlur;
import android.widget.ImageView;

import com.android.volley.VolleyError;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;
import com.xiaobangzhu.xiaobangzhu.Bean.UserBaseInform;
import com.xiaobangzhu.xiaobangzhu.Interface.DataChangeListener;
import com.xiaobangzhu.xiaobangzhu.MyApplication;
import com.xiaobangzhu.xiaobangzhu.NetworkService.NetRequestManager;
import com.xiaobangzhu.xiaobangzhu.R;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * FamousVisitorInfoActivity
 *
 * @author: MurphySL
 * @time: 2016/12/4 23:58
 */


public class FamousVisitorInfoActivity extends AppCompatActivity {

    private ImageView header_bg;
    private CircleImageView header;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.famous_visitor_info);

        NetRequestManager.getInstance().getUserInform(MyApplication.getInstance().getUserToken());
        NetRequestManager.getInstance().setUserBaseInformListener(new DataChangeListener<UserBaseInform>() {
            @Override
            public void onSuccessful(UserBaseInform data) {
                if(data.getData() != null){
                    String imageDir = data.getData().getHeader();
                    if(imageDir != null){
                        Picasso.with(FamousVisitorInfoActivity.this)
                                .load(imageDir)
                                .transform(new GaussTransform())
                                .fit()
                                .into(header_bg);
                        Picasso.with(FamousVisitorInfoActivity.this)
                                .load(imageDir)
                                .into(header);
                    }
                }
            }

            @Override
            public void onError(VolleyError volleyError) {

            }

            @Override
            public void onResponseNull() {

            }
        });

        initView();
    }

    private void initView() {
        header_bg = (ImageView) findViewById(R.id.famous_info_bg);
        header = (CircleImageView) findViewById(R.id.famous_visitor_header);
    }

    public class GaussTransform implements Transformation{

        @Override
        public Bitmap transform(Bitmap source) {
            Bitmap outBitmap = Bitmap.createBitmap(source.getWidth(), source.getHeight(), Bitmap.Config.ARGB_8888);

            RenderScript rs = RenderScript.create(FamousVisitorInfoActivity.this);
            // 创建高斯模糊对象
            ScriptIntrinsicBlur blurScript = ScriptIntrinsicBlur.create(rs, Element.U8_4(rs));

            // 创建Allocations，此类是将数据传递给RenderScript内核的主要方法，
            // 并制定一个后备类型存储给定类型
            Allocation allIn = Allocation.createFromBitmap(rs, source);
            Allocation allOut = Allocation.createFromBitmap(rs, outBitmap);

            // 设定模糊度
            blurScript.setRadius(10.f);

            // Perform the Renderscript
            blurScript.setInput(allIn);
            blurScript.forEach(allOut);

            // Copy the final bitmap created by the out Allocation to the outBitmap
            allOut.copyTo(outBitmap);

            // recycle the original bitmap
            source.recycle();

            // After finishing everything, we destroy the Renderscript.
            rs.destroy();
            return outBitmap;
        }

        @Override
        public String key() {
            return "gauss";
        }
    }
}
