package com.example.cuerpo.initialscreens;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ProgressBar;

import com.example.cuerpo.R;

public class LoadingActivity extends AppCompatActivity {
    private ProgressBar spinner;
    private static final String KEY__1 = "key1";
    private static final String KEY__2 = "key2";
    private static final String KEY__3 = "key3";
    private static final String KEY__4 = "key4";
    private static final String KEY__5 = "key5";
    private static final String KEY__6 = "key6";
    private static final String KEY__7 = "key7";

    private static final String KEY_1 = "key_1";
    private static final String KEY_2 = "key_2";
    private static final String KEY_3 = "key_3";
    private static final String KEY_4 = "key_4";

    private int s;
    private double multFactor;
    private int dailyCalories;
    private double dailySleep;
    private double dailyWater;
    private Intent intent;

    public static final String MY_PREFS_NAME = "MyPrefsFile";
    SharedPreferences.Editor editor;

    private String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);

        spinner = findViewById(R.id.progressBar);

        Bundle bundle = getIntent().getExtras();
        name = bundle.getString(KEY__1);
        String gender = bundle.getString(KEY__2);
        int age = Integer.parseInt(bundle.getString(KEY__3));
        double weight = Double.parseDouble(bundle.getString(KEY__4));
        double height = Double.parseDouble(bundle.getString(KEY__5));
        int lifestyle = Integer.parseInt(bundle.getString(KEY__6));
        double dreamWeight = Double.parseDouble(bundle.getString(KEY__7));

        editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();

        if(gender.equals("male"))
            s = 5;
        else
            s= -161;

        switch(lifestyle){
            case 0:
                multFactor = 1.2;
                break;

            case 1:
                multFactor = 1.375;
                break;

            case 2:
                multFactor = 1.55;
                break;

            case 3:
                multFactor = 1.725;
                break;

            case 4:
                multFactor = 1.9;
                break;
        }

        double bmr = (10 * weight)  + (6.25 * height) - (5 * (double)age)  + s;
        dailyCalories = (int) (bmr*multFactor);

        if(dreamWeight < weight){
            if(dailyCalories >= 1300 && dailyCalories <= 2000)
                dailyCalories -= 250;
            else
                dailyCalories -= 500;
        }

        if(age <= 17)
            dailySleep = 9;
        else if(age <= 64)
            dailySleep = 8;
        else
            dailySleep = 7.5;

        if(gender.equals("female"))
            dailySleep += 0.5;

        dailyWater = (43.47 * weight);
        switch(lifestyle){
            case 0:
                break;
            case 1:
                dailyWater += 202.76;
                break;
            case 2:
                dailyWater += 405.53;
                break;
            case 3:
                dailyWater += 608.28;
                break;
            case 4:
                dailyWater += 709.68;
        }

        dailyWater = (int) dailyWater/250;
        intent = new Intent(LoadingActivity.this, RequirementActivity.class);
        Bundle bundle1 = new Bundle();
        bundle1.putString(KEY_1, name);
        bundle1.putInt(KEY_2, dailyCalories);
        editor.putInt("calories", dailyCalories);
        bundle1.putInt(KEY_3, (int) dailyWater);
        editor.putInt("water", (int)dailyWater);
        bundle1.putDouble(KEY_4, dailySleep);
        editor.putString("sleep",String.valueOf(dailySleep));
        editor.putInt("calories_consumed", 0);
        editor.putInt("total_time", 0);
        editor.putInt("total_distance", 0);
        editor.putInt("total_calories", 0);
        editor.putInt("water_drank", 0);
        editor.putInt("total_steps", 0);
        editor.putString("slept_hours", "0");

        editor.apply();
        intent.putExtras(bundle1);

        new Handler().postDelayed(new Runnable(){
            @Override
            public void run(){
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
            }
        },4000);


    }
}
