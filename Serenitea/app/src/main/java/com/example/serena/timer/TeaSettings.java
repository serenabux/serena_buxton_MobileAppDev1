package com.example.serena.timer;

public class TeaSettings {
    private int desiredDrinkTemp = 140;
    private boolean coolToDesired = true;
    private boolean coolToSteep = true;

    public void setDesiredDrinkTemp(int desiredDrinkTemp) {
        this.desiredDrinkTemp = desiredDrinkTemp;
    }

    public int getDesiredDrinkTemp() {
        return desiredDrinkTemp;
    }

    public void setCoolToDesired(boolean coolToDesired) {
        this.coolToDesired = coolToDesired;
    }

    public boolean isCoolToDesired() {
        return coolToDesired;
    }

    public void setCoolToSteep(boolean coolToSteep) {
        this.coolToSteep = coolToSteep;
    }

    public boolean isCoolToSteep() {
        return coolToSteep;
    }
}
