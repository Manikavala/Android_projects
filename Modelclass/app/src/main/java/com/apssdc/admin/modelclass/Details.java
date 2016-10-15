package com.apssdc.admin.modelclass;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Details extends AppCompatActivity {
TextView t1,t2,t3;
    Model model;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        t1=(TextView)findViewById(R.id.textView);
        t2=(TextView)findViewById(R.id.textView2);
        t3=(TextView)findViewById(R.id.textView3);


        t1.setText(Form.getINSTANCE().getData().getName());
        t2.setText(Form.getINSTANCE().getData().getPhone());
        t3.setText(Form.getINSTANCE().getData().getEmail());

    }
}
