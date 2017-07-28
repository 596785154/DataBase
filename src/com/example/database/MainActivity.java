package com.example.database;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity{
private Button Button1;
DBAdapter db=new DBAdapter(this);
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Button1=(Button)findViewById(R.id.button1);
		Button1.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				db.open();
				db.insert(001,"nana"," feng",001);
				System.out.println("≤Â»Î≥…π¶");
				db.close();
			}
			
		});
	}
	
}