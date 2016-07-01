package com.coffee.coffeemall.view.adapter;

import android.net.Uri;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.coffee.coffeemall.R;
import com.coffee.coffeemall.model.bean.HeaderBean;
import com.facebook.drawee.view.SimpleDraweeView;
import java.util.List;

/**
 * Created by Administrator on 2016/6/30.
 */
public class HeaderPagerAdapter extends PagerAdapter {
    private final LayoutInflater mInflater;
    private final List<HeaderBean> mDatas;

    public HeaderPagerAdapter(LayoutInflater inflater, List<HeaderBean> datas) {
        mInflater = inflater;
        mDatas = datas;
    }

    @Override
    public int getCount() {
        return mDatas.size();
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = mInflater.inflate(R.layout.item_roller_pic, container, false);
        TextView tv = (TextView) view.findViewById(R.id.roller_tv);
        tv.setText(mDatas.get(position).getName());
        SimpleDraweeView draweeView = (SimpleDraweeView) view.findViewById(R.id.roller_iv);
        draweeView.setImageURI(Uri.parse(mDatas.get(position).getImgUrl()));
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View)object);
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }
}
