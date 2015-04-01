package com.midwives.smartappteam4;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

public class CustomDialogFragment1 extends DialogFragment {
	private String title;
	private String message;
	private String buttontext;
	private int activityOption;
	private Context context;
	
	//
		
	//customize parameters constructor
	public CustomDialogFragment1(Context context, String title, String message, String buttontext, int activityOption) {
		this.title = title;
		this.message = message;
		this.buttontext = buttontext;
		this.activityOption = activityOption;
		this.context = context;
	}

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        Dialog dialog = new Dialog(getActivity());
        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN);
        dialog.setContentView(R.layout.layout_custom_dialog1);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        
        TextView title_text = (TextView)dialog.findViewById(R.id.title);
        title_text.setText(title);
        TextView message_text = (TextView)dialog.findViewById(R.id.message);
        message_text.setText(message);
        Button buttonText = (Button)dialog.findViewById(R.id.positive_button);
        buttonText.setText(buttontext);

        // positive button click
        dialog.findViewById(R.id.positive_button).setOnClickListener(new OnClickListener() {
			@Override
            public void onClick(View v) {
            	switch(activityOption) {
            	case 0:
            		dismiss();
            		break;
            	case 1:           		
            		//Intent intent1 = new Intent(context, ActivityA.class);
            		//context.startActivity(intent1);
            		dismiss();
            		break;
            	case 2:
            		dismiss();
            		break;
            	case 3:
            		dismiss();
            		break;          		      	
            	}               
            }
        });

        return dialog;
        
    }//close method oncreatedialog
        
}//close class customdialogfragment1

