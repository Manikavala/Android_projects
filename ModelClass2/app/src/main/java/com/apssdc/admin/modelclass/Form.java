package com.apssdc.admin.modelclass;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Form extends AppCompatActivity {
TextView name,phone,email,branch,gender;
    Model model;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);
    name=(TextView)findViewById(R.id.textView);
    phone=(TextView)findViewById(R.id.textView2);
    email=(TextView)findViewById(R.id.textView3);
    branch=(TextView)findViewById(R.id.textView4);
    gender=(TextView)findViewById(R.id.textView5);
        model=new Model();
        name.setText(MainActivity.getINSTANCE().getData().getName());
        phone.setText(MainActivity.getINSTANCE().getData().getPhone());
        email.setText(MainActivity.getINSTANCE().getData().getEmail());
        branch.setText(MainActivity.getINSTANCE().getData().getBranch());
        gender.setText(MainActivity.getINSTANCE().getData().getGender());




    }
}
