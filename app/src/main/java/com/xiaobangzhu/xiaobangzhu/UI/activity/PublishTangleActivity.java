package com.xiaobangzhu.xiaobangzhu.UI.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.xiaobangzhu.xiaobangzhu.Bean.AddTangleResultCode;
import com.xiaobangzhu.xiaobangzhu.Interface.DataChangeListener;
import com.xiaobangzhu.xiaobangzhu.MyApplication;
import com.xiaobangzhu.xiaobangzhu.NetworkService.NetRequestManager;
import com.xiaobangzhu.xiaobangzhu.R;
import com.xiaobangzhu.xiaobangzhu.Utils.VerifyUtils;

/**
 * Created by WQC on 2016/8/11.
 */
public class PublishTangleActivity extends BaseActivity implements View.OnClickListener, DataChangeListener<AddTangleResultCode> {
    TextView cancleBtn;
    TextView publishBtn;

    EditText descEditText;
    EditText firstChooseEt, secondChooseEt;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publish_tangle);
        initViews();
        initEvevt();
    }

    private void initEvevt() {
        cancleBtn.setOnClickListener(this);
        publishBtn.setOnClickListener(this);
        NetRequestManager.getInstance().setAddTangleResultCodeListener(this);
    }

    void initViews() {
        cancleBtn = (TextView) findViewById(R.id.publish_header_cancle);
        publishBtn = (TextView) findViewById(R.id.publish_header_publish);
        descEditText = (EditText) findViewById(R.id.tangle_desc_text);
        firstChooseEt = (EditText) findViewById(R.id.tangle_first_choose);
        secondChooseEt = (EditText) findViewById(R.id.tangle_second_choose);
    }

    @Override
    public void onClick(View view) {
        int viewId = view.getId();
        switch (viewId) {
            case R.id.publish_header_cancle:
                finish();
                break;
            case R.id.publish_header_publish:
                String content = descEditText.getText().toString().trim();
                String choiseA = firstChooseEt.getText().toString().trim();
                String choiseB = secondChooseEt.getText().toString().trim();

                if (VerifyUtils.isNull(content) || VerifyUtils.isNull(choiseA) || VerifyUtils.isNull(choiseB)) {
                    MyApplication.showToastShort("请检查输入是否完整");
                }else{
                      MyApplication.showProgress(PublishTangleActivity.this,"发布中","请稍后");
                    NetRequestManager.getInstance().addTangleCancer(MyApplication.getInstance().getUserToken(),MyApplication.getInstance().getUserCollegeId(), content, choiseA, choiseB);
                }
                break;
        }

    }


    @Override
    public void onSuccessful(AddTangleResultCode data) {
        MyApplication.dismissProgress();
        if (data != null) {
            if (data.getStatus() == 0) {
                MyApplication.showToastShort("发布成功");
            }else{
                MyApplication.showToastShort("发布失败");
            }
        }
        finish();
    }

    @Override
    public void onError(VolleyError volleyError) {
        MyApplication.dismissProgress();
        MyApplication.showToastShort(volleyError.getMessage());
    }

    @Override
    public void onResponseNull() {
        MyApplication.dismissProgress();
        MyApplication.showToastShort("发布失败");
    }
}
