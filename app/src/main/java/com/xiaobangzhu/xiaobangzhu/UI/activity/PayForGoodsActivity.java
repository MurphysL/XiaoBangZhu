package com.xiaobangzhu.xiaobangzhu.UI.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.xiaobangzhu.xiaobangzhu.R;
import com.xiaobangzhu.xiaobangzhu.View.WheelView;

import java.util.ArrayList;


/**
 * PayForGoodsActivity
 *
 * @author: MurphySL
 * @time: 2017/2/5 15:35
 */


public class PayForGoodsActivity extends AppCompatActivity {

    private TextView pack;
    private TextView now;
    private WheelView wheel_num;
    private WheelView wheel_method;
    private ImageView cancel;

    private ArrayList<String> list = new ArrayList<>();
    private ArrayList<String> mlist = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay_for_goods);

        initView();
    }

    private void initView() {
        cancel = (ImageView) findViewById(R.id.cancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
//        pack = (TextView) findViewById(R.id.pack);
//        now = (TextView) findViewById(R.id.now);
//        pack.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                now.setTextColor(getResources().getColor(R.color.pay_for_goods_grey));
//                now.setTextSize(12);
//                pack.setTextSize(16);
//                pack.setTextColor(getResources().getColor(R.color.pay_for_goods_black));
//            }
//        });
//        now.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                pack.setTextSize(12);
//                pack.setTextColor(getResources().getColor(R.color.pay_for_goods_grey));
//                now.setTextSize(16);
//                now.setTextColor(getResources().getColor(R.color.pay_for_goods_black));
//            }
//        });
        wheel_num = (WheelView) findViewById(R.id.wheel_num);
        wheel_method = (WheelView) findViewById(R.id.wheel_method);
        for(int i = 1 ;i < 10 ;i ++)
            list.add(i +"");

        mlist.add("外带");
        mlist.add("现喝");

        wheel_method.setData(mlist);
        wheel_num.setData(list);
    }
}
