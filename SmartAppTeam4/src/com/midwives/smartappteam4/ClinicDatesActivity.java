package com.midwives.smartappteam4;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import com.midwives.classes.ClinicDates;
import com.midwives.classes.DataManager;
import com.midwives.classes.ServiceOptions;
import com.midwives.classes.XFiles;
import com.midwives.smartappteam4.ClinicsActivity.ViewHolder;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
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

public class ClinicDatesActivity extends Activity {
	
	private ArrayList<String> myList; //list of clinic's opening data
	private Intent intent;
	private Bundle extras;
	private Date date;
	private Button btnBack, btnHome, btnBook,btnCalendar;
	private TextView tvTitle, tvSubtitle;
	private String hint,clinicName, token, apiKey, url; 
	private String[] weekDays;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_clinic_dates);
		
		//Receive data from previous activity...
		clinicName = DataManager.getClinics().getClinicName();
		weekDays = DataManager.getClinics().getOpenDays();
		
		btnBack = (Button) findViewById(R.id.header_btn_back);
		btnHome = (Button) findViewById(R.id.footer_clinicdates_btn_home);
		btnBook = (Button) findViewById(R.id.footer_clinicdates_btn_book);
		btnCalendar = (Button) findViewById(R.id.footer_clinicdates_btn_calendar);
		tvTitle = (TextView) findViewById(R.id.header_text_title);
		tvSubtitle = (TextView) findViewById(R.id.header_text_subtitle);
		
		//buttons listener:
		MyButtons button = new MyButtons();
		btnBack.setOnClickListener(button);
		btnHome.setOnClickListener(button);
		btnBook.setOnClickListener(button);
		btnCalendar.setOnClickListener(button);
		
		tvTitle.setText(R.string.title_activity_clinic_dates);
//		hint=getResources().getString(R.string......)
		tvSubtitle.setText("Select "+ clinicName+ "\'s calendar");
		
		//create dataList for only one day... will be fixed to return more days...............................................
		myList = XFiles.getDateList(weekDays[0]);
		//populate listView content
		ListView lv = (ListView) findViewById(R.id.smart_listview);
		
		MyAdapter customAdapter = new MyAdapter(getApplicationContext(),R.layout.clinic_dates_adapter,myList);
		lv.setAdapter(customAdapter);
		
		//listView listener....
		lv.setOnItemClickListener(new OnItemClickListener(){

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Toast.makeText(getApplicationContext(), "Click on: "+myList.get(position).toString()+"\n"+
									token+"\n"+ apiKey, Toast.LENGTH_SHORT).show();
				intent = new Intent (getApplicationContext(), AppointmentCalendarActivity.class);
				DataManager.setClinicdates(new ClinicDates(myList.get(position),weekDays[0], clinicName));
				//send selected date and clinic name to next activity
//				intent.putExtra("appointment_date", myList.get(position).toString());
//				intent.putExtra("week_day", weekDay);
//				intent.putExtra("clinic_name", clinicName);
				intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(intent);
			}	
		});
		
	}// end onCreate
	
	private class MyButtons implements OnClickListener{

		@Override
		public void onClick(View v) {
			switch(v.getId()){
			case R.id.header_btn_back:
				finish();
				break;
			case R.id.footer_clinicdates_btn_home:
				intent = new Intent(getApplicationContext(),MainViewActivity.class);
				intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(intent);
				break;
			case R.id.footer_clinicdates_btn_book:
				Toast.makeText(getApplicationContext(), "BOOK button - yeah!", Toast.LENGTH_SHORT).show();
				break;
			case R.id.footer_clinicdates_btn_calendar:
				Toast.makeText(getApplicationContext(), "CALENDAR button - yeah!", Toast.LENGTH_SHORT).show();
				break;
			}
		}
	}
	//---custom adapter------------
	public class MyAdapter extends ArrayAdapter<String> { 
		
		public MyAdapter(Context ctx, int txtViewResourceId, ArrayList<String> objects) { 
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
			convertView = inflater.inflate(R.layout.clinic_dates_adapter, parent, false); //layout adapter HERE!
			
			vHolder.tvName = (TextView) convertView.findViewById(R.id.clinicdates_adapter_text_head);
			vHolder.tvDays = (TextView) convertView.findViewById(R.id.clinicdates_adapter_text_sub);
		
			vHolder.tvName.setText((position+1) + " - ID: "+weekDays[0]); //add value from previous Activity for test only!
			//need to hold more than one day, when click on!!!!!!!!!!!!!!!!
			vHolder.tvDays.setText(myList.get(position).toString());// Available data for test only
			
			return convertView;
		} 
	}
	
	class ViewHolder{
		TextView tvName,tvDays;	
	}
	private String makeString(String[] days){
		String result="";
		for(String arr:days){
			result=result.concat(arr+", ");
		}
		return result;
	}
	

}
//Nick
