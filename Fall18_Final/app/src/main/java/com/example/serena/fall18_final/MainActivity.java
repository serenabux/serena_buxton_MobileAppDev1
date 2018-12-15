package com.example.serena.fall18_final;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

//Serena Buxton Final

public class MainActivity extends Activity {

    Pizza myPizza = new Pizza();
    private String pizzaPlace = "Marcos";
    private String pizzaUrl = "https://www.marcos.com/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void generatePizza(View view){
        //get the name from text view
        TextView name = findViewById(R.id.pizzaName);
        String pizName = name.getText().toString();

        //get sauce type
        ToggleButton toggle = findViewById(R.id.toggleButton);
        boolean sauce = toggle.isChecked();

        //get crust type
        RadioGroup radGroup = findViewById(R.id.radioGroup);
        int crust_ID = radGroup.getCheckedRadioButtonId();
        //check radio buttons
        if(crust_ID == -1){
            //toast
            Context context = getApplicationContext();
            CharSequence text = "No crust selected";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context,text, duration);
            toast.show();
            return;
        }
        String crust;
        if(crust_ID == R.id.radioButton) crust = "thin";
        else crust = "thick";


        //get checkboxes
        CheckBox check1 = findViewById(R.id.checkBox1);
        boolean pepperoni = check1.isChecked();
        CheckBox check2 = findViewById(R.id.checkBox2);
        boolean mushrooms = check2.isChecked();
        CheckBox check3 = findViewById(R.id.checkBox3);
        boolean onions = check3.isChecked();
        CheckBox check4 = findViewById(R.id.checkBox4);
        boolean sausage = check4.isChecked();

        //get spinner
        Spinner sizeSpinner = findViewById(R.id.spinner);
        int sizeID = sizeSpinner.getSelectedItemPosition();


        //get switch
        Switch glutenSwitch = findViewById(R.id. switch1);
        boolean glutenFree = glutenSwitch.isChecked();

        //generate string for text view of pizza description
        //toppings
        String toppings = "";
        if(pepperoni) toppings += " pepperoni";
        if(mushrooms) toppings += " mushrooms";
        if(onions) toppings += " onions";
        if(sausage) toppings += " sausage";
        //get if gluten free
        String gluten = "";
        if(glutenFree) gluten= "gluten-free ";
        //get size
        String size;
        switch (sizeID){
            case(0): {
                size = "small";
                break;
            }
            case (1):{
                size = "medium";
                break;
            }
            default:{
                size = "large";
            }
        }
        //get sauce
        String sauceType = "white";
        if(sauce) sauceType = "red";
        //overall string
        String userPizza = "The "+ pizName + " is a "+ size + ", "+ crust+ " crust " +gluten + "pizza with " + sauceType + " and the toppings:" + toppings;
        TextView descrip = findViewById(R.id.resultPizza);
        descrip.setText(userPizza);

        //set Image
        ImageView image = findViewById(R.id.pizzaImage);
        if(!pepperoni && !sausage && !mushrooms && !onions)
            image.setImageResource(R.drawable.pizza_cheese);
        else if ((pepperoni || sausage) && !mushrooms && !onions)
            image.setImageResource(R.drawable.pizza_meat);
        else if((!pepperoni && !sausage && (mushrooms || onions)))
            image.setImageResource(R.drawable.pizza_veggie);
        else
            image.setImageResource(R.drawable.pizza_supreme);

        //find ideal pizza shop
        myPizza.idealPizzaPlace(crust,glutenFree);
        pizzaPlace = myPizza.getPizzaPlace();
        pizzaUrl = myPizza.getPizzaPlaceURL();
        Log.i("pizzaShop", pizzaPlace);

    }
     public void findPizza(View view){
         Intent intent = new Intent(this, PizzaShop.class);
         intent.putExtra("shop", pizzaPlace);
         intent.putExtra("pizzaURL", pizzaUrl);
         startActivity(intent);
     }




}
