package com.example.oem.dev_notetaking;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

public class edit extends AppCompatActivity {
    EditText et1, et2;
    Button b;
    item e;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        b = (Button) findViewById(R.id.b);
        Bundle os = getIntent().getExtras();
        e = (item) os.get("item");
        et1 = (EditText) findViewById(R.id.et1);
        et2 = (EditText) findViewById(R.id.et2);
        et1.setText(e.getSub());
        et2.setText(e.getBody());
        et1.setEnabled(false);
        et2.setEnabled(false);
    }

    public void ed(View view) {
        et1.setEnabled(true);
        et2.setEnabled(true);
        b.setVisibility(View.VISIBLE);
    }

    public void save(View view) {
        if (et1.getText().toString().matches(e.getSub()) && et2.getText().toString().matches(e.getBody())) {
            Intent i = new Intent(this, MainActivity.class);
            startActivity(i);
        } else {
            SimpleDateFormat sdf = new SimpleDateFormat();
            String date = sdf.format(new Date());
            save.saveitem(this, new item(et1.getText().toString(), et2.getText().toString(), date, String.valueOf(System.currentTimeMillis())));
            save.delete(this, e);
            Toast.makeText(this, "Edited", Toast.LENGTH_SHORT).show();
            Intent i=new Intent(getApplicationContext(),MainActivity.class);
            startActivity(i);
        }
    }

    @Override
    public void onBackPressed() {
        DialogInterface.OnClickListener dicl=new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch(which){
                    case DialogInterface.BUTTON_POSITIVE:
                        Intent i=new Intent(getApplicationContext(),MainActivity.class);
                        startActivity(i);
                        break;
                    case DialogInterface.BUTTON_NEGATIVE:
                        break;
                }
            }
        };
        AlertDialog.Builder adb=new AlertDialog.Builder(edit.this);
        adb.setMessage("You want to go Back ?").setNegativeButton("No",dicl).setPositiveButton("Yes",dicl).show();
    }
}


