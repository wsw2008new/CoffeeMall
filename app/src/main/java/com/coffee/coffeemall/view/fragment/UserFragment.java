package com.coffee.coffeemall.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Administrator on 2016/6/30.
 */
public class UserFragment extends BaseFragment {

    public UserFragment() {
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        View view = inflater.inflate(R.layout.frg_home_hot, container, false);
        TextView textView = new TextView(getActivity());
        textView.setText("UserFragment");
        textView.setTextSize(50);
        return textView;
    }
}
