package com.midwives.classes;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

public class SmartLoginTemp extends AsyncTask<String, String, String>{
	private ProgressDialog dialog;
	private Context context;
	
	public SmartLoginTemp(String user, String password, String url){
		
		
	}

	@Override
	protected String doInBackground(String... params) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void onPreExecute() {
		// TODO Auto-generated method stub
		super.onPreExecute();
		dialog = new ProgressDialog(context);
		dialog.setTitle("SMART Login");
		dialog.setMessage("Connection...");
		dialog.setIndeterminate(false);
		dialog.setCancelable(true);
		dialog.show();
	}

	@Override
	protected void onPostExecute(String result) {
		// TODO Auto-generated method stub
		super.onPostExecute(result);
	}

	@Override
	protected void onProgressUpdate(String... values) {
		// TODO Auto-generated method stub
		super.onProgressUpdate(values);
	}

	

}
