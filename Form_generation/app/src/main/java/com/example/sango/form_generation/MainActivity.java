package com.example.sango.form_generation;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.StringReader;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {
    EditText fname,  fauthor, fid, field_name;

    Spinner type;
    TableLayout tl;
    FileWriter f;
    Button bt;

    int counter=0;
    String s = null;
    InputStream is;
    TextView status;
    LinearLayout layout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fname = (EditText) findViewById(R.id.et_formname);
        fid=(EditText)findViewById(R.id.et_label1);
        type=(Spinner)findViewById(R.id.et_fieldtype_1);
        field_name=(EditText)findViewById(R.id.et_field_name);
        tl=(TableLayout)findViewById(R.id.tablelayout);
        fauthor = (EditText) findViewById(R.id.et_formauthor);
        status=(TextView)findViewById(R.id.status);
        bt = (Button) findViewById(R.id.button);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String formname = fname.getText().toString();
                final String formauthor = fauthor.getText().toString();
                final String fieldtype = type.getSelectedItem().toString();
                final String fieldname = field_name.getText().toString();
                final String fieldid = fid.getText().toString();
                            try{
                                JSONObject jobject=new JSONObject();
                                JSONArray jarray=new JSONArray();
                                JSONObject sobject=new JSONObject();
                                jobject.put("name",formname);
                                jobject.put("author",formauthor);
                                sobject.put("type", fieldtype);
                                sobject.put("label",fieldid);
                                sobject.put("fieldname",fieldname);
                                jarray.put(sobject);
                                jobject.put("columns", jarray);
                                JSONArray array =read(v);
                                if (array !=null) {
                                    array.put(sobject);
                                    jobject.put("columns", array);
                                    f = new FileWriter("/storage/sdcard0/form_obj.json");
                                    f.write(jobject.toString());
                                    f.flush();
                                    f.close();

                                } else {
                                    jarray.put(sobject);
                                    jobject.put("columns", jarray);
                                     f = new FileWriter("/storage/sdcard0/form_obj.json");
                                    f.write(jobject.toString());
                                    f.flush();
                                    f.close();
                                }
                                fname.setText("");
                                fauthor.setText("");
                                fid.setText("");
                                field_name.setText("");
                            }catch (Exception e) {
                                e.printStackTrace();
                            }
            }
            private JSONArray read(View v) {
                JSONArray array=null;
                try{
                    FileReader reader= new FileReader("/storage/sdcard0/form_obj.json");
                    int i=reader.read();
                    String msg="";
                    while(i!=-1) {
                        msg = msg + (char) i;
                        i = reader.read();
                    }

                    Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
                    JSONObject jObject = new JSONObject(msg);
                    array = jObject.getJSONArray("columns");
                    Toast.makeText(getApplicationContext(), "" + array.length(), Toast.LENGTH_SHORT).show();
                    for (int j = 0; j < array.length(); j++) {
                        JSONObject object = array.getJSONObject(j);

                        String name = object.getString("name");
                        String author = object.getString("author");
                        String type= object.getString("type");
                        String label = object.getString("label");
                       String fieldname= object.getString("fieldname");
                        Toast.makeText(getApplicationContext(), name + "\n"+ author + "\n" + type + "\n" + label +"\n" + fieldname,
                                Toast.LENGTH_LONG).show();
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
                return array;
            }


        });
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        assert fab != null;
        fab.setOnClickListener(this);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    protected boolean isValidAll(String formname, String formtitle, String formauthor, String fieldtype, String fieldname, String fieldvalue, String fieldid) {

    boolean valid= true;
        if(formname.isEmpty()){
            status.setError("Enter form name");
            valid=false;
        }
        if(formtitle.isEmpty()){
            status.setError("Enter form title");
            valid=false;
        }
        if(formauthor.isEmpty()){
            status.setError("Enter form author name");
            valid=false;
        }
        if(fieldname.isEmpty()){
            status.setError("Enter field name");
            valid=false;
        }
        if(fieldid.isEmpty()){
            status.setError("Enter field ID");
            valid=false;
        }
        if(fieldtype.isEmpty()){
            status.setError("Enter field type");
            valid=false;
        }
        if(fieldvalue.isEmpty()){
            status.setError("Enter field value");
            valid=false;
        }
        return  valid;
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_createform) {
            // Handle the camera action


        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_available_forms) {

        } else if (id == R.id.nav_refresh) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onClick(View v) {
        TableRow tr=new TableRow(this);
        counter++;
       fid = new EditText(this);
        fid.setLayoutParams(new TableRow.LayoutParams(0 , TableRow.LayoutParams.WRAP_CONTENT, (float) 0.75));
        fid.setHint("labelInput"+counter);
type.getSelectedItem().toString();

        ArrayList<String> spinnerarray ;
        spinnerarray = new ArrayList<String>();
        ArrayAdapter<String> stringArrayAdapter= new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,spinnerarray);
        spinnerarray.add("Text");
        spinnerarray.add("Password");
        spinnerarray.add("Button");
        type=new Spinner(this);
        type.setAdapter(stringArrayAdapter);
        type.setLayoutParams(new TableRow.LayoutParams(0 , TableRow.LayoutParams.WRAP_CONTENT, (float) 0.5));
        field_name = new EditText(this);
        field_name.setHint("NameInput"+counter);
        field_name.setLayoutParams(new TableRow.LayoutParams(0 , TableRow.LayoutParams.WRAP_CONTENT, (float) 0.7));
        ImageView iv=new ImageView(this);

        iv.setImageResource(R.mipmap.del);
        tr.addView(fid);
        tr.addView(type);
        tr.addView(field_name);
        tr.addView(iv);
        iv.setLayoutParams(new TableRow.LayoutParams(0 , TableRow.LayoutParams.WRAP_CONTENT, (float) 0.25));
        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View row = (View) v.getParent();
                ViewGroup container = ((ViewGroup)row.getParent());
                // delete the row and invalidate your view so it gets redrawn
                container.removeView(row);
                container.invalidate();

            }
        });
        tl.addView(tr,new TableLayout.LayoutParams(TableLayout.LayoutParams.WRAP_CONTENT, TableLayout.LayoutParams.WRAP_CONTENT));
        }
}
