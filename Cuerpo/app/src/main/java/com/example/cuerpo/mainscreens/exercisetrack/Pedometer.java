package com.example.cuerpo.mainscreens.exercisetrack;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.cuerpo.R;

public class Pedometer extends AppCompatActivity  implements SensorEventListener, StepListener {
    private static final String TIME = "time";
    private static final String DISTANCE = "distance";
    private static final String CALORIES = "calories";
    private static final String STEPS = "steps";

    private TextView textView;
    private StepDetector simpleStepDetector;
    private SensorManager sensorManager;
    private Sensor accel;
    private static final String TEXT_NUM_STEPS = "Number of Steps: ";
    private int numSteps;
    private TextView TvSteps;
    private TextView timer;
    private TextView distance_covered;
    private Button BtnStart;
    private Button BtnStop;
    private boolean isFemale;
    private int height;
    private int weight;
    private double stride;
    private static final double FEMALE_STRIDE = 0.413;
    private static final double MALE_STRIDE = 0.415;
    private int counter = 0;
    SharedPreferences prefs;
    SharedPreferences.Editor editor;
    public static final String MY_PREFS_NAME = "MyPrefsFile";


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedometer);
        editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();

        prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        accel = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        simpleStepDetector = new StepDetector();
        simpleStepDetector.registerListener(this);

        TvSteps = findViewById(R.id.tv_steps);
        timer = findViewById(R.id.timer);
        distance_covered = findViewById(R.id.distance_covered);

        BtnStop = (Button) findViewById(R.id.btn_stop);

//        get the gender, height and weight. Calculate stride leng
        if(prefs.getString("gender", "male").equals("female"))
            isFemale = true;
        else
            isFemale = false;

        height = prefs.getInt("height", 0);
        weight = prefs.getInt("weight", 0);
        if(isFemale)
            stride = FEMALE_STRIDE*height;
        else
            stride = MALE_STRIDE*height;

        numSteps = 0;
        sensorManager.registerListener(Pedometer.this, accel, SensorManager.SENSOR_DELAY_FASTEST);



        BtnStop.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                sensorManager.unregisterListener(Pedometer.this);
//                Intent returnIntent = new Intent();
//                String result = Integer.toString(totalTries);
//                String infoScore = Integer.toString(score);
//                returnIntent.putExtra("result",result);
//                returnIntent.putExtra("key", infoScore);
//                setResult(Activity.RESULT_OK,returnIntent);
//                finish();

                Intent returnIntent = new Intent();
                int min = counter/60;
                double distance = (numSteps*stride/100);
                double speed = 3.6*(distance/counter);
                int calories;
                if(speed<3.2)
//                    multiply 0.57 by weight
                    calories = (int)(0.57*weight*distance/1609);
                else
                    calories = (int)(0.5*weight*distance/1609);

                returnIntent.putExtra(TIME, min);
                returnIntent.putExtra(CALORIES, calories);
                returnIntent.putExtra(DISTANCE, (int)distance);
                returnIntent.putExtra(STEPS, numSteps);
                setResult(Activity.RESULT_OK,returnIntent);
                finish();
            }
        });

    }

    CountDownTimer countDownTimer =  new CountDownTimer(Long.MAX_VALUE, 1000){
        @Override
        public void onTick(long millisUntilFinished){
            counter++;
            if(counter>=60){
                int min = counter/60;
                int sec = counter%60;
                String secStr = String.valueOf(sec);
                if(sec<10)
                    secStr = "0" + secStr;
                String timerStr = min + " : " + secStr;
                timer.setText(timerStr);
            }
            else
                timer.setText(String.valueOf(counter));
        }
        public void onFinish(){

        }
    }.start();

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            simpleStepDetector.updateAccel(
                    event.timestamp, event.values[0], event.values[1], event.values[2]);
        }
    }

    @Override
    public void step(long timeNs) {
        numSteps++;
        TvSteps.setText(TEXT_NUM_STEPS + numSteps);
        Double distance = numSteps*stride/100;
        distance_covered.setText(String.valueOf(distance));
    }
}
