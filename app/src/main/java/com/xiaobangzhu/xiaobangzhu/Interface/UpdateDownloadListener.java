package com.xiaobangzhu.xiaobangzhu.Interface;

/**
 * UpdateDownloadListener
 *
 * @author: MurphySL
 * @time: 2016/11/13 23:55
 */


public interface UpdateDownloadListener {

    /**
     * 下载请求开始回调
     */
    public void onStarted();

    /**
     * 进度更新回调
     * @param progress
     * @param downloadUrl
     */
    public void onProgressChanged(int progress , String downloadUrl);

    /**
     * 下载完成回调
     * @param completeSize
     * @param downloadUrl
     */
    public void onFinished(int completeSize , String downloadUrl);

    /**
     * 下载失败回调
     */
    public void onFailure();
}
