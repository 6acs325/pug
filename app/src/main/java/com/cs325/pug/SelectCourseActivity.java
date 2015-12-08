package com.cs325.pug;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
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
        final String subject = extras.getString("subject");

        int firstDigit = subject.hashCode() % 6 + 1;
        int secondDigit = 0;
        int thirdDigits[] = new int[firstDigit];
        for (int i = 0; i < firstDigit; i++) {
            thirdDigits[i] = subject.hashCode() % 3 + 1;
        }

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
                R.id.textView,
                courseArray
        );

        ListView courseList=(ListView)findViewById(R.id.courseList);
        courseList.setAdapter(adapter);
        courseList.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    public void onItemClick(AdapterView<?> list, View v, int pos, long id) {
                        TextView view = (TextView)findViewById(R.id.textView);
                        String course = view.getText().toString();
                        Intent i = new Intent(getApplicationContext(), SelectGroupActivity.class);
                        i.putExtra("subject", subject);
                        i.putExtra("course", course);
                        startActivity(i);
                    }
                }
        );
    }
}
