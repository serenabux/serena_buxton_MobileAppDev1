package com.example.serena.lab7;

public class Santa {
    private String message;
    private String santaURL;

    private void setInfo(Boolean believer, String userName){
        if(believer){
            message = "Merry Christmas "+ userName +"!";
            santaURL = "https://santatracker.google.com/village.html";
        }
        else {
            message = "Seeing isn't believing. Believing is seeing";
            santaURL = "https://www.emailsanta.com/Santa-Claus-FAQ/is-santa-real.asp";
        }
    }

    public void setMessage(Boolean believer, String userName){
        setInfo(believer,userName);
    }
    public void setSantaURL(Boolean believer, String userName){
        setInfo(believer,userName);
    }

    public String getMessage(){
        return message;
    }
    public String getSantaURL(){
        return santaURL;
    }
}
