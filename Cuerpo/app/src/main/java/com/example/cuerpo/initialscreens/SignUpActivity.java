package com.example.cuerpo.initialscreens;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.cuerpo.R;

public class SignUpActivity extends AppCompatActivity {
    private EditText user;
    private EditText email;
    private EditText pass;
    private EditText repass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        user = findViewById(R.id.user);
        email = findViewById(R.id.emailAddress);
        pass = findViewById(R.id.password);
        repass = findViewById(R.id.repassword);

    }

    public static boolean isValidEmail(CharSequence target) {
        return (!TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches());
    }

    public void didTapButton(View view) {
        Button signUp = findViewById(R.id.signup_button);
        final Animation myAnim = AnimationUtils.loadAnimation(this, R.anim.bounce);

        MyBounceInterpolator interpolator = new MyBounceInterpolator(0.1, 10);
        myAnim.setInterpolator(interpolator);
        myAnim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                String emailAddress = email.getText().toString();
                String password = pass.getText().toString();
                String repassword = repass.getText().toString();
                String name = user.getText().toString();
                if(name.equals(""))
                    Toast.makeText(SignUpActivity.this, "Enter name", Toast.LENGTH_SHORT).show();
                else if(emailAddress.equals(""))
                    Toast.makeText(SignUpActivity.this, "Enter email", Toast.LENGTH_SHORT).show();
                else if(!isValidEmail(emailAddress))
                    Toast.makeText(SignUpActivity.this, "Enter valid email", Toast.LENGTH_SHORT).show();
                else if(password.equals(""))
                    Toast.makeText(SignUpActivity.this, "Enter password", Toast.LENGTH_SHORT).show();
                else if(repassword.equals(""))
                    Toast.makeText(SignUpActivity.this, "Re-Type password", Toast.LENGTH_SHORT).show();
                else if(!password.equals(repassword))
                    Toast.makeText(SignUpActivity.this, "Re-typed password does not match password", Toast.LENGTH_SHORT).show();
                else{
                    Intent intent = new Intent(SignUpActivity.this, GetStartedActivity.class);
                    startActivity(intent);
                }
            }
        });

        signUp.startAnimation(myAnim);
    }
}
