package com.midwives.smartappteam4;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class PostNatalActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_post_natal);
		
//		populateListView();
//		populateListView2();
	}
	


//private void populateListView() {
//
//	//create list of items
//	String[] myItems = {"Ventose","1 Degree w/o sutures","To be given","Midwife notes"};
//
//	//Build adapter

//	ArrayAdapter<String> adapter = new ArrayAdapter<String>(
//		this,
//		R.layout.activity_post_natal_items,
//		myItems);
//
//		//configure the list view
//		ListView list = (ListView) findViewById(R.id.listViewMother);
//		list.setAdapter(adapter);
//	}
//
//private void populateListView2() {
//	
//	String[] myItems = {"Date of Delivery","Time of Delivery","Days since birth","Sex of baby","Birth weight(kg)","VitK"
//,"Hearing","Feeding","NBST"};
//	
//	ArrayAdapter<String> adapter = new ArrayAdapter<String>(
//			this,
//			R.layout.activity_post_natal_items,
//			myItems);
//	ListView list = (ListView) findViewById(R.id.listViewBaby);
//	list.setAdapter(adapter);
//}
}






