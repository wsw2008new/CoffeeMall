package com.coffee.coffeemall.contract;

import com.coffee.coffeemall.presenter.BasePresenter;
import com.coffee.coffeemall.view.BaseView;

/**
 * Created by Administrator on 2016/6/29.
 */
public interface SplashContract {
    interface View extends BaseView<Presenter>{


    }
    interface Presenter extends BasePresenter{
        void checkNewVersion();
    }
}
