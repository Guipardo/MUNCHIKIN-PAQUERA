package classes;

import java.util.ArrayList;

public class GamePlay {

    ArrayList<Meeting> meets = new ArrayList<>();

    public GamePlay() {
    }

    //Retorna uma string com a lista de Gameplays
    public String show() {
        String text = "[ Gameplay ]\n";
        for (int i = 0; i < meets.size(); i++) {
            text += meets.get(i).show();
            text += "\n";
        }
        return text;
    }

    //Adciona um Round em uma Gameplay 
    public void addMeet(Meeting m) {
        meets.add(m);
    }

}
