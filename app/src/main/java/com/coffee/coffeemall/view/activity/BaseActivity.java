package com.coffee.coffeemall.view.activity;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.coffee.coffeemall.R;
import com.coffee.coffeemall.view.widget.MsgDialog;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

/**
 * Created by Administrator on 2016/6/23.
 */
public abstract class BaseActivity extends AppCompatActivity {
    private List<BaseActivity> mActivities = new ArrayList<>();
    protected AlertDialog mAlert;
    public ProgressDialog mDialog;
    public static final int Dialog_Normal = 1000, Dialog_CanntCancel = 1002,
            Dialog_UnZip = 1003;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivities.add(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mActivities.remove(this);
    }

    public void exitApp() {
        if (null != mActivities && !mActivities.isEmpty()) {
            try {
                for (BaseActivity activity :
                        mActivities) {
                    activity.finish();
                }
            } catch (Exception e) {
                System.exit(0);
            }
        }
    }



    protected void showTipsDialog(String title, String tipsMsg) {
        try {
            MsgDialog msgDialog = new MsgDialog(this,
                    getString(R.string.confirm), title, tipsMsg);
            msgDialog.showDialog();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            Toast.makeText(this, tipsMsg, Toast.LENGTH_SHORT).show();
        }
    }

    protected void showMsgRetryDialog(String msg) {
        if (msg != null && msg.length() > 0) {
            AlertDialog.Builder alertBuilder = new AlertDialog.Builder(this);
            if (mAlert == null) {
                mAlert = alertBuilder.create();
                mAlert.setButton(DialogInterface.BUTTON_NEGATIVE,
                        getString(R.string.cancel),
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog,
                                                int which) {

                            }
                        });
                mAlert.setButton(DialogInterface.BUTTON_POSITIVE,
                        getString(R.string.retry),
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog,
                                                int which) {

                            }
                        });
            }
            mAlert.setMessage(msg);
            mAlert.show();
        }
    }
}
