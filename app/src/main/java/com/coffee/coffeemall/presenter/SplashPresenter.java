package com.coffee.coffeemall.presenter;

import android.content.Context;
import android.view.View;

import com.coffee.coffeemall.contract.SplashContract;
import com.coffee.coffeemall.utils.NetUtils;

/**
 * Created by Administrator on 2016/6/29.
 */
public class SplashPresenter implements SplashContract.Presenter {

    private final SplashContract.View mView;
    private final Context mContext;

    public SplashPresenter(SplashContract.View view, Context context) {
        mView = view;
        mContext = context;

        checkNewVersion();
    }

    @Override
    public void checkNewVersion() {
        if(!NetUtils.isConnected(mContext)){
            mView.showNetError();
        }
    }

    @Override
    public void start() {

    }
}
