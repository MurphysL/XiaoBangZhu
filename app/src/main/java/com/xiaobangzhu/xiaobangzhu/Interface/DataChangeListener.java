package com.xiaobangzhu.xiaobangzhu.Interface;

import com.android.volley.VolleyError;

/**
 * Created by WQC on 2016/8/12.
 */
public interface DataChangeListener<T> {
    void onSuccessful(T data);
    void onError(VolleyError volleyError);
    void onResponseNull();
}
