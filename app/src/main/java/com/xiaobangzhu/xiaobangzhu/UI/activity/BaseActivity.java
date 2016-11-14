package com.xiaobangzhu.xiaobangzhu.UI.activity;

import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.xiaobangzhu.xiaobangzhu.R;
import com.xiaobangzhu.xiaobangzhu.View.HeaderLayout;

/**
 * Created by WQC on 2016/8/3.
 */
public class BaseActivity extends AppCompatActivity {
    HeaderLayout headerLayout;
    
    /**
     * 初始化带左右两个button的标题栏
     * @param title
     * @param rightIconId
     * @param leftIconId
     * @param rightButtonClickListener
     * @param leftButtonClickListener
     */
    public void initHeaderWithBothIcon(String title,int rightIconId ,int leftIconId,HeaderLayout.onRightImageButtonClickListener rightButtonClickListener,HeaderLayout.onLeftImageButtonClickListener leftButtonClickListener) {
        headerLayout = (HeaderLayout) findViewById(R.id.main_header);
        headerLayout.init(HeaderLayout.HeaderStyle.TITLE_DOUBLE_IMAGEBUTTON);
        headerLayout.setTitleAndBothImageButton(title,rightIconId,leftIconId,rightButtonClickListener,leftButtonClickListener);
    }

    /**
     * 初始化带左button的标题栏
     * @param title
     * @param leftIconId
     * @param onLeftButtonClickListener
     */
    public void initHeaderWithLeftIcon(String title,int leftIconId,HeaderLayout.onLeftImageButtonClickListener onLeftButtonClickListener) {
        headerLayout = (HeaderLayout) findViewById(R.id.main_header);
        headerLayout.init(HeaderLayout.HeaderStyle.TITLE_LIFT_IMAGEBUTTON);
        headerLayout.setTitleAndLeftImageButton(title,leftIconId,onLeftButtonClickListener);
    }

    /**
     * 初始化带右button的标题栏
     * @param title
     * @param rightIconId
     * @param rightButtonClickListener
     */
    public void initHeaderWithRightIcon(String title,int rightIconId,HeaderLayout.onRightImageButtonClickListener rightButtonClickListener) {
        headerLayout = (HeaderLayout) findViewById(R.id.main_header);
        headerLayout.init(HeaderLayout.HeaderStyle.TITLE_RIGHT_IMAGEBUTTON);
        headerLayout.setTitleAndRightImageButton(title,rightIconId,rightButtonClickListener);
    }

    /**
     * 初始化只有标题的标题栏
     * @param title
     */
    public void initHeaderOnlyTitle(String title){
        headerLayout = (HeaderLayout) findViewById(R.id.main_header);
        headerLayout.init(HeaderLayout.HeaderStyle.DEFAULT_TITLE);
        headerLayout.setDefaultTitle(title);
    }

    /**
     * 隐藏标题栏
     */
    public void hideHeader(){
        headerLayout = (HeaderLayout) findViewById(R.id.main_header);
        headerLayout.setVisibility(View.GONE);
    }

    /**
     * 显示标题栏
     */
    public void showHeader(){
        headerLayout = (HeaderLayout) findViewById(R.id.main_header);
        headerLayout.setVisibility(View.VISIBLE);
    }

}
