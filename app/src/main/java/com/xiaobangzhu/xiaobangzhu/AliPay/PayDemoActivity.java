package com.xiaobangzhu.xiaobangzhu.AliPay;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.alipay.sdk.app.PayTask;
import com.android.volley.VolleyError;
import com.xiaobangzhu.xiaobangzhu.Bean.AdPictures;
import com.xiaobangzhu.xiaobangzhu.Interface.DataChangeListener;
import com.xiaobangzhu.xiaobangzhu.MyApplication;
import com.xiaobangzhu.xiaobangzhu.NetworkService.NetRequestManager;
import com.xiaobangzhu.xiaobangzhu.R;

/**
 *  重要说明:
 *  
 *  这里只是为了方便直接向商户展示支付宝的整个支付流程；所以Demo中加签过程直接放在客户端完成；
 *  真实App里，privateKey等数据严禁放在客户端，加签过程务必要放在服务端完成；
 *  防止商户私密数据泄露，造成不必要的资金损失，及面临各种安全风险； 
 */
public class PayDemoActivity extends FragmentActivity {
	
	/** 支付宝支付业务：入参app_id */
	public static final String APPID = "2016093002022464";
	
	/** 支付宝账户登录授权业务：入参pid值 */
	public static final String PID = "2088421803105578";
	/** 支付宝账户登录授权业务：入参target_id值 */
	public static final String TARGET_ID = "";
	
	private static final int SDK_PAY_FLAG = 1;
	private static final int SDK_AUTH_FLAG = 2;

	@SuppressLint("HandlerLeak")
	private Handler mHandler = new Handler() {
		@SuppressWarnings("unused")
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case SDK_PAY_FLAG: {
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
					Toast.makeText(PayDemoActivity.this, "支付成功", Toast.LENGTH_SHORT).show();
				} else {
					// 该笔订单真实的支付结果，需要依赖服务端的异步通知。
					Toast.makeText(PayDemoActivity.this, "支付失败", Toast.LENGTH_SHORT).show();
				}
				break;
			}
			case SDK_AUTH_FLAG: {
				@SuppressWarnings("unchecked")
				AuthResult authResult = new AuthResult((Map<String, String>) msg.obj, true);
				String resultStatus = authResult.getResultStatus();

				// 判断resultStatus 为“9000”且result_code
				// 为“200”则代表授权成功，具体状态码代表含义可参考授权接口文档
				if (TextUtils.equals(resultStatus, "9000") && TextUtils.equals(authResult.getResultCode(), "200")) {
					// 获取alipay_open_id，调支付时作为参数extern_token 的value
					// 传入，则支付账户为该授权账户
					Toast.makeText(PayDemoActivity.this,
							"授权成功\n" + String.format("authCode:%s", authResult.getAuthCode()), Toast.LENGTH_SHORT)
							.show();
				} else {
					// 其他状态值则为授权失败
					Toast.makeText(PayDemoActivity.this,
							"授权失败" + String.format("authCode:%s", authResult.getAuthCode()), Toast.LENGTH_SHORT).show();

				}
				break;
			}
			default:
				break;
			}
		};
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.pay_main);
	}
	
	/**
	 * 支付宝支付业务
	 * 
	 * @param v
	 */
	public void payV2(View v) {
		if (TextUtils.isEmpty(APPID)) {
			new AlertDialog.Builder(this).setTitle("警告").setMessage("需要配置APPID")
					.setPositiveButton("确定", new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialoginterface, int i) {
							//
							finish();
						}
					}).show();
			return;
		}
	
		/**
		 * 这里只是为了方便直接向商户展示支付宝的整个支付流程；所以Demo中加签过程直接放在客户端完成；
		 * 真实App里，privateKey等数据严禁放在客户端，加签过程务必要放在服务端完成；
		 * 防止商户私密数据泄露，造成不必要的资金损失，及面临各种安全风险； 
		 * 
		 * orderInfo的获取必须来自服务端；
		 */

		/*Map<String, String> params = OrderInfoUtil2_0.buildOrderParamMap(APPID);
		String orderParam = OrderInfoUtil2_0.buildOrderParam(params);
		String sign = OrderInfoUtil2_0.getSign(params, RSA_PRIVATE);*/
		NetRequestManager.getInstance().getAdPictures(MyApplication.getInstance().getUserCollegeId(), MyApplication.getInstance().getUserToken());
		NetRequestManager.getInstance().setAdPicturesListener(new DataChangeListener<AdPictures>() {
			@Override
			public void onSuccessful(AdPictures data) {
				if (data != null) {

				}
			}
			@Override
			public void onError(VolleyError volleyError) {
				MyApplication.showDialog(PayDemoActivity.this,"请检查网络状况");
			}
			@Override
			public void onResponseNull() {
				MyApplication.showDialog(PayDemoActivity.this,"请检查网络状况");
			}
		});

		//final String orderInfo = orderParam + "&" + sign;

		//调用支付接口
		/*Runnable payRunnable = new Runnable() {

			@Override
			public void run() {
				PayTask alipay = new PayTask(PayDemoActivity.this);
				Map<String, String> result = alipay.payV2(orderInfo, true);
				Log.i("msp", result.toString());
				
				Message msg = new Message();
				msg.what = SDK_PAY_FLAG;
				msg.obj = result;
				mHandler.sendMessage(msg);
			}
		};

		Thread payThread = new Thread(payRunnable);
		payThread.start();*/
	}

	/**
	 * 支付宝账户授权业务
	 * 
	 * @param v
	 */
	public void authV2(View v) {
/*
		*//**
		 * 这里只是为了方便直接向商户展示支付宝的整个支付流程；所以Demo中加签过程直接放在客户端完成；
		 * 真实App里，privateKey等数据严禁放在客户端，加签过程务必要放在服务端完成；
		 * 防止商户私密数据泄露，造成不必要的资金损失，及面临各种安全风险； 
		 * 
		 * authInfo的获取必须来自服务端；
		 *//*
		Map<String, String> authInfoMap = OrderInfoUtil2_0.buildAuthInfoMap(PID, APPID, TARGET_ID);
		String info = OrderInfoUtil2_0.buildOrderParam(authInfoMap);
		String sign = OrderInfoUtil2_0.getSign(authInfoMap, RSA_PRIVATE);
		final String authInfo = info + "&" + sign;
		Runnable authRunnable = new Runnable() {

			@Override
			public void run() {
				// 构造AuthTask 对象
				AuthTask authTask = new AuthTask(PayDemoActivity.this);
				// 调用授权接口，获取授权结果
				Map<String, String> result = authTask.authV2(authInfo, true);

				Message msg = new Message();
				msg.what = SDK_AUTH_FLAG;
				msg.obj = result;
				mHandler.sendMessage(msg);
			}
		};

		// 必须异步调用
		Thread authThread = new Thread(authRunnable);
		authThread.start();*/
	}
	
	/**
	 * get the sdk version. 获取SDK版本号
	 * 
	 */
	public void getSDKVersion() {
		PayTask payTask = new PayTask(this);
		String version = payTask.getVersion();
		Toast.makeText(this, version, Toast.LENGTH_SHORT).show();
	}

	/**
	 * 生成订单数据
	 * @param subject 标题
	 * @param body 内容
	 * @param price 赏金
     * @param url 服务器
     * @return
     */
	public String getOrderInfo(String subject, String body, String price , String url) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time =formatter.format(new Date());

		// 签约合作者身份ID
		String orderInfo = "app_id=" + "\"" + APPID + "\"";
		// 该笔订单允许的最晚付款时间，逾期将关闭交易。
		orderInfo += "&biz_content=" + "{" + "\"" + "timeout_express" + "\": " + "\"30m\"" + ",";
		//收款支付宝用户ID。 如果该值为空，则默认为商户签约账号对应的支付宝用户ID
		orderInfo += "\"seller_id\":" + "\"" + PID  + "\"" + ",";
		//销售产品码，商家和支付宝签约的产品码，为固定值QUICK_MSECURITY_PAY
		orderInfo += "\"product_code\":" + "\"" + "\"QUICK_MSECURITY_PAY\"" + ",";
		//订单总金额，单位为元，精确到小数点后两位，取值范围[0.01,100000000]
		orderInfo += "\"total_amount\":" + "\"" + price + "\"" +  ",";
		//商品的标题/交易标题/订单标题/订单关键字等
		orderInfo += "\"subject\":" + "\"" + subject + "\"" + ",";
		//对一笔交易的具体描述信息。如果是多种商品，请将商品描述字符串累加传给body
		orderInfo += "\"body\":" + "\"" + body + "\"" + ",";
		//商户网站唯一订单号
		/*orderInfo += "\"out_trade_no\":"+ "\"" + getOutTradeNo() + "\"" + "}";*/
		// 参数编码， 固定值
		orderInfo += "&charset=utf-8";
		// 编码类型， 固定值
		orderInfo += "&format=json";

		//接口名称
		orderInfo += "&method=alipay.trade.pay";
		//支付宝服务器主动通知商户服务器里指定的页面http/https路径。建议商户使用https
		orderInfo += "&notify_url=" + url;
		//商户生成签名字符串所使用的签名算法类型，目前支持RSA
		orderInfo += "&sign_type=" + "RSA";
		//发送请求的时间，格式"yyyy-MM-dd HH:mm:ss"
		orderInfo += "&timestamp=" + time;
		//调用的接口版本，固定为：1.0
		orderInfo += "&version=1.0";

// extern_token为经过快登授权获取到的alipay_open_id,带上此参数用户将使用授权的账户进行支付
// orderInfo += "&extern_token=" + "\"" + extern_token + "\"";


// 支付宝处理完请求后，当前页面跳转到商户指定页面的路径，可空
		orderInfo += "&return_url=\"m.alipay.com\"";


// 调用银行卡支付，需配置此参数，参与签名， 固定值 （需要签约《无线银行卡快捷支付》才能使用）
// orderInfo += "&paymethod=\"expressGateway\"";


		return orderInfo;
	}

}
