package com.coffee.coffeemall.contract;

import com.coffee.coffeemall.model.bean.HeaderBean;
import com.coffee.coffeemall.model.bean.RecommendBean;
import com.coffee.coffeemall.presenter.BasePresenter;
import com.coffee.coffeemall.view.BaseView;

import java.util.List;

/**
 * Created by Administrator on 2016/6/30.
 */
public interface FragmentContract {
    interface View extends BaseView<Presenter> {
        void setAdapter(List<HeaderBean> headerBeans, List<RecommendBean> recommendBeans);

    }
    interface Presenter extends BasePresenter {
        void loadData();
    }
}
