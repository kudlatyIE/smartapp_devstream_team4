package com.midwives.smartappteam4;

import com.midwives.classes.CheckInternetConnection;

import android.app.Activity;
import android.os.Handler;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class SplashScreenActivity extends Activity {
	
	private final static int SPLASH_TIME_OUT = 1500;
	private final static String GOOD="Welcome", BAD="no internet connection";
	private TextView msg;
	private Intent intent;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash_screen);
		
		msg = (TextView) findViewById(R.id.text_splash_msg);
		
		boolean isConnection = false;
		CheckInternetConnection checker = new CheckInternetConnection(getApplicationContext());
		
		isConnection = checker.isConnection();
		
		if(false==isConnection) {
			msg.setText(BAD);
		}else {
			msg.setText(GOOD);
			new Handler().postDelayed(new Runnable(){

				@Override
				public void run() {
					intent = new Intent(getApplicationContext(),LoginActivity.class);
					startActivity(intent);
					finish();
				}
			
			}, SPLASH_TIME_OUT);
		}
	}
}
