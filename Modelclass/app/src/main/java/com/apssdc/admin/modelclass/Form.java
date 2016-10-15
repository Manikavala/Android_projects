package com.apssdc.admin.modelclass;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Form extends AppCompatActivity {
    EditText Etname,Etemail,Etphone;
    Button next;
    Model model;
    static Form INSTANCE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);
        INSTANCE=this;
        Etname=(EditText)findViewById(R.id.editText);
        Etemail=(EditText)findViewById(R.id.editText2);
        Etphone=(EditText)findViewById(R.id.editText3);
        next=(Button)findViewById(R.id.next);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                model=new Model();
                model.setName(Etname.getText().toString());
                model.setEmail(Etemail.getText().toString());
                model.setPhone(Etphone.getText().toString());
                Intent i=new Intent(getApplicationContext(),Details.class);
                startActivity(i);
            }
        });

    }

    public static Form getINSTANCE()
    {
        return INSTANCE;
    }

    public Model getData()
    {
        return model;
    }

}
