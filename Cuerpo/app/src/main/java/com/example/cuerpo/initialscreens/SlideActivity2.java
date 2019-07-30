package com.example.cuerpo.initialscreens;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.cuerpo.R;

public class SlideActivity2 extends AppCompatActivity {
    private LinearLayout layout;
    private Intent intent1;
    private Intent intent2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slide2);
        layout= findViewById(R.id.layout);
        intent1 = new Intent(SlideActivity2.this, SlideActivity3.class);
        intent2 = new Intent(SlideActivity2.this, SlideActivity1.class);

        TextView signUp_button = findViewById(R.id.signUp_button);
        signUp_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SlideActivity2.this, SignUpActivity.class);
                startActivity(intent);

            }
        });

        layout.setOnTouchListener(new OnSwipeTouchListener(SlideActivity2.this) {

            public void onSwipeLeft() {
                startActivity(intent1);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }

            public void onSwipeRight() {
                startActivity(intent2);
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
            }
        });
    }
}
