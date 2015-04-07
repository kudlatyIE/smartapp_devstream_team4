package com.midwives.smartappteam4;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import com.midwives.classes.Appointment;
import com.midwives.classes.Clinics;
import com.midwives.classes.DataManager;
import com.midwives.classes.Priority;
import com.midwives.classes.ServiceUser;
import com.midwives.classes.SmartAuth;
import com.midwives.classes.VisitType;
import com.midwives.parsers.AppointmentParser;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class ConfirmAppointmentActivity extends Activity {
	
	private Appointment appointment;// from appointmentsFreeSlot
	private ServiceUser serviceUser;
	private Clinics clinic;
	private TextView tvTitle, tvSubtitle,tvUserName,tvHospitalNo,tvClinicName,tvDate, tvTime,tvDuration, 
					tvEmail,tvSMS, tvPriority, tvVisitType;
	private Button btnBack, btnHome, btnBook, btnConfirm;
	private Spinner spinPriority, spinVisitType;
	
	//need to provide priority and visitType from ENUM, will be provide by spinners (?)
	private ArrayList<String> priorityList, visitTypeList;
	private int clinicID, serviceUserID, serviceProviderID;
	private String priority="N/A", visitType="N/A", date, time;
	private String URL, jsonString;
	
	//annoncements from ClinicID to do later
	//

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_confirm_appointment);
		
		//get resources and values
		this.clinic = DataManager.getClinics();
		this.appointment = DataManager.getAppointmentFreeSlot();//may be check if is empty?
		this.clinicID = appointment.getClinicId();
		this.serviceUserID = appointment.getServiceUserId();
		this.serviceProviderID = appointment.getServiceProviderId();
		this.serviceUser = DataManager.getServiceUserMap().get(Integer.valueOf(serviceUserID));
//		this.date = appointment.getAppDate();
		this.time = appointment.getAppTime();
		//get date in right format
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();
		cal.setTime(DataManager.getClinicCalendar().getDate());
		this.date = df.format(cal.getTime()).toString();
		
		//appointment url
		URL = getResources().getString(R.string.auth_url_server).concat(getResources().getString(R.string.auth_url_appointment));
		setListForSpinners();
		
		//set views
		declareView();
		setTextViews();//when we got all values
		setListForSpinners();
		setListeners();
		
	}//end onCreate...............
	
	
	private void declareView(){
		tvTitle = (TextView) findViewById(R.id.header_text_title); 
		tvSubtitle = (TextView) findViewById(R.id.header_text_subtitle);
		tvUserName = (TextView) findViewById(R.id.app_confirm_text_user_name);
		tvHospitalNo = (TextView) findViewById(R.id.app_confirm_text_hospital_number);
		tvClinicName = (TextView) findViewById(R.id.app_confirm_text_clinic_name);
		tvDate = (TextView) findViewById(R.id.app_confirm_text_date);
		tvTime = (TextView) findViewById(R.id.app_confirm_text_time);
		tvDuration = (TextView) findViewById(R.id.app_confirm_text_duration);
		tvEmail = (TextView) findViewById(R.id.app_confirm_text_email);
		tvSMS = (TextView) findViewById(R.id.app_confirm_text_user_phone);

		//spinners for priority and visit type
		spinPriority = (Spinner) findViewById(R.id.app_confirm_spinner_priority);
		spinVisitType = (Spinner) findViewById(R.id.app_confirm_spinner_visitType);
		
		btnHome = (Button) findViewById(R.id.footer_btn_home);
		btnBook = (Button) findViewById(R.id.footer_btn_book);
		btnBack = (Button) findViewById(R.id.header_btn_back);
		btnConfirm = (Button) findViewById(R.id.app_confirm_btn_confirm);
	}
	
	private void setTextViews(){
		
		tvTitle.setText(getResources().getString(R.string.title_activity_confirm_appointment));
		tvSubtitle.setText("Temporary Announcement!");
		tvUserName.setText(serviceUser.getPersonalFields().getName());
		tvHospitalNo.setText(serviceUser.getHospitalNumber());
		tvClinicName.setText(clinic.getClinicName());
		tvDate.setText(date);
		tvTime.setText(time);
		tvDuration.setText(clinic.getAppointmentInterval()+" min");
		tvEmail.setText(serviceUser.getPersonalFields().getEmail());
		tvSMS.setText(serviceUser.getPersonalFields().getMobilePhone());
		
		setSpinners();
		
		
	}
	
	private void setListForSpinners(){
		priorityList = new ArrayList<String>();
		visitTypeList = new ArrayList<String>();
		Priority[] priority = Priority.values();
		for(Priority p:priority) priorityList.add(p.getPriorityName());
		
		VisitType[] visit = VisitType.values();
		for(VisitType v:visit) visitTypeList.add(v.getVisitTypeName());
	}
	
	private void setSpinners(){
		int spinPosition=0;
		SpinnerAdapter priorityAdapter = new SpinnerAdapter(this, R.layout.spinner_custom_item,priorityList);
		SpinnerAdapter visitAdapter = new SpinnerAdapter(this,R.layout.spinner_custom_item,visitTypeList);
		spinPriority.setAdapter(priorityAdapter);
		spinVisitType.setAdapter(visitAdapter);
		
		spinPosition = priorityAdapter.getPosition(priority);
		spinPriority.setSelection(spinPosition);
		spinPosition = visitAdapter.getPosition(visitType);
		spinVisitType.setSelection(spinPosition);
		
		spinPriority.setOnItemSelectedListener(new OnItemSelectedListener(){

			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
				setPriority(parent.getItemAtPosition(position).toString());
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				
			}
			
		});
		
		spinVisitType.setOnItemSelectedListener(new OnItemSelectedListener(){

			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
				setVisitType(parent.getItemAtPosition(position).toString());
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				
			}
			
		});
		
		
		
	}
	
	private class SpinnerAdapter extends ArrayAdapter<String>{
		ArrayList<String> myList;
		public SpinnerAdapter(Context ctx, int txtViewResourceId, ArrayList<String> objects) { 
			super(ctx, txtViewResourceId, objects); 
			this.myList=objects;
		}
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			return getCustomView(position, convertView, parent);
		}

		@Override
		public View getDropDownView(int position, View convertView,	ViewGroup parent) {
			return getCustomView(position, convertView, parent);
		}
		public View getCustomView(int position, View convertView, ViewGroup parent){
			
			LayoutInflater inflater = getLayoutInflater();
			View rowView= inflater.inflate(R.layout.spinner_custom_item, parent,false);
			TextView tvSpinnerItem = (TextView) rowView.findViewById(R.id.spinner_custom_item_text);
			tvSpinnerItem.setText(myList.get(position));

			return rowView;
		}
	}
	
	private void setListeners(){
		MyButtons button = new MyButtons();
		
		btnHome.setOnClickListener(button);
		btnBook.setOnClickListener(button);
		btnBack.setOnClickListener(button);
		btnConfirm.setOnClickListener(button);
		
	}
	
	private class MyButtons implements OnClickListener{
		Intent intent;
		@Override
		public void onClick(View v) {
			switch(v.getId()){
			case R.id.footer_btn_home:
				intent = new Intent(getApplicationContext(),MainViewActivity.class);
				intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(intent);
				break;
			case R.id.footer_btn_book:
				Toast.makeText(getApplicationContext(), "BOOK: go to ServiceOption Activity?", Toast.LENGTH_SHORT).show();
				break;
			case R.id.header_btn_back:
				finish();
				break;
			case R.id.app_confirm_btn_confirm:
				Toast.makeText(getApplicationContext(), "new priority: "+getPriority()+
														"\nnew visitType: "+getVisitType()+
														"who: "+serviceUser.getPersonalFields().getName(), Toast.LENGTH_SHORT).show();
				//create new appointment - for local use and create json string to post a new appointment
				appointment = new Appointment(date, time, serviceProviderID, serviceUserID, clinicID, priority, visitType );
				//store it, just in case
				DataManager.setAppointmentFreeSlot(appointment);
				jsonString=AppointmentParser.createAppointmentJsonString(appointment);
				System.out.println(jsonString);
				System.out.println(URL);
				//post it
				if(SmartAuth.postData(URL, jsonString)) Toast.makeText(getApplicationContext(), "DONE!", Toast.LENGTH_SHORT).show();
				else Toast.makeText(getApplicationContext(), "Booooooo....", Toast.LENGTH_SHORT).show();
				break;
			}
		}
		
	}

	public String getPriority() {
		return priority;
	}
	public void setPriority(String priority) {
		this.priority = priority;
	}
	public String getVisitType() {
		return visitType;
	}
	public void setVisitType(String visitType) {
		this.visitType = visitType;
	}
	
	
}
