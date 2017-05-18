package life.bean.com.beanlife.gesture;

import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import life.bean.com.beanlife.R;
import life.bean.com.beanlife.activity.PasswordActivity;
import life.bean.com.beanlife.utils.IntentUtils;

public class GestureEditActivity extends Activity implements OnClickListener {

	private ImageView im_back;
	private TextView tv_texttip;
	private RelativeLayout mGestureContainer;
	private GestureContentView mGestureContentView;
	// 第一次输入的密码
	private String mFirstPassword = null;
	// 是不是第一次输入
	private boolean mIsFirstInput = true;
	static final String TABLES_NAME_GESTURE = "gesture_password";

	public final static int GESTURE_TIME = 8;
	
	private publicSQLiteOpenHelper sqliteInstance;
	private SQLiteDatabase database;
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.gesture_edit);
		// 错误提示
		tv_texttip = (TextView) findViewById(R.id.tv_edit_texttip);
		// 9点区域
		mGestureContainer = (RelativeLayout) findViewById(R.id.fl_edit_gesture_container);
		// 返回
		im_back = (ImageView) findViewById(R.id.ll_head_left);
		// 初始化继续不可点击
		im_back.setOnClickListener(this);

		mGestureContentView = new GestureContentView(this, false, "",
				new GestureDrawline.GestureCallBack() {

					

					@Override
					public void onGestureCodeInput(String inputCode) {
					
						if (mIsFirstInput) { // 第一次输入手势密码
							if (!isInputPassValidate(inputCode)) {
								// 至少连接4个点
								tv_texttip.setText(getResources().getString(
										R.string.drawguesturewarning));
								// 2秒后清除界面
								mGestureContentView.clearDrawlineState(2000L);
								// 2秒后还原提示
								mHandler.postDelayed(connect4Dot, 2000);
								return;
							}else {
								mFirstPassword = inputCode;
								
								tv_texttip.setText(getResources().getString(
										R.string.drawguestureagain));
								mHandler.postDelayed(clearGreenLine, 1000);
								mIsFirstInput = false;
							}
						} else {
							if (inputCode.equals(mFirstPassword)) { // 第二次输入手势密码与第一次一致
								tv_texttip.setText(getResources().getString(
										R.string.your_gesture_code));

								sqliteInstance = new publicSQLiteOpenHelper(GestureEditActivity.this);
								database = sqliteInstance.getWritableDatabase();
								boolean count = sqliteInstance.queryGestureTableCount(database);
								if (count) {
									//如果有记录
									sqliteInstance.updateGestureInfo(database,mFirstPassword, "8");

								}else {
									//如果没有记录
									sqliteInstance.insertGestureInfo(database,mFirstPassword, "8");

								}

								mHandler.postDelayed(setGestureOkFinish, 1000);
								//两次密码一致，跳转至设置密码界面
								IntentUtils.startActivity(PasswordActivity.class);

							} else {

								if (!isInputPassValidate(inputCode)) {
									// 至少连接4个点
									tv_texttip.setText(getResources().getString(
											R.string.drawguesturewarning));
									// 2秒后清除界面
									mGestureContentView.clearDrawlineState(2000L);
									// 2秒后还原提示
									mHandler.postDelayed(connect4Dot, 2000);
								} else { // 第二次输入手势密码与第一次不一致
									tv_texttip.setText(getResources()
											.getString(R.string.drawagain));
									// 左右移动动画
//									 Animation shakeAnimation =
//									 AnimationUtils.loadAnimation(GestureEditActivity.this,R.anim.shake);
//									 tv_texttip.startAnimation(shakeAnimation);
									// 保持绘制的线，1.5秒后清除
									mGestureContentView
											.clearDrawlineState(2000L);
									mHandler.postDelayed(changeText2again, 2000);
								}

							}
						}
					
					}

					@Override
					public void checkedFail() {

					}

					@Override
					public void onGestureLineMove() {
						tv_texttip.setText(getResources().getString(
								R.string.release_hande_when_finish));
					}

					@Override
					public void onPointDown() {

					}

					@Override
					public void checkedSuccess() {
							//校验成功

					}
				});
		// 设置手势解锁显示到哪个布局里面
		mGestureContentView.setParentView(mGestureContainer);
	}

	@Override
	protected void onResume() {
		super.onResume();
	}

	Handler mHandler = new Handler();

	Runnable clearGreenLine = new Runnable() {
		public void run() {
			mGestureContentView.clearDrawlineState(0L);
		}
	};
	
	Runnable connect4Dot = new Runnable() {
		public void run() {
			tv_texttip.setText(getResources().getString(
					R.string.set_gesture_pattern));
		}
	};

	Runnable setGestureOkFinish = new Runnable() {
		public void run() {
			finish();
		}
	};
	Runnable changeText2again = new Runnable() {
		public void run() {
			tv_texttip.setText(getResources().getString(
					R.string.drawguestureagain));
		}
	};

	private boolean isInputPassValidate(String inputPassword) {
		if (TextUtils.isEmpty(inputPassword) || inputPassword.length() < 4) {
			return false;
		}
		return true;
	}


	protected void onDestroy() {
		super.onDestroy();
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.ll_head_left:
			finish();
			break;
		}
	}
}
