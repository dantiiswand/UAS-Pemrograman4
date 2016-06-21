package com.checkupregister;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;


public class DetailDokActivity extends Activity {

    JSONParser jParser = new JSONParser();
    
    // JSON Node names, ini harus sesuai yang di API
    public static final String TAG_SUCCESS = "success";
    public static final String TAG_ID_DOKTER = "id_dokter";
    public static final String TAG_NAMA_DOKTER = "nama_dokter";
    public static final String TAG_HARI_DOKTER = "hari_dokter";
    public static final String TAG_JAM_DOKTER = "jam";

    TextView ID, NAMA, HARI, JAM;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_dok);

        ID = (TextView) findViewById(R.id.textIDDok);
        NAMA = (TextView) findViewById(R.id.textNAMA);
        HARI = (TextView) findViewById(R.id.textHARI);
        JAM = (TextView) findViewById(R.id.textJAM);

        //menangkap bundle yang telah di-parsed dari MainActivity
        Bundle b = getIntent().getExtras();
        String isi_id_dokter = b.getString("id_dokter");
        String isi_nama_dokter= b.getString("nama_dokter");
        String isi_hari_dokter= b.getString("hari_dokter");
        String isi_jam_dokter= b.getString("jam");
        
        //meng-set bundle tersebut
        ID.setText(isi_id_dokter);
        NAMA.setText(isi_nama_dokter);
        HARI.setText(isi_hari_dokter);
        JAM.setText(isi_jam_dokter);


    }
}

