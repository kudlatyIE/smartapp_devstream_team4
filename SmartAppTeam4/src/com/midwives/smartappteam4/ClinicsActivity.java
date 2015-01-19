package com.midwives.smartappteam4;

import android.app.Activity;
import android.content.Intent;
//test change 
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

public class ClinicsActivity extends Activity {

	private Intent intent;
	private Bundle extras;
	private Button btnBack;
	private TextView tvTitle, tvSubtitle;
	
	private int id;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_clinics);
		//Receive Clinic Id from previous activity
		extras = getIntent().getExtras();
		id = Integer.parseInt(extras.getString("ClinicId"));
		
	}
}
