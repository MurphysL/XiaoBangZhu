package com.xiaobangzhu.xiaobangzhu.UI.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.xiaobangzhu.xiaobangzhu.AliPay.PayOrderActivity;
import com.xiaobangzhu.xiaobangzhu.Bean.BaseResultCode;
import com.xiaobangzhu.xiaobangzhu.Bean.GetExressCompanyResultCode;
import com.xiaobangzhu.xiaobangzhu.Interface.DataChangeListener;
import com.xiaobangzhu.xiaobangzhu.MyApplication;
import com.xiaobangzhu.xiaobangzhu.NetworkService.NetRequestManager;
import com.xiaobangzhu.xiaobangzhu.R;
import com.xiaobangzhu.xiaobangzhu.Utils.PickUtils;
import com.xiaobangzhu.xiaobangzhu.Utils.VerifyUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by WQC on 2016/10/14.
 */

public class PublishExpressActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "PublishExpressActivity";
    private TextView cancleBtn;
    private TextView publishBtn;
    private Spinner spinner;
    private EditText nameEditText;
    private EditText addressEditText;
    private EditText phonetailEditText;
    private EditText expressIdEditText;
    private TextView deathtimeTextView;
    private LinearLayout deathtimeBtn;
    private EditText tipEdittext;

    Map<String, Integer> expressCompanyMap;
    final List<String> expressCompanyName = new ArrayList<>();
    ArrayAdapter<String> expressCompanyAdapter;

    int expressCompanyId = 0;
    String name = null;
    String address= null;
    int expressId=0;
    String phoneTail= null;
    String endTime= null;
    int tip =0;


    private Handler handler  = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            int what = msg.what;
            switch (what) {
                case 0x11:
                    MyApplication.showProgress(PublishExpressActivity.this,"加载中","请稍后");
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
        setContentView(R.layout.activity_publish_express);
        initViews();
        initEvents();
        NetRequestManager.getInstance().getExpressCompany(MyApplication.getInstance().getUserToken());

//        handler.sendEmptyMessage(0x11);

//        Timer timer = new Timer();
//        timer.schedule(new TimerTask() {
//            @Override
//            public void run() {
//                handler.sendEmptyMessage(0x12);
//            }
//        },20000);

    }

    public void initCompanyList() {
        expressCompanyAdapter.notifyDataSetChanged();
    }



    private void initViews() {
        publishBtn = (TextView) findViewById(R.id.publish_header_publish);
        cancleBtn = (TextView) findViewById(R.id.publish_header_cancle);
        spinner = (Spinner) findViewById(R.id.express_type_spinner);
        nameEditText = (EditText) findViewById(R.id.publish_express_name);
        addressEditText = (EditText) findViewById(R.id.publish_express_address);
        phonetailEditText = (EditText) findViewById(R.id.publish_express_phone_tail);
        expressIdEditText = (EditText) findViewById(R.id.publish_express_id);
        deathtimeTextView = (TextView) findViewById(R.id.publish_express_deathtime_show);
        deathtimeBtn = (LinearLayout) findViewById(R.id.publish_express_deathtime);
        tipEdittext = (EditText) findViewById(R.id.publish_express_tip);

        expressCompanyMap = new HashMap<>();
        expressCompanyAdapter = new ArrayAdapter<>(PublishExpressActivity.this,R.layout.college_list_item,expressCompanyName);
        spinner.setAdapter(expressCompanyAdapter);
    }

    private void initEvents() {
        publishBtn.setOnClickListener(this);
        cancleBtn.setOnClickListener(this);
        deathtimeBtn.setOnClickListener(this);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String companyName = expressCompanyName.get(position);
                if (companyName != null) {
                    expressCompanyId = expressCompanyMap.get(companyName);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        NetRequestManager.getInstance().setGetExressCompanyResultCodeListener(new DataChangeListener<GetExressCompanyResultCode>() {
            @Override
            public void onSuccessful(GetExressCompanyResultCode data) {
                if (data != null) {
                    if (data.getStatus() == 0) {
                        Iterator<GetExressCompanyResultCode.Data> iterator =  data.getData().iterator();
                        while (iterator.hasNext()) {
                            GetExressCompanyResultCode.Data company = iterator.next();
                            expressCompanyMap.put(company.getName(), company.getId());
                            expressCompanyName.add(company.getName());
                        }
                        //初始化快递列表
                        initCompanyList();
                        handler.sendEmptyMessage(0x12);
                    }
                }
            }

            @Override
            public void onError(VolleyError volleyError) {
                MyApplication.showToastShort("请检查网络状况");
                handler.sendEmptyMessage(0x12);
            }

            @Override
            public void onResponseNull() {
                MyApplication.showToastShort("请检查网络状况");
            }
        });

        NetRequestManager.getInstance().setAddExpressResultCodeListener(new DataChangeListener<BaseResultCode>() {
            @Override
            public void onSuccessful(BaseResultCode data) {
                MyApplication.dismissProgress();
                if (data != null) {
                    if (data.getStatus() == 0) {
                        MyApplication.showToastShort("发布成功");
                        finish();
                    }
                }
            }

            @Override
            public void onError(VolleyError volleyError) {
                MyApplication.dismissProgress();
                MyApplication.showToastShort("发布失败");
            }

            @Override
            public void onResponseNull() {
                MyApplication.dismissProgress();
                MyApplication.showToastShort("发布失败");
            }
        });

    }


    @Override
    public void onClick(View v) {
        int viewId = v.getId();
        switch (viewId) {
            case R.id.publish_header_publish:
                name = nameEditText.getText().toString().trim();
                address = addressEditText.getText().toString().trim();
                phoneTail = phonetailEditText.getText().toString().trim();
                String expressStr = expressIdEditText.getText().toString().trim();
                String tipStr = tipEdittext.getText().toString().trim();
                endTime = deathtimeTextView.getText().toString().trim().replace(" ","%2B");

                Log.i(TAG, "onClick: " + name + address + tip);
                if (VerifyUtils.isNull(tipStr)||VerifyUtils.isNull(name) ||VerifyUtils.isNull(address) || VerifyUtils.isNull(phoneTail) || expressCompanyId == 0 || VerifyUtils.isNull(endTime) || VerifyUtils.isNull(tipStr)) {
                    MyApplication.showToastShort("请检查输入是否完整");
                }else {
                    expressId = Integer.valueOf(expressStr);
                    tip = Integer.valueOf(tipStr);
                    MyApplication.showProgress(PublishExpressActivity.this,"发布中","请稍等");
                    Log.i(TAG, "onClick: " + name + address + phoneTail + expressId + tip);
                    NetRequestManager.getInstance().addExpress(MyApplication.getInstance().getUserToken(), MyApplication.getInstance().getUserCollegeId(), name, expressId, address, tip, phoneTail, expressCompanyId, endTime);

                    //Y
                    Intent intent = new Intent(PublishExpressActivity.this , PayOrderActivity.class);
                    Bundle b = new Bundle();
                    b.putString("subject" , "normalpublish");
                    b.putString("body" , expressId + "");
                    b.putInt("total_fee" , tip);
                    intent.putExtras(b);
                    startActivity(intent);
                }
                break;
            case R.id.publish_header_cancle:
                finish();
                break;
            case R.id.publish_express_deathtime:
                PickUtils.pickTime(PublishExpressActivity.this,deathtimeTextView);
                break;
        }
    }
}
