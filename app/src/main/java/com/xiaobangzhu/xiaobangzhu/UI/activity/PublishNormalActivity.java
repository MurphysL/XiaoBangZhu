package com.xiaobangzhu.xiaobangzhu.UI.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.alipay.sdk.app.PayTask;
import com.android.volley.VolleyError;
import com.xiaobangzhu.xiaobangzhu.AliPay.PayDemoActivity;
import com.xiaobangzhu.xiaobangzhu.AliPay.PayOrderActivity;
import com.xiaobangzhu.xiaobangzhu.Bean.PublishResultCode;
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
 * Created by WQC on 2016/8/21.
 */
public class PublishNormalActivity extends BaseActivity implements View.OnClickListener,RadioGroup.OnCheckedChangeListener{
    private static final int REQUEST_CODE_GALLERY = 0x11;
    private static final String TAG = "PublishNormalActivity";
    TextView cancleBtn;
    TextView publishBtn;
    EditText tangleDescEdit;
    GFImageView showImageView;
    RadioGroup useTimeRadio,personLimitRadio;
    EditText addressEdit;
    TextView deathLineBtn;
    RadioButton radioButton;


    //所有的数据
    int collegeId;
    String title = null;
    String desc = null;
    String address = null;
    int tip = 0;
    String specify = null;
    String endtime = null;
    private String showImgDir = null;
    String strTip;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publish_normal);
        initViews();
        initEvents();
    }


    private void initViews() {
        cancleBtn = (TextView) findViewById(R.id.publish_header_cancle);
        publishBtn = (TextView) findViewById(R.id.publish_header_publish);
        tangleDescEdit = (EditText) findViewById(R.id.normal_desc_text);
        showImageView = (GFImageView) findViewById(R.id.publish_first_img);
        useTimeRadio = (RadioGroup) findViewById(R.id.radio_pay);
        personLimitRadio = (RadioGroup) findViewById(R.id.radio_limt_person);
        addressEdit = (EditText) findViewById(R.id.publish_normal_address);
        deathLineBtn = (TextView) findViewById(R.id.publish_normal_death_line);
        radioButton = (RadioButton) findViewById(R.id.radio_btn_one);
    }


    private void initEvents() {
        deathLineBtn.setOnClickListener(this);
        personLimitRadio.setOnCheckedChangeListener(this);
        useTimeRadio.setOnCheckedChangeListener(this);
        publishBtn.setOnClickListener(this);
        cancleBtn.setOnClickListener(this);
        showImageView.setOnClickListener(this);
        QiniyunManager.setUploadCallback(new UploadCallback() {
            @Override
            public void complete(String imageUrl) {
                NetRequestManager.getInstance().addNormalTask(MyApplication.getInstance().getUserToken(),MyApplication.getInstance().getUserCollegeId(),title,desc,imageUrl,address,tip,specify,endtime);
            }
        });

        NetRequestManager.getInstance().setAddNormalResultCodeChangeListener(new DataChangeListener<PublishResultCode>() {
            @Override
            public void onSuccessful(PublishResultCode data) {
                MyApplication.dismissProgress();
                if (data != null) {
                    if (data.getStatus() == 0) {
                        MyApplication.showToastShort("发布成功");
                        finish();
                    }
                }
            }
            @Override
            public void onError(VolleyError volleyError) {
                MyApplication.dismissProgress();
                MyApplication.showToastShort("发布失败");
            }

            @Override
            public void onResponseNull() {
                MyApplication.dismissProgress();
                MyApplication.showToastShort("发布失败");
            }
        });
    }

    @Override
    public void onClick(View view) {
        int viewId = view.getId();
        switch (viewId){
            case R.id.publish_header_publish:
                endtime = deathLineBtn.getText().toString().trim();
                Log.i(TAG, "onClick: "+endtime);
                address = addressEdit.getText().toString().trim();
                desc = tangleDescEdit.getText().toString().trim();
                endtime = deathLineBtn.getText().toString().trim();
                if ( VerifyUtils.isNull(address)|| VerifyUtils.isNull(desc) || VerifyUtils.isNull(showImgDir)
                        || VerifyUtils.isNull(strTip) ||  VerifyUtils.isNull(endtime) || VerifyUtils.isNull(specify)) {
                    Log.i(TAG, "onClick: "+address+desc+showImgDir+strTip+endtime+specify);
                    MyApplication.showToastShort("请检查输入是否完整");
                }else{
                    tip = Integer.valueOf(strTip);
                    Log.i(TAG, "onClick: "+ showImgDir+"address:"+address);
                    MyApplication.showProgress(PublishNormalActivity.this,"发布中","请稍等");
                    QiniyunManager.uploadPicture(PublishNormalActivity.this,showImgDir);

                    //Y
                    Intent intent = new Intent(PublishNormalActivity.this , PayOrderActivity.class);
                    Bundle b = new Bundle();
                    b.putString("subject" , "normalpublish");
                    b.putString("body" , desc);
                    b.putInt("total_fee" , tip);
                    intent.putExtras(b);
                    startActivity(intent);
                }
                break;
            case R.id.publish_header_cancle:
                finish();
                break;
            case R.id.publish_first_img:
                GalleryFinal.openGallerySingle(REQUEST_CODE_GALLERY, new GalleryFinal.OnHanlderResultCallback() {
                    @Override
                    public void onHanlderSuccess(int reqeustCode, List<PhotoInfo> resultList) {
                        if (reqeustCode == REQUEST_CODE_GALLERY) {
                            for (PhotoInfo photoInfo : resultList){
                                showImgDir = photoInfo.getPhotoPath();
                                PicassoImageLoader picassoImageLoader = new PicassoImageLoader();
                                picassoImageLoader.displayImage(PublishNormalActivity.this,showImgDir,showImageView, getResources().getDrawable(R.drawable.activity_luancher_bg),70,70);
                            }
                        }
                    }

                    @Override
                    public void onHanlderFailure(int requestCode, String errorMsg) {
                        MyApplication.showToastShort(errorMsg);
                    }
                });
                break;
            case R.id.publish_normal_death_line:
                PickUtils.pickTime(PublishNormalActivity.this,deathLineBtn);
                break;

        }

    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
        int radioGroupId = radioGroup.getId();
        if (radioGroupId == R.id.radio_limt_person) {
            switch (checkedId) {
                case R.id.radio_btn_only_boy:
                    specify = "只限男生";
                    break;
                case R.id.radio_btn_only_girl:
                    specify = "只限女生";
                    break;
                case R.id.radio_btn_no_limit:
                    specify = "不限制";
                    break;
                default:
                    break;
            }
        } else if (radioGroupId == R.id.radio_pay) {
            switch (checkedId) {
                case R.id.radio_btn_one:
                    strTip = 1+"";
                    break;
                case R.id.radio_btn_five:
                    strTip = 5+"";
                    break;
                case R.id.radio__btn_ten:
                    strTip = 10+"";
                    break;
                case R.id.radio__btn_custom:
                    PickUtils.pickString(PublishNormalActivity.this , handler);
                    break;
            }
        }else {

        }
    }

    //Y
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(msg.what == 1){
                Bundle bundle = msg.getData();
                if(!"".equals(bundle.getString("str")) && null != bundle.getString("str")){
                    strTip = bundle.getString("str");
                    Log.i(TAG, "strTip is" + strTip);
                }

            }
        }
    };

}
