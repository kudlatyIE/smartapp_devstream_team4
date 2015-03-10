package com.midwives.smartappteam4;

import java.util.ArrayList;

import com.midwives.classes.*;
import com.midwives.parsers.ServiceOptionsParser;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class ServiceOptionActivity extends Activity {
	

	private Intent intent;
	private Button btnBack, btnHome, btnBook;
	private TextView tvTitle, tvSubtitle;
	private ArrayList<ServiceOptions> myList;
//	private String token, apiKey, url, jsonString;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_service_option);
		

//		url = getResources().getString(R.string.auth_url_server).concat(getResources().getString(R.string.auth_url_service_options));
		
		btnBack = (Button) findViewById(R.id.header_btn_back);
		btnHome = (Button) findViewById(R.id.footer_btn_home);
		btnBook = (Button) findViewById(R.id.footer_btn_book);
		
		tvTitle = (TextView) findViewById(R.id.header_text_title);
		tvSubtitle = (TextView) findViewById(R.id.header_text_subtitle);
		//set title and subtitle for this activity
		tvTitle.setText(R.string.title_activity_service_option);
		tvSubtitle.setText(R.string.service_optiontext);
		//btnBack listsner

		MyButtons button = new MyButtons();
		btnBack.setOnClickListener(button);
		btnHome.setOnClickListener(button);
		btnBook.setOnClickListener(button);
		
		//get json data - old way, now try implement asyncTaskDownloader....
		
//		SmartAuth smart = new SmartAuth(SmartAuth.getToken(),SmartAuth.getApiKey(),url);
//		this.token=SmartAuth.getToken();
//		jsonString=smart.accessTheDBTable(token);
//		
//		myList = ServiceOptionsParser.parseServiceOptions(jsonString);
		
		//get data from DataManager (created by asyncTask in LoginActivity!)
		myList = DataManager.getServiceOptionsList();
		
		ListView lv = (ListView) findViewById(R.id.smart_listview);
		MyAdapter serviceAdapter = new MyAdapter(getApplicationContext(),R.layout.serviceoption_adapter,myList);
		lv.setAdapter(serviceAdapter);
		lv.setClickable(false);
		//listView listener
		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				//we'll send selected ServiceOption data by static way to DataManager - ClinicActivity wll display all clinics match to selected Provider.
				DataManager.setServiceOptions(new ServiceOptions(myList.get(position).getServiceId(),myList.get(position).getServiceName()));
				intent = new Intent(getApplicationContext(),ClinicsActivity.class);
				intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(intent);
			}
		});

	}// end onCreate()
	
	private class MyButtons implements OnClickListener{

		@Override
		public void onClick(View v) {
			switch(v.getId()){
			case R.id.header_btn_back:
				finish();
				break;
			case R.id.footer_btn_home:
				intent = new Intent(getApplicationContext(),MainViewActivity.class);
				intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(intent);
				break;
			case R.id.footer_btn_book:
				Toast.makeText(getApplicationContext(), "BOOK button - yeah!", Toast.LENGTH_SHORT).show();
				break;
			}
		}
	}
	public class MyAdapter extends ArrayAdapter<ServiceOptions> { 
		
		public MyAdapter(Context ctx, int txtViewResourceId, ArrayList<ServiceOptions> objects) { 
			super(ctx, txtViewResourceId, objects); 
		} 
		@Override 
		public View getDropDownView(int position, View cnvtView, ViewGroup prnt) { 
			return getCustomView(position, cnvtView, prnt); 
		} 
		@Override 
		public View getView(int pos, View cnvtView, ViewGroup prnt) { 
			return getCustomView(pos, cnvtView, prnt); 
		} 
		
		public View getCustomView(int position, View convertView, ViewGroup parent) { 
			final ViewHolder vHolder = new ViewHolder();
			LayoutInflater inflater = getLayoutInflater(); 
			convertView = inflater.inflate(R.layout.serviceoption_adapter, parent, false); //layout adapter HERE!
			
			vHolder.tView1 = (TextView) convertView.findViewById(R.id.serviceoption_adapter_textitem);
			vHolder.tView1.setText(myList.get(position).getServiceName());
			
			return convertView;
		} 
	}

	class ViewHolder{
		TextView tView1, tView2;
	}

	
}
//Nick
