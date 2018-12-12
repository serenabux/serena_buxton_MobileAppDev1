package com.example.serena.timer;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends Activity {
    private TextView countdownText;
    private Button countdownButton;
    private  ProgressBar progressBarTimer;
    private Spinner teaTypeSpinner;
    private TextView stageText;
    private ImageButton callSettingsButton;
    //Reference for using media player: https://developer.android.com/guide/topics/media/mediaplayer
    private  MediaPlayer mediaPlayer;

    private int stage=0;
    private int tea = 1;
    private int buttonState = 0 ; //0 if begin, 1 if stop, 2 if okay, 3 if make another

    //Reference material for timer:
    //https://www.youtube.com/watch?v=zmjfAcnosS0
    //https://www.youtube.com/watch?v=SaTx-gLLxWQ
    //https://developer.android.com/reference/java/util/Timer
    private CountDownTimer countDownTimer;
    private final int convertToMilli = 60000;
    private double totTime;
    private long timeLeftInMilliseconds = 600000; //10 min
    private double progress = 100;
    private  boolean timeRunning;

    TeaSettings objTeaSet = new TeaSettings();
    private int desiredDrinkTemp = 140;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        countdownText = findViewById(R.id.countDownText);
        countdownButton = findViewById(R.id.countdownButton);
        progressBarTimer = findViewById(R.id.progressBar);
        teaTypeSpinner = findViewById(R.id.spinner);
        stageText = findViewById(R.id.stageTextView);
        callSettingsButton = findViewById(R.id.settingsButton);
        mediaPlayer = MediaPlayer.create(this, R.raw.alarm);



        teaTypeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //change timer settings
                if(timeRunning) return;
                switch (position){

                    case 0: {
                        //Need to cool from boil to steeping temperature for first time it changed
                        tea = 1;
                        Log.i("test","here");
                        setTeaInfo();

                        break;
                    }
                    case 1: {
                        stage = 1;
                        tea = 2;
                        setTeaInfo();
                        break;
                    }
                    case 2: {
                        stage = 1;
                        tea = 3;
                        setTeaInfo();
                        break;
                    }
                    default:{
                        stage = 0;
                        tea = 4;
                        setTeaInfo();
                        break;
                    }
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                //Another interface callback
            }
        });


        countdownButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (buttonState == 0 || buttonState == 1){
                    startStop();
                }
                else if (buttonState == 2){
                    setTeaInfo();
                    countdownButton.setText("Start");
                    mediaPlayer.stop();
                    buttonState = 0;
                }



            }
        });

        callSettingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callIntent();
            }
        });


        // check for recovering the instance state
        if (savedInstanceState !=null){
            totTime = savedInstanceState.getDouble("totalTime");
            timeLeftInMilliseconds = savedInstanceState.getLong("timeRemain");
            progress = savedInstanceState.getDouble("progress");
            stage = savedInstanceState.getInt("stage");
            timeRunning = savedInstanceState.getBoolean("timeRun");

            progressBarTimer.setProgress((int)progress);
            prettyPrintTime();

            startTimer();

        }
    }

    // invoked when the activity may be temporarily destroyed, save the instance state here
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putDouble("totalTime", totTime);
        outState.putLong("timeRemain", timeLeftInMilliseconds);
        outState.putDouble("progress", progress);
        outState.putInt("stage", stage);
        outState.putBoolean("timeRun", timeRunning);
        super.onSaveInstanceState(outState);
    }


    //Additional resource for passing data back from an intent
    //https://developer.android.com/reference/android/app/Activity
    //https://developer.android.com/training/basics/intents/result#java

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.i("onActivityResult", "here");
        Log.i("onActivityResult", Integer.toString(requestCode));
        if (requestCode == 1) { // Please, use a final int instead of hardcoded int value
            Log.i("onActivityResult", Integer.toString(resultCode));
            //if (resultCode == 1) {
                desiredDrinkTemp = data.getExtras().getInt("desiredDrinkTemp");
                Log.i("onActivityResult", Integer.toString(desiredDrinkTemp));
            //}
        }
    }

    public void callIntent(){
        Intent intent = new Intent(this, Settings.class);
        startActivityForResult(intent,1);
    }
    public double coolingTime( double overallTime){
        //Newton's law of cooling, using a k value of -7.64563E-7, answer is in milliseconds so no conversion is needed
        Log.i("main", Integer.toString(desiredDrinkTemp));
        double tmp = 1.0000/(desiredDrinkTemp-72);
        double time = 1307940*(Math.log(1.00000/(desiredDrinkTemp-72))+4.94164);
        double cooltime = time - overallTime;
        if (cooltime <= 0)  cooltime=0;
        return cooltime;
    }

    public void startStop(){
        if (timeRunning){
            stopTimer();
        }
        else{
            startTimer();

        }
    }

    public void setTeaInfo(){
        switch (tea){
            //green tea
            case(1):{
                switch(stage){
                    case(0):{
                        //Cool from boiling for 3 minutes
                        totTime = 180000;
                        timeLeftInMilliseconds = 180000; //3 min
                        progressBarTimer.setProgress(100);
                        stageText.setText("Cool to Steep Temp");
                        countdownText.setText("3:00");
                        break;
                    }
                    case(1):{
                        //Steep for 3 minutes
                        totTime = 180000;
                        timeLeftInMilliseconds = 180000; //3 min
                        progressBarTimer.setProgress(100);
                        stageText.setText("Steep");
                        countdownText.setText("3:00");
                        break;
                    }
                    case(2):{
                        //Get the cool time for the desired drink temp
                        totTime = coolingTime( 360000);
                        timeLeftInMilliseconds = (long)totTime;
                        progressBarTimer.setProgress(100);
                        stageText.setText("Cool to Desired Drink Temp");
                        prettyPrintTime();
                        break;
                    }
                }
                break;

            }
            //black tea
            case (2):{
                switch(stage){
                    case (0):{
                        stage = 1;
                    }
                    case(1):{
                        totTime = 300000;
                        timeLeftInMilliseconds = 300000; //3 min
                        progressBarTimer.setProgress(100);
                        stageText.setText("Steep");
                        countdownText.setText("5:00");
                        break;
                    }
                    case(2):{
                        //Get the cool time for the desired drink temp
                        totTime = coolingTime( 300000);
                        timeLeftInMilliseconds = (long)totTime;
                        progressBarTimer.setProgress(100);
                        stageText.setText("Cool to Desired Drink Temp");
                        prettyPrintTime();
                        break;
                    }
                }
                break;
            }
            //herbal tea
            case (3):{
                switch(stage){
                    case(0):{
                        stage = 1;
                    }
                    case(1):{
                        totTime = 420000;
                        timeLeftInMilliseconds = 420000;
                        progressBarTimer.setProgress(100);
                        stageText.setText("Steep");
                        countdownText.setText("7:00");
                        break;
                    }
                    case(2):{
                        //Get the cool time for the desired drink temp
                        totTime = coolingTime(420000);
                        timeLeftInMilliseconds = (long)totTime;
                        progressBarTimer.setProgress(100);
                        stageText.setText("Cool to Desired Drink Temp");
                        prettyPrintTime();
                        break;
                    }
                }
                break;
            }
            //demo version
            default:{
                switch(stage){
                    case(0):{
                        totTime = 5000;
                        timeLeftInMilliseconds = 5000; //3 min
                        progressBarTimer.setProgress(100);
                        stageText.setText("Cool to Steep Temp");
                        countdownText.setText("0:05");
                        break;
                        }
                    case(1):{
                        totTime = 4000;
                        timeLeftInMilliseconds = 4000; //3 min
                        progressBarTimer.setProgress(100);
                        stageText.setText("Steep");
                        countdownText.setText("0:04");
                        break;
                        }
                    case(2):{
                        //Get the cool time for the desired drink temp
                        totTime = coolingTime(14000);
                        timeLeftInMilliseconds = (long)totTime;
                        progressBarTimer.setProgress(100);
                        stageText.setText("Cool to Desired Drink Temp");
                        countdownText.setText(Double.toString(totTime));
                        prettyPrintTime();
                        break;
                    }
                }

            }
        }

    }

    public void startTimer(){
    countDownTimer = new CountDownTimer(timeLeftInMilliseconds, 1000) {
        @Override
        public void onTick(long millisUntilFinished) {
            timeLeftInMilliseconds = millisUntilFinished;

            updateTimer();
        }

        @Override
        public void onFinish() {
            countdownButton.setText("Okay");
            countdownText.setText("0:00");
            progressBarTimer.setProgress(0);
            Log.i("test", Integer.toString(stage));
            switch (stage){
                case (0):{
                    stageText.setText("Cooled To Steep Temperature");
                    break;
                }
                case(1):{
                    stageText.setText("Done steeping");
                    break;
                }
                case(2):{
                    stageText.setText("Enjoy!");
                    break;
                }
            }

            mediaPlayer.start();
            stage = (stage + 1) % 3;
            timeRunning = false;
            buttonState = 2;

        }
    }.start();
    countdownButton.setText("Stop");
    buttonState = 1;
    timeRunning = true;
    }

    public void stopTimer(){
        countDownTimer.cancel();
        timeRunning = false;
        countdownButton.setText("Start");
        //reset the timer
        buttonState = 0;
        stage = 0;
        setTeaInfo();
    }
    public void updateTimer() {
        prettyPrintTime();
        progress = (double)timeLeftInMilliseconds/totTime * 100;
        Log.i("progress",Double.toString(progress));
        progressBarTimer.setProgress((int)progress);

    }

    public void prettyPrintTime(){
        int minutes = (int) timeLeftInMilliseconds / convertToMilli;
        int seconds = (int) timeLeftInMilliseconds % convertToMilli / 1000;
        String timeLeftText;
        timeLeftText = ""+minutes;
        timeLeftText += ":";
        if (seconds < 10) timeLeftText += "0";
        timeLeftText += seconds;
        countdownText.setText(timeLeftText);
    }

}


