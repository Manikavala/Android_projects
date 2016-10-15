package com.apssdc.admin.jsontest;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

import org.json.JSONArray;
import org.json.JSONObject;

import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
public class MainActivity extends AppCompatActivity {
    EditText et1,et2,et3,et4;
    String dirpath,internpath;
    Button read,write;
    static MainActivity Instance;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Instance=this;
        setContentView(R.layout.activity_main);
        et1=(EditText)findViewById(R.id.editText1);
        et2=(EditText)findViewById(R.id.editText2);
        et3=(EditText)findViewById(R.id.editText3);
        et4=(EditText)findViewById(R.id.editText4);
        read=(Button)findViewById(R.id.readBT);
        write=(Button)findViewById(R.id.writeBT);

        read.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"methode",Toast.LENGTH_LONG).show();

                    try{
                        dirpath= Environment.getExternalStorageDirectory().getAbsolutePath() + "/JsonData";
                        File folder = new File(dirpath);
                        if(!folder.exists())
                        {
                            folder.mkdirs();
                        }
                        FileReader reader=new FileReader(dirpath+"JsonTest.json");
                        int i=reader.read();
                        String msg="";
                        while(i!=-1){
                            msg=msg+(char)i;
                            i=reader.read();
                        }
                        JSONObject object=new JSONObject(msg);
                        JSONArray  array=object.getJSONArray("employees");
                        for(int j=0;j<array.length();j++){
                            JSONObject obj=array.getJSONObject(j);
                            Toast.makeText(getApplicationContext(), obj.getInt("id")+obj.getString("name")+
                                    obj.getString("desig")+obj.getString("dept"), Toast.LENGTH_LONG).show();
                        }
                    }catch (Exception e) {
                        // TODO: handle exception
                    }
                }
        });
           }
    public void writeMd(View v){
        Toast.makeText(getApplicationContext(),"methode",Toast.LENGTH_LONG).show();
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(getApplicationContext(),"permision",Toast.LENGTH_LONG).show();
            return;
        }
        try{
            dirpath= Environment.getExternalStorageDirectory().getAbsolutePath() + "/JsonData";
            File folder = new File(dirpath);
            if(!folder.exists())
            {
                folder.mkdirs();
                Toast.makeText(getApplicationContext(),"directory",Toast.LENGTH_LONG).show();
            }
            JSONObject object=new JSONObject();
            JSONArray array=new JSONArray();
            JSONObject emp_obj=new JSONObject();

            emp_obj.put("id", et1.getText().toString());
            emp_obj.put("name",et2.getText().toString());
            emp_obj.put("desig",et3.getText().toString());
            emp_obj.put("dept",et4.getText().toString());

            array.put(emp_obj);

            object.put("employees", array);
            String path=dirpath+"/JsonTest.json";
            File f=new File(path);
            FileWriter writer=new FileWriter(f);
            writer.write(object.toString());
            writer.flush();
            writer.close();
        }catch (Exception e) {
            // TODO: handle exception
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }
    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setTitle("Do You Want to EXit")
                .setMessage("confirm")
                .setNegativeButton("No ", null)
                .setPositiveButton("Yes ", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface arg0, int arg1) {
                        Intent intent = new Intent(Intent.ACTION_MAIN);
                        intent.addCategory(Intent.CATEGORY_HOME);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                        finish();
                    }
                }).create().show();
    }
    }