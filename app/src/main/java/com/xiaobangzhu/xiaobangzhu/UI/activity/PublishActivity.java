package com.xiaobangzhu.xiaobangzhu.UI.activity;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.widget.LinearLayout;

import com.xiaobangzhu.xiaobangzhu.R;
import com.xiaobangzhu.xiaobangzhu.Utils.QiniyunManager;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by WQC on 2016/8/3.
 */
public class PublishActivity extends Activity implements View.OnClickListener{

    LinearLayout publishTaskBtn;
    LinearLayout publishImageBtn;
    LinearLayout publishSearchBtn;
    LinearLayout publishSignBtn;
    LinearLayout publishMoreBtn;
    CircleImageView publishCancleBtn;
    LinearLayout publishAttentionBtn;

    LinearLayout linearLayout1;
    LinearLayout linearLayout2;
    LinearLayout linearLayout3;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publish);
        //setEnterSwichLayout();
        initViews();
        setEnterAnim(300);
        initEvent();
    }

    private void initViews() {
        publishTaskBtn = (LinearLayout) findViewById(R.id.publish_normal);
        publishImageBtn = (LinearLayout) findViewById(R.id.publish_second);
        publishSearchBtn = (LinearLayout) findViewById(R.id.publish_tangle);
        publishSignBtn = (LinearLayout) findViewById(R.id.publish_team);
        publishAttentionBtn = (LinearLayout) findViewById(R.id.publish_express);
        publishMoreBtn = (LinearLayout) findViewById(R.id.publish_activity);
        publishCancleBtn = (CircleImageView) findViewById(R.id.publish_cancle);
        linearLayout1 = (LinearLayout) findViewById(R.id.linearLayout1);
        linearLayout2 = (LinearLayout) findViewById(R.id.linearLayout2);
        linearLayout3 = (LinearLayout) findViewById(R.id.linearLayout3);
        QiniyunManager.requestToken(this);
    }

    private void initEvent() {
        publishTaskBtn.setOnClickListener(this);
        publishImageBtn.setOnClickListener(this);
        publishSearchBtn.setOnClickListener(this);
        publishSignBtn.setOnClickListener(this);
        publishMoreBtn.setOnClickListener(this);
        publishCancleBtn.setOnClickListener(this);
        publishAttentionBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int viewId = view.getId();
        Intent intent = new Intent();
        switch (viewId) {
            case R.id.publish_normal:
                intent.setClass(PublishActivity.this, PublishNormalActivity.class);
                startActivity(intent);
                break;
            case R.id.publish_second:
                intent.setClass(PublishActivity.this, PublishSecondActivity.class);
                startActivity(intent);
                break;
            case R.id.publish_tangle:
                intent.setClass(PublishActivity.this, PublishTangleActivity.class);
                startActivity(intent);
                break;
            case R.id.publish_team:
                intent.setClass(PublishActivity.this, PublishTeamActivity.class);
                startActivity(intent);
                break;
            case R.id.publish_express:
                intent.setClass(PublishActivity.this, PublishExpressActivity.class);
                startActivity(intent);
                break;
            case R.id.publish_activity:
                intent.setClass(PublishActivity.this, PublishCollegeActivity.class);
                startActivity(intent);
                break;
            case R.id.publish_cancle:
                setExitAnim(300);
                break;
        }


    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            setExitAnim(3000);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private void setEnterAnim(int duration) {
        ObjectAnimator animator = ObjectAnimator.ofFloat(linearLayout1 , "translationY" , 900f , 0f);
        animator.setDuration(250);
        animator.setInterpolator(new AccelerateDecelerateInterpolator());
        animator.setCurrentPlayTime(50);
        animator.start();

        ObjectAnimator animator1 = ObjectAnimator.ofFloat(linearLayout2 , "translationY" , 900f , 0f);
        animator1.setDuration(250);
        animator1.setInterpolator(new AccelerateDecelerateInterpolator());
        animator1.setCurrentPlayTime(100);
        animator1.start();

        ObjectAnimator animator2 = ObjectAnimator.ofFloat(linearLayout3 , "translationY" , 900f , 0f);
        animator2.setDuration(250);
        animator2.setInterpolator(new AccelerateDecelerateInterpolator());
        animator2.setCurrentPlayTime(50);
        animator2.start();

        ObjectAnimator animator3 = ObjectAnimator.ofFloat(publishCancleBtn , "rotation" , 0 , 45);
        animator3.setDuration(250);
        animator3.start();

    }

    private void setExitAnim(int duration) {

        ObjectAnimator animator = ObjectAnimator.ofFloat(linearLayout1 , "translationY" , 0f , 900f);
        animator.setDuration(100);
        animator.setInterpolator(new LinearInterpolator());
        animator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                finish();
                overridePendingTransition(R.anim.enter , R.anim.exit);
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });

        animator.start();

        ObjectAnimator animator1 = ObjectAnimator.ofFloat(linearLayout2 , "translationY" , 0f , 900f);
        animator1.setDuration(100);
        animator1.setCurrentPlayTime(40);
        animator1.setInterpolator(new LinearInterpolator());
        animator1.start();

        ObjectAnimator animator2 = ObjectAnimator.ofFloat(linearLayout3 , "translationY" , 0f , 900f);
        animator2.setDuration(100);
        animator2.setInterpolator(new LinearInterpolator());
        animator2.start();

        ObjectAnimator animator3 = ObjectAnimator.ofFloat(publishCancleBtn , "rotation" , 45 , 0);
        animator3.setDuration(150);
        animator1.setCurrentPlayTime(80);
        animator3.start();

    }

}
