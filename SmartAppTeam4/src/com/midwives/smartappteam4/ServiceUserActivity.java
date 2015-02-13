package com.midwives.smartappteam4;

import java.util.ArrayList;

import com.midwives.classes.*;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class ServiceUserActivity extends Activity {
	
	private Bundle extras;
	private Intent intent;
	private TextView tvTitle, tvSubtitle, tvContact,tvAddress,tvNextOfKin;
	private Button btnHome, btnBook, btnAnteNatal, btnPostNatal, btnBookAppointment;
	private ImageButton imgBtnBack, imgBtnEdit;
	
	private String serviceOptionUrl, serviceProviderUrl,serviceUserUrl;
	
	private ArrayList<ServiceUser> myList;
	private ArrayList<ServiceProvider>serviceProviderList;
	private ArrayList<ServiceOptions> serviceOptionsList;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_service_user);
		
		//get links from Appointments json
		extras = getIntent().getExtras();
		serviceOptionUrl = extras.getString("service option");
		serviceProviderUrl = extras.getString("service provider");
		serviceUserUrl = extras.getString("service user");
		
		tvTitle=(TextView) findViewById(R.id.serviceuser_header_text_title);
		tvSubtitle=(TextView) findViewById(R.id.serviceuser_header_text_subtitle);
		tvContact=(TextView) findViewById(R.id.serviceuser_body_text_user_contact);
		tvAddress=(TextView) findViewById(R.id.serviceuser_body_text_user_address);
		tvNextOfKin=(TextView) findViewById(R.id.serviceuser_body_text_user_nextofkin);
		
		btnAnteNatal=(Button) findViewById(R.id.serviceuser_body_btn_antenatal);
		btnPostNatal=(Button) findViewById(R.id.serviceuser_body_btn_postnatal);
		btnBookAppointment=(Button) findViewById(R.id.serviceuser_body_book_appointment);
		
		imgBtnBack=(ImageButton) findViewById(R.id.serviceuser_header_imgbtn_back);
		imgBtnEdit=(ImageButton) findViewById(R.id.serviceuser_header_imgbtn_edit);
		
		tvTitle.setText(R.string.title_activity_service_user);
	
	
	}//end onCreate
	
	private class myButtons implements OnItemClickListener{

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,	long id) {

			switch(view.getId()){
				
				case R.id.serviceuser_body_btn_antenatal:
					break;
				case R.id.serviceuser_body_btn_postnatal:
					break;
				case R.id.serviceuser_body_book_appointment:
					break;
					
			}
			
		}
		
	}
}
