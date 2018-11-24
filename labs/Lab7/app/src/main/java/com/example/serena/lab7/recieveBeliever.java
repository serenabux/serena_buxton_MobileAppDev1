package com.example.serena.lab7;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class recieveBeliever extends Activity {

    private String santaMessage;
    private  String santaURL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recieve_believer);
        Intent intent = getIntent();
        santaMessage = intent.getStringExtra("message");
        santaURL = intent.getStringExtra("santaURL");
        TextView messageView = findViewById(R.id.textView2);
        messageView.setText(santaMessage);

        final ImageButton imageButton = findViewById(R.id.imageButton);

        View.OnClickListener onclick = new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                loadWebSite(v);
            }
        };

        imageButton.setOnClickListener(onclick);
    }

    public void loadWebSite(View view){
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(santaURL));
        startActivity(intent);
    }



}
