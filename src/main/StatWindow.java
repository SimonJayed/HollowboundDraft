package main;

import forms.CharacterPICK;
import forms.GameWindowForm;


public class StatWindow extends GameWindowForm {
    GamePanel gp;
    public StatWindow(GamePanel gp){
        this.gp = gp;
    }

    public void popUp(){
        StatWindow statW = new StatWindow(gp);

        setContentPane(statW.getContentPane());
        setVisible(true);
    }
}
