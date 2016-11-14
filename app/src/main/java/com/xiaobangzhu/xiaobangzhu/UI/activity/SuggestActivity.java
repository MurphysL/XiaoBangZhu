package com.xiaobangzhu.xiaobangzhu.UI.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.xiaobangzhu.xiaobangzhu.Bean.BaseResultCode;
import com.xiaobangzhu.xiaobangzhu.Interface.DataChangeListener;
import com.xiaobangzhu.xiaobangzhu.MyApplication;
import com.xiaobangzhu.xiaobangzhu.NetworkService.NetRequestManager;
import com.xiaobangzhu.xiaobangzhu.R;
import com.xiaobangzhu.xiaobangzhu.Utils.VerifyUtils;

/**
 * Created by WQC on 2016/9/30.
 */
public class SuggestActivity  extends AppCompatActivity{
    private static final String TAG = "SuggestActivity";

    ImageView backBtn;
    EditText suggestContent;
    EditText suggestPhone;
    TextView suggestNum;
    TextInputLayout textInputLayout;
    Button sendBtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suggestion);
        initViews();
        initEvents();
    }

    private void initEvents() {
        //初始化回退键
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String content = suggestContent.getText().toString().trim();
                String phone = suggestPhone.getText().toString().trim();
                Log.i(TAG, "onClick: "+ content+phone);
                if (VerifyUtils.isNull(content) || VerifyUtils.isNull(phone)) {
                    MyApplication.showToastShort("请检查输入是否正确");
                }else{
                    if (VerifyUtils.isEmail(phone) || VerifyUtils.isEmail(phone)) {
                        MyApplication.showProgress(SuggestActivity.this,"请稍后","反馈中");
                        NetRequestManager.getInstance().addSuggest(MyApplication.getInstance().getUserToken(), content, phone);
                    }else{
                        MyApplication.showToastShort("请输入正确的联系方式");
                    }
                }
            }
        });

        suggestContent.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                return false;
            }
        });
        //初始化内容edittext
        if (suggestContent != null) {
            suggestContent.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                }
                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    suggestNum.setText(s.length()+"/300");
                    if (s.length() > 300) {
                        textInputLayout.setError("字数超出限制");
                        textInputLayout.setErrorEnabled(true);
                    }
                }
                @Override
                public void afterTextChanged(Editable s) {

                }
            });
        }

        NetRequestManager.getInstance().setAddSuggestResultCodeChangListener(new DataChangeListener<BaseResultCode>() {
            @Override
            public void onSuccessful(BaseResultCode data) {
                if (data != null) {
                    if (data.getStatus() == 0) {
                        MyApplication.dismissProgress();
                        MyApplication.showToastShort("反馈成功");
                        finish();
                    }
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

    private void initViews() {
        backBtn = (ImageView) findViewById(R.id.suggest_back);
        suggestNum = (TextView) findViewById(R.id.suggest_num);
        suggestPhone = (EditText) findViewById(R.id.suggest_phone);
        textInputLayout = (TextInputLayout) findViewById(R.id.suggest_input_layout);
        suggestContent = textInputLayout.getEditText();
        sendBtn = (Button) findViewById(R.id.send_btn);

    }
}
