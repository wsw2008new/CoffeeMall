package com.coffee.coffeemall;

import android.app.Application;

import com.coffee.coffeemall.model.http.ApiClient;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.stetho.Stetho;

/**
 * Created by Administrator on 2016/6/30.
 */
public class CoffeeMallApplication extends Application {
    private static CoffeeMallApplication mApplication;
    @Override
    public void onCreate() {
        super.onCreate();
        initRetrofit();
        mApplication = this;

        Fresco.initialize(this);

        //添加Stetho框架，之后可以用chrome浏览器调试网络
        Stetho.initialize(Stetho
                .newInitializerBuilder(this)
                .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                .enableWebKitInspector(
                        Stetho.defaultInspectorModulesProvider(this)).build());
    }

    private void initRetrofit() {
        ApiClient.init();
    }

    public static CoffeeMallApplication getInstance(){
        return mApplication;
    }
}
