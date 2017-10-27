package classes;

import java.util.Random;

public class Dice {

    private int value;
    Random sort = new Random(6);

    public Dice() {
    }

    //Mostra o resultado da jogada
    public void show() {
        System.out.println("[Resultado da Jogada] " + value);
    }

    //Retorna o valor do dado
    public int getValue() {
        return value;
    }

    //Rola o dado
    public void roll() {
        value = sort.nextInt() + 1;
    }

}
