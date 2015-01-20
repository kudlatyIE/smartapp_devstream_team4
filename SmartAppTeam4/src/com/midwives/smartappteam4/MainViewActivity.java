package com.midwives.smartappteam4;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MainViewActivity extends Activity {
	
	Intent intent;
	Button btnClinics,btnVisits,btnBack;
	TextView tvTitle, tvSubtitle;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_view);
		
		btnClinics = (Button) findViewById(R.id.mainview_btn_clinics);
		btnVisits = (Button) findViewById(R.id.mainview_btn_visits);
		btnBack = (Button) findViewById(R.id.header_btn_back);
		
		tvTitle = (TextView) findViewById(R.id.header_text_title);
		tvSubtitle = (TextView) findViewById(R.id.header_text_subtitle);	
		// set title and subtitle for this activity
		tvTitle.setText(R.string.mainview_title);
		tvSubtitle.setText(R.string.mainview_subtitle);
		
		MyButtons myButton = new MyButtons();
		btnClinics.setOnClickListener(myButton);
		btnVisits.setOnClickListener(myButton);
		btnBack.setOnClickListener(myButton);
		
	}
	
	private class MyButtons implements OnClickListener{

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent intent;
			switch(v.getId()){
				case R.id.mainview_btn_clinics:
					intent = new Intent(getApplicationContext(), ServiceOptionActivity.class);
					intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
					startActivity(intent);
					break;
				case R.id.mainview_btn_visits:
					intent = new Intent(getApplicationContext(), ServiceOptionActivity.class);// temporary intent. to be changed to Visits...............
					intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
					startActivity(intent);
					break;
				case R.id.header_btn_back:
					finish(); // try it.....
			}
		}
	}
}
