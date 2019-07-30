package com.example.cuerpo.initialscreens;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.cuerpo.R;

public class SlideActivity3 extends AppCompatActivity {
    private LinearLayout layout;
    private Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slide3);
        layout= findViewById(R.id.layout);
        intent = new Intent(SlideActivity3.this, SlideActivity2.class);

        TextView signUp_button = findViewById(R.id.signUp_button);
        signUp_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SlideActivity3.this, SignUpActivity.class);
                startActivity(intent);

            }
        });

        layout.setOnTouchListener(new OnSwipeTouchListener(SlideActivity3.this) {

            public void onSwipeRight() {
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
            }
        });
    }
}
