package com.example.cuerpo.initialscreens;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.cuerpo.R;
import com.example.cuerpo.mainscreens.TrackActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    SharedPreferences prefs;
    public static final String MY_PREFS_NAME = "MyPrefsFile";
    private DbHandler dbHandler;
    boolean exists;
    String todayString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageView title = findViewById(R.id.title);
        TextView tagLine = findViewById(R.id.tagLine);
        prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        Animation anim = AnimationUtils.loadAnimation(this, R.anim.splash_transition);
        Date todayDate = Calendar.getInstance().getTime();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        todayString = formatter.format(todayDate);
        dbHandler = new DbHandler(MainActivity.this);
        exists = dbHandler.GetUserByDate(todayString);
        title.startAnimation(anim);
        tagLine.startAnimation(anim);

        final Intent i = new Intent(MainActivity.this, SlideActivity1.class);
        final Intent i1 = new Intent(MainActivity.this, TrackActivity.class);
        Thread timer = new Thread(){
            public void run(){
                try{
                    sleep(4000);
                }catch(InterruptedException e){
                    e.printStackTrace();
                }
                finally{
                    if(!exists){
                        String name = prefs.getString("name", "");

                        if(name.equals("")){
                            dbHandler.insertUserDetails(todayString, 0, "0", 0, 0, 0);
                            startActivity(i);
                            finish();
                        }
                        else{

                            int water_drank = prefs.getInt("water_drank",0);
                            String slept_hours = prefs.getString("slept_hours", "0");
                            int calories_consumed = prefs.getInt("calories_consumed", 0);
                            int total_calories = prefs.getInt("total_calories", 0);
                            int total_steps = prefs.getInt("total_steps", 0);
                            String lastDate = dbHandler.GetLastRow();

                            dbHandler.updateDetails(lastDate,water_drank, slept_hours, calories_consumed, total_calories, total_steps);

                            dbHandler.insertUserDetails(todayString, 0, "0", 0, 0, 0);

                            startActivity(i1);
                            finish();
                        }
                    }
                    else{
                        int water_drank = prefs.getInt("water_drank",0);
                        String slept_hours = prefs.getString("slept_hours", "0");
                        int calories_consumed = prefs.getInt("calories_consumed", 0);
                        int total_calories = prefs.getInt("total_calories", 0);
                        int total_steps = prefs.getInt("total_steps", 0);

                        dbHandler.updateDetails(todayString,water_drank, slept_hours, calories_consumed, total_calories, total_steps);

                        startActivity(i1);
                        finish();

                    }


                }
            }
        };
        timer.start();
    }
}

//        class AlarmReceiver extends BroadcastReceiver {
//
//            public static final String CHANNEL_ID = "exampleServiceChannel";
//
//            @Override
//            public void onReceive(Context context, Intent intent) {
//                Calendar c = Calendar.getInstance();
//                int hour = c.get(Calendar.HOUR_OF_DAY);
//                int minute = c.get(Calendar.MINUTE);
//                if(hour==9|| hour==12||hour==3||hour==6){
//                    String input = "Did you drink enough water?";
//                    Notification notification = new NotificationCompat.Builder(getApplicationContext(), CHANNEL_ID)
//                            .setContentTitle("Change in Audio Input")
//                            .setContentText(input)
//                            .setSmallIcon(R.drawable.tab_water)
//                            .setContentIntent(intent)
//                            .build();
//                }
//
//                Notification notification = new NotificationCompat.Builder(getApplicationContext(), CHANNEL_ID)
//                        .setContentTitle("Change in Audio Imput")
//                        .setContentText("aloha")
//                        .setSmallIcon(R.drawable.tab_water)
//                        .setContentIntent(intent)
//                        .build();
//
//
//            }
//        }
//
//        class DeviceBootReceiver extends BroadcastReceiver {
//
//            @Override
//            public void onReceive(Context context, Intent intent) {
//                if (intent.getAction().equals("android.intent.action.BOOT_COMPLETED")) {
//                    /* Setting the alarm here */
//                    Intent alarmIntent = new Intent(context, AlarmReceiver.class);
//                    PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, alarmIntent, 0);
//
//                    AlarmManager manager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
//                    int interval = 100*60*60*3;
//                    manager.setInexactRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), interval, pendingIntent);
//
//                    Toast.makeText(context, "Alarm Set", Toast.LENGTH_SHORT).show();
//                }
//            }
//        }

//    }
//}
//        Intent alarmIntent = new Intent(MainActivity.this, AlarmReceiver.class);
//        pendingIntent = PendingIntent.getBroadcast(MainActivity.this, 0, alarmIntent, 0);
//        AlarmManager manager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
//        int interval = 1000 * 60 * 60 * 3;
//
//        /* Set the alarm to start at 10:30 AM */
//        Calendar calendar = Calendar.getInstance();
//        calendar.setTimeInMillis(System.currentTimeMillis());
//        calendar.set(Calendar.HOUR_OF_DAY, 9);
//        calendar.set(Calendar.MINUTE, 0);
//
//        if (calendar.before(Calendar.getInstance())) {
//            calendar.add(Calendar.DATE, 1);
//        }
//        if (manager != null) {
//            manager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
//                    AlarmManager.INTERVAL_DAY, pendingIntent);
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//                manager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
//            }
//        }
