package com.xiaobangzhu.xiaobangzhu.UI.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.xiaobangzhu.xiaobangzhu.Bean.BaseResultCode;
import com.xiaobangzhu.xiaobangzhu.Interface.DataChangeListener;
import com.xiaobangzhu.xiaobangzhu.Interface.UploadCallback;
import com.xiaobangzhu.xiaobangzhu.MyApplication;
import com.xiaobangzhu.xiaobangzhu.NetworkService.NetRequestManager;
import com.xiaobangzhu.xiaobangzhu.R;
import com.xiaobangzhu.xiaobangzhu.Utils.PickUtils;
import com.xiaobangzhu.xiaobangzhu.Utils.QiniyunManager;
import com.xiaobangzhu.xiaobangzhu.Utils.VerifyUtils;
import com.xiaobangzhu.xiaobangzhu.View.PicassoImageLoader;

import java.util.List;

import cn.finalteam.galleryfinal.GalleryFinal;
import cn.finalteam.galleryfinal.model.PhotoInfo;
import cn.finalteam.galleryfinal.widget.GFImageView;

/**
 * Created by WQC on 2016/8/22.
 */
public class PublishTeamActivity extends BaseActivity implements View.OnClickListener {
    private static final String TAG = "PublishTeamActivity";
    private TextView publishBtn;
    private TextView cancleBtn;
    private EditText teamdDescEditText;
    private GFImageView showImageView;
    private EditText requireNumEditText;
    private EditText addressEditText;
    private TextView endTimeTextView;
    private RelativeLayout endTimeBtn;
    private RadioGroup specialRadioGroup;

    String desc = null;
    String imageDir = null;
    String address = null;
    String endTime = null;
    String special = null;
    int requireNum = 2;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publish_team);
        initViews();
        initEvents();
    }

    private void initViews() {
        publishBtn = (TextView) findViewById(R.id.publish_header_publish);
        cancleBtn = (TextView) findViewById(R.id.publish_header_cancle);
        teamdDescEditText = (EditText) findViewById(R.id.team_desc_text);
        showImageView = (GFImageView) findViewById(R.id.publish_first_img);
        requireNumEditText = (EditText) findViewById(R.id.team_require_num);
        addressEditText = (EditText) findViewById(R.id.team_address);
        endTimeTextView = (TextView) findViewById(R.id.team_endtime_show);
        endTimeBtn = (RelativeLayout) findViewById(R.id.team_endtime_btn);
        specialRadioGroup = (RadioGroup) findViewById(R.id.team_special_radio);
    }

    private void initEvents() {
        publishBtn.setOnClickListener(this);
        cancleBtn.setOnClickListener(this);
        showImageView.setOnClickListener(this);
        endTimeBtn.setOnClickListener(this);
        specialRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.radio_no_limit:
                        special ="不限制";
                        break;
                    case R.id.radio_only_boy:
                        special = "只限男生";
                        break;
                    case R.id.radio_only_girl:
                        special = "只限女生";
                        break;
                }
            }
        });
        //七牛云上传回调
        QiniyunManager.setUploadCallback(new UploadCallback() {
            @Override
            public void complete(String imageUrl) {
                NetRequestManager.getInstance().addTeamTask(MyApplication.getInstance().getUserToken(), MyApplication.getInstance().getUserCollegeId(), desc, imageUrl, requireNum, address, endTime, special);
            }
        });
        //发布组队任务回调
        NetRequestManager.getInstance().setAddTeamResultCodeListener(new DataChangeListener<BaseResultCode>() {
            @Override
            public void onSuccessful(BaseResultCode data) {
                MyApplication.dismissProgress();
                if (data != null && data.getStatus() == 0) {
                    MyApplication.dismissDialog();
                    MyApplication.showToastShort("发布成功");
                    finish();
                }
            }

            @Override
            public void onError(VolleyError volleyError) {
                MyApplication.dismissProgress();
            }

            @Override
            public void onResponseNull() {
                MyApplication.dismissProgress();
            }
        });

    }


    @Override
    public void onClick(View v) {
        int viewId = v.getId();
        switch (viewId) {
            case R.id.publish_header_cancle:
                finish();
                break;
            case R.id.publish_header_publish:
                address = addressEditText.getText().toString().trim();
                desc = teamdDescEditText.getText().toString().trim();
                String numStr = requireNumEditText.getText().toString().trim();
                endTime = endTimeTextView.getText().toString().trim().replace(" ","%2B");

                if (VerifyUtils.isNull(numStr) || VerifyUtils.isNull(address) || VerifyUtils.isNull(desc) || VerifyUtils.isNull(endTime) || VerifyUtils.isNull(special)) {
                    MyApplication.showToastShort("请检查输入是否完整");
                }else{
                    requireNum = Integer.parseInt(numStr);
                    MyApplication.showProgress(PublishTeamActivity.this,"发布中","请稍等");
                    QiniyunManager.uploadPicture(PublishTeamActivity.this,imageDir);
                }

                break;
            case R.id.publish_first_img:
                GalleryFinal.openGallerySingle(0x11, new GalleryFinal.OnHanlderResultCallback() {
                    @Override
                    public void onHanlderSuccess(int reqeustCode, List<PhotoInfo> resultList) {
                        if (reqeustCode == 0x11) {
                            for (PhotoInfo photoInfo : resultList){
                                imageDir = photoInfo.getPhotoPath();
                                PicassoImageLoader picassoImageLoader = new PicassoImageLoader();
                                Log.i(TAG, "onHanlderSuccess: " + imageDir);
                                picassoImageLoader.displayImage(PublishTeamActivity.this,imageDir,showImageView, getResources().getDrawable(R.drawable.activity_luancher_bg),70,70);
                            }
                        }
                    }

                    @Override
                    public void onHanlderFailure(int requestCode, String errorMsg) {

                    }
                });
                break;
            case R.id.team_endtime_btn:
                PickUtils.pickTime(PublishTeamActivity.this, endTimeTextView);
                break;
        }

    }
}
