package com.xiaobangzhu.xiaobangzhu.UI.activity;

import android.content.Intent;
import android.icu.text.DisplayContext;
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
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.xiaobangzhu.xiaobangzhu.Bean.RegisteResultCode;
import com.xiaobangzhu.xiaobangzhu.Bean.VerifyCode;
import com.xiaobangzhu.xiaobangzhu.Interface.DataChangeListener;
import com.xiaobangzhu.xiaobangzhu.MyApplication;
import com.xiaobangzhu.xiaobangzhu.NetworkService.NetRequestManager;
import com.xiaobangzhu.xiaobangzhu.R;
import com.xiaobangzhu.xiaobangzhu.Utils.VerifyUtils;

import java.lang.reflect.Type;

/**
 * Created by WQC on 2016/8/1.
 */
public class RegisteActivity extends AppCompatActivity implements DataChangeListener{
    private static final String TAG = "RegisteActivity";
    Button nextBtn;
    Button getVerifyCodeBtn;

    TextInputLayout passwordTIL , verifyCodeTIL;
    EditText verifyCodeEditText,accountEditText, passwordEditText;
    LinearLayout haveAccountBtn;

    String userAccount;
    String userPassword;
    VerifyCode verifyCode;
    private boolean mVerifyCodeResult = false;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registe);
        initViews();
        initEvents();
        NetRequestManager.getInstance().setVerifyCodeListener(this);
    }

    private void initViews() {
        nextBtn = (Button) findViewById(R.id.next_btn);
        getVerifyCodeBtn = (Button) findViewById(R.id.regist_get_verify_code);
        accountEditText = (EditText) findViewById(R.id.regist_tele);
        //passwordEditText = (EditText) findViewById(R.id.regist_password);
        verifyCodeEditText = (EditText) findViewById(R.id.regist_verifycode);
        haveAccountBtn = (LinearLayout) findViewById(R.id.regist_have_account_btn);

        passwordTIL = (TextInputLayout) findViewById(R.id.regist_password_til);
        verifyCodeTIL = (TextInputLayout) findViewById(R.id.regist_verifycode_til);
        passwordEditText = passwordTIL.getEditText();
        verifyCodeEditText = verifyCodeTIL.getEditText();
    }

    private void initEvents() {
        //accountInput.setHintTextAppearance(R.style.AppTheme);
//        accountEditText.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//                Log.i(TAG, "onTextChanged: "+charSequence);
//
//            }
//
//            @Override
//            public void afterTextChanged(Editable editable) {
//
//            }
//        });

        passwordEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length() < 9 ||charSequence.length()>12) {
                    passwordTIL.setErrorEnabled(true);
                    passwordTIL.setError("密码以字母开头/长度9到12位");
                }else {
                    if (VerifyUtils.isPassWord(charSequence.toString())) {
                        userPassword = passwordEditText.getText().toString().trim();
                        passwordTIL.setErrorEnabled(false);
                    }else {
                        passwordTIL.setError("密码以字母开头/长度9到12位");
                        passwordTIL.setErrorEnabled(true);
                    }

                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });

        verifyCodeEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (verifyCode != null) {
                    if (!(editable.toString().equals(verifyCode.getData().getCode()))) {
                        verifyCodeTIL.setError("验证码错误");
                        verifyCodeTIL.setErrorEnabled(true);
                    }else {
                        verifyCodeTIL.setErrorEnabled(false);
                    }
                }
            }
        });

        getVerifyCodeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userAccount = accountEditText.getText().toString().trim();
                if (userAccount != null) {
                    if (!VerifyUtils.isPhone(userAccount)) {
                        accountEditText.setText("");
                        Toast.makeText(RegisteActivity.this , "请输入正确的手机号" , Toast.LENGTH_SHORT).show();
                    }else{
                        NetRequestManager.getInstance().getVerifyCode(userAccount, 1);
                        Log.i(TAG, "onClick: " + userAccount);
                        MyApplication.showToastShort("短信验证码已发出,请注意查收");
                    }
                }else{
                    Toast.makeText(RegisteActivity.this, "请输入手机号码", Toast.LENGTH_SHORT).show();
                }
            }
        });
        haveAccountBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (userAccount != null && userPassword != null && mVerifyCodeResult) {
                    Bundle bundle = new Bundle();
                    bundle.putString("userAccount", userAccount);
                    bundle.putString("userPassword", userPassword);
                    bundle.putString("verifycode", verifyCode.getData().getCode());
                    startActivity(new Intent(RegisteActivity.this, SelectCollegeActivity.class).putExtra("userData", bundle));
                }else {
                    MyApplication.showDialog(RegisteActivity.this,"请检查您的输入");
                }
            }
        });


    }
    @Override
    public void onSuccessful(Object data) {
        verifyCode = (VerifyCode) data;
        if (verifyCode.getStatus().equals("0")) {
            mVerifyCodeResult = true;
        }else if(verifyCode.getStatus().equals("1")){
            MyApplication.showDialog(RegisteActivity.this,verifyCode.getDesc());
        }
    }

    @Override
    public void onError(VolleyError volleyError) {
        MyApplication.showDialog(RegisteActivity.this,"请检查网络状况");
    }

    @Override
    public void onResponseNull() {
        MyApplication.showDialog(RegisteActivity.this,"请检查网络状况");
    }
}
