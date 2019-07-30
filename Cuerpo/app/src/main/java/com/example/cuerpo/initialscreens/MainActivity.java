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
