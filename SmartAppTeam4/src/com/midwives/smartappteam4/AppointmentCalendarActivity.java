/**
 * this activity has own header, listView and footer, all widgets starts from eg. 'app_calendar_btn....'
 * so possible crash with NullPointer Exception
 */
package com.midwives.smartappteam4;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import com.midwives.parsers.*;
import com.midwives.classes.*;

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
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class AppointmentCalendarActivity extends Activity {
	
	private Intent intent;
	private TextView tvTitle, tvSubtitle,tvDate;
	private Button btnPrev, btnNext, btnBack, btnBook,btnHome;
	private ListView lv;
	private String clinicName, appointmentDay,weekDay, jsonString, token, tableUrl; 
	private String openTime, closeTime;
	private int interval;
	
	private Clinics clinic;
	private ClinicCalendar clinicDay;
//	private Appointment appointment;
	private Date dateDay;
	
	private ArrayList<Appointment> appointmentList;//list of appointments for specific clinic and day
	private HashMap<Integer,Appointment> appointmentDateMap;//full list of appointments for specific clinic

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_appointment_calendar);
		
		//Receive selected appointment date/clinic name and week day from dataManager

		clinic = DataManager.getClinics();
		clinicDay = DataManager.getClinicCalendar();
		dateDay=clinicDay.getDate();//get selected day
		System.out.println("calendarDay from DM: cal: "+clinicDay.getDate()+" | string: "+clinicDay.getDateString());
		
		clinicName = clinic.getClinicName();
		//for display only - will be handle by getAppointmentsForDay(Calendar cal,HashMap<Integer,Appointment> fullMap) method also	
		//need change appointment date for NEXT/PREV buttons
		appointmentDay = clinicDay.getDateString();
		
		openTime = clinic.getOpeningTime();
		closeTime = clinic.getClosingTime();
		interval = clinic.getAppointmentInterval();
		
		
		
				
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
		tvDate.setText(appointmentDay);
		
		//buttons listener:
		MyButtons button = new MyButtons();
		btnBack.setOnClickListener(button);
		btnHome.setOnClickListener(button);
		btnBook.setOnClickListener(button);
		btnPrev.setOnClickListener(button);
		btnNext.setOnClickListener(button);

/*
 * get a list
 * getAppointmentsForDay(Calendar cal,HashMap<Integer,Appointment> fullMap) return appointment list for specific day
 */
		appointmentDateMap = DataManager.getAppointmentDateMap();
		appointmentList=getAppointmentsForDay(dateDay,appointmentDateMap);
		System.out.println("AppCL: appointmentDateMap size: "+appointmentDateMap.size());
		System.out.println("AppCL: appointmentList size: "+appointmentList.size());
		for(Appointment a:appointmentDateMap.values()){
			System.out.println("AppCALL-appointmentDateMap stuff: "+a.getAppDate()+"|"+a.getAppTime()+" "+a.getServiceUser().getName());
		}
		
		
		//get data List for selected appointment - will be handle by other way... soon..........................................................
		//-----------------------------------------------------------------------------
//		this.tableUrl=getResources().getString(R.string.auth_url_server).concat(getResources().getString(R.string.auth_url_appointment));
//		SmartAuth smart = new SmartAuth(SmartAuth.getToken(),SmartAuth.getApiKey(),tableUrl);
//		this.token=SmartAuth.getToken();
//		jsonString=smart.accessTheDBTable(token); 
//		myList = AppointmentParser.parseAppointment(jsonString); // parse json String into ArrayList
//		DataManager.setAppointmentList(myList);// save appointmentsList into DataManager...
		//---------------------------------------------
		//populate listView
		setListView(appointmentList);
		
	}//end onCreate
	
	
	//---------------------------------------------------------------------------------------------
	private void setListView(ArrayList<Appointment> list){
		final ArrayList<Appointment> myList =list;
		lv = (ListView) findViewById(R.id.smart_appointment_calendar_listview);
		lv.setAdapter(new MyAdapter(getApplicationContext(),R.layout.app_calendar_full_adapter,myList));
		System.out.println("AppCL: setListView: list size: "+list.size());
		
		lv.setOnItemClickListener(new OnItemClickListener(){
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
										
				// send links to service user activity
				intent = new Intent(getApplicationContext(),ServiceUserActivity.class);
				DataManager.setAppointment(myList.get(position));
				DataManager.setLinks(new Links(myList.get(position).getLinks().getServiceOptions(),
												myList.get(position).getLinks().getServiceProviders(),
												myList.get(position).getLinks().getServiceUsers()));
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
		ArrayList<Appointment> list;
		public MyAdapter(Context ctx, int txtViewResourceId, ArrayList<Appointment> objects) { 
			super(ctx, txtViewResourceId, objects); 
			this.list=objects;
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
		
			vHolder.tvTime.setText(list.get(position).getAppTime());
			vHolder.tvName.setText(list.get(position).getServiceUser().getName());
			vHolder.tvGestation.setText(list.get(position).getServiceUser().getGestation()); 
			
			return convertView;
		} 
	}
	
	class ViewHolder{
		TextView tvTime, tvName, tvGestation;	
	}
	
	/**
	 * use to parse selected date into string and compare with appointmentDates value (string date is a key in appointment HashMap) 
	 * @param cal
	 * @return
	 */
	private String getStringFromCalendar(Calendar cal){
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		return formatter.format(cal.getTime());
	}
	
	//filter appointments matched for specific day
	private ArrayList<Appointment> getAppointmentsForDay(Date day, HashMap<Integer,Appointment> fullMap){
		Calendar cal = Calendar.getInstance();
		cal.setTime(day);
		ArrayList<Appointment> resultList = new ArrayList<Appointment>();
		String date = getStringFromCalendar(cal);
		
		for(Appointment app:fullMap.values()){
			if(date.equals(app.getAppDate())) resultList.add(app);
//			System.out.println("AppCal: date:"+date+" time: "+app.getAppTime());
//			System.out.println("AppCal: clinicID: "+app.getClinicId()+" user: "+app.getServiceUser().getName());
		}
		
		return resultList;
	}
}

//Nick
