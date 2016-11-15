package com.xiaobangzhu.xiaobangzhu.NetworkService;

import android.util.Log;

import com.xiaobangzhu.xiaobangzhu.Bean.LoginResultCode;
import com.xiaobangzhu.xiaobangzhu.Interface.UpdateDownloadListener;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * UpdateManager
 *
 * @author: MurphySL
 * @time: 2016/11/14 12:48
 */


public class UpdateManager {
    private static final String TAG = "UpdateManager";

    private static UpdateManager updateManager;
    private ThreadPoolExecutor threadPoolExecutor;
    private UpdateDownloadRequest request;

    private UpdateManager(){
        threadPoolExecutor = (ThreadPoolExecutor) Executors.newCachedThreadPool();
    }

    static {
        updateManager = new UpdateManager();
    }

    public static UpdateManager getInstance(){
        return updateManager;
    }

    public void startDownloads(String url , String localPath , UpdateDownloadListener updateDownloadListener){
        if(request != null){
            return;
        }

        checkLocalFilePath(localPath);

        //下载
        request = new UpdateDownloadRequest(url , localPath , updateDownloadListener);
        Future<?> future = threadPoolExecutor.submit(request);
    }

    /**
     * 检查文件路径是否存在
     * @param localPath
     */
    private void checkLocalFilePath(String localPath) {
        File dir = new File(localPath.substring(0 , localPath.lastIndexOf("/") + 1));

        if(!dir.exists()){
            dir.mkdir();
        }

        File file = new File(localPath);

        if(!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
