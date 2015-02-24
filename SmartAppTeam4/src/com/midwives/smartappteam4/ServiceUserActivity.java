package com.midwives.smartappteam4;

import java.util.ArrayList;

import com.midwives.classes.*;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class ServiceUserActivity extends Activity {
	
	private Bundle extras;
	private Intent intent;
	private TextView tvTitle, tvSubtitle, tvContact,tvAddress,tvNextOfKin;
	private Button btnHome, btnBook, btnAnteNatal, btnPostNatal, btnBookAppointment;
	private ImageButton imgBtnBack, imgBtnEdit;
	private LinearLayout llContact,llAddress,llNextOfKin;
	
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
		btnHome=(Button) findViewById(R.id.footer_btn_home);
		btnBook=(Button) findViewById(R.id.footer_btn_book);
		
		imgBtnBack=(ImageButton) findViewById(R.id.serviceuser_header_imgbtn_back);
		imgBtnEdit=(ImageButton) findViewById(R.id.serviceuser_header_imgbtn_edit);
		
		llContact = (LinearLayout) findViewById(R.id.serviceuser_body_layout_contact);
		llAddress = (LinearLayout) findViewById(R.id.serviceuser_body_layout_address);
		llNextOfKin = (LinearLayout) findViewById(R.id.serviceuser_body_layout_nextofkin);
		
		tvTitle.setText(R.string.title_activity_service_user);
		
		MyButtons button = new MyButtons();
		imgBtnBack.setOnClickListener(button);
		imgBtnEdit.setOnClickListener(button);
		btnAnteNatal.setOnClickListener(button);
		btnPostNatal.setOnClickListener(button);
		btnBookAppointment.setOnClickListener(button);
		btnHome.setOnClickListener(button);
		btnBook.setOnClickListener(button);
		llContact.setOnClickListener(button);
		llAddress.setOnClickListener(button);
	
	
	}//end onCreate
	
	private class MyButtons implements OnClickListener{

		@Override
		public void onClick(View view) {

			switch(view.getId()){
				case R.id.serviceuser_header_imgbtn_back:
					finish();
					break;
				case R.id.serviceuser_header_imgbtn_edit:
					intent = new Intent(getApplicationContext(), ServiceUserEditActivity.class);
					startActivity(intent);
					break;
				case R.id.serviceuser_body_btn_antenatal:
					intent = new Intent(getApplicationContext(), AnteNatalActivity.class);
					startActivity(intent);
					break;
				case R.id.serviceuser_body_btn_postnatal:
					intent = new Intent(getApplicationContext(), PostNatalActivity.class);
					startActivity(intent);
					break;
				case R.id.serviceuser_body_book_appointment:
					Toast.makeText(getApplicationContext(), "go to Ante-Natal BOOKING ????", Toast.LENGTH_SHORT).show();
					break;
				case R.id.serviceuser_body_layout_contact:
					intent = new Intent(getApplicationContext(), ServiceUserAddressActivity.class);
					startActivity(intent);
					break;
				case R.id.serviceuser_body_layout_address:
					intent = new Intent(getApplicationContext(), ServiceUserAddressActivity.class);
					startActivity(intent);
					break;
				case R.id.serviceuser_body_text_user_nextofkin:
					Toast.makeText(getApplicationContext(), "nothinh happend here", Toast.LENGTH_SHORT).show();
					break;
				case R.id.footer_btn_home:
					intent = new Intent(getApplicationContext(),MainViewActivity.class);
					intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
					startActivity(intent);
					break;
				case R.id.footer_btn_book:
					Toast.makeText(getApplicationContext(), "BOOK: go to ServiceOption Activity", Toast.LENGTH_SHORT).show();
					break;
					
			}
			
		}


		
	}//end MyButtons
}
