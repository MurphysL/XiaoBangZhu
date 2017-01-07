package com.xiaobangzhu.xiaobangzhu.UI.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.ImageView;

import com.android.volley.VolleyError;
import com.squareup.picasso.Picasso;
import com.xiaobangzhu.xiaobangzhu.Bean.InitImageResultCode;
import com.xiaobangzhu.xiaobangzhu.Interface.DataChangeListener;
import com.xiaobangzhu.xiaobangzhu.MyApplication;
import com.xiaobangzhu.xiaobangzhu.NetworkService.NetRequestManager;
import com.xiaobangzhu.xiaobangzhu.R;

import java.text.SimpleDateFormat;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by WQC on 2016/10/20.
 */

public class AdActivity extends BaseActivity {
    private static final String TAG = "AdActivity";
    private ImageView bgImageView;


    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {

            if (msg.what == 0x11) {
                String url = (String) msg.obj;
                if (url != null) {
                    Picasso.with(AdActivity.this).load(url).into(bgImageView);
                }
                Log.i(TAG, "handleMessage: " + url);
            }
            super.handleMessage(msg);
        }

    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch_logged);
        bgImageView = (ImageView) findViewById(R.id.activity_launch_bg);
        NetRequestManager.getInstance().getInitImage();
        NetRequestManager.getInstance().setInitImageCodeListener(new DataChangeListener<InitImageResultCode>() {
            @Override
            public void onSuccessful(InitImageResultCode data) {
                if (data != null && data.getStatus() == 0) {
                    String url = data.getData().getUrl();
                    if (url != null && url != "") {
                        Log.i(TAG, "onSuccessful: "+url);
                        handler.obtainMessage(0x11, url).sendToTarget();
                    }
                }else{
                    Log.i(TAG, "onSuccessful: is null");
                }
            }

            @Override
            public void onError(VolleyError volleyError) {
                //MyApplication.showDialog(AdActivity.this,"请检查网络状况"); 不明原因闪退
                Log.i(TAG, "onError: " + System.currentTimeMillis());
            }

            @Override
            public void onResponseNull() {
                MyApplication.showDialog(AdActivity.this,"请检查网络状况");
                Log.i(TAG, "onError: " + System.currentTimeMillis());
            }
        });
        enterApp();//Y

    }

    private void enterApp() {

        long keyLive = MyApplication.getInstance().getKeyLiveTime();
        long loginTime = MyApplication.getInstance().getLoginTime();
        long currTime = System.currentTimeMillis();

        SimpleDateFormat sdf= new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        String s2 = sdf.format(keyLive);
        String s3 = sdf.format(loginTime);
        String s4 = sdf.format(currTime);


        /**
         * Y
         */
        Log.i(TAG, "onCreate: "  + "currTime" + s4 +" " +  currTime +" \n" +
                "login"+ s3+" "+ loginTime +"\n "+
                        "keylive"+ s2+"(" + keyLive+ ")\n" + (currTime-loginTime));
        if (MyApplication.getInstance().isUserLogin() && (currTime-loginTime< (keyLive*1000))) {
            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    Intent intent = new Intent(AdActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            },5000);
        }else{
            Intent intent = new Intent(AdActivity.this, LaunchActivity.class);
            startActivity(intent);
            finish();
        }
    }


}
