package com.xiaobangzhu.xiaobangzhu.NetworkService;

/**
 * Created by WQC on 2016/8/8.
 */
public class BaseHtmlUrlManager {

    private static String htmlAddress = "http://120.76.239.192/";

    //新闻详情
    private static String urlForNewsDetail = htmlAddress + "新闻详情.html#/";
    public static String getUrlForNewsDetail() {
        return urlForNewsDetail;
    }

    private static String urlForActivityDetails = htmlAddress +"活动详情.html#/";
    public static String getUrlForActivityDetails() {
        return urlForActivityDetails;
    }

    //取现
    private static String urlForTakeMoney = htmlAddress + "提现.html#/";
    public static String getUrlForTakeMoney() {
        return urlForTakeMoney;
    }

    //修改密码
    private static String urlForChangePassWord = htmlAddress + "修改密码.html";
    public static String getUrlForChangePassWord() {
        return urlForChangePassWord;
    }

    //我的门票
    private static String urlForMyTicket = htmlAddress + "我的门票.html#/";
    public static String getUrlForMyTicket() {
        return urlForMyTicket;
    }

    //我的订单
    private static String urlForMyOrder = htmlAddress + "我的订单发布.html#/";
    public static String getUrlForMyOrder(){
        return urlForMyOrder;}

    //我的关注
    private static String urlForAttention = htmlAddress + "我的关注.html#/";
    public static String getUrlForAttention() {
        return urlForAttention;
    }

    //我的粉丝
    private static String urlForFans = htmlAddress + "我的粉丝.html#/";
    public static String getUrlForFans() {
        return urlForFans;
    }

    //组队任务列表
    private static String urlForTeamTask = htmlAddress + "tabtest/组队列表一.html#/";
    public static String getUrlForTeamTask() {
        return urlForTeamTask;
    }

    //纠结癌任务列表
    private static String urlForTangleTask = htmlAddress + "tabtest/纠结癌列表一.html#/";
    public static String getUrlForTangleTask() {
        return urlForTangleTask;
    }

    //普通任务列表
    private static String urlForNormalTask = htmlAddress + "tabtest/普通任务列表一.html#/";
    public static String getUrlForNormalTask() {
        return urlForNormalTask;
    }

    //二手任务列表
    private static String urlForSecondTask = htmlAddress + "tabtest/二手列表一.html#/";
    public static String getUrlForSecondTask() {
        return urlForSecondTask;
    }

    //快递列表
    private static String urlForExpressTask = htmlAddress + "tabtest/快递列表一.html#/";
    public static String getUrlForExpressTask() {
        return urlForExpressTask;
    }

    //新闻全部评论
    private static String urlForNewsAllComment = htmlAddress + "新闻全部评论列表.html";
    public static String getUrlForNewsAllComment() {
        return urlForNewsAllComment;
    }


    //填写收货地址
    private static String urlForAddAddress = htmlAddress + "填写收货地址.html";
    public static String getUrlForAddAddress() {
        return urlForAddAddress;
    }

    //安全中心
    public static String urlForSecurityCenter = htmlAddress + "安全中心.html";
    public static String getUrlForSecurityCenter() {
        return urlForSecurityCenter;
    }

    //我的银行卡
    public static String urlForMyBankCard = htmlAddress + "myBankCard.html";
    public static String getUrlForMyBankCard() {
        return urlForMyBankCard;
    }

    //填写卡号
    public static String urlForInputBankCardNum = htmlAddress + "inputBankNum.html";
    public static String getUrlForInputBankCardNum() {
        return urlForInputBankCardNum;
    }

    //填写卡信息
    public static String urlForInputBankCardDetail = htmlAddress + "inputBankDetail.html";
    public static String getUrlForInputBankCardDetail() {
        return urlForInputBankCardDetail;
    }

    //验证手机号
    public static String urlForVerifyPhone = htmlAddress + "checkTelNum.html";
    public static String getUrlForVerifyPhone() {
        return urlForVerifyPhone;
    }

    //设置支付密码
    public static String urlForSetPayPass = htmlAddress + "setPayPass.html";
    public static String getUrlForSetPayPass() {
        return urlForSetPayPass;
    }

    //用户认证
    public static String urlForAuth = htmlAddress + "学生证.html";
    public static String getUrlForAuth() {
        return urlForAuth;
    }

    //还款账单
    private static String urlForDebit = htmlAddress + "借贷.html#/";
    public static String getUrlForDebit(){
        return urlForDebit;
    }

    //更新
    private static String urlForUpdate = htmlAddress + "moblieInterface/getLatestVersion.jhtml";
    public static String getUrlForUpdate(){
        return urlForUpdate;
    }
}
