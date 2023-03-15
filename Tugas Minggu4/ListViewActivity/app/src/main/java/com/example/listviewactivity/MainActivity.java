package com.example.listviewactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.support.v4.app.*;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    ListView listView;
    ArrayAdapter<CharSequence> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView)findViewById(R.id.listView);
        adapter = ArrayAdapter.createFromResource(this, R.array.countris_arry, android.R.layout.simple_list_item_1);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // inflate the menu;
//        getMenuInflater().inflate(R.id.list, menu);
        return true;
    }

    public void onItemClick(AdapterView<?> parent, View view, int position, long id){
        Toast.makeText(this, adapter.getItem(position), Toast.LENGTH_SHORT).show();
    }
}