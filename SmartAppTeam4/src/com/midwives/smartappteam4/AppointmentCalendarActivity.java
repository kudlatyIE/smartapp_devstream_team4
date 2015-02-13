/**
 * this activity has own header, listView and footer, all widgets starts from eg. 'app_calendar_btn....'
 * so possible crash with NullPointer Exception
 */
package com.midwives.smartappteam4;

import java.util.ArrayList;
import java.util.Date;

import com.midwives.parsers.*;
import com.midwives.classes.*;
import com.midwives.smartappteam4.ClinicDatesActivity.ViewHolder;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class AppointmentCalendarActivity extends Activity {
	
//	private ArrayList<String> myList; //list of clinic's opening data
	private Intent intent;
	private Bundle extras;
	private Date date;
	private TextView tvTitle, tvSubtitle,tvDate;
	private Button btnPrev, btnNext, btnBack, btnBook,btnHome;
	private ListView lv;
	private String hint,clinicName, appointmentDate,weekDay, jsonString, token; 
	
	private ArrayList<Appointment> myList;
//	private ArrayList<ServiceOptions> service;
//	private ArrayList<ServiceUser> user;
//	private ArrayList<Clinics> clinic;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_appointment_calendar);
		
		//Receive selected appointment date/clinic name and week day from previous activity... TEST STUFF
		extras = getIntent().getExtras();
		clinicName = extras.getString("clinic_name");
		appointmentDate = extras.getString("appointment_date");
		weekDay = extras.getString("week_day");
				
		btnBack = (Button) findViewById(R.id.app_calendar_header_btn_back);
		btnHome = (Button) findViewById(R.id.footer_btn_home);
		btnBook = (Button) findViewById(R.id.footer_btn_book);
		btnPrev = (Button) findViewById(R.id.app_calendar_header_btn_prevdate);
		btnNext = (Button) findViewById(R.id.app_calendar_header_btn_nextdate);
		tvTitle = (TextView) findViewById(R.id.app_calendar_header_text_title);
		tvSubtitle = (TextView) findViewById(R.id.app_calendar_header_text_subtitle);
		tvDate = (TextView) findViewById(R.id.app_calendar_header_text_username);
		
		//set text for header
		tvTitle.setText(R.string.title_activity_appointment_calendar);
		tvSubtitle.setText(clinicName);
		tvDate.setText(appointmentDate);
		
		//buttons listener:
		MyButtons button = new MyButtons();
		btnBack.setOnClickListener(button);
		btnHome.setOnClickListener(button);
		btnBook.setOnClickListener(button);
		btnPrev.setOnClickListener(button);
		btnNext.setOnClickListener(button);
		
		// generate testing data only - test json...........
		SmartAuth smart = new SmartAuth(SmartAuth.getToken(),SmartAuth.getApiKey(),SmartAuth.getTableUrl());
		
		this.token=SmartAuth.getToken(); // get TOKEN
		
		jsonString=smart.accessTheDBTable(token); // get json String
		
		myList = AppointmentParser.parseAppointment(jsonString); // parse json String into ArrayList
		
		//populate listView
		ListView lv = (ListView) findViewById(R.id.smart_appointment_calendar_listview);
		lv.setAdapter(new MyAdapter(getApplicationContext(),R.layout.app_calendar_full_adapter,myList));
		
		lv.setOnItemClickListener(new OnItemClickListener(){
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {

				intent = new Intent(getApplicationContext(),ServiceUserActivity.class);
				intent.putExtra("service option", myList.get(position).getLinks().getServiceOptions());
				intent.putExtra("service provider", myList.get(position).getLinks().getServiceProviders());
				intent.putExtra("service user", myList.get(position).getLinks().getServiceUsers());
				startActivity(intent);
				
			}
		});
		
	}
	private class MyButtons implements OnClickListener{

		@Override
		public void onClick(View v) {
			switch(v.getId()){
			case R.id.app_calendar_header_btn_prevdate:
				Toast.makeText(getApplicationContext(), "will go to PREVIOUS date", Toast.LENGTH_SHORT).show();
				break;
			case R.id.app_calendar_header_btn_nextdate:
				Toast.makeText(getApplicationContext(), "will go to NEXT date", Toast.LENGTH_SHORT).show();
				break;
			case R.id.app_calendar_header_btn_back:
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
	
	public class MyAdapter extends ArrayAdapter<Appointment> { 
		
		public MyAdapter(Context ctx, int txtViewResourceId, ArrayList<Appointment> objects) { 
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
			convertView = inflater.inflate(R.layout.app_calendar_full_adapter, parent, false); //layout adapter HERE!
			
			vHolder.tvTime = (TextView) convertView.findViewById(R.id.app_calend_adapter_text_time);
			vHolder.tvName = (TextView) convertView.findViewById(R.id.app_calendar_adapter_text_user);
			vHolder.tvGestation = (TextView) convertView.findViewById(R.id.app_calendar_adapter_text_data);
		
			vHolder.tvTime.setText(myList.get(position).getAppTime());
			vHolder.tvName.setText(myList.get(position).getServiceUser().getPersonalFields().getName());
			vHolder.tvGestation.setText(myList.get(position).getServiceUser().getPregnancy().getGestation()); 
			
			return convertView;
		} 
	}
	
	class ViewHolder{
		TextView tvTime, tvName, tvGestation;	
	}
	
}

//Nick
