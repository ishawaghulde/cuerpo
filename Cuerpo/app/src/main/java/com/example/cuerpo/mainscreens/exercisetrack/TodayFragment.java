package com.example.cuerpo.mainscreens.exercisetrack;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.cuerpo.R;
import com.example.cuerpo.mainscreens.exercisetrack.Pedometer;

import me.itangqi.waveloadingview.WaveLoadingView;

public class TodayFragment extends Fragment {
    private static final String TIME = "time";
    private static final String DISTANCE = "distance";
    private static final String CALORIES = "calories";
    private static final String STEPS = "steps";
    private TextView distanceText;
    private TextView calText;
    private TextView timeText;

    private int total_time = 0;
    private int total_calories = 0;
    private int total_steps = 0;
    private int total_distance = 0;
    WaveLoadingView waveLoadingView;
    SharedPreferences prefs;
    SharedPreferences.Editor editor;
    private int calories_consumed;
    public static final String MY_PREFS_NAME = "MyPrefsFile";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_today,container,false);
        waveLoadingView = view.findViewById(R.id.waveLoadingView);
        editor = this.getActivity().getSharedPreferences(MY_PREFS_NAME, Context.MODE_PRIVATE).edit();

        prefs = this.getActivity().getSharedPreferences(MY_PREFS_NAME, Context.MODE_PRIVATE);
        total_time = prefs.getInt("total_time", 0);
        total_distance = prefs.getInt("total_distance", 0);
        total_calories = prefs.getInt("total_calories", 0);
        total_steps = prefs.getInt("total_steps", 0);
//        seekBar = view.findViewById(R.id.seekBar);
        distanceText = view.findViewById(R.id.distanceText);
        distanceText.setText(String.valueOf(total_distance));
        calText = view.findViewById(R.id.calText);
        calText.setText(String.valueOf(total_calories));
        timeText = view.findViewById(R.id.timeText);
        timeText.setText(String.valueOf(total_time));
        int percent = (int)(total_steps/100);
        waveLoadingView.setProgressValue(percent);
        waveLoadingView.setCenterTitle(String.valueOf(total_steps));


        Button start = view.findViewById(R.id.start_button);
        ImageView close_button = view.findViewById(R.id.close_button);

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), Pedometer.class);
                startActivityForResult(i,3);
            }
        });

        waveLoadingView.setProgressValue(0);

//        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
//            @Override
//            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
//                waveLoadingView.setProgressValue(i);
//                waveLoadingView.setCenterTitle(String.format("%d%%", i));
//            }
//
//            @Override
//            public void onStartTrackingTouch(SeekBar seekBar) {
//
//            }
//
//            @Override
//            public void onStopTrackingTouch(SeekBar seekBar) {
//
//            }
//        });
        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == 3) {
            if(resultCode == Activity.RESULT_OK){
                int workout_time = data.getIntExtra(TIME, 1);
                int workout_calories = data.getIntExtra(CALORIES, 0);
                int workout_distance = data.getIntExtra(DISTANCE, 0);
                int workout_steps = data.getIntExtra(STEPS, 0);

                total_time+=workout_time;
                editor.putInt("total_time", total_time);
                total_distance+=workout_distance;
                editor.putInt("total_distance", total_distance);
                total_calories+=workout_calories;
                editor.putInt("total_calories", total_calories);
                total_steps+=workout_steps;
                editor.putInt("total_steps", total_steps);
                calories_consumed = prefs.getInt("calories_consumed", 0);
                if(calories_consumed>total_calories)
                    calories_consumed -= total_calories;
                editor.putInt("calories_consumed", calories_consumed);
                editor.apply();


                timeText.setText(String.valueOf(total_time));
                calText.setText(String.valueOf(total_calories));
                distanceText.setText(String.valueOf(total_distance));

                int percent = (int)(total_steps/100);
                waveLoadingView.setProgressValue(percent);
                waveLoadingView.setCenterTitle(String.valueOf(total_steps));
            }
        }
    }
}

