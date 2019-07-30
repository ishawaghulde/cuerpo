package com.example.cuerpo.initialscreens;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.cuerpo.R;
import com.example.cuerpo.mainscreens.TrackActivity;

public class RequirementActivity extends AppCompatActivity {
    private static final String KEY_1 = "key_1";
    private static final String KEY_2 = "key_2";
    private static final String KEY_3 = "key_3";
    private static final String KEY_4 = "key_4";

    private static final String KEY1 = "key1";
    private static final String KEY2 = "key2";

    private int glassOfWater;
    private double sleepHours;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_requirement);
        Bundle bundle1 = getIntent().getExtras();
        String name = bundle1.getString(KEY_1);
        int calorie = bundle1.getInt(KEY_2);
        glassOfWater = bundle1.getInt(KEY_3);
        sleepHours = bundle1.getDouble(KEY_4);

        TextView nameText = findViewById(R.id.name);
        TextView calorieText = findViewById(R.id.calories);
        TextView waterText = findViewById(R.id.water);
        TextView sleepText = findViewById(R.id.sleep);
        Button trackButton = findViewById(R.id.track_button);

        nameText.setText(name.toUpperCase());
        String calsStr = calorie + " Cals";
        calorieText.setText(calsStr);
        String waterstr = glassOfWater + " Glasses";
        waterText.setText(waterstr);
        String sleepStr = sleepHours + " Hours";
        sleepText.setText(sleepStr);

        trackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RequirementActivity.this, TrackActivity.class);
//                Bundle bundle = new Bundle();
//                bundle.putInt(KEY1, glassOfWater);
//                bundle.putDouble(KEY2, sleepHours);
//                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

    }
}
