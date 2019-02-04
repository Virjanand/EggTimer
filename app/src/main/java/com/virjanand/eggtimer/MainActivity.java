package com.virjanand.eggtimer;

import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    public static final int SECOND = 1000;
    private TextView countDownText;
    private MediaPlayer airhorn;
    private int timeInMilliSeconds;
    private SeekBar timeSeekBar;

    public void startCountDown(View view) {
        timeSeekBar.setVisibility(View.INVISIBLE);
        final Button startTimerButton = (Button) findViewById(R.id.startCountdownButton);
        startTimerButton.setClickable(false);
        new CountDownTimer(timeInMilliSeconds, SECOND) {
            public void onTick(long milliSecondsUntilDone) {
                updateCountDownText(milliSecondsUntilDone);
            }
            public void onFinish() {
                updateCountDownText(0);
                airhorn.start();
                timeSeekBar.setVisibility(View.VISIBLE);
                startTimerButton.setClickable(true);
            }
        }.start();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        countDownText = (TextView) findViewById(R.id.countdownTextView);
        updateCountDownText(0);
        airhorn = MediaPlayer.create(this, R.raw.airhorn);

        timeSeekBar = findViewById(R.id.timeSeekBar);
        timeSeekBar.setMax(300);
        timeSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                timeInMilliSeconds = progress * SECOND;
                updateCountDownText(timeInMilliSeconds);
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
    }

    private void updateCountDownText(long milliSecondsUntilDone) {
        long minutes = milliSecondsUntilDone / 1000 / 60;
        long seconds = milliSecondsUntilDone / 1000 % 60;
        countDownText.setText(String.format("%02d:%02d",minutes,seconds ));
    }
}
