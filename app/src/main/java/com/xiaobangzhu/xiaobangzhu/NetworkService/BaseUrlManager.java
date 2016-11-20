package com.xiaobangzhu.xiaobangzhu.NetworkService;

import android.util.Log;

/**
 * url管理类
 * Created by WQC on 2016/7/26.
 * 已经全部写完的请求:获取验证码，首页图片，用户登录，用户注册，添加纠结癌，获取个人基本信息
 *
 *
 * 尚未写完的请求：添加普通任务，首页新闻列表，首页为活动列表，快递发布，二手发布
 */
public class BaseUrlManager {

    /**
     * 服务器地址
     */
    private static final String serverAddress = "http://120.76.239.192/moblieInterface/";
    private static String getServerAddress() {
        return serverAddress;
    }

    /**
     * 支付签名
     */
    private static String urlForPaySign = serverAddress + "signString.jhtml";
    public static String getUrlForPaySign(String subject , String body ,int total_fee){
        return urlForPaySign + "?param={\"subject\":" + "\"" + subject + "\","
                + "\"body\":" + "\"" + body +"\"," + "\"total_fee\":" + total_fee + "}";
    }

    /**
     * 登录注册
     */
    private static final String urlForVerifyCode = serverAddress + "getCode.jhtml";
    public static String getUrlForVerifyCode(String loginId, int type) {
        return urlForVerifyCode + "?param={login_id:" + loginId + "," + "type:" + type + "}";
    }

    private static final String urlForLogin = serverAddress + "userLogin.jhtml";
    public static String getUrlForLogin() {
        return urlForLogin;
    }

    private static final String urlForRegiste = serverAddress + "addUser.jhtml";
    public static String getUrlForRegiste(String loginId, String password, String sex,int collegeId) {
        return urlForRegiste + "?param={\"login_id\":" + "\"" + loginId + "\"," + "\"password\":" + "\"" + password + "\"," + "\"sex\":" + "\"" + sex +"\"," + "\"college_id\":"  + collegeId + "}";
    }

    private static final String urlForGetCollegeList = serverAddress + "getColleges.jhtml";
    public static final String getUrlForGetCollegeList() {
        return urlForGetCollegeList;
    }

    /**
     * 首页图片和新闻，活动列表
     */
    private static final String urlForAdPictures = serverAddress + "getAdvertisements.jhtml";
    public static String getUrlForAdPictures(int collegeId) {
        return urlForAdPictures+"?param={\"college_id\":" + +collegeId + "}";
    }

    private static final String urlForGetNewsList = serverAddress + "getNewsList.jhtml";
    public static String getUrlForGetNewsList(int collegeId, int index, int pageSize) {
        Log.i("data2", "getUrlForGetActivityList: " + index + " " +pageSize);
        return urlForGetNewsList + "?param={\"college_id\":" + +collegeId + "," + "\"index\":"  + index + "," + "\"pageSize\":"  + pageSize  + "}";
    }

    private static final String urlForGetActivityList = serverAddress + "getActivities.jhtml";
    public static String getUrlForGetActivityList(int collegeId,int index,int pageSize){
        return urlForGetActivityList +"?param={\"college_id\":" + +collegeId + "," + "\"index\":"  + index + "," + "\"pageSize\":"  + pageSize  + "}";
    }

    /**
     * 获取用户基本信息
     */
    private static final String urlForGetUserInform = serverAddress + "getUserData.jhtml";
    public static String getUrlForGetUserInform() {
        return urlForGetUserInform;
    }

    /**
     * 任务发布
     */
    private static final String urlForAddNormalTask = serverAddress + "addNormalTask.jhtml";
    public static String getUrlForAddNormalTask(int collegeId, String title, String description, String picture, String address, int tip, String specify, String end_time) {
        return urlForAddNormalTask + "?param={\"college_id\":" + collegeId + ","
                + "\"title\":" + "\"" + "" + "\","
                + "\"description\":" + "\"" + description + "\","
                + "\"picture\":" + "\"" + picture + "\","
                + "\"" + "address\":" + "\"" + address + "\","
                + "\"tip\":" +tip + ","
                + "\"specify\":" + "\"" + specify + "\","
                + "\"end_time\":" + "\"" + end_time + "\""
                + "}";
    }

    private static final String urlForAddTangleCancer = serverAddress + "addTangleCancer.jhtml";
    public static String getUrlForAddTangleCancer(int collegeId,String content,String chooseA,String chooseB) {
        return urlForAddTangleCancer +  "?param={\"college_id\":" +collegeId + "," + "\"content\":" + "\"" + content + "\"," + "\"choice1\":" + "\"" + chooseA + "\"," + "\"" + "choice2\":" + "\"" + chooseB + "\"" + "}";
    }

    private static  final String urlForAddSecondTask = serverAddress + "addSecondHand.jhtml";
    public static String getUrlForAddSecondTask(int collegeId,int price,String title,String desc,String imag1,String imag2,String imag3){
        return urlForAddSecondTask + "?param={\"college_id\":"+collegeId+",\"title\":\""+title+"\",\"price\":"+price+",\"description\":\""+desc+"\",\"picture1\":\""+imag1+"\",\"picture2\":\""+""+"\",\"picture3\":\""+""+"\"}";
    }

    private static final String urlForAddTeamTask = serverAddress + "addFindGroup.jhtml";
    public static String getUrlForAddTeamTask(int collegeId,String desc,String image,String address,int num,String specify){
        return urlForAddTeamTask +"?param={\"college_id\":"+collegeId+",\"description\":\""+desc+"\",\"picture\":\""+image+"\",\"address\":\""+address+"\",\"num\":"+num+",\"specify\":\""+specify+"\"}";
    }

    private  static final String urlForAddExpressage = serverAddress + "addExpressage.jhtml";
    public static String getUrlForAddExpressage(int collegeId, String name, int expressId, String address, int tip, String phoneTail, int type, String limitTime){
        return urlForAddExpressage + "?param={\"college_id\":"+collegeId+",\"name\":\""+name+"\",\"express_id\":"+expressId+",\"address\":\""+address+"\",\"tip\":"+tip+",\"phone_tail\":\""+phoneTail+"\",\"type\":"+type+",\"limit_time\":\""+limitTime+"\"}";
    }


    private static final String urlForAddActivity = serverAddress + "addActivity.jhtml";
    public static String geturlForAddActivity(int collegeId, String title, String address, String startTime, String endTime, String content, String picture, String poster, String tags,int num) {
        return urlForAddActivity + "?param={\"content\":\""+content+"\",\"title\":\""+title+"\",\"college_id\":"+collegeId+",\"begin_time\":\""+startTime+"\",\"end_time\":\""+endTime+"\",\"tags\":\""+tags+"\",\"poster\":\""+poster+"\",\"address\":\""+address+"\",\"person_num\":"+num+",\"apply_endtime\":\""+endTime+"\",\"picture\":\""+picture+"\"}";
    }

    private static final String urlForGetExpressCompany = serverAddress + "getExpress.jhtml";
    public static String getUrlForGetExpressCompany() {
        return urlForGetExpressCompany;
    }

    /**
     * 用户认证
     */
    private static final String urlForAuth = serverAddress + "addUserAuthenticate.jhtml";
    public static String getUrlForAuth(String name,String college,String education,String picture,String identity,int eduStartDate){
        return urlForAuth + "?param={\"name\":\""+name+"\",\"college\":\""+college+"\",\"education\":\""+education+"\",\"picture\":\""+picture+"\",\"identity\":\""+identity+"\",\"eduStartDate\":"+eduStartDate+"}" ;
    }


    /**
     * 获取七牛云的token
     */
    private static final String urlForGetQiNiuYunToken = serverAddress + "getQiuNiuToken.jhtml";
    public static String getUrlForGetQiNiuYunToken(){
        return urlForGetQiNiuYunToken;
    }

    /**
     * 意见反馈
     */
    private static final String urlForAddSuggest = serverAddress + "addSuggestion.jhtml";
    public static String getUrlForAddSuggest(String content, String contact) {
        return urlForAddSuggest + "?param={\"content\":\""+content+"\",\"contact\":\""+contact+"\"}";
    }


    /**
     * 更新用户信息
     */
    private static final String urlForUpdateUser = serverAddress + "updateUser.jhtml";
    public static String getUrlForUpdateUser(String signature,String major,String header,String sex,int collegeId,String nickName) {
        return urlForUpdateUser + "?param={\"signature\":\""+signature+"\",\"major\":\""+major+"\",\"header\":\""+header+"\",\"sex\":\""+sex+"\",\"college_id\":"+collegeId+",\"nick_name\":\""+nickName+"\"}";
    }

    /**
     * 首页广告
     */
    private static final String urlForInitImage = serverAddress + "getInitPicture.jhtml";
    public static String getUrlForInitImage() {
        return urlForInitImage;
    }

    /**
     * 订单信息
     */
    private static final String urlForOrderInfo = serverAddress + "";
    public static String getUrlForOrderInfo() {
        return urlForOrderInfo;
    }

}
