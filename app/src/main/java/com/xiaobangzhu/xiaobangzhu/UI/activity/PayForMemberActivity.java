package com.xiaobangzhu.xiaobangzhu.UI.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.xiaobangzhu.xiaobangzhu.AliPay.PayOrderActivity;
import com.xiaobangzhu.xiaobangzhu.Bean.AddVIPCode;
import com.xiaobangzhu.xiaobangzhu.Bean.UserBaseInform;
import com.xiaobangzhu.xiaobangzhu.Interface.DataChangeListener;
import com.xiaobangzhu.xiaobangzhu.MyApplication;
import com.xiaobangzhu.xiaobangzhu.NetworkService.NetRequestManager;
import com.xiaobangzhu.xiaobangzhu.R;

import static com.xiaobangzhu.xiaobangzhu.R.id.tv_name;

/**
 * PayForMemberActivity
 *
 * @author: MurphySL
 * @time: 2016/11/22 10:47
 */

public class PayForMemberActivity extends Activity {

    private ImageView cancel;
    private TextView name;
    private Button command_bt , pay_for_member;
    private RelativeLayout pay_for_normal , pay_for_high , pay_for_king , pay_for_emperor;
    private ImageView ic_vip1 , ic_vip2 , ic_vip3 , ic_vip4;
    private TextView tx_vip1,tx_vip2,tx_vip3,tx_vip4;
    private TextView month_1,month_3,month_6,month_other;

    /**
     * 百位 类型
     * 个十位 月份
     */
    private int vip_type;
    private int t;
    private int m;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay_for_member);

        initData();
        initView();
        initEvent();
    }

    private void initEvent() {
        Resources resource = getBaseContext().getResources();
        final ColorStateList csl = resource.getColorStateList(R.color.vip);
        final ColorStateList csl2 = resource.getColorStateList(R.color.base_gray);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        pay_for_normal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                t = 1;
                pay_for_normal.setBackgroundResource(R.drawable.pay_for_member_bg);
                pay_for_high.setBackgroundResource(R.drawable.pay_for_member_bg2);
                pay_for_emperor.setBackgroundResource(R.drawable.pay_for_member_bg2);
                pay_for_king.setBackgroundResource(R.drawable.pay_for_member_bg2);
                ic_vip1.setImageResource(R.drawable.ic_vip1);
                ic_vip2.setImageResource(R.mipmap.high_member);
                ic_vip3.setImageResource(R.mipmap.king_member);
                ic_vip4.setImageResource(R.mipmap.emperor_member);
                tx_vip1.setTextColor(csl);
                tx_vip2.setTextColor(csl2);
                tx_vip3.setTextColor(csl2);
                tx_vip4.setTextColor(csl2);
            }
        });
        pay_for_high.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                t = 2;
                pay_for_normal.setBackgroundResource(R.drawable.pay_for_member_bg2);
                pay_for_high.setBackgroundResource(R.drawable.pay_for_member_bg);
                pay_for_emperor.setBackgroundResource(R.drawable.pay_for_member_bg2);
                pay_for_king.setBackgroundResource(R.drawable.pay_for_member_bg2);
                ic_vip1.setImageResource(R.mipmap.normal_member);
                ic_vip2.setImageResource(R.drawable.ic_vip2);
                ic_vip3.setImageResource(R.mipmap.king_member);
                ic_vip4.setImageResource(R.mipmap.emperor_member);
                tx_vip1.setTextColor(csl2);
                tx_vip2.setTextColor(csl);
                tx_vip3.setTextColor(csl2);
                tx_vip4.setTextColor(csl2);
                month_1.setTextColor(csl2);
                month_1.setTextColor(csl);
                month_1.setTextColor(csl2);
                month_1.setTextColor(csl2);
            }
        });
        pay_for_king.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                t = 3;
                pay_for_normal.setBackgroundResource(R.drawable.pay_for_member_bg2);
                pay_for_high.setBackgroundResource(R.drawable.pay_for_member_bg2);
                pay_for_emperor.setBackgroundResource(R.drawable.pay_for_member_bg2);
                pay_for_king.setBackgroundResource(R.drawable.pay_for_member_bg);
                ic_vip1.setImageResource(R.mipmap.normal_member);
                ic_vip2.setImageResource(R.mipmap.high_member);
                ic_vip3.setImageResource(R.drawable.ic_vip3);
                ic_vip4.setImageResource(R.mipmap.emperor_member);
                tx_vip1.setTextColor(csl2);
                tx_vip2.setTextColor(csl2);
                tx_vip3.setTextColor(csl);
                tx_vip4.setTextColor(csl2);
                month_1.setTextColor(csl2);
                month_1.setTextColor(csl2);
                month_1.setTextColor(csl);
                month_1.setTextColor(csl2);
            }
        });
        pay_for_emperor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                t = 4;
                pay_for_normal.setBackgroundResource(R.drawable.pay_for_member_bg2);
                pay_for_high.setBackgroundResource(R.drawable.pay_for_member_bg2);
                pay_for_emperor.setBackgroundResource(R.drawable.pay_for_member_bg);
                pay_for_king.setBackgroundResource(R.drawable.pay_for_member_bg2);
                ic_vip1.setImageResource(R.mipmap.normal_member);
                ic_vip2.setImageResource(R.mipmap.high_member);
                ic_vip3.setImageResource(R.mipmap.king_member);
                ic_vip4.setImageResource(R.drawable.ic_vip4);
                tx_vip1.setTextColor(csl2);
                tx_vip2.setTextColor(csl2);
                tx_vip3.setTextColor(csl2);
                tx_vip4.setTextColor(csl);

            }
        });

        month_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                m = 1;
                month_1.setBackgroundResource(R.drawable.pay_for_member_bg);
                month_3.setBackgroundResource(R.drawable.pay_for_member_bg2);
                month_6.setBackgroundResource(R.drawable.pay_for_member_bg2);
                month_other.setBackgroundResource(R.drawable.pay_for_member_bg2);
                month_1.setTextColor(csl);
                month_3.setTextColor(csl2);
                month_6.setTextColor(csl2);
                month_other.setTextColor(csl2);
            }
        });
        month_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                m = 3;
                month_1.setBackgroundResource(R.drawable.pay_for_member_bg2);
                month_3.setBackgroundResource(R.drawable.pay_for_member_bg);
                month_6.setBackgroundResource(R.drawable.pay_for_member_bg2);
                month_other.setBackgroundResource(R.drawable.pay_for_member_bg2);
                month_1.setTextColor(csl2);
                month_3.setTextColor(csl);
                month_6.setTextColor(csl2);
                month_other.setTextColor(csl2);
            }
        });
        month_6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                m = 6;
                month_1.setBackgroundResource(R.drawable.pay_for_member_bg2);
                month_3.setBackgroundResource(R.drawable.pay_for_member_bg2);
                month_6.setBackgroundResource(R.drawable.pay_for_member_bg);
                month_other.setBackgroundResource(R.drawable.pay_for_member_bg2);
                month_1.setTextColor(csl2);
                month_3.setTextColor(csl2);
                month_6.setTextColor(csl);
                month_other.setTextColor(csl2);
            }
        });
        month_other.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                m = 9;
                month_1.setBackgroundResource(R.drawable.pay_for_member_bg2);
                month_3.setBackgroundResource(R.drawable.pay_for_member_bg2);
                month_6.setBackgroundResource(R.drawable.pay_for_member_bg2);
                month_other.setBackgroundResource(R.drawable.pay_for_member_bg);
                month_1.setTextColor(csl2);
                month_3.setTextColor(csl2);
                month_6.setTextColor(csl2);
                month_other.setTextColor(csl);
            }
        });

        pay_for_member.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PayForMemberActivity.this , PayOrderActivity.class);
                Bundle b = new Bundle();
                b.putString("subject" , "member");
                b.putString("body" , t*100 + m + "");
                b.putInt("total_fee" , 1);
                intent.putExtras(b);
                startActivity(intent);
            }
        });
    }

    private void initView() {
        pay_for_emperor = (RelativeLayout) findViewById(R.id.pay_for_emperor);
        pay_for_king = (RelativeLayout) findViewById(R.id.pay_for_king);
        pay_for_high = (RelativeLayout) findViewById(R.id.pay_for_high);
        pay_for_normal = (RelativeLayout) findViewById(R.id.pay_for_normal);
        name = (TextView) findViewById(tv_name);
        cancel = (ImageView) findViewById(R.id.pay_for_member_cancel);

        ic_vip1 = (ImageView) findViewById(R.id.ic_vip1);
        ic_vip2 = (ImageView) findViewById(R.id.ic_vip2);
        ic_vip3 = (ImageView) findViewById(R.id.ic_vip3);
        ic_vip4 = (ImageView) findViewById(R.id.ic_vip4);
        tx_vip1 = (TextView) findViewById(R.id.tx_normal_member);
        tx_vip2 = (TextView) findViewById(R.id.tx_high_member);
        tx_vip3 = (TextView) findViewById(R.id.tx_king_member);
        tx_vip4 = (TextView) findViewById(R.id.tx_emperor_member);

        month_1 = (TextView) findViewById(R.id.month_1);
        Drawable drawable = getResources().getDrawable(R.drawable.member_recommend);
        drawable.setBounds(0 ,0 , 60 , 40);
        month_1.setCompoundDrawables(drawable , null , null , null);
        month_3 = (TextView) findViewById(R.id.month_3);
        month_6 = (TextView) findViewById(R.id.month_6);
        month_other = (TextView) findViewById(R.id.month_other);
        pay_for_member = (Button) findViewById(R.id.bt_pay_for_member);
    }

    private void initData() {
        NetRequestManager.getInstance().getUserInform(MyApplication.getInstance().getUserToken());
        NetRequestManager.getInstance().setUserBaseInformListener(new DataChangeListener<UserBaseInform>() {
            @Override
            public void onSuccessful(UserBaseInform data) {
                UserBaseInform info =  data;
                if(info.getData() != null){
                    name.setText(info.getData().getNick_name());
                }
            }

            @Override
            public void onError(VolleyError volleyError) {
                MyApplication.dismissProgress();
                MyApplication.showToastShort(volleyError.getMessage());
            }

            @Override
            public void onResponseNull() {
                MyApplication.dismissProgress();
                MyApplication.showToastShort("获取个人信息失败");
            }
        });

    }
}
