package com.midwives.smartappteam4;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.location.Address;
import android.location.Geocoder;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.midwives.classes.DataManager;
import com.midwives.classes.ServiceUser;


public class ServiceUserAddressActivity extends Activity {
	   private static final LatLng DUBLIN_IRELAND = new LatLng(53.3401586197085 , -6.259285980291502);
	   private String location; // = "13 Dawson St Dublin Ireland";
	   private GoogleMap googleMap; 
	   private Button btnBack;
	   private TextView username;
	   private TextView add;
	   private TextView direct;
	    private ServiceUser serviceUser;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_service_user_address);
		
		btnBack = (Button)findViewById(R.id.serviceuser_map_btn_back);
		btnBack.setOnClickListener(new handleButtonsClicked());
		
		//get address from serviceUeseractivity(originated from personalfields class)
		serviceUser = DataManager.getServiceUser();
		
//		Intent i = getIntent();
		String location = serviceUser.getPersonalFields().getHomeAddress() + " " + serviceUser.getPersonalFields().getHomeCounty() + " " + "ireland";; 
		String name = serviceUser.getPersonalFields().getName();
		String addres = serviceUser.getPersonalFields().getHomeType() + " - " + location;
		String directions = serviceUser.getPersonalFields().getDirections();
		
		username = (TextView)findViewById(R.id.serviceuser_map_username);
		add = (TextView)findViewById(R.id.serviceuser_map_address);
		direct = (TextView)findViewById(R.id.serviceuser_text_hint);
		username.setText(name);
		add.setText(addres);
		direct.setText(directions);
		
	      try {  //use location to get google map 
	    	  LatLng locationLatLng=null;
	    	  double lat,lng;
	    	  Geocoder coder = new Geocoder(this);
	    	  List<Address> address = coder.getFromLocationName(location, 1);
	    	  try{
	    		  lat  = address.get(0).getLatitude();
		    	  lng = address.get(0).getLongitude();
		    	  
		    	  System.out.println("Lat: "+ lat+ " | Lng: "+lng);
	    	  }catch(IndexOutOfBoundsException ex){
	    		  lat=0;lng=0;
	    		  System.out.println("Lat & Lng == dupa!");
//	    		  CustomDialogFragment1 dialog = new CustomDialogFragment1(this, "Address Not Found", "Error in Address or try again!", "Close", 0);
//	 	  		 dialog.getShowsDialog();
	    		  
	    	  }

	    	 locationLatLng = new LatLng(lat, lng);
	    	 googleMap = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();
	         googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
	         googleMap.addMarker(new MarkerOptions().position(locationLatLng).title(location));
	         googleMap.getUiSettings().setZoomControlsEnabled(true);
	 		 CameraUpdate update = CameraUpdateFactory.newLatLngZoom(locationLatLng, 16);
			 googleMap.animateCamera(update);

	      } catch (Exception e) {
	    	 // Toast.makeText(getApplicationContext(), location + "- NOT FOUND", Toast.LENGTH_LONG).show();
	    	 CustomDialogFragment1 dialog = new CustomDialogFragment1(this, "Address Not Found", "Error in Address or try again!", "Close", 0);
	  		 dialog.getShowsDialog();
	         e.printStackTrace();
	      }
			
	}//close method onCreate
	
	private class handleButtonsClicked implements OnClickListener {
		@Override
		public void onClick(View v) {
			switch(v.getId()) {
			case R.id.serviceuser_map_btn_back:
				Intent inten = new Intent(ServiceUserAddressActivity.this, ServiceUserActivity.class);
				startActivity(inten);
				break;
			
			}//close switch	
			
		}//close method onclick
		
	}// close class handleButtonsClicked
	
}//close class ServiceUserAddressActivity
