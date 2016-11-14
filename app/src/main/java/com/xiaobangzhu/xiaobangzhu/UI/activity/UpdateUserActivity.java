package com.xiaobangzhu.xiaobangzhu.UI.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.squareup.picasso.Picasso;
import com.xiaobangzhu.xiaobangzhu.Bean.BaseResultCode;
import com.xiaobangzhu.xiaobangzhu.Bean.UserBaseInform;
import com.xiaobangzhu.xiaobangzhu.Interface.DataChangeListener;
import com.xiaobangzhu.xiaobangzhu.Interface.UploadCallback;
import com.xiaobangzhu.xiaobangzhu.MyApplication;
import com.xiaobangzhu.xiaobangzhu.NetworkService.NetRequestManager;
import com.xiaobangzhu.xiaobangzhu.R;
import com.xiaobangzhu.xiaobangzhu.Utils.QiniyunManager;
import com.xiaobangzhu.xiaobangzhu.View.PicassoImageLoader;

import java.util.List;

import cn.finalteam.galleryfinal.GalleryFinal;
import cn.finalteam.galleryfinal.model.PhotoInfo;
import cn.finalteam.galleryfinal.widget.GFImageView;

/**
 * Created by WQC on 2016/9/30.
 */
public class UpdateUserActivity extends AppCompatActivity implements View.OnClickListener , DataChangeListener{
    GFImageView headerView;
    LinearLayout headerBtn;
    EditText nickNameEditText;
    EditText majorEditText;
    TextView sexTextView;
    TextView collegeTextView;
    Button updateButton;
    ImageView backImageView;
    LinearLayout addressBtn;

    String nickName = null;
    String imageDir  = null;
    String major = null;
    String sex = null;
    String collegeName = null;
    int collegeId = 0;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_user);
        initViews();
        initEvents();
        initUser();
    }

    /**
     * Y
     */
    private void initUser() {
        NetRequestManager.getInstance().getUserInform(MyApplication.getInstance().getUserToken());
        NetRequestManager.getInstance().setUserBaseInformListener(this);
    }

    private void initViews() {
        headerBtn = (LinearLayout) findViewById(R.id.update_header_btn);
        headerView = (GFImageView) findViewById(R.id.update_header_img);
        nickNameEditText = (EditText) findViewById(R.id.update_nick_name);
        majorEditText = (EditText) findViewById(R.id.update_major);
        sexTextView = (TextView) findViewById(R.id.update_sex);
        collegeTextView = (TextView) findViewById(R.id.update_college);
        addressBtn = (LinearLayout) findViewById(R.id.update_address);
        backImageView = (ImageView) findViewById(R.id.update_back_btn);
        updateButton = (Button) findViewById(R.id.update_btn);

        collegeTextView.setText(MyApplication.getInstance().getUserCollegeName());
        sexTextView.setText(MyApplication.getInstance().getUserSex());
    }

    private void initEvents() {
        headerBtn.setOnClickListener(this);
        headerView.setOnClickListener(this);
        addressBtn.setOnClickListener(this);
        backImageView.setOnClickListener(this);
        updateButton.setOnClickListener(this);
        QiniyunManager.setUploadCallback(new UploadCallback() {
            @Override
            public void complete(String imageUrl) {
                Log.i("lalala", "complete: " + imageUrl);
                NetRequestManager.getInstance().updateUserInform(MyApplication.getInstance().getUserToken(),
                        null, major, imageUrl, sex, collegeId, nickName);
            }
        });

        NetRequestManager.getInstance().setUpdateUserCodeListener(new DataChangeListener<BaseResultCode>() {
            @Override
            public void onSuccessful(BaseResultCode data) {
                MyApplication.dismissProgress();
                if (data != null && data.getStatus() == 0) {
                    MyApplication.showToastShort("更新成功");
                    finish();
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

    }

    boolean flag = false;//判断头像是否改变
    @Override
    public void onClick(View v) {
        int viewId = v.getId();
        switch (viewId) {
            case R.id.update_back_btn:
                finish();
                break;
            case R.id.update_address:
                MyApplication.showToastShort("功能即将开启");
                break;
            case R.id.update_header_img:
                GalleryFinal.openGallerySingle(0x11,new GalleryFinal.OnHanlderResultCallback() {
                    @Override
                    public void onHanlderSuccess(int reqeustCode, List<PhotoInfo> resultList) {
                        if (reqeustCode == 0x11) {
                            for (PhotoInfo photoInfo : resultList){
                                imageDir = photoInfo.getPhotoPath();
                                PicassoImageLoader picassoImageLoader = new PicassoImageLoader();
                                picassoImageLoader.displayImage(UpdateUserActivity.this,imageDir,headerView, getResources().getDrawable(R.drawable.activity_luancher_bg),70,70);
                            }
                            flag = true;
                        }
                    }

                    @Override
                    public void onHanlderFailure(int requestCode, String errorMsg) {
                        MyApplication.showDialog(UpdateUserActivity.this,errorMsg);
                    }
                });
                break;
            case R.id.update_btn:
                major = majorEditText.getText().toString().trim();
                sex = sexTextView.getText().toString().trim();
                nickName = nickNameEditText.getText().toString().trim();
                if (nickName.equals("")){
                    nickName = nickNameEditText.getHint().toString().trim();
                }
                collegeId = MyApplication.getInstance().getUserCollegeId();

                //y
                if (imageDir!= null&& major != null && sex != null && nickName != null && collegeId != 0 && flag) {
                    Log.i("img", "onClick: " + imageDir);
                    MyApplication.showProgress(UpdateUserActivity.this,"更新中","请稍候");
                    QiniyunManager.uploadPicture(UpdateUserActivity.this,imageDir);
                }else if(imageDir!= null&& major != null && sex != null && nickName != null && collegeId != 0 ) {
                    Log.i("img", "onClick2: " + imageDir);
                    MyApplication.showProgress(UpdateUserActivity.this,"更新中","请稍候");
                    NetRequestManager.getInstance().updateUserInform(MyApplication.getInstance().getUserToken(),
                            null, major, imageDir, sex, collegeId, nickName);
                } else {
                    MyApplication.showDialog(UpdateUserActivity.this,"请检查输入是否完整");
                }

                break;

        }

    }


    @Override
    public void onSuccessful(Object data) {
        UserBaseInform info = (UserBaseInform) data;
        nickName = info.getData().getNick_name();
        imageDir = info.getData().getHeader();
        major = info.getData().getMajor();
        sex = info.getData().getSex();
        collegeName = info.getData().getCollege_name();

        Picasso.with(UpdateUserActivity.this)
                .load(imageDir)
                .into(headerView);
        nickNameEditText.setText(nickName);
        nickNameEditText.setSelection(nickName.length());
        collegeTextView.setText(sex);
        sexTextView.setText(sex);
        collegeTextView.setText(collegeName);
        majorEditText.setHint(major);

        Log.i("UpDataUser", "onSuccessful: " + nickName + "\n" + imageDir+ "\n" + major+ "\n" + sex);
    }

    @Override
    public void onError(VolleyError volleyError) {
        MyApplication.dismissProgress();
        MyApplication.showToastShort(volleyError.getMessage());
    }

    @Override
    public void onResponseNull() {
        MyApplication.dismissProgress();
        MyApplication.showToastShort("获取个人信息失败");
    }
}
