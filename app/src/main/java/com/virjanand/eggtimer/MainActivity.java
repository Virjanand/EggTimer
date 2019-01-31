package com.virjanand.eggtimer;

import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    private TextView countDownText;
    private MediaPlayer airhorn;

    public void startCountDown(View view) {
        new CountDownTimer(10000, 1000) {
            public void onTick(long milliSecondsUntilDone) {
                updateCountDownText(milliSecondsUntilDone);
            }
            public void onFinish() {
                updateCountDownText(0);
                airhorn.start();
            }
        }.start();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        countDownText = (TextView) findViewById(R.id.countdownTextView);
        airhorn = MediaPlayer.create(this, R.raw.airhorn);
    }

    private void updateCountDownText(long milliSecondsUntilDone) {
        long minutes = milliSecondsUntilDone / 1000 / 60;
        long seconds = milliSecondsUntilDone / 1000 % 60;
        countDownText.setText(String.format("%02d:%02d",minutes,seconds ));
    }
}
