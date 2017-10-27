package model;

import java.io.Serializable;

public class Lady implements Serializable {

    private int defense; // Defesa 
    private String name; // Nome
    private Style itsMyType; // Faz meu tipo
    private String phrase; // Frase de entrada
    private String win; // Frase de vitória
    private String lose; // Frase de derrota

    // CONSTRUTORES
    public Lady(String name, int defense, Style itsMyType, String phrase, String win, String lose) {
        this.defense = defense;
        this.name = name;
        this.itsMyType = itsMyType;
        this.phrase = phrase;
        this.win = win;
        this.lose = lose;
    }

    public Lady() {
    }

    // FICHA
    public String show() {
        return "\n[ " + getName() + " ] Defesa [ " + getDefense()
                + " ]\n Faz meu Tipo [ " + getItsMyType() + " ] "
                + "\n\n - " + phrase;
    }

    // GETTERS E SETTERS
    public String getName() {
        return name;
    }

    public int getDefense() {
        return defense;
    }

    public Style getItsMyType() {
        return itsMyType;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setItsMyType(Style itsMyType) {
        this.itsMyType = itsMyType;
    }

    public String getWin() {
        return win;
    }

    public void setWin(String win) {
        this.win = win;
    }

    public String getLose() {
        return lose;
    }

    public void setLose(String lose) {
        this.lose = lose;
    }

}
