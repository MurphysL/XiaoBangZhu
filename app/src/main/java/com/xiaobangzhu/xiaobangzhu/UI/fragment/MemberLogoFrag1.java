package com.xiaobangzhu.xiaobangzhu.UI.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xiaobangzhu.xiaobangzhu.R;

/**
 * MemberLogoFrag1
 *
 * @author: MurphySL
 * @time: 2017/1/26 15:58
 */


public class MemberLogoFrag1 extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.frag_member1 , container , false);
        return root;
    }
}
