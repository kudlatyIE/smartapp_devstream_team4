package com.midwives.smartappteam4;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.midwives.classes.Appointment;
import com.midwives.classes.DataManager;
import com.midwives.classes.ServiceUser;
import com.midwives.classes.XFiles;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

public class FindServiceUserActivity extends Activity {
	
	private EditText editUserName, editYear, editMonth, editDay, editHospitalNo;
	private TextView tvTitle, tvSubtitle;
	private Button btnBack,btnHome,btnBook,btnSearch;
	private ListView lv;
	private MyAdapter adapter;
	private int matchLevel;
//	private String year,month,day,dob,userName,hospitalNo;
	private ServiceUser serviceUser;
	private HashMap<Integer,ServiceUser> serviceUserMap;//holds all serviceUsers, key is serviceUser ID
	private ArrayList<DbestMatch> searchResult;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_find_service_user);
		
		//geta data
		serviceUserMap = DataManager.getServiceUserMap();
		setServiceUserMap(serviceUserMap);
		//set views & btn listeners
		setViews();
		setBtnListener();
		
	
	}
	
	private void setViews(){

		btnBack = (Button) findViewById(R.id.header_btn_back);
		tvTitle = (TextView) findViewById(R.id.header_text_title);
		tvSubtitle = (TextView) findViewById(R.id.header_text_subtitle);
		
		editUserName = (EditText) findViewById(R.id.findservice_edit_find_user_name);
		editYear = (EditText) findViewById(R.id.findservice_edit_find_user_dob_year);
		editYear.setInputType(InputType.TYPE_CLASS_NUMBER);
		editMonth = (EditText) findViewById(R.id.findservice_edit_find_user_dob_month);
		editMonth.setInputType(InputType.TYPE_CLASS_NUMBER);
		editDay = (EditText) findViewById(R.id.findservice_edit_find_user_dob_day);
		editDay.setInputType(InputType.TYPE_CLASS_NUMBER);
		editHospitalNo = (EditText) findViewById(R.id.findservice_edit_find_hospitalNo);
		btnSearch = (Button) findViewById(R.id.findservice_btn_search);
		
//		adapter = new MyAdapter(getApplicationContext(),getSearchResult(),getServiceUserMap());
//		setAdapter(adapter);
		lv  =(ListView) findViewById(R.id.findservice_list);
		
		btnHome = (Button) findViewById(R.id.footer_btn_home);
		btnBook = (Button) findViewById(R.id.footer_btn_book);
	}
	
	private void setBtnListener(){
		MyButtons button = new MyButtons();
		btnBack.setOnClickListener(button);
		btnHome.setOnClickListener(button);
		btnSearch.setOnClickListener(button);

	}
	
	/**
	 * read search parameters from editTexts(userName, DOB, hospitalNo)
	 * @param usersMap - map of all users
	 * @return ArrayList<matchValue, serviceUserID>
	 */
	private ArrayList<DbestMatch> dbestMatchList(HashMap<Integer,ServiceUser> usersMap){
		String patternName="",patternYear="",patternMonth="",patternDay="",patternDob="",patternHospital="";
		String name, dob, hospitalNo;
		int match,matchTotal,matchName, matchDob, matchHospital;
		int size, sizeName, sizeDob, sizeHospital,sizeN, sizeY, sizeM,sizeD,sizeH;
		
		List<DbestMatch> unsortedList = new ArrayList<DbestMatch>();
		ArrayList<DbestMatch> result = new ArrayList<DbestMatch>();
		try{
			sizeN=editUserName.getText().toString().length();
			sizeY=editYear.getText().toString().length();
			sizeM=editMonth.getText().toString().length();
			sizeD=editDay.getText().toString().length();
			sizeH=editHospitalNo.getText().toString().length();
			if(sizeN!=0) patternName = editUserName.getText().toString().toLowerCase();
			if(sizeY!=0) patternYear = editYear.getText().toString();
			if(sizeM!=0) patternMonth = editMonth.getText().toString();
			if(sizeD!=0) patternDay = editDay.getText().toString();
			if(sizeH!=0) patternHospital = editHospitalNo.getText().toString().toLowerCase();
			
			if((sizeY!=0)&&(sizeM!=0)&&(sizeD!=0)) patternDob = patternYear.concat("-").concat(patternMonth).concat("-").concat(patternDay);
			else patternDob="";
		}catch(Exception ex){
			ex.printStackTrace();
		}
		
		for(Integer key:usersMap.keySet()){
			matchTotal=0;match=0;
			matchName=0;matchDob=0;matchHospital=0;
			size=0;sizeName=0;sizeDob=0;sizeHospital=0;
			name=usersMap.get(key).getPersonalFields().getName().toLowerCase();
			dob=usersMap.get(key).getPersonalFields().getDob();
			hospitalNo=usersMap.get(key).getHospitalNumber().toLowerCase();
			
			if((name.length()!=0)&&(patternName.length()!=0)){
				matchName=XFiles.compareNames(patternName,name);// check IT!................................
				if(matchName>1) matchName=matchName+penalty(name);

				sizeName = Math.max(name.length(), patternName.length());

			}
			if((dob.length()!=0)&&(patternDob.length()!=0)) {
				matchDob = XFiles.compareNames(patternDob, dob);
				if(matchDob>0)matchDob=matchDob+penalty(dob);
				sizeDob = Math.max(dob.length(), patternDob.length());

			}
//			if((hospitalNo.length()!=0)&&(patternHospital.length()!=0)) {
			if((patternHospital.length()!=0)) {
				matchHospital=XFiles.compareNames(patternHospital, hospitalNo);
				if(matchHospital>0) matchHospital=matchHospital+penalty(hospitalNo);
				sizeHospital=Math.max(hospitalNo.length(), patternHospital.length());

			}
			match=matchName+matchDob+matchHospital;
			size=sizeName+sizeDob+sizeHospital;
			matchTotal=(100*(size-match))/size;	

			unsortedList.add(new DbestMatch(matchTotal,key));

		}	
		//sort results by match value
		Comparator<DbestMatch> comp = new CompareIt();
		Collections.sort(unsortedList, comp);
		result.addAll(unsortedList);
		return result;
	}
	/**
	 * add penalty points if strings are different
	 * @param s
	 * @return
	 */
	private int penalty(String s){
		return (int) 0.5*s.length();
	}
	

	private class MyButtons implements OnClickListener{
		Intent intent;
		@Override
		public void onClick(View v) {
			switch(v.getId()){
			case R.id.header_btn_back:
				finish();
				break;
			case R.id.findservice_btn_search:
				setSearchResult(dbestMatchList(serviceUserMap));
				displaySearchResult(getSearchResult(),getServiceUserMap());//test
				lv.setAdapter(new MyAdapter(getApplicationContext(),getSearchResult(),getServiceUserMap()));
				lv.setOnItemClickListener(new OnItemClickListener(){

					@Override
					public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
						Intent intent = new Intent(getApplicationContext(), ConfirmAppointmentActivity.class);
						intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
						Appointment app = DataManager.getAppointmentFreeSlot();
						DataManager.setAppointmentFreeSlot(new Appointment(app.getAppDate(),app.getAppTime(),app.getServiceProviderId(),
																getSearchResult().get(position).getId(),app.getClinicId(),null,null));
						startActivity(intent);
					}
					
				});
				break;
			case R.id.footer_btn_home:
				intent = new Intent(getApplicationContext(), MainViewActivity.class);
				startActivity(intent);
				break;
			case R.id.footer_btn_book:
				break;
			}
			
		}
		
	}
	private void displaySearchResult(ArrayList<DbestMatch>match, HashMap<Integer,ServiceUser> all){
		Integer key;
		System.out.println("---------------------------------------------------");
		for(DbestMatch i: match){
			key=i.getId();
			System.out.println(i.getMatch()+"% | ID: "+all.get(key).getId()+" | "+ 
								all.get(key).getPersonalFields().getDob()+" | "+
								all.get(key).getHospitalNumber()+" | "+
								all.get(key).getPersonalFields().getName());
		}
	}
	
	private class MyAdapter extends BaseAdapter{
		ArrayList<DbestMatch> matchList;
		HashMap<Integer,ServiceUser> allUsers;
		LayoutInflater inflater;
		TextView tvMatch, tvName, tvDOB, tvHospitalNo;
		Integer key;
		
		MyAdapter(Context context, ArrayList<DbestMatch>match, HashMap<Integer,ServiceUser> all){
			this.matchList=match;
			this.allUsers=all;
			System.out.println("MyAdapter, matchSize: "+matchList.size());
			System.out.println("MyAdapter, allUserSize: "+allUsers.size());
			inflater = LayoutInflater.from(context);
		}
		
		@Override
		public int getCount() {
			return matchList.size();
		}

		@Override
		public Object getItem(int position) {
			return matchList.get(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			View view = null;
			if(convertView==null){
				view=inflater.inflate(R.layout.find_serviceuser_adapter, parent, false);
			}else view = convertView;
			
			tvMatch = (TextView) view.findViewById(R.id.findserviceuser_adapter_text_match_value);
			tvName = (TextView) view.findViewById(R.id.findserviceuser_adapter_text_name);
			tvDOB = (TextView) view.findViewById(R.id.findserviceuser_adapter_text_dob);
			tvHospitalNo = (TextView) view.findViewById(R.id.findserviceuser_adapter_text_hospital);
			
			tvMatch.setText(matchList.get(position).getMatch()+"%");
			
			key=matchList.get(position).getId();//serviceUserID == key in HashMap<Integer,ServiceUser>
			try{
				tvName.setText(allUsers.get(key).getPersonalFields().getName());
			}catch (Exception ex){
				tvName.setText("N/A");
			}
			try{
				tvDOB.setText(allUsers.get(key).getPersonalFields().getDob());
			}catch (Exception ex){
				tvDOB.setText("N/A");
			}
			try{
				tvHospitalNo.setText(allUsers.get(key).getHospitalNumber());
			}catch (Exception ex){
				tvHospitalNo.setText("N/A");
			}
			
			return view;
		}
		
	}
	private class DbestMatch{
		private int match;
		private Integer id;
		
		DbestMatch(int match, Integer id){
			this.match=match;
			this.id=id;
		}

		public int getMatch() {
			return match;
		}

		public void setMatch(int match) {
			this.match = match;
		}

		public Integer getId() {
			return id;
		}

		public void setId(Integer id) {
			this.id = id;
		}
	}
	
	private class CompareIt implements Comparator<DbestMatch>{

		@Override
		public int compare(DbestMatch arg0, DbestMatch arg1) {
			Integer x = (Integer) arg0.getMatch();
			Integer y = (Integer) arg1.getMatch();
			if(x.compareTo(y)<0) return 1;
			if(x.compareTo(y)>0) return -1;
			return 0;
		}
	}
	
	public ArrayList<DbestMatch> getSearchResult() {
		return searchResult;
	}

	public void setSearchResult(ArrayList<DbestMatch> searchResult) {
		this.searchResult = searchResult;
	}	

	public HashMap<Integer, ServiceUser> getServiceUserMap() {
		return serviceUserMap;
	}

	public void setServiceUserMap(HashMap<Integer, ServiceUser> serviceUserMap) {
		this.serviceUserMap = serviceUserMap;
	}

	public MyAdapter getAdapter() {
		return adapter;
	}

	public void setAdapter(MyAdapter adapter) {
		this.adapter = adapter;
	}
}

//Nick

//Iterator it = usersMap.entrySet().iterator();
//while(it.hasNext()){
//	Map.Entry pair = (Entry) it.next();
//	
//}
