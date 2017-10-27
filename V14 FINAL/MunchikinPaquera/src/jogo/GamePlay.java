package jogo;

import java.io.Serializable;
import java.util.ArrayList;

public class GamePlay implements Serializable {

    private ArrayList<Meeting> meets = new ArrayList<>();
    private int number;

    public GamePlay() {
        this.number = 0;
    }

    // MOSTRA A LISTA DE GAMEPLAYS
    public String show() {
        String text = " [ Gameplay ] \n";
        for (int i = 0; i < meets.size(); i++) {
            text += meets.get(i).show();
            text += "\n";
        }
        return text;
    }

    // ADCIONA UM ENCONTRO NA GAMEPLAY 
    public void addMeet(Meeting m) {
        if (meets.size() < 10) {
            meets.add(m);
        } else {
            meets.remove(0);
            meets.add(m);
        }
    }

    // GETTERS E SETTERS
    public int getNumber() {
        return number;
    }

    public void setMeets(ArrayList<Meeting> meets) {
        this.meets = meets;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public ArrayList<Meeting> getMeets() {
        return meets;
    }

}
