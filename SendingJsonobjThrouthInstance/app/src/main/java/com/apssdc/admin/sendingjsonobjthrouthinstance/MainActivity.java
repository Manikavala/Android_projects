package com.apssdc.admin.sendingjsonobjthrouthinstance;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    EditText et1,et2,et3,et4;
    static MainActivity INSTANCE;
    static MainActivity mani1;
    Button b;
    JSONObject mani;
    JSONObject jsonobj;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mani1=this;
        INSTANCE = this;
        et1 = (EditText) findViewById(R.id.editText);
        et2 = (EditText) findViewById(R.id.editText2);
        et3 = (EditText) findViewById(R.id.editText3);
        et4 = (EditText) findViewById(R.id.editText4);
        b=(Button)findViewById(R.id.insert);
        b.setOnClickListener(new View.OnClickListener() {

            public void onBackPressed() {
                AlertDialog.Builder builder = new AlertDialog.Builder(getApplicationContext());
                builder.setCancelable(false);

                builder.setMessage("Do you want to Exit?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //if user pressed "yes", then he is allowed to exit from application
                        finish();
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //if user select "No", just cancel this dialog and continue with app
                        dialog.cancel();
                    }
                });
                AlertDialog alert = builder.create();
                alert.show();
            }


            public void onClick(View view) {
                        jsonobj = new JSONObject();
                try {
                    jsonobj.put("name", et1.getText().toString());
                    jsonobj.put("email", et2.getText().toString());
                    jsonobj.put("phone", et3.getText().toString());
                    jsonobj.put("add", et4.getText().toString());

                } catch (JSONException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                Intent i=new Intent(getApplicationContext(),Details.class);
                startActivity(i);
            }
        });
    }
        public static MainActivity getINSTANCE()
        {
            return INSTANCE;
        }
        public JSONObject getData() {
            return jsonobj;
        }
    }

