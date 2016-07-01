package com.coffee.coffeemall.view.widget;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager.LayoutParams;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.coffee.coffeemall.R;
import com.coffee.coffeemall.utils.DensityUtils;

import butterknife.internal.Utils;

public class MsgDialog extends AlertDialog {
	private final int NORMAL_COLOR = Color.parseColor("#ffffff");
	private final int PRESSED_COLOR = Color.parseColor("#e5e5e5");
	private float mRadius;
	private String mTitleText, mMsgText, mLeftBtnText, mRightBtnText;
	private RightBtnClickListener mRightBtnClickListener;
	private LeftBtnClickListener mLeftBtnClickListener;
	private Context mContext;
	private static int theme = R.style.Msg_Dialog;

	public MsgDialog(Context context, String btnText, String title, String msg) {
		super(context, theme);
		this.mTitleText = title;
		this.mMsgText = msg;
		//this.mBtnText = btnText;
		this.mContext = context;
		mRadius = DensityUtils.dp2px(context, 5);
	}
	
	public MsgDialog(Context context, String title, String leftBtnText, String rightBtnText, String msg) {
		super(context, theme);
		this.mTitleText = title;
		this.mMsgText = msg;
		this.mRightBtnText = rightBtnText;
		this.mLeftBtnText = leftBtnText;
		this.mContext = context;
		mRadius = DensityUtils.dp2px(context, 5);
	}
	
	public MsgDialog(Context context) {
		super(context);
	}
	
	public void setTitleText(String title){
		this.mTitleText = title;
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setCanceledOnTouchOutside(false);
		setCancelable(false);

		int screnW = mContext.getResources().getDisplayMetrics().widthPixels;
		
		LinearLayout layout = (LinearLayout) View.inflate(mContext, R.layout.dialog_msg_customer, null);
		layout.setBackgroundDrawable(getBgStateDrawable());
		setContentView(layout);
		
		FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) layout.getLayoutParams();
		params.width = screnW * 4/5;
		layout.setLayoutParams(params);
		
		TextView leftBtn = (TextView)findViewById(R.id.left_btn);
		TextView rightBtn = (TextView)findViewById(R.id.right_btn);
		View centerLine = findViewById(R.id.center_line);
		
		if(mLeftBtnText != null){
			if(mRightBtnText != null){
				leftBtn.setVisibility(View.VISIBLE);
				leftBtn.setBackgroundDrawable(getLeftStateDrawable());
				
				centerLine.setVisibility(View.VISIBLE);
				rightBtn.setVisibility(View.VISIBLE);
				rightBtn.setBackgroundDrawable(getRightStateDrawable());
			}else {
				leftBtn.setVisibility(View.VISIBLE);
				leftBtn.setBackgroundDrawable(getBottomStateDrawable());
				
				centerLine.setVisibility(View.GONE);
				rightBtn.setVisibility(View.GONE);
			}
			
			leftBtn.setText(mLeftBtnText);
			leftBtn.setOnClickListener(onClickListener);
		}
		
		if(mRightBtnText != null){
			if(mLeftBtnText == null){
				rightBtn.setVisibility(View.VISIBLE);
				rightBtn.setBackgroundDrawable(getBottomStateDrawable());
				
				centerLine.setVisibility(View.GONE);
				leftBtn.setVisibility(View.GONE);
			}else {
				centerLine.setVisibility(View.VISIBLE);
			}
			
			rightBtn.setText(mRightBtnText);
			rightBtn.setOnClickListener(onClickListener);
		}
		
		if(mLeftBtnText == null && mRightBtnText == null){
			leftBtn.setVisibility(View.VISIBLE);
			leftBtn.setBackgroundDrawable(getBottomStateDrawable());
			
			centerLine.setVisibility(View.GONE);
			rightBtn.setVisibility(View.GONE);
			
			leftBtn.setText(mContext.getString(R.string.confirm));
			leftBtn.setOnClickListener(onClickListener);
		}
		
		
		TextView titleText = (TextView)findViewById(R.id.title);
		if(mTitleText != null){
			titleText.setVisibility(View.VISIBLE);
			titleText.setText(mTitleText);
			findViewById(R.id.title_line).setVisibility(View.VISIBLE);
		}else {
			titleText.setVisibility(View.GONE);
			findViewById(R.id.title_line).setVisibility(View.GONE);
		}
		
		TextView msgText = (TextView)findViewById(R.id.message);
		if(mMsgText != null){
			msgText.setVisibility(View.VISIBLE);
			msgText.setText(mMsgText);
		}
	};

	View.OnClickListener onClickListener = new View.OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			if(v.getId() == R.id.left_btn){
				
			}
			int id = v.getId();
			if (id == R.id.left_btn) {
				if(mLeftBtnClickListener != null){
					mLeftBtnClickListener.onClick();
				}
				cancel();
			} else if (id == R.id.right_btn) {
				if(mRightBtnClickListener != null){
					mRightBtnClickListener.onClick();
				}
				cancel();
			} else {
			}
		}
	};
	
	public void showDialog(){
		Window win = this.getWindow();
		LayoutParams params = new LayoutParams();
		params.gravity = Gravity.CENTER;
		win.setAttributes(params);
		setCanceledOnTouchOutside(true);
		show();
	}
	
	public interface LeftBtnClickListener{
		public void onClick();
	}
	
	public void setLeftBtnClickListener(LeftBtnClickListener tickClickListener){
		this.mLeftBtnClickListener = tickClickListener;
	}
	
	public interface RightBtnClickListener{
		public void onClick();
	}
	
	public void setRightClickListener(RightBtnClickListener noTickClickListener){
		this.mRightBtnClickListener = noTickClickListener;
	}
	
	private StateListDrawable getLeftStateDrawable() {
		StateListDrawable sd = new StateListDrawable();

		float rad[] = { 0, 0, 0, 0, 0, 0 , mRadius, mRadius};

		GradientDrawable normal = new GradientDrawable();// 创建drawable
		normal.setColor(NORMAL_COLOR);// 内部填充颜色
		normal.setCornerRadii(rad);

		GradientDrawable pressed = new GradientDrawable();// 创建drawable
		pressed.setColor(PRESSED_COLOR);// 内部填充颜色
		pressed.setCornerRadii(rad);

		// 注意该处的顺序，只要有一个状态与之相配，背景就会被换掉
		sd.addState(new int[] { android.R.attr.state_pressed }, pressed);
		sd.addState(new int[] { android.R.attr.state_enabled }, normal);

		return sd;
	}
	
	private StateListDrawable getRightStateDrawable() {
		StateListDrawable sd = new StateListDrawable();

		float rad[] = { 0, 0, 0, 0, mRadius, mRadius, 0, 0 };

		GradientDrawable normal = new GradientDrawable();// 创建drawable
		normal.setColor(NORMAL_COLOR);// 内部填充颜色
		normal.setCornerRadii(rad);

		GradientDrawable pressed = new GradientDrawable();// 创建drawable
		pressed.setColor(PRESSED_COLOR);// 内部填充颜色
		pressed.setCornerRadii(rad);

		// 注意该处的顺序，只要有一个状态与之相配，背景就会被换掉
		sd.addState(new int[] { android.R.attr.state_pressed }, pressed);
		sd.addState(new int[] { android.R.attr.state_enabled }, normal);

		return sd;
	}
	
	private StateListDrawable getBottomStateDrawable() {
		StateListDrawable sd = new StateListDrawable();

		float rad[] = { 0, 0, 0, 0, mRadius, mRadius, mRadius, mRadius };

		GradientDrawable normal = new GradientDrawable();// 创建drawable
		normal.setColor(NORMAL_COLOR);// 内部填充颜色
		normal.setCornerRadii(rad);

		GradientDrawable pressed = new GradientDrawable();// 创建drawable
		pressed.setColor(PRESSED_COLOR);// 内部填充颜色
		pressed.setCornerRadii(rad);

		// 注意该处的顺序，只要有一个状态与之相配，背景就会被换掉
		sd.addState(new int[] { android.R.attr.state_pressed }, pressed);
		sd.addState(new int[] { android.R.attr.state_enabled }, normal);

		return sd;
	}
	
	private StateListDrawable getBgStateDrawable() {
		StateListDrawable sd = new StateListDrawable();

		float rad[] = { mRadius, mRadius, mRadius, mRadius, mRadius, mRadius, mRadius, mRadius };

		GradientDrawable normal = new GradientDrawable();// 创建drawable
		normal.setColor(NORMAL_COLOR);// 内部填充颜色
		normal.setCornerRadii(rad);

		sd.addState(new int[] { android.R.attr.state_enabled }, normal);

		return sd;
	}
	
	/*private StateListDrawable addBottomStateDrawable() {
    StateListDrawable sd = new StateListDrawable();
    //Drawable normal = idNormal == -1 ? null : context.getResources().getDrawable(idNormal);
    //Drawable pressed = idPressed == -1 ? null : context.getResources().getDrawable(idPressed);
    //Drawable focus = idFocused == -1 ? null : context.getResources().getDrawable(idFocused);
   
    GradientDrawable normal = new GradientDrawable();//创建drawable
    normal.setColor(Color.parseColor("#ffffff"));//内部填充颜色
    normal.setCornerRadii(new float[]{0, 0, 0, 0, radius, radius, radius, radius});
    normal.setCornerRadius(30);//圓角
    
    GradientDrawable pressed = new GradientDrawable();//创建drawable
    pressed.setColor(Color.parseColor("#C7EDCC"));//内部填充颜色
    pressed.setCornerRadius(30);//圓角
    pressed.setCornerRadii(new float[]{0, 0, 0, 0, radius, radius, radius, radius});
    
    //注意该处的顺序，只要有一个状态与之相配，背景就会被换掉
    //所以不要把大范围放在前面了，如果sd.addState(new[]{},normal)放在第一个的话，就没有什么效果了
    //sd.addState(new int[]{android.R.attr.state_enabled, android.R.attr.state_focused}, focus);
    //sd.addState(new int[]{android.R.attr.state_pressed, android.R.attr.state_enabled}, pressed);
    //sd.addState(new int[]{android.R.attr.state_focused}, focus);
    sd.addState(new int[]{android.R.attr.state_pressed}, pressed);
    sd.addState(new int[]{android.R.attr.state_enabled}, normal);
    //sd.addState(new int[]{}, normal);
    
    return sd;
}*/
}
