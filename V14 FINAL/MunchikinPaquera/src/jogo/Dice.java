package jogo;

import java.io.Serializable;
import munchikinpaquera.Menu;

public class Dice implements Serializable {

    public static int selectedFace;

    // ROLA O DADO
    public static int roll() {
        int min = 0;
        int max = 6;
        selectedFace = min + (int) (Math.random() * ((max - min)) + 1);
        return selectedFace;
    }

    //M MOSTRA RESULTADO DA JOGADA
    public static void show() {
        Menu.msg(" [ Resultado da Jogada ]  " + selectedFace);
    }

    // RETORNA VALOR DA FACE
    public int getValue() {
        return selectedFace;
    }

}
