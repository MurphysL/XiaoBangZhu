package com.xiaobangzhu.xiaobangzhu.Utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.android.volley.VolleyError;
import com.qiniu.android.http.ResponseInfo;
import com.qiniu.android.storage.UpCompletionHandler;
import com.qiniu.android.storage.UpProgressHandler;
import com.qiniu.android.storage.UploadManager;
import com.qiniu.android.storage.UploadOptions;
import com.xiaobangzhu.xiaobangzhu.Bean.QiNiuResultCode;
import com.xiaobangzhu.xiaobangzhu.Interface.DataChangeListener;
import com.xiaobangzhu.xiaobangzhu.Interface.MutalUploadCallback;
import com.xiaobangzhu.xiaobangzhu.Interface.UploadCallback;
import com.xiaobangzhu.xiaobangzhu.MyApplication;
import com.xiaobangzhu.xiaobangzhu.NetworkService.NetRequestManager;

import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by WQC on 2016/10/8.
 */

public class QiniyunManager {

    private static final String TAG = "QiniyunManager";

    private static final String DOMAIN_NAME = "http://obfj79jro.bkt.clouddn.com/";

    private static UploadCallback uploadCallback;

    private static MutalUploadCallback mutalUploadCallback;

    public static void setMutalUploadCallback(MutalUploadCallback mutalUploadCallback) {
        QiniyunManager.mutalUploadCallback = mutalUploadCallback;
    }

    public static void setUploadCallback(UploadCallback uploadCallback) {
        QiniyunManager.uploadCallback = uploadCallback;
    }

    /**
     * 请求获取七牛云的token
     */
    public static void requestToken(final Context context){
        NetRequestManager.getInstance().setQiNiuResultCodeChangeListener(new DataChangeListener<QiNiuResultCode>() {
            @Override
            public void onSuccessful(QiNiuResultCode data) {
                if (data.getStatus() == 0) {
                    if (data.getData() != null) {
                        Log.i(TAG, "onSuccessful: token : " + data.getData().getToken());
                        storeToken(context, data.getData().getToken());
                    }
                }
            }
            @Override
            public void onError(VolleyError volleyError) {
                Log.i(TAG, "onSuccessful: token2 : ");
                MyApplication.showDialog(context,"网络请求出错");
            }
            @Override
            public void onResponseNull() {
                MyApplication.showDialog(context,"内容为空");
            }
        });
        NetRequestManager.getInstance().getQiniToken(MyApplication.getInstance().getUserToken());
    }

    private static void storeToken(Context context,String token){
        SharedPreferences preferences = context.getSharedPreferences("XiaoBangZhu", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("QiNiuYunToken", token);
        editor.commit();
    }


    private static String getToken(Context context) {
        SharedPreferences preferences = context.getSharedPreferences("XiaoBangZhu", Context.MODE_PRIVATE);
        String token = preferences.getString("QiNiuYunToken", null);
        if (token == null) {
            requestToken(context);
        }
        Log.i(TAG, "getToken: " + token);
        return token;
    }

    /**
     * 上传单张图片
     * @return
     */
    private static boolean flag = true;
    private static Context mcontext;
    private static String mfilePath;
    public static void uploadPicture(final Context context, String filePath) {
        mcontext = context;
        mfilePath = filePath;

        Log.i("uploadPicture", "uploadPicture: "+filePath+"\n");
        UploadManager uploadManager = MyApplication.getInstance().getUploadManager();
        String key = MD5Util.MD5(filePath);
        String token = getToken(context);
        uploadManager.put(filePath, key, token, new UpCompletionHandler() {
            @Override
            public void complete(String key, ResponseInfo info, JSONObject res) {
                Log.i("qiniu",key + ",\r\n " + info + ",\r\n " + res);
                if(info.isOK() == false && info.error.equals("expired token") && flag){
                    requestToken(context);
                    flag = false;
                    uploadPicture(mcontext , mfilePath);
                }
                String imageUrl = DOMAIN_NAME +key;
                uploadCallback.complete(imageUrl);
            }
        },new UploadOptions(null, null, false,
                new UpProgressHandler(){
                    public void progress(String key, double percent){
                        Log.i("qiniu", key + ": " + percent);
                    }
                }, null));
    }

    /**
     * 上传多张图片
     * @param context
     * @param picturePath
     * @param posterPath
     */
    public static void uploadMutlPicture(Context context, final String picturePath, final String posterPath) {
        Log.i("uploadPicture", "uploadPicture: "+picturePath+"\n");
        Log.i(TAG, "uploadMutlPicture: " + posterPath+"\n");
        final UploadManager uploadManager = MyApplication.getInstance().getUploadManager();
        String key = MD5Util.MD5(picturePath);
        final String token = getToken(context);
        final ArrayList<String> resList = new ArrayList<>();

        uploadManager.put(picturePath, key, token, new UpCompletionHandler() {
            @Override
            public void complete(String key, ResponseInfo info, JSONObject res) {
                Log.i("qiniu",key + ",\r\n " + info + ",\r\n " + res);
                Log.i(TAG, "complete: "+info.isOK());
                String imageUrl1 = DOMAIN_NAME +key;
                //第一个上传完成保存url
                resList.add(imageUrl1);

                //开始第二个上传
                uploadManager.put(posterPath, key, token, new UpCompletionHandler() {
                    @Override
                    public void complete(String key, ResponseInfo info, JSONObject response) {
                        Log.i("qiniu",key + ",\r\n " + info + ",\r\n " + info);
                        Log.i(TAG, "complete: "+info.isOK());
                        String imageUrl2 = DOMAIN_NAME +key;
                        //第二个上传完成保存url
                        resList.add(imageUrl2);
                        mutalUploadCallback.mutlComplete(resList);
                    }
                },new UploadOptions(null, null, false,
                        new UpProgressHandler(){
                            public void progress(String key, double percent){
                                Log.i("qiniu", key + ": " + percent);
                            }
                        }, null));

            }
        },new UploadOptions(null, null, false,
                new UpProgressHandler(){
                    public void progress(String key, double percent){
                        Log.i("qiniu", key + ": " + percent);
                    }
                }, null));
    }


}
