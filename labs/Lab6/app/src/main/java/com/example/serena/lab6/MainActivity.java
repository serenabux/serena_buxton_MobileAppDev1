package com.example.serena.lab6;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void pickDish(View view) {
        //toggle
        ToggleButton toggle = findViewById(R.id.toggleButton);
        boolean type = toggle.isChecked();

        //spinner
        Spinner course = findViewById(R.id.meal);
        String courseType = String.valueOf(course.getSelectedItem());

        //radio
        RadioGroup cost = findViewById((R.id.costRadioGroup));
        int cost_id = cost.getCheckedRadioButtonId();

        //checkboxes
        CheckBox mainCheckBox = findViewById(R.id.CheckBox2);
        boolean main = mainCheckBox.isChecked();
        CheckBox sideCheckBox = findViewById(R.id.CheckBox1);
        boolean side = sideCheckBox.isChecked();

        //switch
        Switch traditionalSwitch = findViewById(R.id.switch1);
        boolean traditional = traditionalSwitch.isChecked();

        if(cost_id == -1){
            //toast
            Context context = getApplicationContext();
            CharSequence text = "Please select a cost level";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context,text, duration);
            toast.show();

        }

        String food;

        if (!type){
            switch(courseType){
                case "Main Course":
                    if (main){
                        if (!traditional){
                            food="Deep Fried Turkey";
                            break;
                        }
                        if(cost_id == R.id.radioButton){
                            food = "Stuffing";
                            break;
                        }
                        else{
                            food = "Turkey";
                            break;
                        }
                    }
                   else if (side){
                        if (!traditional){
                            food = "Roasted Pumpkin with Chili Yogurt";
                            break;
                        }
                        if(cost_id == R.id.radioButton){
                            food = "Mashed Potatoes";
                            break;
                        }
                        else{
                            food = "Roast Veggies";
                            break;
                        }
                    }
                case "Dessert":
                    if (!traditional){
                        food = "Meat Pie";
                        break;
                    }
                    if (main){
                        food="Pumpkin Pie";
                        break;
                    }
                    else if (side){
                        food = "Pumpkin Pie Spice";
                        break;
                    }
                case "Drink":
                    if (!traditional){
                        food = "Hot Spiced Wine";
                    }
                    if (main){
                        if(cost_id == R.id.radioButton){
                            food = "Water";
                            break;
                        }
                        else{
                            food = "Wine";
                            break;
                        }
                    }
                    else if (side){
                        if(cost_id == R.id.radioButton){
                            food = "Spiced Tea";
                            break;
                        }
                        else{
                            food = "Cocktail";
                            break;
                        }
                    }
                default:
                    food = "Turkey";
            }
        }
        else{
            switch(courseType){
                case "Main Course":
                    if (main){
                        if(!traditional){
                            food = "Turkey Apple Sasuage";
                            break;
                        }
                        if(cost_id == R.id.radioButton){
                            food = "Stuffing";
                            break;
                        }
                        else{
                            food = "Turkey";
                            break;
                        }
                    }
                    else if (side){
                        if(!traditional){
                            food="Baked Corn Pudding Casserole";
                            break;
                        }
                        if(cost_id == R.id.radioButton){
                            food = "Cranberry Sauce";
                            break;
                        }
                        else{
                            food = "Sweet Potatoes";
                            break;
                        }
                    }
                case "Dessert":
                    if (main){
                        if(!traditional){
                            food="Pumpkin Pecan Cheesecake";
                            break;
                        }
                        if(cost_id == R.id.radioButton){
                            food = "Apple Pie";
                            break;
                        }
                        else{
                            food = "Cherry Pie";
                            break;
                        }
                    }
                    else if (side){
                        food= "Whipped Cream";
                        break;
                    }
                case "Drink":
                    if (main){
                        if(cost_id == R.id.radioButton){
                            food = "water";
                            break;
                        }
                        else{
                            food = "Wine";
                            break;
                        }
                    }
                    else if (side){
                        if(cost_id == R.id.radioButton){
                            food = "Apple Cider";
                            break;
                        }
                        else{
                            food = "Cocktail";
                            break;
                        }
                    }
                default:
                    food="Turkey";
            }
        }
        TextView userDish = findViewById(R.id.userDishTextView);
        userDish.setText(food);

        ImageView image = findViewById(R.id.imageView);
        image.setImageResource(R.drawable.turkey);
    }
}
