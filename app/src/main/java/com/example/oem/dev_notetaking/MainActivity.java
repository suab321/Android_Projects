package com.example.oem.dev_notetaking;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.zip.Inflater;

public class MainActivity extends AppCompatActivity {
    ArrayList<item> notes;
    ArrayAdapter<item> aa;
    ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lv = (ListView) findViewById(R.id.lv);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                item e = (item) parent.getItemAtPosition(position);
                Intent ii=new Intent(MainActivity.this,edit.class);
                ii.putExtra("item",e);
                startActivity(ii);
            }
        });
        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                final item it = (item) parent.getItemAtPosition(position);
                lv.setItemChecked(position, true);
                DialogInterface.OnClickListener dicl=new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which){
                            case DialogInterface.BUTTON_POSITIVE:
                                if(save.delete(getApplicationContext(),it)){
                                    Toast.makeText(MainActivity.this,"Deleted",Toast.LENGTH_SHORT).show();
                                    onResume();
                                }
                                else
                                    Toast.makeText(MainActivity.this,"Not Deleted",Toast.LENGTH_SHORT).show();
                                    break;
                            case DialogInterface.BUTTON_NEGATIVE:
                                break;
                        }
                    }
                };
                AlertDialog.Builder adb=new AlertDialog.Builder(MainActivity.this);
                adb.setMessage("Do you want to Delete this?").setPositiveButton(
                        "Yes",dicl).setNegativeButton("No",dicl).show();
                return true;

            }
        });
    }

    public void create(View v) {
        Intent i = new Intent(this, add.class);
        startActivity(i);
    }

    public void onResume() {
        super.onResume();
        notes = save.getnote(MainActivity.this);
        aa = new custom(this, notes);
        lv.setAdapter(aa);
    }
    @Override
    public void onBackPressed(){
        onStop();
    }

}
