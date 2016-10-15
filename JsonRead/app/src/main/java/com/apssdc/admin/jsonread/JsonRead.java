package com.apssdc.admin.jsonread;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

public class JsonRead extends AppCompatActivity {
    EditText name,eid,dept,email;
    Button read;
    List<String> list=new ArrayList<String>();
    AdapterList adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.json_read);
        name = (EditText) findViewById(R.id.nameET);
        eid = (EditText) findViewById(R.id.eidET);
        dept = (EditText) findViewById(R.id.deptET);
        email = (EditText) findViewById(R.id.mailET);
        read=(Button) findViewById(R.id.readjson);
    }
    public void read(View v){
        try{
          //  FileReader reader=new FileReader("/storage/emulated/0/Download/form2.json");
            FileReader reader=new FileReader("/storage/sdcard0/form2.json");
            Toast.makeText(getApplicationContext(),"read",Toast.LENGTH_LONG).show();

            int i=reader.read();
            String msg="";
            while(i!=-1){
                msg=msg+(char)i;
                i=reader.read();
            }
            JSONObject object=new JSONObject(msg);
         //   JSONObject obj1=object.getJSONObject()
            JSONArray  array=object.getJSONArray("student");

            for(int j=0;j<array.length();j++){

                JSONObject obj=array.getJSONObject(j);
                Toast.makeText(getApplicationContext(), obj.getInt("id")+obj.getString("name")+	obj.getString("email")+obj.getString("dept"), Toast.LENGTH_LONG).show();

                 String name=obj.getString("name");
                 String id=obj.getString("id");
                 String email=obj.getString("email");
                 String dept=obj.getString("dept");

                list.add(name);
                list.add(id);
                list.add(email);
                list.add(dept);
            }
       //adapter=new AdapterList(JsonRead.this,list);
            Intent intent;
            intent=new Intent(getApplicationContext(),listview.class);
            intent.putExtra("swarna",list.toString());
            startActivity(intent);
        }catch (Exception e) {
        }
    }
    public void write(View v){

        try{
            JSONObject object=new JSONObject();
            JSONArray array=new JSONArray();
            JSONObject emp_obj=new JSONObject();

            emp_obj.put("id", Integer.parseInt(eid.getText().toString()));
            emp_obj.put("name",name.getText().toString());
            emp_obj.put("email",email.getText().toString());
            emp_obj.put("dept",dept.getText().toString());
            Toast.makeText(getApplicationContext(),"write",Toast.LENGTH_LONG).show();
            array.put(emp_obj);
            object.put("student", array);
            String path="/storage/sdcard0/form2.json";
           // String path="/Internal storage/Download/form2.json";
            File f=new File(path);
            FileWriter writer=new FileWriter(f);
            writer.write(object.toString());
            writer.flush();
            writer.close();
        }catch (Exception e) {
            // TODO: handle exception
        }
    }
}
