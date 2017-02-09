package com.xiaobangzhu.xiaobangzhu.UI.activity;
;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.xiaobangzhu.xiaobangzhu.Bean.UserBaseInform;
import com.xiaobangzhu.xiaobangzhu.Bean.VipTypeCode;
import com.xiaobangzhu.xiaobangzhu.Interface.DataChangeListener;
import com.xiaobangzhu.xiaobangzhu.MyApplication;
import com.xiaobangzhu.xiaobangzhu.NetworkService.NetRequestManager;
import com.xiaobangzhu.xiaobangzhu.R;
import com.xiaobangzhu.xiaobangzhu.UI.fragment.MemberLogoFrag1;
import com.xiaobangzhu.xiaobangzhu.UI.fragment.MemberLogoFrag2;
import com.xiaobangzhu.xiaobangzhu.UI.fragment.MemberLogoFrag3;

import java.util.ArrayList;
import java.util.List;

/**
 * MemberSelectActivity
 *
 * @author: MurphySL
 * @time: 2017/1/26 16:21
 */


public class MemberSelectActivity extends AppCompatActivity {
    private static final String TAG = "MemberSelectActivity";

    private ImageView cancel;
    private TextView name;
    private TextView price;
    private Button command_bt , pay_for_member;
    private TextView month_1,month_3,month_6;
    private EditText month_other;

    private List<ImageView> indicatorImages;
    private LinearLayout indicator;
    private List<Fragment> fragments;
    private ViewPager viewPager;
    int currentPager = 1;

    private float price_normal , price_high , price_year;
    /**
     * 0--normal
     * 1--high
     * 2--year
     */
    private int vip_type;
    private int t;
    private int m = 1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay_for_member);

        initSelectFrag();
        initData();
        initView();
        initEvent();
    }

    private void initSelectFrag() {
        fragments = new ArrayList<>();
        fragments.add(new MemberLogoFrag3());
        fragments.add(new MemberLogoFrag1());
        fragments.add(new MemberLogoFrag2());
        fragments.add(new MemberLogoFrag3());
        fragments.add(new MemberLogoFrag1());
    }

    private void createIndicator() {
        indicatorImages.clear();
        indicator.removeAllViews();
        for (int i = 1; i < fragments.size() - 1; ++i) {
            ImageView imageView = new ImageView(MemberSelectActivity.this);
            imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(40, 15);
            if (i == 1) {
                imageView.setImageResource(R.drawable.indicator_select);
            } else {
                imageView.setImageResource(R.drawable.indicator_unselect);
            }
            indicatorImages.add(imageView);
            indicator.addView(imageView, params);
        }
    }

    private void initView() {
        price = (TextView) findViewById(R.id.pay_for_member_price);
        indicator = (LinearLayout) findViewById(R.id.indicator);
        viewPager = (ViewPager) findViewById(R.id.pay_for_member_bt);
        name = (TextView) findViewById(R.id.tv_name);
        cancel = (ImageView) findViewById(R.id.pay_for_member_cancel);

        month_1 = (TextView) findViewById(R.id.month_1);
        month_3 = (TextView) findViewById(R.id.month_3);
        month_6 = (TextView) findViewById(R.id.month_6);
        month_other = (EditText) findViewById(R.id.month_other);
        Drawable drawable = getResources().getDrawable(R.drawable.member_recommend);
        drawable.setBounds(0 ,0 , 60 , 40);
        month_1.setCompoundDrawables(drawable , null , null , null);

        pay_for_member = (Button) findViewById(R.id.bt_pay_for_member);
    }

    private void initData() {
        NetRequestManager.getInstance().getUserInform(MyApplication.getInstance().getUserToken());
        NetRequestManager.getInstance().getVipType(MyApplication.getInstance().getUserToken());
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
        NetRequestManager.getInstance().setVipTypeCodeDataChangeListener(new DataChangeListener<VipTypeCode>() {
            @Override
            public void onSuccessful(VipTypeCode data) {
                List<VipTypeCode.Data> list = data.getData();
                if(list != null && list.size() >= 3){
                    price_year = list.get(list.size() - 1).getPrice();
                    price_high = list.get(list.size() - 2).getPrice();
                    price_normal = list.get(list.size() - 3).getPrice();
                }
                calculatePrice();
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

    private void initEvent() {
        indicatorImages = new ArrayList<>();
        viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragments.get(position);
            }

            @Override
            public int getCount() {
                return fragments.size();
            }
        });

        viewPager.setCurrentItem(currentPager);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                Log.i(TAG, "onPageSelected: " + position);
                if(position == fragments.size()-1){
                    currentPager = 1;
                }else if(position == 0){
                    currentPager = fragments.size() -2;
                }else{
                    currentPager = position;
                }

                for(int i = 0 ;i < indicatorImages.size() ;i ++){
                    if(i == (position-1)%indicatorImages.size()){
                        indicatorImages.get(i).setImageResource(R.drawable.indicator_select);
                        vip_type = i;
                    } else {
                        indicatorImages.get(i).setImageResource(R.drawable.indicator_unselect);
                    }
                }
                if(position == 0){
                    indicatorImages.get(2).setImageResource(R.drawable.indicator_select);
                    vip_type = 2;
                }
                calculatePrice();

            }

            @Override
            public void onPageScrollStateChanged(int state) {
                Log.i(TAG, "onPageScrollStateChanged: " + currentPager);
                viewPager.setCurrentItem(currentPager , false);
            }
        });
        createIndicator();


        Resources resource = getBaseContext().getResources();
        final ColorStateList csl = resource.getColorStateList(R.color.vip);
        final ColorStateList csl2 = resource.getColorStateList(R.color.base_color_text_black);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        month_other.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                month_other.setText("");
                //找回焦点
                month_other.setFocusable(true);
                month_other.setFocusableInTouchMode(true);
                month_other.requestFocus();
                month_other.findFocus();

                month_other.setCursorVisible(true);
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

        month_other.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                int i = 0;
                try {
                    i = Integer.valueOf(s.toString());
                    Log.i(TAG, "afterTextChanged: " + i);
                }catch (NumberFormatException e){
                    if(s.toString().equals("")){

                    }else{
                        Toast.makeText(MemberSelectActivity.this , "内容错误" , Toast.LENGTH_SHORT).show();
                    }
                }

                if( i >= 1){
                    m = i;
                    calculatePrice();
                }
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
                month_other.setCursorVisible(false);
                month_other.setFocusable(true);
                calculatePrice();
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
                month_other.setCursorVisible(false);
                month_other.setFocusable(true);
                calculatePrice();
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
                month_other.setCursorVisible(false);
                month_other.setFocusable(true);
                calculatePrice();
            }
        });

//        pay_for_member.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(.this , JoinMember.class);
//                Bundle b = new Bundle();
//                b.putString("subject" , "member");
//                b.putString("body" , t*100 + m + "");
//                b.putInt("total_fee" , 1);
//                intent.putExtras(b);
//                startActivity(intent);
//            }
//        });
    }

    private float calculatePrice(){
        float p = 0;
        switch (vip_type){
            case 0:
                p = price_normal;
                break;
            case 1:
                p = price_high;
                break;
            case 2:
                p = price_year;
                break;
            default:
                p = 100;
        }
        price.setText((p * m) + " ");
        return p * m;
    }
}