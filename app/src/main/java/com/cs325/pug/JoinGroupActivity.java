package com.cs325.pug;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class JoinGroupActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join_group);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Bundle extras = getIntent().getExtras();
        final String subject = extras.getString("subject");
        final String course = extras.getString("course");

        final String title = extras.getString("title");
        final String location = "USpace";
        final String capacity = "2 / 3 people";
        final String duration = "90 minutes";

        TextView textView = (TextView)findViewById(R.id.subject);
        textView.setText(subject);
        textView = (TextView)findViewById(R.id.course);
        textView.setText(course);

        textView = (TextView)findViewById(R.id.title);
        textView.setText(title);
        textView = (TextView)findViewById(R.id.location);
        textView.setText(location);
        textView = (TextView)findViewById(R.id.capacity);
        textView.setText(capacity);
        textView = (TextView)findViewById(R.id.duration);
        textView.setText(duration);

        Button join = (Button)findViewById(R.id.join);
        join.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(final View v) {
                        Intent i = new Intent(getApplicationContext(), ViewGroupActivity.class);
                        i.putExtra("subject", subject);
                        i.putExtra("course", course);
                        i.putExtra("title", title);
                        i.putExtra("location", location);
                        i.putExtra("capacity", capacity);
                        i.putExtra("duration", duration);
                        startActivity(i);
                    }
                }
        );
    }

}
