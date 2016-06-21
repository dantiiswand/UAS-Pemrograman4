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


public class JadwalDokterActivity extends Activity {

    ListView list;

    JSONParser jParser = new JSONParser();
    ArrayList<Dokter> daftar_dokter = new ArrayList<Dokter>();
    JSONArray daftarDokter = null;
    String url_read_dokter = "http://192.168.43.250/CheckUp/aksi/read_dokter.php";
    // JSON Node names, ini harus sesuai yang di API
    public static final String TAG_SUCCESS = "success";
    public static final String TAG_DOKTER = "jadwal_dokter";
    public static final String TAG_ID_DOKTER = "id_dokter";
    public static final String TAG_NAMA_DOKTER = "nama";
    public static final String TAG_HARI_DOKTER = "hari";
    public static final String TAG_JAM_DOKTER = "jam";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jadwal_dokter);
        
        list = (ListView) findViewById(R.id.listview_dokter);

        //jalankan ReadMhsTask
        ReadMhsTask m= (ReadMhsTask) new ReadMhsTask().execute();

        list.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int urutan, long id) {
                //dapatkan data id, nama, nim mahasiswa dan simpan dalam variable string
                String iddokter = ((TextView) view.findViewById(R.id.id_dokter)).getText().toString();
                String nama = ((TextView) view.findViewById(R.id.nama_dokter)).getText().toString();
                String hari = ((TextView) view.findViewById(R.id.hari_dokter)).getText().toString();
                String jam = ((TextView) view.findViewById(R.id.jam)).getText().toString();

                //varible string tadi kita simpan dalam suatu Bundle, dan kita parse bundle tersebut bersama intent menuju kelas UpdateDeleteActivity
                Intent i = null;
                i = new Intent(JadwalDokterActivity.this, DetailDokActivity.class);
                Bundle b = new Bundle();
                b.putString("id_dokter", iddokter);
                b.putString("nama_dokter", nama);
                b.putString("hari_dokter", hari);
                b.putString("jam", jam);
                i.putExtras(b);
                startActivity(i);
            }
        });
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
            Intent i = new Intent(JadwalDokterActivity.this, DetailDokActivity.class);
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
            pDialog = new ProgressDialog(JadwalDokterActivity.this);
            pDialog.setMessage("Mohon Tunggu..");
            pDialog.setIndeterminate(true);
            pDialog.setCancelable(true);
            pDialog.show();
        }

        @Override
        protected String doInBackground(String... sText) {
            String returnResult = getDokterList(); //memanggil method getMhsList()
            return returnResult;

        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            pDialog.dismiss();
            if(result.equalsIgnoreCase("Exception Caught"))
            {
                Toast.makeText(JadwalDokterActivity.this, "Unable to connect to server,please check your internet connection!", Toast.LENGTH_LONG).show();
            }

            if(result.equalsIgnoreCase("no results"))
            {
                Toast.makeText(JadwalDokterActivity.this, "Data empty", Toast.LENGTH_LONG).show();
            }
            else
            {
                list.setAdapter(new DokterAdapter(JadwalDokterActivity.this,daftar_dokter)); //Adapter menampilkan data mahasiswa ke dalam listView
            }
        }


        //method untuk memperoleh daftar mahasiswa dari JSON
        public String getDokterList()
        {
            Dokter tempMhs = new Dokter();
            List<NameValuePair> parameter = new ArrayList<NameValuePair>();
            try {
                JSONObject json = jParser.makeHttpRequest(url_read_dokter,"POST", parameter);

                int success = json.getInt(TAG_SUCCESS);
                if (success == 1) { //Ada record Data (SUCCESS = 1)
                    //Getting Array of daftar_mhs
                    daftarDokter = json.getJSONArray(TAG_DOKTER);
                    // looping through All daftar_mhs
                    for (int i = 0; i < daftarDokter.length() ; i++){
                        JSONObject c = daftarDokter.getJSONObject(i);
                        tempMhs = new Dokter();
                        tempMhs.setDokterId(c.getString(TAG_ID_DOKTER));
                        tempMhs.setDokterNama(c.getString(TAG_NAMA_DOKTER));
                        tempMhs.setDokterHari(c.getString(TAG_HARI_DOKTER));
                        tempMhs.setDokterJam(c.getString(TAG_JAM_DOKTER));
                        daftar_dokter.add(tempMhs);
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
