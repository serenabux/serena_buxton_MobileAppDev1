package com.example.serena.lab7;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.ToggleButton;

public class setInfo extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button button = findViewById(R.id.button);

        View.OnClickListener onclick = new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                checkIfBeliever(v);
            }
        };
        button.setOnClickListener(onclick);
    }

    private Santa mySanta = new Santa();

    public void checkIfBeliever (View view){
        ToggleButton santaButton = findViewById(R.id.toggleButton);
        Boolean believer = santaButton.isChecked();
        EditText textName = findViewById((R.id.editText));
        String name = textName.getText().toString();
        mySanta.setMessage(believer,name);
        String message = mySanta.getMessage();
        String sURl = mySanta.getSantaURL();

        Log.i("message: ",message);
        Log.i("url:", sURl);

        Intent intent = new Intent(this, recieveBeliever.class);
        intent.putExtra("message",message);
        intent.putExtra("santaURL", sURl);

        startActivity(intent);

    }
}
