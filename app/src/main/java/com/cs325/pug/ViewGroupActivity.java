package com.cs325.pug;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.AppCompatActivity;
import android.support.v4.app.NotificationCompat;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ViewGroupActivity extends AppCompatActivity {
    private Notification headsUpNotification;
    private Handler handler = new Handler();
    private Runnable runnable = new Runnable(){
        public void run() {
            prompt();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_group);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Pick Up Group");
        setSupportActionBar(toolbar);

        Bundle extras = getIntent().getExtras();
        final String subject = extras.getString("subject");
        final String course = extras.getString("course");
        final String title = extras.getString("title");
        final String location = extras.getString("location");
        final String capacity = extras.getString("capacity");
        final String duration = extras.getString("duration");

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

        ////
        // notification tap action
        ////

        final int id = 0;
        NotificationCompat.Builder builder
        = new NotificationCompat.Builder(this)
        .setSmallIcon(R.drawable.icon)
        .setContentTitle("Currently in: " + title)
        .setContentText("Swipe to leave the group.");

        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
        stackBuilder.addParentStack(ViewGroupActivity.class);
        Intent tapIntent = new Intent(this, ViewGroupActivity.class);
        tapIntent.putExtra("subject", subject);
        tapIntent.putExtra("course", course);
        tapIntent.putExtra("title", title);
        tapIntent.putExtra("location", location);
        tapIntent.putExtra("capacity", capacity);
        tapIntent.putExtra("duration", duration);
        stackBuilder.addNextIntent(tapIntent);

        builder.setContentIntent(
                stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT)
        );

        ////
        // notification swipe action
        ////

        Intent swipeIntent = new Intent(this, NotificationDeleteReceiver.class);
        swipeIntent.putExtra("subject", subject);
        swipeIntent.putExtra("course", course);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(
            this.getApplicationContext(), 0, swipeIntent, 0
        );
        builder.setDeleteIntent(pendingIntent);
        final NotificationManager notificationManager
        = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(0, builder.build());

        ////
        // heads up notification
        ////

        class DismissNotification extends BroadcastReceiver {
            @Override
            public void onReceive(Context context, Intent intent) {
//                startActivity(intent);
            }
        }

        PendingIntent headsUpIntent = PendingIntent.getActivity(
            this, 1, new Intent(this, DismissNotification.class), 0
        );

        NotificationCompat.Builder headsUpBuilder = new NotificationCompat.Builder(this)
        .setSmallIcon(R.drawable.icon)
                .setContentTitle("You have moved too far.")
                .setContentText("Swipe to leave the group. Tap to stay.")
                .setContentIntent(headsUpIntent)
                .setFullScreenIntent(headsUpIntent, true)
                .setDeleteIntent(pendingIntent)
                .setAutoCancel(true);

        headsUpNotification = headsUpBuilder.build();
        handler.postDelayed(runnable, 10000);

        Button button = (Button)findViewById(R.id.button);
        button.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(final View v) {
                        v.setBackgroundColor(Color.rgb(64, 64, 64));
                        notificationManager.cancelAll();
                        Intent i = new Intent(getApplicationContext(), SelectGroupActivity.class);
                        i.putExtra("subject", subject);
                        i.putExtra("course", course);
                        startActivity(i);
                    }
                }
        );
    }

    private void prompt() {
        NotificationManager notificiationManager
        = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
        notificiationManager.notify(1, headsUpNotification);
    }
}
