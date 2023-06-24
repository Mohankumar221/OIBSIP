
package com.example.stopwatch;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView time;
    private int seconds = 0;
    private boolean running;
    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        time = findViewById(R.id.time);
        if (savedInstanceState != null) {
            seconds = savedInstanceState.getInt("seconds");
            running = savedInstanceState.getBoolean("running");
        }
        runTimer();

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("seconds", seconds);
        outState.putBoolean("running", running);
    }

    @Override
    protected void onPause() {
        super.onPause();
        running = false;
        handler.removeCallbacksAndMessages(null);
    }

    public void onClickStart(View view) {
        running = true;
    }

    public void onClickStop(View view) {
        running = false;
    }

    public void onClickReset(View view) {
        running = false;
        seconds = 0;
        updateTimerText();
    }

    private void runTimer() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (running) {
                    seconds++;
                    updateTimerText();
                }
                handler.postDelayed(this, 1000);
            }
        }, 1000);
    }

    private void updateTimerText() {
        int hours = seconds / 3600;
        int minutes = (seconds % 3600) / 60;
        int secs = seconds % 60;

        TextView timeView = findViewById(R.id.time);
        String stime = String.format("%d:%02d:%02d", hours, minutes, secs);
        timeView.setText(stime);
    }
}