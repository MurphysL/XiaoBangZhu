package com.xiaobangzhu.xiaobangzhu.UI.activity;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xiaobangzhu.xiaobangzhu.MyApplication;
import com.xiaobangzhu.xiaobangzhu.R;
import com.xiaobangzhu.xiaobangzhu.Utils.DataCleanManager;

import static android.content.Intent.FLAG_ACTIVITY_NO_HISTORY;

/**
 * Created by WQC on 2016/9/17.
 */
public class SettingActivity  extends BaseActivity implements View.OnClickListener{
    private static final String TAG = "SettingActivity";
    private ImageView backBtn;
    private LinearLayout informBtn;
    private LinearLayout cleanBtn;
    private LinearLayout suggestBtn;
    private LinearLayout aboutBtn;
    private Button logoutBtn;
    private TextView cleanText;
    private String cacheSize;
    private TextView version;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        cacheSize = getFileSize();
        initViews();
        initEvents();
    }

    private void initViews() {
        backBtn = (ImageView) findViewById(R.id.setting_back_btn);
        informBtn = (LinearLayout) findViewById(R.id.setting_inform_btn);
        cleanBtn = (LinearLayout) findViewById(R.id.setting_clean_btn);
        suggestBtn = (LinearLayout) findViewById(R.id.setting_suggest_btn);
        aboutBtn = (LinearLayout) findViewById(R.id.setting_about_btn);
        logoutBtn = (Button) findViewById(R.id.setting_logout_btn);
        cleanText = (TextView) findViewById(R.id.setting_clean_text);
        version = (TextView) findViewById(R.id.version);
        Log.i(TAG,cacheSize+"");

        if (cacheSize != null) {
            cleanText.setText(cacheSize);
        }

        try {
            PackageManager pm = getPackageManager();
            PackageInfo pi = pm.getPackageInfo("com.xiaobangzhu.xiaobangzhu", 0);
            String name = pi.versionName;
            version.setText(name);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

    }

    private void initEvents() {
        backBtn.setOnClickListener(this);
        informBtn.setOnClickListener(this);
        cleanBtn.setOnClickListener(this);
        suggestBtn.setOnClickListener(this);
        aboutBtn.setOnClickListener(this);
        logoutBtn.setOnClickListener(this);
    }

    /**
     * 获取缓存文件大小
     */
    private String getFileSize() {
        try {
            cacheSize = DataCleanManager.getCacheSize(this.getCacheDir());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cacheSize;
    }

    @Override
    public void onClick(View view) {
        int viewId = view.getId();
        switch (viewId){
            case R.id.setting_back_btn:
                finish();
                break;
            case R.id.setting_inform_btn:
                Intent intent = new Intent();
                if (MyApplication.getInstance().isUserLogin()) {
                    intent.setClass(SettingActivity.this, UpdateUserActivity.class);
                }else{
                    intent.setClass(SettingActivity.this, LaunchActivity.class);
                }
                startActivity(intent);
                break;
            case R.id.setting_clean_btn:
                DataCleanManager.cleanInternalCache(SettingActivity.this);
                MyApplication.showToastShort("缓存清除完成");
                cleanText.setText("0.0MB");
                break;
            case R.id.setting_suggest_btn:
                startActivity(new Intent(SettingActivity.this,SuggestActivity.class));
                break;
            case R.id.setting_about_btn:
                startActivity(new Intent(SettingActivity.this,AboutUsActivity.class));
                break;
            case R.id.setting_logout_btn:
                DataCleanManager.cleanSharedPreference(SettingActivity.this);
                MyApplication.getInstance().setUserLogin(false);
                startActivity(new Intent(SettingActivity.this,LaunchActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|FLAG_ACTIVITY_NO_HISTORY));
                finish();
                break;
        }

    }
}
