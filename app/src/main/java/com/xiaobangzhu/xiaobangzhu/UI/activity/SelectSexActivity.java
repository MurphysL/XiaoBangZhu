package com.xiaobangzhu.xiaobangzhu.UI.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.android.volley.VolleyError;
import com.xiaobangzhu.xiaobangzhu.Bean.RegisteResultCode;
import com.xiaobangzhu.xiaobangzhu.Interface.DataChangeListener;
import com.xiaobangzhu.xiaobangzhu.MyApplication;
import com.xiaobangzhu.xiaobangzhu.NetworkService.NetRequestManager;
import com.xiaobangzhu.xiaobangzhu.R;
import com.xiaobangzhu.xiaobangzhu.Utils.MD5Util;

public class SelectSexActivity extends AppCompatActivity implements View.OnClickListener,DataChangeListener<RegisteResultCode>{
    private static final String TAG = "SelectSexActivity";
    String userAccount;
    String userPassword;
    int selectedCollegeId;
    Button mRegisteButton;
    ImageView boyImageView;
    ImageView girlImageView;
    boolean boySelected = true;
    boolean girlSelected = false;
    String sexResult = "男";
    ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_sex);
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("userData");
        userAccount = bundle.getString("userAccount");
        userPassword = bundle.getString("userPassword");
        selectedCollegeId = bundle.getInt("selectedCollegeId");

        mRegisteButton = (Button) findViewById(R.id.registe_registe_btn);
        boyImageView = (ImageView) findViewById(R.id.registe_boy_iamge);
        girlImageView = (ImageView) findViewById(R.id.registe_girl_iamge);

        mRegisteButton.setOnClickListener(this);
        boyImageView.setOnClickListener(this);
        girlImageView.setOnClickListener(this);
        NetRequestManager.getInstance().setRegisteResultCodeListener(this);
    }

    /**
     * 选择性别时结果进行反转
     */
    private void sexSelected() {
        boySelected = !boySelected;
        girlSelected = !girlSelected;

        if (boySelected == true && girlSelected == false) {
            boyImageView.setSelected(true);
            boyImageView.setImageResource(R.drawable.registe_boy_selected);
            girlImageView.setImageResource(R.drawable.registe_girl_unselected);
            sexResult = "男";
        } else if (boySelected == false && girlSelected == true) {
            girlImageView.setSelected(true);
            girlImageView.setImageResource(R.drawable.registe_girl_selected);
            boyImageView.setImageResource(R.drawable.registe_boy_unselected);
            sexResult = "女";
        }else{
            MyApplication.showToastShort("error:页面错误");
        }
        Log.i(TAG, "sexSelected: " + sexResult);
    }

    @Override
    public void onClick(View view) {
        int viewId = view.getId();
        if (viewId == R.id.registe_boy_iamge || viewId == R.id.registe_girl_iamge) {
            sexSelected();
        } else if (viewId == R.id.registe_registe_btn) {
            if (userAccount != null && userPassword != null && sexResult != null) {
                Log.i(TAG, "userAccount:" + userAccount + "\n" + "userPassword:" + userPassword + "\n" + "sexResult:" + sexResult+"collegeId"+selectedCollegeId);
                MyApplication.showProgress(SelectSexActivity.this,"提示","正在注册 请稍后");
                NetRequestManager.getInstance().registeUser(userAccount, MD5Util.MD5(userPassword), sexResult,selectedCollegeId);
            }
        }

    }

    @Override
    public void onSuccessful(RegisteResultCode data) {
        MyApplication.dismissProgress();
        if (data != null) {
            if (data.getStatus().equals("0")) {
                MyApplication.showToastShort("注册成功,请登录");
                startActivity(new Intent(SelectSexActivity.this, LaunchActivity.class));
                finish();
            } else if (data.getStatus().equals("1")) {
                MyApplication.showToastShort("注册失败");
            } else if (data.getStatus().equals("100")) {
                MyApplication.showToastShort("参数格式错误");
            }
        }
    }

    @Override
    public void onError(VolleyError volleyError) {
        MyApplication.dismissProgress();
        MyApplication.showDialog(SelectSexActivity.this, volleyError.getMessage());
    }

    @Override
    public void onResponseNull() {

    }
}
