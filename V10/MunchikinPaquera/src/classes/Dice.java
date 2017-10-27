package classes;

import java.util.Random;

public class Dice {

    private static Dice instance;
    public int selectedFace;

    private Dice() {
        this.selectedFace = 1;
    }

    public static Dice getInstance() {
        if (instance == null) {
            instance = new Dice();
        }
        return instance;
    }

    //Rola o Dado
    public int roll(){
        int min = 0;
        int max = 6;
        selectedFace = min + (int)(Math.random() * ((max - min)) + 1); 
        return selectedFace;
    }
    
    private int value;
    Random sort = new Random(6);

    //Mostra o resultado da jogada
    public void show() {
        System.out.println("[ Resultado da Jogada ] " + selectedFace);
    }

    //Retorna o valor do dado
    public int getValue() {
        return selectedFace;
    }


}
