package com.xiaobangzhu.xiaobangzhu.UI.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.tencent.smtt.sdk.WebSettings;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;
import com.xiaobangzhu.xiaobangzhu.MyApplication;
import com.xiaobangzhu.xiaobangzhu.NetworkService.HtmlManager;
import com.xiaobangzhu.xiaobangzhu.R;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by WQC on 2016/7/17.
 */
public class PersonCenterActivity extends AppCompatActivity {

    private static final String TAG = "PersonCenterActivity";
    CircleImageView mPhotoImg;
    ImageView mBackGroudImg;
    TextView mFansTv;
    TextView mGoodNumTv;
    TextView mViewNumTv;
    TextView mNickNameTv;
    TextView msloganTv;
    String bg;
    String header;
    WebView webView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_center);
        initViews();
        bg = MyApplication.getInstance().getUserBackGround();
        header = MyApplication.getInstance().getUserHeader();
        initEvents();
        initWebView();
        initUserInform();
    }

    /**
     * 初始化控件
     */
    private void initViews() {
        mPhotoImg = (CircleImageView) findViewById(R.id.person_center_head_portrait);
        mFansTv = (TextView) findViewById(R.id.person_center_fans_nummber);
        mGoodNumTv = (TextView) findViewById(R.id.person_center_good_nummber);
        mNickNameTv = (TextView) findViewById(R.id.person_center_nick_name);
        mViewNumTv = (TextView) findViewById(R.id.person_center_view_nummber);
        msloganTv = (TextView) findViewById(R.id.person_center_slogan);
        mBackGroudImg = (ImageView) findViewById(R.id.person_center_bg);
        webView = (WebView) findViewById(R.id.person_center_webview);

        msloganTv.setText(MyApplication.getInstance().getUserSlogan() + "");
        mFansTv.setText(MyApplication.getInstance().getUserFansNum() + "");
        mViewNumTv.setText(MyApplication.getInstance().getAttentionNum() + "");

        webView.loadUrl(HtmlManager.getmInstance().getUrlForMyOrder(MyApplication.getInstance().getUserToken()));
    }


    private void initEvents() {

    }

    //初始化webview
    private void initWebView() {
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setAppCacheEnabled(true);
        settings.setAppCachePath(this.getCacheDir().getPath());
        settings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView webView, String s) {
                webView.loadUrl(s);
                return true;
            }

        });
    }

    /**
     * 初始化用户信息
     */
    private void initUserInform() {
        if (MyApplication.getInstance().isUserLogin()) {
            mNickNameTv.setText(MyApplication.getInstance().getUserNickName()+"");
            msloganTv.setText(MyApplication.getInstance().getUserSlogan() + "");
            mGoodNumTv.setText(MyApplication.getInstance().getUserLevel() + "");
            mFansTv.setText(MyApplication.getInstance().getUserFansNum()+"");
            if (header != "") {
                Log.i(TAG, "initUserInform: "+header);
                Picasso.with(PersonCenterActivity.this).load(header).fit().centerCrop().into(mPhotoImg);
            }
            if (bg != "") {
                Picasso.with(PersonCenterActivity.this).load(bg).fit().centerCrop().into(mBackGroudImg);
            }
            webView.loadUrl(HtmlManager.getmInstance().getUrlForMyOrder(MyApplication.getInstance().getUserToken()));
        }

    }

}
