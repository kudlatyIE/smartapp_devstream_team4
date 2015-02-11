package com.midwives.smartappteam4;


import java.net.HttpURLConnection;

import com.midwives.classes.SmartAuth;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class LoginActivity extends Activity {

	private Button btnLogin;
	private Intent intent;
	private HttpURLConnection httpcon;
	private String loginUrl= "http://54.72.7.91:8888/login";
	private String tableUrl="http://54.72.7.91:8888/appointments";
	
	private String apiKey1="1b74f729-d05a-4573-b814-95e3c6713fe1";//denmark
	private String apiKey2 = "6f9a1abf-443e-4d18-a1a8-93dd39f69d6a";//andorra
	
	private String user1="team_denmark", user2="team_andorra";
	
	private String pass="smartappiscoming";
	private String result,id="no id",token="no token";
	private SmartAuth smartAuth;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		
		if (android.os.Build.VERSION.SDK_INT > 9) {
		      StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
		      StrictMode.setThreadPolicy(policy);
		    }
		smartAuth = new SmartAuth(user2,loginUrl, tableUrl,apiKey2,pass);
		this.token = smartAuth.getToken();
		
		
		btnLogin = (Button) findViewById(R.id.login_btn_login);
		btnLogin.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				intent = new Intent(getApplicationContext(), MainViewActivity.class);
				intent.putExtra("token",token );
				intent.putExtra("apiKey", apiKey2);
				intent.putExtra("tableUrl", tableUrl);
				intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(intent);	
			}
		});
	}//end onCreate
	

}
