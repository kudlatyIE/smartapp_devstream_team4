package com.midwives.smartappteam4;

import java.util.ArrayList;

import com.midwives.classes.*;

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
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class ServiceOptionActivity extends Activity {
	Button btnBack;
	ArrayList<ServiceOptions> myList;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_service_option);
		btnBack = (Button) findViewById(R.id.header_btn_back);
		//btnBack listsner
		btnBack.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				finish();
			}
		});
		
		myList = createServiceList(); //create testing list of Services
		
		final ListView lv = (ListView) findViewById(R.id.smart_listview);
		CustomAdapter serviceAdapter = new CustomAdapter();
		lv.setAdapter(serviceAdapter);
		lv.setClickable(false);
		//listView listener
		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				ServiceOptions item = (ServiceOptions) lv.getItemAtPosition(position);
				Toast.makeText(getApplicationContext(), "Click on: "+item.getServiceName(), Toast.LENGTH_SHORT).show();
			}
		});

	}// end onCreate()
	
	private class CustomAdapter extends BaseAdapter{
		
		LayoutInflater myInflater;
		
		CustomAdapter(){
			myInflater = (LayoutInflater) ServiceOptionActivity.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
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
				convertView = myInflater.inflate(R.layout.serviceoption_adapter, parent, false);
				//now inflate our adapter
				vHolder.tView1 = (TextView) convertView.findViewById(R.id.serviceoption_adapter_textitem);
				//there is only one field in service_option_adapter
				//we'll add more in other adapters....
			}else  convertView.setTag(vHolder);
			
			//set service name from ArrayList into adapter TextView
			vHolder.tView1.setText(myList.get(position).getServiceName());
			
			return convertView;
		}
		
	}
	
	class ViewHolder{
		//here we declare all fields for current adapter
		TextView tView1, tView2;
		
	}
	
	private ArrayList<ServiceOptions> createServiceList(){
		
		ArrayList<ServiceOptions> serviceList = new ArrayList<ServiceOptions>();
		
		serviceList.add(new ServiceOptions(0,"Domino (Dublin)"));
		serviceList.add(new ServiceOptions(1,"Domino (Wicklow)"));
		serviceList.add(new ServiceOptions(2,"ETH (Dublin)"));
		serviceList.add(new ServiceOptions(3,"ETH (Wicklow)"));
		serviceList.add(new ServiceOptions(4,"Satelite"));
		return serviceList;
	}
	
}
