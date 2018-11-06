package com.example.serena.lab5;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void setMessage(View view) {
        TextView text = findViewById(R.id.message);
        EditText name = findViewById(R.id.editText);
        String nameValue = name.getText().toString();
        text.setText("Happy Fall "+nameValue+"!");
        ImageView leaves = findViewById(R.id.imageView);
        leaves.setImageResource(R.drawable.fall);
    }
}
