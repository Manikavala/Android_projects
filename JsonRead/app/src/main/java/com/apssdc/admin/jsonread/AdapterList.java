package com.apssdc.admin.jsonread;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * Created by admin on 8/27/2016.
 */
public class AdapterList  extends BaseAdapter{

    List<String> values;
    JsonRead read;

    public AdapterList(JsonRead jsonRead, List<String> list) {

        this.values=list;
        this.read=jsonRead;
    }

    @Override
    public int getCount() {
        return values.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        return null;
    }
}
