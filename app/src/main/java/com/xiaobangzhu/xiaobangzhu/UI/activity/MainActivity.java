package com.xiaobangzhu.xiaobangzhu.UI.activity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.uzmap.pkg.uzsocket.api.Receiver;
import com.uzmap.pkg.uzsocket.g.c;
import com.wei.zxinglibrary.activity.CaptureActivity;
import com.xiaobangzhu.xiaobangzhu.MyApplication;
import com.xiaobangzhu.xiaobangzhu.NetworkService.NetRequestManager;
import com.xiaobangzhu.xiaobangzhu.NetworkService.UpdateService;
import com.xiaobangzhu.xiaobangzhu.R;
import com.xiaobangzhu.xiaobangzhu.Receiver.MyReceiver;
import com.xiaobangzhu.xiaobangzhu.UI.fragment.DebitFragment;
import com.xiaobangzhu.xiaobangzhu.UI.fragment.HomeFragment;
import com.xiaobangzhu.xiaobangzhu.UI.fragment.HomeFragment2;
import com.xiaobangzhu.xiaobangzhu.UI.fragment.PersonFragment;
import com.xiaobangzhu.xiaobangzhu.UI.fragment.RewardFragment;
import com.xiaobangzhu.xiaobangzhu.View.HeaderLayout;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends BaseActivity implements View.OnClickListener{
    public static final String TAG = "MainActivity";
    ViewPager mViewPager;
    List<Object> mFragmentList = new ArrayList<>();
    HomeFragment2 mHomeFragment;
    DebitFragment mDebitFragment;
    RewardFragment mRewardFragment;
    PersonFragment mPersonFragment;
    LinearLayout bottom_home,bottom_reward,bottom_debit, bottom_person,bottom_publish;
    TextView bottom_home_text ,bottom_reward_text,bottom_debit_text,bottom_person_text;
    ImageView bottom_home_image,bottom_reward_image,bottom_debit_image, bottom_person_image;
    CircleImageView bottom_publish_image;
    PagerAdapter mAdapter;
    Bundle bundle;
    TextView titleTextView;

    private class MainReceiver extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {
            Bundle bundle = intent.getBundleExtra(MyApplication.getInstance().KEY_EXTRAS);
            Log.i(TAG , "receiver" + bundle.getString(MyApplication.getInstance().KEY_MESSAGE));
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initEvent();
        Log.i(TAG, " initUserInform : " + MyApplication.getInstance().getUserToken());
        
        checkVersion();

    }

    /**
     * 检查是否需要更新
     */
    private void checkVersion() {

        try {
            PackageManager pm = getPackageManager();
            PackageInfo pi = pm.getPackageInfo("com.xiaobangzhu.xiaobangzhu", 0);
            String name = pi.versionName;
            int code = pi.versionCode;
            Log.i(TAG, "checkVersion: " +name);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        /*AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("更新应用");
        builder.setMessage("已有新版本更新");
        builder.setIcon(R.mipmap.ic_launcher);
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(MainActivity.this , UpdateService.class);
                intent.putExtra(url , lastVersion);
                startService(intent);
                dialog.dismiss();
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        builder.create().show();*/

    }

    private void initView() {
        mHomeFragment = new HomeFragment2();
        mDebitFragment = new DebitFragment();
        mRewardFragment = new RewardFragment();
        mPersonFragment = new PersonFragment();

        mFragmentList.add(mHomeFragment);
        mFragmentList.add(mRewardFragment);
        mFragmentList.add(mDebitFragment);
        mFragmentList.add(mPersonFragment);
        mViewPager = (ViewPager) findViewById(R.id.main_view_pager);

        mAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return (Fragment) mFragmentList.get(position);
            }

            @Override
            public int getCount() {
                return mFragmentList.size();
            }
        };

        mViewPager.setAdapter(mAdapter);
        bottom_home = (LinearLayout) findViewById(R.id.bottom_home);
        bottom_reward = (LinearLayout) findViewById(R.id.bottom_reward);
        bottom_publish = (LinearLayout) findViewById(R.id.bottom_publish);
        bottom_debit = (LinearLayout) findViewById(R.id.bottom_debit);
        bottom_person = (LinearLayout) findViewById(R.id.bottom_person);

        bottom_home_image = (ImageView)findViewById(R.id.bottom_home_image);
        bottom_reward_image = (ImageView)findViewById(R.id.bottom_reward_image);
        bottom_publish_image = (CircleImageView) findViewById(R.id.bottom_publish_image);
        bottom_debit_image = (ImageView)findViewById(R.id.bottom_debit_image);
        bottom_person_image = (ImageView)findViewById(R.id.bottom_person_image);

        bottom_home_text = (TextView) findViewById(R.id.bottom_home_text);
        bottom_reward_text = (TextView) findViewById(R.id.bottom_reward_text);
        bottom_debit_text = (TextView) findViewById(R.id.bottom_debit_text);
        bottom_person_text = (TextView) findViewById(R.id.bottom_person_text);
        titleTextView = (TextView) findViewById(R.id.header_htv_subtitle);

        initHeaderWithLeftIcon(getString(R.string.home_title), R.mipmap.ic_header_scan,new HeaderLayout.onLeftImageButtonClickListener() {
            @Override
            public void onClick() {
                startActivityForResult(new Intent(MainActivity.this, CaptureActivity.class), 0x11);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 0x11) {
            if (resultCode == RESULT_OK) {
                Bundle bundle = data.getExtras();
                String res = bundle.getString("result");
                Log.w(TAG, "onActivityResult: "+ res);
                //根据扫描回来的url进行访问
                NetRequestManager.getInstance().sacnQR(MyApplication.getInstance().getUserToken(), res);
            }
        }
    }

    private void initEvent() {
        bottom_home.setOnClickListener(this);
        bottom_reward.setOnClickListener(this);
        bottom_publish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,PublishActivity.class));
            }
        });
        bottom_debit.setOnClickListener(this);
        bottom_person.setOnClickListener(this);

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                resetTextView();
                resetImage();
                switch (position) {
                    case 0:
                        homeSelected();
                        titleTextView.setText("萧帮主");
                        break;
                    case 1:
                        titleTextView.setText("悬赏");
                        rewardSelected();
                        break;
                    case 2:
                        titleTextView.setText("花吧");
                        debitSelected();
                        break;
                    case 3:
                        titleTextView.setText("个人");
                        personSelected();
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void personSelected() {
        //hideHeader();
        showHeader();
        bottom_person_image.setImageResource(R.drawable.person_selected);
        bottom_person_text.setTextColor(getResources().getColor(R.color.color_base));
    }

    private void debitSelected() {
        showHeader();
        bottom_debit_image.setImageResource(R.drawable.bottom_bar_debit_selected);
        bottom_debit_text.setTextColor(getResources().getColor(R.color.color_base));
    }

    private void rewardSelected() {
        showHeader();
        bottom_reward_image.setImageResource(R.drawable.reward_selected);
        bottom_reward_text.setTextColor(getResources().getColor(R.color.color_base));
    }
    public void homeSelected(){
        showHeader();
        bottom_home_image.setImageResource(R.drawable.bottom_bar_home_selected);
        bottom_home_text.setTextColor(getResources().getColor(R.color.color_base));
    }

    private void resetImage() {
        bottom_home_image.setImageResource(R.drawable.bottom_bar_home_normal);
        bottom_reward_image.setImageResource(R.drawable.reward_normal);
        bottom_debit_image.setImageResource(R.drawable.bottom_bar_debit_normal);
        bottom_person_image.setImageResource(R.drawable.person_normal);
    }

    private void resetTextView() {
        bottom_home_text.setTextColor(getResources().getColor(R.color.color_bottom_text_normal));
        bottom_reward_text.setTextColor(getResources().getColor(R.color.color_bottom_text_normal));
        bottom_debit_text.setTextColor(getResources().getColor(R.color.color_bottom_text_normal));
        bottom_person_text.setTextColor(getResources().getColor(R.color.color_bottom_text_normal));
    }

    @Override
    public void onClick(View view) {

        int viewId = view.getId();
        resetTextView();
        resetImage();
        if (viewId == R.id.bottom_home) {
            mViewPager.setCurrentItem(0);
            homeSelected();
        } else if (viewId == R.id.bottom_reward) {
            mViewPager.setCurrentItem(1);
            rewardSelected();
        } else if (viewId == R.id.bottom_debit) {
            mViewPager.setCurrentItem(2);
            debitSelected();
        } else if (viewId == R.id.bottom_person) {
            mViewPager.setCurrentItem(3);
            personSelected();
        } else if (viewId == R.id.bottom_publish) {
            startActivity(new Intent(MainActivity.this, PublishActivity.class));
        }else{

        }

    }


    /**
     * 按两次返回
     * @param keyCode
     * @param event
     * @return
     */
    private long exitTime;
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        //按两次返回键退出程序
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {

            if ((System.currentTimeMillis() - exitTime) > 2000)
            {
                Toast.makeText(getApplicationContext(), "再按一次退出程序", Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
            } else {
                finish();
                System.exit(0);
            }
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }
}
