package com.coffee.coffeemall.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.coffee.coffeemall.R;

/**
 * Created by Administrator on 2016/6/30.
 */
public class CartFragment extends BaseFragment {

    public CartFragment() {
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.frg_home_hot, container, false);
    }
}
