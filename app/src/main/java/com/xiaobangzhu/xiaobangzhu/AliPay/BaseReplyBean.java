package com.xiaobangzhu.xiaobangzhu.AliPay;

/** 基础返回类 */
public class BaseReplyBean {

	/** 返回码 */
	private String status;
	/** 返回说明 */
	private String desc;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

}
