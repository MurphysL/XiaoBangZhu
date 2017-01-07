package com.xiaobangzhu.xiaobangzhu.UI.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.squareup.picasso.Picasso;
import com.xiaobangzhu.xiaobangzhu.Bean.UserBaseInform;
import com.xiaobangzhu.xiaobangzhu.Bean.VipTypeCode;
import com.xiaobangzhu.xiaobangzhu.Interface.DataChangeListener;
import com.xiaobangzhu.xiaobangzhu.MyApplication;
import com.xiaobangzhu.xiaobangzhu.NetworkService.NetRequestManager;
import com.xiaobangzhu.xiaobangzhu.R;

import de.hdodenhof.circleimageview.CircleImageView;

/**开通会员
 * Created by WQC on 2016/10/14.
 */
public class JoinMembersActivity extends AppCompatActivity {
    private static final String TAG = "JoinMembersActivity";

    private CircleImageView header;
    private CircleImageView rank_header;
    private TextView nickname;
    private TextView tvRank;
    private TextView tvJoinMember;
    private TextView tvJoinMemberHint;

    private ImageView imVip;
    private ImageView imSafety;
    private ImageView imPush;
    private ImageView imShoping;
    private ImageView imKuaidi;
    private ImageView imActivity;

    private LinearLayout imDoor;
    private LinearLayout imServer;
    private LinearLayout imFirst;
    private LinearLayout imFenqi;
    private LinearLayout imSchool;
    private LinearLayout imShop;
    private LinearLayout imJifen;
    private LinearLayout imTravel;

    private FrameLayout fl1;
    private FrameLayout fl2;
    private FrameLayout fl3;
    private FrameLayout fl4;

    private View line;
    private View line1;
    private View line2;

    private View vSign1;
    private View vSign2;
    private View vSign3;

    private int rank;


    /**
     * new
     */
    private LinearLayout shop;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join_members);

        initView();
        initEvent();
        initRankView();
    }

    private void initRankView() {
        rank = 1;
        tvJoinMember.setVisibility(View.VISIBLE);
        tvJoinMemberHint.setVisibility(View.VISIBLE);
     /*   if(rank == 1){
            tvJoinMember.setVisibility(View.VISIBLE);
            tvJoinMemberHint.setVisibility(View.VISIBLE);
            imDoor.setVisibility(View.INVISIBLE);
            imServer.setVisibility(View.INVISIBLE);
            imFirst.setVisibility(View.INVISIBLE);
            imFenqi.setVisibility(View.INVISIBLE);
            imSchool.setVisibility(View.INVISIBLE);
            imShop.setVisibility(View.INVISIBLE);
            imJifen.setVisibility(View.INVISIBLE);
            imTravel.setVisibility(View.INVISIBLE);
        } else if(rank == 2){
            vSign1.setVisibility(View.VISIBLE);
            rank_header.setImageResource(R.mipmap.ic_vip_2);
            tvRank.setText("普通会员特权");
            fl1.setVisibility(View.INVISIBLE);
            fl2.setVisibility(View.VISIBLE);
            line.setVisibility(View.VISIBLE);
        }else if(rank == 3){
            vSign2.setVisibility(View.VISIBLE);
            rank_header.setImageResource(R.mipmap.ic_vip_3);
            tvRank.setText("高级会员特权");
            fl1.setVisibility(View.INVISIBLE);
            fl3.setVisibility(View.VISIBLE);
            line1.setVisibility(View.VISIBLE);
        } else{
            vSign3.setVisibility(View.VISIBLE);
            rank_header.setImageResource(R.mipmap.ic_vip_4);
            tvRank.setText("尊贵会员特权");
            fl1.setVisibility(View.GONE);
            fl4.setVisibility(View.VISIBLE);
            line2.setVisibility(View.VISIBLE);
        }*/
    }

    private void initEvent() {
        NetRequestManager.getInstance().getUserInform(MyApplication.getInstance().getUserToken());
        NetRequestManager.getInstance().setUserBaseInformListener(new DataChangeListener<UserBaseInform>() {
            @Override
            public void onSuccessful(UserBaseInform data) {
                UserBaseInform info = data;
                String imageDir = info.getData().getHeader();
                if(imageDir != null){
                    Picasso.with(JoinMembersActivity.this)
                            .load(imageDir)
                            .into(header);
                }
                if(!info.getData().getNick_name().equals("")){
                    nickname.setText(info.getData().getNick_name());
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
        tvJoinMember.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(JoinMembersActivity.this , PayForMemberActivity.class));
            }
        });
        NetRequestManager.getInstance().getVipType(MyApplication.getInstance().getUserToken());
        NetRequestManager.getInstance().setVipTypeCodeDataChangeListener(new DataChangeListener<VipTypeCode>() {
            @Override
            public void onSuccessful(VipTypeCode data) {

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

        Log.i(TAG, "initEvent: ");
        shop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "onClick: ");
                startActivity(new Intent(JoinMembersActivity.this , ShopActivity.class));
            }
        });
    }

    private void initView() {
        header = (CircleImageView) findViewById(R.id.activity_join_member_header_img);
        rank_header = (CircleImageView) findViewById(R.id.circleImageView);
        nickname = (TextView) findViewById(R.id.activity_join_member_user_name);
        tvJoinMember = (TextView) findViewById(R.id.tv_join_member);
        tvJoinMemberHint = (TextView) findViewById(R.id.activity_join_member_not_vip_text);
        tvRank = (TextView) findViewById(R.id.tv_rank);
//        imVip = (ImageView) findViewById(R.id.im_vip);
//        imSafety = (ImageView) findViewById(R.id.safety);
//        imPush = (ImageView) findViewById(R.id.imPush);
//        imShoping = (ImageView) findViewById(R.id.imShopping);
        imKuaidi = (ImageView) findViewById(R.id.im_kuaidi);
        imActivity = (ImageView) findViewById(R.id.im_kuaidi);
        imDoor = (LinearLayout) findViewById(R.id.im_door);
//        imServer = (LinearLayout) findViewById(R.id.im_server);
        imFirst = (LinearLayout) findViewById(R.id.im_first);
        imFenqi = (LinearLayout) findViewById(R.id.im_fenqi);
        imSchool = (LinearLayout) findViewById(R.id.school);
//        imShop = (LinearLayout) findViewById(R.id.im_shop);
        imJifen = (LinearLayout) findViewById(R.id.im_jifen);
        imTravel = (LinearLayout) findViewById(R.id.im_travel);

        fl1 = (FrameLayout) findViewById(R.id.fr1);
        fl2 = (FrameLayout) findViewById(R.id.fr2);
        fl3 = (FrameLayout) findViewById(R.id.fr3);
        fl4 = (FrameLayout) findViewById(R.id.fr4);

        line = findViewById(R.id.join_member_line);
        line1= findViewById(R.id.join_member_line2);
        line2 = findViewById(R.id.join_member_line3);

        vSign1 = findViewById(R.id.view_vip_sign1);
        vSign2 = findViewById(R.id.view_vip_sign2);
        vSign3 = findViewById(R.id.view_vip_sign3);


        shop = (LinearLayout) findViewById(R.id.members_shop);

    }
}
