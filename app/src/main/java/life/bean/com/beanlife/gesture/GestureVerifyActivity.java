package life.bean.com.beanlife.gesture;

import java.util.Timer;
import java.util.TimerTask;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import life.bean.com.beanlife.R;
import life.bean.com.beanlife.utils.IntentUtils;

public class GestureVerifyActivity extends Activity {
	private static String TAG = "GestureVerifyActivity";
	private TextView tv_texttip;
	private RelativeLayout mGestureContainer;
	private ImageView ib_back;
	private GestureContentView mGestureContentView;
	private Timer timer = new Timer() ;
	private TimerTask closeActivityTask = null;
	
	static final String TABLES_NAME_GESTURE = "gesture_password";
	private int remainopportunity ;
	
	private publicSQLiteOpenHelper sqliteInstance;
	private SQLiteDatabase database;
	private String way;

	private void resetCloseEvent(){
		clearCloseEvent() ;
		closeActivityTask = new TimerTask() {
			public void run() {
				Message message = new Message();
				message.what = 1;
				handler.sendMessage(message);
			}
		};
		timer.schedule(closeActivityTask, 30000); 
	}
	private void clearCloseEvent(){
		if(closeActivityTask != null){
			closeActivityTask.cancel() ;
			closeActivityTask = null ;
			Log.i(TAG, "----------closeActivityTask----------"+closeActivityTask);
		}
	}
	

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.gesture_verify);

		resetCloseEvent(); 
		Intent intent = getIntent();
		way = intent.getStringExtra("update");
		tv_texttip = (TextView) findViewById(R.id.tv_edit_texttip);
		ib_back = (ImageView) findViewById(R.id.ll_head_left);
		mGestureContainer = (RelativeLayout) findViewById(R.id.fl_verify_gesture_container);
		
		sqliteInstance = new publicSQLiteOpenHelper(GestureVerifyActivity.this);
		database = sqliteInstance.getWritableDatabase();
		//从数据库中获取手势密码、剩余次数
		firstPassward = sqliteInstance.queryGesturePassword(database);
		remainopportunityStr = sqliteInstance.queryGestureTime(database);
		if (TextUtils.isEmpty(remainopportunityStr)) {
			remainopportunity = 8;
			//初始化8次
			sqliteInstance.updateGestureInfo(database,null, "8");
		} else {
			remainopportunity = Integer.parseInt(remainopportunityStr);
		}
		if(remainopportunity == 0){
			tv_texttip.setTextColor(getResources().getColor(R.color.gestureline_red));
			tv_texttip.setText(getResources().getString(R.string.no_chance));
		}
		
		ib_back.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});
		mGestureContentView = new GestureContentView(this, true, firstPassward,
				new GestureDrawline.GestureCallBack() {
					@Override
					public void onGestureCodeInput(String inputCode) {
					}

					@SuppressLint("ShowToast")
					@Override
					public void checkedSuccess() {
						
						sqliteInstance.updateGestureInfo(database,null, "8");
						mGestureContentView.clearDrawlineState(0L);
						resetCloseEvent();
						if ("1".equals(way)){
							//跳转至设置密码界面
							IntentUtils.startActivity(GestureEditActivity.class);
						}
						finish();
						//发送广播
						sendBroadcast(new Intent("com.godinsec.seland.intent.CASIntent.INTENT_LOAD_SD"));
							
					}
					@Override
					public void checkedFail() {
						mGestureContentView.clearDrawlineState(2000L);
						tv_texttip.setTextColor(getResources().getColor(R.color.gestureline_red));
						if(remainopportunity > 0){
							remainopportunity-- ;
						}
						sqliteInstance.updateGestureInfo(database,null, ""+remainopportunity);
						resetCloseEvent();
						changeText();
						
					}

					@Override
					public void onGestureLineMove() {
						if(remainopportunity > 0){
							tv_texttip.setTextColor(getResources().getColor(R.color.textcolor_black_bb));
							tv_texttip.setText(getResources().getString(R.string.release_hande_when_finish));
						}
					}

					@Override
					public void onPointDown() {
						clearCloseEvent() ;
					}
					
				});
		mGestureContentView.setParentView(mGestureContainer);
	}
	
	protected void changeText() {
		Log.e("TAGG", "changeText");
		if (remainopportunity == 7) {
			tv_texttip.setText(getResources().getString(
					R.string.wrong_answer_seven));
		} else if (remainopportunity == 6) {
			tv_texttip.setText(getResources().getString(
					R.string.wrong_answer_six));
		} else if (remainopportunity == 5) {
			tv_texttip.setText(getResources().getString(
					R.string.wrong_answer_five));
		} else if (remainopportunity == 4) {
			tv_texttip.setText(getResources().getString(
					R.string.wrong_answer_four));
		} else if (remainopportunity == 3) {
			tv_texttip.setText(getResources().getString(
					R.string.wrong_answer_three));
		} else if (remainopportunity == 2) {
			tv_texttip.setText(getResources().getString(
					R.string.wrong_answer_two));
		} else if (remainopportunity == 1) {
			tv_texttip.setText(getResources().getString(
					R.string.wrong_answer_one));
		} else {
			tv_texttip.setText(getResources().getString(
					R.string.wrong_answer_zero));
			handler.postDelayed(errorFinish, 2000);
		}
	}

	@Override
	protected void onResume() {
		super.onResume();
	}
	
	Runnable errorFinish = new Runnable() {
		public void run() {
			finish();
		}
	};

	@SuppressLint("HandlerLeak")
	Handler handler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			switch (msg.what) {
			case 1:
				finish();
				break;
			}
		}
	};
	private String remainopportunityStr;
	private String firstPassward;

	protected void onDestroy() {
		super.onDestroy();
	}
	protected void onStop() {
		super.onStop();
	}
}
