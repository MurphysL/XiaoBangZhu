package com.xiaobangzhu.xiaobangzhu.AliPay;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;

import com.xiaobangzhu.xiaobangzhu.Bean.OrderDetailBean;

/**
 * 支付管理
 *
 * @author ZhangMY
 *
 */
public class RequestPayment {

	/**
	 * 支付宝支付
	 *
	 * @param activity
	 * @param orderInfo
	 */
	public static void payByAli(final Activity activity,
								final OrderDetailBean orderInfo, final Handler handler) {

		AliPayManager aliPayManager = new AliPayManager(activity);
		aliPayManager.setOnPayResultListener(new AliPayManager.OnPayResultListener() {

			@Override
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
			}
		});
		aliPayManager.pay("黑白小生", "黑白小生", orderInfo.getTotal_price() + "",
				orderInfo.getOrdernum());
	}
}
