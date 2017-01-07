package com.xiaobangzhu.xiaobangzhu.UI.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.xiaobangzhu.xiaobangzhu.R;

/**
 * NormalVisitorInfoActivity
 *
 * @author: MurphySL
 * @time: 2016/12/4 22:49
 */


public class NormalVisitorInfoActivity extends AppCompatActivity implements View.OnClickListener{

    private ImageView back;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.normal_visitor_info);

        initView();
    }

    private void initView() {
        back = (ImageView) findViewById(R.id.activity_normal_visitor_back_icon);
        back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.activity_normal_visitor_back_icon:
                finish();
                break;
        }
    }
}
