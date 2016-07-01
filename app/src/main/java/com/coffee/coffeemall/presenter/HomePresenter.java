package com.coffee.coffeemall.presenter;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import com.coffee.coffeemall.contract.FragmentContract;
import com.coffee.coffeemall.model.bean.HeaderBean;
import com.coffee.coffeemall.model.bean.RecommendBean;
import com.coffee.coffeemall.model.http.ApiClient;
import com.coffee.coffeemall.utils.HttpUtils;
import com.coffee.coffeemall.utils.L;
import com.coffee.coffeemall.utils.NetUtils;

import java.util.List;
import java.util.concurrent.CountDownLatch;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Administrator on 2016/6/30.
 */
public class HomePresenter implements FragmentContract.Presenter {
    private final FragmentContract.View mView;
    private final Context mContext;

    private List<RecommendBean> mRecommendBeans;
    private List<HeaderBean> mHeaderBeens;

    private static CountDownLatch latch = new CountDownLatch(2);

    public HomePresenter(FragmentContract.View view, Context context) {
        mView = view;
        mContext = context;
    }

    @Override
    public void start() {
        L.e(NetUtils.isConnected(mContext)+":NetUtils.isConnected(mContext)");
        if(!NetUtils.isConnected(mContext)){
            mView.showNetError();
            return;
        }
        loadData();
    }

    @Override
    public void loadData() {
        mView.showLoading("正在拼命加载中");
        Call<List<HeaderBean>> headerData = (Call<List<HeaderBean>>) ApiClient.mHttpService.getHeaderData("1");
        headerData.enqueue(new Callback<List<HeaderBean>>() {

            @Override
            public void onResponse(Call<List<HeaderBean>> call, Response<List<HeaderBean>> response) {
                mHeaderBeens = response.body();
                latch.countDown();
            }

            @Override
            public void onFailure(Call<List<HeaderBean>> call, Throwable t) {

            }
        });

        Call<List<RecommendBean>> recommendData = ApiClient.mHttpService.getRecommendData();

        recommendData.enqueue(new Callback<List<RecommendBean>>() {
            @Override
            public void onResponse(Call<List<RecommendBean>> call, Response<List<RecommendBean>> response) {
                mRecommendBeans = response.body();
                latch.countDown();
            }

            @Override
            public void onFailure(Call<List<RecommendBean>> call, Throwable t) {

            }
        });

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    latch.await();
                    Handler handler = new Handler(Looper.getMainLooper());
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            mView.hideLoading();
                            mView.setAdapter(mHeaderBeens, mRecommendBeans);
                        }
                    });
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}