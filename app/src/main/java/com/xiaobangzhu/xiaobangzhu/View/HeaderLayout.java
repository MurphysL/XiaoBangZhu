package com.xiaobangzhu.xiaobangzhu.View;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xiaobangzhu.xiaobangzhu.R;

/**
 * Created by WQC on 2016/7/12.
 */
public class HeaderLayout extends LinearLayout {
    private LayoutInflater mInflater;
    private View mHeader;
    private LinearLayout mLayoutLeftContainer;
    private LinearLayout mLayoutRightContainer;
    private TextView mHtvSubTitle;
    private LinearLayout mLayoutRightImageButtonLayout;
    private ImageButton mRightImageButton;
    private onRightImageButtonClickListener mRightImageButtonClickListener;

    private LinearLayout mLayoutLeftImageButtonLayout;
    private ImageButton mLeftImageButton;
    private onLeftImageButtonClickListener mLeftImageButtonClickListener;

    public enum HeaderStyle {// 头部整体样式
        DEFAULT_TITLE, TITLE_LIFT_IMAGEBUTTON, TITLE_RIGHT_IMAGEBUTTON, TITLE_DOUBLE_IMAGEBUTTON;
    }

    public HeaderLayout(Context context) {
        super(context);
        init(context);
    }

    public HeaderLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public void init(Context context) {
        mInflater = LayoutInflater.from(context);
        mHeader = mInflater.inflate(R.layout.common_header, null);
        addView(mHeader);
        initViews();
    }

    public void initViews() {
        mLayoutLeftContainer = (LinearLayout) findViewByHeaderId(R.id.header_layout_leftview_container);
//         mLayoutMiddleContainer = (LinearLayout)findViewByHeaderId(R.id.header_layout_middleview_container);
        mLayoutRightContainer = (LinearLayout) findViewByHeaderId(R.id.header_layout_rightview_container);
        mHtvSubTitle = (TextView) findViewByHeaderId(R.id.header_htv_subtitle);

    }

    public View findViewByHeaderId(int id) {
        return mHeader.findViewById(id);
    }

    public void init(HeaderStyle hStyle) {
        switch (hStyle) {
            case DEFAULT_TITLE:
                defaultTitle();
                break;

            case TITLE_LIFT_IMAGEBUTTON:
                defaultTitle();
                titleLeftImageButton();
                break;

            case TITLE_RIGHT_IMAGEBUTTON:
                defaultTitle();
                titleRightImageButton();
                break;

            case TITLE_DOUBLE_IMAGEBUTTON:
                defaultTitle();
                titleLeftImageButton();
                titleRightImageButton();
                break;
        }
    }

    // 默认文字标题
    private void defaultTitle() {
        mLayoutLeftContainer.removeAllViews();
        mLayoutRightContainer.removeAllViews();
    }
    // 左侧自定义按钮
    private void titleLeftImageButton() {
        View mleftImageButtonView = mInflater.inflate(
                R.layout.common_header_button, null);
        mLayoutLeftContainer.addView(mleftImageButtonView);
        mLayoutLeftImageButtonLayout = (LinearLayout) mleftImageButtonView
                .findViewById(R.id.header_layout_imagebuttonlayout);
        mLeftImageButton = (ImageButton) mleftImageButtonView
                .findViewById(R.id.header_ib_imagebutton);
        mLayoutLeftImageButtonLayout.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                if (mLeftImageButtonClickListener != null) {
                    mLeftImageButtonClickListener.onClick();
                }
            }
        });
    }

    // 右侧自定义按钮
    private void titleRightImageButton() {
        View mRightImageButtonView = mInflater.inflate(
                R.layout.common_header_rightbutton, null);
        mLayoutRightContainer.addView(mRightImageButtonView);
        mLayoutRightImageButtonLayout = (LinearLayout) mRightImageButtonView
                .findViewById(R.id.header_layout_imagebuttonlayout);
        mRightImageButton = (ImageButton) mRightImageButtonView
                .findViewById(R.id.header_ib_imagebutton);
        mLayoutRightImageButtonLayout.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                if (mRightImageButtonClickListener != null) {
                    mRightImageButtonClickListener.onClick();
                }
            }
        });
    }

    /** 获取右边按钮
     * @Title: getRightImageButton
     * @param @return
     * @return Button
     * @throws
     */
    public ImageButton getRightImageButton(){
        if(mRightImageButton!=null){
            return mRightImageButton;
        }
        return null;
    }
    public  ImageButton getLeftImageButton(){
        if (mLeftImageButton != null) {
            return mLeftImageButton;
        }
        return null;
    }

    public void setDefaultTitle(CharSequence title) {
        if (title != null) {

            mHtvSubTitle.setText(title);
            mHtvSubTitle.setVisibility(View.VISIBLE);
        } else {
            mHtvSubTitle.setVisibility(View.VISIBLE);
        }
    }

    public void setTitleAndRightImageButton(CharSequence title, int backid,
                                            onRightImageButtonClickListener onRightImageButtonClickListener) {
        setDefaultTitle(title);
        mLayoutRightContainer.setVisibility(View.VISIBLE);
        if (mRightImageButton != null && backid > 0) {
            mRightImageButton.setImageResource(backid);
            setOnRightImageButtonClickListener(onRightImageButtonClickListener);
        }
    }

    public void setTitleAndLeftImageButton(CharSequence title, int id,
                                           onLeftImageButtonClickListener listener) {
        setDefaultTitle(title);
        mLayoutRightContainer.setVisibility(View.VISIBLE);
        if (mLeftImageButton != null && id > 0) {
            mLeftImageButton.setImageResource(id);
            setOnLeftImageButtonClickListener(listener);
        }

    }


    public void setTitleAndBothImageButton(CharSequence title,int rightId,int leftId,onRightImageButtonClickListener rightListener,onLeftImageButtonClickListener leftListener){
        setDefaultTitle(title);
        if (mRightImageButton != null && mLeftImageButton != null && rightId > 0 && leftId > 0) {
            mLeftImageButton.setImageResource(leftId);
            setOnLeftImageButtonClickListener(leftListener);
            mRightImageButton.setImageResource(rightId);
            setOnRightImageButtonClickListener(rightListener);
        }
    }

    public void setOnRightImageButtonClickListener(
            onRightImageButtonClickListener listener) {
        mRightImageButtonClickListener = listener;
    }

    public interface onRightImageButtonClickListener {
        void onClick();
    }

    public void setOnLeftImageButtonClickListener(
            onLeftImageButtonClickListener listener) {
        mLeftImageButtonClickListener = listener;
    }

    public interface onLeftImageButtonClickListener {
        void onClick();
    }


}
