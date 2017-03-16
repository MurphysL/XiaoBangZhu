package com.xiaobangzhu.xiaobangzhu;

import android.app.ActivityManager;
import android.app.AlertDialog;
import android.app.Application;
import android.app.Notification;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.qiniu.android.common.Zone;
import com.qiniu.android.storage.Configuration;
import com.qiniu.android.storage.UploadManager;
import com.squareup.picasso.LruCache;
import com.squareup.picasso.Picasso;
import com.xiaobangzhu.xiaobangzhu.Bean.LoginResultCode;
import com.xiaobangzhu.xiaobangzhu.UI.activity.MainActivity;
import com.xiaobangzhu.xiaobangzhu.Utils.PushUtil;
import com.xiaobangzhu.xiaobangzhu.View.PicassoImageLoader;
import com.xiaobangzhu.xiaobangzhu.db.DaoMaster;
import com.xiaobangzhu.xiaobangzhu.db.DaoSession;
import com.xiaobangzhu.xiaobangzhu.db.NotificationDao;

import java.util.List;

import cn.finalteam.galleryfinal.CoreConfig;
import cn.finalteam.galleryfinal.FunctionConfig;
import cn.finalteam.galleryfinal.GalleryFinal;
import cn.finalteam.galleryfinal.ImageLoader;
import cn.finalteam.galleryfinal.ThemeConfig;
import cn.jpush.android.api.BasicPushNotificationBuilder;
import cn.jpush.android.api.JPushInterface;

/**
 * Created by WQC on 2016/7/12.
 */
public class MyApplication extends Application {
    private static final String TAG = "MyApplication";
    private static final String KEY_LOGIN_TIME = "login_time";
    private static MyApplication mInstance;
    private static RequestQueue mRequestQueue;
    private static boolean isUserLogin = false;
    private static ProgressDialog mProgressDialog;
    private static AlertDialog alertDialog;
    private static SharedPreferences mSharedPreferences;
    private static final String KEY_LOGIN_ID = "loginId";
    private static final String KEY_TOKEN = "token";
    private static final String KEY_COLLEGE_ID = "collegeId";
    private static final String KEY_COLLEGE_NAME = "college_name";
    private static final String KEY_NICK_NAME = "nick_name";
    private static final String KEY_IS_LOGIN = "isLogin";
    private static final String KEY_HEADER = "header";
    private static final String KEY_BACKGROUND = "activity_luancher_bg";
    private static final String KEY_SLOGAN = "signature";
    private static final String KEY_FANS_NUM = "fansNum";
    private static final String KEY_C_TIME = "c_time";
    private static final String KEY_IDENTIFIED = "identified";
    private static final String KEY_SEX = "sex";
    private static final String KEY_TYPE = "type";
    private static final String KEY_ID = "id";
    private static final String KEY_LEVEL = "level";
    private static final String KEY_LIVE_TIME = "live_time";
    private static final String KEY_ATTENTION_NUM = "attentionNum";
    private static final String KEY_DEPOSIT = "deposit";
    private static final String KEY_LOAN_LIMIT = "loanlimit";

    private static final String sharedPreferencesName = "XiaoBangZhu";
    private static final int PICASO_LRUCHCHE_SIZE = 10 << 20;
    private static final int PICASO_DISK_CACHE_SIZE = 70 << 20;
    private UploadManager uploadManager;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        mRequestQueue = Volley.newRequestQueue(this);
        mSharedPreferences = getSharedPreferences(sharedPreferencesName, MODE_PRIVATE);
        initUserInform();
        initGalleryFinal();
        initPicaso();
        initQiNiuYun();
        initJPush();
    }

    /**
     * Y
     * 初始化极光推送
     */
    private void initJPush() {
        registerMessageReceiver();
        
        JPushInterface.setDebugMode(true); 	// 设置开启日志,发布时请关闭日志
        JPushInterface.init(getApplicationContext());

        BasicPushNotificationBuilder builder = new BasicPushNotificationBuilder(getApplicationContext());
        builder.statusBarDrawable = R.mipmap.ic_launcher;
        builder.notificationFlags = Notification.FLAG_AUTO_CANCEL;  //设置为点击后自动消失
        builder.notificationDefaults = Notification.DEFAULT_SOUND;  //设置为铃声（ Notification.DEFAULT_SOUND）或者震动（ Notification.DEFAULT_VIBRATE）
        JPushInterface.setPushNotificationBuilder(1, builder);
    }

    //for receive customer msg from jpush server
    private MessageReceiver mMessageReceiver;
    public static final String MESSAGE_RECEIVED_ACTION = "com.example.jpushdemo.MESSAGE_RECEIVED_ACTION";
    public static final String KEY_TITLE = "title";
    public static final String KEY_MESSAGE = "message";
    public static final String KEY_EXTRAS = "extras";

    public void registerMessageReceiver() {
        mMessageReceiver = new MessageReceiver();
        IntentFilter filter = new IntentFilter();
        filter.setPriority(IntentFilter.SYSTEM_HIGH_PRIORITY);
        filter.addAction(MESSAGE_RECEIVED_ACTION);
        registerReceiver(mMessageReceiver, filter);
    }

    public class MessageReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            if (MESSAGE_RECEIVED_ACTION.equals(intent.getAction())) {
                String messge = intent.getStringExtra(KEY_MESSAGE);
                String extras = intent.getStringExtra(KEY_EXTRAS);
                StringBuilder showMsg = new StringBuilder();
                showMsg.append(KEY_MESSAGE + " : " + messge + "\n");
                if (!PushUtil.isEmpty(extras)) {
                    showMsg.append(KEY_EXTRAS + " : " + extras + "\n");
                }
            }
        }
    }


    /**
     * 初始化picaso加载器
     */
    private void initPicaso() {
        Picasso picasso = new Picasso.Builder(getApplicationContext())
                .indicatorsEnabled(false)
                .memoryCache(new LruCache(PICASO_LRUCHCHE_SIZE))
                .build();
        Picasso.setSingletonInstance(picasso);
    }

    /**
     * 初始化图片选择器
     */
    private void initGalleryFinal() {
        ThemeConfig theme = ThemeConfig.ORANGE;
        FunctionConfig functionConfig = new FunctionConfig.Builder()
                .setEnableCamera(true)
                .setEnableEdit(true)
                .setEnableCrop(true)
                .setForceCrop(true)
                .setEnableRotate(false)
                .setEnablePreview(true)
                .setCropSquare(true)
                .setMutiSelectMaxSize(3)//配置多选数量
                .build();

        ImageLoader imageLoader = new PicassoImageLoader();

        CoreConfig coreConfig = new CoreConfig.Builder(getApplicationContext(), imageLoader, theme).setFunctionConfig(functionConfig).build();

        GalleryFinal.init(coreConfig);
    }

    /**
     * 初始化七牛云
     */
    public void initQiNiuYun() {
        Configuration configuration = new Configuration.Builder()
                .chunkSize(256 * 1024)  //分片上传时，每片的大小。 默认256K
                .putThreshhold(512 * 1024)  // 启用分片上传阀值。默认512K
                .connectTimeout(10) // 链接超时。默认10秒
                .responseTimeout(60) // 服务器响应超时。默认60秒
                .zone(Zone.zone1).build();
        uploadManager = new UploadManager(configuration);
    }

    /**
     * 获取uploadManager实例
     *
     * @return
     */
    public UploadManager getUploadManager() {
        return uploadManager;
    }

    public static MyApplication getInstance() {
        return mInstance;
    }

    private void initUserInform() {

    }

    public static RequestQueue getRequestQueue() {
        return mRequestQueue;
    }

    public static void showToastShort(String message) {
        Toast.makeText(mInstance, message, Toast.LENGTH_LONG).show();
    }

    /**
     * 保存用户信息
     *
     * @param userInform
     * @return
     */
    public boolean writeUserInfrom(LoginResultCode.Data userInform) {
        Log.i(TAG, "writeUserInfrom: " + "writeUserInfrom");
        boolean result = false;
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putString(KEY_TOKEN, userInform.getToken());//用户token
        editor.putInt(KEY_COLLEGE_ID, userInform.getCollege_id());//学校id
        editor.putString(KEY_COLLEGE_NAME, userInform.getCollege_name());//学校名称
        editor.putString(KEY_LOGIN_ID, userInform.getLogin_id());//用户账户
        editor.putInt(KEY_LEVEL, userInform.getLevel());//用户等级
        editor.putString(KEY_IDENTIFIED, userInform.getIdentified());//是否认证
        editor.putString(KEY_HEADER, userInform.getHeader());//头像
        editor.putString(KEY_BACKGROUND, userInform.getBackground());//背景图片
        editor.putInt(KEY_FANS_NUM, userInform.getFansNum());//粉丝数
        editor.putInt(KEY_ATTENTION_NUM, userInform.getAttentionNum());
        editor.putInt(KEY_ID, userInform.getId());//用户id
        editor.putString(KEY_SLOGAN, userInform.getSignature());//签名
        editor.putString(KEY_SEX, userInform.getSex());//性别
        editor.putString(KEY_C_TIME, userInform.getC_time());//注册时间
        editor.putString(KEY_TYPE, userInform.getType());//用户类型
        editor.putString(KEY_NICK_NAME, userInform.getNick_name());//昵称
        editor.putBoolean(KEY_IS_LOGIN, true);//是否登录
        Log.i(TAG, "writeUserInfrom: " + userInform.getLive());
        editor.putLong(KEY_LIVE_TIME, userInform.getLive());//有效时长
        editor.putInt(KEY_DEPOSIT, userInform.getDeposit());//积分
        editor.putInt(KEY_LOAN_LIMIT, userInform.getLoanlimit());
        editor.putLong(KEY_LOGIN_TIME, System.currentTimeMillis());
        result = editor.commit();
        isUserLogin = true;
        Log.i(TAG, "写入成功");
        return result;
    }

    public long getKeyLiveTime() {
        return mSharedPreferences.getLong(KEY_LIVE_TIME,0);
    }

    public long getLoginTime() {
        return mSharedPreferences.getLong(KEY_LOGIN_TIME,0);
    }

    public String getUserSex(){
        return mSharedPreferences.getString(KEY_SEX, "");
    }

    public int getAttentionNum(){
        return mSharedPreferences.getInt(KEY_ATTENTION_NUM, 0);
    }

    public String getUserType() {
        return mSharedPreferences.getString(KEY_TYPE, "");
    }

    public String getUserNickName() {
        return mSharedPreferences.getString(KEY_NICK_NAME, "");
    }

    public int getUserId() {
        return mSharedPreferences.getInt(KEY_ID, 0);
    }

    public String getUserLoginId() {
        return mSharedPreferences.getString(KEY_LOGIN_ID, "");
    }

    public int getUserCollegeId() {
        return mSharedPreferences.getInt(KEY_COLLEGE_ID, 0);
    }

    public String getUserCollegeName() {
        return mSharedPreferences.getString(KEY_COLLEGE_NAME, "");
    }

    public String getUserToken() {
        return mSharedPreferences.getString(KEY_TOKEN, "");
    }

    public String getUserHeader() {
        return mSharedPreferences.getString(KEY_HEADER, "");
    }

    public String getUserBackGround() {
        return mSharedPreferences.getString(KEY_BACKGROUND, "");
    }

    public String getUserSlogan() {
        return mSharedPreferences.getString(KEY_SLOGAN, "");
    }

    public int getUserFansNum() {
        return mSharedPreferences.getInt(KEY_FANS_NUM, 0);
    }

    public int getUserLevel() {
        return mSharedPreferences.getInt(KEY_LEVEL, 0);
    }


    /**
     * 获取是否登录
     */
    public boolean isUserLogin() {
        isUserLogin = mSharedPreferences.getBoolean(KEY_IS_LOGIN, false);
        return isUserLogin;
    }

    public void setUserLogin(boolean isUserLogin) {
        SharedPreferences.Editor edit = mSharedPreferences.edit();
        edit.putBoolean(KEY_IS_LOGIN, isUserLogin);
        edit.commit();
    }

    /**
     * 显示进度条
     */
    public static void showProgress(Context context, String title, String message) {
        mProgressDialog = null;
        mProgressDialog = new ProgressDialog(context, ProgressDialog.STYLE_SPINNER);
        mProgressDialog.setMessage(message);
        mProgressDialog.setTitle(title);
        mProgressDialog.show();
    }

    /**
     * 取消进度条
     */
    public static void dismissProgress() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
    }

    /**
     * 显示对话框
     */
    public static void showDialog(Context context, String message) {
        if (alertDialog != null) {
            alertDialog.setMessage(message);
            if(context != null)
                alertDialog.show();
        } else {
            alertDialog = new AlertDialog.Builder(context).setMessage(message).create();
            alertDialog.setCanceledOnTouchOutside(true);
            alertDialog.show();
        }

    }

    /**
     * 取消对话框
     */
    public static void dismissDialog() {
        if (alertDialog != null && alertDialog.isShowing()) {
            alertDialog.dismiss();
        }
    }

    /**
     * 判断主界面是否在前台
     *
     */
    private boolean isForeground() {

        ActivityManager am = (ActivityManager) getApplicationContext().getSystemService(getApplicationContext().ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> list = am.getRunningTasks(1);
        if (list != null && list.size() > 0) {
            ComponentName cpn = list.get(0).topActivity;
            if (MainActivity.class.equals(cpn.getClassName())) {
                return true;
            }
        }

        return false;
    }

    public NotificationDao getDao(){
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "notes-db", null);
        SQLiteDatabase db = helper.getWritableDatabase();
        DaoMaster daoMaster = new DaoMaster(db);
        DaoSession daoSession = daoMaster.newSession();
        NotificationDao notificationDao = daoSession.getNotificationDao();
        return  notificationDao;
    }


}
