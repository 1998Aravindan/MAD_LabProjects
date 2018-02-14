package com.example.aravindan.db;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class Register extends Activity {

    EditText E1,E2,E3,E4;
    Button B1;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        E1=(EditText)findViewById(R.id.E1);
        E2=(EditText)findViewById(R.id.E2);
        E3=(EditText)findViewById(R.id.E3);
        E4=(EditText)findViewById(R.id.E4);

        B1=(Button)findViewById(R.id.B1);

        db=openOrCreateDatabase("SAMPLEDB", Context.MODE_PRIVATE,null);

        B1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = E1.getText().toString();
                String age = E2.getText().toString();
                String usrname = E3.getText().toString();
                String pswd = E4.getText().toString();

                Cursor c = db.rawQuery("SELECT COUNT(*) FROM LOGIN WHERE USRNAME='" + usrname + "'", null);
                if(c.moveToFirst())
                {
                    if(c.getInt(0)==0)
                    {
                        db.execSQL("INSERT INTO LOGIN VALUES('" + name + "','" + age +
                                "','" + usrname + "','" + pswd + "');");
                        showMessage("Success","Record Added");
                    }
                    else
                        showMessage("Error","UserName not available");
                }
            }
        });
    }

    public void showMessage(String title,String message)
    {
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }

}

