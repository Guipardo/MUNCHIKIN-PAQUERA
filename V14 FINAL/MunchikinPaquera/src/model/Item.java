package model;

import java.io.Serializable;

/*
    Bonus - Valor adcionado no charme do paquerador em um encontro
 */
public abstract class Item implements Serializable {

    protected String name; // Nome
    protected int bonus; //Bônus
    protected int price; //Preço

    // Métodos para mostrar o objeto
    public abstract String show();
    public abstract String showSimple();

}
