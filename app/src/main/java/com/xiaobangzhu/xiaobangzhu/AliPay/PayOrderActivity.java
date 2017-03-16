package com.xiaobangzhu.xiaobangzhu.AliPay;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.alipay.sdk.app.PayTask;
import com.android.volley.VolleyError;
import com.xiaobangzhu.xiaobangzhu.Bean.AddVIPCode;
import com.xiaobangzhu.xiaobangzhu.Interface.DataChangeListener;
import com.xiaobangzhu.xiaobangzhu.MyApplication;
import com.xiaobangzhu.xiaobangzhu.NetworkService.NetRequestManager;
import com.xiaobangzhu.xiaobangzhu.R;
import com.xiaobangzhu.xiaobangzhu.UI.activity.PayMemberSuccessActivity;

import java.util.Map;

/**
 * PayOrderActivity
 *
 * 支付入口
 *
 * @author: MurphySL
 * @time: 2016/11/12 17:01
 */

public class PayOrderActivity extends AppCompatActivity implements View.OnClickListener{

    private static final String TAG  = "PayOrderActivity";

    /** 支付宝支付业务：入参app_id */
    public static final String APPID = "2016093002022464";

    /** 支付宝账户登录授权业务：入参pid值 */
    public static final String PID = " 2088421803105578";

    /** 商户私钥，pkcs8格式 */
    public static final String RSA_PRIVATE = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAPGsisI0ZBSOsaIv" +
            "DcmYbBAo7I9Q9+l1QignCGwhl0qH8uFVNWjLGh7mNcB41e1yZzsMJWedv8deeG2w" +
            "mLPFp/Zc4vby+7kNEeCInd5phMfNCvgr+NHEaTqH+6MdOEgEkde0lhJcisBRN2eb" +
            "nOjkX75EZiWhra3+PJBEHWT3PBxBAgMBAAECgYB+jfsvzZ2nAj5VDszqh633HGcc" +
            "easJDwVw31fSwBhoyB5RD3zjfpAUJDt7qgtkx2b1jdu8kegOsBLiZfrhER1tqh4K" +
            "ZQ1EQQI1lK8ib7GG9prtEtToZbC/R42OGKmkrH+YNs928xJ/F0VOCL9ETT4kaPVk" +
            "4Fa6BL84cNBRgGYkQQJBAPnirqWYTBzko0QI+NoJkNAcWj8aOHImfS2KGj5E4jSW" +
            "ALORKMXdK1ldH+mXl7vRUIFHR9dnOY9zoTmSmbYnorkCQQD3lmwChzLbZ/4pdhah" +
            "PhKMOJRZY65JCi66ExYGIxc72jy/XR1O6FiV+2e0EeLDOWa/5GDU895tekuLlaUj" +
            "f6HJAkEAtrW/e0crR+kJU6K3yj06TGCewAr9AMIRvy1+WM9nEoongyR9SoeXvaSw" +
            "w8za3jKDCbPD3MUWOkSSuxTtHVpHyQJAHM1a81BXEj8eyiP6rfdSoQ1T9LIr7ENK" +
            "/EqMHVqnsUbKGljyt+M/qvL8NW5/OsbKtTgUK7HTBllbwHlAJMkMQQJBAI0s6xB3" +
            "OdzHKZeMbLZmWv1KvgTkVQCNwKCJlsdLCXBlJooaMUIumwrMXpTuav5AkdHeXaTs" +
            "sPqJO5cd9Ck6+NQ=";

    private Button pay;
    TextView cancleBtn;
    TextView publishBtn;

    private String subject ;
    private String body ;
    private int total_fee;

    private int vip_type;
    private int vip_month;

    private static final int SDK_PAY_FLAG = 1;

    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @SuppressWarnings("unused")
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SDK_PAY_FLAG:
                    @SuppressWarnings("unchecked")
                    PayResult payResult = new PayResult((Map<String, String>) msg.obj);
                    /**
                     对于支付结果，请商户依赖服务端的异步通知结果。同步通知结果，仅作为支付结束的通知。
                     */
                    String resultInfo = payResult.getResult();// 同步返回需要验证的信息
                    String resultStatus = payResult.getResultStatus();
                    // 判断resultStatus 为9000则代表支付成功
                    if (TextUtils.equals(resultStatus, "9000")) {
                        // 该笔订单是否真实支付成功，需要依赖服务端的异步通知。
                        Toast.makeText(PayOrderActivity.this, "支付成功", Toast.LENGTH_SHORT).show();
                        if(subject.equals("member")){
                            final int vip = Integer.parseInt(body);
                            vip_type = vip%10;
                            vip_month = vip/10;
                           /* NetRequestManager.getInstance().addVIP(MyApplication.getInstance().getUserToken() , vip_type , vip_month);
                            NetRequestManager.getInstance().setAddVIPCodeDataChangeListener(new DataChangeListener<AddVIPCode>() {
                                @Override
                                public void onSuccessful(AddVIPCode data) {
                                    if (data != null) {
                                        if (data.getStatus() == 0) {
                                            MyApplication.showToastShort("申请会员成功");
                                            Intent intent = new Intent();
                                            intent.setClass(PayOrderActivity.this , PayMemberSuccessActivity.class);
                                            intent.putExtra("type" ,vip_type);
                                            Log.i("123" , vip_type+"");
                                            startActivity(intent);
                                            finish();
                                        }
                                    }
                                }

                                @Override
                                public void onError(VolleyError volleyError) {
                                    MyApplication.dismissProgress();
                                    MyApplication.showToastShort("申请会员失败");
                                }

                                @Override
                                public void onResponseNull() {
                                    MyApplication.dismissProgress();
                                    MyApplication.showToastShort("申请会员失败");
                                }
                            });*/
                        }
                    } else {
                        // 该笔订单真实的支付结果，需要依赖服务端的异步通知。
                        Toast.makeText(PayOrderActivity.this, "支付失败", Toast.LENGTH_SHORT).show();
                    }
                    break;
                default:
                    break;
            }
        };
    };

    /**
     * 支付宝支付业务
     */
    public void payV2() {

        /**
         * 这里只是为了方便直接向商户展示支付宝的整个支付流程；所以Demo中加签过程直接放在客户端完成；
         * 真实App里，privateKey等数据严禁放在客户端，加签过程务必要放在服务端完成；
         * 防止商户私密数据泄露，造成不必要的资金损失，及面临各种安全风险；
         *
         * orderInfo的获取必须来自服务端；
         */
        Map<String, String> params = OrderInfoUtil2_0.buildOrderParamMap(APPID , total_fee , subject , body);
        String orderParam = OrderInfoUtil2_0.buildOrderParam(params);
        String sign = OrderInfoUtil2_0.getSign(params, RSA_PRIVATE);
        final String orderInfo = orderParam + "&" + sign;

        Log.i("test", "payV2: " + orderInfo);
        Runnable payRunnable = new Runnable() {

            @Override
            public void run() {
                PayTask alipay = new PayTask(PayOrderActivity.this);
                Map<String, String> result = alipay.payV2(orderInfo, true);
                Log.i("msp", result.toString());

                Message msg = new Message();
                msg.what = SDK_PAY_FLAG;
                msg.obj = result;
                mHandler.sendMessage(msg);
            }
        };

        Thread payThread = new Thread(payRunnable);
        payThread.start();
    }





    private TextView tvFee;
    private TextView tvFeeS;

    private String orderInfo;

//    private Handler handler = new Handler() {
//        public void handleMessage(final Message msg) {
//            switch (msg.what) {
//                case 3:
//                    new updateOrderStatusByIdTask().execute();
//                    break;
//            }
//        }
//    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay_order);

        initData();
        initView();
        initEvent();
    }

    private void initData() {
        Intent intent = getIntent();

        Bundle bundle = intent.getExtras();
        Log.i("123" , "000000");
        if(bundle != null){
            subject = bundle.getString("subject");
            body = bundle.getString("body");
            total_fee = bundle.getInt("total_fee");

            Log.i(TAG, "initData: " + subject + " " + body + " " + total_fee ) ;

        }

    }

    private void initEvent() {
        cancleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        pay.setOnClickListener(this);
//        if(!"".equals(subject) && !"".equals(body)){
//
//            NetRequestManager.getInstance().getPaySign(MyApplication.getInstance().getUserToken() , subject , body , total_fee);
//            NetRequestManager.getInstance().setPaySignCodeDataChangeListener(new DataChangeListener<PaySignCode>() {
//                @Override
//                public void onSuccessful(PaySignCode data) {
//                    MyApplication.dismissProgress();
//                    Log.i(TAG, "onSuccessful: " + "获取成功");
//                    pay.setBackgroundResource(R.color.orange);
//                    pay.setClickable(true);
//
//                    orderInfo = data.getData();
//                }
//
//                @Override
//                public void onError(VolleyError volleyError) {
//                    MyApplication.dismissProgress();
//                    MyApplication.showToastShort("发布失败");
//                }
//
//                @Override
//                public void onResponseNull() {
//                    MyApplication.dismissProgress();
//                    MyApplication.showToastShort("发布失败");
//                }
//            });
//        }

    }

    private void initView() {
        pay = (Button) findViewById(R.id.btn_to_pay);
        cancleBtn = (TextView) findViewById(R.id.publish_header_cancle);
        publishBtn = (TextView) findViewById(R.id.publish_header_publish);
        tvFee = (TextView) findViewById(R.id.tv_fee);
        tvFeeS = (TextView) findViewById(R.id.tv_fee_s);


        publishBtn.setVisibility(View.GONE);
        tvFee.setText(total_fee + ".00");
        tvFeeS.setText(total_fee + ".00");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_to_pay:
                //orderInfo = "app_id=2016093002022464&timestamp=2016-07-29+16%3A55%3A53&biz_content=%7B%22timeout_express%22%3A%2230m%22%2C%22product_code%22%3A%22QUICK_MSECURITY_PAY%22%2C%22total_amount%22%3A%220.01%22%2C%22subject%22%3A%221%22%2C%22body%22%3A%22%E6%88%91%E6%98%AF%E6%B5%8B%E8%AF%95%E6%95%B0%E6%8D%AE%22%2C%22out_trade_no%22%3A%22121309430218130%22%7D&method=alipay.trade.app.pay&charset=utf-8&version=1.0&sign_type=RSA&sign=epWkbEUDmCs9gCQwF8e%2BgXanE5UgFzB526t%2F7onWOcsMz6NxGWMkaAT7LTuJnrMKzTx2nczxXAUDPt0hDPJWu9p29peGfDhpuqZ6QoZQEHa045cs1lfZcSOsiGc19%2FG3XG6m1uhlpDPKTXIR1dq8Xo7un4NBSZB5hjkH5Buo6Hw%3D";
                //RequestPayment.payByAli(this , orderInfo , handler);

                payV2();
                break;
        }
    }

    /**
     * 修改订单状态(支付完成)
     */
//    class updateOrderStatusByIdTask extends AsyncTask<Void, Void, BaseReplyBean> {
//
//        @Override
//        protected void onPreExecute() {
//            //showLoading();
//        }
//
//        @Override
//        protected BaseReplyBean doInBackground(Void... params) {
//            AccountService service = new AccountService(PayOrderActivity.this);
//            Request_updateOrderStatus_bean updateOrderStatus_bean = new Request_updateOrderStatus_bean();
//           // updateOrderStatus_bean.setOrder_id(order_id);
//            //return service.updateOrderStatusById(updateOrderStatus_bean);
//            return null;
//        }
//
//        @Override
//        protected void onPostExecute(BaseReplyBean result) {
//            //dismissLoading();
//            if (result == null || result.getDesc() == null) {
//                MyApplication.showToastShort("网络出错!");
//                return;
//            }
//            /*if (!result.getStatus().equals(Constant.NET_SUCCESS_CODE)) {
//                String contentStr = result.getDesc();
//                contentStr = StringUtils.isEmpty(contentStr) ? "操作失败！"
//                        : contentStr;
//                Toast.makeText(PayOrderActivity.this ,contentStr , Toast.LENGTH_SHORT).show();
//
//                return;
//            }*/
//            Toast.makeText(PayOrderActivity.this ,"支付成功!" , Toast.LENGTH_SHORT).show();
//
//            finish();
//        }
//    }


}
