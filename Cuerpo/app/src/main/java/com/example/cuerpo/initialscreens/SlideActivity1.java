package com.example.cuerpo.initialscreens;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.TargetApi;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.cuerpo.R;

public class SlideActivity1 extends AppCompatActivity {
    Intent intent;
    private TextView signUp_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slide1);
        LinearLayout layout = findViewById(R.id.layout);
        intent = new Intent(this, SlideActivity2.class);
        signUp_button = findViewById(R.id.signUp_button);

        layout.setOnTouchListener(new OnSwipeTouchListener(SlideActivity1.this) {

            public void onSwipeLeft() {
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });
    }

    @TargetApi(21)
    public void sharedElementTransition(View v){
        try {
            ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(this, signUp_button, "shared_signUp");
            Intent intent = new Intent(SlideActivity1.this, SignUpActivity.class);
            startActivity(intent, options.toBundle());

        } catch (Exception e) {
            System.out.println("Error " + e.getMessage());
            Intent intent = new Intent(SlideActivity1.this, SignUpActivity.class);
            startActivity(intent);
        }

    }
}
