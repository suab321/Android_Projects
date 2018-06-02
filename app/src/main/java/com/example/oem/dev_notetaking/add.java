package com.example.oem.dev_notetaking;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;

public class add extends AppCompatActivity {
    EditText et1, et2;SimpleDateFormat sdf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        et1 = (EditText) findViewById(R.id.et1);
        et2 = (EditText) findViewById(R.id.et2);
        sdf=new SimpleDateFormat("HH:mm dd:MM:yyyy");
    }

    public void create(View v) {
        String sub = et1.getText().toString();
        String body = et2.getText().toString();
        if(sub.matches("")&&body.matches("")){
            et1.setError("Need a Subject");
            et2.setError("Describe your task");return;
        }
        else if(sub.matches("")){
            et1.setError("Need a Subject");return;}
        else if(body.matches("")){
            et2.setError("Describe your task");return;}

        if (save.saveitem(this, new item(sub, body,String.valueOf(sdf.format(new Date())),String.valueOf(System.currentTimeMillis())))) {
            Toast.makeText(this, "Saved", Toast.LENGTH_SHORT).show();
            Intent i = new Intent(this, MainActivity.class);
            startActivity(i);
        } else
            Toast.makeText(this, "Not saved", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onBackPressed(){
        DialogInterface.OnClickListener dicl = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case DialogInterface.BUTTON_POSITIVE:
                        String sub = et1.getText().toString();
                        String body = et2.getText().toString();
                        if(sub.matches("")&&body.matches("")){
                            et1.setError("Need a Subject");
                            et2.setError("Describe your task");return;}

                        else if(sub.matches("")){
                            et1.setError("Need a Subject");return;}
                        else if(body.matches("")){
                            et2.setError("Describe your task");return;}
                        if (save.saveitem(add.this, new item(sub, body,String.valueOf(sdf.format(new Date())),String.valueOf(System.currentTimeMillis())))) {
                            Toast.makeText(add.this, "Saved", Toast.LENGTH_SHORT).show();
                            Intent i = new Intent(add.this, MainActivity.class);
                            startActivity(i);
                        } else
                            Toast.makeText(add.this, "Not saved", Toast.LENGTH_SHORT).show();
                        break;
                    case DialogInterface.BUTTON_NEGATIVE:
                        Intent i = new Intent(add.this, MainActivity.class);
                        startActivity(i);
                        break;
                }

            }
        };
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Do you want to Save this ?").setPositiveButton("Yes", dicl).setNegativeButton("No", dicl).show();
    }
    public void note(){
       new MainActivity().notes=save.getnote(this);

    }

}

