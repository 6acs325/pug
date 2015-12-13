package com.cs325.pug;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class LoadAppActivity extends AppCompatActivity {
    class LoadHandler extends Handler {
        @Override
        public void handleMessage(Message message) {
            try {
                Thread.sleep(2000);
                load();
            }
            catch (InterruptedException e) {

            }
        }
    }
    LoadHandler loadHandler = new LoadHandler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load_app);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        toolbar.setTitle("Pick Up Group");
        setSupportActionBar(toolbar);
        loadHandler.handleMessage(null);
    }

    private void load() {
        Intent intent = new Intent(this, SelectSubjectActivity.class);
        startActivity(intent);
    }
}
