package com.apssdc.admin.modelclass;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
EditText name,email,phone;
    RadioButton male,female;
    Spinner branch;
    RadioGroup rg;
    Button submit;
    static MainActivity INSTANCE;
    String gender,branchs;
    Model mdl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        INSTANCE=this;

        name=(EditText)findViewById(R.id.ETname);
        phone=(EditText)findViewById(R.id.ETphone);
        email=(EditText)findViewById(R.id.ETemail);

        submit=(Button)findViewById(R.id.submit);
        branch=(Spinner)findViewById(R.id.spinner);
        rg=(RadioGroup)findViewById(R.id.rg);
        male=(RadioButton)findViewById(R.id.male);
        female=(RadioButton)findViewById(R.id.female);


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                 mdl=new Model();
                if (rg.getCheckedRadioButtonId() != -1) {
                    int id = rg.getCheckedRadioButtonId();
                    View radioButton = rg.findViewById(id);
                    int radioId = rg.indexOfChild(radioButton);
                    RadioButton btn = (RadioButton) rg.getChildAt(radioId);
                    gender = (String) btn.getText();
                }
                mdl.setName(name.getText().toString());
                mdl.setEmail(email.getText().toString());
                mdl.setPhone(phone.getText().toString());
              //  mdl.setPhone(Integer.parseInt(phone.getText().toString()));
                mdl.setBranch(branch.getSelectedItem().toString());
                mdl.setGender(gender);
                Toast.makeText(getApplicationContext(),gender,Toast.LENGTH_LONG).show();

            Intent i=new Intent(getApplicationContext(),Form.class);
                startActivity(i);

            }


            });


        }
    public static MainActivity getINSTANCE()
    {
        return INSTANCE;
    }

    public Model getData()
    {
        return mdl;
    }
    }

