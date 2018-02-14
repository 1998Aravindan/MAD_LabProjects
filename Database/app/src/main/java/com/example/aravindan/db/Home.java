package com.example.aravindan.db;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class Home extends Activity {

    TextView T1,T2,T3;
    Button B1;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        T1=(TextView)findViewById(R.id.T1);
        T2=(TextView)findViewById(R.id.T2);
        T3=(TextView)findViewById(R.id.T3);

        B1=(Button)findViewById(R.id.B1);

        db=openOrCreateDatabase("SAMPLEDB", Context.MODE_PRIVATE,null);

        Cursor c = db.rawQuery("SELECT * FROM LOGIN WHERE USRNAME='" + getIntent().getStringExtra("USRNAME") + "'", null);

        if(c.moveToFirst())
        {
            T1.setText("Name    :" +c.getString(0));
            T2.setText("Age     :"+c.getString(1));
            T3.setText("UserName:"+c.getString(2));
        }

        B1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor c = db.rawQuery("SELECT * FROM LOGIN", null);
                StringBuffer buffer=new StringBuffer();
                while(c.moveToNext())
                {
                    buffer.append("Name: "+c.getString(0)+"\n");
                    buffer.append("Age: "+c.getString(1)+"\n");
                    buffer.append("UserName: "+c.getString(2)+"\n\n");
                }
                showMessage("Student Details", buffer.toString());
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

