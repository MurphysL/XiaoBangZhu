package com.xiaobangzhu.xiaobangzhu.NetworkService;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.xiaobangzhu.xiaobangzhu.Interface.UpdateDownloadListener;
import com.xiaobangzhu.xiaobangzhu.R;

import java.io.File;

/**
 * UpdateService
 *
 * @author: MurphySL
 * @time: 2016/11/14 14:01
 */


public class UpdateService extends Service {
    private static final String TAG = "UpdateService";

    private String apkURL;
    private String filePath;
    private Notification notification;
    private NotificationManager notificationManager;

    @Override
    public void onCreate() {
        super.onCreate();

        notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        filePath = Environment.getExternalStorageDirectory() + "/xbz/XiaoBangZhu.apk";
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if(intent == null){
            notifyUser("下载失败" , "下载失败" , 0);
            stopSelf();
        }

        apkURL = intent.getStringExtra("apkUrl");

        notifyUser("下载开始" ,"下载开始" , 0);
        startDownload();
        return super.onStartCommand(intent, flags, startId);
    }

    private void startDownload() {
        Log.i(TAG, "startDownload: " + "startDowmload");
        UpdateManager.getInstance().startDownloads(apkURL, filePath,
                new UpdateDownloadListener() {
                    @Override
                    public void onStarted() {
                        Log.i(TAG, "startDownload: " + "start");
                    }

                    @Override
                    public void onProgressChanged(int progress, String downloadUrl) {
                        Log.i(TAG, "startDownload: " + "onProgressChanged");
                        notifyUser("下载中" , "下载中" , progress);
                    }

                    @Override
                    public void onFinished(int completeSize, String downloadUrl) {
                        Log.i(TAG, "startDownload: " + "onFinished" +completeSize);
                        notifyUser("下载成功" , "下载成功" , 100);
                        stopSelf();
                    }

                    @Override
                    public void onFailure() {
                        Log.i(TAG, "startDownload: " + "onFailure");
                        notifyUser("下载失败" , "下载失败" , 0);
                        stopSelf();
                    }
                });
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    /**
     * 下载进度
     * @param result
     * @param reason
     * @param progress
     */
    private void notifyUser(String result , String reason , int progress){
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        builder.setSmallIcon(R.mipmap.ic_launcher)
                .setLargeIcon(BitmapFactory.decodeResource(getResources() , R.mipmap.ic_launcher));

        if(progress > 0 && progress < 100){
            Log.i(TAG, "notifyUser: " + progress);
            builder.setProgress(100 , progress , false);
        }else{
            Log.i(TAG, "notifyUser1: " + progress);
            builder.setProgress(0 , 0 , false);
        }

        builder.setAutoCancel(true);
        builder.setWhen(System.currentTimeMillis());
        builder.setTicker(result);
        Log.i(TAG, "notifyUser: " + progress);
        builder.setContentIntent(progress >= 100 ? getContentIntent()
        :PendingIntent.getActivity(this , 0 , new Intent() , PendingIntent.FLAG_UPDATE_CURRENT));
        builder.setContentTitle(progress>= 100? "点击安装" :"下载中");
        notification = builder.build();
        notificationManager.notify(0 , notification);
    }

    private PendingIntent getContentIntent() {
        Log.i(TAG, "getContentIntent: " + "pend");
        File apkFile = new File(filePath);
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setDataAndType(Uri.parse("file://" + apkFile.getAbsolutePath()),
                "application/vnd.android.package-archive");
        PendingIntent pendingIntent = PendingIntent.getActivity(this , 0 , intent , PendingIntent.FLAG_UPDATE_CURRENT);
        return pendingIntent;
    }
}
