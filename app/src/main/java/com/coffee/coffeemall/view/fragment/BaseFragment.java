package com.coffee.coffeemall.view.fragment;


import android.app.AlertDialog;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.View;

/**
 * Created by Administrator on 2016/6/23.
 */
public class BaseFragment extends Fragment {
    protected Context mContext;
    protected AlertDialog mAlertDialog;

    @Override
    public void onAttach(Context context) {
        mContext = context;
        super.onAttach(context);

    }

    public void showDiaglog(String msg){
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        builder.setMessage(msg);
        mAlertDialog = builder.create();
        mAlertDialog.show();

    }
}
