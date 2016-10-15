package com.apssdc.admin.jsonread;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

public class listview extends Activity {
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list);
        listView=(ListView)findViewById(R.id.listView);
        String value = getIntent().getExtras().getString("swarna");
        Toast.makeText(getApplicationContext(),value,Toast.LENGTH_SHORT).show();

    }
}
