package com.coffee.coffeemall.view.activity;

import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;

import com.coffee.coffeemall.R;
import com.coffee.coffeemall.view.fragment.CartFragment;
import com.coffee.coffeemall.view.fragment.DiscoverFragment;
import com.coffee.coffeemall.view.fragment.HomeFragment;
import com.coffee.coffeemall.view.fragment.HotFragment;
import com.coffee.coffeemall.view.fragment.UserFragment;
import com.coffee.coffeemall.view.widget.TabFragmentHost;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity {

    private String mTextArray[] = { "首页", "热门", "探索", "购物车", "用户" };
    private Class mFragmentArray[] = { HomeFragment.class, HotFragment.class,
            DiscoverFragment.class, CartFragment.class, UserFragment.class };
    private int mImageArray[] = { R.drawable.home_selector,
            R.drawable.hot_selector, R.drawable.discover_selector,
            R.drawable.cart_selector, R.drawable.user_selector };

    @BindView(R.id.real_content)
    FrameLayout mRealContent;
    @BindView(android.R.id.tabcontent)
    FrameLayout mTabcontent;
    @BindView(android.R.id.tabhost)
    TabFragmentHost mTabhost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

//        FragmentManager fragmentManager = getSupportFragmentManager();
//        HomeFragment homeFragment = new HomeFragment();
//        ActivityUtils.addFragmentToActivity(fragmentManager,homeFragment,R.id.real_content);


        mTabhost.setup(this, getSupportFragmentManager(), R.id.real_content);

        for(int i=0;i<mTextArray.length;i++){
            TabHost.TabSpec tabSpec = mTabhost.newTabSpec(mTextArray[i]).setIndicator(getIndicator(i));
            mTabhost.addTab(tabSpec,mFragmentArray[i], null);
        }

        mTabhost.setCurrentTab(0);

    }

    private View getIndicator(int position) {
        LayoutInflater inflater=LayoutInflater.from(this);
        View view = inflater.inflate(R.layout.tab_item_view,null,false);
        ImageView imageView = (ImageView) view.findViewById(R.id.imageview);
        TextView textView = (TextView) view.findViewById(R.id.textview);
        textView.setText(mTextArray[position]);
        imageView.setImageResource(mImageArray[position]);
        return view;
    }

}
