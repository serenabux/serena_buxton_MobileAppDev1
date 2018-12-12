package com.example.serena.timer;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Switch;

public class Settings extends Activity {

    private Switch coolSteepSwitch;
    private Switch coolDrinkSwitch;
    private Spinner drinkTempSpinner;
    private Button tmpButton;
    private int drinkTemp;

    TeaSettings objTeaSet = new TeaSettings();

    Intent resultIntent = new Intent();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        coolSteepSwitch = findViewById(R.id.switchSteepTemp);
        coolDrinkSwitch = findViewById(R.id.switchDrinkTemp);
        drinkTempSpinner = findViewById(R.id.spinnerDrinkTemps);

        tmpButton = findViewById(R.id.button);

        coolSteepSwitch.setChecked(objTeaSet.isCoolToSteep());
        coolDrinkSwitch.setChecked(objTeaSet.isCoolToDesired());
        drinkTemp = objTeaSet.getDesiredDrinkTemp();
        switch (drinkTemp){
            case(180): drinkTempSpinner.setSelection(0);
            case(170):drinkTempSpinner.setSelection(1);
            case(160):drinkTempSpinner.setSelection(2);
            case(150):drinkTempSpinner.setSelection(3);
            case(140):drinkTempSpinner.setSelection(4);
            case(130):drinkTempSpinner.setSelection(5);
            case(120):drinkTempSpinner.setSelection(6);
            case(110):drinkTempSpinner.setSelection(7);
            case(100):drinkTempSpinner.setSelection(8);
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
                }

                resultIntent.putExtra("desiredDrinkTemp", drinkTemp);
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


    }

    @Override
    protected void onPause(){
        Log.i("stop", "stopping");
        finish();
        super.onPause();
    }
}
