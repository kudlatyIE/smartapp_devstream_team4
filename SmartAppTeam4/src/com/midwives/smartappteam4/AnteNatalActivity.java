package com.midwives.smartappteam4;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class AnteNatalActivity extends Activity {
	
	private Bundle extras;
	//handle extras values for AnteNatal Activity
	private String eed, blood, rhesus, parity, obstretic;
	private String[] dob, gestation, gender, birthMode;
	private int [] weight;
	//handle extras values for PostNatal Activity
	private String motherBirthMode, motherPerineum, motherAntiD;
	private String[] motherMidwifeNotes;
	private String babeDeliveryDate, babeDeliveryTime, babeDaysSindeBirth, babeGender, babeBirthWeight,
					babeVitK, babeHearing, babeFeeding, babeNBST;
	
	
	// to be continue...............

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_ante_natal);
	}
}
