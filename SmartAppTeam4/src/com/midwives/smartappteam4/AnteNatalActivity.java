package com.midwives.smartappteam4;

import java.util.ArrayList;

import com.midwives.classes.Appointment;
import com.midwives.classes.DataManager;
import com.midwives.classes.ServiceOptions;
import com.midwives.classes.ServiceProvider;
import com.midwives.classes.ServiceUser;
import com.midwives.classes.XFiles;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class AnteNatalActivity extends Activity {
	
	private Button btnHome, btnBook;//footer
	private ImageButton imgBtnBack, imgBtnEdit;//header
	private Button btnAnteNatal, btnPersonal, btnPostNatal, btnParity, btnObstretric; //body
	private TextView tvTitle, tvSubtitle1, tvSubtitle2;
	private TextView tvEDD, tvGestation, tvBlood,tvRhesus,tvParity, tvObstretic;//body
	
	//handle extras values for AnteNatal Activity
	private String eed, blood, rhesus, parity, obstretic;
	private String[] dob, gestation, gender, birthMode;
	private int [] weight;
	//handle extras values for PostNatal Activity
	private String motherBirthMode, motherPerineum, motherAntiD;
	private String[] motherMidwifeNotes;
	private String babeDeliveryDate, babeDeliveryTime, babeDaysSindeBirth, babeGender, babeBirthWeight,
					babeVitK, babeHearing, babeFeeding, babeNBST;
	
	private String serviceUserName, serviceUserDetails;
	private int age;
	
	private ServiceUser serviceUser;
	private ServiceProvider serviceProvider;
	private Appointment appointment;// I'm not sure will be used here.......
	private ArrayList<ServiceOptions> serviceOptionsList;
	
	
	// to be continue...............

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_ante_natal);
		imgBtnBack=(ImageButton) findViewById(R.id.serviceuser_header_imgbtn_back);
		imgBtnEdit=(ImageButton) findViewById(R.id.serviceuser_header_imgbtn_edit);
		btnHome=(Button) findViewById(R.id.footer_btn_home);
		btnBook=(Button) findViewById(R.id.footer_btn_book);
		
		tvTitle=(TextView) findViewById(R.id.serviceuser_header_text_title);
		tvSubtitle1=(TextView) findViewById(R.id.serviceuser_header_text_subtitle1);
		tvSubtitle2=(TextView) findViewById(R.id.serviceuser_header_text_subtitle2);
		tvEDD = (TextView) findViewById(R.id.antenatal_text_edd);
		tvGestation = (TextView) findViewById(R.id.antenatal_text_gestation_weeks);
		tvBlood = (TextView) findViewById(R.id.antenatal_text_bloodgroup_type);
		tvRhesus = (TextView) findViewById(R.id.antenatal_text_rhesus_value);
		tvParity = (TextView) findViewById(R.id.antenatal_text_parity);
		tvObstretic = (TextView) findViewById(R.id.antenatal_text_obstretic_history);
		
		btnAnteNatal=(Button) findViewById(R.id.antenatal_btn_antenatal);
		btnPostNatal=(Button) findViewById(R.id.antenatal_btn_postnatal);
		btnPersonal = (Button) findViewById(R.id.antenatal_btn_personal);
		btnParity = (Button) findViewById(R.id.antenatal_btn_parity);
		btnObstretric = (Button) findViewById(R.id.antenatal_btn_obstretic_history);
		
		MyButtons button = new MyButtons();
		imgBtnBack.setOnClickListener(button);
		imgBtnEdit.setOnClickListener(button);
		btnHome.setOnClickListener(button);
		btnBook.setOnClickListener(button);
		btnPersonal.setOnClickListener(button);
		btnParity.setOnClickListener(button);
		btnObstretric.setOnClickListener(button);
		
		//get data.....
		serviceUser = DataManager.getServiceUser();
		serviceProvider = DataManager.getServiceProvider();
		appointment = DataManager.getAppointment();// I'm not sure will be used here.......
		serviceOptionsList = DataManager.getServiceOptionsList();
		
		//populate data in textViews......
		tvTitle.setText(getResources().getString(R.string.title_activity_service_user));
		
		serviceUserName = serviceUser.getPersonalFields().getName();// need to add one more textView......
		tvSubtitle1.setText(serviceUserName);
		// need to find what is "P"
		age = XFiles.getAge(serviceUser.getPersonalFields().getDob());
		serviceUserDetails = String.valueOf(age).concat("yrs, ").concat("G:"+appointment.getServiceUser().getGestation()
							.concat(", P:").concat(serviceUser.getClinicalFields().getParity()));//hm......
		tvSubtitle2.setText(serviceUserDetails);
		
		tvEDD.setText(serviceUser.getPregnancies().get(0).getEstDeliveryDate());// to be fixed: what this EDD came from?
		tvGestation.setText(serviceUser.getPregnancies().get(0).getGestation());
		tvBlood.setText(serviceUser.getClinicalFields().getBloodType());
		
		//rhesus - get msg
		if(serviceUser.getClinicalFields().getRhesus()) rhesus="Negaive";
		else rhesus="Positive";
		tvRhesus.setText(rhesus);
		
		tvParity.setText(serviceUser.getClinicalFields().getParity());
		tvObstretic.setText(serviceUser.getClinicalFields().getObstetricHistory());
		
		
	}
	
	private class MyButtons implements OnClickListener{
		Intent intent;
		@Override
		public void onClick(View v) {
			
			switch(v.getId()){
			case R.id.serviceuser_header_imgbtn_back:
				finish();
				break;
			case R.id.serviceuser_header_imgbtn_edit:
				intent = new Intent(getApplicationContext(),AnteNatalEditActivity.class);
				intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(intent);
				break;
			case R.id.antenatal_btn_postnatal:
				intent = new Intent(getApplicationContext(), PostNatalActivity.class);
				intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(intent);
				break;
			case R.id.antenatal_btn_personal:
				intent = new Intent(getApplicationContext(), ServiceUserActivity.class);
				intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(intent);
				break;
			case R.id.antenatal_btn_parity:
				intent = new Intent(getApplicationContext(), ParityActivity.class);
				intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(intent);
				break;
			case R.id.antenatal_btn_obstretic_history:
				intent = new Intent(getApplicationContext(), ObstreticHistoryActivity.class);
				intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(intent);
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
		
	}
}
