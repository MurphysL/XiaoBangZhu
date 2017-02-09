package com.xiaobangzhu.xiaobangzhu.UI.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.xiaobangzhu.xiaobangzhu.R;
import com.xiaobangzhu.xiaobangzhu.View.WheelView;

import java.util.ArrayList;

import cn.finalteam.toolsfinal.coder.RSACoder;


/**
 * PayForGoodsActivity
 *
 * @author: MurphySL
 * @time: 2017/2/5 15:35
 */


public class PayForGoodsActivity extends AppCompatActivity {

    private TextView pack;
    private TextView now;
    private WheelView wheel;

    private ArrayList<String> list = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay_for_goods);

        initView();
    }

    private void initView() {
        pack = (TextView) findViewById(R.id.pack);
        now = (TextView) findViewById(R.id.now);
        pack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                now.setTextColor(getResources().getColor(R.color.pay_for_goods_grey));
                now.setTextSize(12);
                pack.setTextSize(16);
                pack.setTextColor(getResources().getColor(R.color.pay_for_goods_black));
            }
        });
        now.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pack.setTextSize(12);
                pack.setTextColor(getResources().getColor(R.color.pay_for_goods_grey));
                now.setTextSize(16);
                now.setTextColor(getResources().getColor(R.color.pay_for_goods_black));
            }
        });
        wheel = (WheelView) findViewById(R.id.wheel);
        for(int i = 1 ;i < 10 ;i ++){
            list.add(i +"");
        }

        wheel.setData(list);
    }
}
