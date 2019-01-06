package com.example.oasis.tictactoe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void dropIn(View view){
        // get the tapped placeholder
        ImageView counter = (ImageView) view;
        //Hide the image off the screen
        counter.setTranslationY(-1500);
        counter.setImageResource(R.drawable.yellow);
        //bring the image from the top of the screen rotating it
        counter.animate().translationYBy(1500).rotation(3600).setDuration(500);
    }
}
