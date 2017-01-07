package com.xiaobangzhu.xiaobangzhu.AliPay;

import android.app.Activity;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.alipay.sdk.app.EnvUtils;

import java.util.Map;

/**
 * 支付管理
 *
 * @author ZhangMY
 *
 */
public class RequestPayment {

	private static final String TAG = "RequestPayment";

	public static void payByAli(final Activity activity, final String orderInfo, final Handler handler) {
		Log.i(TAG, "payByAli: " + orderInfo);

		AliPayManager aliPayManager = new AliPayManager(activity);
		aliPayManager.setOnPayResultListener(new AliPayManager.OnPayResultListener() {

			@Override
			public void onPayResult(Map<String, String> result) {

				Log.i(TAG, "onPayResult: " +  result);
				PayResult payResult = new PayResult(result);
				/**
				 对于支付结果，请商户依赖服务端的异步通知结果。同步通知结果，仅作为支付结束的通知。
				 */
				String resultInfo = payResult.getResult();// 同步返回需要验证的信息
				String resultStatus = payResult.getResultStatus();

				// 判断resultStatus 为9000则代表支付成功
				if (TextUtils.equals(resultStatus, "9000")) {
					// 该笔订单是否真实支付成功，需要依赖服务端的异步通知。
					Log.i(TAG , "支付成功");
					Toast.makeText(activity, "支付成功", Toast.LENGTH_SHORT).show();
				} else {
					// 该笔订单真实的支付结果，需要依赖服务端的异步通知。
					Log.i(TAG , "支付失败");
					Toast.makeText(activity, "支付失败", Toast.LENGTH_SHORT).show();
				}
			}

			/*@Override
			public void onPayResult(final String result) {
				activity.runOnUiThread(new Runnable() {

					@Override
					public void run() {
						Result resultObj = new Result(result);
						String resultStatus = resultObj.resultStatus;

						// 判断resultStatus 为“9000”则代表支付成功，具体状态码代表含义可参考接口文档
						if (TextUtils.equals(resultStatus, "9000")) {
							// 支付成功，修改支付状态
							// RequestUpdateOrderStatus.showOrderList(activity);
							Message msg = new Message();
							msg.what = 3;
							msg.getData().putString("order_id", orderInfo.getOrder_id());
							handler.sendMessage(msg);
						} else {
							// 判断resultStatus 为非“9000”则代表可能支付失败
							// “8000”
							// 代表支付结果因为支付渠道原因或者系统原因还在等待支付结果确认，最终交易是否成功以服务端异步通知为准（小概率状态）
							if (TextUtils.equals(resultStatus, "8000")) {
							} else {
							}
						}
					}
				});
			}*/
		});

		aliPayManager.pay(orderInfo);
	}
}
