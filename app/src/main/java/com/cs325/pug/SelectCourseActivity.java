package com.cs325.pug;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class SelectCourseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_course);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String value = extras.getString("subject");
        }

        int firstDigit = value.hashCode() % 6 + 1;
        int secondDigit = 0;
        int thirdDigits[] = new int[firstDigit];
        for (int i = 0; i < firstDigit; i++) {
            thirdDigits[i] = value.hashCode() % 3 + 1;
        }

        String[] subjectArray = {
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
                R.layout.course_list_item,
                R.id.textView,
                courseArray
        );

        ListView listView=(ListView)findViewById(R.id.listView);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    public void onItemClick(AdapterView<?> list, View v, int pos, long id) {
                        TextView view = (TextView)findViewById(R.id.textView);
                        String subject = view.getText().toString();
                        Intent i = new Intent(getApplicationContext(), SelectGroupActivity.class);
                        i.putExtra("subject", subject);
                        startActivity(i);
                    }
                }
        );
    }
}
