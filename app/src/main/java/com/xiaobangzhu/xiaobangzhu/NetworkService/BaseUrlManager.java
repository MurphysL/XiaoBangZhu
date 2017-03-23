package com.xiaobangzhu.xiaobangzhu.NetworkService;

import android.util.Log;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.sql.Date;

/**
 * url管理类
 *
 *  支付签名
 *  登录注册、更新用户信息、用户认证
 *  首页图片和新闻，活动列表
 *  任务发布
 *  获取七牛云的token
 *  意见反馈
 *
 *  首页广告
 *  添加会员
 *  获取商品列表
 *  操作购物车接口
 *  生成订单接口、删除订单接口、获取用户所有的订单、获取用户购物车所有商品
 *  订单支付成功接口
 *
 * Created by WQC on 2016/7/26.
 */
public class BaseUrlManager {

    /**
     * 服务器地址
     */
    private static final String serverAddress = "http://120.76.239.192/moblieInterface/";
    //private static final String serverAddress = "http://120.76.239.192/xbzAdmin/moblieInterface/";
    /**
     * 服务器地址2
     */
    private static final String serverAddress3 = "http://120.76.239.192/xbzAdmin/moblieInterface/";

    /**
     * 支付签名
     */
    private static final String urlForPaySign = serverAddress + "signString.jhtml";
    static String getUrlForPaySign(String subject, String body, int total_fee){
        return urlForPaySign + "?param={\"subject\":" + "\"" + subject + "\","
                + "\"body\":" + "\"" + body +"\"," + "\"total_fee\":" + total_fee + "}";
    }

    /**
     * 登录注册
     */
    private static final String urlForVerifyCode = serverAddress + "getCode.jhtml";
    static String getUrlForVerifyCode(String loginId, int type) {
        return urlForVerifyCode + "?param={login_id:" + loginId + "," + "type:" + type + "}";
    }

    private static final String urlForLogin = serverAddress + "userLogin.jhtml";
    static String getUrlForLogin() {
        return urlForLogin;
    }

    private static final String urlForRegiste = serverAddress + "addUser.jhtml";
    static String getUrlForRegiste(String loginId, String password, String sex, int collegeId) {
        return urlForRegiste + "?param={\"login_id\":" + "\"" + loginId + "\"," + "\"password\":" + "\"" + password + "\"," + "\"sex\":" + "\"" + sex +"\"," + "\"college_id\":"  + collegeId + "}";
    }

    private static final String urlForGetCollegeList = serverAddress + "getColleges.jhtml";
    static String getUrlForGetCollegeList() {
        return urlForGetCollegeList;
    }

    /**
     * 首页图片和新闻，活动列表
     */
    private static final String urlForAdPictures = serverAddress + "getAdvertisements.jhtml";
    static String getUrlForAdPictures(int collegeId) {
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
    static String getUrlForGetUserInform() {
        return urlForGetUserInform;
    }

    /**
     * 任务发布
     */
    private static final String urlForAddNormalTask = serverAddress + "addNormalTask.jhtml";
    static String getUrlForAddNormalTask(int collegeId, String title, String description, String picture, String address, int tip, String specify, String end_time) {
        return urlForAddNormalTask + "?param={\"college_id\":" + collegeId + ","
                + "\"title\":" + "\"" + title+ "\","
                + "\"description\":" + "\"" + description + "\","
                + "\"picture\":" + "\"" + picture + "\","
                + "\"" + "address\":" + "\"" + address + "\","
                + "\"tip\":" +tip + ","
                + "\"specify\":" + "\"" + specify + "\","
                + "\"end_time\":" + "\"" + end_time + "\""
                + "}";
    }

    private static final String urlForAddTangleCancer = serverAddress + "addTangleCancer.jhtml";
    static String getUrlForAddTangleCancer(int collegeId, String content, String chooseA, String chooseB) {
        return urlForAddTangleCancer +  "?param={\"college_id\":" +collegeId + "," + "\"content\":" + "\"" + content + "\"," + "\"choice1\":" + "\"" + chooseA + "\"," + "\"" + "choice2\":" + "\"" + chooseB + "\"" + "}";
    }

    private static  final String urlForAddSecondTask = serverAddress + "addSecondHand.jhtml";
    static String getUrlForAddSecondTask(int collegeId, int price, String title, String desc, String imag1, String imag2, String imag3){
        return urlForAddSecondTask + "?param={\"college_id\":"+collegeId+",\"title\":\""+title+"\",\"price\":"+price+",\"description\":\""+desc+"\",\"picture1\":\""+imag1+"\",\"picture2\":\""+""+"\",\"picture3\":\""+""+"\"}";
    }

    private static final String urlForAddTeamTask = serverAddress + "addFindGroup.jhtml";
    static String getUrlForAddTeamTask(int collegeId, String desc, String image, String address, int num, String specify){
        return urlForAddTeamTask +"?param={\"college_id\":"+collegeId+",\"description\":\""+desc+"\",\"picture\":\""+image+"\",\"address\":\""+address+"\",\"num\":"+num+",\"specify\":\""+specify+"\"}";
    }

    private  static final String urlForAddExpressage = serverAddress + "addExpressage.jhtml";
    static String getUrlForAddExpressage(int collegeId, String name, int expressId, String address, int tip, String phoneTail, int type, String limitTime){
        return urlForAddExpressage + "?param={\"college_id\":"+collegeId+",\"name\":\""+name+"\",\"express_id\":"+expressId+",\"address\":\""+address+"\",\"tip\":"+tip+",\"phone_tail\":\""+phoneTail+"\",\"type\":"+type+",\"limit_time\":\""+limitTime+"\"}";
    }


    private static final String urlForAddActivity = serverAddress + "addActivity.jhtml";
    static String geturlForAddActivity(int collegeId, String title, String address, String startTime, String endTime, String content, String picture, String poster, String tags, int num) {
        return urlForAddActivity + "?param={\"content\":\""+content+"\",\"title\":\""+title+"\",\"college_id\":"+collegeId+",\"begin_time\":\""+startTime+"\",\"end_time\":\""+endTime+"\",\"tags\":\""+tags+"\",\"poster\":\""+poster+"\",\"address\":\""+address+"\",\"person_num\":"+num+",\"apply_endtime\":\""+endTime+"\",\"picture\":\""+picture+"\"}";
    }

    private static final String urlForGetExpressCompany = serverAddress + "getExpress.jhtml";
    static String getUrlForGetExpressCompany() {
        return urlForGetExpressCompany;
    }

    /**
     * 用户认证
     */
    private static final String urlForAuth = serverAddress + "addUserAuthenticate.jhtml";
    static String getUrlForAuth(String name, String college, String education, String picture, String identity, int eduStartDate){
        return urlForAuth + "?param={\"name\":\""+name+"\",\"college\":\""+college+"\",\"education\":\""+education+"\",\"picture\":\""+picture+"\",\"identity\":\""+identity+"\",\"eduStartDate\":"+eduStartDate+"}" ;
    }


    /**
     * 获取七牛云的token
     */
    private static final String urlForGetQiNiuYunToken = serverAddress + "getQiuNiuToken.jhtml";
    static String getUrlForGetQiNiuYunToken(){
        return urlForGetQiNiuYunToken;
    }

    /**
     * 意见反馈
     */
    private static final String urlForAddSuggest = serverAddress + "addSuggestion.jhtml";
    static String getUrlForAddSuggest(String content, String contact) {
        return urlForAddSuggest + "?param={\"content\":\""+content+"\",\"contact\":\""+contact+"\"}";
    }


    /**
     * 更新用户信息
     */
    private static final String urlForUpdateUser = serverAddress + "updateUser.jhtml";
    static String getUrlForUpdateUser(String signature, String major, String header, String sex, int collegeId, String nickName) {
        return urlForUpdateUser + "?param={\"signature\":\""+signature+"\",\"major\":\""+major+"\",\"header\":\""+header+"\",\"sex\":\""+sex+"\",\"college_id\":"+collegeId+",\"nick_name\":\""+nickName+"\"}";
    }

    /**
     * 首页广告
     */
    private static final String urlForInitImage = serverAddress + "getInitPicture.jhtml";
    static String getUrlForInitImage() {
        return urlForInitImage;
    }

    /**
     * 订单信息
     */
    private static final String urlForOrderInfo = serverAddress + "";
    public static String getUrlForOrderInfo() {
        return urlForOrderInfo;
    }

    /**
     * 会员信息
     */
    private static final String urlForGetVipType = serverAddress +"getVipType.jhtml";
    static String getUrlForGetVipType(){
        return urlForGetVipType;
    }

   /* private static final String urlForAddVIP = serverAddress+"addVIP.jhtml";
    public static String getUrlForAddVIP(int vip_id , int month){
        return urlForAddVIP + "?param={\"vip_id\":" +vip_id +",\"month\":" + month +"}";
    }*/

    /**
     * 添加会员
     */
    private static final String urlForAddVIPUser = serverAddress3+"addVIPUser.jhtml";
    static String getUrlForAddVIPUser(){
        return urlForAddVIPUser;
    }

    /**
     * 操作购物车接口
     */
    private static final String urlForOptionItemsToCart = serverAddress3+"optionItemsToCart.jhtml";
    static String getUrlForOptionItemsToCart(int uid , int usertype , int itemsid , int itemsnum){
        return urlForOptionItemsToCart + "?param={\"uid\":" +uid +",\"usertype\":" + usertype +",\"itemsid\":" + itemsid+
                ",\"itemsnum\":" + itemsnum +"}";
    }

    /**
     * 订单支付成功接口
     */
    private static final String urlForPaySuccessByNumber = serverAddress3+"paySuccessByNumber.jhtml";
    static String getUrlForPaySuccessByNumber(int number){
        return urlForPaySuccessByNumber + "?param={\"number\":" +number+"}";
    }

    /**
     * 获取商品列表
     */
    private static final String urlForGetAllItems = serverAddress3+"getAllItems.jhtml";
    public static String getUrlForGetAllItems(){
        return urlForGetAllItems;
    }

    /**
     * 获取用户购物车所有商品
     */
    private static final String urlForGetCartAllItems = serverAddress3+"getCartAllItems.jhtml";
    public static String getUrlForGetCartAllItems(int uid){
        return urlForGetCartAllItems + "?param={\"uid\":" +uid+"}";
    }

    /**
     * 获取用户所有的订单
     */
    private static final String urlForGetAllOrdersByUid = serverAddress3 +"getAllOrdersByUid.jhtml";
    static String getUrlForGetAllOrdersByUid(int uid){
        return urlForGetAllOrdersByUid + "?param={\"uid\":" +uid+"}";
    }

    /**
     * 删除订单接口
     */
    private static final String urlForDelOrdersByNumber = serverAddress3 +"delOrdersByNumber.jhtml";
    static String getUrlForDelOrdersByNumber(int number){
        return urlForDelOrdersByNumber + "?param={\"number\":" +number+"}";
    }

    /**
     * 生成订单接口
     */
    private static final String urlForOptionOrders = serverAddress3 + "optionOrders.jhtml";
    static String getUrlForOptionOrders(int uid , String note){
        try {
            byte[] b = note.getBytes("UTF-8");
            String s = new String(b , "Unicode");
            Log.i("SS", "urlForOptionOrders: " + s);
            return urlForOptionOrders+ "?param={\"uid\":" +uid+",\"note\":" + s +"}";
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }

    }



}
