package com.checkupregister;


import java.util.ArrayList;
import java.util.HashMap;
import com.checkupregister.History;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class HistoryAdapter extends BaseAdapter {
    private Activity activity;
    //private ArrayList<HashMap<String, String>> data;
    private ArrayList<History> data_history=new ArrayList<History>();

    private static LayoutInflater inflater = null;

    public HistoryAdapter(Activity a, ArrayList<History> d) {
        activity = a; data_history = d;
        inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    public int getCount() {
        return data_history.size();
    }
    public Object getItem(int position) {
        return data_history.get(position);
    }
    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View vi = convertView;
        if (convertView == null)
            vi = inflater.inflate(R.layout.activity_list_dokter, null);
        TextView id = (TextView) vi.findViewById(R.id.id);
        TextView id_user = (TextView) vi.findViewById(R.id.id_user);
        TextView id_dokter_history = (TextView) vi.findViewById(R.id.id_dokter_history);
        TextView Status = (TextView) vi.findViewById(R.id.Status);

        History daftar_history = data_history.get(position);
        id.setText(daftar_history.getId());
        id_user.setText(daftar_history.getUser());
        id_dokter_history.setText(daftar_history.getDokter());
        Status.setText(daftar_history.getStatus());

        return vi;
    }
}

