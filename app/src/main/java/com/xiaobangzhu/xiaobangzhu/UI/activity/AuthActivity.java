package com.xiaobangzhu.xiaobangzhu.UI.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.xiaobangzhu.xiaobangzhu.Bean.BaseResultCode;
import com.xiaobangzhu.xiaobangzhu.Interface.DataChangeListener;
import com.xiaobangzhu.xiaobangzhu.Interface.UploadCallback;
import com.xiaobangzhu.xiaobangzhu.MyApplication;
import com.xiaobangzhu.xiaobangzhu.NetworkService.NetRequestManager;
import com.xiaobangzhu.xiaobangzhu.R;
import com.xiaobangzhu.xiaobangzhu.Utils.QiniyunManager;
import com.xiaobangzhu.xiaobangzhu.Utils.VerifyUtils;
import com.xiaobangzhu.xiaobangzhu.View.PicassoImageLoader;

import java.util.List;

import cn.finalteam.galleryfinal.GalleryFinal;
import cn.finalteam.galleryfinal.model.PhotoInfo;
import cn.finalteam.galleryfinal.widget.GFImageView;

/**
 * Created by WQC on 2016/9/30.
 */

public class AuthActivity extends BaseActivity implements RadioGroup.OnCheckedChangeListener, View.OnClickListener {
    private static final String TAG = "AuthActivity";
    EditText nameEditText;
    RadioGroup educationRadioGroup;
    EditText collegeEditText;
    EditText collegeStartEditText;
    TextView phoneTextView;
    EditText identifyEditText;
    GFImageView imageView;
    Button submitButton;
    ImageView backImageView;

    String name = null;
    String college = null;
    String education = "在读";
    String picture = null;
    String identity = null;
    int eduStartDate = 0;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);
        initViews();
        initEvent();

    }

    private void initViews() {
        backImageView = (ImageView) findViewById(R.id.auth_back_btn);
        nameEditText = (EditText) findViewById(R.id.auth_name);
        educationRadioGroup = (RadioGroup) findViewById(R.id.auth_education_radio_group);
        collegeEditText = (EditText) findViewById(R.id.auth_college);
        collegeStartEditText = (EditText) findViewById(R.id.auth_college_start_time);
        phoneTextView = (TextView) findViewById(R.id.auth_phone);
        identifyEditText = (EditText) findViewById(R.id.auth_identify);
        imageView = (GFImageView) findViewById(R.id.auth_picture);
        submitButton = (Button) findViewById(R.id.auth_submit_btn);
        phoneTextView.setText(MyApplication.getInstance().getUserLoginId());
    }

    private void initEvent() {
        backImageView.setOnClickListener(this);
        submitButton.setOnClickListener(this);
        imageView.setOnClickListener(this);

        educationRadioGroup.setOnCheckedChangeListener(this);

        QiniyunManager.setUploadCallback(new UploadCallback() {
            @Override
            public void complete(String imageUrl) {
                NetRequestManager.getInstance().auth(MyApplication.getInstance().getUserToken(), name, college, education, imageUrl, identity, eduStartDate);
            }
        });

        NetRequestManager.getInstance().setAuthResultCodeListener(new DataChangeListener<BaseResultCode>() {
            @Override
            public void onSuccessful(BaseResultCode data) {
                MyApplication.dismissProgress();
                if (data != null) {
                    if (data.getStatus() == 0) {
                        MyApplication.showToastShort("认证信息已提交");
                    } else {
                        MyApplication.showToastShort(data.getDesc());
                    }
                }
            }

            @Override
            public void onError(VolleyError volleyError) {
                MyApplication.dismissProgress();
                MyApplication.showDialog(AuthActivity.this,"请检查网络状况");
            }

            @Override
            public void onResponseNull() {
                MyApplication.dismissProgress();
                MyApplication.showDialog(AuthActivity.this,"请检查网络状况");
            }
        });
    }

    /**
     * Y
     * 教育背景--》》性质
     * @param group
     * @param checkedId
     */
    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.auth_stu:
                education = "学生";
                break;
            case R.id.auth_tea:
                education = "教师";
                break;
            case R.id.auth_org:
                education = "组织";
                break;
            case R.id.auth_bus:
                education = "商家";
                break;
        }
        Log.i(TAG, "onCheckedChanged: " + education);
    }

    @Override
    public void onClick(View v) {
        int viewId = v.getId();
        switch (viewId) {
            case R.id.auth_back_btn:
                finish();
                break;
            case R.id.auth_picture:
                GalleryFinal.openGallerySingle(0x11, new GalleryFinal.OnHanlderResultCallback() {

                    @Override
                    public void onHanlderSuccess(int reqeustCode, List<PhotoInfo> resultList) {
                        if (reqeustCode == 0x11) {
                            for (PhotoInfo photoInfo : resultList){
                                picture = photoInfo.getPhotoPath();
                                PicassoImageLoader picassoImageLoader = new PicassoImageLoader();
                                picassoImageLoader.displayImage(AuthActivity.this,picture,imageView, getResources().getDrawable(R.drawable.activity_luancher_bg),70,70);
                            }
                        }
                    }

                    @Override
                    public void onHanlderFailure(int requestCode, String errorMsg) {

                    }
                });

                break;
            case R.id.auth_submit_btn:
                name = nameEditText.getText().toString().trim();
                String edu = collegeStartEditText.getText().toString().trim();
                Log.i(TAG, "onClick: " + edu);
                identity = identifyEditText.getText().toString().trim();
                college = collegeEditText.getText().toString().trim();

                Log.i(TAG, "onClick: " + name+eduStartDate + identity + college);

                if (VerifyUtils.isNull(college) || VerifyUtils.isNull(name)|| VerifyUtils.isNull(education)|| VerifyUtils.isNull(identity) || VerifyUtils.isNull(picture)|| VerifyUtils.isNull(edu)) {
                    MyApplication.showToastShort("请检查输入");
                } else {
                    if (edu != null && edu != "") {
                        eduStartDate = Integer.valueOf(edu);
                    }
                    MyApplication.showProgress(AuthActivity.this, "上传中", "请稍等");
                    QiniyunManager.uploadPicture(AuthActivity.this, picture);
                }
                break;

        }
    }
}
