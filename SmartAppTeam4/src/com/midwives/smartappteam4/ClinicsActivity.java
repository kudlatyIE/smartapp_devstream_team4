package com.midwives.smartappteam4;

import java.util.ArrayList;  //// Chris
import java.util.HashMap;

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
	private Button btnBack, btnHome,btnBook;
	private TextView tvTitle, tvSubtitle;
	private ArrayList<Clinics> myList,fullList; 	
	private int [] clinicsIds;
	private int id;
	private String hint,clinicName;
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
		

//		myList = ClinicsParser.parseClinics(jsonString);  //parse json format of clinics table into array which will populate widgets
		myList = new ArrayList<Clinics>();
		
		HashMap<Integer,Clinics> clinicMap = DataManager.getClinicsMap();
		
		fullList = DataManager.getClinicList();//all clinic, need to be filter to match to specific ServiceProvider (selected in previous activity)
		clinicsIds = DataManager.getServiceOptions().getClinicsIDs();
		
		boolean flag = false;
		for(int i:clinicsIds){
			if(clinicMap.containsKey(Integer.valueOf(i))){
				myList.add(clinicMap.get(Integer.valueOf(i)));
				flag=true;
			}
			
		}
		//get list of clinics from selected serviceOption only!
//		for(Clinics fl:fullList){
//			for(int cl:clinicsIds){
//				if(cl==fl.getClinicId()) myList.add(fl);
//			}
//		}
		
		//populate list of clinics...
		if(true==flag) setListView();
		else {
			Toast.makeText(getApplicationContext(), "No values, list size: "+myList.size()+" is empty: "+myList.isEmpty(), Toast.LENGTH_LONG).show();
			finish();
		}
		
	}//end onCreate
	
	private void setListView(){
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
			vHolder.tvRecurrence.setText(myList.get(position).getRecurrence().getReccName());
			vHolder.tvDays.setText(daysToString(myList.get(position).getOpenDays())); //get the open days for the clinic from the list
				
			
			return convertView; 
		} 
	}//end MyAdapter class

	class ViewHolder{
		//here we declare all fields for current adapter
		TextView tvName, tvAddress, tvRecurrence,tvDays;	
	}
	
	private String daysToString(String [] listOfClinics) {		
		String daysOpen = "";
		for(int i = 0; i < listOfClinics.length; i++){
			if(listOfClinics[i] != null)
				 daysOpen += listOfClinics[i] + ", ";
		}
		return daysOpen;
	}
	

	
}

// Nick
