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

	private HttpURLConnection httpcon;

	private static String token;
	private String thing;
	private String tableURL;
	private String apiKey;
	private String data;// string with our JSON - for our parser

	private static String loginUrl= "http://54.72.7.91:8888/login";
	private static String tableUrl="http://54.72.7.91:8888/appointments";


	//	
//	private String apiKey1="1b74f729-d05a-4573-b814-95e3c6713fe1";//denmark
	private static String apiKey2 = "6f9a1abf-443e-4d18-a1a8-93dd39f69d6a";//andorra
//	
//	private String user1="team_denmark", user2="team_andorra";
//	
//	private String pass="smartappiscoming";
	
	private String appointemtJson;
	public SmartAuth(){}
	
	public SmartAuth(String user, String loginURL, String tableURL, String key, String password) {
		this.tableURL = tableURL;
		this.apiKey=key;
		this.thing="{\"login\":{\"username\":\""+user+"\",\"password\":\""+password+"\"}}";
		String token = getTheAuthKey(loginURL);
		System.out.println(token);
//		this.appointemtJson=accessTheDBTable(token);
	}
	
	public SmartAuth(String token, String apiKey, String tableUrl){//to get json
		this.token=token;
		this.apiKey=apiKey;
		this.tableURL=tableUrl;
		
	}

	public static String getTableUrl() {
		return tableUrl;
	}

	
	public String getTableURL() {
		return tableURL;
	}

//	public void setTableURL(String tableURL) {
//		this.tableURL = tableURL;
//	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public static String getApiKey() {
		return apiKey2;
	}

	public void setApiKey(String apiKey2) {
		this.apiKey2 = apiKey2;
	}

	public static String getToken(){
		return token;
	}
	public void setToken (String str){
		this.token=str;
	}
	public String getDataList() {
		return this.appointemtJson;
	}

	public void setDataList(String data) {
		this.appointemtJson = data;
	}

	// get the auth key
	private String getTheAuthKey(String loginURL) {
		try {
			httpcon = (HttpURLConnection) ((new URL(loginURL).openConnection()));
			URLEncoder.encode(loginURL,"UTF-8");
			httpcon.setDoOutput(true);
			httpcon.setRequestProperty("Content-Type", "application/json");
			httpcon.setRequestProperty("Accept", "application/json");
			httpcon.setRequestMethod("POST");
			httpcon.connect();
			// form request
			byte[] inputBytes = thing.getBytes("UTF-8");
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
		      String strToken = (String) ((JSONObject) json.get("login")).get("token");
		      System.out.println(strToken);
		      httpcon.disconnect();
		      setToken(strToken);
		      return strToken;
		} catch (IOException | JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}return null;		
	}
	
	// using the auth key and api key we can access the database
	/**
	 * 
	 * @param token
	 * @return json String
	 */
	public String accessTheDBTable(String token) {
		URL obj;
		try {
			obj = new URL(tableURL);
			HttpURLConnection con = (HttpURLConnection) obj.openConnection();
			 
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
}

//----new stuff crashed-------------------------------

//public class SmartAuth {
//
//	private HttpURLConnection httpcon;
//
//	private String token;
//	private String thing;
//	private String tableUrl;
//	private String apiKey;
//	private String data;// string with our JSON - for our parser
//	
//	private static String statToken, statApiKey, statTableUrl;
//
//
//	private String appointemtJson;
//	
//	public SmartAuth(){}
//	
////	public SmartAuth(String user, String loginURL, String tableURL, String key, String password) {
////		this.tableUrl = tableURL;
////		this.apiKey=key;
////		this.thing="{\"login\":{\"username\":\""+user+"\",\"password\":\""+password+"\"}}";
////		String token = getTheAuthKey(loginURL);
////		System.out.println(token);
//////		this.appointemtJson=accessTheDBTable(token);
////	}
//	
//	public SmartAuth(String token, String apiKey, String tableUrl){//to get json
//		this.token=token;
//		this.apiKey=apiKey;
//		this.tableUrl=tableUrl;	
//	}
//
//
//
//	// get the auth key
//	public  String getTheAuthKey(String user, String password,String loginURL) throws IOException, IOException, JSONException {
//		thing="{\"login\":{\"username\":\""+user+"\",\"password\":\""+password+"\"}}";
////		try {
//			httpcon = (HttpURLConnection) ((new URL(loginURL).openConnection()));
//			URLEncoder.encode(loginURL,"UTF-8");
////			httpcon.setDoOutput(true);
//			httpcon.setRequestProperty("Content-Type", "application/json");
//			httpcon.setRequestProperty("Accept", "application/json");
//			httpcon.setRequestMethod("POST");
//			httpcon.connect();
//			// form request
//			byte[] inputBytes = thing.getBytes("UTF-8");
//			OutputStream os = httpcon.getOutputStream();
//			os.write(inputBytes);
//			System.out.println(os.toString());
//			os.close();
//			// grab the response
//			InputStream is = httpcon.getInputStream();
//		    int ch;
//		    StringBuffer sb = new StringBuffer();
//		    while ((ch = is.read()) != -1) {
//		        sb.append((char) ch);
//		      }
//		      System.out.println(sb.toString());
//		      String st = sb.toString();
//		      
//		      // create JSON Object to get Token using token key
//		      JSONObject json = new JSONObject(st);
//		      String strToken = (String) ((JSONObject) json.get("login")).get("token");
//		      System.out.println(strToken);
//		      httpcon.disconnect();
//		      setStatToken(strToken);
//		      return strToken;
////		} catch (IOException | JSONException e) {
////			e.printStackTrace();
////		}
////		return null;		
//	}
//	
//	// using the auth key and api key we can access the database
////	public String accessTheDBTable(String token, String apiKey, String tableUrl) throws IOException {
//	public String accessTheDBTable() throws IOException {
//		BufferedReader in = null;
//		apiKey=getStatApiKey();
//		token=getStatToken();
//		tableUrl=getStatTableUrl();
//		System.out.println("accessTheDBTable - token: "+token);
//		System.out.println("accessTheDBTable --- key: "+apiKey);
//		System.out.println("accessTheDBTable --- url: "+tableUrl);
//		
////		try {
//			
//			HttpURLConnection con = (HttpURLConnection) (new URL(tableUrl)).openConnection();
//			URLEncoder.encode(tableUrl,"UTF-8");
//			//add request header
//			con.setRequestProperty("Api-Key", apiKey);
//			con.setRequestProperty("Auth-Token", token);
//			con.setRequestMethod("GET");
//			
//			con.connect();
//			
//			int responseCode = con.getResponseCode();
//			System.out.println("\nSending 'GET' request to URL : " + tableUrl);
//			System.out.println("Response Code : " + responseCode);
//			
//			in = new BufferedReader(new InputStreamReader(con.getInputStream()));
//			
//			String inputLine;
//			StringBuffer response = new StringBuffer();
//	 
//			while ((inputLine = in.readLine()) != null) {
//				response.append(inputLine+ "\n");
//			}
//			in.close();
//			con.disconnect();
//			//print result
//			data = response.toString();
////			System.out.println(data);
////		} catch (IOException e) {
////			// TODO Auto-generated catch block
////			e.printStackTrace();
////		}
//		return data;
//	}
//	
//	public String getTableUrl() {
//		return tableUrl;
//	}
//	public String getTableURL() {
//		return tableUrl;
//	}
//
//	public String getData() {
//		return data;
//	}
//
//	public void setData(String data) {
//		this.data = data;
//	}
//
//	public String getApiKey() {
//		return apiKey;
//	}
//
//	public void setApiKey(String apiKey2) {
//		this.apiKey = apiKey2;
//	}
//
//	public String getToken(){
//		return token;
//	}
//	public void setToken (String str){
//		this.token=str;
//	}
//	public String getDataList() {
//		return this.appointemtJson;
//	}
//
//	public void setDataList(String data) {
//		this.appointemtJson = data;
//	}
//
//	public static String getStatToken() {
//		return statToken;
//	}
//
//	public static void setStatToken(String statToken) {
//		SmartAuth.statToken = statToken;
//	}
//
//	public static String getStatApiKey() {
//		return statApiKey;
//	}
//
//	public static void setStatApiKey(String statApiKey) {
//		SmartAuth.statApiKey = statApiKey;
//	}
//
//	public static String getStatTableUrl() {
//		return statTableUrl;
//	}
//
//	public static void setStatTableUrl(String statTableUrl) {
//		SmartAuth.statTableUrl = statTableUrl;
//	}
//}