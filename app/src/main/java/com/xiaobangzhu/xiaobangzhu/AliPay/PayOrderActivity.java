package com.xiaobangzhu.xiaobangzhu.AliPay;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.xiaobangzhu.xiaobangzhu.Bean.PaySignCode;
import com.xiaobangzhu.xiaobangzhu.Bean.Request_updateOrderStatus_bean;
import com.xiaobangzhu.xiaobangzhu.Interface.DataChangeListener;
import com.xiaobangzhu.xiaobangzhu.MyApplication;
import com.xiaobangzhu.xiaobangzhu.NetworkService.NetRequestManager;
import com.xiaobangzhu.xiaobangzhu.R;

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

    private Button pay;

    private String subject ;
    private String body ;
    private int total_fee;

    private String orderInfo;

    private Handler handler = new Handler() {
        public void handleMessage(final Message msg) {
            switch (msg.what) {
                case 3:
                    new updateOrderStatusByIdTask().execute();
                    break;
            }
        }
    };

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
        if(bundle != null){
            subject = bundle.getString("subject");
            body = bundle.getString("body");
            total_fee = bundle.getInt("total_fee");
            Log.i(TAG, "initData: " + subject + " " + body + " " + total_fee) ;
        }

    }

    private void initEvent() {
        pay.setOnClickListener(this);
        if(!"".equals(subject) && !"".equals(body)){

            NetRequestManager.getInstance().getPaySign(MyApplication.getInstance().getUserToken() , subject , body , total_fee);
            NetRequestManager.getInstance().setPaySignCodeDataChangeListener(new DataChangeListener<PaySignCode>() {
                @Override
                public void onSuccessful(PaySignCode data) {
                    MyApplication.dismissProgress();
                    Log.i(TAG, "onSuccessful: " + "获取成功");
                    pay.setBackgroundResource(R.color.orange);
                    pay.setClickable(true);

                    orderInfo = data.getData();
                }

                @Override
                public void onError(VolleyError volleyError) {
                    MyApplication.dismissProgress();
                    MyApplication.showToastShort("发布失败");
                }

                @Override
                public void onResponseNull() {
                    MyApplication.dismissProgress();
                    MyApplication.showToastShort("发布失败");
                }
            });
        }

    }

    private void initView() {
        pay = (Button) findViewById(R.id.btn_to_pay);

        pay.setClickable(false);
        pay.setBackgroundResource(R.color.base_gray);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_to_pay:
                RequestPayment.payByAli(this , orderInfo , handler);
                break;
        }
    }

    /**
     * 修改订单状态(支付完成)
     */
    class updateOrderStatusByIdTask extends
            AsyncTask<Void, Void, BaseReplyBean> {

        @Override
        protected void onPreExecute() {
            //showLoading();
        }

        @Override
        protected BaseReplyBean doInBackground(Void... params) {
            AccountService service = new AccountService(PayOrderActivity.this);
            Request_updateOrderStatus_bean updateOrderStatus_bean = new Request_updateOrderStatus_bean();
           // updateOrderStatus_bean.setOrder_id(order_id);
            //return service.updateOrderStatusById(updateOrderStatus_bean);
            return null;
        }

        @Override
        protected void onPostExecute(BaseReplyBean result) {
            //dismissLoading();
            if (result == null || result.getDesc() == null) {
                MyApplication.showToastShort("网络出错!");
                return;
            }
            /*if (!result.getStatus().equals(Constant.NET_SUCCESS_CODE)) {
                String contentStr = result.getDesc();
                contentStr = StringUtils.isEmpty(contentStr) ? "操作失败！"
                        : contentStr;
                Toast.makeText(PayOrderActivity.this ,contentStr , Toast.LENGTH_SHORT).show();

                return;
            }*/
            Toast.makeText(PayOrderActivity.this ,"支付成功!" , Toast.LENGTH_SHORT).show();

            finish();
        }
    }


}
