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
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class SelectGroupActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_group);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Bundle extras = getIntent().getExtras();
        final String subject = extras.getString("subject");
        final String course = extras.getString("course");

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
                R.id.textView,
                groupArray
        );

        ListView groupList=(ListView)findViewById(R.id.groupList);
        groupList.setAdapter(adapter);
        groupList.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    public void onItemClick(AdapterView<?> list, View v, int pos, long id) {
                        TextView view = (TextView) findViewById(R.id.textView);
                        String title = view.getText().toString();
                        Intent i = new Intent(getApplicationContext(), JoinGroupActivity.class);
                        i.putExtra("subject", subject);
                        i.putExtra("course", course);
                        i.putExtra("title", title);
                        startActivity(i);
                    }
                }
        );

        Button create = (Button)findViewById(R.id.create);
        create.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(final View v) {
                        Intent i = new Intent(getApplicationContext(), CreateGroupActivity.class);
                        i.putExtra("subject", subject);
                        i.putExtra("course", course);
                        startActivity(i);
                    }
                }
        );
    }
}
