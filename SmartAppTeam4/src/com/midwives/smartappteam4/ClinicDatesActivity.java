package com.midwives.smartappteam4;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

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
	private String hint,clinicName, weekDay; 

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_clinic_dates);
		
		//Receive data from previous activity...
		extras = getIntent().getExtras();
		clinicName = extras.getString("clinicName");
		weekDay = extras.getString("weekDay");
		
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
		
		//create fake data list for test only...
		myList = XFiles.getDateList(weekDay);
		//populate listView content
		ListView lv = (ListView) findViewById(R.id.smart_listview);
		CustomAdapter customAdapter = new CustomAdapter();
		lv.setAdapter(customAdapter);
		
		//listView listener....
		lv.setOnItemClickListener(new OnItemClickListener(){

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Toast.makeText(getApplicationContext(), "Click on: "+myList.get(position).toString(), Toast.LENGTH_SHORT).show();
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
	
	private class CustomAdapter extends BaseAdapter{
		
		LayoutInflater myInflater;
		
		CustomAdapter(){
			myInflater = (LayoutInflater) ClinicDatesActivity.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		}

		@Override
		public int getCount() {
			return myList.size();
		}

		@Override
		public Object getItem(int position) {
			return position;
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			final ViewHolder vHolder = new ViewHolder();
			
			if(convertView==null){
				convertView = myInflater.inflate(R.layout.clinic_dates_adapter, parent, false);
				//now inflate our adapter
				vHolder.tvName = (TextView) convertView.findViewById(R.id.clinicdates_adapter_text_head);
				vHolder.tvDays = (TextView) convertView.findViewById(R.id.clinicdates_adapter_text_sub);
				//there are two fields (textView)
			}else  convertView.setTag(vHolder);
			
			//set service name from ArrayList into adapter TextView
			vHolder.tvName.setText((position+1) + " - ID: "+weekDay); //add value from previous Activity for test only!

			vHolder.tvDays.setText(myList.get(position).toString());// Available data for test only
			
			return convertView;
		}
		
	}
	
	class ViewHolder{
		//here we declare all fields for current adapter
		TextView tvName,tvDays;	
	}

}
//Nick
