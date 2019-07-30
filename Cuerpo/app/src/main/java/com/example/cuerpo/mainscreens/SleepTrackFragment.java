package com.example.cuerpo.mainscreens;

import android.app.TimePickerDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import com.example.cuerpo.R;

public class SleepTrackFragment extends Fragment{
    private int timeStart;
    private int timeStop;
    private int timeSleep;
    private DialogFragment timePicker1;
    private DialogFragment timePicker2;
    private String callback;
    private TextView time;
    private double sleepHours;
    private static final String SLEEP = "sleep";
    private LinearLayout linLayout;
    private ImageView square1;
    private ImageView square2;
    private ImageView square3;
    private ImageView square4;
    private ImageView square5;
    private double slept_hours;

    public static final String MY_PREFS_NAME = "MyPrefsFile";
    SharedPreferences.Editor editor;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.sleep_track_fragment,container,false);

        SharedPreferences prefs = this.getActivity().getSharedPreferences(MY_PREFS_NAME, Context.MODE_PRIVATE);

        sleepHours = Double.parseDouble(prefs.getString("sleep", "8"));
        slept_hours = Double.parseDouble(prefs.getString("slept_hours", "0"));
        editor = this.getActivity().getSharedPreferences(MY_PREFS_NAME, Context.MODE_PRIVATE).edit();
        setBackgroundColor(slept_hours);

//        sleepHours = getArguments().getDouble(SLEEP);

        Button start_button = view.findViewById(R.id.start_time);
        time = view.findViewById(R.id.show_time);
        linLayout = view.findViewById(R.id.linLayout);
        Button stop_button = view.findViewById(R.id.end_time);
        square1 = view.findViewById(R.id.square1);
        square2 = view.findViewById(R.id.square2);
        square3 = view.findViewById(R.id.square3);
        square4 = view.findViewById(R.id.square4);
        square5 = view.findViewById(R.id.square5);

        square1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                square1.setBackgroundColor(getResources().getColor(R.color.app_primary));
                square2.setBackgroundColor(getResources().getColor(R.color.red_transparent));
                square3.setBackgroundColor(getResources().getColor(R.color.red_transparent));
                square4.setBackgroundColor(getResources().getColor(R.color.red_transparent));
                square5.setBackgroundColor(getResources().getColor(R.color.red_transparent));
            }
        });

        square2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                square1.setBackgroundColor(getResources().getColor(R.color.app_primary));
                square2.setBackgroundColor(getResources().getColor(R.color.app_primary));
                square3.setBackgroundColor(getResources().getColor(R.color.red_transparent));
                square4.setBackgroundColor(getResources().getColor(R.color.red_transparent));
                square5.setBackgroundColor(getResources().getColor(R.color.red_transparent));
            }
        });

        square3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                square1.setBackgroundColor(getResources().getColor(R.color.app_primary));
                square2.setBackgroundColor(getResources().getColor(R.color.app_primary));
                square3.setBackgroundColor(getResources().getColor(R.color.app_primary));
                square4.setBackgroundColor(getResources().getColor(R.color.red_transparent));
                square5.setBackgroundColor(getResources().getColor(R.color.red_transparent));
            }
        });

        square4.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                square1.setBackgroundColor(getResources().getColor(R.color.app_primary));
                square2.setBackgroundColor(getResources().getColor(R.color.app_primary));
                square3.setBackgroundColor(getResources().getColor(R.color.app_primary));
                square4.setBackgroundColor(getResources().getColor(R.color.app_primary));
                square5.setBackgroundColor(getResources().getColor(R.color.red_transparent));
            }
        });

        square5.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                square1.setBackgroundColor(getResources().getColor(R.color.app_primary));
                square2.setBackgroundColor(getResources().getColor(R.color.app_primary));
                square3.setBackgroundColor(getResources().getColor(R.color.app_primary));
                square4.setBackgroundColor(getResources().getColor(R.color.app_primary));
                square5.setBackgroundColor(getResources().getColor(R.color.app_primary));
            }
        });
        start_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callback = "for_start_time";
                timePicker1 = new TimePickerFragment(mTimeSetListener);
                timePicker1.show(getFragmentManager(), "time picker1");

            }
        });

        stop_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callback = "for_end_time";
                timePicker2 = new TimePickerFragment(mTimeSetListener);
                timePicker2.show(getFragmentManager(), "time picker2");

            }
        });

        return view;
    }

    private void setBackgroundColor(double time){
//         if(time< sleepHours+7.5)
//            linLayout.setBackgroundColor(getResources().getColor(R.color.blue1));
//
//
//        else if(time< sleepHours+6.5)
//            linLayout.setBackgroundColor(getResources().getColor(R.color.blue2));
//
//        else if(time< sleepHours+5.5)
//            linLayout.setBackgroundColor(getResources().getColor(R.color.blue3));
//
//         else if(time< sleepHours+4.5)
//            linLayout.setBackgroundColor(getResources().getColor(R.color.blue4));
//         else if(time< sleepHours+3.5)
//            linLayout.setBackgroundColor(getResources().getColor(R.color.blue5));
//        else if(time< sleepHours+2.5)
//            linLayout.setBackgroundColor(getResources().getColor(R.color.blue6));
//        else if(time<sleepHours + 1.5)
//            linLayout.setBackgroundColor(getResources().getColor(R.color.blue7));
        if(time> sleepHours-0.5 && time<sleepHours+0.5)
            linLayout.setBackgroundColor(getResources().getColor(R.color.blue_final));

        else if(time> sleepHours-1.5)
            linLayout.setBackgroundColor(getResources().getColor(R.color.blue7));

        else if(time> sleepHours-2.5)
            linLayout.setBackgroundColor(getResources().getColor(R.color.blue6));

        else if(time> sleepHours-3.5)
            linLayout.setBackgroundColor(getResources().getColor(R.color.blue5));

        else if(time> sleepHours-4.5)
            linLayout.setBackgroundColor(getResources().getColor(R.color.blue4));

        else if(time> sleepHours-5.5)
            linLayout.setBackgroundColor(getResources().getColor(R.color.blue3));

        else if(time> sleepHours-6.5)
            linLayout.setBackgroundColor(getResources().getColor(R.color.blue2));

        else if(time> sleepHours-7.5)
            linLayout.setBackgroundColor(getResources().getColor(R.color.blue1));


    }

    TimePickerDialog.OnTimeSetListener mTimeSetListener =
            new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(android.widget.TimePicker view,
                                      int i, int i1) {

                    if (TextUtils.isEmpty(callback))
                        return;

                    if (callback.equalsIgnoreCase("for_start_time")){
                        timeStart = i*60 + i1;
                    }
                    else if (callback.equalsIgnoreCase("for_end_time")){
                        timeStop = i*60 + i1;
                    }

                    if(timeStart<=timeStop) {
                        int hours = (timeStop - timeStart)/60;
                        int minutes = (timeStop - timeStart)%60;
                        String str = hours + "hours " + minutes + "minutes";
                        time.setText(str);
                        double s = (double)hours + ((double)minutes)/60;
                        String s1 = String.valueOf(s);
                        editor.putString("slept_hours",s1);
                        editor.apply();
                        setBackgroundColor(s);
                    }
                    else{
                        int hours = ((24*60) + timeStop - timeStart)/60;
                        int minutes = ((24*60) + timeStop - timeStart)%60;
                        String str = hours + " hrs " + minutes + " mins";
                        time.setText(str);
                        double s = (double)hours + ((double)minutes)/60;
                        String s1 = String.valueOf(s);
                        editor.putString("slept_hours",s1);
                        editor.apply();
                        setBackgroundColor(s);
                    }

                    callback = "";

                }
            };

}
