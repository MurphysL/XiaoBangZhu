package com.xiaobangzhu.xiaobangzhu.Utils;

import android.util.Log;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by WQC on 2016/8/3.
 */
public class VerifyUtils {
    public static final String TAG = "VerifyUtils";
    /**
     * 检查手机号格式是否正确
     * @param inputText
     * @return
     */
    public static boolean isPhone(String inputText) {
        boolean flag=false;

        if(inputText.length()==0){
            return false;
        }
        String[] mobiles=inputText.split(",");
        int len=mobiles.length;
        if(len==1){
            flag = Pattern.matches("^((13[0-9])|(14[5,7,9])|(15[^4,\\D])|(17[0,1,3,5-8])|(18[0-9]))\\d{8}$", inputText);
        }else{flag = false;}
        return flag;

    }

    /**
     * 密码校验
     * @param inputText
     * @return
     */
    public static boolean isPassWord(String inputText) {
        boolean flag=false;

        if(inputText.length()==0){
            return false;
        }
        flag = Pattern.matches("^[a-zA-Z]\\w{8,12}$", inputText);
        return flag;

    }



    /**
     * 检测邮箱格式是否正确
     * @param inputText
     * @return
     */
    public static boolean isEmail(String inputText) {
        String str = "^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$";
        Pattern p = Pattern.compile(str);
        Matcher m = p.matcher(inputText);
        return m.matches();
    }

    /**
     * 将空格替换成%2B
     * @param string
     * @return
     */
    public static String replaceSpaces(String string){
        if (string.contains(" ")) {
            Log.i(TAG, string+"含空格");
            return string.replaceAll(" ","%2B");
        }else{
            Log.i(TAG, string+ "不含空格");
            return string;
        }
    }

    /**
     * 检验String是否为空
     * @param str
     * @return
     */
    public static boolean isNull(String str){
        if (str == null || str == "") {
            return true;
        } else {
            return false;
        }
    }

}
