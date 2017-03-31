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
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.squareup.picasso.Picasso;
import com.xiaobangzhu.xiaobangzhu.Bean.UserBaseInform;
import com.xiaobangzhu.xiaobangzhu.Bean.UserVIPInfo;
import com.xiaobangzhu.xiaobangzhu.Bean.VipTypeCode;
import com.xiaobangzhu.xiaobangzhu.Interface.DataChangeListener;
import com.xiaobangzhu.xiaobangzhu.MyApplication;
import com.xiaobangzhu.xiaobangzhu.NetworkService.NetRequestManager;
import com.xiaobangzhu.xiaobangzhu.R;

import de.hdodenhof.circleimageview.CircleImageView;

/**开通会员
 *
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

    private ImageView back;

    private LinearLayout imService;
    private LinearLayout imPrivate;
    private LinearLayout imSchool;
    private LinearLayout imShop;
    private LinearLayout imKTV;
    private LinearLayout imHotel;
    private LinearLayout imRecommend;
    private LinearLayout imCommonweal;

    private FrameLayout fl1;
    private FrameLayout fl2;
    private FrameLayout fl3;

    private View line;
    private View line1;

    private View vSign1;
    private View vSign2;
    private View vSign3;

    private int rank = 0;


    /**
     * new
     */
    private LinearLayout shop;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join_members);

        initVIP();

        initView();
        initEvent();
        initRankView();
    }

    private void initVIP() {
        NetRequestManager.getInstance().getGetUserVIPInfo("18435186562");
        NetRequestManager.getInstance().setGetUserVIPInfo(new DataChangeListener<UserVIPInfo>() {
            @Override
            public void onSuccessful(UserVIPInfo data) {
                rank = data.getViptype();
                initRankView();
                Log.i(TAG, "onSuccessful: " + rank);
            }

            @Override
            public void onError(VolleyError volleyError) {
                Log.i(TAG, volleyError.toString() +"fdfdgrgrf");
            }

            @Override
            public void onResponseNull() {
                Log.i(TAG, "onSuccessful:2323 " );
            }
        });
        /*NetRequestManager.getInstance().getUserInform(MyApplication.getInstance().getUserToken());
        NetRequestManager.getInstance().setUserBaseInformListener(new DataChangeListener<UserBaseInform>() {
            @Override
            public void onSuccessful(UserBaseInform data) {
                final String uid = data.getData().getLogin_id();
                NetRequestManager.getInstance().getGetUserVIPInfo(uid);
                NetRequestManager.getInstance().setGetUserVIPInfo(new DataChangeListener<UserVIPInfo>() {
                    @Override
                    public void onSuccessful(UserVIPInfo data) {
                        rank = data.getViptype();
                        Log.i(TAG, "onSuccessful: " +rank);
                    }

                    @Override
                    public void onError(VolleyError volleyError) {
                        MyApplication.dismissProgress();
                        MyApplication.showToastShort("请检查网络状况");
                        Log.i(TAG, "onError: " );
                    }

                    @Override
                    public void onResponseNull() {
                        MyApplication.dismissProgress();
                        MyApplication.showToastShort("请检查网络状况");
                        Log.i(TAG, "onResponseNull: " );
                    }
                });
            }

            @Override
            public void onError(VolleyError volleyError) {
                MyApplication.dismissProgress();
                MyApplication.showToastShort("请检查网络状况");
                Log.i(TAG, "onError: " );
            }

            @Override
            public void onResponseNull() {
                MyApplication.dismissProgress();
                MyApplication.showToastShort("请检查网络状况");
                Log.i(TAG, "onResponseNull: " );
            }
        });*/
    }

    private void initRankView() {
        if(rank == 0){
            tvJoinMember.setVisibility(View.VISIBLE);
            tvJoinMemberHint.setVisibility(View.VISIBLE);
        } else {
            tvJoinMember.setVisibility(View.INVISIBLE);
            tvJoinMemberHint.setVisibility(View.INVISIBLE);
            if(rank == 1){
                vSign1.setVisibility(View.VISIBLE);
                rank_header.setImageResource(R.mipmap.ic_vip_1);
                tvRank.setText("普通会员特权");
                fl1.setVisibility(View.VISIBLE);
                fl2.setVisibility(View.INVISIBLE);
                fl3.setVisibility(View.INVISIBLE);
            }else if(rank == 2){
                vSign2.setVisibility(View.VISIBLE);
                rank_header.setImageResource(R.mipmap.ic_vip_2);
                tvRank.setText("高级会员特权");
                fl1.setVisibility(View.INVISIBLE);
                fl2.setVisibility(View.VISIBLE);
                fl3.setVisibility(View.INVISIBLE);
                line.setVisibility(View.VISIBLE);
            } else{
                vSign3.setVisibility(View.VISIBLE);
                rank_header.setImageResource(R.mipmap.ic_vip_3);
                tvRank.setText("年卡会员特权");
                fl1.setVisibility(View.INVISIBLE);
                fl2.setVisibility(View.INVISIBLE);
                fl3.setVisibility(View.VISIBLE);
                line1.setVisibility(View.VISIBLE);
            }
        }
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
                startActivity(new Intent(JoinMembersActivity.this , MemberSelectActivity.class));
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

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        if(rank == 1){
            imShop.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(JoinMembersActivity.this , ShopActivity.class));
                    //Toast.makeText( JoinMembersActivity.this ,"仅对会员开放", Toast.LENGTH_SHORT).show();
                }
            });
            imKTV.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText( JoinMembersActivity.this ,"仅对会员开放", Toast.LENGTH_SHORT).show();
                }
            });
            imHotel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText( JoinMembersActivity.this ,"仅对会员开放", Toast.LENGTH_SHORT).show();
                }
            });
            imSchool.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText( JoinMembersActivity.this ,"仅对会员开放", Toast.LENGTH_SHORT).show();
                }
            });
            imPrivate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText( JoinMembersActivity.this ,"仅对会员开放", Toast.LENGTH_SHORT).show();
                }
            });
            imService.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText( JoinMembersActivity.this ,"仅对会员开放", Toast.LENGTH_SHORT).show();
                }
            });
            imRecommend.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText( JoinMembersActivity.this ,"仅对会员开放", Toast.LENGTH_SHORT).show();
                }
            });
            imCommonweal.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText( JoinMembersActivity.this ,"仅对会员开放", Toast.LENGTH_SHORT).show();
                }
            });
        }else{
            shop.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.i(TAG, "onClick: ");
                    startActivity(new Intent(JoinMembersActivity.this , ShopActivity.class));
                }
            });

        }
    }

    private void initView() {
        back = (ImageView) findViewById(R.id.activity_join_member_back_icon);
        header = (CircleImageView) findViewById(R.id.activity_join_member_header_img);
        rank_header = (CircleImageView) findViewById(R.id.circleImageView);
        nickname = (TextView) findViewById(R.id.activity_join_member_user_name);
        tvJoinMember = (TextView) findViewById(R.id.tv_join_member);
        tvJoinMemberHint = (TextView) findViewById(R.id.activity_join_member_not_vip_text);
        tvRank = (TextView) findViewById(R.id.tv_rank);

        imShop = (LinearLayout) findViewById(R.id.members_shop);

        imKTV = (LinearLayout) findViewById(R.id.im_ktv);

        imHotel = (LinearLayout) findViewById(R.id.im_hotel);

        imSchool = (LinearLayout) findViewById(R.id.im_school);
        imPrivate = (LinearLayout) findViewById(R.id.im_private);
        imService = (LinearLayout) findViewById(R.id.im_service);
        imRecommend = (LinearLayout) findViewById(R.id.im_recommend);
        imCommonweal = (LinearLayout) findViewById(R.id.im_commonweal);

        fl1 = (FrameLayout) findViewById(R.id.fr1);
        fl2 = (FrameLayout) findViewById(R.id.fr2);
        fl3 = (FrameLayout) findViewById(R.id.fr3);

        line = findViewById(R.id.join_member_line);
        line1= findViewById(R.id.join_member_line2);

        vSign1 = findViewById(R.id.view_vip_sign1);
        vSign2 = findViewById(R.id.view_vip_sign2);
        vSign3 = findViewById(R.id.view_vip_sign3);


        shop = (LinearLayout) findViewById(R.id.members_shop);

    }

}
