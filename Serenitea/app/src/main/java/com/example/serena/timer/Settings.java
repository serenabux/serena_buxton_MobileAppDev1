package com.example.serena.timer;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Spinner;
import android.widget.Switch;

public class Settings extends Activity {

    private Switch coolSteepSwitch;
    private Switch coolDrinkSwitch;
    private Spinner drinkTempSpinner;
    private int drinkTemp;
    private boolean steep;
    private boolean desiredTemp;

    TeaSettings objTeaSet = new TeaSettings();


    Intent resultIntent = new Intent();




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        Intent recieveIntent = getIntent();
        drinkTemp = recieveIntent.getIntExtra("desiredDrinkTemp", 140);
        Log.i("drinkTemp", Integer.toString(drinkTemp));
        steep = recieveIntent.getBooleanExtra("coolToSteep", true);
        desiredTemp = recieveIntent.getBooleanExtra("coolToDesired",true);




        coolSteepSwitch = findViewById(R.id.switchSteepTemp);
        coolDrinkSwitch = findViewById(R.id.switchDrinkTemp);
        drinkTempSpinner = findViewById(R.id.spinnerDrinkTemps);



        coolSteepSwitch.setChecked(steep);

        coolDrinkSwitch.setChecked(desiredTemp);

        Log.i("drinkTemp", Integer.toString(drinkTemp));
        switch (drinkTemp){
            case(180): {
                drinkTempSpinner.setSelection(0);
                break;
            }
            case(170):{
                drinkTempSpinner.setSelection(1);
                break;
            }
            case(160):{
                drinkTempSpinner.setSelection(2);
                break;
            }
            case(150):{
                drinkTempSpinner.setSelection(3);
                break;
            }
            case(140):{
                drinkTempSpinner.setSelection(4);
                break;
            }
            case(130):{
                drinkTempSpinner.setSelection(5);
                break;
            }
            case(120):{
                drinkTempSpinner.setSelection(6);
                break;
            }
            case(110):{
                drinkTempSpinner.setSelection(7);
                break;
            }
            case(100):{
                drinkTempSpinner.setSelection(8);
                break;
            }
            case(210):{
                drinkTempSpinner.setSelection(9);
                break;
            }
            default:drinkTempSpinner.setSelection(4);
        }

        drinkTempSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case(0):{
                        drinkTemp = 180;
                        break;
                    }
                    case(1):{
                        drinkTemp = 170;
                        break;
                    }
                    case(2):{
                        drinkTemp = 160;
                        break;
                    }
                    case(3):{
                        drinkTemp = 150;
                        break;
                    }
                    case(4):{
                        drinkTemp = 140;
                        break;
                    }
                    case(5):{
                        drinkTemp = 130;
                        break;
                    }
                    case(6):{
                        drinkTemp = 120;
                        break;
                    }
                    case(7):{
                        drinkTemp = 110;
                        break;
                    }
                    case(8):{
                        drinkTemp = 100;
                        break;
                    }
                    case(9):{
                        drinkTemp = 210;
                        break;
                    }
                }

                resultIntent.putExtra("desiredDrinkTemp", drinkTemp);
                resultIntent.putExtra("coolDesire",desiredTemp);
                resultIntent.putExtra("coolSteep", steep);
                setResult(Activity.RESULT_OK, resultIntent);
               // objTeaSet.setDesiredDrinkTemp(drinkTemp);
                //Log.i("setSettings", Integer.toString(drinkTemp));
                //Log.i("getSettings", Integer.toString(objTeaSet.getDesiredDrinkTemp()));

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                //Another interface callback
            }
        });

    coolSteepSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            steep = isChecked;
            objTeaSet.setCoolToSteep(steep);
            resultIntent.putExtra("desiredDrinkTemp", drinkTemp);
            resultIntent.putExtra("coolDesire",desiredTemp);
            resultIntent.putExtra("coolSteep", steep);
            setResult(Activity.RESULT_OK, resultIntent);
        }
    });

    coolDrinkSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            desiredTemp = isChecked;
            objTeaSet.setCoolToDesired(desiredTemp);
            resultIntent.putExtra("desiredDrinkTemp", drinkTemp);
            resultIntent.putExtra("coolDesire",desiredTemp);
            resultIntent.putExtra("coolSteep", steep);
            setResult(Activity.RESULT_OK, resultIntent);
        }
    });

    }

    @Override
    protected void onPause(){
        Log.i("stop", "stopping");
        finish();
        super.onPause();
    }
}
