package com.midwives.smartappteam4;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import org.json.JSONObject;

public class LoginGetAuthToken {
	private HttpURLConnection httpcon;
	private int responseCode;
	private String userName;
	private String passWord;
	private String JsonObjectKey;

	public LoginGetAuthToken() {
		
	}
	
	// get the auth key
	//this note  be removed : at mo the null return is due to an exception being thrown after passing through the catch block 
	//would be stabler code if this was managed before exception see note at line  'getInputStream() below
	public String getTheAuthKey(String loginURL, String userName, String passWord) {
		try {
			httpcon = (HttpURLConnection) ((new URL(loginURL).openConnection()));
			URLEncoder.encode(loginURL,"UTF-8");
			httpcon.setDoOutput(true);
			httpcon.setRequestProperty("Content-Type", "application/json");
			httpcon.setRequestProperty("Accept", "application/json");
			httpcon.setRequestMethod("POST");			
			httpcon.connect();
			
						// structure  request
						String s = "{\"login\":{\"username\":\"" + userName + "\",\"password\":\"" + passWord + "\"}}";
						byte[] inputBytes = s.getBytes("UTF-8");
						OutputStream os = httpcon.getOutputStream();
						os.write(inputBytes);
						os.close();
			
			// grab the response
			InputStream is = httpcon.getInputStream(); //at this point if wrong username / password throws IOexception a http 422 error.
		    int ch;
		    StringBuffer sb = new StringBuffer();
		    while ((ch = is.read()) != -1) {
		        sb.append((char) ch);
		      }

		      String st = sb.toString();
		      
		      JSONObject json = new JSONObject(st); // can be jason 'login' object or jason 'errors' object 
		      
		      String strToken = (String) ((JSONObject) json.get("login")).get("token");
		      responseCode = httpcon.getResponseCode();
		      httpcon.disconnect();
		      return strToken;
		      
		} catch (Exception e) {                             	
			e.printStackTrace();
		} 
		return null;			
	}

}//close class logingetauthtoken
