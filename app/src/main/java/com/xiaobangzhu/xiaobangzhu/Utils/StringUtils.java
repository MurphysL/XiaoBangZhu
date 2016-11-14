package com.xiaobangzhu.xiaobangzhu.Utils;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Locale;

public class StringUtils {
	/**
	 * �ж��ַ����Ƿ�ǿ�
	 * 
	 * @param str
	 * @return �ַ�����Ϊ��ʱ����false, �ַ���Ϊnull, "", "  "�����ʱ����true
	 */
	public static boolean isEmpty(String str) {
		if (str != null && str.trim().length() > 0) {
			return false;
		}
		return true;
	}
	
	public static String bigDecimal2String(BigDecimal b){
		DecimalFormat a = new DecimalFormat("#,##0.00");
		return a.format(b);
	}
	
	public static BigDecimal string2BigDecimal(String str){
		BigDecimal b = new BigDecimal("0");
		DecimalFormat a = new DecimalFormat("#,##0.00");
		try {
			if(StringUtils.isEmpty(str)){
				return b;
			}
			b = new BigDecimal(a.parseObject(str).toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return b;
	}

	public static String bigDouble2String(double d) {
		DecimalFormat a = new DecimalFormat("#,##0.00");
		return a.format(d);
	}

	public static double string2Double(String str) {
		double result = 0.0;
		DecimalFormat a = new DecimalFormat("#,##0.00");
		try {
			if(StringUtils.isEmpty(str)){
				return result;
			}
			result = a.parse(str).doubleValue();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * ���stringΪnull���򷵻�""
	 * @return
	 */
	public static String filterNullStr(String str){
		return str == null ? "" : str;
	}
	
//	/**
//	 * ��ȡ����ƴ��
//	 * 
//	 * @param input
//	 * @return
//	 */
//	public static String getPinYin(String input) {
//		String pinyinStr = PinyinUtils.toPinyin(FxtxAppManager.getContext(), input);
//		return isEmpty(pinyinStr) ? "" : pinyinStr.toUpperCase(Locale.getDefault());
//	}
	
	/**
	 * ��ȡƴ������ĸ(��д),�������Ӣ����ĸ,�򷵻�!
	 * @param sortKey
	 * @return
	 */
	public static String getFirstSpell(String sortKey){
		char c = StringUtils.isEmpty(sortKey) ? '!' : sortKey.charAt(0);
		if((c >= 'A' && c <= 'Z') 
				|| (c >= 'a' && c <= 'z')){
			return String.valueOf(c).toUpperCase(Locale.getDefault());
		}
		return "!";
	}
	
	/**
	 * �Ƿ��Ƿ��ϸ�ʽ������
	 * @param pwd
	 * @return
	 */
	public static boolean isValidPassword(String pwd){
		return pwd.matches("^(?=.*\\d.*)(?=.*[a-zA-Z].*).{6,20}$");
	}
}