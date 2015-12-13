package com.cs325.pug;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class SelectCourseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_course);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Pick Up Group");
        setSupportActionBar(toolbar);

        Bundle extras = getIntent().getExtras();
        final String subject = extras.getString("subject");

        TextView textView = (TextView)findViewById(R.id.selection1);
        textView.setText(subject);

        String[] courseArray = {
                "101",
                "102",
                "103",
                "201",
                "202",
                "203",
                "301",
                "302",
                "303",
                "401",
                "402",
                "403",
                "501",
                "502",
                "503",
                "601",
                "602",
                "603"
        };

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this,
                R.layout.list_item,
                R.id.item,
                courseArray
        );

        final ListView listView=(ListView)findViewById(R.id.listView);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    public void onItemClick(AdapterView<?> list, View v, int pos, long id) {
                        String selection = (String) listView.getItemAtPosition(pos);
                        Intent i = new Intent(getApplicationContext(), SelectGroupActivity.class);
                        i.putExtra("subject", subject);
                        i.putExtra("course", selection);
                        startActivity(i);
                    }
                }
        );

        Button back = (Button)findViewById(R.id.back);
        back.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(final View v) {
                        v.setBackgroundColor(Color.rgb(64, 64, 64));
                        Intent i = new Intent(getApplicationContext(), SelectSubjectActivity.class);
                        i.putExtra("subject", subject);
                        startActivity(i);
                    }
                }
        );
    }
}
