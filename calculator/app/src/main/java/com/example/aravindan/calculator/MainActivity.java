package com.example.aravindan.calculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Button b1,b2,b3,b4;
    EditText e1,e2;
    TextView t1,t2,t3,t4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        b1 = (Button)findViewById(R.id.b1);
        b2 = (Button)findViewById(R.id.b2);
        b3 = (Button)findViewById(R.id.b3);
        b4 = (Button)findViewById(R.id.b4);
        e1=  (EditText) findViewById(R.id.e1);
        e2 = (EditText) findViewById(R.id.e2);
        t1 = (TextView) findViewById(R.id.t1);
        t2 = (TextView) findViewById(R.id.t2);
        t3 = (TextView) findViewById(R.id.t3);
        t4 = (TextView) findViewById(R.id.t4);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Integer N1 = Integer.parseInt(e1.getText().toString());
                Integer N2 = Integer.parseInt(e2.getText().toString());
                Integer Res = N1+N2;
                t4.setText(Res.toString());
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Integer N1 = Integer.parseInt(e1.getText().toString());
                Integer N2 = Integer.parseInt(e2.getText().toString());
                Integer Res = N1-N2;
                t4.setText(Res.toString());
            }
        });
        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Integer N1 = Integer.parseInt(e1.getText().toString());
                Integer N2 = Integer.parseInt(e2.getText().toString());
                Integer Res = N1*N2;
                t4.setText(Res.toString());
            }
        });
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Integer N1 = Integer.parseInt(e1.getText().toString());
                Integer N2 = Integer.parseInt(e2.getText().toString());
                Integer Res = N1/N2;
                t4.setText(Res.toString());
            }
        });

    }
}
