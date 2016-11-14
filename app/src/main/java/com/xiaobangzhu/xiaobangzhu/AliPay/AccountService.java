package com.xiaobangzhu.xiaobangzhu.AliPay;

import android.content.Context;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/** �û�������Ϣ�������񼯺� */
public class AccountService {

	private Context context;

	public AccountService(Context context) {
		this.context = context;
	}
	
	/** ���� */
	/*public BaseReplyBean addWithdrawRecord(Request_addWithdrawRecord_bean data) {
		try {
			Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss")
					.create();
			BasicNameValuePair param = new BasicNameValuePair(
					Constant.REQUEST_PARAM_NAME, gson.toJson(data));
			String result = HttpConnection.postContentByHttpUrlConnection(
					context, RequestUrl.addWithdrawRecord, param);
			System.out.println("����===����"+result);
			return gson.fromJson(result, BaseReplyBean.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	*//** ��ȡ��֤�� *//*
	public BaseReplyBean getCode(Request_getCode_bean data) {
		try {
			Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss")
					.create();
			BasicNameValuePair param = new BasicNameValuePair(
					Constant.REQUEST_PARAM_NAME, gson.toJson(data));
			String result = HttpConnection.postContentByHttpUrlConnection(
					context, RequestUrl.getCode, param);
			return gson.fromJson(result, BaseReplyBean.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	*//** ע�� *//*
	public BaseReplyBean addUser(Request_addUser_bean data) {
		try {
			Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss")
					.create();
			BasicNameValuePair param = new BasicNameValuePair(
					Constant.REQUEST_PARAM_NAME, gson.toJson(data));
			System.out.println("param===>>" + param.getValue());
			String result = HttpConnection.postContentByHttpUrlConnection(
					context, RequestUrl.addUser, param);
			return gson.fromJson(result, BaseReplyBean.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	*//** �û���¼ *//*
	public Return_userData_bean userLogin(Request_userLogin_bean data) {
		try {
			Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss")
					.create();
			BasicNameValuePair param = new BasicNameValuePair(
					Constant.REQUEST_PARAM_NAME, gson.toJson(data));
			String result = HttpConnection.postContentByHttpUrlConnection(
					context, RequestUrl.userLogin, param);
			return gson.fromJson(result, Return_userData_bean.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	*//** �����û���Ϣ *//*
	public BaseReplyBean updateUser(Request_updateUser_bean data) {
		try {
			Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss")
					.create();
			BasicNameValuePair param = new BasicNameValuePair(
					Constant.REQUEST_PARAM_NAME, gson.toJson(data));
			String result = HttpConnection.postContentByHttpUrlConnection(
					context, RequestUrl.updateUser, param);
			return gson.fromJson(result, BaseReplyBean.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	*//** �޸��û����� *//*
	public BaseReplyBean updateUserPassword(Request_updateUserPassword_bean data) {
		try {
			Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss")
					.create();
			BasicNameValuePair param = new BasicNameValuePair(
					Constant.REQUEST_PARAM_NAME, gson.toJson(data));
			System.out.println("��ֵ===>>" + param.getValue());
			String result = HttpConnection.postContentByHttpUrlConnection(
					context, RequestUrl.updateUserPassword, param);
			System.out.println("�޸����뷵��===>>" + result);
			return gson.fromJson(result, BaseReplyBean.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	*//** ��ȡ���ﳵ��Ʒ�б� *//*
	public Return_shoppingcarList_bean getShoppingCar(
			Request_myShoppingCar_bean data) {
		try {
			Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss")
					.create();
			BasicNameValuePair param = new BasicNameValuePair(
					Constant.REQUEST_PARAM_NAME, gson.toJson(data));
			String result = HttpConnection.postContentByHttpUrlConnection(
					context, RequestUrl.myShoppingCar, param);
			return gson.fromJson(result, Return_shoppingcarList_bean.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	*//** ɾ�����ﳵ *//*
	public BaseReplyBean delShoppingCar(Request_delShoppingCar_bean data) {
		try {
			Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss")
					.create();
			BasicNameValuePair param = new BasicNameValuePair(
					Constant.REQUEST_PARAM_NAME, gson.toJson(data));
			System.out.println("param===>>" + param.getValue());
			String result = HttpConnection.postContentByHttpUrlConnection(
					context, RequestUrl.delShoppingCar, param);
			return gson.fromJson(result, BaseReplyBean.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	*//** ��ȡ�ջ���ַ *//*
	public Return_getMyAddress_bean getShippingAddressList(
			Request_getMyAddress_bean data) {
		try {
			Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss")
					.create();
			BasicNameValuePair param = new BasicNameValuePair(
					Constant.REQUEST_PARAM_NAME, gson.toJson(data));
			String result = HttpConnection.postContentByHttpUrlConnection(
					context, RequestUrl.getMyAddress, param);
			System.out.println("��ȡ�ջ���ַ===>>" + result);
			return gson.fromJson(result, Return_getMyAddress_bean.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	*//** �����ջ���ַ *//*
	public BaseReplyBean addShippingAddress(Request_addMyAddress_bean data) {
		try {
			Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss")
					.create();
			BasicNameValuePair param = new BasicNameValuePair(
					Constant.REQUEST_PARAM_NAME, gson.toJson(data));
			String result = HttpConnection.postContentByHttpUrlConnection(
					context, RequestUrl.addMyAddress, param);
			return gson.fromJson(result, BaseReplyBean.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	*//** �޸��ջ���ַ *//*
	public BaseReplyBean updateMyAddress(Request_updateMyAddress_bean data) {
		try {
			Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss")
					.create();
			BasicNameValuePair param = new BasicNameValuePair(
					Constant.REQUEST_PARAM_NAME, gson.toJson(data));
			String result = HttpConnection.postContentByHttpUrlConnection(
					context, RequestUrl.updateMyAddress, param);
			System.out.println("�޸��ջ���ַ===����" + result);
			return gson.fromJson(result, BaseReplyBean.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	*//** ��ȡ�����б� *//*
	public Return_myOrder_bean myOrder(Request_getMyAddress_bean data) {
		try {
			Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss")
					.create();
			BasicNameValuePair param = new BasicNameValuePair(
					Constant.REQUEST_PARAM_NAME, gson.toJson(data));
			String result = HttpConnection.postContentByHttpUrlConnection(
					context, RequestUrl.myOrder, param);
			System.out.println("�����б�===>>" + result);
			return gson.fromJson(result, Return_myOrder_bean.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	*//** ������� *//*
	public BaseReplyBean addSuggest(Request_addSuggest_bean data) {
		try {
			Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss")
					.create();
			BasicNameValuePair param = new BasicNameValuePair(
					Constant.REQUEST_PARAM_NAME, gson.toJson(data));
			String result = HttpConnection.postContentByHttpUrlConnection(
					context, RequestUrl.addSuggest, param);
			return gson.fromJson(result, BaseReplyBean.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	*//** ��ȡ�������� *//*
	public Return_getOrderById_bean getOrderById(
			Request_getPostTaskById_bean data) {
		try {
			Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss")
					.create();
			BasicNameValuePair param = new BasicNameValuePair(
					Constant.REQUEST_PARAM_NAME, gson.toJson(data));
			System.out.println("��ֵ===>>"+param.getValue());
			String result = HttpConnection.postContentByHttpUrlConnection(
					context, RequestUrl.getOrderById, param);
			System.out.println("��������===����"+result);
			return gson.fromJson(result, Return_getOrderById_bean.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	*//**
	 * 
	 * ���Ķ���״̬
	 * 
	 * order_id:����id order_status:״ֵ̬[1.�ȴ����� 2.��֧�� 3.������ 4.�ѷ��� 5.����� 6.����ȡ��
	 * 
	 **//*
	public BaseReplyBean updateOrderStatus(Request_updateOrderStatus_bean data) {
		try {
			Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss")
					.create();
			BasicNameValuePair param = new BasicNameValuePair(
					Constant.REQUEST_PARAM_NAME, gson.toJson(data));
			String result = HttpConnection.postContentByHttpUrlConnection(
					context, RequestUrl.updateOrderStatus, param);
			return gson.fromJson(result, BaseReplyBean.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	*//**
	 * ���Ķ���״̬��֧���ɹ���
	 **//*
	public BaseReplyBean updateOrderStatusById(Request_updateOrderStatus_bean data) {
		try {
			Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss")
					.create();
			BasicNameValuePair param = new BasicNameValuePair(
					Constant.REQUEST_PARAM_NAME, gson.toJson(data));
			System.out.println("��ֵ===>>"+param.getValue());
			String result = HttpConnection.postContentByHttpUrlConnection(
					context, RequestUrl.updateOrderStatusById, param);
			System.out.println("��ɶ���֧������===>>"+result);
			return gson.fromJson(result, BaseReplyBean.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	*//** ��ȡ����״̬�б� *//*
	public Return_getOrderStatus_bean getOrderStatus(
			Request_updateOrderStatus_bean data) {
		try {
			Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss")
					.create();
			BasicNameValuePair param = new BasicNameValuePair(
					Constant.REQUEST_PARAM_NAME, gson.toJson(data));
			String result = HttpConnection.postContentByHttpUrlConnection(
					context, RequestUrl.getOrderStatus, param);
			System.out.println("����״̬===>>"+result);
			return gson.fromJson(result, Return_getOrderStatus_bean.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	*//** ��ȡ�ҵĳ�ֵ��¼ *//*
	public Return_getMyRechargeRecord_bean getMyRechargeRecord(
			Request_getMyAddress_bean data) {
		try {
			Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss")
					.create();
			BasicNameValuePair param = new BasicNameValuePair(
					Constant.REQUEST_PARAM_NAME, gson.toJson(data));
			String result = HttpConnection.postContentByHttpUrlConnection(
					context, RequestUrl.getMyRechargeRecord, param);
			return gson.fromJson(result, Return_getMyRechargeRecord_bean.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	*//** ��ȡxiaotoubi��ֵ��¼ *//*
	public Return_getMyThiefCoinRechargeRecord_bean getMyThiefCoinRechargeRecord(
			Request_getMyAddress_bean data) {
		try {
			Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss")
					.create();
			BasicNameValuePair param = new BasicNameValuePair(
					Constant.REQUEST_PARAM_NAME, gson.toJson(data));
			String result = HttpConnection.postContentByHttpUrlConnection(
					context, RequestUrl.getMyThiefCoinRechargeRecord, param);
			System.out.println("С͵�ҳ�ֵ��¼===>>"+result);
			return gson.fromJson(result, Return_getMyThiefCoinRechargeRecord_bean.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	*//** ��ȡ�ҵ����ּ�¼ *//*
	public Return_getMyWithDrawRecord_bean getMyWithDrawRecord(
			Request_getMyAddress_bean data) {
		try {
			Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss")
					.create();
			BasicNameValuePair param = new BasicNameValuePair(
					Constant.REQUEST_PARAM_NAME, gson.toJson(data));
			String result = HttpConnection.postContentByHttpUrlConnection(
					context, RequestUrl.getMyWithDrawRecord, param);
			System.out.println("���ּ�¼����===>>" + result);
			return gson.fromJson(result, Return_getMyWithDrawRecord_bean.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	*//** �ύ���� *//*
	public Return_submit_order submitOrder(
			Request_submitOrder_bean data) {
		try {
			Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss")
					.create();
			BasicNameValuePair param = new BasicNameValuePair(
					Constant.REQUEST_PARAM_NAME, gson.toJson(data));
			System.out.println("��ֵ===>>"+param.getValue());
			String result = HttpConnection.postContentByHttpUrlConnection(
					context, RequestUrl.submitOrder, param);
			System.out.println("�ύ����===>>" + result);
			return gson.fromJson(result, Return_submit_order.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	*//** ��ӳ�ֵ��¼ *//*
	public BaseReplyBean addRechargeRecord(Request_addRechargeRecord_bean bean) {
		try {
			Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss")
					.create();
			BasicNameValuePair param = new BasicNameValuePair(
					Constant.REQUEST_PARAM_NAME, gson.toJson(bean));
			System.out.println("��ֵ===����" + param.getValue());
			String result = HttpConnection.postContentByHttpUrlConnection(
					context, RequestUrl.addRechargeRecord, param);
			System.out.println("��ӳ�ֵ��¼===����" + result);
			return gson.fromJson(result, BaseReplyBean.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	*//** ���С͵�ҳ�ֵ��¼ *//*
	public BaseReplyBean addThiefCoinRechargeRecord(Request_addRechargeRecord_bean bean) {
		try {
			Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss")
					.create();
			BasicNameValuePair param = new BasicNameValuePair(
					Constant.REQUEST_PARAM_NAME, gson.toJson(bean));
			System.out.println("��ֵ===����" + param.getValue());
			String result = HttpConnection.postContentByHttpUrlConnection(
					context, RequestUrl.addThiefCoinRechargeRecord, param);
			System.out.println("���xiaotoubi��ֵ��¼===����" + result);
			return gson.fromJson(result, BaseReplyBean.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	*//** ��ȡС͵�ҳ�ֵ�һ����� *//*
	public Return_getThiefCoinRechargeSet_bean getThiefCoinRechargeSet() {
		try {
			Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss")
					.create();
			BaseRequestBean bean = new BaseRequestBean();
			BasicNameValuePair param = new BasicNameValuePair(
					Constant.REQUEST_DATA_NAME, gson.toJson(bean));
			String result = HttpConnection.postContentByHttpUrlConnection(
					context, RequestUrl.getThiefCoinRechargeSet, param);
			System.out.println("�һ�����===>>"+result);
			return gson.fromJson(result, Return_getThiefCoinRechargeSet_bean.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}*/
	
}
