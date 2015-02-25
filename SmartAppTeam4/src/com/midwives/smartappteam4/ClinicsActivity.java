package com.midwives.smartappteam4;

import java.util.ArrayList;  //// Chris

import com.midwives.classes.Clinics;
import com.midwives.classes.DataManager;
import com.midwives.classes.Days;
import com.midwives.classes.Recurrence;



import com.midwives.classes.SmartAuth;
import com.midwives.parsers.AppointmentParser;
import com.midwives.parsers.ClinicsParser;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class ClinicsActivity extends Activity {

	private Intent intent;
	private Bundle extras;
	private Button btnBack, btnHome,btnBook;
	private TextView tvTitle, tvSubtitle;
	private ArrayList<Clinics> myList; 
	private String token, apiKey, url;	
	private int id;
	private String hint,clinicName;
	private String jsonString;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_clinics);
		
		//Receive Clinic Id, token, apiKey from previous activity by DataManager!!!!!
		
		// add try-catch block - if my serviceOptions data are NULL !!!!!
		id = DataManager.getServiceOptions().getServiceId();
		clinicName = DataManager.getServiceOptions().getServiceName();
		
		//set title and subtitle for this activity
		tvTitle = (TextView) findViewById(R.id.header_text_title);
		tvSubtitle = (TextView) findViewById(R.id.header_text_subtitle);
		tvTitle.setText(R.string.title_activity_clinic_options);
		
		//set hint text as subtitle view
		hint = getResources().getString(R.string.clinic_hint).concat(clinicName);
		tvSubtitle.setText(hint);
		
		btnBack = (Button) findViewById(R.id.header_btn_back);
		btnHome = (Button) findViewById(R.id.footer_btn_home);
		btnBook = (Button) findViewById(R.id.footer_btn_book);
		//add listener for buttons
		MyButtons button = new MyButtons();
		btnBack.setOnClickListener(button);
		btnHome.setOnClickListener(button);
		btnBook.setOnClickListener(button);
		
		//get the list of clinics 
		//myList=createClinicList(); //switch to hard coded array see below for populate method 
		
		//to be fixed: if clinic list doesn't exist in DataManager then run code below, if exist, just populate listView!!!!!!!.....................
		
		SmartAuth smart = new SmartAuth(SmartAuth.getToken(),SmartAuth.getApiKey(),"http://54.72.7.91:8888/clinics");
		this.token=SmartAuth.getToken(); //get token needed to get the table 
		jsonString=smart.accessTheDBTable(token); //get the clinics table as a json formatted string
		myList = ClinicsParser.parseClinics(jsonString);  //parse json format of clinics table into array which will populate widgets
		
		//populate list of clinics...
		ListView lv = (ListView) findViewById(R.id.smart_listview);
		MyAdapter clinicsAdapter = new MyAdapter(getApplicationContext(),R.layout.clinicsoption_adapter,myList);
		lv.setAdapter(clinicsAdapter);
		
		//set listener for listView
		lv.setOnItemClickListener(new OnItemClickListener(){

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Toast.makeText(getApplicationContext(), "Click on: "+myList.get(position).getClinicName()+"-"+ myList.get(position).getOpenDays()[0], Toast.LENGTH_SHORT).show();
				intent = new Intent(getApplicationContext(),ClinicDatesActivity.class);
				intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

				DataManager.setClinics(new Clinics(myList.get(position).getClinicId(),myList.get(position).getClinicName(), myList.get(position).getClinicAddress(),
													myList.get(position).getOpeningTime(),myList.get(position).getClosingTime(),myList.get(position).getRecurrence(),
													myList.get(position).getType(),myList.get(position).getAppointmentInterval(),
													myList.get(position).getOpenDays(),myList.get(position).getServiceOptionIds()));

				startActivity(intent);
				
			}
		});
		

	}
	
	private class MyButtons implements OnClickListener{

		@Override
		public void onClick(View v) {
			switch(v.getId()){
			case R.id.header_btn_back:
				finish();
				break;
			case R.id.footer_btn_home:
				intent = new Intent(getApplicationContext(),MainViewActivity.class);
				intent.putExtra("token", token);
				intent.putExtra("apiKey", apiKey);
				intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(intent);
				break;
			case R.id.footer_btn_book:
				Toast.makeText(getApplicationContext(), "BOOK button - yeah!", Toast.LENGTH_SHORT).show();
				break;
			}
		}
	}
	

	public class MyAdapter extends ArrayAdapter<Clinics> { 
		
		public MyAdapter(Context ctx, int txtViewResourceId, ArrayList<Clinics> objects) { 
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
			convertView = inflater.inflate(R.layout.clinicsoption_adapter, parent, false); //layout adapter HERE!
			
			vHolder.tvName = (TextView) convertView.findViewById(R.id.clinicsoption_adapter_text_clinicname);
			//vHolder.tvAddress = (TextView) convertView.findViewById(R.id.clinicsoption_adapter_text_clinicaddress);
			vHolder.tvRecurrence = (TextView) convertView.findViewById(R.id.clinicsoption_adapter_text_reccurence);
			vHolder.tvDays = (TextView) convertView.findViewById(R.id.clinicsoption_adapter_text_bookongday);
						
			vHolder.tvName.setText((myList.get(position).getClinicName()));     // + " - ID:"+String.valueOf(id))); //add value from previous Activity for test only!
			//vHolder.tvAddress.setText(myList.get(position).getClinicAddress());
			vHolder.tvRecurrence.setText(myList.get(position).getRecurrence().getReccName());
			//vHolder.tvDays.setText(myList.get(position).getOpenDays().getDayName()); //when switch to hard coded test array
			vHolder.tvDays.setText(myList.get(position).getOpenDays()[0]); //get the open days for the clinic from the list
			// need to fix how hold click on more than one day.............!!!!!!!!!!!!!!!
			
			return convertView; } }

	class ViewHolder{
		//here we declare all fields for current adapter
		TextView tvName, tvAddress, tvRecurrence,tvDays;	
	}
	
//	private String getOpenDays(ArrayList<Clinics> listOfClinics, int position) {		
//		String daysOpen = "";
//		String[] temp = listOfClinics.get(position).getOpenDays();
//		int length = temp.length;
//		for(int i = 0; i < length; i++){
//			if(temp[i] != null)
//				 daysOpen += temp[i] + " ";
//		}
//		return daysOpen;
//	}
	
//	private String[] openDays(ArrayList<Clinics> clinicList, int position){
//		ArrayList<String> days = new ArrayList<String>();
//		String [] temp = clinicList.get(position).getOpenDays();
//		for(int i=0;i<temp.length;i++){
//			if(temp[i]!=null) days.add(temp[i]);
//		}
//		String[] result = days.toArray(new String[days.size()]);
//		return result;
//	}
	private String makeString(String[] days){
		String result="";
		for(String arr:days){
			result=result.concat(arr+", ");
		}
		return result;
	}
	
	/*//note the clinics constructor has changes to suit the dynamic array 
	private ArrayList<Clinics> createClinicList(){
		ArrayList<Clinics> myList = new ArrayList<Clinics>();
		
		myList.add(new Clinics(0,"NMH","OPD Location",Recurrence.WEEKLY,Days.MONDAY));
		myList.add(new Clinics(1,"Leopardstown","",Recurrence.WEEKLY,Days.TUESDAY));
		myList.add(new Clinics(2,"Dun Laoghaire","St. Michaael's Hospital",Recurrence.WEEKLY,Days.WEDNESDAY));
		myList.add(new Clinics(3,"Churchtown","",Recurrence.WEEKLY,Days.THURSDAY));
		myList.add(new Clinics(4,"NMH","other NMH",Recurrence.WEEKLY,Days.FRIDAY));
		myList.add(new Clinics(5,"Home Visits","",Recurrence.DAILY,Days.MISCELLANEOUS));
		
		return myList;
	}
	*/
	
}

// Chris
