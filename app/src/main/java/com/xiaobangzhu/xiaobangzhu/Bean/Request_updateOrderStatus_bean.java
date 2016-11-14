package com.xiaobangzhu.xiaobangzhu.Bean;

/** ����״̬���������ʵ��/��ȡ����״̬����Σ�ֻ��Ҫorder_id�� */
public class Request_updateOrderStatus_bean {

	// ������ʽ{order_id:����id,order_status:״ֵ̬[1.�ȴ����� 2.��֧�� 3.������ 4.�ѷ��� 5.�����
	// 6.����ȡ��]}
	private String order_id;
	private String order_status;

	public String getOrder_id() {
		return order_id;
	}

	public void setOrder_id(String order_id) {
		this.order_id = order_id;
	}

	public String getOrder_status() {
		return order_status;
	}

	public void setOrder_status(String order_status) {
		this.order_status = order_status;
	}

}
