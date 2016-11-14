package com.xiaobangzhu.xiaobangzhu.AliPay;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Random;
import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.widget.Toast;
import com.alipay.sdk.app.PayTask;

/**
 * 支付宝支付管理
 *
 * @from ZhangMY
 *
 */
public class AliPayManager {

	public static final String PARTNER = "2088421803105578";
	public static final String APPID = "2016093002022464";
	/*public static final String SELLER = "724801676@qq.com";*/
	public static final String RSA_PRIVATE = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDxrIrCNGQUjrGiLw3JmGwQKOyPUPfpdUIoJwhsIZdKh/LhVTVoyxoe5jXAeNXtcmc7DCVnnb/HXnhtsJizxaf2XOL28vu5DRHgiJ3eaYTHzQr4K/jRxGk6h/ujHThIBJHXtJYSXIrAUTdnm5zo5F++RGYloa2t/jyQRB1k9zwcQQIDAQAB";

	private Activity mActivity;

	public AliPayManager(Activity activity) {
		this.mActivity = activity;
	}

	private OnPayResultListener onPayResultListener;

	public void setOnPayResultListener(OnPayResultListener onPayResultListener) {
		this.onPayResultListener = onPayResultListener;
	}

	public interface OnPayResultListener {
		public void onPayResult(String result);
	}

	/**
	 * call alipay sdk pay. 调用SDK支付
	 *
	 */
	public void pay(String subject, String body, String price, String outTradeNo) {
		String orderInfo = getOrderInfo(subject, body, price, outTradeNo);
		String sign = sign(orderInfo);
		try {
			// 仅需对sign 做URL编码
			sign = URLEncoder.encode(sign, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		final String payInfo = orderInfo + "&sign=\"" + sign + "\"&"
				+ getSignType();

		Runnable payRunnable = new Runnable() {

			@Override
			public void run() {
				// 构造PayTask 对象
				PayTask alipay = new PayTask(mActivity);
				String result = null;
				try {
					// 调用支付接口。
					result = alipay.pay(payInfo , true);
				} catch (Exception e) {
					// TODO: handle exception
					handler.obtainMessage(0).sendToTarget();
				}

				// 支付完成之后的调用
				onPayResultListener.onPayResult(result);
			}
		};

		Thread payThread = new Thread(payRunnable);
		payThread.start();
	}

	/**
	 * create the order info. 创建订单信息
	 *
	 */
	public String getOrderInfo(String subject, String body, String price, String outTradeNo) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time =formatter.format(new Date());

		/*// 签约合作者身份ID
		String orderInfo = "app_id=" + "\"" + APPID + "\"";
		// 该笔订单允许的最晚付款时间，逾期将关闭交易。
		orderInfo += "&biz_content=" + "{" + "\"" + "timeout_express" + "\": " + "\"30m\"" + ",";
		//收款支付宝用户ID。 如果该值为空，则默认为商户签约账号对应的支付宝用户ID
		orderInfo += "\"seller_id\":" + "\"" + PARTNER  + "\"" + ",";
		//销售产品码，商家和支付宝签约的产品码，为固定值QUICK_MSECURITY_PAY
		orderInfo += "\"product_code\":" + "\"" + "\"QUICK_MSECURITY_PAY\"" + ",";
		//订单总金额，单位为元，精确到小数点后两位，取值范围[0.01,100000000]
		orderInfo += "\"total_amount\":" + "\"" + price + "\"" +  ",";
		//商品的标题/交易标题/订单标题/订单关键字等
		orderInfo += "\"subject\":" + "\"" + subject + "\"" + ",";
		//对一笔交易的具体描述信息。如果是多种商品，请将商品描述字符串累加传给body
		orderInfo += "\"body\":" + "\"" + body + "\"" + ",";
		//商户网站唯一订单号
		*//*orderInfo += "\"out_trade_no\":"+ "\"" + getOutTradeNo() + "\"" + "}";*//*
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
		orderInfo += "&version=1.0";*/


		// 合作者身份ID
		String orderInfo = "partner=" + "\"" + PARTNER + "\"";

		// ����֧�����˺�
		//orderInfo += "&seller_id=" + "\"" + SELLER + "\"";

		// �̻���վΨһ������
		orderInfo += "&out_trade_no=" + "\"" + outTradeNo + "\"";

		// ��Ʒ����
		orderInfo += "&subject=" + "\"" + subject + "\"";

		// ��Ʒ����
		orderInfo += "&body=" + "\"" + body + "\"";

		// ��Ʒ���
		orderInfo += "&total_fee=" + "\"" + price + "\"";

		// �������첽֪ͨҳ��·��
		orderInfo += "&notify_url="
				+ "\""
				+ "http://121.41.73.196:51017/BlackWhiteStudent/moblieInterface/updateOrderStatusById.jhtml?param={"
				+ "\"" + "order_id" + "\"" + ":" + "\"" + outTradeNo + "\""
				+ "}" + "\"";

		// �ӿ����ƣ� �̶�ֵ
		orderInfo += "&service=\"mobile.securitypay.pay\"";

		// ֧�����ͣ� �̶�ֵ
		orderInfo += "&payment_type=\"1\"";

		// �������룬 �̶�ֵ
		orderInfo += "&_input_charset=\"utf-8\"";

		// ����δ����׵ĳ�ʱʱ��
		// Ĭ��30���ӣ�һ����ʱ���ñʽ��׾ͻ��Զ����رա�
		// ȡֵ��Χ��1m��15d��
		// m-���ӣ�h-Сʱ��d-�죬1c-���죨���۽��׺�ʱ����������0��رգ���
		// �ò�����ֵ������С���㣬��1.5h����ת��Ϊ90m��
		orderInfo += "&it_b_pay=\"30m\"";

		// ֧��������������󣬵�ǰҳ����ת���̻�ָ��ҳ���·�����ɿ�
		// orderInfo += "&return_url=\"m.alipay.com\"";
		// orderInfo += "&return_url=" + "\"" + "" + "\"";

		// �������п�֧���������ô˲���������ǩ���� �̶�ֵ
		// orderInfo += "&paymethod=\"expressGateway\"";

		return orderInfo;
	}

	/**
	 * get the out_trade_no for an order. 获取外部订单号
	 *
	 */
	public String getOutTradeNo() {
		SimpleDateFormat format = new SimpleDateFormat("MMddHHmmss",
				Locale.getDefault());
		Date date = new Date();
		String key = format.format(date);

		Random r = new Random();
		key = key + r.nextInt();
		key = key.substring(0, 15);
		return key;
	}

	/**
	 * sign the order info. 对订单信息进行签名
	 *
	 * @param content
	 *            待签名订单信息
	 */
	public String sign(String content) {
		return SignUtils.sign(content, RSA_PRIVATE);
	}

	/**
	 * get the sign type we use. 获取签名方式
	 *
	 */
	public String getSignType() {
		return "sign_type=\"RSA\"";
	}

	private Handler handler = new Handler() {
		public void handleMessage(final Message msg) {
			switch (msg.what) {
			case 0:
				Toast.makeText(mActivity, "您尚未安装支付宝或者支付宝版本太低，请您先安装或者更新支付宝",
						Toast.LENGTH_LONG).show();
				break;
			default:
				break;
			}
		}
	};
}
