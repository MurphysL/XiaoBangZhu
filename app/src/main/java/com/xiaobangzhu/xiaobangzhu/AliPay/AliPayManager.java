package com.xiaobangzhu.xiaobangzhu.AliPay;

import java.util.Map;
import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

import com.alipay.sdk.app.EnvUtils;
import com.alipay.sdk.app.PayTask;

/**
 * 支付宝支付管理
 */
public class AliPayManager {
	private static final String TAG = "AliPayManager";
	private Activity mActivity;

	public AliPayManager(Activity activity) {
		this.mActivity = activity;
	}

	private OnPayResultListener onPayResultListener;

	public void setOnPayResultListener(OnPayResultListener onPayResultListener) {
		this.onPayResultListener = onPayResultListener;
	}

	public interface OnPayResultListener {
		void onPayResult(Map<String, String> result);
	}

	/**
	 * call alipay sdk pay. 调用SDK支付
	 */
	public void pay(final String orderInfo) {
		Log.i(TAG, "pay: " + orderInfo);

		Runnable payRunnable = new Runnable() {

			@Override
			public void run() {
				PayTask alipay = new PayTask(mActivity);
				//EnvUtils.setEnv(EnvUtils.EnvEnum.SANDBOX);//沙箱环境
				Map<String, String>  result = alipay.payV2(orderInfo , true);

				Log.i(TAG, " run: " + result.toString());

				// 支付完成之后的调用
				onPayResultListener.onPayResult(result);
			}
		};

		Thread payThread = new Thread(payRunnable);
		payThread.start();
	}

}
