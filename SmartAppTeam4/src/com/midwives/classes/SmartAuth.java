package com.midwives.classes;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import org.json.JSONException;
import org.json.JSONObject;


public class SmartAuth {

	private static String token;
	private static String jsonLogin;
//	private String tableURL;
	private static String apiKey;
	private static String data;// string with our JSON - for our parser


	
	private String appointemtJson;
	public SmartAuth(){}
	/**
	 * constructor used for login to system and get token
	 * @param user
	 * @param tableURL
	 * @param password
	 */
	public SmartAuth(String user,String tableURL, String password) {
//		this.tableURL = tableURL;
		SmartAuth.jsonLogin="{\"login\":{\"username\":\""+user+"\",\"password\":\""+password+"\"}}";
		String token = getTheAuthKey(tableURL);
		setToken(token);
		System.out.println(token);
	}

	/**
	 * constructor used before exchange data with DB: pass valid token and api key
	 * @param token
	 * @param key
	 */
	public SmartAuth(String token, String key){//to get json
		SmartAuth.token=token;
		SmartAuth.apiKey=key;
	}

	// get the auth key
	private static String getTheAuthKey(String loginURL) {
		HttpURLConnection httpcon;
		String login =getJsonLogin();
		try {
			httpcon = (HttpURLConnection) ((new URL(loginURL).openConnection()));
			URLEncoder.encode(loginURL,"UTF-8");
			httpcon.setDoOutput(true);
			httpcon.setRequestProperty("Content-Type", "application/json");
			httpcon.setRequestProperty("Accept", "application/json");
			httpcon.setRequestMethod("POST");
			httpcon.connect();
			byte[] inputBytes=login.getBytes("UTF-8");
			OutputStream os = httpcon.getOutputStream();
			os.write(inputBytes);
			System.out.println(os.toString());
			os.close();
			// grab the response
			InputStream is = httpcon.getInputStream();
		    int ch;
		    StringBuffer sb = new StringBuffer();
		    while ((ch = is.read()) != -1) {
		        sb.append((char) ch);
		      }
		      System.out.println(sb.toString());
		      String st = sb.toString();
		      
		      // create JSON Object to get Token using token key
		      JSONObject json = new JSONObject(st);
		      String strToken = (String) json.getJSONObject("login").get("token");
		      httpcon.disconnect();
		      return strToken;
		} catch (IOException | JSONException e) {
			e.printStackTrace();
		}return null;		
	}
	
	// using the auth key and api key we can access the database
	/**
	 * 
	 * @param token
	 * @return json String
	 */
	public static String accessTheDBTable(String tableURL) {
		URL obj;
		HttpURLConnection con;
		try {
			obj = new URL(tableURL);
			con = (HttpURLConnection) obj.openConnection();
			URLEncoder.encode(tableURL,"UTF-8");
			con.setRequestMethod("GET");
	 
			//add request header
			con.setRequestProperty("Api-Key", apiKey);
			con.setRequestProperty("Auth-Token", token);
	 
			int responseCode = con.getResponseCode();
			System.out.println("\nSending 'GET' request to URL : " + tableURL);
			System.out.println("Response Code : " + responseCode);
	 
			BufferedReader in = new BufferedReader(
			        new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();
	 
			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine+ "\n");
			}
			in.close();
			//print result
			data = response.toString();
//			System.out.println(data);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return data;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getApiKey() {
		return apiKey;
	}

	public void setApiKey(String apiKey2) {
		this.apiKey = apiKey2;
	}

	public static String getToken(){
		return token;
	}
	public void setToken (String str){
		SmartAuth.token=str;
	}
	public String getDataList() {
		return this.appointemtJson;
	}

	public void setDataList(String data) {
		this.appointemtJson = data;
	}

	public static String getJsonLogin() {
		return jsonLogin;
	}

	public static void setJsonLogin(String jsonLogin) {
		SmartAuth.jsonLogin = jsonLogin;
	}
}