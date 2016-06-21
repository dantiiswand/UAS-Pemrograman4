package com.checkupregister;


import java.util.ArrayList;
import java.util.HashMap;
import com.checkupregister.Dokter;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class DokterAdapter extends BaseAdapter {
    private Activity activity;
    //private ArrayList<HashMap<String, String>> data;
    private ArrayList<Dokter> data_dokter=new ArrayList<Dokter>();

    private static LayoutInflater inflater = null;

    public DokterAdapter(Activity a, ArrayList<Dokter> d) {
        activity = a; data_dokter = d;
        inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    public int getCount() {
        return data_dokter.size();
    }
    public Object getItem(int position) {
        return data_dokter.get(position);
    }
    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View vi = convertView;
        if (convertView == null)
            vi = inflater.inflate(R.layout.activity_list_dokter, null);
        TextView id_dokter = (TextView) vi.findViewById(R.id.id_dokter);
        TextView nama_dokter = (TextView) vi.findViewById(R.id.nama_dokter);
        TextView hari_dokter = (TextView) vi.findViewById(R.id.hari_dokter);
        TextView jam = (TextView) vi.findViewById(R.id.jam);

        Dokter daftar_dokter = data_dokter.get(position);
        id_dokter.setText(daftar_dokter.getDokterId());
        nama_dokter.setText(daftar_dokter.getDokterNama());
        hari_dokter.setText(daftar_dokter.getDokterHari());
        jam.setText(daftar_dokter.getDokterJam());

        return vi;
    }
}

