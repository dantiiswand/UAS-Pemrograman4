package com.checkupregister;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class ProfilActivity extends Activity implements OnClickListener {
	Button bDokter, bCU, bHistory; 
	
	@Override
	protected void onCreate(Bundle savedInstanceState){ 
		super.onCreate(savedInstanceState); 
		setContentView(R.layout.activity_profil);
		 bDokter = (Button) findViewById(R.id.buttonDokter);
		 bCU 	 = (Button) findViewById(R.id.buttonCU);
		 bHistory 	 = (Button) findViewById(R.id.buttonHistory);
		 
	     bDokter.setOnClickListener(this);
	     bCU.setOnClickListener(this);
	     bHistory.setOnClickListener(this);
	    }
	    
	    @Override
	    public void onClick(View v){ 
	    	switch(v.getId()){
	    	case R.id.buttonDokter : Intent intent1 = new Intent(ProfilActivity.this, JadwalDokterActivity.class);
	    						   startActivity(intent1);
	    	break;
	    	case R.id.buttonCU : Intent intent2 = new Intent(ProfilActivity.this, FormRegActivity.class);
			   					 startActivity(intent2);
			break;
	    	case R.id.buttonHistory : Intent intent3 = new Intent(ProfilActivity.this, HistoryActivity.class);
				 startActivity(intent3);
		    break;
	    	}
	    }
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.profil, menu);
		return true;
	}
}
