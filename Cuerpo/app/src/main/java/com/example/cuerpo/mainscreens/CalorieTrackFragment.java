package com.example.cuerpo.mainscreens;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.cuerpo.mainscreens.exercisetrack.ExerciseActivity;
import com.example.cuerpo.mainscreens.foodtrack.FoodListActivity;
import com.example.cuerpo.R;
import com.razerdp.widget.animatedpieview.AnimatedPieView;
import com.razerdp.widget.animatedpieview.AnimatedPieViewConfig;
import com.razerdp.widget.animatedpieview.data.SimplePieInfo;

public class CalorieTrackFragment extends Fragment implements View.OnClickListener {

    private static final String CALORIES_CONSUMED = "calories_consumed";
    private static final String CALORIES_LOST = "calories_lost";
    private AnimatedPieViewConfig config;
    private  AnimatedPieView animatedPieView;
    private int calories_consumed;
    private int calories_lost;
    private int total_calories;
    private int daily_calories;
    SharedPreferences prefs;
    SharedPreferences.Editor editor;
    public static final String MY_PREFS_NAME = "MyPrefsFile";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.calorie_track_fragment,container,false);

        editor = this.getActivity().getSharedPreferences(MY_PREFS_NAME, Context.MODE_PRIVATE).edit();

        prefs = this.getActivity().getSharedPreferences(MY_PREFS_NAME, Context.MODE_PRIVATE);


        daily_calories = prefs.getInt("calories", 0);
        calories_consumed = prefs.getInt("calories_consumed", 0);
        total_calories = prefs.getInt("total_calories", 0);


        animatedPieView = view.findViewById(R.id.pieView);
        Button exercise_button = view.findViewById(R.id.exercise_button);
        Button calorie_button = view.findViewById(R.id.calorie_button);
        config = new AnimatedPieViewConfig();
        config.addData(new SimplePieInfo(calories_consumed, getResources().getColor(R.color.app_primary), "Calories : 0"));
        config.addData(new SimplePieInfo(daily_calories, getResources().getColor(R.color.white), "unconsumed calories"));
        animatedPieView.applyConfig(config);
        animatedPieView.start();
        config.duration(2000);
        config.drawText(true);
        config.textSize(48);
        config.strokeMode(true);
        config.startAngle(-180);
        config.pieRadius(150);

        exercise_button.setOnClickListener(this);
        calorie_button.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.exercise_button:
                Intent i = new Intent(getActivity(), ExerciseActivity.class);
                startActivity(i);
                break;

            case R.id.calorie_button:
                Intent i1 = new Intent(getActivity(), FoodListActivity.class);
                startActivityForResult(i1,1);
                break;


        }

    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == 1) {
            if (resultCode == Activity.RESULT_OK) {
                calories_consumed = prefs.getInt("calories_consumed", 0);
                calories_consumed += (int) data.getDoubleExtra(CALORIES_CONSUMED, 1);
                editor.putInt("calories_consumed", calories_consumed);
                editor.apply();


            }
        }

//        if (requestCode == 2) {
//            if (resultCode == Activity.RESULT_OK) {
//                calories_lost = prefs.getInt("total_calories", 0);
//                calories_lost += data.getIntExtra(CALORIES_LOST, 0);
//                editor.putInt("calories_consumed", calories_consumed - calories_lost);
//                editor.apply();
//
//            }
//        }

        config = new AnimatedPieViewConfig();
        if(calories_consumed>daily_calories){
            config.addData(new SimplePieInfo(daily_calories, getResources().getColor(R.color.app_primary), "Calories : " + calories_consumed));
            config.addData(new SimplePieInfo(0, getResources().getColor(R.color.white), "unconsumed calories"));
            animatedPieView.applyConfig(config);
            animatedPieView.start();
            config.duration(2000);
            config.drawText(true);
            config.textSize(48);
            config.strokeMode(true);
            config.startAngle(-180);
            config.pieRadius(150);
        }else{
            config.addData(new SimplePieInfo(calories_consumed, getResources().getColor(R.color.app_primary), "Calories : " + calories_consumed));
            config.addData(new SimplePieInfo(daily_calories-calories_consumed, getResources().getColor(R.color.white), "unconsumed calories"));
            animatedPieView.applyConfig(config);
            animatedPieView.start();
            config.duration(2000);
            config.drawText(true);
            config.textSize(48);
            config.strokeMode(true);
            config.startAngle(-180);
            config.pieRadius(150);
        }


    }
}