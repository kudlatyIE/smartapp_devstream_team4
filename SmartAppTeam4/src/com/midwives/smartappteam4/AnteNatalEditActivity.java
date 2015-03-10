package com.midwives.smartappteam4;
/**
 * Lots of temporary stuff: some fields follow API, some, wireframe..... to be fixed one day......
 */
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.midwives.classes.Appointment;
import com.midwives.classes.BloodType;
import com.midwives.classes.ClinicalFields;
import com.midwives.classes.DataManager;
import com.midwives.classes.Rhesus;
import com.midwives.classes.ServiceUser;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class AnteNatalEditActivity extends Activity {
	
	private Intent intent;
	private EditText editEdd, editGestation, editParity, editObstretic; //for ClinicalFields in ServiceUser! - deleted: editBlood, editRhesus,
	private TextView tvBlood, tvRhesus;
	private Spinner spinnerBlood, spinnerRhesus;
	private ImageButton imgBtnBack;
	private Button btnDone, btnHome, btnBook;
	private String edd, gestation, parity,obstretic;
	private BloodType bloodType;
	private Rhesus rhesusValue;
	private ServiceUser serviceUser;
	private ClinicalFields clinicalFields;
	private Appointment apointment;
	private ArrayList<Appointment> appointmentsList; //we have to update appointments
	private ArrayList<String> bloodList, rhesusList;
	
	private String myBlood="", myRhesus="";
	private boolean rhesus;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_ante_natal_edit);
		
		serviceUser = DataManager.getServiceUser();
		
		imgBtnBack = (ImageButton) findViewById(R.id.serviceuseredit_header_imgbtn_back);
		btnDone = (Button) findViewById(R.id.serviceuseredit_header_btn_done);
		btnHome = (Button) findViewById(R.id.footer_btn_home);
		btnBook = (Button) findViewById(R.id.footer_btn_book);
		
		tvBlood = (TextView) findViewById(R.id.antenataledit_textView_blood);
		tvRhesus = (TextView) findViewById(R.id.antenataledit_textView_rhesus);
		
		editEdd = (EditText) findViewById(R.id.antenataledit_edit_edd);
		editGestation = (EditText) findViewById(R.id.antenataledit_edit_gestation);
//		editBlood = (EditText) findViewById(R.id.antenataledit_edit_blood);//to be replaced with spinner
//		editRhesus = (EditText) findViewById(R.id.antenataledit_edit_rhesus);//to be replaced with spinner
		editParity = (EditText) findViewById(R.id.antenataledit_edit_parity);
		editObstretic = (EditText) findViewById(R.id.antenataledit_edit_obstretic_history);
		
		spinnerBlood = (Spinner) findViewById(R.id.antenataledit_spinner_blood);
		spinnerRhesus = (Spinner) findViewById(R.id.antenataledit_spinner_rhesus);
		//create bloodType list for spinner
		bloodList = new ArrayList<String>();
		BloodType[] bt= BloodType.values();
		if(bt.length==0) Toast.makeText(getApplicationContext(), "Blood enum list is 0!", Toast.LENGTH_SHORT).show();
		else{
			for(BloodType b:bt) bloodList.add(b.getBloodType());
//				System.out.println(b.getBloodType());
		}

		
		//create Rhesus list for spinner
		rhesusList = new ArrayList<String>();
		Rhesus[] rh =Rhesus.values();
		if(rh.length==0) Toast.makeText(getApplicationContext(), "Rhesus enum list is 0!", Toast.LENGTH_SHORT).show();
		for(Rhesus r:rh){
			rhesusList.add(r.getRhesusValue());
		}

		editEdd.setText(serviceUser.getPregnancies().get(0).getEstDeliveryDate());//temporary EDD value, will be came from ClinicalFields....
//		editGestation.setText(serviceUser.getGestation());
		myBlood = serviceUser.getClinicalFields().getBloodType();
		editParity.setText(serviceUser.getClinicalFields().getParity());
		editObstretic.setText(serviceUser.getClinicalFields().getObsteticHistory());
		editGestation.setText(serviceUser.getPregnancies().get(0).getGestation());
		
		//buttons....
		MyButtons button = new MyButtons();
		imgBtnBack.setOnClickListener(button);
		btnHome.setOnClickListener(button);
		btnBook.setOnClickListener(button);
		btnDone.setOnClickListener(button);

		//create blood spinner.......

		
		SpinnerAdapter bloodAdapter = new SpinnerAdapter(this, R.layout.spinner_custom_item,bloodList);
		spinnerBlood.setAdapter(bloodAdapter);
		//set default bloodType value....
		int spinnerBloodPosition = bloodAdapter.getPosition(myBlood);
		spinnerBlood.setSelection(spinnerBloodPosition);
		spinnerBloodPosition=0;
		
		spinnerBlood.setOnItemSelectedListener(new OnItemSelectedListener(){

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				setBlood(parent.getItemAtPosition(position).toString());
//				tvBlood.setText(myBlood);
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				tvBlood.setText(serviceUser.getClinicalFields().getBloodType());
			}
			
		});

		SpinnerAdapter rhesusAdapter = new SpinnerAdapter(this,R.layout.spinner_custom_item,rhesusList);
		spinnerRhesus.setAdapter(rhesusAdapter);
		int rhesusPosition = rhesusAdapter.getPosition(myRhesus);
		spinnerRhesus.setSelection(rhesusPosition);
		rhesusPosition=0;
		
		spinnerRhesus.setOnItemSelectedListener(new OnItemSelectedListener(){

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				String str = parent.getItemAtPosition(position).toString();
				setRhesusString(str);
//				tvRhesus.setText(str);
				setIsRhesus(returnRhesusBoolean(str));
			}
			@Override
			public void onNothingSelected(AdapterView<?> parent) {
			}
		});
		
	}//end onCreate
	
	

	private class MyButtons implements OnClickListener{

		@Override
		public void onClick(View v) {
			switch(v.getId()){
			case R.id.serviceuseredit_header_imgbtn_back:
//				finish();
				//need to relaunch previous activity, to load a new data
				intent = new Intent(getApplicationContext(), AnteNatalActivity.class);
				startActivity(intent);
				break;
			case R.id.serviceuseredit_header_btn_done://create new ClinicalFields!
				try {
					edd = getNewValue(editEdd.getText().toString());
					gestation = getNewValue(editGestation.getText().toString());//......temporary lets switch gestation into BMI, ok?
//					blood = getNewValue(editBlood.getText().toString());
//					rhesus = getRhesus(getNewValue(editRhesus.getText().toString()));
					parity = getNewValue(editParity.getText().toString());
					obstretic = getNewValue(editObstretic.getText().toString());
					DataManager.setClinicalFields(new ClinicalFields(getBlood(), gestation,parity, obstretic, getIsRhesus()));//store new data before POST!
					
					//need set this new data into service_user array.... but how?
					//.................. to be continue.....................................
					//i need set flag when update this value, then previous activity has to refresh data
					finish();
					break;
				} catch (Exception e) {
					Toast.makeText(getApplicationContext(), "All fielsd must be filled", Toast.LENGTH_SHORT).show();
				}
				break;
			case R.id.footer_btn_home:
				intent = new Intent(getApplicationContext(),MainViewActivity.class);
				intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(intent);
				break;
			case R.id.footer_btn_book:
				intent = new Intent(getApplicationContext(),ServiceOptionActivity.class);
				intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(intent);
				break;
			}
		}
		
	}


	//create my custom spinner adapter....... in progress....
	private class SpinnerAdapter extends ArrayAdapter<String>{
		ArrayList<String> myList;
		public SpinnerAdapter(Context ctx, int txtViewResourceId, ArrayList<String> objects) { 
			super(ctx, txtViewResourceId, objects); 
			this.myList=objects;
		}
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			return getCustomView(position, convertView, parent);
		}

		@Override
		public View getDropDownView(int position, View convertView,	ViewGroup parent) {
			return getCustomView(position, convertView, parent);
		}
		public View getCustomView(int position, View convertView, ViewGroup parent){
			
			LayoutInflater inflater = getLayoutInflater();
			View rowView= inflater.inflate(R.layout.spinner_custom_item, parent,false);
			TextView tvSpinnerItem = (TextView) rowView.findViewById(R.id.spinner_custom_item_text);
			tvSpinnerItem.setText(myList.get(position));

			return rowView;
		}
	}


	//check if the new value is entered...
	private String getNewValue(String newValue) throws Exception{
		if(newValue.isEmpty())throw new Exception();
		return newValue;
	}
	
	private boolean returnRhesusBoolean(String rhesus){
		if(rhesus.equals(Rhesus.POSITIVE.getRhesusValue())) return true;
		return false;
	}
	private String returnRhesusString(boolean bool){
		if(true==bool) return Rhesus.POSITIVE.toString();
		return Rhesus.NEGATIVE.toString();
	}
	
	private void setBlood(String blood){
		this.myBlood=blood;
	}
	private String getBlood(){
		return this.myBlood;
	}
	public String getRhesusString() {
		return myRhesus;
	}
	public void setRhesusString(String rhesus){
		this.myRhesus=rhesus;
	}
	public boolean getIsRhesus() {
		return rhesus;
	}
	public void setIsRhesus(boolean rhesus) {
		this.rhesus = rhesus;
	}
	
}
//Nick
