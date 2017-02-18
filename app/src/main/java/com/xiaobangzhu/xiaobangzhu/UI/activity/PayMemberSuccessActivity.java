package com.xiaobangzhu.xiaobangzhu.UI.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.xiaobangzhu.xiaobangzhu.R;

/**
 * PayMemberSuccessActivity
 *
 * @author: MurphySL
 * @time: 2017/2/18 20:32
 */


public class PayMemberSuccessActivity extends AppCompatActivity {

    private ImageView cancel;
    private ImageView type_bg;
    private int type;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay_member_success);
        initData();
        initView();
    }

    private void initData() {
        type = getIntent().getIntExtra("type" , 0);
    }

    private void initView() {
        cancel = (ImageView) findViewById(R.id.cancel);
        type_bg = (ImageView) findViewById(R.id.type);

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        switch (type){
            case 0:
                type_bg.setBackgroundResource(R.drawable.member_success);
                break;
            case 1:
                type_bg.setBackgroundResource(R.drawable.member_success);
                break;
            case 2:
                type_bg.setBackgroundResource(R.drawable.year_member_success);
                break;
        }

        cancel.setVisibility(View.VISIBLE);

    }
}
