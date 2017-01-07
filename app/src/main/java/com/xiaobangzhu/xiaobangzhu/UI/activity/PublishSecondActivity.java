package com.xiaobangzhu.xiaobangzhu.UI.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.xiaobangzhu.xiaobangzhu.AliPay.PayOrderActivity;
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
 * Created by WQC on 2016/8/19.
 */
public class PublishSecondActivity  extends BaseActivity implements View.OnClickListener, RadioGroup.OnCheckedChangeListener {

    private static final String TAG = "PublishSecondActivity";
    private static int REQUEST_CODE_GALLERY = 0x11;

    TextView publishBtn;
    TextView cancleBtn;
    GFImageView showImageView;
    EditText descEditText;
    RadioGroup useTimeRadio;
    EditText priceEditText;
    EditText tagEditText;
    EditText addressEditText;
    TextView publishCustomTextView;

    //要发布的数据
    String desc = null;
    String showImageDir = null;
    String useTime = null;
    String tag = null;
    String address = null;
    int price;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publish_second);
        initViews();
        initEvents();
    }

    private void initViews() {
        cancleBtn = (TextView) findViewById(R.id.publish_header_cancle);
        publishBtn = (TextView) findViewById(R.id.publish_header_publish);
        descEditText = (EditText) findViewById(R.id.second_desc_text);
        showImageView = (GFImageView) findViewById(R.id.publish_first_img);
        useTimeRadio = (RadioGroup) findViewById(R.id.radio_use_time);
        priceEditText = (EditText) findViewById(R.id.second_price_text);
        tagEditText = (EditText) findViewById(R.id.second_tag_text);
        addressEditText = (EditText) findViewById(R.id.second_address_text);
        publishCustomTextView = (TextView) findViewById(R.id.publish_second_custom);

    }


    private void initEvents() {
        cancleBtn.setOnClickListener(this);
        publishBtn.setOnClickListener(this);
        showImageView.setOnClickListener(this);
        useTimeRadio.setOnCheckedChangeListener(this);
        QiniyunManager.setUploadCallback(new UploadCallback() {
            @Override
            public void complete(String imageUrl) {
                NetRequestManager.getInstance().addSecondTask(MyApplication.getInstance().getUserToken(), MyApplication.getInstance().getUserCollegeId(), price, tag, desc, imageUrl, null, null);
            }
        });
        NetRequestManager.getInstance().setAddSecondResultCodeChangeListener(new DataChangeListener<BaseResultCode>() {
            @Override
            public void onSuccessful(BaseResultCode data) {
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
                MyApplication.showToastShort(volleyError.getMessage());
            }

            @Override
            public void onResponseNull() {
                MyApplication.dismissProgress();
                MyApplication.showToastShort("发布失败");
            }
        });
    }

    @Override
    public void onClick(View v) {
        int viewId = v.getId();
        switch (viewId) {
            case R.id.publish_header_publish:
                address = addressEditText.getText().toString().trim();
                desc = descEditText.getText().toString().trim();
                String priceStr = priceEditText.getText().toString().trim();
                tag = tagEditText.getText().toString().trim();

                if (VerifyUtils.isNull(priceStr)||VerifyUtils.isNull(showImageDir)|| VerifyUtils.isNull(address)||VerifyUtils.isNull(desc)|| VerifyUtils.isNull(tag)) {
                    MyApplication.showToastShort("请检查输入是否完整");
                }else{
                    price = Integer.parseInt(priceStr);
                    Log.i(TAG, "onClick: "+ showImageDir+"address:"+address);
                    MyApplication.showProgress(PublishSecondActivity.this,"发布中","请稍后");
                    QiniyunManager.uploadPicture(PublishSecondActivity.this, showImageDir);

                    //Y
                    Intent intent = new Intent(PublishSecondActivity.this , PayOrderActivity.class);
                    Bundle b = new Bundle();
                    b.putString("subject" , "secondpublish");
                    b.putString("body" , desc);
                    b.putInt("total_fee" , price);
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
                                showImageDir = photoInfo.getPhotoPath();
                                PicassoImageLoader picassoImageLoader = new PicassoImageLoader();
                                picassoImageLoader.displayImage(PublishSecondActivity.this,showImageDir,showImageView, getResources().getDrawable(R.drawable.activity_luancher_bg),70,70);
                            }
                        }
                    }
                    @Override
                    public void onHanlderFailure(int requestCode, String errorMsg) {
                        MyApplication.showToastShort(errorMsg);
                    }
                });
                break;
        }
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        if (group.getId() == R.id.radio_use_time) {
            switch (checkedId) {
                case R.id.full_new:
                    useTime = "全新";
                    break;
                case R.id.one_month:
                    useTime = "一个月";
                    break;
                case R.id.one_year:
                    useTime = "一年";
                    break;
                case R.id.use_custom:
                    //useTime = PickUtils.pickString(PublishSecondActivity.this);
                    publishCustomTextView.setText(useTime);
                    break;
            }
        }

    }
}
