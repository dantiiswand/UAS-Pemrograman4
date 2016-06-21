package com.checkupregister;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity implements OnClickListener {
	Button bMain, bExit;
 
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
 
        bMain = (Button) findViewById(R.id.buttonMain);
        bExit = (Button) findViewById(R.id.buttonExit);
 
        bMain.setOnClickListener(this);
        bExit.setOnClickListener(this);
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu){ 
    	getMenuInflater().inflate(R.menu.main, menu);
    	return true;
    }
    
    @Override
    public void onClick(View v){ 
    	switch(v.getId()){
    	case R.id.buttonMain : Intent intent = new Intent(MainActivity.this, LoginActivity.class);
    						   startActivity(intent);
    	break;
    	case R.id.buttonExit : showExitDialog();
		break;
    	}
    }
    
    private void showExitDialog(){ 
    	AlertDialog.Builder ad = new AlertDialog.Builder(this);
    	ad.setTitle("Warning");
    	ad.setMessage("Are you sure to exit?");
    	ad.setPositiveButton("yes", new DialogInterface.OnClickListener() {
    		@Override
    		public void onClick(DialogInterface dialog, int which){
    			MainActivity.this.finish();
    		}
    	});
    	
    	ad.setNegativeButton("no", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				dialog.dismiss();
			}
		});
    	ad.show();
    }
}