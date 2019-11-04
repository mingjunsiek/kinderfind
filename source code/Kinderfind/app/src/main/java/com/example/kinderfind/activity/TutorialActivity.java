package com.example.kinderfind.activity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;

import com.example.kinderfind.R;
import com.example.kinderfind.adapters.TutorialAdapter;

import java.util.ArrayList;

import me.relex.circleindicator.CircleIndicator;

public class TutorialActivity extends AppCompatActivity {

    private static ViewPager mPager;
    private static int currentPage = 0;
    private static final Integer[] img = {R.drawable.tutorial_1,R.drawable.tutorial_2,R.drawable.tutorial_3,R.drawable.tutorial_4};
    private ArrayList<Integer> ImgArray = new ArrayList<Integer>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tutorial_interface);

        View decorView = getWindow().getDecorView();
        // Hide the status bar.
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);
        // Hide action bar.
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        Button skipBtn = findViewById(R.id.skipBtn);
        skipBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TutorialActivity.this, MapsActivity.class);
                startActivity(intent);
                finish();
            }
        });

        init();
    }

    private void init (){
        for (int i=0; i<img.length; i++)
            ImgArray.add(img[i]);

        mPager = (ViewPager) findViewById(R.id.pager);
        mPager.setAdapter(new TutorialAdapter(TutorialActivity.this, ImgArray));
        CircleIndicator indicator = findViewById(R.id.indicator);
        indicator.setViewPager(mPager);

        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            @Override
            public void run() {
                if (currentPage == img.length){
                    currentPage = 0;
                }
                mPager.setCurrentItem(currentPage++, true);
            }
        };
    }
}
