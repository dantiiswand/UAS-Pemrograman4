package com.checkupregister;

import java.util.ArrayList;
import java.util.List;
 
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;
 
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.app.ProgressDialog;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
 
public class FormRegActivity extends Activity {
 
    ProgressDialog pDialog;
 
    JSONParser jsonParser = new JSONParser();
    EditText IDPasien, IDDokter; 
    Button bRegis, bCancelForm;
    
    private static String url = "http://192.168.43.250/CheckUp/daftar.php?";
 
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_reg);
 
        IDPasien    =(EditText)findViewById(R.id.editIDPasien);
        IDDokter   =(EditText)findViewById(R.id.editIDDokter);
        bRegis = (Button)findViewById(R.id.buttonRegis);
        bCancelForm = (Button)findViewById(R.id.buttonCancelForm);
        
        bRegis.setOnClickListener(new View.OnClickListener() {
 
            @Override
            public void onClick(View v) {
                    new daftarah().execute();
            }
        });
        
        bCancelForm.setOnClickListener(new View.OnClickListener() {
        	 
            @Override
            public void onClick(View v) {
            	FormRegActivity.this.finish();
            }
        });
    }
 
    public class daftarah extends AsyncTask<String, String, String> {
 
        String success;
 
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(FormRegActivity.this);
            pDialog.setMessage("Proses mendaftar...");
            pDialog.setIndeterminate(false);
            pDialog.show();
        }
 
        @Override
        protected String doInBackground(String... params) {
 
            String strpasien      = IDPasien.getText().toString();
            String strdokter     = IDDokter.getText().toString();
 
            List<NameValuePair> nvp = new ArrayList<NameValuePair>();
            nvp.add(new BasicNameValuePair("nama", strpasien));
            nvp.add(new BasicNameValuePair("email", strdokter));
 
            JSONObject json = jsonParser.makeHttpRequest(url, "POST", nvp);
            try { 
                success = json.getString("success");
 
            } catch (Exception e) {
                Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_LONG).show();
            }
 
            return null;
        }
 
        protected void onPostExecute(String file_url) {
            // dismiss the dialog once done
            pDialog.dismiss();
 
            if (success.equals("1")) {
                Toast.makeText(getApplicationContext(), "Regitrasi sukses", Toast.LENGTH_LONG).show();
 
            } else {
                Toast.makeText(getApplicationContext(), "Registrasi gagal", Toast.LENGTH_LONG).show();
            }
        }
 
    }
 
}