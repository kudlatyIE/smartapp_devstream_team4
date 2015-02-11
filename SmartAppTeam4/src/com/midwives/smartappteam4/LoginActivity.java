package com.midwives.smartappteam4;



import com.midwives.classes.SmartAuth;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;



import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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

	

//Chris stuff

//	public class LoginActivity extends Activity implements View.OnClickListener {
//		private final String CHECK_NETWORK_CONNECTIVITY_URL = "http://www.google.com";
//		private static String loginURL = "http://54.72.7.91:8888/login";
//		private static String loginUsername = "team_andorra";
//		private static String loginPassword = "smartappiscoming";
//		private Button btnLogin;
//		private EditText userName;
//		private EditText password;
//		private Intent intent;
//		
//		@Override
//		protected void onCreate(Bundle savedInstanceState) {
//			super.onCreate(savedInstanceState);
//			setContentView(R.layout.activity_login);
//			
//			userName = (EditText)findViewById(R.id.login_edit_username);
//			password = (EditText)findViewById(R.id.login_edit_password);
//			
//			btnLogin = (Button) findViewById(R.id.login_btn_login);
//			btnLogin.setOnClickListener(this);
//			
//		}//close method oncreate
//		
//
//
//		@Override
//		public void onClick(View v) {
//			userName = (EditText)findViewById(R.id.login_edit_username);
//			password = (EditText)findViewById(R.id.login_edit_password);
//			loginUsername = userName.getText().toString();
//			loginPassword = password.getText().toString();
//			
//			switch(v.getId()){
//			case R.id.login_btn_login:
//				if(loginUsername.equals("") && loginPassword.equals("")){
//					Toast.makeText(getApplicationContext(), "Missing password and username", Toast.LENGTH_LONG).show();
//				} else if (loginUsername.equals("")){
//					Toast.makeText(getApplicationContext(), "Missing userName", Toast.LENGTH_LONG).show();
//				} else if (loginPassword.equals("")){
//					Toast.makeText(getApplicationContext(), "Missing password", Toast.LENGTH_LONG).show();
//				} else {
//				    CheckNetworkConnect(v);
//				}
//				break;
//				
//			}//close switch
//			
//		}//close method onclick
//		
//		
//	    public void CheckNetworkConnect(View view){             //calls class asynctask netcheck
//	        new NetCheck().execute();
//	    }
//		
//		
//		 //Async Task to check whether internet connection is working.
//
//	    private class NetCheck extends AsyncTask<String, Void, Boolean> { //calls asynctask process login
//	        private ProgressDialog nDialog;
//	        
//	        @Override
//	        protected void onPreExecute(){
//	            super.onPreExecute();
//	            nDialog = new ProgressDialog(LoginActivity.this);
//	            nDialog.setTitle("Checking Network");
//	            nDialog.setMessage("Loading...");
//	            nDialog.setIndeterminate(false);
//	            nDialog.setCancelable(true);
//	            nDialog.show();
//	        }
//	        
//	        @Override
//	        protected Boolean doInBackground(String... args){
//	                   //Gets current device state and checks for working internet connection by trying Google.
//	            ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
//	            NetworkInfo netInfo = cm.getActiveNetworkInfo();
//	            if (netInfo != null && netInfo.isConnected()) {
//	                try {
//	                    URL url = new URL(CHECK_NETWORK_CONNECTIVITY_URL);
//	                    HttpURLConnection urlc = (HttpURLConnection) url.openConnection();
//	                    urlc.setConnectTimeout(3000);
//	                    urlc.connect();
//	                    if (urlc.getResponseCode() == 200) {
//	                        return true;
//	                    }
//	                } catch (MalformedURLException e1) {
//	                    // TODO Auto-generated catch block
//	                    e1.printStackTrace();
//	                } catch (IOException e) {
//	                    // TODO Auto-generated catch block
//	                    e.printStackTrace();
//	                }
//	            }
//	            return false;
//	        }
//	        
//	        @Override
//	        protected void onPostExecute(Boolean th){
//	            if(th == true){
//	                nDialog.dismiss();
//	                //Toast.makeText(getApplicationContext(), "Network Connection good", Toast.LENGTH_LONG).show();
//	                 new ProcessLogin().execute();              
//	            }
//	            else{
//	                nDialog.dismiss();
//	                Toast.makeText(getApplicationContext(), "Error in Network Connection", Toast.LENGTH_LONG).show();
//	            }
//	        }
//	        
//	    } //close class netcheck asynctask
//	    
//	    private class ProcessLogin extends AsyncTask<String, Void, String> {
//	        private ProgressDialog pDialog;
//	              
//	        @Override
//	        protected void onPreExecute() {
//	            super.onPreExecute();
//	           
//	            pDialog = new ProgressDialog(LoginActivity.this);
//	            pDialog.setTitle("Contacting Servers");
//	            pDialog.setMessage("Logging in ...");
//	            pDialog.setIndeterminate(false);
//	            pDialog.setCancelable(true);
//	            pDialog.show();
//	        }
//	        
//	        @Override       
//	        protected String doInBackground(String... args) {
//	            LoginGetAuthToken getAuthToken = new LoginGetAuthToken ();
//	            String s =  getAuthToken.getTheAuthKey(loginURL, loginUsername, loginPassword);
//	            return s;
//	        }
//	        
//	        @Override
//	        protected void onPostExecute(String authToken) {
//	            try {
//	                  if (authToken != null) {         
//	        				intent = new Intent(getApplicationContext(), MainViewActivity.class);
//	        				intent.putExtra("Token", authToken);
//	        				intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//	        				pDialog.dismiss();
//	        				startActivity(intent);                      
//	                                                
//	                       // finish();// Close Login Screen 
//	                        
//	                    } else {
//	                        pDialog.dismiss();
//	                        Toast.makeText(getApplicationContext(),"Incorrect username or password", Toast.LENGTH_LONG).show();
//	                    }
//	                } catch (Exception e) {
//	                      e.printStackTrace();
//	                }
//	       }
//	        
//	    }//close class processlogin async task
//	    	
//	}//close class loginactivity