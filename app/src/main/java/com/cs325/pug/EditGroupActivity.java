package com.cs325.pug;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

public class EditGroupActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_group);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Bundle extras = getIntent().getExtras();
        final String subject = extras.getString("subject");
        final String course = extras.getString("course");
        final String title = extras.getString("title");
        final String location = extras.getString("location");
        final String capacity = extras.getString("capacity");
        final String duration = extras.getString("duration");

        String[] capacityArray = {
                "Unlimited",
                "2 people",
                "3 people",
                "4 people",
                "5 people",
                "6 people",
                "7 people",
                "8 people"
        };
        ArrayAdapter<String> capacityAdapter = new ArrayAdapter<String>(
                this,
                R.layout.spinner_item,
                R.id.item,
                capacityArray
        );

        Spinner capacitySpinner = (Spinner) findViewById(R.id.capacity);
        capacitySpinner.setAdapter(capacityAdapter);

        String[] durationArray = {
                "Unlimited",
                "60 minutes",
                "90 minutes",
                "2 hours",
                "3 hours",
                "4 hours",
                "5 hours",
                "6 hours",
                "7 hours",
                "8 hours"
        };
        ArrayAdapter<String> durationAdapter = new ArrayAdapter<String>(
                this,
                R.layout.spinner_item,
                R.id.item,
                durationArray
        );

        Spinner durationSpinner = (Spinner) findViewById(R.id.duration);
        durationSpinner.setAdapter(durationAdapter);

        TextView textView = (TextView)findViewById(R.id.subject);
        textView.setText(subject);
        textView = (TextView)findViewById(R.id.course);
        textView.setText(course);
        textView = (TextView)findViewById(R.id.title);
        textView.setText(title);
        textView = (TextView)findViewById(R.id.location);
        textView.setText(location);
        capacitySpinner.setSelection(capacityAdapter.getPosition(capacity));
        durationSpinner.setSelection(durationAdapter.getPosition(duration));

        Button back = (Button) findViewById(R.id.back);
        back.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(final View v) {
                        v.setBackgroundColor(Color.rgb(64, 64, 64));
                        Intent i = new Intent(getApplicationContext(), LeaderViewGroupActivity.class);
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

        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(final View v) {
                        v.setBackgroundColor(Color.rgb(64, 64, 64));
                        TextView textView = (TextView) findViewById(R.id.title);
                        String title = textView.getText().toString();

                        textView = (TextView) findViewById(R.id.location);
                        String location = textView.getText().toString();

                        Spinner spinner = (Spinner) findViewById(R.id.capacity);
                        String capacity = spinner.getSelectedItem().toString();

                        spinner = (Spinner) findViewById(R.id.duration);
                        String duration = spinner.getSelectedItem().toString();

                        Intent i = new Intent(getApplicationContext(), LeaderViewGroupActivity.class);
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
