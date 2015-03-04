package com.midwives.smartappteam4;

import java.util.ArrayList;
import java.util.Date;

import com.midwives.classes.Appointment;
import com.midwives.classes.DataManager;
import com.midwives.classes.ServiceUser;
import com.midwives.classes.VisitLogs;
import com.midwives.classes.XFiles;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

public class ObstreticHistoryActivity extends Activity {
	
	private TextView tvTitle, tvSubtitle1, tvSubtitle2;
	private EditText editNote;
	private ImageButton imgBtnBack;
	private Button btnAddNote;
	private ServiceUser serviceUser;
	private Appointment appointment;
	private int listSize;
	private String serviceUserName,serviceUserDetails;
	private ArrayList<VisitLogs> myList;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_obstretic_history);
		
		tvTitle = (TextView) findViewById(R.id.parity_header_text_title);
		tvSubtitle1 = (TextView) findViewById(R.id.parity_header_text_subtitle1);
		tvSubtitle2 = (TextView) findViewById(R.id.parity_header_text_subtitle2);
		
		imgBtnBack = (ImageButton) findViewById(R.id.parity_header_imgbtn_back);
		btnAddNote = (Button) findViewById(R.id.obstretric_btn_add_new_note);
		editNote = (EditText) findViewById(R.id.obstretric_edit_new_note);
		
		serviceUser = DataManager.getServiceUser();
		appointment = DataManager.getAppointment();
		
		MyButtons button = new MyButtons();
		
		//just for test - I not sure what does visitLogs came from....
		if(DataManager.getVisitLogs()==null) myList = createNotes();
		else myList = DataManager.getVisitLogs();
		
		imgBtnBack.setOnClickListener(button);
		btnAddNote.setOnClickListener(button);
		
		//display all notes
		
		generateNoteList();
	
	}//end onCreate
	
	//create note list
	private void generateNoteList(){ // to be continue.....................
		View rowView;
		LayoutInflater inflater;
		TextView tvRowDate, tvRowNote;
		TableLayout table;
		table = (TableLayout) this.findViewById(R.id.obstretric_table_visitlogs);
		
		for(int i=0; i<myList.size();i++){
			inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			rowView = inflater.inflate(R.layout.obstretric_row, null);
			
			tvRowDate = (TextView) rowView.findViewById(R.id.obstretic_text_visit_date);
			tvRowNote = (TextView) rowView.findViewById(R.id.obstretric_text_visit_note);
			
			tvRowDate.setText(myList.get(i).getVisitDate());
			tvRowNote.setText(myList.get(i).getVisitNote());
			
			table.addView(rowView);
		}
	}
	
	private ArrayList<VisitLogs>createNotes(){
		ArrayList<VisitLogs> notes = new ArrayList<VisitLogs>();
		
		notes.add(new VisitLogs("15/02/2015", "bla bla bla and ble ble ble...."));
		notes.add(new VisitLogs("24/12/2014", "cat's walk: xnjbewc  idnejiolkwenlewdc lewlkmed e3kndm ied ienclkwdmxn"));
		notes.add(new VisitLogs("29/11/2014", "monky writing: iuxwnxxUUUUUUUUUUUUUUkswkj2s  jwns nxnkwjnex  wenx xwenx wxnwnn nenw we "));
		return notes;
	}
	
	private class MyButtons implements OnClickListener{

		@Override
		public void onClick(View v) {
			switch(v.getId()){
			case R.id.parity_header_imgbtn_back:
				finish();
				break;
			case R.id.obstretric_btn_add_new_note:
				String date, note="empty";
				Date now = new Date();
				try{
					if (editNote.getText().toString().isEmpty()) throw new Exception();
					note = editNote.getText().toString();
					date = XFiles.getShortDate(now.toString());
					myList.add(new VisitLogs(date, note));
					DataManager.setVisitLogs(myList);
					finish();
				}catch(Exception e){
					Toast.makeText(getApplicationContext(), "note can't be empty!!!", Toast.LENGTH_SHORT).show();
				}
				
				break;
			}
		}
		
	}

}
