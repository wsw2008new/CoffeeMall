package com.coffee.coffeemall.view.bases;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.List;

/**
 * Created by Administrator on 2016/6/25.
 */
public abstract class BaseRvHolder<T> extends RecyclerView.ViewHolder
{

    public BaseRvHolder(View itemView) {
        super(itemView);
    }

    /**
     * 设置要显示的数据
     * @param datas 数据，转换成对应的数据对象
     * @param position 当前item的位置
     */
    public abstract void setData(int position,List<T> datas);

}