package com.xiaobangzhu.xiaobangzhu.NetworkService;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import com.xiaobangzhu.xiaobangzhu.Interface.UpdateDownloadListener;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DecimalFormat;

/**
 * UpdateDownloadRequest
 *
 * @author: MurphySL
 * @time: 2016/11/14 0:09
 */


public class UpdateDownloadRequest implements Runnable {

    private String downloadUrl;
    private String localFilePath;
    private UpdateDownloadListener updateDownloadListener;
    private boolean isDownloading = false;
    private long currentLength;
    private DownloadResponseHandler downloadResponseHandler;

    public UpdateDownloadRequest(String downloadUrl , String localFilePath , UpdateDownloadListener updateDownloadListener){
        this.downloadUrl = downloadUrl;
        this.localFilePath = localFilePath;
        this.updateDownloadListener = updateDownloadListener;
        this.isDownloading = true;
        this.downloadResponseHandler = new DownloadResponseHandler();
    }

    /**
     * 建立连接
     * @throws IOException
     * @throws InterruptedException
     */
    private void makeRequest() throws IOException , InterruptedException{
        if(!Thread.currentThread().isInterrupted()){
            try {
                URL url = new URL(downloadUrl);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                connection.setConnectTimeout(5000);
                connection.setRequestProperty("Connection" , "Keep-Alive");
                connection.connect();
                currentLength = connection.getContentLength();
                if(!Thread.currentThread().isInterrupted()){
                    //文件下载
                    downloadResponseHandler.sendResponseMessage(connection.getInputStream());
                }
            }catch (IOException e){
                throw e;
            }

        }
    }

    @Override
    public void run() {
        try {
            makeRequest();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 格式化数字
     * @param value
     * @return
     */
    private String getTwoPointFloatStr(float value){
        DecimalFormat fnum = new DecimalFormat("0.00");
        return fnum.format(value);
    }

    public enum FailureCode{
        UnknownHost , Socket , SocketTimeout , ConnectTimeOut , IO , HttpResponse , JSON , Interrupted
    }

    /**
     * 下载文件 发送消息和回调接口
     */
    public class DownloadResponseHandler{
        protected static final int SUCCESS_MESSAGE = 0;
        protected static final int FAILURE_MESSAGE = 1;
        protected static final int START_MESSAGE = 2;
        protected static final int FINISH_MESSAGE = 3;
        protected static final int NETWORK_OFF = 4;
        protected static final int PROGRESS_CHANGED = 5;

        private int mCompleteSize = 0;
        private int progress = 0;

        private Handler handler;

        public DownloadResponseHandler(){
            handler = new Handler(Looper.getMainLooper()){
                @Override
                public void handleMessage(Message msg) {
                    handleSelfMessage(msg);
                }
            };
        }

        /**
         * 发送不同消息对象
         */
        protected void sendFinishMessage() {
            sendMessage(obtainMessage(FINISH_MESSAGE, null) );
        }

        private void sendProgressChangedMessage(int progress){
            sendMessage(obtainMessage(PROGRESS_CHANGED , new Object[]{progress}));
        }

        protected void sendFailureMessage(FailureCode failureCode){
            sendMessage(obtainMessage(FAILURE_MESSAGE , new Object[]{failureCode}));
        }

        protected void sendMessage(Message msg){
            if(handler != null){
                handler.sendMessage(msg);
            }else {
                handleSelfMessage(msg);
            }
        }

        /**
         * 获取消息对象
         * @param responseMessage
         * @param response
         * @return
         */
        protected Message obtainMessage(int responseMessage , Object response){
            Message msg = null;
            if(handler != null){
                msg = handler.obtainMessage(responseMessage , response);
            }else{
                msg = Message.obtain();
                msg.what = responseMessage;
                msg.obj = response;
            }
            return msg;
        }

        protected void handleSelfMessage(Message msg){
            Object[] response;
            switch (msg.what){
                case FAILURE_MESSAGE:
                    response = (Object[]) msg.obj;
                    handleFailureMessage((FailureCode) response[0]);
                    break;
                case PROGRESS_CHANGED:
                    response = (Object[]) msg.obj;
                    handleProgressChangedMessage(((Integer) response[0]).intValue());
                    break;
                case FINISH_MESSAGE:
                    onFinish();
                    break;

            }
        }

        protected void handleProgressChangedMessage(int progress){

        }

        protected void handleFailureMessage(FailureCode failureCode){
            onFailure(failureCode);
        }

        //外部接口回调
        public void onFinish(){
            updateDownloadListener.onFinished(mCompleteSize , "");
        }

        public void onFailure(FailureCode failureCode){
            updateDownloadListener.onFailure();
        }

        //文件 下载方法 发送事件
        void sendResponseMessage(InputStream is){
            RandomAccessFile randomAccessFile = null;
            mCompleteSize = 0;

            try {
                byte[] buffer = new byte[1024];
                int length = -1;
                int limit = 0;

                randomAccessFile = new RandomAccessFile(localFilePath , "rwd");

                while ((length = is.read(buffer)) != -1 ){
                    if (isDownloading) {
                        randomAccessFile.write(buffer , 0 , length);
                        mCompleteSize += length;
                        if(mCompleteSize < currentLength){
                            progress = (int) Float.parseFloat(getTwoPointFloatStr(mCompleteSize/currentLength));
                            if(limit / 30 == 0 || progress <= 100){
                                //限制notification更新频率
                                sendProgressChangedMessage(progress);
                            }

                            limit ++;
                        }
                    }
                }

                sendFinishMessage();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
                sendFailureMessage(FailureCode.IO);
            } finally {

                try {
                    if(is != null){
                        is.close();
                    }
                    if(randomAccessFile != null){
                        randomAccessFile.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    sendFailureMessage(FailureCode.IO);
                }

            }
        }

    }
}
