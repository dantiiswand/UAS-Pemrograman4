package com.checkupregister;

import java.util.ArrayList;
import java.util.HashMap;
 
import org.json.JSONArray;
import org.json.JSONObject;
 
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
 

public class LoginActivity extends Activity{
 
    Button bLogin, bCancel;
    Intent a;
    EditText nama,password;
    String url, success;
    SessionManager session;
 
    
    //public boolean onCreateOptionsMenu(Menu menu){ 
    	//getMenuInflater().inflate(R.menu.login, menu);
    	//return true;
    //}
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
 
        session = new SessionManager(getApplicationContext());
 
        bLogin = (Button) findViewById(R.id.button);
        bCancel = (Button) findViewById(R.id.buttonCancel);
       
        nama = (EditText) findViewById(R.id.nama);
        password = (EditText) findViewById(R.id.password);
        
        bCancel.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				LoginActivity.this.finish();
			}
		});
 
        bLogin.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				 url = "http://192.168.43.250/CheckUp/login.php?" + "nama="
	                        + nama.getText().toString() + "&password="
	                        + password.getText().toString();
	 
	                if (nama.getText().toString().trim().length() > 0
	                        && password.getText().toString().trim().length() > 0) 
	                {
	                    new masuk().execute();
	                } 
	                else
	                {
	                    Toast.makeText(getApplicationContext(), "Username/password masih kosong gan.!!", Toast.LENGTH_LONG).show();
	                }
			}
		});
    }
 
    public class masuk extends AsyncTask<String, String, String> 
    {
        ArrayList<HashMap<String, String>> contactList = new ArrayList<HashMap<String, String>>();
        ProgressDialog pDialog;
 
        @Override
        protected void onPreExecute() {
            // TODO Auto-generated method stub
            super.onPreExecute();
 
            pDialog = new ProgressDialog(LoginActivity.this);
            pDialog.setMessage("Tunggu Bentar ya...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }
        @Override
        protected String doInBackground(String... arg0) {
            JSONParser jParser = new JSONParser();
 
            JSONObject json = jParser.getJSONFromUrl(url);
 
            try {
                success = json.getString("success");
 
                Log.e("error", "nilai sukses=" + success);
 
                JSONArray hasil = json.getJSONArray("button");
 
                if (success.equals("1")) {
 
                    for (int i = 0; i < hasil.length(); i++) {
 
                        JSONObject c = hasil.getJSONObject(i);
 
                        String nama = c.getString("nama").trim();
                        String email = c.getString("password").trim();
                        session.createLoginSession(nama, email);
                        Log.e("ok", " ambil data");
 
                    }
                } else {
                    Log.e("erro", "tidak bisa ambil data 0");
                }
 
            } catch (Exception e) {
                // TODO: handle exception
                Log.e("erro", "tidak bisa ambil data 1");
            }
 
            return null;
 
        }
        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            pDialog.dismiss();
            if (success.equals("1")) {
                a = new Intent(LoginActivity.this, ProfilActivity.class);
                startActivity(a);
                finish();
            } else {
 
                Toast.makeText(getApplicationContext(), "Username/password salah gan.!!", Toast.LENGTH_LONG).show();
            }
 
        }
 
    }
 
}