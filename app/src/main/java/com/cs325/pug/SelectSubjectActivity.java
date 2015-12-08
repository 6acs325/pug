package com.cs325.pug;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class SelectSubjectActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_subject);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        String[] subjectArray = {
                "Accounting",
                "Biology",
                "Computer Science",
                "Dance",
                "Engineering",
                "Food Science",
                "Geography",
                "History",
                "Italian",
                "Japanese",
                "Korean",
                "Linguistics",
                "Management",
                "Nursing",
                "Operations & Info Management",
                "Philosophy",
                "Regional Planning",
                "Sport Management",
                "Theater",
                "UMass Practicum",
                "Women, Gender, Sexuality",
                "Yiddish"
        };
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this,
                R.layout.subject_list_item,
                R.id.textView,
                subjectArray
        );

        ListView listView=(ListView)findViewById(R.id.listView);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(
            new AdapterView.OnItemClickListener() {
                public void onItemClick(AdapterView<?> list, View v, int pos, long id) {
                    TextView view = (TextView)findViewById(R.id.textView);
                    String subject = view.getText().toString();
                    Intent i = new Intent(getApplicationContext(), SelectCourseActivity.class);
                    i.putExtra("subject", subject);
                    startActivity(i);
                }
            }
        );
    }
}
