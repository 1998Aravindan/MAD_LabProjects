package com.example.aravindan.dataconnect;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import java.sql.SQLData;
import android.database.sqlite.SQLiteDatabase;

public class MainActivity extends AppCompatActivity {
    EditText et1, et2, et3;
    Button b1, b2, b3, b4, b5, b6;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et1 = (EditText)findViewById(R.id.et1);
        et2 = (EditText)findViewById(R.id.et2);
        et3 = (EditText)findViewById(R.id.et3);


        b1 = (Button)findViewById(R.id.b1);
        b2 = (Button)findViewById(R.id.b2);
        b3 = (Button)findViewById(R.id.b3);
        b4 = (Button)findViewById(R.id.b4);
        b5 = (Button)findViewById(R.id.b5);
        b6 = (Button)findViewById(R.id.b6);

        db = openOrCreateDatabase("StudentDatabase", Context.MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS studentdb(name varchar,rollno varchar,marks varchar);");

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(et1.getText().toString().trim().length()==0||
                        et2.getText().toString().trim().length()==0||
                        et3.getText().toString().trim().length()==0)
                {
                    showMessage("Error", "Please enter all values");
                    return;
                }
                db.execSQL("INSERT INTO studentdb VALUES('"+et1.getText()+"','"+et2.getText()+ "','"+et3.getText()+"');");
                showMessage("Success", "Record added");
                clearText();
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(et1.getText().toString().trim().length()==0)
                {
                    showMessage("Error", "Please enter your Name");
                    return;
                }
                Cursor c=db.rawQuery("SELECT * FROM studentdb WHERE name='"+et1.getText()+"'", null);
                if(c.moveToFirst())
                {
                    db.execSQL("DELETE FROM studentdb WHERE name='"+et1.getText()+"'");
                    showMessage("Success", "Record Deleted");
                }
                else
                {
                    showMessage("Error", "Invalid Name");
                }
                clearText();
            }
        });
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(et1.getText().toString().trim().length()==0 || et2.getText().toString().trim().length()==0)
                {
                    showMessage("Error", "Please enter Name and Roll No and enter your new marks !");
                    return;
                }
                Cursor c=db.rawQuery("SELECT * FROM studentdb WHERE name='"+et1.getText()+"'", null);
                if(c.moveToFirst())
                {
                    db.execSQL("UPDATE studentdb SET marks='"+et3.getText()+"' WHERE name='"+et1.getText()+"'");
                    showMessage("Success", "Record Modified");
                }
                else
                {
                    showMessage("Error", "Invalid Name");
                }
                clearText();
            }
        });
        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor c=db.rawQuery("SELECT * FROM studentdb WHERE name='"+et1.getText()+"'", null);
                StringBuffer buffer=new StringBuffer();
                if(et1.getText().toString().trim().length()==0)
                {
                    showMessage("Error", "Please enter Name");
                    return;
                }
                if(c.getCount()==0)
                {
                    showMessage("Error", "No records found");
                    return;
                }

                while(c.moveToNext())
                {
                    buffer.append("Name: "+c.getString(0)+"\n");
                    buffer.append("Roll No: "+c.getString(1)+"\n");
                    buffer.append("Marks: "+c.getString(2)+"\n\n");
                }
                showMessage("Student Details", buffer.toString());

            }
        });
        b5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor c=db.rawQuery("SELECT * FROM studentdb", null);
                if(c.getCount()==0)
                {
                    showMessage("Error", "No records found");
                    return;
                }
                StringBuffer buffer=new StringBuffer();
                while(c.moveToNext())
                {
                    buffer.append("Name: "+c.getString(0)+"\n");
                    buffer.append("Roll No: "+c.getString(1)+"\n");
                    buffer.append("Marks: "+c.getString(2)+"\n\n");
                }
                showMessage("Student Details", buffer.toString());
            }
        });
        b6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showMessage("Student Management Application", "Developed by Aravindan M");
            }
        });


    }

    public void showMessage(String title,String message)
    {
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }
    public void clearText()
    {
        et1.setText("");
        et2.setText("");
        et3.setText("");
        et1.requestFocus();
    }




}