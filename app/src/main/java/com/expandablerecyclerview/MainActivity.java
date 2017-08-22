package com.expandablerecyclerview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.expandablerecyclerview.adapter.OnSubItemClick;
import com.expandablerecyclerview.adapter.ParentRecyclerViewAdapter;
import com.expandablerecyclerview.data.ListData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements OnSubItemClick {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rv_main);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new ParentRecyclerViewAdapter(this, getListData()));
    }

    private ArrayList<ListData> getListData() {
        ArrayList<ListData> arrayList = new ArrayList<>();

        StringBuilder builder = new StringBuilder();
        InputStream inputStream = getResources().openRawResource(R.raw.list);
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        String line;
        try {
            while ((line = reader.readLine()) != null) {
                builder.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        final String rawJson = builder.toString();
        try {
            JSONArray jsonArray = new JSONObject(rawJson).getJSONArray("data");
            ListData listData;

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject listJSONObject = jsonArray.getJSONObject(i);

                listData = new ListData();
                listData.setName(listJSONObject.getString("name"));

                JSONArray subJSONArray = listJSONObject.getJSONArray("subcategory");

                for (int j = 0; j < subJSONArray.length(); j++) {
                    listData.addSubCategories(subJSONArray.getString(j));
                }

                arrayList.add(listData);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return arrayList;
    }

    @Override
    public void onSubItemClick(String item) {
        Toast.makeText(this, item + " clicked", Toast.LENGTH_SHORT).show();
    }
}
