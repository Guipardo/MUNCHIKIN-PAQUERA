package classes;

import java.util.ArrayList;

public class GamePlay {

    ArrayList<Round> rounds = new ArrayList<>();

    public GamePlay() {
    }

    //Retorna uma string com a lista de Gameplays
    public String show() {
        String text = "[Gameplay]\n";
        for (int i = 0; i < rounds.size(); i++) {
            text += rounds.get(i).show();
            text += "\n";
        }
        return text;
    }

    //Adciona um Round em uma Gameplay 
    public void addRound(Round r) {
        rounds.add(r);
    }

}
