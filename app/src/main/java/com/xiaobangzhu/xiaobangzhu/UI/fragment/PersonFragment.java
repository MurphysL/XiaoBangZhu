package com.xiaobangzhu.xiaobangzhu.UI.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import com.xiaobangzhu.xiaobangzhu.Bean.UserBaseInform;
import com.xiaobangzhu.xiaobangzhu.Interface.DataChangeListener;
import com.xiaobangzhu.xiaobangzhu.MyApplication;
import com.xiaobangzhu.xiaobangzhu.NetworkService.HtmlManager;
import com.xiaobangzhu.xiaobangzhu.NetworkService.NetRequestManager;
import com.xiaobangzhu.xiaobangzhu.R;
import com.xiaobangzhu.xiaobangzhu.UI.activity.AuthActivity;
import com.xiaobangzhu.xiaobangzhu.UI.activity.JoinMembersActivity;
import com.xiaobangzhu.xiaobangzhu.UI.activity.LaunchActivity;
import com.xiaobangzhu.xiaobangzhu.UI.activity.MessageActivity;
import com.xiaobangzhu.xiaobangzhu.UI.activity.PersonCenterActivity;
import com.xiaobangzhu.xiaobangzhu.UI.activity.SettingActivity;
import com.xiaobangzhu.xiaobangzhu.UI.activity.UpdateUserActivity;
import com.xiaobangzhu.xiaobangzhu.UI.activity.WebActivity;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by WQC on 2016/7/12.
 */
public class PersonFragment extends Fragment implements AdapterView.OnItemClickListener,View.OnClickListener , DataChangeListener{
    private static final String TAG = "PersonFragment";
    View rootView;
    CircleImageView mCircleImageView;
    LinearLayout mMessageBtn , mCollectionBtn , mFansBtn;
    ListView mListView;
    TextView mFansNumTv;
    TextView mViewsNumTv;
    TextView mLevelTv;
    TextView mTouchLoginTv;

    //图标资源
    int[] icons = new int[]{R.drawable.person_verify, R.drawable.person_vip,
            R.drawable.person_tickets, R.drawable.person_order, R.drawable.person_pay,
            R.drawable.person_setting};
    String[] names = new String[]{"用户认证","开通会员","我的门票","我的订单","还款账单","设置"};
    List<Map<String, Object>> dataList;
    //头像路径
    String header;
    SimpleAdapter simpleAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dataList = new ArrayList<Map<String, Object>>();
        for (int i = 0;i<icons.length;i++) {
            Map<String, Object> itemMap = new HashMap<String, Object>();
            itemMap.put("icon", icons[i]);
            itemMap.put("name", names[i]);
            dataList.add(itemMap);
        }
        simpleAdapter = new SimpleAdapter(getContext(), dataList, R.layout.person_listview_item, new String[]{"icon", "name"}, new int[]{R.id.list_item_image, R.id.list_item_text});

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_person, container, false);
        mCircleImageView = (CircleImageView) rootView.findViewById(R.id.person_circle_view);
        mMessageBtn = (LinearLayout) rootView.findViewById(R.id.person_message_btn);
        mCollectionBtn = (LinearLayout) rootView.findViewById(R.id.person_attention_btn);
        mTouchLoginTv = (TextView) rootView.findViewById(R.id.touch_login);
        mFansBtn = (LinearLayout) rootView.findViewById(R.id.person_fans_btn);
        mFansNumTv = (TextView) rootView.findViewById(R.id.person_fans_num);
        mViewsNumTv = (TextView) rootView.findViewById(R.id.person_attention_num);
        mLevelTv = (TextView) rootView.findViewById(R.id.person_level);
        mListView = (ListView) rootView.findViewById(R.id.person_listview);


        mListView.setAdapter(simpleAdapter);
        //初始化事件监听
        initEvent();
        initUserInform();

        return rootView;
    }

    /**
     * 初始化用户信息
     */
    private void initUserInform() {
        NetRequestManager.getInstance().getUserInform(MyApplication.getInstance().getUserToken());
        NetRequestManager.getInstance().setUserBaseInformListener(this);

        if (MyApplication.getInstance().isUserLogin()) {
            mViewsNumTv.setText(MyApplication.getInstance().getUserId()+"");
            mLevelTv.setText(MyApplication.getInstance().getUserLevel()+"");
            mFansNumTv.setText(MyApplication.getInstance().getUserFansNum()+"");
            mViewsNumTv.setText(MyApplication.getInstance().getAttentionNum()+"");

           /*
            header = MyApplication.getInstance().getUserHeader();
            Log.w(TAG, header);

            if (header != "") {
                Picasso.with(getContext()).load(header)
                        .fit()
                        .into(mCircleImageView);
            }*/
            mTouchLoginTv.setVisibility(View.GONE);
        }else{

        }

    }

    @Override
    public void onResume() {
        super.onResume();
        initUserInform();
    }

    /**
     * 初始化事件监听
     */
    private void initEvent() {
        mCircleImageView.setOnClickListener(this);
        mMessageBtn.setOnClickListener(this);
        mCollectionBtn.setOnClickListener(this);
        mFansBtn.setOnClickListener(this);
        mListView.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
        Intent intent = new Intent(getActivity(), WebActivity.class);
        Bundle bundle = new Bundle();

        boolean flag = true;//Y
        switch (position){
            case 0:
                intent.setClass(getActivity(), AuthActivity.class);
                break;
            case 1:
                MyApplication.showToastShort("功能暂未开放");
                //intent.setClass(getActivity(),JoinMembersActivity.class);
                flag = false;
                break;
            case 2:
                bundle.putString("title", "我的门票");
                bundle.putString("url",HtmlManager.getmInstance().getUrlForMyTicket(MyApplication.getInstance().getUserToken()));
                break;
            case 3:
                bundle.putString("title", "我的任务");
                bundle.putString("url",HtmlManager.getmInstance().getUrlForMyOrder(MyApplication.getInstance().getUserToken()));
                break;
            case 4:
                MyApplication.showToastShort("功能暂未开放");
                flag = false;
                //bundle.putString("title", "还款账单");
                //intent.putExtra("url",HtmlManager.getmInstance().getUrlForDebit(MyApplication.getInstance().getUserToken()));
                break;
            case 5:
                intent.setClass(getActivity(), SettingActivity.class);
                break;
        }
        if(flag){
            intent.putExtras(bundle);
            startActivity(intent);
        }

    }
    @Override
    public void onClick(View view) {
        int viewId = view.getId();
        Intent intent = new Intent();
        Bundle bundle = new Bundle();
        switch (viewId) {
            case R.id.person_circle_view:
                /*if (MyApplication.getInstance().isUserLogin()) {
                    intent.setClass(getActivity(), PersonCenterActivity.class);
                }else{
                    intent.setClass(getActivity(), LaunchActivity.class);
                }*/
                intent.setClass(getActivity() , UpdateUserActivity.class);//Y
                intent.putExtras(bundle);
                startActivity(intent);
                break;
            case R.id.person_message_btn:
                startActivity(new Intent(getContext() , MessageActivity.class));
                break;
            case R.id.person_attention_btn:
                intent.setClass(getActivity(), WebActivity.class);
                bundle.putString("title", "我的关注");
                bundle.putString("url", HtmlManager.getmInstance().getUrlForAttenList(MyApplication.getInstance().getUserToken()));
                intent.putExtras(bundle);
                startActivity(intent);
                break;
            case R.id.person_fans_btn:
                intent.setClass(getActivity(), WebActivity.class);
                bundle.putString("title", "我的粉丝");
                bundle.putString("url", HtmlManager.getmInstance().getUrlForFans(MyApplication.getInstance().getUserToken()));
                intent.putExtras(bundle);
                startActivity(intent);
                break;
            default:
                break;
        }

    }

    @Override
    public void onSuccessful(Object data) {
        UserBaseInform info = (UserBaseInform) data;
        String imageDir = info.getData().getHeader();
        if(imageDir != null){
            Picasso.with(getContext())
                    .load(imageDir)
                    .into(mCircleImageView);
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
}
