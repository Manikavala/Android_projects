package com.apssdc.admin.formfilling2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText ename1,eno1;
    Spinner sp;
    RadioButton male,female;
    Button insert,read;
    CheckBox telugu,eng,hindi;
    SQLiteDatabase database;
    String gender,gender1,branchtext,eename,enumber,language,telugus,engs,hindis;
    RadioGroup rg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ename1 = (EditText) findViewById(R.id.nameET);
        eno1 = (EditText) findViewById(R.id.noET);
        insert = (Button) findViewById(R.id.button);
        read = (Button) findViewById(R.id.readB);
        sp = (Spinner) findViewById(R.id.Branch);
        male = (RadioButton) findViewById(R.id.male);
        female = (RadioButton) findViewById(R.id.female);
        telugu = (CheckBox) findViewById(R.id.a);
        eng = (CheckBox) findViewById(R.id.b);
        hindi = (CheckBox) findViewById(R.id.c);
        rg = (RadioGroup) findViewById(R.id.rg);


        database = openOrCreateDatabase("studentdb", Context.MODE_PRIVATE, null);
        database.execSQL("create table if not exists student(name varchar(40),number varchar(40),branch varchar(40),gender varchar(40),language varchar(40))");




        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(male.isChecked()){
                    gender1=male.getText().toString();
                }if(female.isChecked()){
                    gender1=female.getText().toString();
                }


                if(telugu.isChecked())
                {  telugus=telugu.getText().toString();}
                if(eng.isChecked())
                {  engs=eng.getText().toString();}
                if(hindi.isChecked())
                {  hindis=hindi.getText().toString();}

                 language=telugus+"\t"+engs+"\t"+hindis;
                if (rg.getCheckedRadioButtonId() != -1) {
                    int id = rg.getCheckedRadioButtonId();
                    View radioButton = rg.findViewById(id);
                    int radioId = rg.indexOfChild(radioButton);
                    RadioButton btn = (RadioButton) rg.getChildAt(radioId);
                    gender = (String) btn.getText();
                }
                //  Spinner spinner = (Spinner)findViewById(R.id.Branch);
                branchtext = sp.getSelectedItem().toString();
                enumber   = eno1.getText().toString();
                eename  = ename1.getText().toString();

                ContentValues cv = new ContentValues();
                cv.put("name", eename);
                cv.put("number", enumber);
                cv.put("gender", gender);
                cv.put("branch", branchtext);
                cv.put("language", language);

                database.insert("student", null, cv);
                // Toast.makeText(getApplicationContext(),, Toast.LENGTH_LONG).show();
                Toast.makeText(getApplicationContext(), eename + "\n" + enumber+"\n" +branchtext + "\n" + gender+ "\n" + gender1+ "\n" + language , Toast.LENGTH_LONG).show();
            }
        });

        read.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Cursor c=database.query("student", new String[]{"number","gender","branch","language"}, "name=?", new String[]{ename1.getText().toString()}, null, null, null);
                Toast.makeText(getApplicationContext(),"hello"+"  ->"+eename, Toast.LENGTH_LONG).show();

                while(c.moveToNext()){


                    Toast.makeText(getApplicationContext(), c.getString(0)+"\n"+c.getString(1)+"\n"+c.getString(2)+"\n"+c.getString(3)/*+"\n"+c.getString(4)*/, Toast.LENGTH_LONG).show();

                }



            }
        });


    }


}
