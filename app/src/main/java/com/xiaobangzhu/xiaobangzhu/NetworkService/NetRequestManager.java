package com.xiaobangzhu.xiaobangzhu.NetworkService;

import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;
import com.xiaobangzhu.xiaobangzhu.Bean.ActivityListResultCode;
import com.xiaobangzhu.xiaobangzhu.Bean.AdPictures;
import com.xiaobangzhu.xiaobangzhu.Bean.AddTangleResultCode;
import com.xiaobangzhu.xiaobangzhu.Bean.BaseResultCode;
import com.xiaobangzhu.xiaobangzhu.Bean.CollegeListResultCode;
import com.xiaobangzhu.xiaobangzhu.Bean.GetExressCompanyResultCode;
import com.xiaobangzhu.xiaobangzhu.Bean.InitImageResultCode;
import com.xiaobangzhu.xiaobangzhu.Bean.LatestVersionCode;
import com.xiaobangzhu.xiaobangzhu.Bean.LoginResultCode;
import com.xiaobangzhu.xiaobangzhu.Bean.NewsListResultCode;
import com.xiaobangzhu.xiaobangzhu.Bean.PublishResultCode;
import com.xiaobangzhu.xiaobangzhu.Bean.QiNiuResultCode;
import com.xiaobangzhu.xiaobangzhu.Bean.RegisteResultCode;
import com.xiaobangzhu.xiaobangzhu.Bean.UserBaseInform;
import com.xiaobangzhu.xiaobangzhu.Bean.VerifyCode;
import com.xiaobangzhu.xiaobangzhu.Interface.DataChangeListener;
import com.xiaobangzhu.xiaobangzhu.MyApplication;
import com.xiaobangzhu.xiaobangzhu.Utils.VerifyUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by WQC on 2016/7/27.
 */
public class NetRequestManager {
    static final String TAG = "NetRequestManager";
    static NetRequestManager mInstance = new NetRequestManager();
    Gson gson;
    DataChangeListener<LoginResultCode> loginResultCodeListener;
    DataChangeListener<RegisteResultCode> registeResultCodeListener;
    DataChangeListener<UserBaseInform> userBaseInformListener;
    DataChangeListener<AddTangleResultCode> addTangleResultCodeListener;
    DataChangeListener<VerifyCode> verifyCodeListener;
    DataChangeListener<AdPictures> adPicturesListener;
    DataChangeListener<NewsListResultCode> newsListResultCodeListener;
    DataChangeListener<QiNiuResultCode> qiNiuResultCodeChangeListener;
    DataChangeListener<CollegeListResultCode> collegeListResultCodeListener;
    DataChangeListener<ActivityListResultCode> activityListResultCodeListener;

    DataChangeListener<PublishResultCode> addNormalResultCodeChangeListener;
    DataChangeListener<BaseResultCode> addSecondResultCodeChangeListener;
    DataChangeListener<BaseResultCode> addSuggestResultCodeChangListener;
    DataChangeListener<BaseResultCode> addExpressResultCodeListener;
    DataChangeListener<BaseResultCode> addTeamResultCodeListener;
    DataChangeListener<BaseResultCode> addActivityResultCodeListener;
    DataChangeListener<GetExressCompanyResultCode> getExressCompanyResultCodeListener;
    DataChangeListener<BaseResultCode> authResultCodeListener;
    DataChangeListener<BaseResultCode> updateUserCodeListener;
    DataChangeListener<InitImageResultCode> initImageCodeListener;
    DataChangeListener<LatestVersionCode> latestVersionCodeDataChangeListener;


    private NetRequestManager() {
        gson = new Gson();
    }

    public static NetRequestManager getInstance() {
        return mInstance;
    }
    /**
     * post请求
     * @param url
     * @param responseListener
     * @param errorListener
     */
    public void postRequest(String url, Response.Listener<String> responseListener,Response.ErrorListener errorListener) {
        StringRequest request = new StringRequest(Request.Method.POST, url, responseListener, errorListener);
        Log.i(TAG, url);
        request.setTag(url);
        MyApplication.getRequestQueue().add(request);
        MyApplication.getRequestQueue().start();
    }

    /**
     * post请求,可自定义request的Headers
     * @param url
     * @param responseListener
     * @param errorListener
     */
    public void postRequest(String url, Response.Listener<String> responseListener, Response.ErrorListener errorListener, final Map<String,String> headers) throws IllegalArgumentException {
        if (url != null && responseListener != null && errorListener != null) {
            if (headers == null) {
                postRequest(url, responseListener, errorListener);
            }else{
                StringRequest request = new StringRequest(Request.Method.POST, url, responseListener, errorListener){
                    @Override
                    public Map<String, String> getHeaders() throws AuthFailureError {
                        return headers;
                    }
                };
                Log.i(TAG, url);
                request.setTag(url);
                MyApplication.getRequestQueue().add(request);
            }
        }else{
            throw new IllegalArgumentException();
        }

    }

    /**
     *
     * @param token
     * @param url
     * @param headers
     * @param parems
     * @param responseListener
     * @param errorListener
     */
    public void postRequest(String token,String url,final Map<String,String> headers,final Map<String,String> parems,Response.Listener<String> responseListener,Response.ErrorListener errorListener) {
        if (url != null && responseListener != null && errorListener != null) {
            if (headers == null) {
                postRequest(url, responseListener, errorListener);
            }else{
                StringRequest request = new StringRequest(Request.Method.POST, url, responseListener, errorListener){
                    @Override
                    public Map<String, String> getHeaders() throws AuthFailureError {
                        return headers;
                    }

                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        return parems;
                    }
                };
                Log.i(TAG, url);
                request.setTag(url);
                MyApplication.getRequestQueue().add(request);
            }
        }else{
            throw new IllegalArgumentException();
        }
    }

    /**
     * get请求
     * @param url
     * @param responseListener
     * @param errorListener
     */
    public void getRequest(String url, Response.Listener<String> responseListener,Response.ErrorListener errorListener) {
        StringRequest request = new StringRequest(Request.Method.GET, url, responseListener, errorListener);
        Log.i(TAG, url);
        request.setTag(url);
        MyApplication.getRequestQueue().add(request);
    }

    /**
     * get请求,可自定义request的Headers
     * @param url
     * @param responseListener
     * @param errorListener
     */
    public void getRequest(String url, Response.Listener<String> responseListener, Response.ErrorListener errorListener, final Map<String,String> headers) throws IllegalArgumentException {
        if (url != null && responseListener != null && errorListener != null) {
            if (headers == null) {
                getRequest(url, responseListener, errorListener);
            }else{
                StringRequest request = new StringRequest(Request.Method.GET, url, responseListener, errorListener){
                    @Override
                    public Map<String, String> getHeaders() throws AuthFailureError {
                        return headers;
                    }
                };
                request.setTag(url);
                Log.i(TAG, url);
                MyApplication.getRequestQueue().add(request);
            }
        }else{
            throw new IllegalArgumentException();
        }
    }

    /**
     * 获取验证码
     * @param loginId
     * @param type
     * @return verifycode
     */
    public void getVerifyCode(String loginId, int type) {
        String url = BaseUrlManager.getUrlForVerifyCode(loginId, type);
        Log.i(TAG, "getVerifyCode: " + url);
        getRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.i(TAG, "verifyCodeResponse: " + response);
                if (response != null) {
                    VerifyCode verifyCode = gson.fromJson(response, VerifyCode.class);
                    if (verifyCode != null) {
                        verifyCodeListener.onSuccessful(verifyCode);
                    }
                }else{
                    verifyCodeListener.onResponseNull();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i(TAG, "onErrorResponse: " + error.getMessage()+error.getCause().getMessage());
                verifyCodeListener.onError(error);
            }
        });
    }

    /**
     * 注册用户
     *
     * @param loginId
     * @param password
     * @param sex
     * @return
     */
    public void registeUser(String loginId, String password, String sex,int collegeId) {
        String url = BaseUrlManager.getUrlForRegiste(loginId, password, sex,collegeId);
        Log.i(TAG, "registeUser: " + url);
        postRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.i(TAG, "registResponse: " + response);
                RegisteResultCode registResultCode = gson.fromJson(response, RegisteResultCode.class);
                registeResultCodeListener.onSuccessful(registResultCode);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                registeResultCodeListener.onError(error);
            }
        });
    }

    /**
     * 用户登录
     *
     * @param loginId
     * @param password
     * @return
     */
    public void login(String loginId, String password) {
        String url = BaseUrlManager.getUrlForLogin() + "?param={\"login_id\":" + "\"" + loginId + "\"," + "\"password\":" + "\"" + password + "\"}";
        Log.i(TAG, "login: " + url);
        postRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.i(TAG, "onResponse: " + response);
                if (response != null) {
                    LoginResultCode loginResultCode = gson.fromJson(response, LoginResultCode.class);
                    if (loginResultCode != null) {
                        loginResultCodeListener.onSuccessful(loginResultCode);
                    }
                }else {

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                loginResultCodeListener.onError(error);
            }
        });
    }

    /**
     * 获取用户基本信息的接口
     *
     * @return
     */
    public void getUserInform(final String token) {
        String url = BaseUrlManager.getUrlForGetUserInform();
        Log.i(TAG, "getUserInform: " + url);
        Map<String, String> headers = new HashMap<String, String>();
        headers.put("token",token );
        getRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response != null) {
                    UserBaseInform userBaseInform = gson.fromJson(response, UserBaseInform.class);
                    if (userBaseInform != null) {
                        userBaseInformListener.onSuccessful(userBaseInform);
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i(TAG, "onErrorResponse: " + error.getMessage());
                userBaseInformListener.onError(error);
            }
        },headers);
    }
    /**
     * 添加纠结癌
     * @param collegeId
     * @param content
     * @return
     */
    public void addTangleCancer(final String token ,int collegeId, String content, String chooseA, String chooseB) {
        String url = BaseUrlManager.getUrlForAddTangleCancer(collegeId, content, chooseA, chooseB);
        Map<String, String> headers = new HashMap<String, String>();
        headers.put("token",token);
        postRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.i(TAG, "onResponse: " + response);
                if (response != null) {
                    AddTangleResultCode resultCode = gson.fromJson(response, AddTangleResultCode.class);
                    if (resultCode != null) {
                        addTangleResultCodeListener.onSuccessful(resultCode);
                    }
                }else{
                    addTangleResultCodeListener.onResponseNull();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i(TAG, "onErrorResponse: " + error.getMessage());
                addTangleResultCodeListener.onError(error);
            }
        },headers);
    }

    /**
     * 获取首页图片
     * @param collegeId
     */
    public void getAdPictures(int collegeId,final String token){
        String url = BaseUrlManager.getUrlForAdPictures(collegeId);
        Log.i(TAG, "getAdPictures: " + url);
        Map<String, String> headers = new HashMap<String, String>();
        headers.put("token", token);
        getRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.i(TAG, "getAdPictures: " + response);
                if (response != null) {
                    AdPictures adPictures = gson.fromJson(response, AdPictures.class);
                    if (adPictures != null) {
                        adPicturesListener.onSuccessful(adPictures);
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                adPicturesListener.onError(error);
                Log.e(TAG, "onErrorResponse: " +error.getMessage()+"\n"+error.getCause());
            }
        },headers);
    }

    /**
     * 获取首页新闻列表
     */
    public void getNewsList(final String token,String url) {
        Map<String, String> headers = new HashMap<String, String>();
        headers.put("token", token);
        Log.i(TAG, "getNewsList:" + url);
        getRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.i(TAG, "getNewsList:" + response);
                if (response != null) {
                    NewsListResultCode newsListResultCode = gson.fromJson(response, NewsListResultCode.class);
                    if (newsListResultCode != null) {
                        if (newsListResultCodeListener != null) {
                            newsListResultCodeListener.onSuccessful(newsListResultCode);
                        }else{
//                            throw new NullPointerException("NewsListResultCodeListener is null");
                        }
                    }else{
//                        throw new NullPointerException("NewsListResultCode is null");
                    }
                }else {
                    newsListResultCodeListener.onResponseNull();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i(TAG, "getNewsList:" + error.getMessage());
                newsListResultCodeListener.onError(error);
            }
        }, headers);
    }

    /**
     * 获取首页活动列表
     */
    public void getActivityList(final String token,String url) {
        Map<String, String> headers = new HashMap<String, String>();
        headers.put("token", token);
        Log.i(TAG, "getActivityList: " + url);
        getRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.i(TAG, "getActivityList: " + response);
                if (response != null && response != "") {
                    ActivityListResultCode resultCode = gson.fromJson(response, ActivityListResultCode.class);
                    if (resultCode != null) {
                        if (activityListResultCodeListener != null) {
                            activityListResultCodeListener.onSuccessful(resultCode);
                        }
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (activityListResultCodeListener != null) {
                    activityListResultCodeListener.onError(error);
                }
            }
        }, headers);

    }

    /**
     * 获取学校列表
     */
    public void getCollegeList(){
        String url = BaseUrlManager.getUrlForGetCollegeList();
        getRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.i(TAG, "onResponse: "+response);
                if (response != null) {
                    CollegeListResultCode collegeList = gson.fromJson(response, CollegeListResultCode.class);
                    if (collegeList != null) {
                        if (collegeListResultCodeListener != null) {
                            collegeListResultCodeListener.onSuccessful(collegeList);
                        }else{
                            throw new NullPointerException("CollegeListResultCodeListener is null");
                        }

                    }else{
                        throw new NullPointerException("collegeList is null");
                    }

                }else{
                    collegeListResultCodeListener.onResponseNull();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.w(TAG, "onErrorResponse: " + error.getMessage());
                collegeListResultCodeListener.onError(error);
            }
        });
    }


    /**
     * 获取七牛云的token
     */
    public void getQiniToken(String token) {
        Map<String, String> headers = new HashMap<>();
        headers.put("token", token);
        String url = BaseUrlManager.getUrlForGetQiNiuYunToken();
        getRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (qiNiuResultCodeChangeListener != null) {
                    if (response != null) {
                        QiNiuResultCode data = gson.fromJson(response, QiNiuResultCode.class);
                        if (data != null) {
                            qiNiuResultCodeChangeListener.onSuccessful(data);
                        }else {
                            throw new NullPointerException("Gson convert error");
                        }
                    }else{
                        qiNiuResultCodeChangeListener.onResponseNull();
                    }
                }else{
                    throw new NullPointerException("QiNiuResultCodeChangeListener is null");
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }, headers);


    }

    /**
     * 添加普通任务
     * @param token
     * @param collegeId
     * @param title
     * @param desc
     * @param imageUrl
     * @param address
     * @param tip
     * @param specify
     * @param endTime
     */
    public void addNormalTask(String token,int collegeId,String title,String desc,String imageUrl,String address,int tip,String specify,String endTime) {
        Map<String, String> headers = new HashMap<>();
        headers.put("token", token);
        Log.i(TAG, "addNormalTask: " + token);
        String url = BaseUrlManager.getUrlForAddNormalTask(collegeId, title, desc, imageUrl, address, tip, specify, endTime);
        if (url.contains(" ")) {
            Log.i(TAG, "addNormalTask: has 空格");
            url = url.replaceAll(" ","%2B");
        }else{
            Log.i(TAG, "addNormalTask:  不含空格");
        }
        postRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.i(TAG, "onResponse: " + response);
                if (response != null) {
                     PublishResultCode publishResultCode = gson.fromJson(response, PublishResultCode.class);
                    if (publishResultCode != null) {
                        addNormalResultCodeChangeListener.onSuccessful(publishResultCode);
                    }
                }else{
                    addNormalResultCodeChangeListener.onResponseNull();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i(TAG, "onErrorResponse: " + error.getMessage());
                addNormalResultCodeChangeListener.onError(error);
            }
        },headers);

    }
    /**
     * 添加二手任务
     * @param token
     * @param collegeId
     * @param price
     * @param title
     * @param desc
     * @param picture1
     * @param picture2
     * @param picture3
     */
    public void addSecondTask(String token,int collegeId,int price,String title,String desc,String picture1,String picture2,String picture3) {
        Map<String, String> headers = new HashMap<>();
        headers.put("token", token);
        String url = BaseUrlManager.getUrlForAddSecondTask(collegeId, price, title, desc, picture1, picture2, picture3).trim();
        if (url.contains(" ")) {
            Log.i(TAG, "addNormalTask: has 空格");
            url = url.replaceAll(" ","%2B");
        }else{
            Log.i(TAG, "addNormalTask:  不含空格");
        }
        postRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.i(TAG, "onResponse: " + response);
                if (response != null) {
                    BaseResultCode resultCode = gson.fromJson(response, BaseResultCode.class);
                    if (resultCode != null) {
                        if (addSecondResultCodeChangeListener != null) {
                            addSecondResultCodeChangeListener.onSuccessful(resultCode);
                        }
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (addSecondResultCodeChangeListener != null) {
                    addSecondResultCodeChangeListener.onError(error);
                }
            }
        }, headers);

    }

    /**
     * 添加建议
     * @param token
     * @param content
     * @param contact
     */
    public void addSuggest(String token, String content, String contact) {
        Map<String, String> headers = new HashMap<>();
        headers.put("token", token);
        String url = BaseUrlManager.getUrlForAddSuggest(content, contact);
        postRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response != null) {
                    BaseResultCode data = gson.fromJson(response, BaseResultCode.class);
                    if (data != null) {
                        addSuggestResultCodeChangListener.onSuccessful(data);
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (addSuggestResultCodeChangListener != null) {
                    addSuggestResultCodeChangListener.onError(error);
                }
            }
        }, headers);
    }

    /**
     * 扫描二维码
     * @param token
     * @param url
     */
    public void sacnQR(String token, String url) {
        Map<String, String> headers = new HashMap<>();
        headers.put("token", token);
        Log.i(TAG, "sacnQR: " + url);
        postRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response != null) {
                    BaseResultCode resultCode = gson.fromJson(response, BaseResultCode.class);
                    if (resultCode != null) {
                        if (resultCode.getStatus() == 0) {
                            Log.i(TAG, "onResponse: "+resultCode.getDesc());
                            MyApplication.showToastShort("扫码成功");
                        }else{
                            MyApplication.showToastShort("二维码无效");
                        }
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                MyApplication.showToastShort("检查网络连接");
            }
        }, headers);
    }

    /**
     * 快递发布
     *
     * @param token
     */
    public void addExpress(String token, int collegeId, String name, int expressId, String address, int tip, String phoneTail, int type, String limitTime) {
        Map<String, String> headers = new HashMap<>();
        headers.put("token", token);
        String url = BaseUrlManager.getUrlForAddExpressage(collegeId, name, expressId, address, tip, phoneTail, type, limitTime);
        if (url.contains(" ")) {
            Log.i(TAG, "addNormalTask: has 空格");
            url = url.replaceAll(" ","%2B");
        }else{
            Log.i(TAG, "addNormalTask:  不含空格");
        }
        Log.i(TAG, "addExpress: " + url);
        postRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.i(TAG, "onResponse: " + response);

                if (response != null) {
                    BaseResultCode resultCode = gson.fromJson(response, BaseResultCode.class);
                    if (resultCode != null) {
                        if (addExpressResultCodeListener != null) {
                            addExpressResultCodeListener.onSuccessful(resultCode);
                        }
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (addExpressResultCodeListener != null) {
                    addExpressResultCodeListener.onError(error);
                }
            }
        }, headers);
    }

    /**
     * 获取快递公司列表
     * @param token
     */
    public void getExpressCompany(String token) {
        Map<String, String> headers = new HashMap<>();
        headers.put("token", token);
        String url = BaseUrlManager.getUrlForGetExpressCompany();
        Log.i(TAG, "getExpressCompany: " + url);
        getRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.i(TAG, "onResponse: " + response);
                if (response != null) {
                    GetExressCompanyResultCode data = gson.fromJson(response, GetExressCompanyResultCode.class);
                    if (data != null) {
                        if (getExressCompanyResultCodeListener != null) {
                            getExressCompanyResultCodeListener.onSuccessful(data);
                        }
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (getExressCompanyResultCodeListener != null) {
                    getExressCompanyResultCodeListener.onError(error);
                }
            }
        }, headers);

    }

    /**
     * 发布校园活动
     * @param token
     * @param collegeId
     * @param title
     * @param address
     * @param startTime
     * @param endTime
     * @param content
     * @param picture
     * @param poster
     * @param tags
     * @param num
     */
    public void addCollegeActivity(String token, int collegeId, String title, String address, String startTime, String endTime, String content, String picture, String poster, String tags,int num) {
        Map<String, String> headers = new HashMap<>();
        headers.put("token", token);
        String url = BaseUrlManager.geturlForAddActivity(collegeId, title, address, startTime, endTime, content, picture, poster, tags, num);
        url = VerifyUtils.replaceSpaces(url);
        postRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.i(TAG, "onResponse: "+response);
                if (response != null) {
                    BaseResultCode data = gson.fromJson(response, BaseResultCode.class);
                    if (data != null) {
                        addActivityResultCodeListener.onSuccessful(data);
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (addActivityResultCodeListener != null) {
                    addActivityResultCodeListener.onError(error);
                }
            }
        }, headers);
    }


    /**
     * 添加组队任务
     * @param token
     * @param collegeId
     * @param content
     * @param picture
     * @param num
     * @param address
     * @param endTime
     * @param special
     */
    public void addTeamTask(String token ,int collegeId,String content,String picture,int num,String address,String endTime,String special){
        Map<String, String> headers = new HashMap<>();
        headers.put("token", token);
        String url = BaseUrlManager.getUrlForAddTeamTask(collegeId, content, picture, address, num, special);
        url = VerifyUtils.replaceSpaces(url);
        postRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response != null) {
                    BaseResultCode data = gson.fromJson(response, BaseResultCode.class);
                    if (data != null) {
                        if (addTeamResultCodeListener != null) {
                            addTeamResultCodeListener.onSuccessful(data);
                        }
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (addTeamResultCodeListener != null) {
                    addActivityResultCodeListener.onError(error);
                }
            }
        }, headers);

    }

    /**
     * 添加用户认证
     * @param token
     * @param name
     * @param college
     * @param education
     * @param picture
     * @param identity
     * @param eduStartDate
     */
    public void auth(String token,String name, String college, String education, String picture, String identity, int eduStartDate) {
        Map<String, String> headers = new HashMap<>();
        headers.put("token", token);
        String url = BaseUrlManager.getUrlForAuth(name, college, education, picture, identity, eduStartDate);
        url = VerifyUtils.replaceSpaces(url);
        postRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.i(TAG, "onResponse: " + response);
                if (response != null) {
                    BaseResultCode data = gson.fromJson(response, BaseResultCode.class);
                    if (data != null) {
                        if (authResultCodeListener != null) {
                            authResultCodeListener.onSuccessful(data);
                        }
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (authResultCodeListener != null) {
                    authResultCodeListener.onError(error);
                }
            }
        }, headers);

    }

    /**
     * 更新用户信息
     * @param signature
     * @param major
     * @param header
     * @param sex
     * @param collegeId
     * @param nickName
     */
    public void updateUserInform(String token,String signature,String major,String header,String sex,int collegeId,String nickName){
        Map<String, String> headers = new HashMap<>();
        headers.put("token", token);
        String url = BaseUrlManager.getUrlForUpdateUser(signature, major, header, sex, collegeId, nickName);
        url = VerifyUtils.replaceSpaces(url);
        postRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response != null && response != "") {
                    BaseResultCode data = gson.fromJson(response, BaseResultCode.class);
                    if (updateUserCodeListener != null) {
                        updateUserCodeListener.onSuccessful(data);
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (updateUserCodeListener != null) {
                    updateUserCodeListener.onError(error);
                }
            }
        },headers);
    }

    /**
     * 启动图片
     */
    public void getInitImage(){
        String url = BaseUrlManager.getUrlForInitImage();
        Log.i(TAG, "getInitImage: "+ url);
        getRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.i(TAG, "onResponse: " + response);
                if (response != null) {
                    InitImageResultCode data = gson.fromJson(response, InitImageResultCode.class);
                    if (data != null) {
                        if (initImageCodeListener != null) {
                            initImageCodeListener.onSuccessful(data);
                        }
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (initImageCodeListener != null) {
                    initImageCodeListener.onError(error);
                }
            }
        });
    }

    /**
     * 新版本
     */
    public void getLatestVersion(String token){
        Map<String, String> headers = new HashMap<>();
        headers.put("token", token);

        String url = HtmlManager.getmInstance().getUrlForUpdate();

        getRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (!response.equals("")) {
                    Log.i(TAG, "getLatestVersion: " + response);
                    LatestVersionCode data = gson.fromJson(response , LatestVersionCode.class);
                    if(latestVersionCodeDataChangeListener != null){
                        latestVersionCodeDataChangeListener.onSuccessful(data);
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if(latestVersionCodeDataChangeListener != null){
                    latestVersionCodeDataChangeListener.onError(error);
                }
            }
        } , headers);
    }


    public void setInitImageCodeListener(DataChangeListener<InitImageResultCode> initImageCodeListener) {
        this.initImageCodeListener = initImageCodeListener;
    }

    public void setUpdateUserCodeListener(DataChangeListener<BaseResultCode> updateUserCodeListener) {
        this.updateUserCodeListener = updateUserCodeListener;
    }

    public void setAuthResultCodeListener(DataChangeListener<BaseResultCode> authResultCodeListener) {
        this.authResultCodeListener = authResultCodeListener;
    }

    public void setAddTeamResultCodeListener(DataChangeListener<BaseResultCode> addTeamResultCodeListener) {
        this.addTeamResultCodeListener = addTeamResultCodeListener;
    }

    public void setAddActivityResultCodeListener(DataChangeListener<BaseResultCode> addActivityResultCodeListener) {
        this.addActivityResultCodeListener = addActivityResultCodeListener;
    }

    public void setGetExressCompanyResultCodeListener(DataChangeListener<GetExressCompanyResultCode> getExressCompanyResultCodeListener) {
        this.getExressCompanyResultCodeListener = getExressCompanyResultCodeListener;
    }

    public void setAddExpressResultCodeListener(DataChangeListener<BaseResultCode> addExpressResultCodeListener) {
        this.addExpressResultCodeListener = addExpressResultCodeListener;
    }

    public void setAddSuggestResultCodeChangListener(DataChangeListener<BaseResultCode> addSuggestResultCodeChangListener) {
        this.addSuggestResultCodeChangListener = addSuggestResultCodeChangListener;
    }

    public void setAddSecondResultCodeChangeListener(DataChangeListener<BaseResultCode> addSecondResultCodeChangeListener) {
        this.addSecondResultCodeChangeListener = addSecondResultCodeChangeListener;
    }

    public void setActivityListResultCodeListener(DataChangeListener<ActivityListResultCode> activityListResultCodeListener) {
        this.activityListResultCodeListener = activityListResultCodeListener;
    }

    public void setAddNormalResultCodeChangeListener(DataChangeListener<PublishResultCode> publishResultCodeChangeListener) {
        this.addNormalResultCodeChangeListener = publishResultCodeChangeListener;
    }

    public void setQiNiuResultCodeChangeListener(DataChangeListener<QiNiuResultCode> qiNiuResultCodeChangeListener) {
        this.qiNiuResultCodeChangeListener = qiNiuResultCodeChangeListener;
    }

    public void setNewsListResultCodeListener(DataChangeListener<NewsListResultCode> newsListResultCodeListener) {
        this.newsListResultCodeListener = newsListResultCodeListener;
    }

    public void setAdPicturesListener(DataChangeListener<AdPictures> adPicturesListener) {
        this.adPicturesListener = adPicturesListener;
    }

    public void setLoginResultCodeListener(DataChangeListener<LoginResultCode> loginResultCodeListener) {
        this.loginResultCodeListener = loginResultCodeListener;
    }

    public void setRegisteResultCodeListener(DataChangeListener<RegisteResultCode> registeResultCodeListener) {
        this.registeResultCodeListener = registeResultCodeListener;
    }

    public void setUserBaseInformListener(DataChangeListener<UserBaseInform> userBaseInformListener) {
        this.userBaseInformListener = userBaseInformListener;
    }

    public void setAddTangleResultCodeListener(DataChangeListener<AddTangleResultCode> addTangleResultCodeListener) {
        this.addTangleResultCodeListener = addTangleResultCodeListener;
    }

    public void setVerifyCodeListener(DataChangeListener<VerifyCode> verifyCodeListener) {
        this.verifyCodeListener = verifyCodeListener;
    }

    public void setCollegeListResultCodeListener(DataChangeListener<CollegeListResultCode> collegeListResultCodeListener) {
        this.collegeListResultCodeListener = collegeListResultCodeListener;
    }

    public void setLatestVersionCodeDataChangeListener(DataChangeListener<LatestVersionCode> latestVersionCodeDataChangeListener){
        this.latestVersionCodeDataChangeListener = latestVersionCodeDataChangeListener;
    }
}