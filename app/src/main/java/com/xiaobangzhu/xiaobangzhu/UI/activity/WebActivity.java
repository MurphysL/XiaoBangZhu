package com.xiaobangzhu.xiaobangzhu.UI.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.tencent.smtt.sdk.WebSettings;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;
import com.xiaobangzhu.xiaobangzhu.R;

/**
 * Created by WQC on 2016/10/12.
 */

public class WebActivity extends AppCompatActivity {
    private static final String TAG = "WebActivity";
    private ImageView webBack;
    private TextView webTitle;
    private WebView webView;
    private String title;
    private String url;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);

        Intent intent = getIntent();
        if (intent != null) {
            Bundle bundle = intent.getExtras();
            title = bundle.getString("title");
            url = bundle.getString("url");
        }
        Log.i(TAG, "onCreate: " + title);
        Log.i(TAG, "onCreate: " + url);
        initViews();
        initEvents();

    }

    private void initEvents() {
        webBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (webView.canGoBack()) {
                    webView.goBack();
                }else{
                    finish();
                }
            }
        });
    }

    private void initViews() {
        webBack = (ImageView) findViewById(R.id.web_back);
        webTitle = (TextView) findViewById(R.id.web_title);
        webView = (WebView) findViewById(R.id.web_webview);
        webTitle.setText(title);
        initWebView();
        webView.loadUrl(url);
    }

    private void initWebView() {
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setDatabaseEnabled(true);
        settings.setAppCacheEnabled(true);
        settings.setAppCachePath(this.getCacheDir().getPath());
        settings.setCacheMode(WebSettings.LOAD_DEFAULT);
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
        settings.setUseWideViewPort(true);
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView webView, String s) {
                webView.loadUrl(s);
                return true;
            }

            @Override
            public void onPageFinished(WebView webView, String s) {
                super.onPageFinished(webView, s);
            }

            @Override
            public void onPageStarted(WebView webView, String s, Bitmap bitmap) {
                super.onPageStarted(webView, s, bitmap);
            }

            @Override
            public void onLoadResource(WebView webView, String s) {
                super.onLoadResource(webView, s);
            }
        });

    }


}
