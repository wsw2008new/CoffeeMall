package com.coffee.coffeemall.view.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.coffee.coffeemall.R;
import com.coffee.coffeemall.utils.L;

/**
 * Created by Administrator on 2016/6/30.
 */
public class CustomToolbar extends Toolbar {

    private boolean mIsShowSearchView;
    private LayoutInflater mInflater;
    private EditText mEtSearch;

    public CustomToolbar(Context context) {
        this(context,null);
    }

    public CustomToolbar(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public CustomToolbar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.CustomToolbar, defStyleAttr, 0);
        int n = a.getIndexCount();
        for (int i = 0; i < n; i++)
        {
            int attr = a.getIndex(i);
            switch (attr)
            {
                case R.styleable.CustomToolbar_showSearchView:
                    mIsShowSearchView = a.getBoolean(attr,false);
                    break;
            }
        }
        a.recycle();

        mInflater = LayoutInflater.from(context);
        View toolbarView = mInflater.inflate(R.layout.toolbar_layout, null, false);
        LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        addView(toolbarView,params);
        mEtSearch = (EditText) toolbarView.findViewById(R.id.et_search);
        if(!mIsShowSearchView){
            mEtSearch.setVisibility(View.INVISIBLE);
        }
    }


}
