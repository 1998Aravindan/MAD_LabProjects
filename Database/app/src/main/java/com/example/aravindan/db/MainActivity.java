package com.example.aravindan.db;


        import android.app.Activity;
        import android.app.AlertDialog;
        import android.content.Context;
        import android.content.Intent;

        import android.database.Cursor;
        import android.database.sqlite.SQLiteDatabase;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.TextView;


public class MainActivity extends Activity {

    EditText E1,E2;
    Button B1;
    TextView L1;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        E1=(EditText)findViewById(R.id.E1);
        E2=(EditText)findViewById(R.id.E2);

        B1=(Button)findViewById(R.id.B1);

        L1=(TextView)findViewById(R.id.L1);

        db=openOrCreateDatabase("SAMPLEDB", Context.MODE_PRIVATE,null);
        db.execSQL("CREATE TABLE IF NOT EXISTS LOGIN(NAME VARCHAR,AGE VARCHAR,USRNAME VARCHAR,PSWD VARCHAR);");

        B1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor c = db.rawQuery("SELECT * FROM LOGIN WHERE USRNAME='" + E1.getText().toString() + "' AND PSWD='" + E2.getText().toString() + "'", null);
                if (c.moveToFirst())
                {
                    Intent intent=new Intent(MainActivity.this,Home.class);
                    intent.putExtra("USRNAME",E1.getText().toString());
                    startActivity(intent);
                }
                else
                    showMessage("Error", "Invalid Data!...");
            }
        });

        L1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,Register.class));
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
