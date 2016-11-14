package com.xiaobangzhu.xiaobangzhu.UI.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tencent.smtt.sdk.WebSettings;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;
import com.xiaobangzhu.xiaobangzhu.R;

/**
 * Created by WQC on 2016/10/18.
 */

public class RewardListFragment extends Fragment {

    private static final String TAG = "RewardListFragment";
    View mRootView;
    WebView mWebView;
    String url = null;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.reward_list_fragment, container, false);
        url = getArguments().getString("url");
        initWebView(mRootView);
        Log.i(TAG, "onCreateView: " + url);

        if (url != null) {
            mWebView.loadUrl(url);
        }


        return mRootView;
    }

    /**
     * 重新加载页面
     */
    public void reLoadWeb(){
        mWebView.reload();
    }


    /**
     * 初始化webView
     */
    private void initWebView(View rootView) {
        mWebView = (WebView) rootView.findViewById(R.id.reward_list_fragment_webview);
        WebSettings settings = mWebView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setAppCacheEnabled(true);
        settings.setAppCachePath(getContext().getCacheDir().getPath());
        settings.setDatabaseEnabled(true);
        settings.setCacheMode(WebSettings.LOAD_NORMAL);
        mWebView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView webView, String s) {
                webView.loadUrl(s);
                return true;
            }
        });
    }

}
