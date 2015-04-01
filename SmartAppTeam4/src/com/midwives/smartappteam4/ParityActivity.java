package com.midwives.smartappteam4;

import java.util.ArrayList;
import java.util.HashMap;

import com.midwives.classes.Appointment;
import com.midwives.classes.Baby;
import com.midwives.classes.DataManager;
import com.midwives.classes.Pregnancies;
import com.midwives.classes.ServiceUser;
import com.midwives.classes.XFiles;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class ParityActivity extends Activity {
	
	private TextView tvDOB, tvGestation, tvGender, tvWeight, tvBirthMode;
	private TextView tvTitle, tvSubtitle1, tvSubtitle2;
//	private TableLayout tbDOB, tbGestation, tbGender, tbWeight, tbBirthMode;
	private ImageButton imgBtnBack;

	
	private ServiceUser serviceUser;
	private Appointment appointment;
	private String age;
	private int[] babiesIDs, pregnancyIDs;
	private String serviceUserName,serviceUserDetails;
	
	private HashMap<Integer,Baby> babyMap;
	private HashMap<Integer,Pregnancies> pregnancyMap;
	ArrayList<Tab> myList;//test list only
	ArrayList<Baby>babyList;
	ArrayList<Pregnancies> pregnanciesList;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_parity);
		
		tvDOB = (TextView) findViewById(R.id.parity_tab_title_dob);
		tvGestation = (TextView) findViewById(R.id.parity_tab_title_gestation);
		tvGender = (TextView) findViewById(R.id.parity_tab_title_gender);
		tvWeight = (TextView) findViewById(R.id.parity_tab_title_weight);
		tvBirthMode = (TextView) findViewById(R.id.parity_tab_title_birthmode);
		
		tvTitle = (TextView) findViewById(R.id.parity_header_text_title);
		tvSubtitle1 = (TextView) findViewById(R.id.parity_header_text_subtitle1);
		tvSubtitle2 = (TextView) findViewById(R.id.parity_header_text_subtitle2);
		
//		tbDOB = (TableLayout) findViewById(R.id.parity_table_dob);
//		tbGestation = (TableLayout) findViewById(R.id.parity_table_gestation);
//		tbGender = (TableLayout) findViewById(R.id.parity_table_gender);
//		tbWeight = (TableLayout) findViewById(R.id.parity_table_weight);
//		tbBirthMode = (TableLayout) findViewById(R.id.parity_table_birthmode);
		
		imgBtnBack = (ImageButton) findViewById(R.id.parity_header_imgbtn_back);
		
		tvDOB.setText("DOB");
		tvGestation.setText("GESTSTION");
		tvGender.setText("SEX");
		tvWeight.setText("WEIGHT (kg)");
		tvBirthMode.setText("Mode Of Birth");
		
		//get parity data
		myList = Tab.getList();
		serviceUser = DataManager.getServiceUser();
		appointment = DataManager.getAppointment();
		
		babiesIDs = serviceUser.getBabyIds();
		pregnancyIDs = serviceUser.getPregnencyIds();
		babyList= new ArrayList<Baby>();
		babyMap = DataManager.getBabyMap();
		
		for(int id:babiesIDs){
			babyList.add(babyMap.get(Integer.valueOf(id)));
		}
		pregnanciesList = new ArrayList<Pregnancies>();
		pregnancyMap = DataManager.getPregnanciesMap();
		for(int id: pregnancyIDs){
			pregnanciesList.add(pregnancyMap.get(Integer.valueOf(id)));
		}
		

		System.out.println("DOB size: "+babyList.size());
		for(Baby b: babyList){
			System.out.println("DOB: "+b.getDeliveryDateTime()+" | ID: "+b.getId()+" | PregID: "+b.getPregnancyId()
					//+" | GEST: "+pregnanciesList.get(pregnanciesList.indexOf(b.getPregnancyId())).getGestation());
					);
		}
		System.out.println("Gestation size: "+pregnanciesList.size());
		for(Pregnancies p:pregnanciesList){
			System.out.println("Gest: "+p.getGestation()+" | bayID: "+p.getBabyIds()[0]);
		}
		
		serviceUserName = serviceUser.getPersonalFields().getName();
		tvTitle.setText(getResources().getString(R.string.title_activity_parity));
		tvSubtitle1.setText(serviceUserName);
		// need to find what is "P"
		age = XFiles.getAge(serviceUser.getPersonalFields().getDob());
		serviceUserDetails = String.valueOf(("Parity: ").concat(serviceUser.getClinicalFields().getParity()));//hm......
		tvSubtitle2.setText(serviceUserDetails);
		
		imgBtnBack.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				finish();
			}	
		});
		
		
		// populate all my TableLayouts....................
		
		createDOB();
		createGestation();
		createSex();
		createWeight();
		createBirthMode();
		
		
		//myList test only! 
		myList = Tab.getList();
		
	
	}//end onCreate

	//--------------
	private void createDOB(){
		View rowView;
		LayoutInflater inflater;
		TextView tvRowValue, tvRowIndex;
		TableLayout table;
		table = (TableLayout) this.findViewById(R.id.parity_table_dob);
		
		for(int i=0; i<babyList.size();i++){
			inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			rowView = inflater.inflate(R.layout.parity_row, null);
			
			tvRowValue = (TextView) rowView.findViewById(R.id.parity_row_text_value);
			tvRowIndex = (TextView) rowView.findViewById(R.id.parity_row_text_index);
			String shortDate = XFiles.getShortDate(babyList.get(i).getDeliveryDateTime());
			String birthOutcome = babyList.get(i).getBirthOutcome();
			tvRowValue.setText(shortDate.concat(" ("+birthOutcome+")"));
			tvRowIndex.setText(myList.get(i).getIndex());
			
			table.addView(rowView);
		}
	}
	//--------
	private void createGestation(){
		View rowView;
		LayoutInflater inflater;
		TextView tvRowValue, tvRowIndex;
		TableLayout table;
		table = (TableLayout) this.findViewById(R.id.parity_table_gestation);
		
		for(int i=0; i<babyList.size();i++){
			inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			rowView = inflater.inflate(R.layout.parity_row, null);
			
			tvRowValue = (TextView) rowView.findViewById(R.id.parity_row_text_value);
			tvRowIndex = (TextView) rowView.findViewById(R.id.parity_row_text_index);

			String gestation="";
			for(Pregnancies p:pregnanciesList){
				if(p.getId()==babyList.get(i).getPregnancyId()) {
					gestation=p.getGestation();
					break;
				}
			}
			tvRowValue.setText(gestation);
			tvRowIndex.setText(myList.get(i).getIndex());
			
			table.addView(rowView);
		}
	}
	//-----------------
	private void createSex(){
		View rowView;
		LayoutInflater inflater;
		TextView tvRowValue, tvRowIndex;
		TableLayout table;
		table = (TableLayout) this.findViewById(R.id.parity_table_gender);
		
		for(int i=0; i<babyList.size();i++){
			inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			rowView = inflater.inflate(R.layout.parity_row, null);
			
			tvRowValue = (TextView) rowView.findViewById(R.id.parity_row_text_value);
			tvRowIndex = (TextView) rowView.findViewById(R.id.parity_row_text_index);

			String sex;
			if(babyList.get(i).getGender().equals("F")) sex = "Female";
			else sex = "Male";
			tvRowValue.setText(sex);
			tvRowIndex.setText(myList.get(i).getIndex());
			
			table.addView(rowView);
		}
	}
	//----------------
	private void createWeight(){
		View rowView;
		LayoutInflater inflater;
		TextView tvRowValue, tvRowIndex;
		TableLayout table;
		table = (TableLayout) this.findViewById(R.id.parity_table_weight);
		
		for(int i=0; i<babyList.size();i++){
			inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			rowView = inflater.inflate(R.layout.parity_row, null);
			
			tvRowValue = (TextView) rowView.findViewById(R.id.parity_row_text_value);
			tvRowIndex = (TextView) rowView.findViewById(R.id.parity_row_text_index);
			double weight=babyList.get(i).getWeight()*0.001;
			tvRowValue.setText(String.format("%.2f", weight));
//			tvRowValue.setText(String.valueOf(babyList.get(i).getWeight()));
			tvRowIndex.setText(myList.get(i).getIndex());
			
			table.addView(rowView);
		}
	}
	//------------
	private void createBirthMode(){
		View rowView;
		LayoutInflater inflater;
		TextView tvRowValue, tvRowIndex;
		TableLayout table;
		table = (TableLayout) this.findViewById(R.id.parity_table_birthmode);
		
		for(int i=0; i<babyList.size();i++){
			inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			rowView = inflater.inflate(R.layout.parity_row, null);
			
			tvRowValue = (TextView) rowView.findViewById(R.id.parity_row_text_value);
			tvRowIndex = (TextView) rowView.findViewById(R.id.parity_row_text_index);

			String birthMode="";
			for(Pregnancies p:pregnanciesList){
				if(p.getId()==babyList.get(i).getPregnancyId()) {
					birthMode=p.getBirthMode()[0];
					break;
				}
			}
			tvRowValue.setText(birthMode);
			tvRowIndex.setText(myList.get(i).getIndex());
			
			table.addView(rowView);
		}
	}

}

//class for test only !!!!!
	class Tab{
	String value, index;
	
	Tab(String value, String index){
		this.value=value;
		this.index=index;
	}

	public static ArrayList<Tab> getList(){
		
		ArrayList<Tab> list = new ArrayList<Tab>();
		list.add(new Tab("my first Value", "A"));
		list.add(new Tab("my second Value", "B"));
		list.add(new Tab("my third Value", "C"));
		list.add(new Tab("my fourth Value", "E"));
		list.add(new Tab("my fourth Value", "F"));
		list.add(new Tab("my fourth Value", "G"));
		list.add(new Tab("my fourth Value", "H"));
		list.add(new Tab("my fourth Value", "I"));
		list.add(new Tab("my fourth Value", "J"));
		
		return list;
	}
	
	
	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getIndex() {
		return index;
	}

	public void setIndex(String index) {
		this.index = index;
	}
}
