package com.xiaobangzhu.xiaobangzhu.NetworkService;

import java.sql.Date;

/**
 * HtmlManager
 *
 * Created by WQC on 2016/8/8.
 */
public class HtmlManager {

    private static HtmlManager mInstance = new HtmlManager();

    private HtmlManager() {
    }

    public static HtmlManager getmInstance() {return mInstance;}

    //新闻详情
    public String getUrlForNewsDetail(String token , int newsId) {
        return BaseHtmlUrlManager.getUrlForNewsDetail() + "?id=" + newsId+"&"+"token="+token;
    }

    public String getUrlForActivityDetail(String userToken, int id) {
        return BaseHtmlUrlManager.getUrlForActivityDetails() + "?id=" + id +"&"+"token="+userToken;
    }
    //取现
    public String getUrlForTakeMoney(String token) {
        return BaseHtmlUrlManager.getUrlForTakeMoney() + "?token=" + token;
    }

    //我的门票
    public String getUrlForMyTicket(String token){
        return BaseHtmlUrlManager.getUrlForMyTicket()+ "?token="+token;
    }

    /**
     * 悬赏部分
     * @param token
     * @param collegeId
     * @return
     */

    //组队任务列表
    public String getUrlForTeam(String token,int collegeId) {
        return BaseHtmlUrlManager.getUrlForTeamTask() + "?id=" + collegeId+"&"+"token="+token;
    }

    //纠结癌任务列表
    public String getUrlForTangle(String token ,int collegeId) {
        return BaseHtmlUrlManager.getUrlForTangleTask() + "?id=" + collegeId+"&"+"token="+token;
    }

    //普通任务列表
    public String getUrlForNormalTask(String token,int collegeId) {
        return BaseHtmlUrlManager.getUrlForNormalTask() + "?id=" + collegeId+"&"+"token="+token;
    }

    //二手任务列表
    public String getUrlForSecond(String token ,int goodsId) {
        return BaseHtmlUrlManager.getUrlForSecondTask() + "?id=" + goodsId+"&"+"token="+token;
    }

    //快递列表
    public String getUrlForExpress(String token, int collegeId) {
        return BaseHtmlUrlManager.getUrlForExpressTask() + "?id=" + collegeId + "&" + "token=" + token;
    }

    //我的订单列表
    public String getUrlForMyOrder(String token){
        return BaseHtmlUrlManager.getUrlForMyOrder() + "?token=" + token;
    }

    //新闻全部评论列表
    public String getUrlForNewsAllComment(String token,int newsId) {
        return BaseHtmlUrlManager.getUrlForNewsAllComment() + "?id=" + newsId + "&token=" + token;
    }

    //添加收货地址
    public String getUtlForAddAddress() {
        return BaseHtmlUrlManager.getUrlForAddAddress();
    }

    //安全中心
    public String getUrlForSecurityCenter() {
        return BaseHtmlUrlManager.getUrlForSecurityCenter();
    }

    //我的银行卡
    public String getUrlForMyBankCard(){
        return BaseHtmlUrlManager.getUrlForMyBankCard();
    }

    //填写银行卡号
    public String getUrlForInputBankCardNum() {
        return BaseHtmlUrlManager.getUrlForInputBankCardNum();
    }

    //填写银行卡信息
    public String getUrlForInputBankCardDetail() {
        return BaseHtmlUrlManager.getUrlForInputBankCardDetail();
    }

    //验证手机号
    public String getUrlForVerifyPhone() {
        return BaseHtmlUrlManager.getUrlForVerifyPhone();
    }

    //设置支付密码
    public String getUrlForSetPayPass() {
        return BaseHtmlUrlManager.getUrlForSetPayPass();
    }

    //用户认证
    public String getUrlForAuth() {
        return BaseHtmlUrlManager.getUrlForAuth();
    }

    //获取我的关注列表
    public String getUrlForAttenList(String token) {
        return BaseHtmlUrlManager.getUrlForAttention() + "?token=" + token;
    }

    //获取我的粉丝列表
    public String getUrlForFans(String token) {
        return BaseHtmlUrlManager.getUrlForFans() + "?token=" + token;
    }

    public String getUrlForDebit(String token) {
        return BaseHtmlUrlManager.getUrlForDebit() + "?token=" + token;
    }

    //获取最新版本 Y
    public String getUrlForUpdate() {
        return BaseHtmlUrlManager.getUrlForUpdate();
    }

    //获取支付宝签名
    public String getUrlForPaySign(String subject , String body ,int total_fee){
        return BaseUrlManager.getUrlForPaySign(subject , body ,total_fee);
    }

    public String getUrlForVipType(){
        return BaseUrlManager.getUrlForGetVipType();
    }

    public String getUrlForAddVIP(int uid , int viptype , int expressnum , Date starttime , Date endtime){
        //return BaseUrlManager.getUrlForAddVIP(vip_id , month);
        return BaseUrlManager.getUrlForAddVIPUser(uid , viptype , expressnum , starttime , endtime);
    }



}
