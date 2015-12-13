package com.cs325.pug;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class SelectGroupActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_group);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Pick Up Group");
        setSupportActionBar(toolbar);

        Bundle extras = getIntent().getExtras();
        final String subject = extras.getString("subject");
        final String course = extras.getString("course");

        TextView textView = (TextView)findViewById(R.id.selection1);
        textView.setText(subject);
        textView = (TextView)findViewById(R.id.selection2);
        textView.setText(course);

        String[] groupArray = {
                "HW",
                "Homework for reals!",
                "Study",
                "Study Time 7.0",
                "Despair"
        };
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this,
                R.layout.list_item,
                R.id.item,
                groupArray
        );

        final ListView listView=(ListView)findViewById(R.id.listView);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    public void onItemClick(AdapterView<?> list, View v, int pos, long id) {
                        String selection = (String) listView.getItemAtPosition(pos);
                        Intent i = new Intent(getApplicationContext(), JoinGroupActivity.class);
                        i.putExtra("subject", subject);
                        i.putExtra("course", course);
                        i.putExtra("title", selection);
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
                        Intent i = new Intent(getApplicationContext(), SelectCourseActivity.class);
                        i.putExtra("subject", subject);
                        i.putExtra("course", course);
                        startActivity(i);
                    }
                }
        );

        Button button = (Button)findViewById(R.id.button);
        button.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(final View v) {
                        v.setBackgroundColor(Color.rgb(64, 64, 64));
                        Intent i = new Intent(getApplicationContext(), CreateGroupActivity.class);
                        i.putExtra("subject", subject);
                        i.putExtra("course", course);
                        startActivity(i);
                    }
                }
        );
    }
}
