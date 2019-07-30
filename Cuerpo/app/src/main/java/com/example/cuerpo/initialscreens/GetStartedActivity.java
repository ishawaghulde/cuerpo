package com.example.cuerpo.initialscreens;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cuerpo.R;

public class GetStartedActivity extends AppCompatActivity {
    private static final String KEY__1 = "key1";
    private static final String KEY__2 = "key2";
    private static final String KEY__3 = "key3";
    private static final String KEY__4 = "key4";
    private static final String KEY__5 = "key5";
    private static final String KEY__6 = "key6";
    private static final String KEY__7 = "key7";
    boolean genderMale = false;
    boolean genderFemale = false;
    private String userName;
    private String userAge;
    private String userWeight;
    private String userHeight;
    private int userLifestyle;
    private String userDreamWeight;

    private EditText user;
    private EditText age;
    private EditText weight;
    private EditText height;
    private EditText dream_weight;
    private Button male;
    private Button female;
    private Spinner lifestyle;
    private Button next;

    private TextView userWarning;
    private TextView genderWarning;
    private TextView ageWarning;
    private TextView weightWarning;
    private TextView heightWarning;
    private TextView lifestyleWarning;
    private TextView dreamWeightWarning;
    public static final String MY_PREFS_NAME = "MyPrefsFile";
    SharedPreferences.Editor editor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_started);
        user = findViewById(R.id.user);
        male = findViewById(R.id.male);
        female = findViewById(R.id.female);
        age = findViewById(R.id.age);
        weight = findViewById(R.id.weight);
        height = findViewById(R.id.height);
        lifestyle = findViewById(R.id.lifestyle);
        dream_weight = findViewById(R.id.dream_weight);
        next = findViewById(R.id.btn_next);

        userWarning = findViewById(R.id.userWarning);
        genderWarning = findViewById(R.id.genderWarning);
        ageWarning = findViewById(R.id.ageWarning);
        weightWarning = findViewById(R.id.weightWarning);
        heightWarning = findViewById(R.id.heightWarning);
        lifestyleWarning = findViewById(R.id.lifestyleWarning);
        dreamWeightWarning = findViewById(R.id.dreamWeightWarning);

        editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();

        user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userWarning.setVisibility(View.VISIBLE);
            }
        });

        male.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                male.setBackgroundColor(Color.parseColor("#F92E2E"));
                female.setBackgroundColor(Color.parseColor("#F0EFEF"));
                userName = user.getText().toString();
                if(!userName.equals(""))
                    userWarning.setVisibility(View.INVISIBLE);
                genderWarning.setVisibility(View.VISIBLE);
                genderMale = true;
                genderFemale = false;
            }
        });

        female.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                female.setBackgroundColor(Color.parseColor("#F92E2E"));
                male.setBackgroundColor(Color.parseColor("#F0EFEF"));
                userName = user.getText().toString();
                if(!userName.equals(""))
                    userWarning.setVisibility(View.INVISIBLE);
                genderWarning.setVisibility(View.VISIBLE);
                genderFemale = true;
                genderMale = false;
            }
        });

        age.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userName = user.getText().toString();
                if(!userName.equals(""))
                    userWarning.setVisibility(View.INVISIBLE);
                genderWarning.setVisibility(View.INVISIBLE);
                ageWarning.setVisibility(View.VISIBLE);
            }
        });

        weight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userName = user.getText().toString();
                if(!userName.equals(""))
                    userWarning.setVisibility(View.INVISIBLE);
                genderWarning.setVisibility(View.INVISIBLE);
                userAge = age.getText().toString();
                if(!userAge.equals(""))
                    ageWarning.setVisibility(View.INVISIBLE);
                weightWarning.setVisibility(View.VISIBLE);
            }
        });

        height.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userName = user.getText().toString();
                if(!userName.equals(""))
                    userWarning.setVisibility(View.INVISIBLE);
                genderWarning.setVisibility(View.INVISIBLE);
                userAge = age.getText().toString();
                if(!userAge.equals(""))
                    ageWarning.setVisibility(View.INVISIBLE);
                userWeight = weight.getText().toString();
                if(!userWeight.equals(""))
                    weightWarning.setVisibility(View.VISIBLE);
                heightWarning.setVisibility(View.VISIBLE);

            }
        });

        lifestyle.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(
                    AdapterView<?> adapterView, View view,
                    int i, long l) {
                userName = user.getText().toString();
                if(!userName.equals(""))
                    userWarning.setVisibility(View.INVISIBLE);
                genderWarning.setVisibility(View.INVISIBLE);
                userAge = age.getText().toString();
                if(!userAge.equals(""))
                    ageWarning.setVisibility(View.INVISIBLE);
                userWeight = weight.getText().toString();
                if(!userWeight.equals(""))
                    weightWarning.setVisibility(View.VISIBLE);
                userHeight = height.getText().toString();
                if(!userHeight.equals(""))
                    heightWarning.setVisibility(View.INVISIBLE);
                lifestyleWarning.setVisibility(View.VISIBLE);
                userLifestyle = i;
            }

            public void onNothingSelected(
                    AdapterView<?> adapterView) {

            }
        });

        dream_weight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userName = user.getText().toString();
                if(!userName.equals(""))
                    userWarning.setVisibility(View.INVISIBLE);
                genderWarning.setVisibility(View.INVISIBLE);
                userAge = age.getText().toString();
                if(!userAge.equals(""))
                    ageWarning.setVisibility(View.INVISIBLE);
                userWeight = weight.getText().toString();
                if(!userWeight.equals(""))
                    weightWarning.setVisibility(View.VISIBLE);
                if(!userHeight.equals(""))
                    heightWarning.setVisibility(View.INVISIBLE);
                lifestyleWarning.setVisibility(View.INVISIBLE);
                dreamWeightWarning.setVisibility(View.VISIBLE);
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userName = user.getText().toString();
                userAge = age.getText().toString();
                userWeight = weight.getText().toString();
                userHeight = height.getText().toString();
                userDreamWeight = dream_weight.getText().toString();
                if(userName.equals(""))
                    Toast.makeText(GetStartedActivity.this, "Enter name", Toast.LENGTH_SHORT).show();
                if(userWeight.equals(""))
                    Toast.makeText(GetStartedActivity.this, "Enter Weight", Toast.LENGTH_SHORT).show();
                if(userHeight.equals(""))
                    Toast.makeText(GetStartedActivity.this, "Enter Height", Toast.LENGTH_SHORT).show();
                if(userDreamWeight.equals(""))
                    Toast.makeText(GetStartedActivity.this, "Enter Dream Weight", Toast.LENGTH_SHORT).show();
                if(userAge.equals(""))
                    Toast.makeText(GetStartedActivity.this, "Enter Age", Toast.LENGTH_SHORT).show();
                if(Integer.parseInt(userAge) < 14)
                    Toast.makeText(GetStartedActivity.this, "Enter age above 13", Toast.LENGTH_SHORT).show();
                if(!(userName.equals("") || userAge.equals("") || userWeight.equals("") || userHeight.equals("") || (!genderMale && !genderFemale))){
                    Intent intent = new Intent(GetStartedActivity.this, LoadingActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putString(KEY__1,userName);
                    editor.putString("name", userName);
                    if(genderFemale){
                        bundle.putString(KEY__2,"female");
                        editor.putString("gender", "female");
                    }

                    else{
                        bundle.putString(KEY__2,"male");
                        editor.putString("gender", "male");
                    }

                    bundle.putString(KEY__3,userAge);
                    editor.putInt("age", Integer.parseInt(userAge));
                    bundle.putString(KEY__4,userWeight);
                    editor.putInt("weight", Integer.parseInt(userWeight));
                    bundle.putString(KEY__5,userHeight);
                    editor.putInt("height", Integer.parseInt(userAge));
                    editor.apply();
                    bundle.putString(KEY__6,String.valueOf(userLifestyle));
                    bundle.putString(KEY__7,String.valueOf(userDreamWeight));
                    intent.putExtras(bundle);
                    startActivity(intent);
                    overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
                }

            }
        });
    }
}
