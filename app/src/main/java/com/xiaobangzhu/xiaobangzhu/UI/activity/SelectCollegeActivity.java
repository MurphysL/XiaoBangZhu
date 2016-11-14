package com.xiaobangzhu.xiaobangzhu.UI.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.android.volley.VolleyError;
import com.xiaobangzhu.xiaobangzhu.Bean.CollegeListResultCode;
import com.xiaobangzhu.xiaobangzhu.Interface.DataChangeListener;
import com.xiaobangzhu.xiaobangzhu.MyApplication;
import com.xiaobangzhu.xiaobangzhu.NetworkService.NetRequestManager;
import com.xiaobangzhu.xiaobangzhu.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by WQC on 2016/8/29.
 */
public class SelectCollegeActivity extends BaseActivity {
    private static final String TAG = "SelectCollegeActivity";
    Spinner collegeSpinner;
    Button nextBtn;

    ArrayAdapter<String> collegeAdapter;
    Map<String, Integer> collegeMap;
    final List<String> collegeName = new ArrayList<>();
    int selectedCollegeId = 0;
    String userAccount;
    String userPassword;

    private Handler handler  = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            int what = msg.what;
            switch (what) {
                case 0x11:
                    MyApplication.showProgress(SelectCollegeActivity.this,"加载中","请稍后");
                    break;
                case 0x12:
                    MyApplication.dismissProgress();
                    break;
            }

        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_college);
        //接收上一个界面传过来的值
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("userData");
        userAccount = bundle.getString("userAccount");
        userPassword = bundle.getString("userPassword");
        NetRequestManager.getInstance().getCollegeList();
        initViews();
        initEvents();

        handler.sendEmptyMessage(0x11);

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.sendEmptyMessage(0x12);
            }
        },40000);
    }

    private void initCollegeList() {
        collegeAdapter.notifyDataSetChanged();
    }

    /**
     * 初始化界面
     */
    private void initViews() {
        nextBtn = (Button) findViewById(R.id.registe_next_btn);
        collegeSpinner = (Spinner) findViewById(R.id.regist_college_spinner);
        collegeMap = new HashMap<>();
        collegeAdapter = new ArrayAdapter<>(SelectCollegeActivity.this,R.layout.college_list_item,collegeName);
        collegeSpinner.setAdapter(collegeAdapter);
    }

    /**
     * 初始化事件监听
     */
    private void initEvents() {
        collegeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                String selectedCollege = collegeName.get(position);
                if (selectedCollege != null) {
                    selectedCollegeId = collegeMap.get(selectedCollege);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (selectedCollegeId != 0 && userAccount != null && userPassword != null) {

                    Bundle bundle = new Bundle();
                    bundle.putString("userAccount", userAccount);
                    bundle.putString("userPassword", userPassword);
                    bundle.putInt("selectedCollegeId", selectedCollegeId);
                    Intent intent = new Intent(SelectCollegeActivity.this, SelectSexActivity.class);
                    intent.putExtra("userData",bundle);
                    startActivity(intent);
                }
            }
        });

        NetRequestManager.getInstance().setCollegeListResultCodeListener(new DataChangeListener<CollegeListResultCode>() {
            @Override
            public void onSuccessful(CollegeListResultCode data) {
                if (data != null) {
                    Iterator<CollegeListResultCode.Data> iterator =  data.getData().iterator();
                    while (iterator.hasNext()) {
                        CollegeListResultCode.Data college = iterator.next();
                        collegeMap.put(college.getName(), college.getId());
                        collegeName.add(college.getName());
                    }
                    //初始化学校列表
                    initCollegeList();
                   handler.sendEmptyMessage(0x12);
                }
            }

            @Override
            public void onError(VolleyError volleyError) {
                Log.e(TAG, volleyError.getMessage());
            }

            @Override
            public void onResponseNull() {
                Log.i(TAG,"onResponseNull");
            }
        });

    }


}
