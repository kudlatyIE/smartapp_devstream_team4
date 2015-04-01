package com.midwives.smartappteam4;

import java.util.ArrayList;

import com.midwives.classes.*;
import com.midwives.parsers.ServiceOptionsParser;
import com.midwives.parsers.ServiceProviderParser;
import com.midwives.parsers.ServiceUserParser;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
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
	
	private Intent intent;
	private TextView tvTitle, tvSubtitle1, tvSubtitle2, tvContact,tvAddress,tvNextOfKin;
	private Button btnHome, btnBook, btnAnteNatal, btnPostNatal, btnBookAppointment;
	private ImageButton imgBtnBack, imgBtnEdit;
	private LinearLayout llContact,llAddress,llNextOfKin;//clicable
	
	private String serviceOptionUrl, serviceProviderUrl,serviceUserUrl,token,apiKey,jsonString;
	private String serviceUserName,serviceUserDetails; // display in tvSubtitle (name, age, gestation ect..)
	private String contact, address, nextOfKin, nextOfKinPhone;
	
	private String age;
	private int serviceUserId, serviceProviderId;
	
	private ArrayList<ServiceUser> myList;//but we expected only one object by ID...
	private ServiceUser serviceUser;
	private ArrayList<ServiceProvider>serviceProviderList;// but we expected only one object by ID???...
	private ServiceProvider serviceProvider;
	private Appointment appointment;
	private ArrayList<ServiceOptions> serviceOptionsList;
	
	private Links links;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_service_user);
		

		//resources set in AppointmentCalendar activity
		links = DataManager.getLinks();
		
		serviceOptionUrl = getResources().getString(R.string.auth_url_server).concat(links.getServiceOptions());
		serviceProviderUrl = getResources().getString(R.string.auth_url_server).concat(links.getServiceProviders());
		serviceUserUrl = getResources().getString(R.string.auth_url_server).concat(links.getServiceUsers());
		
		appointment = DataManager.getAppointment();//get data from AppointmentCalendar.....
		
		tvTitle=(TextView) findViewById(R.id.serviceuser_header_text_title);
		tvSubtitle1=(TextView) findViewById(R.id.serviceuser_header_text_subtitle1);
		tvSubtitle2=(TextView) findViewById(R.id.serviceuser_header_text_subtitle2);
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
		llNextOfKin.setOnClickListener(button);
	
		//lets get DB data!
		token = SmartAuth.getToken();
		apiKey = getResources().getString(R.string.auth_api_key);
		SmartAuth smart;

			smart = new SmartAuth(token, apiKey);
			jsonString = SmartAuth.accessTheDBTable(serviceOptionUrl);
			System.out.println(jsonString);
			serviceOptionsList = ServiceOptionsParser.parseServiceOptions(jsonString);
			DataManager.setServiceOptionsList(serviceOptionsList);
			
			//get serviceProviderId
			serviceProviderId = appointment.getServiceProviderId();
			//get serviceProvider from DM
			serviceProvider = DataManager.getServiceProviderMap().get(Integer.valueOf(serviceProviderId));
			DataManager.setServiceProvider(serviceProvider);
			//get serviceUser ID from appointment
			serviceUserId = appointment.getServiceUser().getId();
			//get selected serviceUser from DM
			serviceUser = DataManager.getServiceUserMap().get(Integer.valueOf(serviceUserId));
			DataManager.setServiceUser(serviceUser);

		
		//display data in textViews....
		tvTitle.setText(getResources().getString(R.string.title_activity_service_user));
		
		serviceUserName = serviceUser.getPersonalFields().getName();// need to add one more textView......
		tvSubtitle1.setText(serviceUserName);
		// need to find what is "P"
		age = XFiles.getAge(serviceUser.getPersonalFields().getDob());
		serviceUserDetails = String.valueOf(age).concat("'yrs, ").concat("G:"+appointment.getServiceUser().getGestation()
				.concat(", P:").concat(serviceUser.getClinicalFields().getParity()));//hm......
		tvSubtitle2.setText(serviceUserDetails);
		
		contact = serviceUser.getHospitalNumber()+"\n"+serviceUser.getPersonalFields().getEmail()+"\n"+serviceUser.getPersonalFields().getMobilePhone();
		tvContact.setText(contact);
		
		address = serviceUser.getPersonalFields().getHomeType()+"\n"+serviceUser.getPersonalFields().getHomeAddress();
		tvAddress.setText(address);
		
		nextOfKin = serviceUser.getPersonalFields().getNextOfKinName()+"\n"+serviceUser.getPersonalFields().getNextOfKinPhone();
		tvNextOfKin.setText(nextOfKin);
		
		nextOfKinPhone = serviceUser.getPersonalFields().getNextOfKinPhone();
	
	}//end onCreate
	
	//--------------------------------------------------------
	private class MyButtons implements OnClickListener{

		@Override
		public void onClick(View view) {

			switch(view.getId()){
				case R.id.serviceuser_header_imgbtn_back:
					Intent intent_a = new Intent(ServiceUserActivity.this, AppointmentCalendarActivity.class);
					startActivity(intent_a);
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
				case R.id.serviceuser_body_layout_contact:  //goes to phone and email
					intent = new Intent(getApplicationContext(), ServiceUserAddressActivity.class);
					startActivity(intent);
					break;
				case R.id.serviceuser_body_layout_address: //goes to google map of address 
					intent = new Intent(getApplicationContext(), ServiceUserAddressActivity.class);
					//get location, name,  address, directions
//					String location = serviceUser.getPersonalFields().getHomeAddress() + " " + serviceUser.getPersonalFields().getHomeCounty() + " " + "ireland";
//					String name = serviceUser.getPersonalFields().getName();
//					String address = serviceUser.getPersonalFields().getHomeType() + " - " + location;
//					String directions = serviceUser.getPersonalFields().getDirections();
//					
//					//send over to serviceuseraddressactivity
//					intent.putExtra("location", location); //needed for google maps
//					intent.putExtra("name", name);
//					intent.putExtra("address", address);
//					intent.putExtra("directions", directions);
					startActivity(intent);
					break;
				case R.id.serviceuser_body_layout_nextofkin:
					Intent intent = new Intent(Intent.ACTION_DIAL);
					intent.setData(Uri.parse("tel:"+nextOfKinPhone));
					if(intent.resolveActivity(getPackageManager())!=null) startActivity(intent);
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
