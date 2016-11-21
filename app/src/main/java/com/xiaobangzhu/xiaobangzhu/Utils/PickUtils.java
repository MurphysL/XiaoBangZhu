package com.xiaobangzhu.xiaobangzhu.Utils;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import com.xiaobangzhu.xiaobangzhu.R;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;


/**
 * Created by WQC on 2016/10/8.
 */

public class PickUtils {

    private static final String TAG = "TimePickUtils";

    /**
     * 获取时间
     * @param context
     * @return
     */
        public static String pickTime(Context context, final TextView textView) {
            final TextView textView1 = textView;
            final StringBuilder res = new StringBuilder();
            final Map<String, Integer> valueMap = new HashMap<>();
            Calendar c = Calendar.getInstance();

            View view = LayoutInflater.from(context).inflate(R.layout.dialog_datetime, null);
            final DatePicker datePicker = (DatePicker) view.findViewById(R.id.dialog_datepicker);
            final TimePicker timePicker = (TimePicker) view.findViewById(R.id.dialog_timepicker);
            timePicker.setIs24HourView(true);

            valueMap.put("year", datePicker.getYear());
            valueMap.put("monthOfYear", datePicker.getMonth());
            valueMap.put("dayOfMonth", datePicker.getDayOfMonth());
            valueMap.put("hourOfDay", timePicker.getCurrentHour());
            valueMap.put("minute", timePicker.getCurrentMinute());

            datePicker.init(c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH), new DatePicker.OnDateChangedListener() {
                @Override
                public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                    valueMap.put("year", year);
                    valueMap.put("monthOfYear", monthOfYear);
                    valueMap.put("dayOfMonth", dayOfMonth);
                }
            });

            timePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
                @Override
                public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                    valueMap.put("hourOfDay", hourOfDay);
                    valueMap.put("minute", minute);
                }
            });
            new AlertDialog.Builder(context).setView(view).setPositiveButton("确定", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    res.append(valueMap.get("year") + "-");
                    res.append(valueMap.get("monthOfYear") + "-");
                    res.append(valueMap.get("dayOfMonth")+" ");
                    res.append(valueMap.get("hourOfDay") + ":");
                    res.append(valueMap.get("minute") + ":00");
                    textView1.setText(res.toString());
                }
            }).create().show();

            Log.i(TAG, "pickTime: "+ res.toString());
//            new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {
//                @Override
//                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
//                    res.append(year + "-" + month + "-" + dayOfMonth);
//                    textView.setText(res.toString());
//                }
//            },c.get(Calendar.YEAR),c.get(Calendar.MONTH),c.get(Calendar.DAY_OF_MONTH)).show();
//            Log.i(TAG, "pickTime: "+res.toString());
            return res.toString();
        }


    /**
     * 自定义输入
     * @param context
     * @return
     */
    public static String pickString(Context context , final Handler handler) {
        final StringBuilder res = new StringBuilder();
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_pick, null);
        final EditText inPutEdit = (EditText) view.findViewById(R.id.dialog_input);

        new AlertDialog.Builder(context).setView(view).setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                res.append(inPutEdit.getText().toString().trim());
                Log.i(TAG, "onClick: " + res.toString());
                Message msg = new Message();
                msg.what = 1;
                Bundle bundle = new Bundle();
                bundle.putString("str" ,  res.toString());
                msg.setData(bundle);
                handler.sendMessage(msg);
            }
        }).create().show();
        return res.toString();
    }
}
