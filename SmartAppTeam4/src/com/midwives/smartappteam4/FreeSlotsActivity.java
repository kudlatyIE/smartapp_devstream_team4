package com.midwives.smartappteam4;

import com.midwives.classes.Appointment;
import com.midwives.classes.ServiceUser;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class FreeSlotsActivity extends Activity {
	
	// temporary we jump over this activity directly to FindServiceUser Activity

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_free_slots);
	}
}
