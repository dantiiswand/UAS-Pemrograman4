package com.checkupregister;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import org.apache.http.NameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;


public class HistoryActivity extends Activity {

    ListView list;

    JSONParser jParser = new JSONParser();
    ArrayList<History> daftar_history = new ArrayList<History>();
    JSONArray daftarHistory = null;
    String url = "http://192.168.43.250/CheckUp/aksi/read_history.php";
    // JSON Node names, ini harus sesuai yang di API
    public static final String TAG_SUCCESS = "success";
    public static final String TAG_HISTORY = "check_up";
    public static final String TAG_ID = "id";
    public static final String TAG_USER = "id_user";
    public static final String TAG_DOKTER = "id_dokter";
    public static final String TAG_STATUS = "Status";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        
        list = (ListView) findViewById(R.id.listview_history);

        //jalankan ReadMhsTask
        ReadMhsTask m= (ReadMhsTask) new ReadMhsTask().execute();

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();


        if (id == R.id.action_settings) {
            Intent i = new Intent(HistoryActivity.this, ProfilActivity.class);
            startActivity(i);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    class ReadMhsTask extends AsyncTask<String, Void, String>
    {
        ProgressDialog pDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(HistoryActivity.this);
            pDialog.setMessage("Mohon Tunggu..");
            pDialog.setIndeterminate(true);
            pDialog.setCancelable(true);
            pDialog.show();
        }

        @Override
        protected String doInBackground(String... sText) {
            String returnResult = getHistoryList(); //memanggil method getMhsList()
            return returnResult;

        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            pDialog.dismiss();
            if(result.equalsIgnoreCase("Exception Caught"))
            {
                Toast.makeText(HistoryActivity.this, "Unable to connect to server,please check your internet connection!", Toast.LENGTH_LONG).show();
            }

            if(result.equalsIgnoreCase("no results"))
            {
                Toast.makeText(HistoryActivity.this, "Data empty", Toast.LENGTH_LONG).show();
            }
            else
            {
                list.setAdapter(new HistoryAdapter(HistoryActivity.this,daftar_history)); //Adapter menampilkan data mahasiswa ke dalam listView
            }
        }


        //method untuk memperoleh daftar mahasiswa dari JSON
        public String getHistoryList()
        {
            History tempMhs = new History();
            List<NameValuePair> parameter = new ArrayList<NameValuePair>();
            try {
                JSONObject json = jParser.makeHttpRequest(url,"POST", parameter);

                int success = json.getInt(TAG_SUCCESS);
                if (success == 1) { //Ada record Data (SUCCESS = 1)
                    //Getting Array of daftar_mhs
                    daftarHistory = json.getJSONArray(TAG_HISTORY);
                    // looping through All daftar_mhs
                    for (int i = 0; i < daftarHistory.length() ; i++){
                        JSONObject c = daftarHistory.getJSONObject(i);
                        tempMhs = new History();
                        tempMhs.setId(c.getString(TAG_ID));
                        tempMhs.setUser(c.getString(TAG_USER));
                        tempMhs.setDokter(c.getString(TAG_DOKTER));
                        tempMhs.setStatus(c.getString(TAG_STATUS));
                        daftar_history.add(tempMhs);
                    }
                    return "OK";
                }
                else {
                    //Tidak Ada Record Data (SUCCESS = 0)
                    return "no results";
                }

            } catch (Exception e) {
                e.printStackTrace();
                return "Exception Caught";
            }
        }

    }
}
