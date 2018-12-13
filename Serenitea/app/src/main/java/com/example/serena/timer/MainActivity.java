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
    private String stageDescription;

    private int stage;
    private int tea;
    private int buttonState; //0 if begin, 1 if stop, 2 if okay, 3 if make another
    private int change;

    //Reference material for timer:
    //https://www.youtube.com/watch?v=zmjfAcnosS0
    //https://www.youtube.com/watch?v=SaTx-gLLxWQ
    //https://developer.android.com/reference/java/util/Timer
    private CountDownTimer countDownTimer;
    private final int convertToMilli = 60000;
    private double totTime;
    private long timeLeftInMilliseconds;
    private double progress;
    private  boolean timeRunning;

    TeaSettings objTeaSet = new TeaSettings();
    private int desiredDrinkTemp;
    private boolean coolToSteep ;
    private boolean coolToDesired ;
    private boolean endOfStage;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        stage=0;
        tea = 0;
        buttonState = 0;
        progress = 100;
        desiredDrinkTemp = 140;
        coolToSteep = true;
        coolToDesired = true;
        change = 0;
        endOfStage = false;

        countdownText = findViewById(R.id.countDownText);
        countdownButton = findViewById(R.id.countdownButton);
        progressBarTimer = findViewById(R.id.progressBar);
        teaTypeSpinner = findViewById(R.id.spinner);
        stageText = findViewById(R.id.stageTextView);
        callSettingsButton = findViewById(R.id.settingsButton);
        mediaPlayer = MediaPlayer.create(this, R.raw.alarm);


        //makes changes automatically when spinner state changes
        teaTypeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //change timer settings
                //Upon rotating it calls this twice and resets it so this is my hacky fix, I am sure there is a better way to do this
                if(timeRunning && change!=0) {
                    //if running and rotate dont want to stop timer
                    return;
                };
                if(change != 0){
                    if(endOfStage) change = change+1;
                    else change = (change+1)%3;
                    return;
                }
                //if it is running want to stop it
                if(timeRunning) {
                    startStop();
                    progress = 0;
                }
                switch (position){

                    case 0: {
                        //Need to cool from boil to steeping temperature for first time it changed
                        tea = 0;
                        if(coolToSteep) stage = 0;
                        else stage = 1;
                        Log.i("test","here");
                        setTeaInfo();

                        break;
                    }
                    case 1: {
                        stage = 1;
                        tea = 1;
                        setTeaInfo();
                        break;
                    }
                    case 2: {
                        stage = 1;
                        tea = 2;
                        setTeaInfo();
                        break;
                    }
                    default:{
                        if(coolToSteep) stage = 0;
                        else stage =1;
                        tea = 3;
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
                switch(buttonState){
                    case(0):{ //start
                        startStop();
                        break;
                    }
                    case(1):{ //stop
                        startStop();
                        progress = 0;
                        break;
                    }
                    case(2):{ //okay
                        buttonState = 0;
                        countdownButton.setText("Start");
                        Log.i("onclicklistener","set button state to 0");
                        //attempt at fixing why it would not replay at start when it is completely played
                        //mediaPlayer.stop();
                        endOfStage = false;
                        mediaPlayer.pause();
                        mediaPlayer.seekTo(0);
                        stage = (stage + 1)%3;
                        setTeaInfo();
                        break;
                    }
                    case (3):{ //make another?
                        stage = 0;
                        buttonState = 0;
                        countdownButton.setText("Start");
                        setTeaInfo();
                        break;

                    }
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
            tea = savedInstanceState.getInt("teaType");
            timeRunning = savedInstanceState.getBoolean("timeRun");
            coolToDesired = savedInstanceState.getBoolean("coolDesired");
            coolToSteep = savedInstanceState.getBoolean("coolSteep");
            desiredDrinkTemp = savedInstanceState.getInt("temp");
            endOfStage = savedInstanceState.getBoolean("endStage");

            progressBarTimer.setProgress((int)progress);
            prettyPrintTime();
            if (timeRunning) {
                //if time is running start the timer
                startTimer();
            }
            else if (!endOfStage){
                //if it is not the end of the stage it is the beginning so set all the info
                setTeaInfo();
            }
            else{
                //it is the end so the progress should be 0
                progressBarTimer.setProgress(0);
                countdownText.setText("0:00");
                buttonState = 2;
                countdownButton.setText("Okay");
            }
            //set change which is used to stop the on spinner change call
            change = 1;
            teaTypeSpinner.setSelection(tea);
            printStageDescription();


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
        outState.putInt("teaType",tea);
       outState.putBoolean("coolSteep",coolToSteep);
       outState.putBoolean("coolDesired",coolToDesired);
       outState.putInt("temp",desiredDrinkTemp);
       outState.putBoolean("endStage",endOfStage);

        super.onSaveInstanceState(outState);
    }


    //Additional resource for passing data back from an intent
    //https://developer.android.com/reference/android/app/Activity
    //https://developer.android.com/training/basics/intents/result#java
    //This is called when the back button is used from settings

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.i("onActivityResult", "here");
        Log.i("onActivityResult", Integer.toString(requestCode));
        if (requestCode == 1) { // Please, use a final int instead of hardcoded int value
            Log.i("onActivityResult", Integer.toString(resultCode));
                desiredDrinkTemp = data.getExtras().getInt("desiredDrinkTemp");
                Log.i("onActivityResult", Integer.toString(desiredDrinkTemp));
                coolToDesired = data.getExtras().getBoolean("coolDesire");
                Log.i("onActivityResult",Boolean.toString(coolToDesired));
                coolToSteep = data.getExtras().getBoolean("coolSteep");
                Log.i("onActivityResult",Boolean.toString(coolToSteep));
                setTeaInfo();

        }
    }

    public void callIntent(){
        Intent intent = new Intent(this, Settings.class);
        intent.putExtra("desiredDrinkTemp", desiredDrinkTemp);
        intent.putExtra("coolToSteep", coolToSteep);
        intent.putExtra("coolToDesired", coolToDesired);
        startActivityForResult(intent,1);
    }

    //Calculate the cooling time based on the desired temp and the time taken so far
    public double coolingTime( double overallTime){
        //Newton's law of cooling, using a k value of -7.64563E-7, answer is in milliseconds so no conversion is needed
        Log.i("main", Integer.toString(desiredDrinkTemp));
        double tmp = 1.0000/(desiredDrinkTemp-72);
        double time = 1307940*(Math.log(1.00000/(desiredDrinkTemp-72))+4.94164);
        double cooltime = time - overallTime;
        if (cooltime <= 0)  cooltime=0;
        return cooltime;
    }

    //used for starting/ stopping timer
    public void startStop(){
        if (timeRunning){
            stopTimer();
        }
        else{
            startTimer();

        }
    }

    //used to set and load the information for the specific tea based on tea type and stage
    public void setTeaInfo(){
        if(!coolToDesired && stage==2){
            setEnjoy();
            stage = 0;
            return;
        }
        if(stage == 3) {
            setEnjoy();
            return;
        }
        switch (tea){
            //green tea
            case(0):{
                if(!coolToSteep && stage==0) stage = 1;
                switch(stage){
                    case(0):{
                        //Cool from boiling for 3 minutes
                        totTime = 180000;
                        timeLeftInMilliseconds = 180000; //3 min
                        progressBarTimer.setProgress(100);
                        countdownText.setText("3:00");
                        break;

                    }
                    case(1):{
                        //Steep for 3 minutes
                        totTime = 180000;
                        timeLeftInMilliseconds = 180000; //3 min
                        progressBarTimer.setProgress(100);
                        countdownText.setText("3:00");
                        break;
                    }
                    case(2):{
                        //Get the cool time for the desired drink temp
                        totTime = coolingTime( 360000);
                        timeLeftInMilliseconds = (long)totTime;
                        progressBarTimer.setProgress(100);
                        prettyPrintTime();
                        break;
                    }
                }
                break;

            }
            //black tea
            case (1):{
                switch(stage){
                    case (0):{
                        //black tea doesnt cool to steep so fall through
                        stage = 1;
                    }
                    case(1):{
                        totTime = 300000;
                        timeLeftInMilliseconds = 300000; //3 min
                        progressBarTimer.setProgress(100);
                        countdownText.setText("5:00");
                        break;
                    }
                    case(2):{
                        //Get the cool time for the desired drink temp
                        totTime = coolingTime( 300000);
                        timeLeftInMilliseconds = (long)totTime;
                        progressBarTimer.setProgress(100);
                        prettyPrintTime();
                        break;
                    }
                }
                break;
            }
            //herbal tea
            case (2):{
                switch(stage){
                    case(0):{
                        //herbal tea doesn't cool to steep so fall through
                        stage = 1;
                    }
                    case(1):{
                        totTime = 420000;
                        timeLeftInMilliseconds = 420000;
                        progressBarTimer.setProgress(100);
                        countdownText.setText("7:00");
                        break;
                    }
                    case(2):{
                        //Get the cool time for the desired drink temp
                        totTime = coolingTime(420000);
                        timeLeftInMilliseconds = (long)totTime;
                        progressBarTimer.setProgress(100);
                        prettyPrintTime();
                        break;
                    }
                }
                break;
            }
            //demo version
            default:{
                if(!coolToSteep && stage==0) stage = 1;
                switch(stage){
                    case(0):{
                        totTime = 5000;
                        timeLeftInMilliseconds = 5000; //3 min
                        progressBarTimer.setProgress(100);
                        countdownText.setText("0:05");
                        break;
                        }
                    case(1):{
                        totTime = 4000;
                        timeLeftInMilliseconds = 4000; //3 min
                        progressBarTimer.setProgress(100);
                        countdownText.setText("0:04");
                        break;
                        }
                    case(2):{
                        //Get the cool time for the desired drink temp
                        totTime = coolingTime(14000);
                        timeLeftInMilliseconds = (long)totTime;
                        progressBarTimer.setProgress(100);
                        countdownText.setText(Double.toString(totTime));
                        prettyPrintTime();
                        break;
                    }
                }

            }
        }
        printStageDescription();

    }

    //start the timer
    public void startTimer(){
    countDownTimer = new CountDownTimer(timeLeftInMilliseconds, 1000) {
        @Override
        public void onTick(long millisUntilFinished) {
            timeLeftInMilliseconds = millisUntilFinished;

            updateTimer();
        }
//At the end of the timer
        @Override
        public void onFinish() {
            countdownButton.setText("Okay");
            countdownText.setText("0:00");
            progressBarTimer.setProgress(0);
            progress = 0;
            //timeLeftInMilliseconds = 0;
            Log.i("test", Integer.toString(stage));
            endOfStage = true;
            printStageDescription();
            //reset the media
            try {
                // Code that might throw
                // an exception.
                mediaPlayer.prepare();
            } catch (Exception e) {
                // Handle it.
                Log.i("MediaError","Cannot reprepare sound");
            }


            mediaPlayer.start();
            timeRunning = false;
            buttonState = 2;

        }
    }.start();
    countdownButton.setText("Stop");
    Log.i("stopTimer","set button state to 0");
    buttonState = 1;
    timeRunning = true;
    }

    //cancel the timer and reset to initial stage
    public void stopTimer(){
        countDownTimer.cancel();
        timeRunning = false;
        countdownButton.setText("Start");
        //reset the timer
        Log.i("stopTimer","set button state to 0");
        buttonState = 0;
        stage = 0;
        progress = 100;
        setTeaInfo();
    }
    public void updateTimer() {
        prettyPrintTime();
        progress = (double)timeLeftInMilliseconds/totTime * 100;
        Log.i("progress",Double.toString(progress));
        progressBarTimer.setProgress((int)progress);

    }

    //print the time in minutes/ seconds as opposed to milliseconds
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

    //print the specific stage description
    public void printStageDescription(){
        if(endOfStage){
            switch (stage){
                case (0):{
                    stageDescription = "Cooled To Steep Temperature";
                    break;
                }
                case(1):{
                    stageDescription = "Done Steeping";
                    break;
                }
                case(2):{
                    setEnjoy();
                    break;
                }
            }
        }
        else if (timeRunning){
            switch (stage){
                case (0):{
                    stageDescription = "Cooling To Steep Temperature";
                    break;
                }
                case(1):{
                    stageDescription = "Steeping";
                    break;
                }
                case(2):{
                    stageDescription = "Cooling to Desired Temperature";
                    break;
                }
                case(3):{
                    setEnjoy();
                    stage = 0;
                    break;
                }
            }
        }
        else{
            switch (stage){
                case (0):{
                    stageDescription = "Cool To Steep Temperature";
                    break;
                }
                case(1):{
                    stageDescription = "Steep";
                    break;
                }
                case(2):{
                    stageDescription = "Cool To Desired Temperature";
                    break;
                }
                case(3):{
                    setEnjoy();
                    stage = 0;
                    break;
                }
            }
        }

        stageText.setText(stageDescription);
    }

    //Final stage settings
    public void setEnjoy(){
        stageDescription = "Enjoy!";
        stageText.setText(stageDescription);
        buttonState = 3;
        Log.i("setEnjoy", "set button to 3");
        countdownButton.setText("Make Another?");
    }

}


