package com.midwives.parsers;

import java.io.IOException;
//import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class SmartAppPutPostDeleteData { 
	//fields
	private static  HttpURLConnection httpcon;
	private static String httpUrl;              //given by construction of table url plus id of item to update
	private static String authToken;	        // given by login classes
	private static String apiKey;               //given by login classes 
	private static String data;              	// string in JSON form  - comes from jason serialiser (ie. reverse parser)
	
	//constructor
	public SmartAppPutPostDeleteData() {
		
	}

	public static String getHttpUrl() {
		return httpUrl;
	}

	public static void setHttpUrl(String httpPutUrl) {
		SmartAppPutPostDeleteData.httpUrl = httpPutUrl;
	}

	public static String getAuthToken() {
		return authToken;
	}

	public static void setAuthToken(String authToken) {
		SmartAppPutPostDeleteData.authToken = authToken;
	}

	public static String getApiKey() {
		return apiKey;
	}

	public static void setApiKey(String apiKey) {
		SmartAppPutPostDeleteData.apiKey = apiKey;
	}

		
	public static String getData() {
		return data;
	}

	public static void setData(String data) {
		SmartAppPutPostDeleteData.data = data;
	}

	//method to put data returns true if success returns false if failed 
	public static boolean updatetItemByItemId(String httpUrl, String data, String apiKey, String authToken) {
		try {
			httpcon = (HttpURLConnection) ((new URL(httpUrl).openConnection()));
			URLEncoder.encode(httpUrl,"UTF-8");
			httpcon.setDoOutput(true);
			httpcon.setRequestProperty("Content-Type", "application/json");
			httpcon.setRequestProperty("Auth-Token", authToken);
			httpcon.addRequestProperty("Api-Key", apiKey);		
			httpcon.setRequestMethod("PUT");
			httpcon.connect();
						
			// form request
			byte[] inputBytes = data.getBytes("UTF-8");
			OutputStream os = httpcon.getOutputStream();
			os.write(inputBytes);
			System.out.println(os.toString());
			os.close();
			
			int httpResponseCode = httpcon.getResponseCode();
			
		    httpcon.disconnect();
		    System.out.println(httpResponseCode);
		    if((httpResponseCode == 200) || (httpResponseCode == 201)){
		    	return true;
		    }
		    
		    return false;  //if no success 
		      		      
		} catch (IOException e ) {
			e.printStackTrace(); //comment out in final code and handle with appropriate heads up for user
		}
		return false;	 // if exception thrown no success	
	}
	
	//method to delete data returns true if success returns false if failed 
	public static boolean deleteItemByItemId(String httpUrl, String apiKey, String authToken) {
		try {
			httpcon = (HttpURLConnection) ((new URL(httpUrl).openConnection()));
			URLEncoder.encode(httpUrl,"UTF-8");
			httpcon.setDoOutput(true);
			//httpcon.setRequestProperty("Content-Type", "application/json"); //note for delete this gives http 400 error 
			httpcon.setRequestProperty("Auth-Token", authToken);
			httpcon.addRequestProperty("Api-Key", apiKey);			
			httpcon.setRequestMethod("DELETE");
			httpcon.connect();
			
			int httpResponseCode = httpcon.getResponseCode();
			
		    httpcon.disconnect();
		    System.out.println(httpResponseCode);
		    if((httpResponseCode == 200) || (httpResponseCode == 201)){
		    	return true;
		    }
		    
		    return false;  //if no success 
		      		      
		} catch (IOException e ) {
			e.printStackTrace(); //comment out in final code and handle with appropriate heads up for user
		}
		return false;	 // if exception thrown no success	
	}
	
	//method to post data returns true if success returns false if failed 
	public static boolean createItem(String httpUrl, String data, String apiKey, String authToken) {
		try {
			httpcon = (HttpURLConnection) ((new URL(httpUrl).openConnection()));
			URLEncoder.encode(httpUrl,"UTF-8");
			httpcon.setDoOutput(true);
			httpcon.setRequestProperty("Content-Type", "application/json");
			httpcon.setRequestProperty("Auth-Token", authToken);
			httpcon.addRequestProperty("Api-Key", apiKey);		
			httpcon.setRequestMethod("POST");
			httpcon.connect();
						
			// form request
			byte[] inputBytes = data.getBytes("UTF-8");
			OutputStream os = httpcon.getOutputStream();
			os.write(inputBytes);
			System.out.println(os.toString());
			os.close();
			
			int httpResponseCode = httpcon.getResponseCode();
			
		    httpcon.disconnect();
		    System.out.println(httpResponseCode);
		    if((httpResponseCode == 200) || (httpResponseCode == 201)){
		    	return true;
		    }
		    
		    return false;  //if no success 
		      		      
		} catch (IOException e ) {
			e.printStackTrace(); //comment out in final code and handle with appropriate heads up for user
		}
		return false;	 // if exception thrown no success	
	}

}//close class
