package com.midwives.smartappteam4;

import com.midwives.classes.Appointment;
import com.midwives.classes.Clinics;
import com.midwives.classes.DataManager;
import com.midwives.classes.ServiceUser;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

public class ConfirmAppointmentActivity extends Activity {
	
	private Appointment appointment;// from appointmentsFreeSlot
	private ServiceUser serviceUser;
	private Clinics clinic;
	private TextView tvTitle, tvSubtitle,tvUserName,tvHospitalNo,tvClinicName,tvDate, tvTime,tvDuration, 
					tvEmail,tvSMS, tvPriority, tvVisitType;
	private Button btnBack, btnHome, btnBook, btnConfirm;
	
	//need to provide priority and visitType from ENUM, will be provide by spinners (?)
	
	private int clinicID, serviceUserID, serviceProviderID;
	
	//annoncements from ClinicID to do later
	//

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_confirm_appointment);
		//get resources and values
		clinic = DataManager.getClinics();
		appointment = DataManager.getAppointmentFreeSlot();//may be check if is empty?
		clinicID = appointment.getClinicId();
		serviceUserID = appointment.getServiceUserId();
		serviceProviderID = appointment.getServiceProviderId();
		serviceUser = DataManager.getServiceUserMap().get(Integer.valueOf(serviceUserID));
		
		declareView();
		setTextViews();
	}
	
	
	private void declareView(){
		tvTitle = (TextView) findViewById(R.id.header_text_title); 
		tvSubtitle = (TextView) findViewById(R.id.header_text_subtitle);
		tvUserName = (TextView) findViewById(R.id.app_confirm_text_user_name);
		tvHospitalNo = (TextView) findViewById(R.id.app_confirm_text_hospital_number);
		tvClinicName = (TextView) findViewById(R.id.app_confirm_text_clinic_name);
		tvDate = (TextView) findViewById(R.id.app_confirm_text_date);
		tvTime = (TextView) findViewById(R.id.app_confirm_text_time);
		tvDuration = (TextView) findViewById(R.id.app_confirm_text_duration);
		tvEmail = (TextView) findViewById(R.id.app_confirm_text_email);
		tvSMS = (TextView) findViewById(R.id.app_confirm_text_user_phone);

		//spinners for priority and visit type
		
		btnBack = (Button) findViewById(R.id.footer_btn_home);
		btnBook = (Button) findViewById(R.id.footer_btn_book);
		btnBack = (Button) findViewById(R.id.header_btn_back);
		btnConfirm = (Button) findViewById(R.id.app_confirm_btn_confirm);
	}
	
	private void setTextViews(){
		
		tvTitle.setText(getResources().getString(R.string.title_activity_confirm_appointment));
		tvSubtitle.setText("Temporary Announcement!");
		tvUserName.setText(serviceUser.getPersonalFields().getName());
		tvHospitalNo.setText(serviceUser.getHospitalNumber());
		tvClinicName.setText(clinic.getClinicName());
		tvDate.setText(appointment.getAppDate());
		tvTime.setText(appointment.getTime());
		tvDuration.setText(clinic.getAppointmentInterval()+" min");
		tvEmail.setText(serviceUser.getPersonalFields().getEmail());
		tvSMS.setText(serviceUser.getPersonalFields().getMobilePhone());
		
	}
}
