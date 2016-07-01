package com.coffee.coffeemall.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.coffee.coffeemall.R;
import com.coffee.coffeemall.contract.FragmentContract;
import com.coffee.coffeemall.model.bean.HeaderBean;
import com.coffee.coffeemall.model.bean.RecommendBean;
import com.coffee.coffeemall.presenter.HomePresenter;
import com.coffee.coffeemall.view.adapter.MyRvAdapter;
import com.coffee.coffeemall.view.widget.CustomToolbar;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/6/30.
 */
public class HotFragment extends BaseFragment implements FragmentContract.View {

    @BindView(R.id.toolbar)
    CustomToolbar mToolbar;
    @BindView(R.id.appbar)
    AppBarLayout mAppbar;
    @BindView(R.id.rv)
    RecyclerView mRv;

    private View mRootView;
    private HomePresenter mHomePresenter;
    private LayoutInflater mInflater;
    private MyRvAdapter myRvAdapter;
    private View mHeaderView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mInflater = inflater;
        mRootView = inflater.inflate(R.layout.frg_home_hot, container, false);
        ButterKnife.bind(this, mRootView);


        mHomePresenter = new HomePresenter(this, getActivity());

        initView();
        return mRootView;
        
    }

    private void initView() {
        mHomePresenter.loadData();



    }


    @Override
    public void setAdapter(List<HeaderBean> headerBeans, List<RecommendBean> recommendBeans) {

        myRvAdapter =  new MyRvAdapter(mContext, headerBeans, recommendBeans);


        mHeaderView = mInflater.inflate(R.layout.header_home, null);
        myRvAdapter.setHeaderView(mHeaderView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
        mRv.setLayoutManager(layoutManager);

        mRv.setAdapter(myRvAdapter);
        myRvAdapter.setOnItemClickListener(new MyRvAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(View view, RecommendBean.Item item) {
                Toast.makeText(mContext,item.getTitle(),Toast.LENGTH_SHORT).show();
            }
        });
    }


    @Override
    public void showLoading(String msg) {
        showDiaglog(msg);
    }

    @Override
    public void hideLoading() {
        mAlertDialog.hide();
    }

    @Override
    public void showError(String msg, View.OnClickListener onClickListener) {

    }

    @Override
    public void showEmpty(String msg, View.OnClickListener onClickListener) {

    }

    @Override
    public void showEmpty(String msg, View.OnClickListener onClickListener, int imageId) {

    }

    @Override
    public void showNetError() {
        showDiaglog("网络错误");
    }
}
