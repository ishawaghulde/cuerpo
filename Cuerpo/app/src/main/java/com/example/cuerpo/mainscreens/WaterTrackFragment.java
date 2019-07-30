package com.example.cuerpo.mainscreens;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.drawable.ClipDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.cuerpo.R;

public class WaterTrackFragment extends Fragment {

    private static final String WATER = "water";
    private int mLevel;
    private int inLevel;
    ClipDrawable mImageDrawable;
    int step;
    int small_step;
    private TextView textView;
    private int glassOfWater;
    private int water;
    private int water_drank;
    private Handler mHandler;
    public static final String MY_PREFS_NAME = "MyPrefsFile";
    SharedPreferences.Editor editor;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.water_track_fragment,container,false);

        SharedPreferences prefs = this.getActivity().getSharedPreferences(MY_PREFS_NAME, Context.MODE_PRIVATE);
        editor = this.getActivity().getSharedPreferences(MY_PREFS_NAME, Context.MODE_PRIVATE).edit();

        glassOfWater = prefs.getInt("water", 0);
        water_drank = prefs.getInt("water_drank", 0);


//        glassOfWater = getArguments().getInt(WATER);
        water = glassOfWater - water_drank;

        ImageView img = view.findViewById(R.id.imageView1);

        mImageDrawable = (ClipDrawable) img.getDrawable();
        inLevel = img.getDrawable().getLevel();
        mImageDrawable.setLevel(inLevel);
        step = (10000-mLevel)/(glassOfWater+1);
        small_step = step/10;
        inLevel = mLevel;
        Button add_button = view.findViewById(R.id.layout);
        textView = view.findViewById(R.id.textView);
        String text = "You have only " + (glassOfWater - water_drank) + " glasses left!";
        textView.setText(text);
        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                mLevel += step;
                if( (mLevel<= glassOfWater*step) && (water > 0)) {
                    mHandler = new Handler();
                    mHandler.post(animateImage);
//                    mImageDrawable.setLevel(mLevel)
                    water -= 1;
                    if (water != 0) {
                        String text = "You have only " + water + " glasses left!";
                        textView.setText(text);
                        editor.putInt("water_drank", glassOfWater-water);
                        editor.apply();
                    } else {
                        String text = "Bravo! You completed your daily quota.";
                        textView.setText(text);
                        editor.putInt("water_drank", glassOfWater);
                        editor.apply();
                    }
                }
                else{
                    water++;
                    String text = "You have " + water + " glasses in excess!";
                    textView.setText(text);
                    editor.putInt("water_drank", glassOfWater + water);
                    editor.apply();
                }


            }
        });

        return view;
    }

    private Runnable animateImage = new Runnable() {

        @Override
        public void run() {
            doTheAnimation();
        }
    };

    private void doTheAnimation() {
        int nLevel = mImageDrawable.getLevel() + small_step;
        mImageDrawable.setLevel(nLevel);
        if ( mImageDrawable.getLevel() <= mLevel) {
            mHandler.postDelayed(animateImage, 50);
        } else {
            mHandler.removeCallbacks(animateImage);
        }
    }
}
