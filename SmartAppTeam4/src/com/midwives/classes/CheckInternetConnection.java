package com.midwives.classes;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;


public class CheckInternetConnection {
	
	private Context context;
	
	public CheckInternetConnection(Context context){
		this.context=context;
	}
	public boolean isConnection(){
		
		ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		
		if(cm!= null){
			NetworkInfo[] info = cm.getAllNetworkInfo();
			if(info !=null){
				for(int i =0;i<info.length;i++) if(info[i].getState()==NetworkInfo.State.CONNECTED){
					return true;
				}
			}
		}
		return false;
	}
	

}
