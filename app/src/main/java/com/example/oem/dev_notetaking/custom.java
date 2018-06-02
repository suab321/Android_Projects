package com.example.oem.dev_notetaking;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.zip.Inflater;

public class custom extends ArrayAdapter<item>{
    View v;
    public custom(Context context, ArrayList<item>notes){
        super(context,R.layout.custom,notes);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater li= LayoutInflater.from(getContext());
        v=li.inflate(R.layout.custom,parent,false);
        TextView tv1=(TextView)v.findViewById(R.id.tv1);
        TextView tv2=(TextView)v.findViewById(R.id.tv2);
        TextView tv3=(TextView)v.findViewById(R.id.tv3);
        item e=(item)getItem(position);
        tv1.setText(e.getSub());
        tv2.setText(e.getBody());
        tv3.setText(e.getDate());
        return v;
    }

}
