package com.example.serena.fall18_final;

public class Pizza {
    private String pizzaPlace;
    private String pizzaPlaceURL;

    public void idealPizzaPlace(String crust, boolean glutenFree){
        if(crust == "thin"){
            pizzaPlace = "Pizzeria Locale";
            pizzaPlaceURL = "https://localeboulder.com/";
        }
        else{
            pizzaPlace = "Backcountry Pizza";
            pizzaPlaceURL = "https://backcountrypizzaandtaphouse.info/";
        }
        if (glutenFree){
            pizzaPlace = "Boss Lady";
            pizzaPlaceURL = "https://bossladypizza.com/";
        }
    }

    public String getPizzaPlace() {
        return pizzaPlace;
    }

    public void setPizzaPlace(String pizzaPlace) {
        this.pizzaPlace = pizzaPlace;
    }

    public String getPizzaPlaceURL(){
        return pizzaPlaceURL;
    }

    public void setPizzaPlaceURL(String pizzaPlaceURL) {
        this.pizzaPlaceURL = pizzaPlaceURL;
    }
}
