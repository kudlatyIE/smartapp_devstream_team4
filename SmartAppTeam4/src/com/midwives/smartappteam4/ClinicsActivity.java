package com.midwives.smartappteam4;

import java.util.ArrayList;

import com.midwives.classes.Clinics;
import com.midwives.classes.Days;
import com.midwives.classes.Recurrence;

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
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_clinics);
		
		//Receive Clinic Id, token, apiKey from previous activity
		extras = getIntent().getExtras();
		id = Integer.parseInt(extras.getString("ClinicId"));
		clinicName = extras.getString("clinicName");
		
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
		
		//create clinics list for testing....
		myList=createClinicList();
		
		//populate list of clinics...
		ListView lv = (ListView) findViewById(R.id.smart_listview);
		MyAdapter clinicsAdapter = new MyAdapter(getApplicationContext(),R.layout.clinicsoption_adapter,myList);
		lv.setAdapter(clinicsAdapter);
		
		//set listener for listView
		lv.setOnItemClickListener(new OnItemClickListener(){

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Toast.makeText(getApplicationContext(), "Click on: "+myList.get(position).getClinicName()+"-"+ String.valueOf(position)+"\n"+
													token+"\n"+ apiKey, Toast.LENGTH_SHORT).show();
				intent = new Intent(getApplicationContext(),ClinicDatesActivity.class);
				intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				intent.putExtra("clinicName", myList.get(position).getClinicName());// to be handle by clinicID in the future...
				intent.putExtra("weekDay", myList.get(position).getOpenDays().getDayName());
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
			vHolder.tvAddress = (TextView) convertView.findViewById(R.id.clinicsoption_adapter_text_clinicaddress);
			vHolder.tvRecurrence = (TextView) convertView.findViewById(R.id.clinicsoption_adapter_text_reccurence);
			vHolder.tvDays = (TextView) convertView.findViewById(R.id.clinicsoption_adapter_text_bookongday);
			
			
			vHolder.tvName.setText((myList.get(position).getClinicName() + " - ID:"+String.valueOf(id))); //add value from previous Activity for test only!
			vHolder.tvAddress.setText(myList.get(position).getClinicAddress());
			vHolder.tvRecurrence.setText(myList.get(position).getRecurrence().getReccName());
			vHolder.tvDays.setText(myList.get(position).getOpenDays().getDayName());
			
			return convertView; } }

	class ViewHolder{
		//here we declare all fields for current adapter
		TextView tvName, tvAddress, tvRecurrence,tvDays;	
	}
	
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
	
}

// Nick