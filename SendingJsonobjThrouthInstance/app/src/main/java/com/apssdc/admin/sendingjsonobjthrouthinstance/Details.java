package com.apssdc.admin.sendingjsonobjthrouthinstance;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;

public class Details extends AppCompatActivity {
TextView tv1,tv2,tv3,tv4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        tv1=(TextView)findViewById(R.id.textView);
        tv2=(TextView)findViewById(R.id.textView2);
        tv3=(TextView)findViewById(R.id.textView3);
        tv4=(TextView)findViewById(R.id.textView4);
    //    Toast.makeText(getApplicationContext(),MainActivity.getINSTANCE().getData().toString(),Toast.LENGTH_LONG).show();

        try{
            tv1.setText(MainActivity.getINSTANCE().getData().getString("name"));
            tv2.setText(MainActivity.getINSTANCE().getData().getString("email"));
            tv3.setText(MainActivity.getINSTANCE().getData().getString("phone"));
            tv4.setText(MainActivity.getINSTANCE().getData().getString("add"));

        }catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


    }
}
