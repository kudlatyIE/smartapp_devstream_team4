/**
 * this activity has own header, listView and footer, all widgets starts from eg. 'app_calendar_btn....'
 * so possible crash with NullPointer Exception
 */
package com.midwives.smartappteam4;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

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
import android.widget.BaseAdapter;
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
	private String clinicName, appointmentDay; 
	private String openTime, closeTime;
	private int interval;
	private String[] keysList;
	
	private Clinics clinic;
	private ClinicCalendar clinicDay;
	private ArrayList<ClinicCalendar> clinicCalendarList;// short calendar for selected clinic - size was set in ClinicDatesActivity
//	private Appointment appointment;
	private Date dateDay;
	private int dayId=0, dayListSize,currentDayId=0;
	
	private HashMap<Integer,Appointment> appointmentFullMap;//full list of appointments for specific clinic

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_appointment_calendar);
		
		//Receive selected appointment date/clinic name and week day from dataManager
		clinicCalendarList = DataManager.getClinicCalendarList();//list of open day for current clinic, will use to display PREV and NEXT appointments!
		clinic = DataManager.getClinics();
		clinicName = clinic.getClinicName();
		clinicDay = DataManager.getClinicCalendar();
		dateDay=clinicDay.getDate();//get selected day Date
		appointmentDay = clinicDay.getDateString();//String
		setDayListSize(clinicCalendarList.size());

		openTime = clinic.getOpeningTime();
		closeTime = clinic.getClosingTime();
		interval = clinic.getAppointmentInterval();
		keysList = XFiles.getTimeList(openTime, closeTime, interval);//time list from open until close HH:m:ss
		appointmentFullMap = DataManager.getAppointmentFullMap();
		//get position of current day, dayId is used to display current/PREVIER/NEXT day appointments
		setCurrentDayId(clinicCalendarList,appointmentDay);
	
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
//		tvDate.setText(appointmentDay);// displayed for selected day
		
		//buttons listener:
		MyButtons button = new MyButtons();
		btnBack.setOnClickListener(button);
		btnHome.setOnClickListener(button);
		btnBook.setOnClickListener(button);
		btnPrev.setOnClickListener(button);
		btnNext.setOnClickListener(button);

		//populate default listView for current day
		setListView(appointmentFullMap, clinic, keysList,clinicCalendarList,dayId);//main view for selected day, for NEXT/PREVIEW set different clinicDay by MyButtons class
		
	}//end onCreate
	
	//---------------------------------------------------------------------------------------------
	
	/**
	 * handle ListView for current day and PREVIEW/NEXT days appointments,
	 * set selected date in layout header
	 * @param appointmentFullMap HashMap<Integer,Appointment> - all appointments
	 * @param clinic Clinic - current clinic
	 * @param keysList String[] - open-close times with interval set for selected clinic
	 * @param clinicCalendarList ArrayList<ClinicCalendar> - list of open day for selected clinic
	 * @param dayId - selected dayId for clinicCalendarList
	 */
	private void setListView(HashMap<Integer,Appointment> appointmentFullMap, Clinics clinic, String[] keysList,ArrayList<ClinicCalendar> clinicCalendarList,int dayId){
		
		ClinicCalendar day = clinicCalendarList.get(dayId);
		dateDay=day.getDate();//get selected day Date
		appointmentDay = day.getDateString();//String
		HashMap<String, Appointment> singleDayMap = XFiles.getAppointmentsForDay(dateDay,appointmentFullMap);
		final ArrayList<Appointment> appList = XFiles.getAppointmentsTimeList(keysList,singleDayMap,appointmentDay, clinic);

		tvDate.setText(appointmentDay);//display selected date in layout header
		
		lv = (ListView) findViewById(R.id.smart_appointment_calendar_listview);
		MyBaseAdapter adapter = new MyBaseAdapter(getApplicationContext(),appList );
		
		lv.setAdapter(adapter);
		
		lv.setOnItemClickListener(new OnItemClickListener(){
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//				String key = times[position];
				Appointment app = appList.get(position);
				// send links to service user activity
				if(app.isAppointmentExist()){
//				try{
					app.getAppointmentId();//try if appointment Id exist
					intent = new Intent(getApplicationContext(),ServiceUserActivity.class);
					DataManager.setAppointment(app);
					DataManager.setLinks(new Links(app.getLinks().getServiceOptions(),
													app.getLinks().getServiceProviders(),
													app.getLinks().getServiceUsers()));
					startActivity(intent);
//				}catch(NullPointerException ex){//run when appointment doesn't exist -> clicked on Free Slot
				}else{
					//create FreeSlot half empty appointment: holds clinicID, serviceProviderID, date, time....
					//user ID, visitType and priority will set on next Activity.
					int serviceProviderID = SmartAuth.getServiceProviderID();
					int clinicID = app.getClinicId();
					String date = app.getAppDate();
					String time = app.getAppTime();
//					intent = new Intent(getApplicationContext(),FreeSlotsActivity.class);
					//temporary jump to FindServiceUser
					intent = new Intent(getApplicationContext(),FindServiceUserActivity.class);
					intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
					DataManager.setAppointment(null);
					DataManager.setAppointmentFreeSlot(new Appointment(date,time,serviceProviderID,0,clinicID,null,null));
					startActivity(intent);
				}
				
			}
		});
	}

	private class MyButtons implements OnClickListener{

		int listSize = getDayListSize();
		int firstDay=0, lastDay=listSize-1, currentDay;
		@Override
		public void onClick(View v) {
			switch(v.getId()){
			case R.id.app_calendar_header_btn_prevdate:
				currentDay=getDayId()-1;
				if(currentDay>=firstDay){
					setDayId(currentDay);
					setListView(appointmentFullMap, clinic, keysList,clinicCalendarList,currentDay);
				}else Toast.makeText(getApplicationContext(), "There is nothing PREVIEW...", Toast.LENGTH_SHORT).show();
				
				break;
			case R.id.app_calendar_header_btn_nextdate:
				currentDay=getDayId()+1;
				if(currentDay<=lastDay){
					setDayId(currentDay);
					setListView(appointmentFullMap, clinic, keysList,clinicCalendarList,currentDay);
				}else Toast.makeText(getApplicationContext(), "There is nothing NEXT...", Toast.LENGTH_SHORT).show();
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
	
	public class MyBaseAdapter extends BaseAdapter{

		private ArrayList<Appointment> appList;
		private LayoutInflater inflater;
		TextView tvTime, tvName, tvGestation;
		
		public MyBaseAdapter(Context context,ArrayList<Appointment> appList){
			this.appList=appList;
			this.inflater = LayoutInflater.from(context);
		}
		@Override
		public int getCount() {
			return appList.size();
		}

		@Override
		public Object getItem(int position) {
			return appList.get(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {

			View view = null;
			if(convertView == null){
				view = inflater.inflate(R.layout.app_calendar_full_adapter, parent, false);
			}else view = convertView;
			
			tvTime = (TextView)view.findViewById(R.id.app_calend_adapter_text_time);
			tvName = (TextView) view.findViewById(R.id.app_calendar_adapter_text_user);
			tvGestation = (TextView) view.findViewById(R.id.app_calendar_adapter_text_data);
			
			tvTime.setText(appList.get(position).getTime());

			if(appList.get(position).isAppointmentExist()){
				System.out.println("name: "+appList.get(position).getServiceUser().getName());
				tvName.setText(appList.get(position).getServiceUser().getName());
				tvGestation.setText(appList.get(position).getServiceUser().getGestation());
			}else{
				tvName.setText("Free Slot");
				tvGestation.setText("");
			}
			return view;
		}
		
	}

	
	public int getDayId() {
		return dayId;
	}


	public void setDayId(int dayId) {
		this.dayId = dayId;
	}
	public int getDayListSize() {
		return dayListSize;
	}
	public void setDayListSize(int dayListSize) {
		this.dayListSize = dayListSize;
	}
	public int getCurrentDayId() {
		return currentDayId;
	}
	public void setCurrentDayId(int currentDayId) {
		this.currentDayId = currentDayId;
	}
	public void setCurrentDayId(ArrayList<ClinicCalendar> list, String day){
		for(int i=0; i<list.size();i++){
			if(list.get(i).getDateString().equals(day)) {
				setDayId(i);
				break;
			}
		}
	}
	

}

//Nick
