package com.xiaobangzhu.xiaobangzhu.NetworkService;

import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;
import com.xiaobangzhu.xiaobangzhu.Bean.ActivityListResultCode;
import com.xiaobangzhu.xiaobangzhu.Bean.AdPictures;
import com.xiaobangzhu.xiaobangzhu.Bean.AddTangleResultCode;
import com.xiaobangzhu.xiaobangzhu.Bean.AddVIPCode;
import com.xiaobangzhu.xiaobangzhu.Bean.BaseResultCode;
import com.xiaobangzhu.xiaobangzhu.Bean.CollegeListResultCode;
import com.xiaobangzhu.xiaobangzhu.Bean.DelOrdersByNumberCode;
import com.xiaobangzhu.xiaobangzhu.Bean.GetAllItemsCode;
import com.xiaobangzhu.xiaobangzhu.Bean.GetAllOrdersByUidCode;
import com.xiaobangzhu.xiaobangzhu.Bean.GetCartAllItemsCode;
import com.xiaobangzhu.xiaobangzhu.Bean.GetExressCompanyResultCode;
import com.xiaobangzhu.xiaobangzhu.Bean.InitImageResultCode;
import com.xiaobangzhu.xiaobangzhu.Bean.LatestVersionCode;
import com.xiaobangzhu.xiaobangzhu.Bean.LoginResultCode;
import com.xiaobangzhu.xiaobangzhu.Bean.NewsListResultCode;
import com.xiaobangzhu.xiaobangzhu.Bean.OptionItemsToCartCode;
import com.xiaobangzhu.xiaobangzhu.Bean.OptionOrdersCode;
import com.xiaobangzhu.xiaobangzhu.Bean.PaySignCode;
import com.xiaobangzhu.xiaobangzhu.Bean.PaySuccessByNumberCode;
import com.xiaobangzhu.xiaobangzhu.Bean.PublishResultCode;
import com.xiaobangzhu.xiaobangzhu.Bean.QiNiuResultCode;
import com.xiaobangzhu.xiaobangzhu.Bean.RegisteResultCode;
import com.xiaobangzhu.xiaobangzhu.Bean.UserBaseInform;
import com.xiaobangzhu.xiaobangzhu.Bean.UserVIPInfo;
import com.xiaobangzhu.xiaobangzhu.Bean.VerifyCode;
import com.xiaobangzhu.xiaobangzhu.Bean.VipTypeCode;
import com.xiaobangzhu.xiaobangzhu.Interface.DataChangeListener;
import com.xiaobangzhu.xiaobangzhu.MyApplication;
import com.xiaobangzhu.xiaobangzhu.Utils.VerifyUtils;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * BaseHtmlUrlManager
 *
 * Created by WQC on 2016/7/27.
 */
public class NetRequestManager {
    private static final String TAG = "NetRequestManager";
    private static NetRequestManager mInstance = new NetRequestManager();
    private Gson gson;
    private DataChangeListener<LoginResultCode> loginResultCodeListener;
    private DataChangeListener<RegisteResultCode> registeResultCodeListener;
    private DataChangeListener<UserBaseInform> userBaseInformListener;
    private DataChangeListener<AddTangleResultCode> addTangleResultCodeListener;
    private DataChangeListener<VerifyCode> verifyCodeListener;
    private DataChangeListener<AdPictures> adPicturesListener;
    private DataChangeListener<NewsListResultCode> newsListResultCodeListener;
    private DataChangeListener<QiNiuResultCode> qiNiuResultCodeChangeListener;
    private DataChangeListener<CollegeListResultCode> collegeListResultCodeListener;
    private DataChangeListener<ActivityListResultCode> activityListResultCodeListener;
    private DataChangeListener<PublishResultCode> addNormalResultCodeChangeListener;
    private DataChangeListener<BaseResultCode> addSecondResultCodeChangeListener;
    private DataChangeListener<BaseResultCode> addSuggestResultCodeChangListener;
    private DataChangeListener<BaseResultCode> addExpressResultCodeListener;
    private DataChangeListener<BaseResultCode> addTeamResultCodeListener;
    private DataChangeListener<BaseResultCode> addActivityResultCodeListener;
    private DataChangeListener<GetExressCompanyResultCode> getExressCompanyResultCodeListener;
    private DataChangeListener<BaseResultCode> authResultCodeListener;
    private DataChangeListener<BaseResultCode> updateUserCodeListener;
    private DataChangeListener<InitImageResultCode> initImageCodeListener;
    private DataChangeListener<LatestVersionCode> latestVersionCodeDataChangeListener;
    private DataChangeListener<PaySignCode> paySignCodeDataChangeListener;
    private DataChangeListener<VipTypeCode> vipTypeCodeDataChangeListener;
    private DataChangeListener<AddVIPCode> addVIPCodeDataChangeListener;
    private DataChangeListener<OptionItemsToCartCode> optionItemsToCartDataChangeListener;
    private DataChangeListener<PaySuccessByNumberCode> paySuccessByNumberDataChangeListener;
    private DataChangeListener<OptionOrdersCode> optionOrdersDataChangeListener;
    private DataChangeListener<DelOrdersByNumberCode> delOrdersByNumberDataChangeListener;
    private DataChangeListener<GetAllItemsCode> getAllItemsDataChangeListener;
    private DataChangeListener<GetAllOrdersByUidCode> getAllOrdersByUidDataChangeListener;
    private DataChangeListener<GetCartAllItemsCode> getCartAllItemsDataChangeListener;
    private DataChangeListener<UserVIPInfo> getUserVIPInfoDataChangeListener;


    private NetRequestManager() {
        gson = new Gson();
    }

    public static NetRequestManager getInstance() {
        return mInstance;
    }
    /**
     * post请求
     */
    private void postRequest(String url, Response.Listener<String> responseListener, Response.ErrorListener errorListener) {
        StringRequest request = new StringRequest(Request.Method.POST, url, responseListener, errorListener);
        Log.i(TAG, url);
        request.setTag(url);
        MyApplication.getRequestQueue().add(request);
        MyApplication.getRequestQueue().start();
    }

    /**
     * post请求,可自定义request的Headers
     */
    private void postRequest(String url, Response.Listener<String> responseListener, Response.ErrorListener errorListener, final Map<String, String> headers) throws IllegalArgumentException {
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
     * post请求，JSON
     */
    private void postRequest(String url, Response.Listener<JSONObject> responseListener, Response.ErrorListener errorListener , final JSONObject param){
        if(url != null && responseListener != null && errorListener != null){
            JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST , url , param , responseListener, errorListener);
            Log.i(TAG, url);
            request.setTag(url);
            MyApplication.getRequestQueue().add(request);
        }
    }

    /**
     * post请求，JSON
     */
   /* private void postRequest(String url, Response.Listener<JSONArray> responseListener, Response.ErrorListener errorListener , final JSONArray param){
        if(url != null && responseListener != null && errorListener != null){
            JsonArrayRequest request = new JsonArrayRequest(Request.Method.POST , url , param , responseListener, errorListener);
            Log.i(TAG, url);
            request.setTag(url);
            MyApplication.getRequestQueue().add(request);
        }
    }*/

    /**
     * get请求
     */
    private void getRequest(String url, Response.Listener<String> responseListener, Response.ErrorListener errorListener) {
        StringRequest request = new StringRequest(Request.Method.GET, url, responseListener, errorListener);
        Log.i(TAG, url);
        request.setTag(url);
        MyApplication.getRequestQueue().add(request);
    }

    /**
     * get请求,可自定义request的Headers
     */
    private void getRequest(String url, Response.Listener<String> responseListener, Response.ErrorListener errorListener, final Map<String, String> headers) throws IllegalArgumentException {
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
                    Log.i(TAG, "Error: login");
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
     */
    public void getUserInform(final String token) {
        String url = BaseUrlManager.getUrlForGetUserInform();
        Log.i(TAG, "getUserInform: " + url);
        Map<String, String> headers = new HashMap<>();
        headers.put("token",token );
        getRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response != null) {
                    Log.i(TAG, "onResponse: " + response);
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
     */
    public void addTangleCancer(final String token ,int collegeId, String content, String chooseA, String chooseB) {
        String url = BaseUrlManager.getUrlForAddTangleCancer(collegeId, content, chooseA, chooseB);
        Map<String, String> headers = new HashMap<>();
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
     */
    public void getAdPictures(int collegeId,final String token){
        String url = BaseUrlManager.getUrlForAdPictures(collegeId);
        Log.i(TAG, "getAdPictures: " + url);
        Map<String, String> headers = new HashMap<>();
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
        Map<String, String> headers = new HashMap<>();
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
                        }
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
        Map<String, String> headers = new HashMap<>();
        headers.put("token", token);
        Log.i(TAG, "getActivityList: " + url);
        getRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.i(TAG, "getActivityList: " + response);
                if (response != null && !response.equals("")) {
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
                    Log.i(TAG, "Express onResponse: " + response);
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
     */
    public void updateUserInform(String token,String signature,String major,String header,String sex,int collegeId,String nickName){
        Map<String, String> headers = new HashMap<>();
        headers.put("token", token);
        String url = BaseUrlManager.getUrlForUpdateUser(signature, major, header, sex, collegeId, nickName);
        url = VerifyUtils.replaceSpaces(url);
        postRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response != null && !response.equals("") ) {
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
                Log.i(TAG, "onErrorResponse: " + error);
                if (initImageCodeListener != null) {
                    initImageCodeListener.onError(error);
                }
            }
        });
    }

    /**
     * 支付宝签名
     */
    public void getPaySign(String token , String subject , String body ,int total_fee){
        Map<String, String> headers = new HashMap<>();
        headers.put("token", token);

        String url = HtmlManager.getmInstance().getUrlForPaySign(subject , body ,total_fee);
        Log.i(TAG, "getPaySign: " + url);

        postRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (!response.equals("")) {
                    Log.i(TAG, "getUrlForPaySign: " + response);
                    PaySignCode data = gson.fromJson(response , PaySignCode.class);
                    if(paySignCodeDataChangeListener != null){
                        paySignCodeDataChangeListener.onSuccessful(data);
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if(paySignCodeDataChangeListener != null){
                    paySignCodeDataChangeListener.onError(error);
                }
            }
        } , headers);
    }

    /**
     * 获取会员种类
     */
    public void getVipType(String token){
        Map<String, String> headers = new HashMap<>();
        headers.put("token", token);

        String url = HtmlManager.getmInstance().getUrlForVipType();
        Log.i(TAG, "getVipType: " + url);

        getRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (!response.equals("")) {
                    Log.i(TAG, "getVipType: " + response);
                    VipTypeCode data = gson.fromJson(response , VipTypeCode.class);
                    if(vipTypeCodeDataChangeListener != null){
                        vipTypeCodeDataChangeListener.onSuccessful(data);
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if(vipTypeCodeDataChangeListener != null){
                    vipTypeCodeDataChangeListener.onError(error);
                }
            }
        } , headers);
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

    /**
     * 添加会员
     */
    public void addVIP(String uid , int viptype , int expressnum , String starttime , String endtime){
        Map<String , Object> map = new HashMap<>();
        map.put("uid", uid);
        map.put("viptype", viptype);
        map.put("expressnum" , expressnum);
        map.put("starttime" , starttime);
        map.put("endtime" , endtime);
        JSONObject object = new JSONObject(map);

        String url = BaseUrlManager.getUrlForAddVIPUser();
        Log.i(TAG, "addVIP: " + url + "\nbody:" + object.toString());

        postRequest(url, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.i(TAG, "onResponse: " + response.toString());
                if(!response.toString().equals("")){
                    AddVIPCode data = gson.fromJson(response.toString(), AddVIPCode.class);
                    if(addVIPCodeDataChangeListener != null){
                        addVIPCodeDataChangeListener.onSuccessful(data);
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i(TAG, "onErrorResponse: " + error.toString());
                if(addVIPCodeDataChangeListener != null){
                    addVIPCodeDataChangeListener.onError(error);
                }
            }
        },object);
    }

    /**
     * 操作购物车
     */
    public void getOptionItemsToCart(String token , String url){
        Map<String, String> headers = new HashMap<>();
        headers.put("token", token);

        Log.i(TAG, "getOptionItemsToCart: " + url);

        postRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (!response.equals("")) {
                    Log.i(TAG, "getOptionItemsToCart: " + response);
                    OptionItemsToCartCode data = gson.fromJson(response , OptionItemsToCartCode.class);
                    if(optionItemsToCartDataChangeListener != null){
                        optionItemsToCartDataChangeListener.onSuccessful(data);
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if(optionItemsToCartDataChangeListener != null){
                    optionItemsToCartDataChangeListener.onError(error);
                }
            }
        } , headers);

    }

    /**
     * 操作购物车
     */
    public void getPaySuccessByNumber(String token , String url){
        Map<String, String> headers = new HashMap<>();
        headers.put("token", token);

        Log.i(TAG, "getPaySuccessByNumber: " + url);

        postRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (!response.equals("")) {
                    Log.i(TAG, "getPaySuccessByNumber: " + response);
                    PaySuccessByNumberCode data = gson.fromJson(response , PaySuccessByNumberCode.class);
                    if(paySuccessByNumberDataChangeListener != null){
                        paySuccessByNumberDataChangeListener.onSuccessful(data);
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if(paySuccessByNumberDataChangeListener != null){
                    paySuccessByNumberDataChangeListener.onError(error);
                }
            }
        } , headers);

    }

    /**
     * 生成订单
     */
    public void getOptionOrders(String token , String url){
        Map<String, String> headers = new HashMap<>();
        headers.put("token", token);

        Log.i(TAG, "getOptionOrders: " + url);

        postRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (!response.equals("")) {
                    Log.i(TAG, "getOptionOrders: " + response);
                    OptionOrdersCode data = gson.fromJson(response , OptionOrdersCode.class);
                    if(optionOrdersDataChangeListener != null){
                        optionOrdersDataChangeListener.onSuccessful(data);
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if(optionOrdersDataChangeListener != null){
                    optionOrdersDataChangeListener.onError(error);
                }
            }
        } , headers);
    }

    /**
     * 获取商品列表
     */
    public void getCartAllItems(String token , String url){
        Map<String, String> headers = new HashMap<>();
        headers.put("token", token);

        Log.i(TAG, "getCartAllItems: " + url);

        postRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (!response.equals("")) {
                    Log.i(TAG, "getCartAllItems: " + response);
                    GetCartAllItemsCode data = gson.fromJson(response , GetCartAllItemsCode.class);
                    if(getCartAllItemsDataChangeListener != null){
                        getCartAllItemsDataChangeListener.onSuccessful(data);
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if(getCartAllItemsDataChangeListener != null){
                    getCartAllItemsDataChangeListener.onError(error);
                }
            }
        } , headers);
    }

    /**
     * 删除订单
     */
    public void getDelOrdersByNumber(String token , String url){
        Map<String, String> headers = new HashMap<>();
        headers.put("token", token);

        Log.i(TAG, "getDelOrdersByNumber: " + url);

        postRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (!response.equals("")) {
                    Log.i(TAG, "getDelOrdersByNumber: " + response);
                    DelOrdersByNumberCode data = gson.fromJson(response , DelOrdersByNumberCode.class);
                    if(delOrdersByNumberDataChangeListener != null){
                        delOrdersByNumberDataChangeListener.onSuccessful(data);
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if(delOrdersByNumberDataChangeListener != null){
                    delOrdersByNumberDataChangeListener.onError(error);
                }
            }
        } , headers);
    }

    public void getGetUserVIPInfo(String uid){
        String url = BaseUrlManager.getUrlForGetVipUser();

        Long id = Long.parseLong(uid);

        Map<String, Object> map = new HashMap<>();
        map.put("uid", id);
        JSONObject object = new JSONObject(map);
        Log.i(TAG, "getGetUserVIPInfo: "+ url+ "\nbody"+object.toString());

        postRequest(url, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                if (!response.toString().equals("")) {
                    Log.i(TAG, "getGetUserVIPInfo: " + response.toString());

                    UserVIPInfo data = gson.fromJson(response.toString() , UserVIPInfo.class);
                    if(getUserVIPInfoDataChangeListener != null){
                        getUserVIPInfoDataChangeListener.onSuccessful(data);
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i(TAG, "onErrorResponse: " + "getGetUserVIPInfo");
                if(getUserVIPInfoDataChangeListener != null){
                    getUserVIPInfoDataChangeListener.onError(error);
                }
            }
        } , object);
    }

    /**
     * 获取商品列表
     */
    public void getAllItems(String uid , int usertype){
        String url = BaseUrlManager.getUrlForGetAllItems();

        Map<String, Object> map = new HashMap<>();
        long id = Long.parseLong(uid);
        map.put("uid" , id);
        map.put("usertype", usertype);
        JSONObject object = new JSONObject(map);
        Log.i(TAG, "getAllItems: " + url +"\nbody"+object.toString());

        postRequest(url, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                if (!response.toString().equals("")) {
                    Log.i(TAG, "getAllItems: " + response.toString());

                    GetAllItemsCode data = gson.fromJson(response.toString() , GetAllItemsCode.class);
                    if(getAllItemsDataChangeListener != null){
                        getAllItemsDataChangeListener.onSuccessful(data);
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if(getAllItemsDataChangeListener != null){
                    getAllItemsDataChangeListener.onError(error);
                }
            }
        } , object);
    }

    /**
     * 获取用户所有的订单
     */
    public void getAllOrdersByUid(String token , String url){
        Map<String, String> headers = new HashMap<>();
        headers.put("token", token);

        Log.i(TAG, "getAllOrdersByUid: " + url);

        postRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (!response.equals("")) {
                    Log.i(TAG, "getAllOrdersByUid: " + response);
                    GetAllOrdersByUidCode data = gson.fromJson(response , GetAllOrdersByUidCode.class);
                    if(getAllOrdersByUidDataChangeListener != null){
                        getAllOrdersByUidDataChangeListener.onSuccessful(data);
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if(getAllOrdersByUidDataChangeListener != null){
                    getAllOrdersByUidDataChangeListener.onError(error);
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

    public void setPaySignCodeDataChangeListener(DataChangeListener<PaySignCode> paySignCodeDataChangeListener){
        this.paySignCodeDataChangeListener = paySignCodeDataChangeListener;
    }

    public void setVipTypeCodeDataChangeListener(DataChangeListener<VipTypeCode> vipTypeCodeDataChangeListener){
        this.vipTypeCodeDataChangeListener = vipTypeCodeDataChangeListener;
    }

    public void setAddVIPCodeDataChangeListener(DataChangeListener<AddVIPCode> addVIPCodeDataChangeListener){
        this.addVIPCodeDataChangeListener = addVIPCodeDataChangeListener;
    }

    public void setOptionItemsToCartDataChangeListener(DataChangeListener<OptionItemsToCartCode> optionItemsToCartDataChangeListener){
        this.optionItemsToCartDataChangeListener = optionItemsToCartDataChangeListener;
    }

    public void setPaySuccessByNumberDataChangeListener(DataChangeListener<PaySuccessByNumberCode> paySuccessByNumberDataChangeListener){
        this.paySuccessByNumberDataChangeListener = paySuccessByNumberDataChangeListener;
    }

    public void setOptionOrdersDataChangeListener(DataChangeListener<OptionOrdersCode> optionOrdersDataChangeListener){
        this.optionOrdersDataChangeListener = optionOrdersDataChangeListener;
    }

    public void setDelOrdersByNumberDataChangeListener(DataChangeListener<DelOrdersByNumberCode> delOrdersByNumberDataChangeListener){
        this.delOrdersByNumberDataChangeListener = delOrdersByNumberDataChangeListener;
    }

    public void setGetAllItemsDataChangeListener(DataChangeListener<GetAllItemsCode> getAllItemsDataChangeListener){
        this.getAllItemsDataChangeListener = getAllItemsDataChangeListener;
    }

    public void setGetAllOrdersByUidDataChangeListener(DataChangeListener<GetAllOrdersByUidCode> getAllOrdersByUidDataChangeListener){
        this.getAllOrdersByUidDataChangeListener = getAllOrdersByUidDataChangeListener;
    }

    public void setGetCartAllItemsDataChangeListener(DataChangeListener<GetCartAllItemsCode> getCartAllItemsDataChangeListener){
        this.getCartAllItemsDataChangeListener = getCartAllItemsDataChangeListener;
    }

    public void setGetUserVIPInfo(DataChangeListener<UserVIPInfo> getUserVIPInfoDataChangeListener){
        this.getUserVIPInfoDataChangeListener = getUserVIPInfoDataChangeListener;
    }
}