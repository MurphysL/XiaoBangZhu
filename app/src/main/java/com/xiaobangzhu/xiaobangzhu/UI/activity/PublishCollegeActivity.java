package com.xiaobangzhu.xiaobangzhu.UI.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.xiaobangzhu.xiaobangzhu.Bean.BaseResultCode;
import com.xiaobangzhu.xiaobangzhu.Interface.DataChangeListener;
import com.xiaobangzhu.xiaobangzhu.Interface.MutalUploadCallback;
import com.xiaobangzhu.xiaobangzhu.MyApplication;
import com.xiaobangzhu.xiaobangzhu.NetworkService.NetRequestManager;
import com.xiaobangzhu.xiaobangzhu.R;
import com.xiaobangzhu.xiaobangzhu.Utils.PickUtils;
import com.xiaobangzhu.xiaobangzhu.Utils.QiniyunManager;
import com.xiaobangzhu.xiaobangzhu.View.PicassoImageLoader;

import java.util.ArrayList;
import java.util.List;

import cn.finalteam.galleryfinal.GalleryFinal;
import cn.finalteam.galleryfinal.model.PhotoInfo;
import cn.finalteam.galleryfinal.widget.GFImageView;

/**
 * Created by WQC on 2016/10/7.
 */
public class PublishCollegeActivity  extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "PublishCollegeActivity";
    private static final int REQUEST_PICTURE = 0x11;
    private static final int REQUEST_POSTER = 0x13;
    TextView cancleBtn;
    TextView publishBtn;
    RelativeLayout moreBtn;
    LinearLayout optionsLayout;
    ScrollView rootView;
    RelativeLayout beginTimeBtn;
    TextView beginTimeShow;
    RelativeLayout endTimeBtn;
    TextView endTimeShow;
    EditText titleEditText;
    EditText addressEditText;
    EditText contentEditText;
    GFImageView pictureImgView;
    GFImageView posterImgView;
    EditText tagsEditText;
    EditText ticketEditText;

    private String title;
    private String address;
    private String startTime;
    private String endTime;
    private String content;
    private String pictureDir;
    private String posterDir;
    private String tags;
    private int ticketNum;
    GalleryFinal.OnHanlderResultCallback hanlderResultCallback;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publish_college_activity);
        hanlderResultCallback = new GalleryFinal.OnHanlderResultCallback() {
            @Override
            public void onHanlderSuccess(int reqeustCode, List<PhotoInfo> resultList) {
                switch (reqeustCode) {
                    case REQUEST_PICTURE:
                        for (PhotoInfo photoInfo : resultList) {
                            pictureDir = photoInfo.getPhotoPath();
                            Log.i(TAG, "onHanlderSuccess: picture:" + pictureDir);
                            PicassoImageLoader picassoImageLoader = new PicassoImageLoader();
                            picassoImageLoader.displayImage(PublishCollegeActivity.this, pictureDir, pictureImgView, getResources().getDrawable(R.drawable.activity_luancher_bg), 70, 70);
                        }
                        break;
                    case REQUEST_POSTER:
                        for (PhotoInfo photoInfo : resultList) {
                            posterDir = photoInfo.getPhotoPath();
                            Log.i(TAG, "onHanlderSuccess: poster:"+posterDir);
                            PicassoImageLoader picassoImageLoader = new PicassoImageLoader();
                            picassoImageLoader.displayImage(PublishCollegeActivity.this, posterDir, posterImgView, getResources().getDrawable(R.drawable.activity_luancher_bg), 70, 70);
                        }
                        break;
                }
            }
            @Override
            public void onHanlderFailure(int requestCode, String errorMsg) {
                Log.i(TAG, "onHanlderFailure: " + errorMsg);
            }
        };
        initViews();
        initEvients();
    }



    /**
     * 初始化控件
     */
    private void initViews() {
        rootView = (ScrollView) findViewById(R.id.publish_activity_root);

        cancleBtn = (TextView) findViewById(R.id.publish_header_cancle);
        publishBtn = (TextView) findViewById(R.id.publish_header_publish);

        titleEditText = (EditText) findViewById(R.id.publish_activity_title);
        addressEditText = (EditText) findViewById(R.id.publish_activity_address);

        beginTimeBtn = (RelativeLayout) findViewById(R.id.publish_activity_begintime);
        beginTimeShow = (TextView) findViewById(R.id.publish_activity_begintime_show);

        endTimeBtn = (RelativeLayout) findViewById(R.id.publish_activity_endtime);
        endTimeShow = (TextView) findViewById(R.id.publish_activity_endtime_show);

        contentEditText = (EditText) findViewById(R.id.publish_activity_content);
        pictureImgView = (GFImageView) findViewById(R.id.publish_activity_picture);

        moreBtn = (RelativeLayout) findViewById(R.id.publish_activity_more);
        optionsLayout = (LinearLayout) findViewById(R.id.publish_activity_options);

        tagsEditText = (EditText) findViewById(R.id.publish_activity_tag);
        ticketEditText = (EditText) findViewById(R.id.publish_activity_ticket);
        posterImgView = (GFImageView) findViewById(R.id.publish_activity_poster);
    }

    /**
     * 初始化事件监听
     */
    private void initEvients() {
        cancleBtn.setOnClickListener(this);
        publishBtn.setOnClickListener(this);
        endTimeBtn.setOnClickListener(this);
        beginTimeBtn.setOnClickListener(this);
        moreBtn.setOnClickListener(this);
        posterImgView.setOnClickListener(this);
        pictureImgView.setOnClickListener(this);
        posterImgView.setOnClickListener(this);

        NetRequestManager.getInstance().setAddActivityResultCodeListener(new DataChangeListener<BaseResultCode>() {
            @Override
            public void onSuccessful(BaseResultCode data) {
                MyApplication.dismissProgress();
                if (data != null && data.getStatus() == 0) {
                    MyApplication.showToastShort("活动发布成功");
                    finish();
                }else{
                    MyApplication.showToastShort(data.getDesc());
                }
            }

            @Override
            public void onError(VolleyError volleyError) {
                MyApplication.dismissProgress();
                MyApplication.showToastShort("请检查网络状况");
            }

            @Override
            public void onResponseNull() {
                MyApplication.dismissProgress();
                MyApplication.showToastShort("请检查网络状况");
            }
        });

        QiniyunManager.setMutalUploadCallback(new MutalUploadCallback() {
            @Override
            public void mutlComplete(ArrayList<String> imageUrls) {
                Log.i(TAG, "mutlComplete: " + imageUrls.size());
                Log.i(TAG, "mutlComplete: " + imageUrls.get(0));
                Log.i(TAG, "mutlComplete: " + imageUrls.get(1));

                NetRequestManager.getInstance().addCollegeActivity(
                        MyApplication.getInstance().getUserToken(),
                        MyApplication.getInstance().getUserCollegeId(),
                        title,
                        address,
                        startTime,
                        endTime,
                        content,
                        imageUrls.get(0),
                        imageUrls.get(1),
                        tags,
                        ticketNum);

            }
        });
    }

    @Override
    public void onClick(View v) {
        int viewId = v.getId();
        switch (viewId) {
            case R.id.publish_activity_more:
                if (optionsLayout.getVisibility() == View.GONE) {
                    optionsLayout.setVisibility(View.VISIBLE);
                }else{
                    optionsLayout.setVisibility(View.GONE);
                }
                break;
            case R.id.publish_header_publish:
                title = titleEditText.getText().toString().trim();
                address = addressEditText.getText().toString().trim();
                content = contentEditText.getText().toString().trim();
                tags = tagsEditText.getText().toString().trim();
                String ticketStr = ticketEditText.getText().toString().trim();
                startTime = beginTimeShow.getText().toString().trim();
                Log.i(TAG, "onClick: "+startTime);
                endTime = endTimeShow.getText().toString().trim();
                Log.i(TAG, "onClick: "+endTime);
                if (ticketStr ==""||ticketStr==null||pictureDir == null || pictureDir==""||posterDir==null|posterDir==""||title == null || title == "" || address == null || address == "" || startTime == null || startTime == "" | endTime == "" || endTime == null || content == null || content == "") {
                    MyApplication.showToastShort("请检查输入");
                }else{
                    ticketNum  = Integer.parseInt(ticketStr);
                    MyApplication.showProgress(PublishCollegeActivity.this,"发布中","请稍后");
                    QiniyunManager.uploadMutlPicture(PublishCollegeActivity.this, pictureDir, posterDir);
                }
                break;
            case R.id.publish_header_cancle:
                finish();
                break;
            case R.id.publish_activity_begintime:
                PickUtils.pickTime(PublishCollegeActivity.this, beginTimeShow);
                break;
            case R.id.publish_activity_endtime:
                PickUtils.pickTime(PublishCollegeActivity.this, endTimeShow);
                break;
            case R.id.publish_activity_picture:
                GalleryFinal.openGallerySingle(REQUEST_PICTURE,hanlderResultCallback);
                break;
            case R.id.publish_activity_poster:
                GalleryFinal.openGallerySingle(REQUEST_POSTER,hanlderResultCallback);
                break;

        }
    }
}
