package com.xiaobangzhu.xiaobangzhu.UI.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.android.volley.VolleyError;
import com.ant.liao.GifView;
import com.xiaobangzhu.xiaobangzhu.Bean.LoginResultCode;
import com.xiaobangzhu.xiaobangzhu.Interface.DataChangeListener;
import com.xiaobangzhu.xiaobangzhu.MyApplication;
import com.xiaobangzhu.xiaobangzhu.NetworkService.NetRequestManager;
import com.xiaobangzhu.xiaobangzhu.R;
import com.xiaobangzhu.xiaobangzhu.Utils.MD5Util;
import com.xiaobangzhu.xiaobangzhu.Utils.VerifyUtils;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by WQC on 2016/7/17.
 */
public class LaunchActivity extends AppCompatActivity implements View.OnClickListener,DataChangeListener<LoginResultCode>{
    private static final String TAG = "LaunchActivity";
    GifView mGifView;
    EditText accountEditText;
    EditText passWordEditText;
    Button loginButton;
    LinearLayout forgetPasswordBtn,registeBtn;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch);
        initViews();
        initEvent();
    }

    private void initViews() {
        mGifView = (GifView) findViewById(R.id.launch_gif);
        mGifView.setShowDimension(this.getWindowManager().getDefaultDisplay().getWidth(), this.getWindowManager().getDefaultDisplay().getHeight());
        mGifView.setGifImage(R.drawable.activity_luancher_bg);

        forgetPasswordBtn = (LinearLayout) findViewById(R.id.forget_password_btn);
        registeBtn = (LinearLayout) findViewById(R.id.registe_btn);
        loginButton = (Button) findViewById(R.id.login_btn);
        accountEditText = (EditText) findViewById(R.id.login_tele);
        passWordEditText = (EditText) findViewById(R.id.login_password);
    }

    private void initEvent() {
        NetRequestManager.getInstance().setLoginResultCodeListener(this);
        loginButton.setOnClickListener(this);
        registeBtn.setOnClickListener(this);
        forgetPasswordBtn.setOnClickListener(this);

        if (accountEditText != null) {
            accountEditText.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {

                }
                @Override
                public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                    if (charSequence.length() == 11) {

                    }
                }
                @Override
                public void afterTextChanged(Editable editable) {

                }
            });
        }
        //passWordInputLayout.setHint("请输入密码");
        //passWordEditText = (EditText) passWordInputLayout.getEditText();
        if (passWordEditText != null) {
            passWordEditText.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                    if (charSequence.length() < 6 || charSequence.length()>9) {
                    }
                }

                @Override
                public void afterTextChanged(Editable editable) {

                }
            });
        }

    }
    @Override
    public void onClick(View view) {
        int viewId = view.getId();
        Intent intent = new Intent();
        switch (viewId) {
            case R.id.login_btn:
                String account = accountEditText.getText().toString().trim();
                String pass = passWordEditText.getText().toString().trim();

                Log.i(TAG, "onClick: " + account +pass);
                if (!VerifyUtils.isPhone(account)) {
                    MyApplication.showToastShort("请输入正确的账号");
                }
                Log.w(TAG, "onClick: "+account+"\t"+pass);
                MyApplication.showProgress(LaunchActivity.this,"登录中","正在登录请稍后");
                NetRequestManager.getInstance().login(account, MD5Util.MD5(pass));
                break;
            case R.id.forget_password_btn:
                break;
            case R.id.registe_btn:
                startActivity(new Intent(LaunchActivity.this, RegisteActivity.class));
                break;
            default:
                break;
        }

    }


    @Override
    public void onSuccessful(LoginResultCode data) {
        MyApplication.dismissProgress();
        if (data.getStatus() == 0) {
            MyApplication.getInstance().setUserLogin(true);//Y
            Intent intent = new Intent(LaunchActivity.this, MainActivity.class);
            MyApplication.getInstance().writeUserInfrom(data.getData());
            startActivity(intent);
            finish();
        } else if (data.getStatus() == 1) {
            MyApplication.showDialog(LaunchActivity.this,"登录失败,请重试");
        }
    }

    @Override
    public void onError(VolleyError volleyError) {
        MyApplication.dismissProgress();
        MyApplication.showDialog(LaunchActivity.this,"请检查网络状况");
    }

    @Override
    public void onResponseNull() {
        MyApplication.dismissProgress();
        MyApplication.showDialog(LaunchActivity.this,"请检查网络状况");
    }
}
