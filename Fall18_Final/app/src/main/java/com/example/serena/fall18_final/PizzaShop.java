package com.example.serena.fall18_final;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class PizzaShop extends Activity {

    private String shopName;
    private String shopURL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pizza_shop);

        Intent intent = getIntent();
        shopName = intent.getStringExtra("shop");
        shopURL = intent.getStringExtra("pizzaURL");

        TextView pizzaShopText = findViewById(R.id.textView4);
        pizzaShopText.setText(shopName);

    }

    public void loadWebSite(View view){
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(shopURL));
        startActivity(intent);
    }

}
