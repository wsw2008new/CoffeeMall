package com.coffee.coffeemall.view.activity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.VideoView;

import com.coffee.coffeemall.R;
import com.coffee.coffeemall.common.Config;
import com.coffee.coffeemall.contract.SplashContract;
import com.coffee.coffeemall.presenter.SplashPresenter;
import com.coffee.coffeemall.utils.SPUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SplashActivity extends BaseActivity implements SplashContract.View {

    @BindView(R.id.vv_splash)
    VideoView mVvSplash;
    @BindView(R.id.bt_enter)
    Button mBtEnter;
    private SplashContract.Presenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        boolean isFirstEnter = (boolean) SPUtils.get(this, Config.IS_FIRST_ENTER, true);
        if(isFirstEnter){
            jumpToActivity();
            return;
        }
        mPresenter = new SplashPresenter(this, this);
        initView();
    }

    private void initView() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);//隐藏标题栏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);//隐藏状态栏
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);

        showVideo();
        mBtEnter.setOnClickListener((v)->{
            jumpToActivity();
            SPUtils.put(this, Config.IS_FIRST_ENTER,false);
        });

    }

    private void jumpToActivity() {
        Intent intent=new Intent(this,MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.start();
    }

    public void showVideo() {
        mVvSplash.setVideoURI(Uri.parse("android.resource://"+this.getPackageName()+"/"+R.raw.kr36));
        mVvSplash.start();
        mVvSplash.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                mVvSplash.start();

            }
        });
    }


    @Override
    public void showLoading(String msg) {

    }

    @Override
    public void hideLoading() {

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
        showTipsDialog(null,"网络错误");
    }
}
